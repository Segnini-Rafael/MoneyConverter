import com.google.gson.Gson;

public class MoneyConverter {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Ejemplo: Convertir un objeto Java a JSON
        Conversor conversor = new Conversor("USD", "EUR", 1.2);
        String json = gson.toJson(conversor);
        System.out.println("JSON generado: " + json);

        // Ejemplo: Convertir JSON a un objeto Java
        String jsonString = "{\"from\":\"USD\",\"to\":\"EUR\",\"rate\":1.2}";
        Conversor conversor2 = gson.fromJson(jsonString, Conversor.class);
        System.out.println("Conversión: " + conversor2.from + " -> " + conversor2.to + " a tasa " + conversor2.rate);
    }

    static class Conversor {
        String from;
        String to;
        double rate;

        Conversor(String from, String to, double rate) {
            this.from = from;
            this.to = to;
            this.rate = rate;
        }
    }
}
