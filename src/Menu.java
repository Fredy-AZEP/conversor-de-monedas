import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Menu {

    public MonedaExchangeRate codigoPais() {
        URI codigoPais = URI.create("https://v6.exchangerate-api.com/v6/f8fc36d27264791334c5f0df/codes");


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

            //Crear tabla de monedas -----------------------------------------------------------------------------------
            // Determinar el número total de elementos y el número de columnas
            int totalElementos = codigos.size();
            int columnas = 18; // Cambia este valor según la cantidad de columnas que desees

            // Calcular la cantidad de elementos por columna
            int elementosPorColumna = (int) Math.ceil((double) totalElementos / columnas);

            // Imprimir título
            System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+------------+");
            System.out.println("|                                              Monedas Disponibles                                                   |");
            System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+------------+");

            // Mostrar los datos en forma de tabla
            int n = 1;
            int contador = 0;
            for (Map.Entry<String, String> entry : codigos.entrySet()) {
                if (contador % elementosPorColumna == 0 && contador != 0) {
                    System.out.println("|");
                }
                //if (contador % elementosPorColumna == 0) {
                //System.out.println();
                //}
                System.out.printf("| %-10s ", n + "." + entry.getKey());
                n++;
                contador++;
            }

            // Rellenar las últimas celdas si es necesario
            int celdasRestantes = columnas * elementosPorColumna - totalElementos;
            for (int i = 0; i < celdasRestantes; i++) {
                System.out.print("|                         |                         ");
            }

            // Imprimir borde inferior
            System.out.println("|");
            System.out.println("+-------------------------+-------------------------+-------------------------+-------------------------+------------+");


            return supported_codes;
        } catch (SocketException e) {
            throw new RuntimeException("Error al establecer una conexion con la api");
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException("No valido.");
        }
    }
}