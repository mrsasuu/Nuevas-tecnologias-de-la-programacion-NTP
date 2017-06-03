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

    lista.sortWith((elemento1, elemento2) => elemento1._2 < elemento2._2)

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
    val resultado = (generarArbol(nodos) :: nodos.tail.tail).sortWith((elemento1, elemento2) => calcularPeso(elemento1) < calcularPeso(elemento2))

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

  val codigoHuffmanFrances: Nodo = NodoInterno(
    NodoInterno(
      NodoInterno(
        NodoHoja('s', 121895),
        NodoInterno(
          NodoHoja('d', 56269),
          NodoInterno(
            NodoInterno(
              NodoInterno(
                NodoHoja('x', 5928),
                NodoHoja('j', 8351),
                List('x', 'j'), 14279),
              NodoHoja('f', 16351),
              List('x', 'j', 'f'), 30630),
            NodoInterno(
              NodoInterno(
                NodoInterno(
                  NodoInterno(
                    NodoHoja('z', 2093),
                    NodoInterno(
                      NodoHoja('k', 745),
                      NodoHoja('w', 1747),
                      List('k', 'w'), 2492),
                    List('z', 'k', 'w'), 4585),
                  NodoHoja('y', 4725),
                  List('z', 'k', 'w', 'y'), 9310),
                NodoHoja('h', 11298),
                List('z', 'k', 'w', 'y', 'h'), 20608),
              NodoHoja('q', 20889),
              List('z', 'k', 'w', 'y', 'h', 'q'), 41497),
            List('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 72127),
          List('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 128396),
        List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 250291),
      NodoInterno(
        NodoInterno(
          NodoHoja('o', 82762),
          NodoHoja('l', 83668),
          List('o', 'l'), 166430),
        NodoInterno(
          NodoInterno(
            NodoHoja('m', 45521),
            NodoHoja('p', 46335),
            List('m', 'p'), 91856),
          NodoHoja('u', 96785),
          List('m', 'p', 'u'), 188641),
        List('o', 'l', 'm', 'p', 'u'), 355071),
      List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u'), 605362),
    NodoInterno(
      NodoInterno(
        NodoInterno(
          NodoHoja('r', 100500),
          NodoInterno(
            NodoHoja('c', 50003),
            NodoInterno(
              NodoHoja('v', 24975),
              NodoInterno(
                NodoHoja('g', 13288),
                NodoHoja('b', 13822),
                List('g', 'b'), 27110),
              List('v', 'g', 'b'), 52085),
            List('c', 'v', 'g', 'b'), 102088),
          List('r', 'c', 'v', 'g', 'b'), 202588),
        NodoInterno(
          NodoHoja('n', 108812),
          NodoHoja('t', 111103),
          List('n', 't'), 219915),
        List('r', 'c', 'v', 'g', 'b', 'n', 't'), 422503),
      NodoInterno(
        NodoHoja('e', 225947),
        NodoInterno(
          NodoHoja('i', 115465),
          NodoHoja('a', 117110),
          List('i', 'a'), 232575),
        List('e', 'i', 'a'), 458522),
      List('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 881025),
    List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 1486387)

   val mensajeSecretoFrances: List[Int] = List(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1,
   0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)

  def decodificar( mensajeSecreto : List[Int],raiz : Nodo ) : List[Char] ={
    def aux( mensajeSecreto: List[Int],nodo: Nodo): List[Char] ={
      nodo match {
        case NodoHoja(letra, _) =>
          if (mensajeSecreto.length == 0) {
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

    aux(mensajeSecreto,raiz) // comienza desde la raiz con el texto codificado completo
  }

  type TablaCodigo = List[(Char, List[Int])]

  def convertirArbolTabla(arbolCodificacion: Nodo) : TablaCodigo = {

    def aux(nodo: Nodo, tabla: List[Int]): TablaCodigo = {
      nodo match {                                                                          // nodo actual
        case NodoHoja(caracter, _) => List((caracter, tabla))                               // es nodo hoja => devuelve el caracter con la lista hasta llegar a él
        case NodoInterno(nodoIzquierda, nodoDerecha, _, _) =>                                            // es nodo intermedio => recursividad a izda y dcha agregando un 0 y 1
          aux(nodoIzquierda, tabla :+ 0) ::: aux(nodoDerecha, tabla :+ 1) //                       a la lista respectivamente
      }
    }

    aux(arbolCodificacion, List())
  }

  def codificarConTabla(tabla : TablaCodigo)(caracter : Char) : List[Int] ={


  }

  def codificacionRapida(arbol: Nodo)(texto: List[Char]): List[Int] = {
    val tablaCodigo = convertirArbolTabla(arbol)                            // crea la tabla a partir del árbol
    (for(caracter <- texto) yield codificarConTabla(tablaCodigo)(caracter)) // obtiene lista para cada caracter del texto
      .flatten                                                              // convierte el List[List[Int]] a List[Int]
  }
















}
