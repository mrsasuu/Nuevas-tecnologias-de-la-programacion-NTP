import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mrsas on 13/03/2017.
 */
public class Listado {

    Map<String,Empleado> lista;

    public Listado(String nombreArchivo) throws IOException {
        lista = new HashMap<String,Empleado>();
        Stream<String> lineas = Files.lines(Paths.get(nombreArchivo));
        lista = lineas.map(linea -> {
            return crearEmpleado(linea);//Dento de crear empleado filtraremos la linea y construiremos el objeto.
        }).collect(Collectors.toMap(empleado -> empleado.getDNI(), Function.identity()));

        cargarArchivoAsignacionDivision("./data/asignacionDIVNA.txt");
        cargarArchivoAsignacionDivision("./data/asignacionDIVID.txt");
        cargarArchivoAsignacionDivision("./data/asignacionDIVSW.txt");
        cargarArchivoAsignacionDivision("./data/asignacionDIVHW.txt");
        cargarArchivoAsignacionDivision("./data/asignacionDIVSER.txt");
        cargarArchivoAsignacionDepartamento("./data/asignacionDEPNA.txt");
        cargarArchivoAsignacionDepartamento("./data/asignacionDEPSB.txt");
        cargarArchivoAsignacionDepartamento("./data/asignacionDEPSM.txt");
        cargarArchivoAsignacionDepartamento("./data/asignacionDEPSA.txt");


    }


    private Empleado crearEmpleado(String linea){
        Pattern patron = Pattern.compile(",");
        List<String> infos = patron.splitAsStream(linea).collect(Collectors.toList());

        return new Empleado(infos.get(0),infos.get(1),infos.get(2),infos.get(3));//Colocar a no asignado la division y departamento
    }

    public void cargarArchivoAsignacionDivision(String nombreArchivo) throws IOException {
        Stream<String> lineas = Files.lines(Paths.get(nombreArchivo));

        //Sacaremos el titulo de la division
        String divisionLine = lineas.findFirst().orElseGet(null);

        //Saltamos las 2 primeras lineas
        Files.lines(Paths.get(nombreArchivo)).skip(2).forEach(linea -> procesarAsignacionDivision(linea,divisionLine));



    }

    public void cargarArchivoAsignacionDepartamento(String nombreArchivo){

    }

    private void procesarAsignacionDivision(String dni, String cadDivision){
        Predicate<Division> condicion = division ->{ division.name().equals(cadDivision);};

        Division divisionRes = Arrays.stream(Division.values()).filter(condicion).findFirst().get();
    }

    private Division equivalenteDivision(String nombreDivision){
        switch (nombreDivision){
            case "DIVNA":
                break;
        }
    }
}
