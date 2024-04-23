import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConsultaMoneda {

    Gson gson  = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();
    String tipoMoneda = "USD";
    public MonedaExchangeRate buscaMoneda() {
        URI moneda = URI.create("https://v6.exchangerate-api.com/v6/f8fc36d27264791334c5f0df/latest/" + tipoMoneda);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(moneda)
                .build();

        try {
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());


            String json = response.body();
            System.out.println(json);
            /*MonedaExchangeRate monedaExchangeRate = gson.fromJson(json, MonedaExchangeRate.class);
            Moneda moneda1 = new Moneda(monedaExchangeRate);
            System.out.println(moneda1);*/

            //return new Gson().fromJson(response.body(), MonedaExchangeRate.class);

            MonedaExchangeRate exchangeRate = new Gson().fromJson(response.body(), MonedaExchangeRate.class);

            Map<String, Double> conversion_rates = exchangeRate.conversion_rates();

            Double ars = conversion_rates.get("ARS");
            Double bob = conversion_rates.get("BOB");
            Double brl = conversion_rates.get("BRL");
            Double clp = conversion_rates.get("CLP");
            Double cop = conversion_rates.get("COP");
            Double usd = conversion_rates.get("USD");

            System.out.println(ars);
            System.out.println(bob);
            System.out.println(brl);
            System.out.println(clp);
            System.out.println(cop);
            System.out.println(usd);

            return exchangeRate;
            //double usdToArg = (usd * 10) * usdToArgRate;
            //System.out.println(usdToArg);


        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException("No valido.");
        }

    }
}
