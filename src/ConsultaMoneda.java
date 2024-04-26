import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ConsultaMoneda {

    public MonedaExchangeRate buscaMoneda() {
        URI codigoPais = URI.create("https://v6.exchangerate-api.com/v6/f8fc36d27264791334c5f0df/codes");

        Scanner lectura = new Scanner(System.in);

        //int cantidad = cantidadPorConvertir;
        try {

            //Obtencion del codigo del pais-----------------------------------------------------------------------------
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(codigoPais)
                    .build();
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            MonedaExchangeRate supported_codes = new Gson().fromJson(response.body(), MonedaExchangeRate.class);
            Map<String, String> codigos = supported_codes.supported_codes();

            //Obtener la moneda seleccionada----------------------------------------------------------------------------
            Map<String, String> codigosOrdenados = new TreeMap<>(codigos);

            String primerValor = null;
            String siguienteValor = null;

            int numeroElemento;
            do {
                System.out.print("Elija el número de la moneda que desea convertir: ");
                while (!lectura.hasNextInt()) {
                    System.out.println("Entrada no valida. Por favor ingrese un numero valido: ");
                    lectura.nextLine();
                }
                numeroElemento = Integer.parseInt(lectura.nextLine());
                primerValor = obtenerValorPorNumero(codigosOrdenados, numeroElemento);
                // Verificar si el número está dentro del rango
                if (numeroElemento < 1 || numeroElemento > codigos.size()) {
                    System.out.println("Número de moneda no válido. Por favor, ingrese un número válido: ");
                }
            } while (numeroElemento < 1 || numeroElemento > codigos.size());
            System.out.println("Seleccionaste la moneda [" + primerValor + "].\n");

            int numeroElemento2;
            do {
                System.out.print("Ahora elija el numero de la moneda a la que quiere convertir: ");
                while (!lectura.hasNextInt()) {
                    System.out.println("Entrada no valida. Por favor ingrese un numero valido: ");
                    lectura.nextLine();
                }
                numeroElemento2 = Integer.parseInt(lectura.nextLine());
                siguienteValor = obtenerValorPorNumero2(codigosOrdenados, numeroElemento2);

                // Verificar si el número está dentro del rango
                if (numeroElemento2 < 1 || numeroElemento2 > codigos.size()) {
                    System.out.println("Entrada no valida. Por favor, ingrese un número válido: ");
                }
            } while (numeroElemento2 < 1 || numeroElemento2 > codigos.size());
            System.out.println("Se convertira [" + primerValor + "] por [" + siguienteValor + "]\n");

            int cantidadPorConvertir;
            do {
                System.out.println("Ingrese la cantidad que desea convertir: ");
                while (!lectura.hasNextInt()) {
                    System.out.println("Formato invalido, ingrese una cantida: ");
                    lectura.nextLine();
                }
                cantidadPorConvertir = Integer.valueOf(lectura.nextLine());
            } while (cantidadPorConvertir < 1);




            // Obtener el valor del map según el número dado
            String valorDeseado = obtenerValorPorNumero(codigosOrdenados, numeroElemento);
            String segundoValor = obtenerValorPorNumero2(codigosOrdenados, numeroElemento2);




            // Imprimir el valor deseado del map
            /*if (valorDeseado != null) {

                System.out.println("Elegiste convertir: " + valorDeseado);
            } else {
                System.out.println("No se encontró el elemento #" + numeroElemento + " en el mapa.");
            }


            if (segundoValor != null) {

                System.out.println("Se convertira [" + valorDeseado + "] por [" + segundoValor + "]");
            } else {
                System.out.println("No se encontró el elemento #" + numeroElemento2 + " en el mapa.");
            }*/


            //Obtnecion de la conversion--------------------------------------------------------------------------------

            URI monedaSeleccionada = URI.create("https://v6.exchangerate-api.com/v6/f8fc36d27264791334c5f0df/pair/" + valorDeseado + "/" + segundoValor + "/" + cantidadPorConvertir);
            //System.out.println(monedaSeleccionada);
            HttpClient client1 = HttpClient.newHttpClient();
            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(monedaSeleccionada)
                    .build();
            HttpResponse<String> response1 = client1
                    .send(request1, HttpResponse.BodyHandlers.ofString());

            MonedaExchangeRate conversion_result = new Gson().fromJson(response1.body(), MonedaExchangeRate.class);


            Double resultado = conversion_result.conversion_result();
            //System.out.println(resultado);
            System.out.println("La cantidad de " + cantidadPorConvertir + "[" + valorDeseado + "] corresponde al valor final de ==>> " + resultado + "[" + segundoValor + "]\n");

            return supported_codes;
        } catch (SocketException e) {
            throw new RuntimeException("Error al establecer una conexion con la api");
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException("No valido.");
        }


    }

    public static String obtenerValorPorNumero(Map<String, String> mapa, int numero) {
        int contador = 1;
        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            if (contador == numero) {
                return entry.getKey();
            }
            contador++;
        }
        return null; // Devolver null si el número está fuera de rango
    }

    private static String obtenerValorPorNumero2(Map<String, String> mapa, int numero2) {
        int contador = 1;
        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            if (contador == numero2) {
                return entry.getKey();
            }
            contador++;
        }
        return null;
    }

}
