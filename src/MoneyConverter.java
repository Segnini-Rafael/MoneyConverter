import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MoneyConverter {
    public static void main(String[] args) {
        try {
            // Crear el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // URL de la API con tu API Key
            String apiKey = "ce19a254375356b26c0fd8c0";
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

            // Crear la solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Mostrar la respuesta JSON completa (para depuración)
            System.out.println("Respuesta completa:");
            System.out.println(response.body());

            // Parsear la respuesta JSON
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            // Extraer las tasas de cambio
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
            double tasaEuro = conversionRates.get("EUR").getAsDouble();
            double tasaVes = conversionRates.get("VES").getAsDouble();

            // Mostrar tasas de cambio específicas
            System.out.println("\nTasa de cambio de USD a EUR: " + tasaEuro);
            System.out.println("Tasa de cambio de USD a VES: " + tasaVes);
        } catch (Exception e) {
            System.out.println("Error al consumir la API: " + e.getMessage());
        }
    }
}
