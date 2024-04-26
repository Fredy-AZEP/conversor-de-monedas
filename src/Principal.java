import com.google.gson.JsonSyntaxException;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        int opcion;
        ConsultaMoneda consulta = new ConsultaMoneda();
        Menu menu = new Menu();
        try {


            do {

                System.out.println("***********************************************\n" +
                                   "*Bienvino al sistema de conversion de monedas!*\n" +
                                   "***********************************************\n" +
                                   "*Elija una opcion para continuar              *\n" +
                                   "*1 - Iniciar Conversion                       *\n" +
                                   "*0 - Salir del Sistema                        *\n" +
                                   "***********************************************");
                while (!lectura.hasNextInt()){
                    System.out.println("Formato invalido. Ingrese un numero dentro del menu!");
                    lectura.nextLine();
                }
                opcion = Integer.parseInt(lectura.nextLine());
                switch (opcion) {
                    case 1:

                        //System.out.println("***********************************************\n" +
                        //        "*Bienvino al sistema de conversion de monedas!*\n" +
                        //        "***********************************************\n");
                        //MonedaExchangeRate monedaExchangeRate = menu.codigoPais();
                        menu.codigoPais();
                        /*System.out.println("Elija el numero de la moneda que quiera convertir: ");
                        var numeroElemeto = Integer.valueOf(lectura.nextLine());

                        System.out.println("Ahora elija el numero de la moneda que quiere convertir: ");
                        var numeroElemeto2 = Integer.valueOf(lectura.nextLine());

                        System.out.println("Ingrese la cantidad que desea convertir: ");
                        var cantidadPorConvertir = Integer.valueOf(lectura.nextLine());

                         */
                        consulta.buscaMoneda();

                        break;
                    case 0:
                        System.out.println("Muchas gracias por usar nuestro sistema!");
                        break;
                    default:
                        System.out.println("Opcion invalida!");
                        break;
                }
            } while (opcion != 0);
            lectura.close();
        } catch (NumberFormatException e) {
            System.out.println("Formato invalido");
        } catch (JsonSyntaxException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicacion.");
        }
    }
}
