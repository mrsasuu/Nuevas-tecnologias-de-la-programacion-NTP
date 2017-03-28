var ciudades = Set("Granada","Almeria","Jaen")
//Var sirve como variable, con val no funcionaria con una coleccion inmutable

ciudades += "Malaga"

println(ciudades)


import scala.collection.mutable.Set
val asignaturas = Set("Física","Matematicas")

import scala.collection.immutable.HashSet
val conjunto = HashSet("Granada","Almeria","Jaen")

//Por defecto los mapas son inmutables
val dias = Map(1->"Lunes",2 -> "Martes")

println(dias)
