/**
 * Created by mrsas on 13/03/2017.
 */
public class Empleado {

    private String dni,nombre,apellidos,email;
    private Division division;
    private Departamento departamento;

    public Empleado(){
        this.dni = "";
        this.nombre = "";
        this.apellidos = "";
        this.email = "";
        this.division = Division.DIVNA;
        this.departamento = Departamento.DEPNA;

    }

    public Empleado(String dni, String nombre, String apellidos, String email  ){
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.division = Division.DIVNA;
        this.departamento = Departamento.DEPNA;

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

    public Departamento getDepartamento(){
        return this.departamento;
    }

    public Division getDivision(){
        return this.division;
    }

    public void setDivision(Division div){
        this.division = div;
    }

    public void setDepartamento(Departamento dep){
        this.departamento = dep;
    }

}
