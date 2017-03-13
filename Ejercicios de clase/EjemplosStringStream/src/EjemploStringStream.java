import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mrsas on 06/03/2017.
 */
public class EjemploStringStream {

    public class EjemplosStringStream {
        // Dato miembro para almacenar las cadenas de caracteres con las que probar
        private String[] cadenas={"Rojo", "Naranja", "Amarillo", "Verde", "azul", "indigo", "Violeta"};

        public List<String> pasarMayusculas(){//Dos formas de hacer lo mismo.
            List<String> collect = Arrays.stream(cadenas).map(String::toUpperCase).collect(Collectors.toList());
            return Arrays.stream(cadenas).map(x -> x.toUpperCase()).collect(Collectors.toList());
        }

        public List<String> filtrarOrdenar(){
            //Filtramos todas las cadenas que tengan una letra superior a la m, ordenado sin tener en cuenta mayusculas o minusculas
            return Arrays.stream(cadenas).filter(cadena -> cadena.compareToIgnoreCase("m")>0).sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
        }

        public List<String> filtrarOrdenarInvertido(){
            //Filtramos todas las cadenas que tengan una letra superior a la m, ordenado sin tener en cuenta mayusculas o minusculas pero filtrado al reves.
            return Arrays.stream(cadenas).filter(cadena -> cadena.compareToIgnoreCase("m")>0).sorted(String.CASE_INSENSITIVE_ORDER.reversed()).collect(Collectors.toList());
        }

        public void imprimirLista(List<String> lista){
            for(int i =0; i < lista.size(); i++ ){
                System.out.println(lista.get(i).toString());
            }
        }

        // Metodo main para pruebas
        public static void main(String args[]){
            // Prueba del metodo de paso a mayusculas
            // Se crea objeto de la clase
            EjemplosStringStream objeto=new EjemplosStringStream();

            List<String> cadenasProcesadas = objeto.filtrarOrdenar();
            objeto.imprimirLista(objeto.filtrarOrdenar());
            objeto.imprimirLista(objeto.filtrarOrdenarInvertido());

            cadenasProcesadas.stream().forEach(x-> System.out.println(x));


            List<String> cadenasInvertidas = objeto.filtrarOrdenarInvertido();

            cadenasInvertidas.stream().forEach(x -> System.out.println(x));




        }
    }

}
