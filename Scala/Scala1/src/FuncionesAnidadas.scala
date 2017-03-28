/**
  * Created by mrsas on 28/03/2017.
  */
object FuncionesAnidadas extends App{
  /**
    *
    * @param x
    * @param y
    * @param z
    * @return
    */
  def max(x:Int,y:Int,z:Int)={
    // Funcion auxiliar para dos valores
    def max(x:Int,y:Int) = if(x > y) x else y

    max(x,max(y,z))
  }


}
