
import junit.framework.TestCase
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import Conjunto._


class ConjuntoSuite extends TestCase {




  // Se crea un trait incluyendo tres conjuntos, que se
  // usan en cada test
  /**
    * No he llegado a usarlo ya que no se ejecutaban los test que llevaban el new TestSets por una razón que desconozco.
    */
  trait TestSets {
    val s1 = conjuntoUnElemento(1)
    val s2 = conjuntoUnElemento(2)
    val s3 = conjuntoUnElemento(3)
  }

  /**
    * Test para comprobar la funcionalidad de la unión sobre conjuntos de un solo elemento.
    */
  def testUnion() = {

    val s1 = conjuntoUnElemento(1)
    val s2 = conjuntoUnElemento(2)
    val s3 = conjuntoUnElemento(3)


    val s = union(s1, s2)
    assert(s(1), "fallo: s no contiene a 1")
    assert(s(2), "fallo: s no contiene a 2")
    assert(!s(3), "fallo: s contiene a 3")

    /*
    new TestSets {
      val s = union(s1, s2)
      assert(s(1), "fallo: s no contiene a 1")
      assert(s(2), "fallo: s no contiene a 2")
      assert(!s(3), "fallo: s contiene a 3")
    }*/
  }

  /**
    * Test para comprobar que forma correctamente un conjunto.
    */
  def testConstruccion() = {
    val conjunto1 = Conjunto((x: Int) => x > 3)

    assert(conjunto1(4))
    assert(conjunto1(1))


  }

  /**
    * Test para comprobar que los conjuntos de un elemento solo contienen a un único elemento.
    */
  def testContieneUno() = {

    val s1 = conjuntoUnElemento(1)

    assert(s1(1), "fallo: s1 no contiene a 1")
    assert(!s1(2), "fallo: s1 contiene a 2")
    /*
        // Se crea instancia de los conjuntos
        new TestSets {
          // Si falla el assert se muestra el mensaje de error
          // que aparece como segundo argumento
          assert(s1(1), "fallo: s1 no contiene a 1")
        }*/
  }

  /**
    * Test para comprobar la unión entre conjuntos de más de un elemento.
    */
  def testUnionGeneral() = {
    val conjunto1 = Conjunto((x: Int) => x > 3)
    val conjunto2 = Conjunto((x: Int) => x > 5)


    // Conjunto union: enteros mayores de 3
    val conjuntoUnion: Conjunto = Conjunto.union(conjunto1, conjunto2)

    // 4, 5, 6 y 7  pertenecen a la union
    assert(conjuntoUnion(4))
    assert(conjuntoUnion(5))
    assert(conjuntoUnion(6))
    assert(conjuntoUnion(7))

    // 3 y 0 no pertenecen a la union
    assert(!conjuntoUnion(3))
    assert(!conjuntoUnion(0))
  }

  /**
    * Test para comprobar la intersección entre conjuntos de más de un elemento.
    */
  def testInterseccion() = {
    val conjunto1 = Conjunto((x: Int) => x > 3)
    val conjunto2 = Conjunto((x: Int) => x > 5)

    // Formacion de la interseccion: solo a partir de 5
    val conjuntoInterseccion = interseccion(conjunto1, conjunto2)

    // 6 pertenece
    assert(conjuntoInterseccion(6))

    // no 4 ni 5 pertenecen
    assert(!conjuntoInterseccion(4))
    assert(!conjuntoInterseccion(5))
  }

  /**
    * Test para comprobar la diferencia entre conjuntos de más de un elemento.
    */
  def testDiferencia() = {
    val conjunto1 = Conjunto((x: Int) => x > 3)
    val conjunto2 = Conjunto((x: Int) => x < 10)

    // Diferencia: mayores de 3 pero no menores de 10
    val conjuntoDiferencia = diferencia(conjunto1, conjunto2)

    // 6 no pertenece y 11 si
    assert(!conjuntoDiferencia(6))
    assert(conjuntoDiferencia(11))
  }

  /**
    * Test para comprobar el filtrado de un conjunto de más de un elemento con una función de filtrado.
    */
  def testFiltrado() = {
    val conjunto1 = Conjunto((x: Int) => x > 3)
    val conjunto2 = Conjunto((x: Int) => x < 10)

    // Deja en conjunto1 los elementos de conjunto2
    val conjuntoFiltrado = filtrar(conjunto1, conjunto2.funcionCaracteristica)

    // 6 debe pertenecer y 11 no
    assert(conjuntoFiltrado(6))
    assert(!conjuntoFiltrado(11))
  }

  /**
    * Test para comprobar que un conjunto cumple una determinada función para todos sus elementos dado un predicado.
    */
  def testParatodo() = {
    val conjunto = Conjunto((x: Int) => x < 10)

    // No todos los elementos del conjunto son > 0
    assert(!paraTodo(conjunto, x => x > 0))

    // Si que todos son menores de 15
    assert(paraTodo(conjunto, x => x < 15))
  }

  /**
    * Test para comprobar que un conjunto cumple una determinada función para al menos un elemento dado un predicado.
    */
  def testExiste() = {
    val conjunto = Conjunto((x: Int) => x < 10)

    // No existe en el conjunto ningun elemento mayor de 10
    assert(!existe(conjunto, x => x > 10))

    // Si existe en el conjunto algun elemento menor de 15
    assert(existe(conjunto, x => x < 15))
  }


  /**
    * Test para comprobar que se realiza una transformación sobre un conjunto dada una función predicado.
    */
  def testMap() = {
    // Definicion del conjunto
    val conjunto = Conjunto((x: Int) => x < 10)

    // Mapeo: sumar 25 a todos los elementos del conjunto
    val resultado = map(conjunto, (x => x + 25))

    // 30 y 31 pertenecen al conjunto resultado, ya que
    // 5 y 6 pertenecen al conjunto de partida
    assert(resultado(30))
    assert(resultado(31))

    // 125 no pertenece, porque 100 no esa en el conjunto
    // de partida
    assert(!resultado(125))
  }
}

