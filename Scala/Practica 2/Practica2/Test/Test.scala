/**
  * Created by Antonio Javier Benítez Guijarro.
  */
import junit.framework.TestCase
import Main.calcularValorTrianguloPascal
import Main.chequearBalance
import Main.contarCambiosPosibles
import Main.busqueda

class Test extends TestCase{

  def testCalcularValorTrianguloPascal ={
    assert(calcularValorTrianguloPascal(0,2) == 1)
    assert(calcularValorTrianguloPascal(0,0) == 1)
    assert(calcularValorTrianguloPascal(1,2) == 2)
    assert(calcularValorTrianguloPascal(1,3) == 3)
    assert(calcularValorTrianguloPascal(5,10) == 252)
    assert(calcularValorTrianguloPascal(10,15) == 3003)
  }

  /**
    * Test de la función chequearBalance.
    * En dicho test lo que comprobaremos es que para dos listas dadas, una correcta y otra incorrecta el resultado que se nos ofrece es el correcto.
    */
  def testChequearBalance ={
    val listaCorrecta = List('(', '(',')',')')
    val listaCorrecta2 = List('(','a','b', '(','c',')',')')
    val listaIncorrecta = List(')', '(', '(', ')', ')',')')
    val listaIncorrecta2 = List( '(','(', ')', ')',')')

    assert(chequearBalance(listaCorrecta))
    assert(chequearBalance(listaCorrecta2))
    assert(!chequearBalance(listaIncorrecta))
    assert(!chequearBalance(listaIncorrecta2))
  }

  /**
    * Test de la función contarCambiosPosibles.
    * Para evaluar este test comprobaremos que la función devuelve una cantidad de formas posibles de cambio para
    * una cantidad determinada y con una lista de monedas determinadas.
    *
    * Comprobaremos a su vez que dicha funcionalidad se ejecuta correctamente ofreciendole la lista desordenada.
    *
    * Y comprobaremos que funciona también para listas vacias o con listas de distinto tamaño.
    */
  def testContarCambiosPosibles ={

    val monedas = List(1,2,3)

    assert(contarCambiosPosibles(6,monedas)== 7)

    val monedasDesordenado = List(2,1,3)

    assert(contarCambiosPosibles(6,monedasDesordenado)== 7)


    assert(contarCambiosPosibles(5,monedas)== 5)

    val monedas2 = List(1,2)
    assert(contarCambiosPosibles(4,monedas2)== 3)

    val monedas3 = List()
    assert(contarCambiosPosibles(4,monedas3)== 0)

  }

  def testBusqueda ={

    val array1:Array[Int] = Array(0, 1, 5, 20, 35, 57, 98, 123, 215)

    /**
      * Comprobaremos que la función devuelve correctamente la posición del elemento buscado.
      */
    assert(busqueda(array1,0,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == 0)
    assert(busqueda(array1,1,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == 1)
    assert(busqueda(array1,35,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == 4)
    assert(busqueda(array1,123,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == 7)
    assert(busqueda(array1,215,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == 8)

    /**
      * Comprobaremos que la función devuelve -1 para un elemento que no pertenece al vector
      */
    assert(busqueda(array1,-1,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == -1)
    assert(busqueda(array1,2,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == -1)
    assert(busqueda(array1,30,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == -1)
    assert(busqueda(array1,99999,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == -1)

    /**
      * Comprobaremos que la función devuelve -2 para un vector no ordenado.
      */
    val array2:Array[Int] = Array(-1, 1, 5, 20, 35, 57, 98, 123, 0)
    val array3:Array[Int] = Array(1, 0, 5, 4, 35, 57, 98, 123, 215)
    val array4:Array[Int] = Array(0, 1, 5, 20, 500, 57, 98, 123, 215)

    assert(busqueda(array2,35,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == -2)
    assert(busqueda(array3,35,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == -2)
    assert(busqueda(array4,35,(x:Int,y:Int) => (x > y),(x:Int,y:Int) => (x < y)) == -2)

  }



}
