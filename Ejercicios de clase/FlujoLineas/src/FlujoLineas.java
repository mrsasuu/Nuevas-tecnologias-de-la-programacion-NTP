import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

/**
 * Clase para mostrar el uso de flujos con texto
 */
public class FlujoLineas {

    // Dato miembro para almacenar el nombre del archivo
    private String nombreArchivo;

    // Dato miembro para almacenar las lineas del archivo como
    // una lista y asi poder procesarlas posteriormente
    private List<String> lineas;

    /**
     * Constructor de la clase
     * @param nombreArchivo
     */
    public FlujoLineas(String nombreArchivo){
        //Asignamos el dato miembro
        this.nombreArchivo = nombreArchivo;

        //Se procesa el contenido
        try {
            lineas = Files.lines(Paths.get(nombreArchivo), StandardCharsets.UTF_8).map(linea -> linea.replaceAll("(?!')\\p{Punct}","")).collect(Collectors.toList());
        }catch (IOException e){
            System.out.println("Error en la apertura del archivo.");
            System.exit(-1);
        }
    }

    //Para cada palabra contar cuantas veces aparece en el texto
    public Map<String,Long> obtenerContadoresPalabras(){
        //Definir patron para eliminar los espacios en blanco
        Pattern patron = Pattern.compile("\\s+");

        //Procesamiento de las cadenas almacenadas en lineas teniendo en cuenta que cada cadena representa una linea completa
        Stream<String> palabras = lineas.stream().flatMap(linea -> patron.splitAsStream(linea)).filter(palabra -> !palabra.isEmpty());

        TreeMap<String, Long> mapa = palabras.collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()));

        return mapa;
    }

    // Metodo main para probar
    public static void main(String[] args) throws IOException {
        // Se crea un objeto de la clase
        FlujoLineas objeto=new FlujoLineas("alicia.txt");

        // Se llama al metodo para la creacion del mapa
        Map<String,Long> mapa = objeto.obtenerContadoresPalabras();

        // Crear un flujo a partir de las entradas del mapa
        Stream<Entry<String,Long>> flujo = mapa.entrySet().stream();

        // Se rea un mapa organanizando por iniciales
        TreeMap<Character, List<Entry<String, Long>>> mapaFinal = flujo.collect(Collectors.groupingBy(entrada -> entrada.getKey().charAt(0), TreeMap::new, Collectors.toList()));

        // Se muestra la informacion del mapa obtenido
        mapaFinal.forEach((letra, listaLetra) -> {
            System.out.println("\n"+letra+ " --------------");
            listaLetra.stream().forEach(entrada -> {
                System.out.println("    " + entrada.getKey()+" - "+entrada.getValue());
            });
        });

    }
}

