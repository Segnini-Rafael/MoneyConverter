import java.util.Scanner;

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

        // Aquí llamaremos a la lógica de conversión.
    }
}
