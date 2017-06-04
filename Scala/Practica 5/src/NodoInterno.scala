/**
  * Created by mrsas on 01/06/2017.
  */
case class NodoInterno (val hijoIzquierda: Nodo, val hijoDerecha: Nodo, val caracteres: List[Char], val contador: Int) extends Nodo {
  override def toString: String = "Interno: " + caracteres + " -> " + contador + "\n{\n1.- " + hijoIzquierda.toString + "2.- " + hijoDerecha.toString + "}\n"
}
