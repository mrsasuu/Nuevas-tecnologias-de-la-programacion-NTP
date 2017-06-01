/**
  * Created by mrsas on 01/06/2017.
  */
object Main extends App{

  def calcularPeso(nodo : Nodo) : Int ={
    case NodoHoja(_, contador) => contador
    case NodoInterno(nodoIzquierda,nodoDerecha,_,_) => calcularPeso(nodoIzquierda) + calcularPeso(nodoDerecha)
  }

  def obtenerCaracteres(nodo : Nodo ) : List[Char] ={
    case NodoHoja(caracter, _) => List(caracter)
    case NodoInterno(nodoIzquierda,nodoDerecha,_,_) => obtenerCaracteres(nodoIzquierda) ::: obtenerCaracteres(nodoDerecha)
  }

  def generarArbol(nodo : Nodo) : Nodo ={
    ???
  }

  /**
    *
    * @param texto Lista de caracteres de donde se extraerán los caracteres de las hojas del árbol.
    * @return extrae de la lista de caracteres todos los elementos diferentes que hay, elimina los duplicados y los ordena según su
    *         frecuencia de aparición, retornando una lista de pares de caracter y contador de veces que aparece.
    */
  def frecuenciasAparicion(texto : List[Char]) : List[(Char,Int)] = {

   var lista = texto.map(caracter => (caracter, texto.count(segundoCaracter =>caracter == segundoCaracter)))

    lista = lista.distinct

    lista.sortWith((elemento1, elemento2) => elemento1._2 < elemento2._2)

    lista

  }

  def singleton(nodos: List[Nodo]): Boolean ={
    nodos.size == 1
  }











}
