/**
  * Created by mrsas on 24/04/2017.
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

  def testChequearBalance ={
    val listaCorrecta = List('(', '(',')',')')
    val listaIncorrecta = List(')', '(', '(', ')', ')',')')

    assert(chequearBalance(listaCorrecta))

    assert(!chequearBalance(listaIncorrecta))
  }

  def testContarCambiosPosibles ={
    assert(true)
  }

  def testBusqueda ={
    assert(true)
  }



}
