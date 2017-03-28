/**
  * Created by mrsas on 28/03/2017.
  */
object Factorial extends App{

  // Se queja porque no es tail recursive @annotation.tailrec
  def factorial(x:Int):Int = {
    if(x == 0) 1
    else x * factorial( x-1 )
  }

  val fact15 = factorial(15)

  println(fact15)

  // Tail recursion. La ultima operacion es la llamada a la recursividad.
  // Pero esta funcion no es así porque la ultima operacion es el producto

  @annotation.tailrec
  def factorial2(x:Int, acum:Int=1):Int ={ // Podemos poner valores por defecto
    if(x == 0||x == 1) acum
    else factorial2(x-1,x*acum)
  }

  //Ahora la funcion anterior seria Tail recursive y por ello seria mas optimo

  val fact15V2 = factorial2(15,1)
  println(fact15V2)
  val fact15V21 = factorial2(15)
  println(fact15V21)
  val fact15V22 = factorial2(acum = 3,x = 15) // Mecanimsmo de llamadas con nombre
  println(fact15V21)

  def factorial3(x:BigInt):BigInt = {
    @annotation.tailrec
    def auxiliar(x:BigInt, acum:BigInt ):BigInt ={
      if(x == 0 || x == 1) acum
      else auxiliar(x-1,x*acum)
    }

    auxiliar(x,1)
  }
  // Asi tendremos una funcion recursiva mas "natural" y que seria exactamente igual que las anteriores
  // y ademas Tail recursive

  val fact15V3 = factorial3(30)
  println(fact15V3)

}
