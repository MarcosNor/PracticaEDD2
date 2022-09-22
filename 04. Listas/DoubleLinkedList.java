import java.util.Iterator;

/**
 * Implementación de una lista doblemente ligada
 * 
 * @author Emmanuel Cruz Hernández
 * @version 1.0 2022
 * @since Estructuras de datos 2023-1.
 */
public class DoubleLinkedList<T> implements TDAList<T> {

  /**
   * Representación de un Nodo
   */
  private class Node {

    /** Elemento almacenado en el nodo */
    public T element;

    /** Referencia a un nodo siguiente */
    public Node next;

    /** Referencia a un nodo anterior */
    public Node prev;

    /**
     * Crea un nuevo nodo
     * 
     * @param e el elemento a almacenar en el nodo
     */
    public Node(T e) {
      this.element = e;
    }

  }

  /** Nodo cabeza */
  public Node head;

  /** Nodo cola */
  public Node tail;

  /** Longitud de la lista para el método size */
  private int longitud;

  @Override
  public void add(int i, T e) throws IndexOutOfBoundsException {
    // Pasan algún indice inválido
    if (i < 0 || i > size())
      throw new IndexOutOfBoundsException("La posicion " + i + " esta fuera del rango valido. [0," + size() + "]");

    // Crear un nuevo nodo
    Node newNode = new Node(e);

    // Cuando la lista es vacía
    if (head == null) {
      head = newNode;
      tail = newNode;
      longitud++;
      return;
    }

    // Se agrega en la primera posición
    if (i == 0) {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
      longitud++;
      return;
    }

    // Agregar en la última posición
    if (i == size()) {
      newNode.prev = tail;
      tail.next = newNode;
      tail = newNode;
      longitud++;
      return;
    }

    // Para saber si insertamos desde head o insertamos desde tail
    if (i <= size() / 2) { // Insertar desde head
      Node headIterator = head;

      for (int counter = 0; counter < i - 1; counter++)
        headIterator = headIterator.next;

      newNode.next = headIterator.next;
      newNode.prev = headIterator;
      headIterator.next.prev = newNode;
      headIterator.next = newNode;
      longitud++;

    } else { // Insertar desde tail

      Node tailIterator = tail;

      for (int counter = size(); counter > i + 1; counter--)
        tailIterator = tailIterator.prev;

      newNode.next = tailIterator;
      newNode.prev = tailIterator.prev;
      tailIterator.prev.next = newNode;
      tailIterator.prev = newNode;
      longitud++;
    }

  }

  @Override
  public T remove(int i) throws IndexOutOfBoundsException {

    // Pasan algún indice inválido
    if (i < 0 || i > size()) {
      throw new IndexOutOfBoundsException("La posicion " + i + " esta fuera del rango valido. [0," + size() + "]");
    }

    // Cuando la lista es vacía
    if (head == null) {
    }

    // Se elimina en la primera posición
    if (i == 0) {
      head = head.next;
      head.prev = null;
      longitud--;
      return null;
    }

    // Eliminamos en la última posición
    if (i == size()) {
      tail = tail.prev;
      tail.next = null;
      longitud--;
      return null;
    }

    // Para saber si eliminamos desde head o insertamos desde tail
    if (i < size() / 2) { // Insertar desde head
      Node headIterator = head;

      for (int counter = 0; counter < i; counter++) {
        headIterator = headIterator.next;
      }

      headIterator.prev.next = headIterator.next;
      headIterator.next.prev = headIterator.prev;
      longitud--;
      return null;

    } else { // Insertar desde tail

      Node tailIterator = tail;

      for (int counter = size(); counter > i; counter--) {
        tailIterator = tailIterator.prev;
      }

      tailIterator.prev.next = tailIterator.next;
      tailIterator.next.prev = tailIterator.prev;
      longitud--;
      return null;
    }
  }

  @Override
  public void clear() {
    tail = null;
    head = null;
    longitud = 0;
  }

  @Override
  public T get(int i) throws IndexOutOfBoundsException {

    // Pasan algún indice inválido
    if (i < 0 || i > size()) {
      throw new IndexOutOfBoundsException("La posicion " + i + " esta fuera del rango valido. [0," + size() + "]");
    }

    if (i < size() / 2) { // Insertar desde head
      Node headIterator = head;

      for (int counter = 0; counter < i - 1; counter++) {
        headIterator = headIterator.next;
      }
      return headIterator.element;

    } else { // Iterar desde tail
      Node tailIterator = tail;

      for (int counter = size(); counter > i + 1; counter--) {
        tailIterator = tailIterator.prev;
      }
      return tailIterator.element;
    }
  }

  Node iteradorContains; // Nodo que se usará para el método contains.

  @Override
  public boolean contains(T e) {

    if (isEmpty()) {
      return false;
    }

    iteradorContains = head;
    for (int counter = 0; counter <= size() / 2; counter++) {
      if (e.equals(iteradorContains.element)) {
        return true;
      }
      iteradorContains = iteradorContains.next;
    }

    iteradorContains = tail;
    for (int counter = size(); counter >= size() / 2; counter--) {
      if (e.equals(iteradorContains.element)) {
        return true;
      }
      iteradorContains = iteradorContains.prev;
    }

    return false;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public int size() {
    return longitud;
  }

  /**
   * Mostar los elementos de una lista
   */
  public void show() {
    Node iterator = head;
    while (iterator != null) {
      System.out.println(iterator.element);
      iterator = iterator.next;
    }
  }

  /**
   * Invierte el orden de una lista
   */
  public void reverse() {
    Node n = head;
    boolean opc = true;
    while (n != null) {
      if (n.next == null)
        break;
      add(0, n.next.element);
      n = n.next;
      if (opc) {
        Node t = n.prev;
        t.next = null;
        opc = false;
      }
    }
  }

  /**
   * Mezcla dos listas alternadamente
   */
  public void merge(DoubleLinkedList<T> list2) {
    Node n1 = head;
    Node n2 = list2.head;
    if (list2.isEmpty()) {
      return;
    }
    while (n1 != null && n2 != null) {
      Node aux1 = n1.next;
      n1.next = n2;
      n2.prev = n1;
      Node aux2 = n2.next;
      n2.next = aux1;
      if (aux1 == null) {
        tail = n2;
      } else if (aux2 == null) {
        tail = n1.next;

      }
      n1 = aux1;
      n2 = aux2;
    }
    Node aux = n1 == null ? n2 : n1;

    while (aux != null) {
      aux.prev = tail;
      tail.next = aux;
      tail = aux;
      aux = aux.next;

    }
  }
}
