/**
  * Created by mrsas on 01/06/2017.
  */
case class NodoHoja(val caracter: Char, val contador: Int) extends Nodo {
  override def toString: String = "Hoja: " + caracter + " -> " + contador + "\n"
}
