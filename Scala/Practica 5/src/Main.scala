/**
  * Created by mrsas on 01/06/2017.
  */
object Main extends App{

  def calcularPeso(nodo : Nodo) : Int = nodo match{
    case NodoHoja(_, contador) => contador
    case NodoInterno(nodoIzquierda,nodoDerecha,_,_) => calcularPeso(nodoIzquierda) + calcularPeso(nodoDerecha)
  }

  def obtenerCaracteres(nodo : Nodo ) : List[Char] = nodo match{
    case NodoHoja(caracter, _) => List(caracter)
    case NodoInterno(nodoIzquierda,nodoDerecha,_,_) => obtenerCaracteres(nodoIzquierda) ::: obtenerCaracteres(nodoDerecha)
  }

  def generarArbol(nodos : List[Nodo]) : Nodo ={
    var nodo1 = nodos.head
    var nodo2 = nodos.tail.head

    val resultado = NodoInterno(nodo1, nodo2,obtenerCaracteres(nodo1) ::: obtenerCaracteres(nodo2), calcularPeso(nodo1) + calcularPeso(nodo2))

    resultado

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

    lista = lista.sortWith((elemento1, elemento2) => elemento1._2 < elemento2._2)

    lista

  }

  def crearListaNodosHoja(listaCaracteresFrecuencia : List[(Char,Int)]): List[NodoHoja] ={
    val resultado =  listaCaracteresFrecuencia.map(elemento => NodoHoja(elemento._1, elemento._2))

    resultado
  }

  def singleton(nodos: List[Nodo]): Boolean = {
    nodos.size == 1

  }

  def combinar(nodos: List[Nodo]): List[Nodo] = {
    var resultado = (generarArbol(nodos) :: nodos.tail.tail)

    resultado = resultado.sortWith((elemento1, elemento2) => calcularPeso(elemento1) < calcularPeso(elemento2))

    resultado
  }

  def repetir(singleton: List[Nodo] => Boolean, combinar: List[Nodo] => List[Nodo])(listaNodos : List[Nodo]) :List[Nodo]={
    if(singleton(listaNodos)){
      listaNodos
    }else {
      repetir(singleton,combinar)(combinar(listaNodos))
    }

  }

  def crearArbolCodificacion(textoCodificar : List[Char]) : Nodo ={
    val listaFrecuencias = frecuenciasAparicion(textoCodificar)
    val listaNodos = crearListaNodosHoja(listaFrecuencias)


    val resultado = repetir(singleton,combinar)(listaNodos)

    resultado.head
  }



  def decodificar( mensajeSecreto : List[Int],raiz : Nodo ) : List[Char] ={
    def aux( mensajeSecreto: List[Int],nodo: Nodo): List[Char] ={
      nodo match {
        case NodoHoja(letra, _) =>
          if (mensajeSecreto.isEmpty) {
            List(letra)
          }else{
            letra :: aux(mensajeSecreto,raiz)
          }
        case NodoInterno(nodoIzquierda, nodoDerecha, _, _) =>
          if (mensajeSecreto.head == 0) {
            aux(mensajeSecreto.tail,nodoIzquierda )              
          }
          else {
            aux(mensajeSecreto.tail, nodoDerecha)
          }
      }
    }

    aux(mensajeSecreto,raiz)
  }

  type TablaCodigo = List[(Char, List[Int])]

  def convertirArbolTabla(arbolCodificacion: Nodo) : TablaCodigo = {

    def aux(nodo: Nodo, tabla: List[Int]): TablaCodigo = {
      nodo match {
        case NodoHoja(letra, _) => List((letra, tabla))
        case NodoInterno(nodoIzquierda, nodoDerecha, _, _) =>
          aux(nodoIzquierda, tabla :+ 0) ::: aux(nodoDerecha, tabla :+ 1)
      }
    }

    aux(arbolCodificacion, List())
  }

  def codificarConTabla(tabla : TablaCodigo)(caracter : Char) : List[Int] ={
    val result = tabla.filter(entrada => entrada._1 == caracter).head._2

    result
  }

  def rapidoDeDecodificacion(arbolCodificacion: Nodo,textoCodificar: List[Char]): List[Int] = {
    val tablaCodigo = convertirArbolTabla(arbolCodificacion)

    var resultado : List[Int] = List()

    textoCodificar.foreach(elemento => resultado=  resultado ++ codificarConTabla(tablaCodigo)(elemento))

    resultado
  }

  def leerFichero(path: String) : List[Char] ={
    import scala.io.Source

    var resultado : List[Char] = List()

    Source.fromFile(path).getLines().toList.foreach(linea => {
      resultado = resultado ++ linea.toList})


    resultado
  }
}
