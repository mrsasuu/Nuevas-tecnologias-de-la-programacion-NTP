/**
  * Created by Sasu on 10/05/2017.
  */
/**
  * Clase para representar conjuntos definidos mediante una funcion
  * caracteristica (un predicado). De esta forma, se declara el tipo
  * conjunto como un predicado que recibe un entero (elemento) como
  * argumento y dvuelve un valor booleano que indica si pertenece o no
  * al conjunto
  *
  * @param funcionCaracteristica
  */
class Conjunto(val funcionCaracteristica: Int => Boolean) {
  /**
    * Crea una cadena con el contenido completo del conjunto
    *
    * @return
    */
  override def toString(): String = {
    val elementos = for (i <- -Conjunto.LIMITE to Conjunto.LIMITE
                         if funcionCaracteristica(i)) yield i
    elementos.mkString("{", ",", "}")
  }

  /**
    * Metodo para determinar la pertenencia de un elemento al
    * conjunto
    * @param elemento
    * @return valor booleano indicando si elemento cumple
    * la funcion caracteristica o no
    */
  def apply(elemento: Int): Boolean = {
    funcionCaracteristica(elemento)
  }

}


/**
  * Objecto companion que ofrece metodos para trabajar con
  * conjuntos
  */
object Conjunto {
  /**
    * Función que a partir de un elemento devuelve un conjunto para ese único elemento.
    * @param elemto único elemento perteneciente al conjunto.
    * @return conjunto resultante con la modificación correspondiente.
    */
  def conjuntoUnElemento(elemto : Int) : Conjunto ={

    Conjunto((x:Int) => x == elemto)
  }

  /**
    *
    * @param conjunto1 primer conjunto de elementos.
    * @param conjunto2 segundo conjunto de elementos.
    * @return conjunto resultante de la unión de los conjunto parámetros conjunto1 y conjunto2.
    *         El conjunto resultante estará formado por todos los elementos de conjunto1 + conjunto2
    */
  def union(conjunto1: Conjunto, conjunto2: Conjunto) : Conjunto ={
    Conjunto((x:Int) => conjunto1(x)||conjunto2(x))
  }

  /**
    *
    * @param conjunto1 primer conjunto de elementos.
    * @param conjunto2 segundo conjunto de elementos.
    * @return conjunto resultante de la intersección de los conjuntos parámetros conjunto1 y conjunto2.
    *         El conjunto resultante serán todos los elementos que aparezcan tanto en conjunto1 como en conjunto2.
    */
  def interseccion(conjunto1: Conjunto, conjunto2: Conjunto) : Conjunto ={
    Conjunto((x:Int) => conjunto1(x)&&conjunto2(x))
  }

  /**
    *
    * @param conjunto1 primer conjunto de elementos.
    * @param conjunto2 segundo conjunto de elementos.
    * @return conjunto resultante de la resta de los conjuntos parámetros conjunto1 y conjunto2.
    *         El conjunto resultante serán todos los elementos de conjunto1 que no aparecen en conjunto2.
    */
  def diferencia(conjunto1: Conjunto, conjunto2: Conjunto): Conjunto ={
    Conjunto((x:Int) => (conjunto1(x))&&(!conjunto2(x)))
  }

  /**
    *
    * @param c conjunto de elementos.
    * @param predicado función para filtrar el conjunto.
    * @return conjunto resultante del uso de la función filtro sobre los elementos del conjunto parámetros c.
    *         El conjunto resultante estará formado por los elementos que estuviesen dentro del conjunto c inicial que a su vez
    *         satisfacen la función filtro.
    */
  def filtrar(c : Conjunto, predicado : Int => Boolean) : Conjunto ={
    Conjunto((x:Int) => (c(x))&&(predicado(x)))
  }

  /**
    *
    * @param c conjunto sobre el que se va a trabajar.
    * @param predicado función que servirá de filtro.
    * @return devulverá true si existe algún elemento en el conjunto que cumpla la función predicado. Si ningún elemento
    *         cumple dicha función se devolverá false.
    */
  def existe(c : Conjunto, predicado : Int => Boolean) : Boolean ={
    /**
      * Se trata de una función auxiliar para iterar sobre el conjunto de elementos desde -Limite hasta Limite.
      * @param elemento elemento que se analizará si cumple o no el predicado
      * @return devuelve true si el elemento cumple el predicado. false si no cumple el predicado.
      */
    def iterar(elemento : Int) : Boolean = {
      if( c(elemento) && predicado(elemento) ){
        true
      }
      else if( elemento > Conjunto.LIMITE){
        false
      }
      else{
        iterar(elemento+1)
      }
    }
    iterar(-LIMITE)

  }

  /**
    *
    * @param conjunto conjunto sobre el que se va a trabajar.
    * @param predicado función que servirá de filtro.
    * @return devulverá true si todos los elementos en el conjunto que cumplen la función predicado. Si algún elemento
    *         no cumple dicha función se devolverá false.
    */
    def paraTodo(conjunto : Conjunto, predicado : Int => Boolean) : Boolean = {
      /**
        * Se trata de una función auxiliar para iterar sobre el conjunto de elementos desde -Limite hasta Limite.
        * @param elemento elemento que se analizará si cumple o no el predicado
        * @return devuelve true si el elemento cumple el predicado. false si no cumple el predicado.
        */
      def iterar(elemento : Int) : Boolean = {
        /**
          * Si hemos llegado al final del límite devolveremos true.
          *
          * Si el elemento a analizar no esta en el conjunto, analizaremos el siguiente elemento.
          *
          * Si el elemento si está en el conjunto evaluaremos si cumple el predicado y buscaremos el siguiente elemento.
          * Al usarse el operador && en cuanto un elemento de false todos los demás serán falsos y responderá con que no
          * se cumple dicho predicado para todo el conjunto.
          *
          */
        if( elemento > Conjunto.LIMITE){
          true
        }
        else if (!conjunto(elemento)){
          iterar(elemento+1)
        }
        else{
          predicado(elemento) && iterar(elemento+1)
        }
      }

      iterar(-LIMITE)
    }

  /**
    *
    * @param c conjunto sobre el que se realizará una transformación.
    * @param funcion función para transformar el conjunto.
    * @return se devolverá un nuevo conjunto con la transformación aplicada por el parámetro funcion.
    */
  def map(c : Conjunto, funcion : Int => Int) : Conjunto ={
    Conjunto((x : Int) => existe(c, (y:Int) => funcion(y) == x))
  }




  /**
    * Limite para la iteracion necesaria algunas operaciones,
    * entre -1000 y 1000
    */
  private final val LIMITE = 1000

  /**
    * Metodo que permite crear objetos de la clase Conjunto
    * de forma sencilla
    * @param f
    * @return
    */
  def apply(f: Int => Boolean): Conjunto = {
    new Conjunto(f)
  }

}

