import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.TreeMap;

public class ConsultaMoneda {

    public MonedaExchangeRate buscaMoneda(int numeroElemento, int numeroElemento2, int cantidadPorConvertir) {
        URI codigoPais = URI.create("https://v6.exchangerate-api.com/v6/f8fc36d27264791334c5f0df/codes");

        int cantidad = cantidadPorConvertir;
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

            // Obtener el valor del mapa según el número dado
            String valorDeseado = obtenerValorPorNumero(codigosOrdenados, numeroElemento);
            String segundoValor = obtenerValorPorNumero2(codigosOrdenados, numeroElemento2);


            // Imprimir el valor deseado del mapa
            if (valorDeseado != null) {

                System.out.println("Elegiste convertir: " + valorDeseado);
            } else {
                System.out.println("No se encontró el elemento #" + numeroElemento + " en el mapa.");
            }


            if (segundoValor != null) {

                System.out.println("Se convertira " + valorDeseado + " por " + segundoValor);
            } else {
                System.out.println("No se encontró el elemento #" + numeroElemento2 + " en el mapa.");
            }


            //Obtnecion de la conversion--------------------------------------------------------------------------------

            URI monedaSeleccionada = URI.create("https://v6.exchangerate-api.com/v6/f8fc36d27264791334c5f0df/pair/" + valorDeseado + "/" + segundoValor + "/" + cantidad);
            System.out.println(monedaSeleccionada);
            HttpClient client1 = HttpClient.newHttpClient();
            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(monedaSeleccionada)
                    .build();
            HttpResponse<String> response1 = client1
                    .send(request1, HttpResponse.BodyHandlers.ofString());

            MonedaExchangeRate conversion_result = new Gson().fromJson(response1.body(), MonedaExchangeRate.class);


            Double resultado = conversion_result.conversion_result();
            System.out.println(resultado);

            return supported_codes;


        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException("No valido.");
        }


    }

    private static String obtenerValorPorNumero(Map<String, String> mapa, int numero) {
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
        return null; // Devolver null si el número está fuera de rango
    }
}
