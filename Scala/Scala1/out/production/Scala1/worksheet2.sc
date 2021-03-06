var i:Int = 10

while(i > 0){
  println("Valor de i: " + i )
  i = i - 1
}

i = 10

do{
  println("Valor de i: " + i )
  i = i - 1
}while(i >= 0)

//Expresion for for-comprehsion
val x = 1 to 10
val y = 1 until 10
val z = 1 to 10 by 2
val w = 10 to 1 by -2

for(i <- 1 to 10){
  println("Valor de i: " + i )
  i
}

for(i <- 1 to 5;
    j <- 2 to 4){
  println("(" + i","+j+")")
}

for(1<-1 to 5
  j <- 2 to 4){
  println("("+i","+j+")")
  yield i+j//Devuelve la coleccion de valores i+j
}

//Parametrizacion
var saludos = new Array[String](3)
saludos(0)="Hola"
saludos(1) = "mundo"
saludos(2) = "cruel"

for(i <- 0 until saludos.length){
  println(saludos(i))
}

