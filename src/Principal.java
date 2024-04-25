import com.google.gson.JsonSyntaxException;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();
        Menu menu = new Menu();
        try {

            System.out.println("***********************************************\n"+
                    "*Bienvino al sistema de conversion de monedas!*\n"+
                    "***********************************************\n");
            MonedaExchangeRate monedaExchangeRate = menu.codigoPais();
            System.out.println("Elija la moneda que quiera convertir: ");
            var numeroElemeto = Integer.valueOf(lectura.nextLine());
            System.out.println("A que moneda quiere convertir: ");
            var numeroElemeto2 = Integer.valueOf(lectura.nextLine());
            System.out.println("Que cantidad es la que quiere convertir: ");
            var cantidadPorConvertir = Integer.valueOf(lectura.nextLine());
            MonedaExchangeRate consultaMoneda = consulta.buscaMoneda(numeroElemeto, numeroElemeto2,cantidadPorConvertir);

        }catch (JsonSyntaxException e){
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicacion.");
        }
    }
}
