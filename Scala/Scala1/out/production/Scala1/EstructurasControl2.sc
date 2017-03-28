val archivos = (new java.io.File(".")).listFiles

for(archivo <- archivos
if archivo.isFile
if archivo.getName.contains("s")) yield archivo

for{
  x <- 1 to 2
  y <- 1 to 3
}yield (x,y)

