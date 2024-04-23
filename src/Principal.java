import com.google.gson.JsonSyntaxException;

public class Principal {
    public static void main(String[] args) {
        ConsultaMoneda consulta = new ConsultaMoneda();
        try {
            MonedaExchangeRate moneda = consulta.buscaMoneda();
            System.out.println(moneda);
        }catch (JsonSyntaxException e){
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicacion.");
        }
    }
}
