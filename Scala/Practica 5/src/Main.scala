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
    println(listaNodos)
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
      println(resultado)
      resultado = resultado ++ linea.toList})


    resultado
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


  val mensajeDecodificado: List[Char] = decodificar(mensajeSecretoFrances,codigoHuffmanFrances)

  println("Mensaje decodificado: " + mensajeDecodificado.mkString)


  val codificacionTabla = rapidoDeDecodificacion(codigoHuffmanFrances,mensajeDecodificado)
println(codificacionTabla)

  println("Mensaje original == mensaje codificado: " + (mensajeSecretoFrances == codificacionTabla))

  val arbolOriginal = NodoInterno(
    NodoHoja('a', 8),
    NodoInterno(
      NodoInterno(
        NodoInterno(
          NodoHoja('g', 1),
          NodoHoja('h', 1),
          List('g','h'), 2),
        NodoInterno(
          NodoHoja('e', 1),
          NodoHoja('f', 1),
          List('e','f'), 2),
        List('g','h','e','f'), 4),
      NodoInterno(
        NodoInterno(
          NodoHoja('c', 1),
          NodoHoja('d', 1),
          List('c', 'd'), 2),
        NodoHoja('b', 3),
        List('c','d','b'), 5),
      List('g','h','e','f','c','d','b'), 9),
    List('a','g','h','e','f','c','d','b'), 17)

  /**
    * Cadena con la que construir el árbol
    */
  val cadena = List[Char]('a','a','a','a','a','a','a','a','b','b','b','c','d','e','f','g','h')

  // Árbol generado
  val arbol = crearArbolCodificacion(cadena)

  println(arbol)
  println("\n__________________\nEspacio\n__________________\n")
  println(arbolOriginal)

  // Debe ser igual a árbol original
  println("Árbol original == Árbol generado: " + (arbolOriginal == arbol))

  // Se decodifica este mensaje secreto
  val mensajeSecretoEsp = List(0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1,
    0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0,
    1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
    0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1,
    1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0,
    0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1,
    1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1)

  val regenta: List[Char] = leerFichero("./src/regenta.txt")

  val arbol2 = crearArbolCodificacion(regenta)



  println(decodificar(mensajeSecretoEsp,arbol2))

  val mensaje : List[Char] = "La regenta de Benito Perez Galdos".toList

  val codificacionTabla2 = rapidoDeDecodificacion(codigoHuffmanFrances,mensajeDecodificado)
  println(codificacionTabla2)

  println("mensajecofificadoESP == mensajeSecretoEsp" + codificacionTabla2 == mensajeSecretoEsp)

























}
