
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mrsas on 06/03/2017.
 */
public class Empleado {
    // Dato miembro para almacenar el nombre
    private String nombre;

    // Dato miembro para almacenar primer apellido
    private String primerApellido;

    // Dato miembro para almacenar el sueldo
    private double sueldo;

    // Dato miembro para almacenar el departamento
    private String departmento;

    // Constructor
    public Empleado(String nombre, String primerApellido,
                    double sueldo, String dapartamento) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.sueldo = sueldo;
        this.departmento = dapartamento;
    }

    // Metodo para asignar el nombre
    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }

    // Metodo para acceder al nombre
    public String obtenerNombre() {
        return nombre;
    }

    // Metodo para asignar el apellido
    public void asignarPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    // Metodo para obtener el primer apellido
    public String obtenerPrimerApellido() {
        return primerApellido;
    }

    // Metodo para asignar el valor de sueldo
    public void asignarSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    // Metodo para obtener el sueldo
    public double obtenerSueldo() {
        return sueldo;
    }

    // Metodo par asignar el departamento
    public void asignarDepartamento(String departamento) {
        this.departmento = departamento;
    }

    // Metodo para obteber el valor del dato miembro departamento
    public String obtenerDepartamento() {
        return departmento;
    }

    // Recupera nombre y primer apellido
    public String obtenerNombreApellido() {
        return String.format("%s %s", obtenerNombre(), obtenerPrimerApellido());
    }

    // Metodo toString
    @Override
    public String toString() {
        return String.format("%-8s %-8s %8.2f   %s",
                obtenerNombre(), obtenerPrimerApellido(), obtenerSueldo(),
                obtenerDepartamento());
    }
}


