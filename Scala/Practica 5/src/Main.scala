/**
  * Created by mrsas on 01/06/2017.
  */
object Main extends App{

  /**
    *
    * @param nodo Nodo del árbol de codificación que en el caso de ser un nodo hoja devolverá su contador, o
    *             en el caso de ser un nodo interno llamará recursivamente de nuevo a calcular peso y devolverá la suma del peso de sus hijos.
    * @return devuelve el contador de ese nodo, teniendo en cuenta el peso de los nodos hijos si procediera.
    */
  def calcularPeso(nodo : Nodo) : Int = nodo match{
    case NodoHoja(_, contador) => contador
    case NodoInterno(nodoIzquierda,nodoDerecha,_,_) => calcularPeso(nodoIzquierda) + calcularPeso(nodoDerecha)
  }

  /**
    *
    * @param nodo Nodo del árbol de codificación que en el caso de ser un nodo hoja devolverá una lista formada por el caracter
    *             del que está formado, y en el caso de ser un nodo interno llamará recursivamente de nuevo a obtenerCaracteres y devolverá la
    *             concatenación de listas de caracteres de sus nodos hijo.
    * @return devuelve una lista de caracteres con los caracteres que forman los hijos que cuelgan de dicho nodo.
    */
  def obtenerCaracteres(nodo : Nodo ) : List[Char] = nodo match{
    case NodoHoja(caracter, _) => List(caracter)
    case NodoInterno(nodoIzquierda,nodoDerecha,_,_) => obtenerCaracteres(nodoIzquierda) ::: obtenerCaracteres(nodoDerecha)
  }

  /**
    *
    * @param nodos Lista de Nodos con los que se creará un nuevo nodo. Dicha lusta se presupone ordenada para poder extraer los
    *              dos primeros hijos con menos frecuencia.
    * @return retornará un nodo intermedio con la unión de los dos primeros nodos de la lista.
    */
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

  /**
    *
    * @param listaCaracteresFrecuencia lista de pares de elementos (caracter, frecuencia de aparición).
    * @return devuelve una lista de nodos hoja.
    */
  def crearListaNodosHoja(listaCaracteresFrecuencia : List[(Char,Int)]): List[NodoHoja] ={
    val resultado =  listaCaracteresFrecuencia.map(elemento => NodoHoja(elemento._1, elemento._2))

    resultado
  }

  /**
    *
    * @param nodos lista de nodos
    * @return devuelve true si la lista tiene tamaño 1.
    */
  def singleton(nodos: List[Nodo]): Boolean = {
    nodos.size == 1

  }

  /**
    *
    * @param nodos lista de nodos a combinar
    * @return devuelve una lista con los dos nodos con menor frecuencia de aparición combinados en un nodo intermedio.
    */
  def combinar(nodos: List[Nodo]): List[Nodo] = {
    var resultado = (generarArbol(nodos) :: nodos.tail.tail)

    resultado = resultado.sortWith((elemento1, elemento2) => calcularPeso(elemento1) < calcularPeso(elemento2))

    resultado
  }

  /**
    *
    * @param singleton función para el criterio de parada.
    * @param combinar función de unión de los nodos menos frecuentes
    * @param listaNodos lista de nodos a combinar
    * @return Lista de nodos combinados y ordenados por frecuencia de aparición.
    */
  def repetir(singleton: List[Nodo] => Boolean, combinar: List[Nodo] => List[Nodo])(listaNodos : List[Nodo]) :List[Nodo]={
    //Si la lista tiene un único elemento ya hemos terminado
    if(singleton(listaNodos)){
      listaNodos
    }else {//Si aún tiene más de un elemento combinaremos esos elementos en un nodo intermedio.
      repetir(singleton,combinar)(combinar(listaNodos))
    }

  }

  /**
    *
    * @param textoCodificar texto para crear el árbol de codificación
    * @return devuelve el nodo intermedio que será la raiz del árbol.
    */
  def crearArbolCodificacion(textoCodificar : List[Char]) : Nodo ={
    //Calculamos los caracteres y el orden de aparición de estos
    val listaFrecuencias = frecuenciasAparicion(textoCodificar)
    //Creamos la lista de nodos hoja a partir de la lista de frecuencias anteriormente calculada
    val listaNodos = crearListaNodosHoja(listaFrecuencias)

    //Generamos el árbol de codificación a partir de la función recursiva repetir.
    val resultado = repetir(singleton,combinar)(listaNodos)

    //Extraemos el nodo de la lista.
    resultado.head
  }


  /**
    *
    * @param mensajeSecreto lista de enteros que contendrá el mensaje codificado para decodificar.
    * @param raiz nodo intermedio raiz del árbol de codificación para extraer las dependencias. Si el árbol de codificación no
    *             es el correcto y por ello no contiene todos los caracteres que aparecen en el mensaje, podría darse un fallo.
    * @return devuelve una lista de caracteres con el mensaje decodificado.
    */
  def decodificar( mensajeSecreto : List[Int],raiz : Nodo ) : List[Char] ={
    /**
      * Función recursiva auxiliar para decodificar
      * @param mensajeSecreto lista de enteros que contendrá el mensaje codificado para decodificar.
      * @param nodo nodo intermedio raiz del árbol de codificación donde se extraerán las dependencias de cada caracter.
      * @return en caso de ser un nodo hoja, devuelve una concatenación del caracter de dicho nodo con el siguiente caracter correspondiente
      *         a la codificación restante del mensaje, o bien la lista final decodificada si no queda ningún elemento en la lista de enteros mensajeSecreto.
      *         En caso de ser un nodo intermedio si el elemento es un cero exploraremos por la izquierda del árbol eliminando el elemento de la lista mensajeSecreto
      *         ya analizado, en caso de ser un 1 haremos lo anterior pero por la derecha del árbol.
      */
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

  /**
    *
    * @param arbolCodificacion raiz del árbol de codificación a partir del cual se va a generar la tabla de codificación.
    * @return devuelve una tabla de codificación (Char, List[int]) para codificar rápidamente un mensaje.
    */
  def convertirArbolTabla(arbolCodificacion: Nodo) : TablaCodigo = {

    /**
      * Función recursiva auxiliar para analizar cada nodo del árbol.
      * @param nodo nodo raiz a analizar
      * @param tabla lista de enteros que formará nuestra tabla de codificación.
      * @return develve una tabla de codificación a partir de la raiz del árbol de codificación.
      */
    def aux(nodo: Nodo, tabla: List[Int]): TablaCodigo = {
      /**
        * En caso de que el nodo a analizar sea un nodo hoja, devolveremos una lista con la letra de dicho nodo y el código asociado.
        * En caso de que el nodo a analizar sea un nodo intermedio, llamaremos a la función recursiva auxiliar para su hijo izquierdo añadiendo un
        * 0 a la lista tabla, y concatenaremos con otra llamada a la función recursiva auxiliar para su hijo derecho añadiendo un 1 a la lista tabla.
        */

      nodo match {
        case NodoHoja(letra, _) => List((letra, tabla))
        case NodoInterno(nodoIzquierda, nodoDerecha, _, _) =>
          aux(nodoIzquierda, tabla :+ 0) ::: aux(nodoDerecha, tabla :+ 1)
      }
    }
    //Inicializamos nuestra función.
    aux(arbolCodificacion, List())
  }

  /**
    *
    * @param tabla tabla de codificación rápida donde se hará la búsqueda.
    * @param caracter caracter para el que se quiere sacar su correspondencia en codificación entera.
    * @return devuelve la codificación correspondiente en 1 y 0 para ese caracter dado.
    */
  def codificarConTabla(tabla : TablaCodigo)(caracter : Char) : List[Int] ={
    //Se filtra en la tabla el caracter deseado y se extrae la lista de enteros que corresponde a su codificación
    val result = tabla.filter(entrada => entrada._1 == caracter).head._2

    result
  }

  /**
    *
    * @param arbolCodificacion nodo raiz del árbol de codificación fuente con el que se va a construir la tabla de codificación.
    * @param textoCodificar texto que se codificará a partir de la tabla de codificación rápida construida a través del nodo riz del árbol de
    *                       codificación.
    * @return devolverá una lista de números enteros con la correspondencia del mensaje codificado.
    */
  def rapidoDeDecodificacion(arbolCodificacion: Nodo,textoCodificar: List[Char]): List[Int] = {
    //Construcción de la tabla de codificación
    val tablaCodigo = convertirArbolTabla(arbolCodificacion)

    var resultado : List[Int] = List()

    //Bucle para la construcción de la lista de enteros con la correspondencia de cada caracter.
    textoCodificar.foreach(elemento => resultado=  resultado ++ codificarConTabla(tablaCodigo)(elemento))

    resultado
  }

  /**
    *
    * @param path ruta del archivo de texto
    * @return devuelve una lista con todos los caracteres que aparecen en archivo de la ruta path.
    */
  def leerFichero(path: String) : List[Char] ={
    import scala.io.Source

    var resultado : List[Char] = List()

    //Bucle para obtener los caracteres de cada línea del archivo.
    Source.fromFile(path).getLines().toList.foreach(linea => {
      resultado = resultado ++ linea.toList})


    resultado
  }
}
