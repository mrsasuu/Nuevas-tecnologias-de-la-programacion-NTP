import java.util.stream.Stream;

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

    public String getDepartamentoString() {
        String resultado = "DEPNA";
        switch (departamento){
            case DEPNA:
                resultado = "DEPNA";
                break;
            case DEPSA:
                resultado = "DEPSA";
                break;
            case DEPSB:
                resultado = "DEPSB";
                break;
            case DEPSM:
                resultado = "DEPSM";
                break;
        }

        return resultado;

    }

    public String getDivisionString() {
        String resultado = "DIVNA";
        switch (this.division){
            case DIVNA:
                resultado = "DIVNA";
                break;
            case DIVSW:
                resultado = "DIVSW";
                break;
            case DIVHW:
                resultado = "DIVHW";
                break;
            case DIVID:
                resultado = "DIVID";
                break;
            case DIVSER:
                resultado = "DIVSER";
                break;
        }

        return resultado;
    }


    @Override
    public String toString() {
        return getDNI() + " " + getNombre() + " " + getApllidos() + " " +  getEmail() + " " + getDivisionString() + " " + getDepartamentoString();
    }


}
