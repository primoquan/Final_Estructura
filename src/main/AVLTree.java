//Luis Miguel Quan
//Examen Final Estructura de Datos
//Ingeniero Brandon Chitay

package src.main;

import java.util.ArrayList;
import java.util.List;


//Aqui vamos a trabajar con implementar el arbol avl, con insercesiones y el balanceo automatico
//Root va a ser la raiz del arbol

public class AVLTree {
    private Node root;


    // Aqui vamos a insertar un valor en el arbol

    public void insert(int value) {
        root = insert(root, value);
    }

    //Con este metodo vamos a insertar los valores en el arbol
    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        //Aqui vamos a insertar la parte izquierda del arbol si el valos es menor
        if (value < node.value) {
            node.left = insert(node.left, value);
        }
        //Con este vamos a insertar del lado derecho si el valor es mayor
        else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }

        //Con este vamos a actualizar la altura del nodo
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return balance(node, value);
    }

    //Con este metodo vamos a balancear el factor del nodo para determinar si es 1, 0, -1
    //Tambien vamos a determinar las rotaciones
    private Node balance(Node node, int value) {
        int balance = getBalance(node);

        //Este es para rotacion simple a la derecha
        if (balance > 1 && value < node.left.value) {
            return rotateRight(node);
        }

        //Este es para rotacion simple a la izquierda
        if (balance < -1 && value > node.right.value) {
            return rotateLeft(node);
        }

        //Este es para rotacion doble izquierda derecha
        if (balance > 1 && value > node.left.value) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        //Este es para rotacion doble derecha izquierda
        if (balance < -1 && value < node.right.value) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        //si no necesita continuamos con el nodo
        return node;
    }

    //con este metodo vamos a obtener la altura
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    //Con este metodo obtenemos el factor de balance
    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    //este ya es nuestro metodo de para hacer la rotacion simple a la derecha
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    //este ya es nuestro metodo de para hacer la rotacion simple a la izquierda
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }


    // Esta es nuestro metodo para imprimir el arbol con las conexiones
    public void printTree() {
        if (root == null) {
            System.out.println("Árbol vacío");
            return;
        }

        List<List<String>> levels = new ArrayList<>();
        buildTree(root, 0, levels);

        for (int i = 0; i < levels.size(); i++) {
            if (i % 2 == 1) { // Nivel de conexiones
                printConnections(levels.get(i));
            } else { // Nivel de valores
                printValues(levels.get(i));
            }
        }
    }

    //Aqui vamos a construir como se ve el arbol por los niveles para poder verlo
    private void buildTree(Node node, int level, List<List<String>> levels) {
        if (levels.size() <= level) {
            levels.add(new ArrayList<>());
        }

        if (node == null) {
            levels.get(level).add("   ");
            if (levels.size() <= level + 1) {
                levels.add(new ArrayList<>());
            }
            levels.get(level + 1).add("   ");
            return;
        }

        levels.get(level).add(String.format("%2d ", node.value));

        if (levels.size() <= level + 1) {
            levels.add(new ArrayList<>());
        }

        if (node.left != null || node.right != null) {
            levels.get(level + 1).add(node.left != null ? " / " : "   ");
            levels.get(level + 1).add(node.right != null ? " \\ " : "   ");
        }

        buildTree(node.left, level + 2, levels);
        buildTree(node.right, level + 2, levels);
    }

    //Aqui imprimimos las conexiones entre los nodos con / y \
    private void printConnections(List<String> connections) {
        for (String conn : connections) {
            System.out.print(conn);
        }
        System.out.println();
    }

    //Aqui imprimimos los valores del nodo de un nivel
    private void printValues(List<String> values) {
        for (String val : values) {
            System.out.print(val);
        }
        System.out.println();
    }
}