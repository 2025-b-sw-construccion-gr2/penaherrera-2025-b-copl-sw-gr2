package calculadora;
Import Suma;
Import Resta;
import java.util.Scanner;

public class MainCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== CALCULADORA INTERACTIVA =====");
        System.out.print("Ingrese el primer número: ");
        int numero1 = sc.nextInt();

        System.out.print("Ingrese el segundo número: ");
        int numero2 = sc.nextInt();

        System.out.println("\nSeleccione una operación:");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Ambas");
        System.out.print("Opción: ");
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                System.out.printf("%d + %d = %d\n", numero1, numero2, Suma.sumar(numero1, numero2));
                break;
            case 2:
                System.out.printf("%d - %d = %d\n", numero1, numero2, Resta.restar(numero1, numero2));
                break;
            case 3:
                System.out.printf("%d + %d = %d\n", numero1, numero2, Suma.sumar(numero1, numero2));
                System.out.printf("%d - %d = %d\n", numero1, numero2, Resta.restar(numero1, numero2));
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
        }

        sc.close();
        System.out.println("===================================");
    }
}
