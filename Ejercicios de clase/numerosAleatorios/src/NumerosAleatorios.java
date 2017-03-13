import java.security.SecureRandom;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mrsas on 07/03/2017.
 */
public class NumerosAleatorios {
    public static void main(String args[]){
        //Generados de numeros aleatorios
        SecureRandom generador = new SecureRandom();

        //Especificamos el numero de muestras
        long muestras = 100_000_000;

        //Generacion de muestras
        Stream<Integer> flujoMuestras = generador.ints(muestras, 1, 7).boxed();

        //Organizamos los datos en un mapa con entradas de tipo <Integer,Long>
        Map<Integer, Long> mapaFinal = flujoMuestras.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        mapaFinal.forEach((valor,contador)->{
            System.out.println(valor + " - " + contador + " - Probabilidad:  " + ((contador*1.0)/muestras));
        });
    }
}
