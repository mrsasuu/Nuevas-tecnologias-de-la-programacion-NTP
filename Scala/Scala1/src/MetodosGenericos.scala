/**
  * Created by mrsas on 28/03/2017.
  */
object MetodosGenericos extends App{

  /**
    *
    * @param lista
    * @tparam A
    * @return
    */
  def eliminarPrimero[A](lista : List[A]) = lista.tail

  var resultado = eliminarPrimero(List(1,2,3))

  var resultado2 = eliminarPrimero(List(3.5,8.3,2.9))

  var resultado3 = eliminarPrimero(List("hola","adios"))
}
