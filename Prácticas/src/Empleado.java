/**
 * Created by mrsas on 13/03/2017.
 */
public class Empleado {

    private String dni,nombre,apellidos,email,division,departamento;

    public Empleado(){
        this.dni = "";
        this.nombre = "";
        this.apellidos = "";
        this.email = "";
        this.division = "DIVNA";
        this.departamento = "DEPNA";

    }

    public Empleado(String dni, String nombre, String apellidos, String email  ){
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.division = "DIVNA";
        this.departamento = "DEPNA";

    }

    public String getDNI(){
        return this.dni;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getApllidos(){
        return this.apellidos;
    }

    public String getEmail(){
        return this.email;
    }

    public String getDepartamento(){
        return this.departamento;
    }

    public String getDivision(){
        return this.division;
    }

}
