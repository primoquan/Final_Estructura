//Luis Miguel Quan
//Examen Final Estructura de Datos
//Ingeniero Brandon Chitay

package src.main;

//Con esta clase vamos a representar un nodo del Arbol, va a tener el valor y la altura del arbol que va a eser height

public class Node {
    public int value;
    public Node left;
    public Node right;
    public int height;

    public Node(int value) {
        this.value = value;
        this.height = 1;
    }
}