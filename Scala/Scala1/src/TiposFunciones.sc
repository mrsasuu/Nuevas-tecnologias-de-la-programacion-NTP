def duplicar(x:Int) = x*2

val funcion : (Int) => Int = duplicar

// No se puede hacer asi porque no sabe
// si esta siendo invocadaval f = duplicar

funcion(3)

val otra = funcion

otra(3)

val funcion2 = duplicar _

funcion2(3)

def calcularMaximo(a:Int,b:Int) = if(a > b) a else b

val f2 = calcularMaximo _

f2(23,67)