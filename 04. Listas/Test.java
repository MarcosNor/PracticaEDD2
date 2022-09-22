import java.util.ArrayList;
/**
 * Clase con las pruebas de la implementación de una lista doblemente ligada
 */
public class Test{

  private static DoubleLinkedList<Integer> listDouble;
  private static SimpleLinkedList<Integer> listaSimple;
  private static ArrayList<Integer> listaArray;
  
  private static long getTimeAddElementsDoubleList(int n, int position){
    long inicio1 = System.currentTimeMillis(); // Determinando el incio de tiempo
    for(int i = 1; i <= n; i++ ){
        listDouble.add(position, i);
    }
    long fin1 = System.currentTimeMillis(); // Determinando el fin de tiempo
    return fin1-inicio1;
  }

  private static long getTimeAddElementsSimpleList(int n, int position){
    long inicio1 = System.currentTimeMillis(); // Determinando el incio de tiempo
    for(int i = 1; i <= n; i++ ){
        listaSimple.add(position, i);
    }
    long fin1 = System.currentTimeMillis(); // Determinando el fin de tiempo
    return fin1-inicio1;
  }

  private static long getTimeAddElementsArrayList(int n, int position){
    long inicio1 = System.currentTimeMillis(); // Determinando el incio de tiempo
    for(int i = 1; i <= n; i++ ){
        listaArray.add(position, i);
    }
    long fin1 = System.currentTimeMillis(); // Determinando el fin de tiempo
    return fin1-inicio1;
  }

  public static void main(String[] args) {
    System.out.println("prueba Lista de doblemente ligada \n");
    listDouble = new DoubleLinkedList<>();
    System.out.println("incersion al final de 1000 elementos: " + getTimeAddElementsDoubleList(1000, 0));
    listDouble.clear();
    System.out.println("incersion al final de 100000 elementos: " + getTimeAddElementsDoubleList(100000, 0));
    listDouble.clear();
    System.out.println("incersion al inicio de 1000 elementos: " + getTimeAddElementsDoubleList(1000, 0));
    listDouble.clear();
    System.out.println("incersion al inicio de 100000 elementos: " + getTimeAddElementsDoubleList(100000, 0));
    System.out.println("incersion posicion aleatoria de 1000 elementos: " + getTimeAddElementsDoubleList(1000, 200));
    System.out.println("incersion posicion aleatoria de 100000 elementos: " + getTimeAddElementsDoubleList(100000, 777));
    listDouble.clear();
    getTimeAddElementsDoubleList(1000,0);
    long inicio1 = System.currentTimeMillis(); // Determinando el incio de tiempo
    listDouble.reverse();
    long fin1 = System.currentTimeMillis(); // Determinando el fin de tiempo
    System.out.println("Tiempo que tardo en invertir la lista con 1000 elementos: " + (fin1-inicio1));
    listDouble.clear();
    getTimeAddElementsDoubleList(100000,0);
    inicio1 = System.currentTimeMillis(); // Determinando el incio de tiempo
    listDouble.reverse();
    fin1 = System.currentTimeMillis(); // Determinando el fin de tiempo
    System.out.println("Tiempo que tardo en invertir la lista con 100000 elementos: " + (fin1-inicio1));

    //simple
    System.out.println("Pruebas SImpleLinkedList \n");
    listaSimple = new SimpleLinkedList<>();
    System.out.println("incersion al final de 1000 elementos: " + getTimeAddElementsSimpleList(1000, 0));
    listaSimple.clear();
    System.out.println("incersion al final de 100000 elementos: " + getTimeAddElementsSimpleList(100000, 0));
    listaSimple.clear();
    System.out.println("incersion al inicio de 1000 elementos: " + getTimeAddElementsSimpleList(1000, 0));
    listaSimple.clear();
    System.out.println("incersion al inicio de 100000 elementos: " + getTimeAddElementsSimpleList(100000, 0));
    System.out.println("incersion posicion aleatoria de 1000 elementos: " + getTimeAddElementsSimpleList(1000, 200));
    System.out.println("incersion posicion aleatoria de 100000 elementos: " + getTimeAddElementsSimpleList(100000, 777));
    listaSimple.clear();
    //simpleLinkedList no tiene el metodo reverse

    //ArrayList
    System.out.println("Pruebas ArrayList \n");
    listaArray = new ArrayList<>();
    System.out.println("incersion al final de 1000 elementos: " + getTimeAddElementsArrayList(1000, 0));
    listaArray.clear();
    System.out.println("incersion al final de 100000 elementos: " + getTimeAddElementsArrayList(100000, 0));
    listaArray.clear();
    System.out.println("incersion al inicio de 1000 elementos: " + getTimeAddElementsArrayList(1000, 0));
    listaArray.clear();
    System.out.println("incersion al inicio de 100000 elementos: " + getTimeAddElementsArrayList(100000, 0));
    System.out.println("incersion posicion aleatoria de 1000 elementos: " + getTimeAddElementsArrayList(1000, 200));
    System.out.println("incersion posicion aleatoria de 100000 elementos: " + getTimeAddElementsArrayList(100000, 777));
    listaArray.clear();
  }
}