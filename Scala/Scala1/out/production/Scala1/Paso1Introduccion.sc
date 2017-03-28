// Definicion de la funcion mas sencilla
def saludo = "hola"

saludo
// no se puede usar con parentesis si no esta definida
// asi saluco()

def saludo1:String = "hola"
// Pueden devolverse tipos.

def multiplicar(x:Int, y:Int):Int = {x * y}

multiplicar(5,6)

def quitarBlancosIniciales(s:String):String = {
  if(s == null)
    return null

  s.trim //trim() tambien vale

}

quitarBlancosIniciales("         yujuuu jeje")

// Procedimientos: funcion que no devuelve
// nada
def mostrar(d:Double):Unit={
  println(f"Valor = $d%.2f")
}
mostrar(7.54544)

// Usar bloques en la llamada asi no hay que crear variables
// intermedias
mostrar{2.78*0.15+24}
