import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MoneyConverter {
    public static void main(String[] args) {
        try {
            // URL de la API
            String url = "https://v6.exchangerate-api.com/v6/ce19a254375356b26c0fd8c0/latest/USD";

            // Cliente y solicitud
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Respuesta de la API
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();

            // Parseando el JSON usando Gson
            JsonObject root = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject conversionRates = root.getAsJsonObject("conversion_rates");

            // Monedas disponibles
            String[] monedas = {"USD", "PEN", "CLP", "VES", "EUR", "JPY"};

            // Interacción con el usuario
            Scanner scanner = new Scanner(System.in);
            boolean continuar = true;

            while (continuar) {
                System.out.println("\n=== Convertidor de Monedas ===");
                System.out.println("1. Realizar una conversión");
                System.out.println("2. Salir");
                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                if (opcion == 1) {
                    // Mostrar monedas disponibles
                    System.out.println("\nMonedas disponibles:");
                    for (int i = 0; i < monedas.length; i++) {
                        System.out.println((i + 1) + ". " + monedas[i]);
                    }

                    // Selección de moneda origen
                    System.out.print("Selecciona la moneda de origen (número): ");
                    int indiceOrigen = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpiar el buffer

                    // Selección de moneda destino
                    System.out.print("Selecciona la moneda de destino (número): ");
                    int indiceDestino = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpiar el buffer

                    // Validar selección
                    if (indiceOrigen >= 0 && indiceOrigen < monedas.length &&
                            indiceDestino >= 0 && indiceDestino < monedas.length) {
                        String monedaOrigen = monedas[indiceOrigen];
                        String monedaDestino = monedas[indiceDestino];

                        // Ingresar cantidad
                        System.out.print("Ingresa la cantidad a convertir: ");
                        double cantidad = scanner.nextDouble();
                        scanner.nextLine(); // Limpiar el buffer

                        // Obtener tasas de conversión
                        double tasaOrigen = conversionRates.get(monedaOrigen).getAsDouble();
                        double tasaDestino = conversionRates.get(monedaDestino).getAsDouble();

                        // Calcular conversión
                        double resultado = (cantidad / tasaOrigen) * tasaDestino;

                        // Mostrar resultado
                        System.out.printf("La conversión de %.2f %s a %s es: %.2f %s\n",
                                cantidad, monedaOrigen, monedaDestino, resultado, monedaDestino);
                    } else {
                        System.out.println("Selección inválida. Intenta de nuevo.");
                    }
                } else if (opcion == 2) {
                    System.out.println("Gracias por usar el convertidor de monedas. ¡Adiós!");
                    continuar = false;
                } else {
                    System.out.println("Opción no válida. Intenta de nuevo.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
