import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Scanner;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class MoneyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Conversor de Monedas");
        System.out.println("Seleccione la moneda de origen:");
        System.out.println("1. USD (Dólar estadounidense)");
        System.out.println("2. PEN (Sol peruano)");
        System.out.println("3. CLP (Peso chileno)");
        System.out.println("4. VES (Bolívar venezolano)");
        System.out.println("5. EUR (Euro)");
        int monedaOrigen = scanner.nextInt();

        System.out.println("Seleccione la moneda de destino:");
        System.out.println("1. USD (Dólar estadounidense)");
        System.out.println("2. PEN (Sol peruano)");
        System.out.println("3. CLP (Peso chileno)");
        System.out.println("4. VES (Bolívar venezolano)");
        System.out.println("5. EUR (Euro)");
        int monedaDestino = scanner.nextInt();

        System.out.println("Ingrese la cantidad a convertir:");
        double cantidad = scanner.nextDouble();

        System.out.println("Procesando conversión...");

        try {
            // Crear cliente y solicitud
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/ce19a254375356b26c0fd8c0/latest/USD"))
                    .GET()
                    .build();

            // Obtener respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Analizar JSON
            Gson gson = new Gson();
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            // Extraer tasas de cambio
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
            double tasaPEN = rates.get("PEN").getAsDouble();
            double tasaCLP = rates.get("CLP").getAsDouble();
            double tasaUSD = rates.get("USD").getAsDouble();
            double tasaVES = rates.get("VES").getAsDouble();
            double tasaEUR = rates.get("EUR").getAsDouble();

            // Imprimir tasas de cambio
            System.out.println("Tasa de cambio de USD a PEN (Perú): " + tasaPEN);
            System.out.println("Tasa de cambio de USD a CLP (Chile): " + tasaCLP);
            System.out.println("Tasa de cambio de USD a USD (EE.UU.): " + tasaUSD);
            System.out.println("Tasa de cambio de USD a VES (Venezuela): " + tasaVES);
            System.out.println("Tasa de cambio de USD a EUR (Eurozona): " + tasaEUR);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
