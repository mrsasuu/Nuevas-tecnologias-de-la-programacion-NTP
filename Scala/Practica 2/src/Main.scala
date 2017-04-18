
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
  }

  /**
    * Ejercicio 1: funcion para generar el triangulo de Pascal
    *
    * @param columna
    * @param fila
    * @return
    */

  //@annotation.tailrec
  def calcularValorTrianguloPascal(columna: Int, fila: Int): Int = {
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

    @annotation.tailrec
    /**
      *
      * La función servira como apoyo al metodo chequearBalance para contar el numero de parentesis abiertos y cerrados que hay
      * @param cadena
      * @param indiceFun indice que va indicando la cuenta de parentesis. Si ese indice es negativo en algun momento los parentesis
      *                  estan mal formados
      * @return devuelve 0 cuando la cadena no tiene mas parentesis, devuelve 1 si el parentesis es ( y devuelve -1 si es )
      *         si no queda ningun parentesis y solo otros caracteres se devuelve 0
      */
     def comprobarParentesis(cadena: List[Char],indiceFun : Int): Int ={
       var indice = 0
       var condicion = true
       var cadena2 = cadena

       if(indiceFun <0)
         return -1

       if(cadena.isEmpty)
         0
       else{
         while(condicion && !cadena2.isEmpty){
           if(cadena2(0) == '('){
             indice += 1
             condicion = false
           }else if(cadena2(0) == ')'){
             indice -= 1
             condicion = false
           }
           print(cadena2(0))
           cadena2 = cadena2.tail

         }

         comprobarParentesis(cadena2,indice+indiceFun)
       }
     }

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
    */
  /*
  def contarCambiosPosibles(cantidad: Int, monedas: List[Int]): Int = {
     // A rellenar
  }*/
}
