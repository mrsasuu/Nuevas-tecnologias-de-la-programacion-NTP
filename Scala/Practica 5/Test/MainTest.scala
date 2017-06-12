import junit.framework.TestCase

import Main._

/**
  * Created by mrsas on 12/06/2017.
  *
  *
  * Clase para realizar el testeo de la práctica.
  */
class MainTest extends TestCase{

  /**
    * Variables que se reusarán en algunos test.
    */
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


  /**
    * Test para comprobar la funcionalidad del la función decodificar.
    * Pasará el test si el mensaje previamente codificado (por el profesor) coincide con el retornado en a función decodificar.
    */
  def testDecodificarFrances() = {

    val mensajeDecodificado: List[Char] = decodificar(mensajeSecretoFrances,codigoHuffmanFrances)

    assert("huffmanestcool"== mensajeDecodificado.mkString)

  }
  /**
    * Test para comprobar la funcionalidad del la función rapidoDeDecodificacion.
    * Pasará el test si el mensaje codificado por la función rapidoDeDecodificación coincide con el mensaje codificado por el profesor.
    */
  def testRapidoDeDecodificacion() = {

    val mensajeDecodificado: List[Char] = decodificar(mensajeSecretoFrances,codigoHuffmanFrances)
    val codificacionTabla = rapidoDeDecodificacion(codigoHuffmanFrances,mensajeDecodificado)

    assert(mensajeSecretoFrances == codificacionTabla)
  }
  /**
    * Test para comprobar la funcionalidad del la función CrearArbolCodificación.
    * Pasará el test si el árbol creado por la función CrearArbolCodificacion es identico al creado manualmente.
    */
  def testCrearArbol() = {
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

    val cadena = List[Char]('a','a','a','a','a','a','a','a','b','b','b','c','d','e','f','g','h')

    val arbol = crearArbolCodificacion(cadena)

    assert(arbolOriginal == arbol)
  }

  /**
    * Test para comprobar la funcionalidad de la función leerFichero, Decodificar y rapidoDeDecodificación en español a partir de un
    * texto de un archivo en español.
    */
  def testDecodificarEspan() ={
    val mensajeSecretoEsp = List(0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1,
      0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0,
      1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
      0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1,
      1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0,
      0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1,
      1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1)

    val regenta: List[Char] = leerFichero("./src/regenta.txt")

    val arbol = crearArbolCodificacion(regenta)

    val mensajeDecodificado: List[Char] = decodificar(mensajeSecretoEsp, arbol)
    val mensaje : List[Char] = "La regenta de Benito Perez Galdos".toList

    val codificacionTabla2 = rapidoDeDecodificacion(arbol,mensajeDecodificado)

    assert( mensajeDecodificado == "La regenta de Benito Perez Galdos")
    assert(codificacionTabla2 == mensajeSecretoEsp)
  }
}
