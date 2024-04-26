# Conversor de Monedas
Programa realizado en Java para convertir diferentes divisas en otra, dicha aplicacion se muestra en la consola y esta misma consume una API para mostrar dichos datos.
## Comenzando 🚀
A continuacion explicare el funcionamiento de dicho programa.
### Pre-requisitos📋
- Tener un IDE que pueda utilizar Java por ejemplo Intellij IDE, Eclipse o Visual Estudio Code.
- Tener una cuenta en <link>https://www.exchangerate-api.com</link> para poder consumir la api.
### Configuracion de la API 🔧
- Para poder usar su propia api tendra que registrarse en la pagina que se proporciono antes.
- Un vez obtenida su API tendra que configurar los archivos Menu.java y ConsultaMoneda.java dentro de las variables URI y colocar su propia API.
```java
URI.create("https://v6.exchangerate-api.com/v6/YOUR-API-KEY/codes");
```
## Modo de Uso
- Al iniciar el programa se le mostrara un pequeño saludo seguido de un menu en el que se le pedira que seleccione una opción:
![image](https://github.com/Fredy-AZEP/conversor-de-monedas/assets/66339764/8a74ee17-2fd2-490d-b4b4-c6350d01a892)
- Si selecciona (1) se desplegara una tabla en la que ustes podra escoger le moneda que quiere convertir:
![image](https://github.com/Fredy-AZEP/conversor-de-monedas/assets/66339764/fb2f3ba0-5d75-4e87-b82d-da76194fe416)
- Caso contrario si selecciona (0) se finalizara el programa con un mensaje de salida:
![image](https://github.com/Fredy-AZEP/conversor-de-monedas/assets/66339764/2851484c-58f2-48d4-8b37-eed431ee11a3)
- Despues de seleccionar la moneda se mostrara un mensaje con la moneda que selecciono en base al numero que coloco y despues tendra que seleccionar la moneda a la que hara la conversion:
![image](https://github.com/Fredy-AZEP/conversor-de-monedas/assets/66339764/2ef6d6bd-eb24-433b-be55-07f2eb12994f)
- Seguido de eso se mostrar un mensaje con las monedas seleccionadas y ahora toca colocar la cantidad que quiere convertir:
![image](https://github.com/Fredy-AZEP/conversor-de-monedas/assets/66339764/7c1c38e2-66df-4083-a426-6de734194a42)
- Para finalizar se muestra un mensaje con la cantidad convertida y se redirije al menu del inicio:
![image](https://github.com/Fredy-AZEP/conversor-de-monedas/assets/66339764/6876a741-338e-4beb-b924-f5ff15306fb3)
- En caso de poner un dato incorrecto, el programa esta validado para solo aceptar datos que pida el mismo programa, en caso contrario mostrar una advertencia y no procedera hasta poner un dato valido y claro que esto esta implementado en cada processo del programa:
![image](https://github.com/Fredy-AZEP/conversor-de-monedas/assets/66339764/a04b18c0-db58-4945-8a37-d94331f9cef1)
![image](https://github.com/Fredy-AZEP/conversor-de-monedas/assets/66339764/95d33f14-e50b-4d0c-aba6-166a7aa5e383)



---
Eso seria todo, espero les sea de utilidad este programa ya sea para lo que fue contruido o para baserse en este y crear uno propio con ❤️.
