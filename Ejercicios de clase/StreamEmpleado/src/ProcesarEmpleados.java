import com.sun.java.swing.plaf.windows.WindowsTreeUI;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by mrsas on 06/03/2017.
 */
public class ProcesarEmpleados {
    // Se crea un array de empleados para trabajar con distintos ejemplos
    private Empleado[] empleados = {
            new Empleado("Juan", "Lopez", 5000, "IT"),
            new Empleado("Antonio", "Garcia", 7600, "IT"),
            new Empleado("Mateo", "Insausti", 3587.5, "Ventas"),
            new Empleado("JoaquÃ­n", "Fernandez", 4700.77, "Marketing"),
            new Empleado("Lucas", "Martinez", 6200, "IT"),
            new Empleado("Pedro", "Garcia", 3200, "Ventas"),
            new Empleado("Fernado", "Gonzalez", 4236.4, "Marketing")
    };

    // Se crea una lista para facilitar la creacion de flujos
    private List<Empleado> lista=Arrays.asList(empleados);

    public void listarEmpleados(){
        lista.stream().forEach(System.out::println);

    }

    public List<Empleado> filtrarCondicion(){
        Predicate<Empleado> condicion = empleado -> (empleado.obtenerSueldo()>=4000) && (empleado.obtenerSueldo()<=6000);

        return (lista.stream().filter(condicion).collect(Collectors.toList()));
        /*
        Stream<Empleado> empleadoStream = lista.stream().filter(condicion);
        empleadoStream.forEach(System.out::println);
        */


    }

    public Empleado buscarPrimero(){
        //Aplicamos el filtro solo al primero y así lo paramos antes de que busque por toda la coleccion.
        Predicate<Empleado> condicion = empleado -> (empleado.obtenerSueldo()>=4000) && (empleado.obtenerSueldo()<=6000);

        return lista.stream().filter(condicion).findFirst().get();
        //return lista.stream().filter(condicion).findFirst().orElse(null);
    }

    public Map<String,List<Empleado>> agruparPorDepartamentosImperativo(){
        Map<String,List<Empleado>> agrupamiento = new TreeMap<>();
        String departamento;
        List<Empleado> listaEmpleados;

        //Iteracion sobre la lista
        for (int i =0; i < lista.size(); i++){

            departamento = lista.get(i).obtenerDepartamento();

            listaEmpleados = agrupamiento.get(departamento);

            if (listaEmpleados == null){
                listaEmpleados = new LinkedList<>();
                agrupamiento.put(departamento,listaEmpleados);

            }
            //Agregamos el empleado a la lista

            listaEmpleados.add(lista.get(i));
        }

        Iterator<String> claves = agrupamiento.keySet().iterator();

        String clave;

        while(claves.hasNext()){
            clave = claves.next();

            //mostrar el numero de empleados en el departamento
            System.out.println(clave+": " + agrupamiento.get(clave).size());

            //listamos los empleados del departamento
            listaEmpleados=agrupamiento.get(clave);

            for (int i = 0; i < listaEmpleados.size();i++){
                System.out.println(listaEmpleados.get(i));
            }
        }

        return agrupamiento;
    }

    public Map<String,List<Empleado>> agrupamientoPorDepartamentos(){
        Map<String, List<Empleado>> agrupamiento = lista.stream().collect(Collectors.groupingBy(Empleado::obtenerDepartamento));

        agrupamiento.forEach((departamento,empleados) -> {
            System.out.println(departamento);
            empleados.forEach(System.out::println);
        });

        return agrupamiento;
    }
    
    public Map<String, Long> contarPorDepartamento(){
        //Vamos a especificarle con que funcion va a agrupar, en que tipo de dato va a guardarla y le vamos a decir que la colleccion que se genera se reduza a un solo valor.
        TreeMap<String, Long> grupos = lista.stream().collect(Collectors.groupingBy(Empleado::obtenerDepartamento, TreeMap::new, Collectors.counting()));
    }

    //Obtener la suma de los sueldos.
    public double sumarSueldos(){
        return lista.stream().mapToDouble(Empleado::obtenerSueldo).sum();
    }



    // Metodo main para pruebas
    public static void main(String[] args) {
        ProcesarEmpleados objeto = new ProcesarEmpleados();

        objeto.listarEmpleados();

        List<Empleado> lista = objeto.filtrarCondicion();

        System.out.println();
        lista.stream().forEach(System.out::println);

    }

}
