//Definicion de rangos
val x = 1 to 6
val y = 1 until 10

//indicacion del paso
val z = 1 to 7 by 2

//Posibilidad de definir pasos negativos
val w = 10 to 1 by -1

//For comprehesion(es una expresion)
for(x <- 1 to 7){
  println(x)
}

for(x <- y){
  println(x)
}

//uso yield. Yield es para devolver algo. Podriamos usar x =  for
val resultado = for(x <-1 to 7)yield{
  println(x)
  x
}
println(resultado)

// Es posible definir condiciones
val mult3 = for(i <- 1 to 1000 if i %3 ==0) yield i

println(mult3)


val mult4 = for(i <- 1 to 1000 if i %3 ==0
if i%7==0) yield i

println(mult4)
