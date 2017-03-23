import com.sun.java.swing.plaf.windows.WindowsTreeUI;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collector;
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

    public void cargarArchivoAsignacionDepartamento(String nombreArchivo) throws IOException {
        Stream<String> lineas = Files.lines(Paths.get(nombreArchivo));

        //Sacaremos el titulo del departamento
        String departamentoLine = lineas.findFirst().orElseGet(null);

        //Saltamos las 2 primeras lineas
        Files.lines(Paths.get(nombreArchivo)).skip(2).forEach(linea -> procesarAsignacionDepartamento(linea,departamentoLine));

    }

    private void procesarAsignacionDivision(String dni, String cadDivision){
        /*Predicate<Division> condicion = division ->{ division.name().equals(cadDivision);};

        Division divisionRes = Arrays.stream(Division.values()).filter(condicion).findFirst().get();*/

        //Predicate<Empleado> condicion = empleado -> (empleado.getDNI().compareTo(dni)==0);

        //lista.stream().filter(condicion).findFirst().get();

        //lista.entrySet().stream().filter(condicion).findAny().get().

        lista.get(dni).setDivision(equivalenteDivision(cadDivision));

        //System.out.println(cadDivision);



    }

    private void procesarAsignacionDepartamento(String dni, String cadDepartamento){
        /*Predicate<Division> condicion = division ->{ division.name().equals(cadDivision);};

        Division divisionRes = Arrays.stream(Division.values()).filter(condicion).findFirst().get();*/

        //Predicate<Empleado> condicion = empleado -> (empleado.getDNI().compareTo(dni)==0);

        //lista.stream().filter(condicion).findFirst().get();

        //lista.entrySet().stream().filter(condicion).findAny().get().

        lista.get(dni).setDepartamento(equivalenteDepartamento(cadDepartamento));



    }

    private Division equivalenteDivision(String nombreDivision){
        Division resultado = Division.DIVNA;
        switch (nombreDivision){
            case "DIVNA":
                resultado = Division.DIVNA;
                break;
            case "DIVSW":
                resultado = Division.DIVSW;
                break;
            case "DIVHW":
                resultado = Division.DIVHW;
                break;
            case "DIVID":
                resultado = Division.DIVID;
                break;
            case "DIVSER":
                resultado = Division.DIVSER;
                break;
        }

        return resultado;
    }

    private Departamento equivalenteDepartamento(String nombreDepartamento){
        Departamento resultado = Departamento.DEPNA;
        switch (nombreDepartamento){
            case "DEPNA":
                resultado = Departamento.DEPNA;
                break;
            case "DEPSA":
                resultado = Departamento.DEPSA;
                break;
            case "DEPSB":
                resultado = Departamento.DEPSB;
                break;
            case "DEPSM":
                resultado = Departamento.DEPSM;
                break;
        }

        return resultado;
    }

    public void listarEmpleados(){
        lista.entrySet().stream().forEach(System.out::println);
    }

    @Override
    public String toString() {
        String resultado = "";

        //System.out.println( lista.entrySet().toString());
         return lista.entrySet().toString();

        //return lista.entrySet().stream().forEach(elemento ->{ resultado = elemento.toString();});
    }

    public Map<Division, Map<Departamento, Long>> obtenerContadoresDivisionDepartamento(){
        Map<Division, Map<Departamento, Long>> resultado = new HashMap<>();
        Division[] divisiones = {Division.DIVID,Division.DIVHW,Division.DIVSER,Division.DIVSW,Division.DIVNA};

        List<Division> listaDivisiones = Arrays.asList(divisiones);

        listaDivisiones.forEach(division ->{resultado.put(division,obtenerContadoresDepartamento(division));});

        return resultado;


    }

    public Map<Departamento,Long> obtenerContadoresDepartamento(Division division){
        Predicate<Empleado> condicion = empleado -> (empleado.getDivision() == division);
        Stream<Empleado> empleados = lista.values().stream();

        return empleados.filter(condicion).collect(Collectors.groupingBy(Empleado::getDepartamento,TreeMap::new,Collectors.counting()));
    }

    public List<Empleado> buscarEmpleadosSinDivision(){
        Predicate<Empleado> condicion = empleado -> (empleado.getDivision() == Division.DIVNA);

        return  lista.values().stream().filter(condicion).collect(Collectors.toList());
    }

    public List<Empleado> buscarEmpleadosConDivisionSinDepartamento(){
        Predicate<Empleado> condicion = empleado -> ((empleado.getDivision() != Division.DIVNA)&&(empleado.getDepartamento()==Departamento.DEPNA));

        return  lista.values().stream().filter(condicion).collect(Collectors.toList());
    }

    public List<Empleado> buscarEmpleadosSinDepartamento(Division division){
        Predicate<Empleado> condicion = empleado -> ((empleado.getDivision() == division)&&(empleado.getDepartamento()==Departamento.DEPNA));

        return  lista.values().stream().filter(condicion).collect(Collectors.toList());
    }

    public boolean hayDnisRepetidos(){
        Stream<Empleado> empleados = lista.values().stream();


        TreeMap<String, Long> resultado = empleados.collect(Collectors.groupingBy(Empleado::getDNI, TreeMap::new, Collectors.counting()));

        Predicate<Map.Entry<String,Long>> condicion = entry -> (entry.getValue()>1);

        //ap.Entry<String, Long> stringLongEntry = resultado.entrySet().stream().filter(condicion).findAny().get();

        List<Map.Entry<String, Long>> repetidos = resultado.entrySet().stream().filter(condicion).collect(Collectors.toList());

        if(repetidos.size()>0)
            return true;
        else
            return false;
    }

    public Map<String,List<Empleado>> obtenerDnisRepetidos(){

        Stream<Empleado> empleados = lista.values().stream();

        TreeMap<String, List<Empleado>> resultado = empleados.collect(Collectors.groupingBy(Empleado::getDNI, TreeMap::new, Collectors.toList()));


        TreeMap<Integer, List<List<Empleado>>> collect = resultado.values().stream().filter(listaEmpleados -> listaEmpleados.size() > 1).collect(Collectors.groupingBy(empleado -> empleado.size(),TreeMap::new, Collectors.toList()));


        return null;
    }

    public boolean hayCorreosRepetidos(){
        Stream<Empleado> empleados = lista.values().stream();


        TreeMap<String, Long> resultado = empleados.collect(Collectors.groupingBy(Empleado::getEmail, TreeMap::new, Collectors.counting()));

        Predicate<Map.Entry<String,Long>> condicion = entry -> (entry.getValue()>1);

        List<Map.Entry<String, Long>> repetidos = resultado.entrySet().stream().filter(condicion).collect(Collectors.toList());

        if(repetidos.size()>0)
            return true;
        else
            return false;
    }

    public Map<String,List<Empleado>> obtenerCorreosRepetidos(){
        Stream<Empleado> empleados = lista.values().stream();



        TreeMap<String, List<Empleado>> resultado = empleados.collect(Collectors.groupingBy(Empleado::getEmail, TreeMap::new, Collectors.toList()));

        Predicate<Map.Entry<String,List<Empleado>>> condicion = entry -> (entry.getValue().size()>1);


        return resultado.entrySet().stream().filter(condicion).map(key -> key.getValue()).flatMap(empleado -> empleado.stream()).collect(Collectors.groupingBy(Empleado::getDNI));


    }


    public int obtenerNumeroEmpleados() {
        return lista.size();
    }
}
