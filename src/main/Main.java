//Luis Miguel Quan
//Examen Final Estructura de Datos
//Ingeniero Brandon Chitay

package src.main;

import java.util.Scanner;


//Esta es nuestra clase principal, donde vamos a tener la interfaz donde se ingresan los numeros, aqui llamamos a nuestra clase AVLTree
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree tree = new AVLTree();

        System.out.println("Bienvenido a Árbol AVL");
        System.out.println("Ingrese números enteros uno por uno, o 'exit'/-1 para salir del programa");


        //Este es nuestro bucle que estara pidiendo el numero si detecta exit o -1 el programa se terminara
        while (true) {
            System.out.print("Ingrese un número: ");
            String input = scanner.nextLine().trim();

            // Esta es nuestra parte donde vamos a verificar si salimos o seguimos con el programa
            if (input.equalsIgnoreCase("exit") || input.equals("-1")) {
                break;
            }

            try {
                int value = Integer.parseInt(input);
                tree.insert(value);
                System.out.println("Árbol:");
                tree.printTree();
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida. Por favor ingrese un número entero o ya sea 'exit' o '-1' para salir");
            }
        }

        //Mensaje de despedida
        System.out.println("Programa finalizado.");
        scanner.close();
    }
}