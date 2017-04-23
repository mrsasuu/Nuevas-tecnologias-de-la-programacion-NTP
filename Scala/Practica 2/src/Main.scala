import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

/**
  * Objeto singleton para probar la funcionalidad del triangulo
  * de Pascal
  */
object Main {

  /**
    * Metodo main: en realidad no es necesario porque el desarrollo
    * deberia guiarse por los tests de prueba
    *
    * @param args
    */
  def main(args: Array[String]) {
    println("................... Triangulo de Pascal ...................")

    // Se muestran 10 filas del trinagulo de Pascal
    for (row <- 0 to 10) {
      // Se muestran 10 10 columnas
      for (col <- 0 to row)
        print(calcularValorTrianguloPascal(col, row) + " ")

      // Salto de linea final para mejorar la presentacion
      println()
    }

    // Se muestra el valor que debe ocupar la columna 5 en la fila 10
    println(calcularValorTrianguloPascal(10, 15))
    println(calcularValorTrianguloPascal(0, 0))

    val listaCorrecta = List('(', '(',')',')')
    val listaIncorrecta = List(')', '(', '(', ')', ')',')')

    if(chequearBalance(listaCorrecta))
      println("La lista 1 es correcta")

    if(!chequearBalance(listaIncorrecta))
      println("La lista 2 es incorrecta")

    var array1:Array[Int] = Array(0, 1, 5, 20, 35, 57, 98, 123, 215)
    println(ordenado(array1, (x:Int,y:Int) => (x < y)))

    import scala.collection.mutable.ArrayBuffer

    println(busqueda(array1,20,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)))

  }

  /**
    * Ejercicio 1: funcion para generar el triangulo de Pascal
    *
    * @param columna número entero que indicará que columna se desea calcular.
    * @param fila número entero que indicará que fila se desea calcular.
    * @return el valor correspondiente a la fila y la columna de la pirámide de Pascal
    */

  //@annotation.tailrec
  def calcularValorTrianguloPascal(columna: Int, fila: Int): Int = {
    /**
      * Si nos encontramos en los extremos de la fila o en la 1 columna nos encontraremos en el caso base, y por tanto
      * equivaldrá a 1.
      *
      * Sino llamaremos a la misma función con una fila y una columna menos más el valor de la fila anterior.
      */
    if(fila == 0 || fila == columna || columna == 0)
      1
    else
      calcularValorTrianguloPascal(columna-1, fila-1) + calcularValorTrianguloPascal(columna, fila-1)
  }

  /**
    * Ejercicio 2: funcion para chequear el balance de parentesis
    *
    * @param cadena cadena a analizar
    * @return valor booleano con el resultado de la operacion
    */
  def chequearBalance(cadena: List[Char]): Boolean = {

    /**
      *
      * La función servira como apoyo al metodo chequearBalance para contar el numero de parentesis abiertos y cerrados que hay
      * @param cadena
      * @param indiceFun indice que va indicando la cuenta de parentesis. Si ese indice es negativo en algun momento los parentesis
      *                  estan mal formados
      * @return devuelve 0 cuando la cadena no tiene mas parentesis, devuelve 1 si el parentesis es ( y devuelve -1 si es )
      *         si no queda ningun parentesis y solo otros caracteres se devuelve 0
      */
    @annotation.tailrec
     def comprobarParentesis(cadena: List[Char],indiceFun : Int): Int ={
       var indice = 0
       var condicion = true
       var cadena2 = cadena

      /**
        * Si el balance de paréntesis es negativo quiere decir que está mal construida la expresión, así que terminamos la función
        */
       if(indiceFun <0)
         return -1

      /**
        * Si la cadena está vacia quiere decir que no quedan elementos que analizar y por tanto este segmento está balanceado y devolvemos 0.
        *
        * Si no está vacia tenemos que ir filtrando todos los caracteres hasta encontrar el próximo ( o ).
        */
       if(cadena.isEmpty)
         0
       else{
         while(condicion && !cadena2.isEmpty){
           /**
             * Si nos encontramos un ( sumamos 1 al contador de paréntesis y paramos el bucle.
             * Si encontramos un ) restamos 1 al contador de paréntesis y paramos el bucle.
             */
           if(cadena2(0) == '('){
             indice += 1
             condicion = false
           }else if(cadena2(0) == ')'){
             indice -= 1
             condicion = false
           }
           print(cadena2(0))
           /**
             * Por cada elemento que encontremos sea (, ) o cualquier otro valor lo quitamos de la cabeza de la lista ya que ha sido analizado.
             */
           cadena2 = cadena2.tail

         }
         /**
           * Una vez hemos analizado el paréntesis volveremos a llamar a la función con la nueva lista y los índices actualizados.
           */
         comprobarParentesis(cadena2,indice+indiceFun)
       }
     }

    /**
      * Si el resultado es 0 quiere decir que está balanceado, sino en cualquier otro caso (mal formado o no balanceado) devolveremos false.
      */
    if(comprobarParentesis(cadena,0) == 0)
      true
    else
      false

  }



  /**
    * Ejercicio 3: funcion para determinar las posibles formas de devolver el
    * cambio de una determinada cantidad con un conjunto de monedas
    *
    * @param cantidad
    * @param monedas
    * @return contador de numero de vueltas posibles
    * @author Antonio Javier Benítez Guijarro.
    */
  /*
  def contarCambiosPosibles(cantidad: Int, monedas: List[Int]): Int = {
     // A rellenar
  }*/

  def ordenado[A](array:Array[A], comparar:(A,A) => Boolean) : Boolean = {
    @annotation.tailrec
    def iterar(indice:Int) : Boolean = {
      if(indice == array.length-2)
        comparar(array(indice),array(indice+1))
      else if(!comparar(array(indice), array(indice+1)))
        false
      else
        iterar(indice+1)

    }

    iterar(0)
  }

  /**
    *
    * Función para buscar un elemento de un tipo A en un vector de valores del mismo tipo que el elemento que buscamos.
    *
    * @param array Vector en el que se realizará la busqueda binaria.
    * @param elemento Valor que se buscará dentro del vector.
    * @param mayor Función de criterio que indicará cuando el primer elemento X es mayor que el segundo elemento Y.
    * @param comparar Función de criterio que servirá para comprobar si están ordenados los elementos.
    * @tparam A Tipo de los elementos del vector.
    * @return -1 cuando el elemento no está en el vector. -2 si el vector no está ordenado ascendentemente.
    *         En cualquier otro caso devolverá el indice en la posición en la que se encuentre el elemento.
    * @author Antonio Javier Benítez Guijarro.
    */
  def busqueda[A](array:Array[A], elemento : A, mayor:(A,A) => Boolean, comparar:(A,A) => Boolean) : Int = {

    /**
      * Si el vector no está ordenado devolveremos -2.
      */
    if(!ordenado(array, comparar))
      return -2

    /**
      * Función recursiva. Es el núcleo de la funcion busqueda.
      *
      * @param array2 Vector en el que se realizará la busqueda binaria.
      * @param indiceInf Parámetro que indica el inicio del vector por el que se empezará a buscar. Por defecto para empezar será el 0.
      * @param indiceSup Parámetro que indica el final del vector por el que se terminará de buscar. Por defecto inicialmente será el final del vector.
      * @return La función devolverá -1 si el elemento no está en el vector y en cualquier otro caso devolverá el indice donde se encuentra el elemento.
      * @author Antonio Javier Benítez Guijarro.
      */
    @annotation.tailrec
    def Buscar(array2:Array[A],indiceInf:Int, indiceSup:Int) : Int = {

      /**
        * Calcularemos la posición mitad de nuestro vector a partir de los indices inferior y superior.
        */
      var mitad : Int = (indiceInf+indiceSup)/2

      /**
        * Si el valor que hay en la posición mitad del vector (según los índices inferior y superior en este momento) es igual al valor del elemento que buscamos
        * entonces habremos encontrado nuestro elemento y deberemos retornar la posición de dicho elemento.
        *
        * Si por el contrario no es igual, comprobaremos si el valor del elemento que buscamos es mayor al de la posición mitad del vector según
        * el criterio de la condición mayor(X,Y). Si el valor del elemento que buscamos es mayor entonces volveremos a llamar a la función
        * buscar con los índices actualizados Buscar(array2,mitad+1,indiceSup)
        *
        * Si por el contrario el valor del elemento que buscamos no es mayor, volveremos a llamar a la función buscar con los índices actualizados Buscar(array2,indiceInf,mitad).
        */
      if(array2(mitad) == elemento)
        mitad
      else if (mayor(elemento,array2(mitad))){
        /**
          * Si los índices se sobrepasan quiere decir que el elemento que buscamos no se encuentra dentro del vector, así que retornamos -1.
          */
        if(indiceInf >= indiceSup  )
          return -1
        Buscar(array2,mitad+1,indiceSup)
      }else{
        /**
          * Si los índices se sobrepasan quiere decir que el elemento que buscamos no se encuentra dentro del vector, así que retornamos -1.
          */
        if(indiceInf >= indiceSup  )
          return -1
        Buscar(array2,indiceInf,mitad)
      }
    }

    /**
      * Haremos la primera llamada con los índices en el principio del vector y su final.
      */
    Buscar(array,0,array.length-1)
  }

}





