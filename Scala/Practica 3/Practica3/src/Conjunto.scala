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
  def union(conjunto1: Conjunto, conjunto2: Conjunto) : Conjunto ={
    Conjunto((x:Int) => conjunto1(x)||conjunto2(x))
  }

  def interseccion(conjunto1: Conjunto, conjunto2: Conjunto) : Conjunto ={
    Conjunto((x:Int) => conjunto1(x)&&conjunto2(x))
  }

  def diferencia(conjunto1: Conjunto, conjunto2: Conjunto): Conjunto ={
    Conjunto((x:Int) => (conjunto1(x))&&(!conjunto2(x)))
  }

  def filtrar(c : Conjunto, predicado : Int => Boolean) : Conjunto ={
    Conjunto((x:Int) => (c(x))&&(predicado(x)))
  }

  def existe(conjunto: Conjunto, function: (Nothing) => Any) = ???

  def paraTodo(conjunto: Conjunto, function: (Nothing) => Any) : Boolean = {
    def paraTodo(conjunto : Conjunto, predicado : Int => Boolean) : Boolean = {
      def iterar(elemento : Int) : Boolean = {
        if(???) ???
        else if (???) ???
        else predicado(elemento) && iterar(???)
      }
      iterar(-LIMITE)
    }


    false
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

