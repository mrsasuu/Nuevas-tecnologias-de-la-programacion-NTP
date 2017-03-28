//Creación de listas
val lista1 = List(1,2,3,4)
val lista2 = List(5,6)

//Concatenación operador
val lista12 = lista1:::lista2

//Agregar elementos al principio
val lista3 = 1::lista1

//Programacion funcional incorporada en la clase (no flujo previo)
val mayor2 = lista1.filter(x => x > 2)

val cuantosMayor2 = lista1.count(x => x >2)

//Conjunto de operaciones muy amplio
lista1.drop(2)
lista1.dropRight(2)
println(lista1)

val resultado = lista1.exists(x => x%2 ==0)

lista1.length

lista1.head
lista1.tail

lista1.foreach(println)
