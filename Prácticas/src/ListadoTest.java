
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Práctica 1 NTP
 */
public class ListadoTest {
    private static Listado listado;

    /**
     * Codigo a ejecutar antes de realizar las llamadas a los métodos
     * de la clase; incluso antes de la propia instanciación de la
     * clase. Por eso el método debe ser estatico
     */
    @BeforeClass
    public static void inicializacion() {
        System.out.println("Metodo inicializacion conjunto pruebas");
        // Se genera el listado de empleados
        try {
            listado = new Listado("./data/datos.txt");
        }catch(IOException e){
            System.out.println("Error en lectura de archivo de datos");
        };

        // Una vez disponibles los empleados se leen las listas
        // de asignaciones de empleados a cada grupo de las diferentes
        // asignaturas consideradas
        try {
            listado.cargarArchivoAsignacionDivision("./data/asignacionDIVNA.txt");
            listado.cargarArchivoAsignacionDivision("./data/asignacionDIVID.txt");
            listado.cargarArchivoAsignacionDivision("./data/asignacionDIVSW.txt");
            listado.cargarArchivoAsignacionDivision("./data/asignacionDIVHW.txt");
            listado.cargarArchivoAsignacionDivision("./data/asignacionDIVSER.txt");
            listado.cargarArchivoAsignacionDepartamento("./data/asignacionDEPNA.txt");
            listado.cargarArchivoAsignacionDepartamento("./data/asignacionDEPSB.txt");
            listado.cargarArchivoAsignacionDepartamento("./data/asignacionDEPSM.txt");
            listado.cargarArchivoAsignacionDepartamento("./data/asignacionDEPSA.txt");
        } catch (IOException e) {
            System.out.println("Error en lectura de archivos de asignacion");
        }
        System.out.println();
    }

    /**
     * Test para comprobar que se ha leido de forma correcta la
     * informacion de los empleados (al menos que el listado contiene
     * datos de 100 empleados)
     * @throws Exception
     */
    @org.junit.Test
    public void testConstruccionListado() throws Exception{
        assert(listado.obtenerNumeroEmpleados() == 1000);
    }

    /**
     * Test del procedimiento de asignacion de grupos procesando
     * los archivos de asignacion. Tambien implica la prueba de
     * busqueda de empleados sin grupo asignado en alguna asignatura
     * @throws Exception
     */
    @Test
    public void testCargarArchivosAsignacion() throws Exception {
        // Se obtienen los empleados no asignados a cada asignatura
        // y se comprueba su valor
        /*
        System.out.println(listado.buscarEmpleadosSinDepartamento(Division.DIVNA).size());
        System.out.println(listado.buscarEmpleadosSinDepartamento(Division.DIVID).size());
        */
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVNA).size() == 49);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVID).size() == 54);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVSW).size() == 42);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVHW).size() == 44);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVSER).size() == 49);
    }

    /**
     * Prueba para el procedimiento de conteo de grupos para cada una
     * de las asignaturas
     */
    @Test
    public void testObtenerContadoresDepartamentos(){

        // Se comprueba que los valores son DEPNA = 49, DEPSB = 48, DEPSM = 53, DEPSA = 41
        Long contadoresReferenciaSER[]={49L,48L,53L,41L};
        Long contadoresReferenciaNA[]={49L,53L,53L,58L};
        Long contadoresReferenciaSW[]={42L,52L,45L,53L};
        Long contadoresReferenciaHW[]={44L,43L,67L,62L};
        Long contadoresReferenciaID[]={54L,49L,42L,43L};

        Long contadoresCalculados[]=new Long[4];

        // Se obtienen los contadores para la asignatura ES
        Map<Departamento, Long> contadoresSER = listado.obtenerContadoresDepartamento(
                Division.DIVSER);
        System.out.println(Division.DIVSER );
        contadoresSER.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadoresSER.get(key)));
        assertArrayEquals(contadoresSER.values().toArray(contadoresCalculados),
                          contadoresReferenciaSER);

        System.out.println("\n"+Division.DIVNA);
        Map<Departamento, Long> contadoresNA = listado.obtenerContadoresDepartamento(
                Division.DIVNA);
        contadoresNA.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadoresNA.get(key)));
        // Se comprueba que los valores son DEPNA = 49, DEPSB = 48, DEPSM = 53, DEPSA = 41
        assertArrayEquals(contadoresNA.values().toArray(contadoresCalculados),
                contadoresReferenciaNA);

        System.out.println("\n"+Division.DIVID);
        Map<Departamento, Long> contadoresID = listado.obtenerContadoresDepartamento(
                Division.DIVID);
        contadoresID.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadoresID.get(key)));
        // Se comprueba que los valores son DEPNA = 49, DEPSB = 48, DEPSM = 53, DEPSA = 41
        assertArrayEquals(contadoresID.values().toArray(contadoresCalculados),
                contadoresReferenciaID);

        System.out.println("\n"+Division.DIVSW);
        Map<Departamento, Long> contadoresSW = listado.obtenerContadoresDepartamento(
                Division.DIVSW);
        contadoresID.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadoresSW.get(key)));
        // Se comprueba que los valores son DEPNA = 49, DEPSB = 48, DEPSM = 53, DEPSA = 41
        assertArrayEquals(contadoresSW.values().toArray(contadoresCalculados),
                contadoresReferenciaSW);

        System.out.println("\n"+Division.DIVHW);
        Map<Departamento, Long> contadoresHW = listado.obtenerContadoresDepartamento(
                Division.DIVHW);
        contadoresID.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadoresHW.get(key)));
        // Se comprueba que los valores son DEPNA = 49, DEPSB = 48, DEPSM = 53, DEPSA = 41
        assertArrayEquals(contadoresHW.values().toArray(contadoresCalculados),
                contadoresReferenciaHW);
    }

    /**
     * Prueba del procedimiento general de obtencion de contadores
     * para todas las asignaturas
     * @throws Exception
     */
    @Test
    public void testObtenerContadoresDivisionDepartamento() throws Exception {
        // Se obtienen los contadores para todos los grupos
        Map<Division, Map<Departamento, Long>> contadores =
                listado.obtenerContadoresDivisionDepartamento();

        // Se comprueban los valores obtenenidos con los valores por referencia
        Long contadoresReferenciaSER[]={49L,48L,53L,41L};
        Long contadoresReferenciaNA[]={49L,53L,53L,58L};
        Long contadoresReferenciaSW[]={42L,52L,45L,53L};
        Long contadoresReferenciaHW[]={44L,43L,67L,62L};
        Long contadoresReferenciaID[]={54L,49L,42L,43L};



        // Se comprueban los resultado del metodo con los de referencia
        Long contadoresCalculados[]=new Long[4];
        assertArrayEquals(contadores.get(Division.DIVNA).values().
                toArray(contadoresCalculados),contadoresReferenciaNA);
        assertArrayEquals(contadores.get(Division.DIVID).values().
                toArray(contadoresCalculados),contadoresReferenciaID);
        assertArrayEquals(contadores.get(Division.DIVHW).values().
                toArray(contadoresCalculados),contadoresReferenciaHW);
        assertArrayEquals(contadores.get(Division.DIVSW).values().
                toArray(contadoresCalculados),contadoresReferenciaSW);
        assertArrayEquals(contadores.get(Division.DIVSER).values().
                toArray(contadoresCalculados),contadoresReferenciaSER);
    }

    /**
     * Prueba del procedimiento general de obtencion de empleados sin departamento
     * @throws Exception
     */
    @Test
    public void testBuscarEmpleadosSinDepartamento()throws Exception {

        assertEquals(listado.buscarEmpleadosSinDepartamento(Division.DIVNA).size(),49);
        assertEquals(listado.buscarEmpleadosSinDepartamento(Division.DIVSER).size(),49);
        assertEquals(listado.buscarEmpleadosSinDepartamento(Division.DIVID).size(),54);
        assertEquals(listado.buscarEmpleadosSinDepartamento(Division.DIVSW).size(),42);
        assertEquals(listado.buscarEmpleadosSinDepartamento(Division.DIVHW).size(),44);

    }

    /**
     * Prueba del procedimiento general de obtencion de empleados con division pero sin departamento
     * @throws Exception
     */
    @Test
    public void testbuscarEmpleadosConDivisionSinDepartamento()throws Exception {

        assertEquals(listado.buscarEmpleadosConDivisionSinDepartamento().size(),189);

    }

    /**
     * Prueba del procedimiento general de obtencion de empleados sin division
     * @throws Exception
     */
    @Test
    public void testbuscarEmpleadosSinDivision()throws Exception {

        assertEquals(listado.buscarEmpleadosSinDivision().size(),213);

    }

    /**
     * Prueba del procedimiento toString
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception{
        System.out.println(listado.toString());
    }

    /**
     * Prueba del procedimiento listar empleados
     * @throws Exception
     */
    @Test
    public void testListarEmpleados() throws Exception{
        listado.listarEmpleados();
    }

    /**
     * Prueba del procedimiento hayDniRepetidos
     * @throws Exception
     */
    @Test
    public void testHayDnisRepetidos() throws Exception{
        assertFalse(listado.hayDnisRepetidos());
    }

    /**
     * Prueba del procedimiento hayCorreosRepetidos
     * @throws Exception
     */
    @Test
    public void testHayCorreosRepetidos() throws Exception{
        assert(listado.hayCorreosRepetidos());
    }

    /**
     * Prueba del procedimiento ObtenerCorreosRepetidos
     * @throws Exception
     */
    @Test
    public void testObtenerCorreosRepetidos() throws Exception{
        //Comprobamos el numero de emails repetidos; que en este caso son 8.
        System.out.println("Numero de correos repetidos: " + listado.obtenerCorreosRepetidos().entrySet().stream().map(key -> key.getValue()).flatMap(empleado -> empleado.stream()).collect(Collectors.groupingBy(Empleado::getEmail)).size());

        assertEquals(listado.obtenerCorreosRepetidos().entrySet().stream().map(key -> key.getValue()).flatMap(empleado -> empleado.stream()).collect(Collectors.groupingBy(Empleado::getEmail)).size(),8);
        //O bien calculamos el numero de empleados que repirten el email y lo comparamos.
        assertEquals(listado.obtenerCorreosRepetidos().size(),17);

    }

    /**
     * Prueba del procedimiento ObtenerDniRepetidos
     * @throws Exception
     */
    @Test
    public void testObtenerDnisRepetidos() throws Exception{
        //Comprobamos el numero de DNIs repetidos; que en este caso son 0.
        System.out.println("Numero de DNIs repetidos: " + listado.obtenerDnisRepetidos().size());

        assertEquals(listado.obtenerDnisRepetidos().size(),0);

    }



}