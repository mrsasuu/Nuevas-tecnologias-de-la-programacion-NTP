/**
  * Created by mrsas on 28/03/2017.
  */
object ListaArgumentosVariables extends App{

  def sumar(numero : Int *):Int = {
    var total = 0

    for(i <- numero){
      total += i
    }
    total
  }

  var resultado = sumar(1)
  resultado = sumar(2,3)
  resultado = sumar(1,2,3,4,5,6,7)

  // Funciones con varias listas de argumentos
  def max(x:Int)(y:Int) = if(x > y) x else y

  // Podemos dejar un argumento libre con _ y con ello en f se guardara la funcion que podremos invocar luego.
  val f =  max(3)_

  println(f(1))
  println(f(10))




}
