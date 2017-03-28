/**
  * Created by mrsas on 21/03/2017.
  */
import scala.io.Source

object Procesamiento_de_lineas {

  def imprimirLineas(nombreArchivo : String) : Unit ={
    val lineas : Seq[String] = Source.fromFile(nombreArchivo).getLines().toList

    for(linea <- lineas){
      println(linea.length + " | " + linea)
    }
  }

  def calcularAnchoLinea(linea : String) = linea.length.toString.length

  def imprimirLineasV2(nombreArchivo : String) : Unit = {
    val lineas :Seq[String] = Source.fromFile(nombreArchivo).getLines().toList

    var maximoAnchoTam = 0
    for(linea <- lineas){
      maximoAnchoTam = maximoAnchoTam.max(calcularAnchoLinea(linea))
    }

    //Una vez acabado el bucle ya sabemos el maximo de sangrad que necesitamos

    for(linea <- lineas){
      val tamanioLinea = calcularAnchoLinea(linea)
      val relleno = " " * (maximoAnchoTam - tamanioLinea)

      println(relleno + linea.length + " | " + linea)

    }
  }

  def imprimirLineasV3(nombreArchivo : String) : Unit = {
    val lineas :Seq[String] = Source.fromFile(nombreArchivo).getLines().toList

    var lineaMasLarga = lineas.reduceLeft((a,b) => if(a.length > b.length) a else b)

    val maximoAnchoTam = calcularAnchoLinea(lineaMasLarga)

    //Una vez acabado el bucle ya sabemos el maximo de sangrad que necesitamos

    for(linea <- lineas){
      val tamanioLinea = calcularAnchoLinea(linea)
      val relleno = " " * (maximoAnchoTam - tamanioLinea)

      println(relleno + linea.length + " | " + linea)

    }
  }


  def main(args: Array[String]): Unit = {
    if(args.length > 0){
      //imprimirLineas(args(0))
      imprimirLineasV2(args(0))
    }
    else{
      Console.err.println("Introduzca nombre archivo")
    }

  }

}
