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
    if(i < 0 || i > size())
      throw new IndexOutOfBoundsException("La posicion "+i+" esta fuera del rango valido. [0,"+size()+"]");

    // Crear un nuevo nodo
    Node newNode = new Node(e);

    // Cuando la lista es vacía
    if(head == null){
      head = newNode;
      tail = newNode;
      longitud ++;
      return;
    }

    // Se agrega en la primera posición
    if(i == 0) {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
      longitud ++;
      return;
    }

    // Agregar en la última posición
    if(i == size()){
      newNode.prev = tail;
      tail.next = newNode;
      tail = newNode;
      longitud ++;
      return;
    }

    // Para saber si insertamos desde head o insertamos desde tail
    if(i <= size()/2){ // Insertar desde head
      Node headIterator = head;
  
      for(int counter = 0; counter < i-1; counter++)
        headIterator = headIterator.next;
      
      newNode.next = headIterator.next;
      newNode.prev = headIterator;
      headIterator.next.prev = newNode;
      headIterator.next = newNode;
      longitud ++;

    } else { // Insertar desde tail

      Node tailIterator = tail;

      for(int counter = size(); counter > i + 1 ; counter--)
        tailIterator = tailIterator.prev;
      
      newNode.next = tailIterator;
      newNode.prev = tailIterator.prev;
      tailIterator.prev.next = newNode;
      tailIterator.prev = newNode;
      longitud ++;
    }


  }

  @Override
  public T remove(int i) throws IndexOutOfBoundsException {

    // Pasan algún indice inválido
    if(i < 0 || i > size()){
      throw new IndexOutOfBoundsException("La posicion "+i+" esta fuera del rango valido. [0,"+size()+"]");
    }


    // Cuando la lista es vacía
    if(head == null){
    }

    // Se elimina en la primera posición
    if(i == 0) {
      head = head.next;
      head.prev = null;
      longitud --;
      return null;
    }

    // Eliminamos en la última posición
    if(i == size()){
      tail = tail.prev;
      tail.next = null;
      longitud --;
      return null;
    }

    // Para saber si eliminamos desde head o insertamos desde tail
    if(i < size()/2){ // Insertar desde head
      Node headIterator = head;
  
      for(int counter = 0; counter < i; counter++){
        headIterator = headIterator.next;
      }
      
      headIterator.prev.next = headIterator.next;
      headIterator.next.prev = headIterator.prev;
      longitud --;
      return null;

    } else { // Insertar desde tail

      Node tailIterator = tail;

      for(int counter = size(); counter > i  ; counter--){
        tailIterator = tailIterator.prev;
      }
      
      tailIterator.prev.next = tailIterator.next;
      tailIterator.next.prev = tailIterator.prev;
      longitud --;
      return null;
    }
  }

  @Override
  public void clear(){
    tail = null;
    head = null;
    longitud = 0;
  }

  @Override
  public T get(int i) throws IndexOutOfBoundsException {

    // Pasan algún indice inválido
    if(i < 0 || i > size()){
      throw new IndexOutOfBoundsException("La posicion "+i+" esta fuera del rango valido. [0,"+size()+"]");
    }

    if(i < size()/2){ // Iterar desde head
      Node headIterator = head;
  
      for(int counter = 0; counter < i-1; counter++){
        headIterator = headIterator.next;
      }
      return headIterator.element;

    } else { // Iterar desde tail
      Node tailIterator = tail;

      for(int counter = size(); counter > i+1  ; counter--){
        tailIterator = tailIterator.prev;
      }
      return tailIterator.element;
    }
  }

  Node iteradorContains; //Nodo que se usará para el método contains.
  @Override
  public boolean contains(T e){

    if (isEmpty()) {
      return false;
    }

    iteradorContains = head;
    for(int counter = 0; counter <= size()/2; counter ++){
      if(e.equals(iteradorContains.element)){
        return true;
      }
      iteradorContains = iteradorContains.next;
    }
  
    iteradorContains = tail;
    for(int counter = size(); counter >= size()/2; counter --){
      if(e.equals(iteradorContains.element)){
        return true;
      }
      iteradorContains = iteradorContains.prev;
    }

    return false;
  }

  @Override
  public boolean isEmpty() {

    if(head == null)
      return true;
    
    if (tail == null)
      return true;

    if (longitud == 0)
      return true;
    
    return false;
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
    while(iterator != null){
      System.out.println(iterator.element);
      iterator = iterator.next;
    }
  }

  /**
   * NodoDesdeElCentro
   * @param B La posición que debemos buscar
   * @return regresa null en caso de que B no se encuentre del centro de la lista al comienzo, si no regresa el valor de B
   */
  public T nodoDesdeElCentro(int B){
    if (isEmpty()) {
      return null;
    }
    if(B <= 0 || B > size()){
      throw new IndexOutOfBoundsException("La posicion "+B+" esta fuera del rango valido [0,"+size()+"]");
    }
    if(B > size()/2){
      return null;
    }

    Node tailIterator = tail;
    for(int counter = size(); counter > size()/2  ; counter--){
      tailIterator = tailIterator.prev;
    }

    Node centroIterator = tailIterator;
    for(int counter = size()/2+1; counter > B+1; counter--){
      centroIterator = centroIterator.prev;
    } 
    return centroIterator.element;

  }


  // public class IteradorContenedor<T> implements Iterator<T> {

  //   /** Conjunto de elementos de tipo T */
  //   public T[] valores;

  //   /** Tope */
  //   public int tope;

  //   /** Apuntador */
  //   public int apuntador;


  //   public IteradorContenedor(T[] arr, int tope){
  //     valores = arr;
  //     this.tope = tope;
  //     this.apuntador = 0;
  //   }

  //   @Override
  //   public boolean hasNext(){
  //     if(apuntador >= tope)
  //       return false;
  //     return true;
  //   }

  //   @Override
  //   public T next(){
  //     return valores[apuntador++];
  //   }

  // }

  /** Iterador */

  // public class IteratorDoubleLinkedList<T> implements Iterator<T> {

  //   /** Conjunto de elementos de tipo T */
  //   public Node doubleIterator;

  //   /** Apuntador */
  //   public int apuntador;

  //   /**
  //    * Crea un nuevo iterador
  //    * @param arr el arreglo con los valores a recorrer
  //    * @param tope el tope de los elementos válidos por recorrer
  //    */
  //   public IteratorDoubleLinkedList(T e){
  //     this.apuntador = 0;

  //   }

  //   @Override
  //   public boolean hasNext(){
  //     if(apuntador >= size()){
  //       return false;
  //     }
  //     return true;
  //   }

  //   @Override
  //   public T next(){
  //     if(!hasNext()){
  //       throw new NoSuchElementException();
  //     }
  //     doubleIterator = doubleIterator.next;
  //     return;
  //   }

  // }

  // private class IteradorArreglo implements Iterador{
  //   private int index;

  // public IteradorArreglo(){
  //   index = 0;
  // }
    
  //   @Override
  //   /**
  //    * Metodo que indica si hay un elemento siguiente o no
  //    * @return true si hay siguiente y false en caso contrario
  //    */
  //   public boolean hasNext(){
  //   if(index < menuF.length){
  //     if(menuF[index] != null){
  //       return true;
  //     }
  //   }
  //   return false;
  //   }
    
  //   @Override
  //   /**
  //    * Metodo que regresa al elemento siguiente
  //    * @return el elemento siguiente
  //    */	    
  //   public Object next(){
  //   int devolver = index;
  //   index++;
  //   return menuF[devolver];
  //   }
    
//Fin clase privada IteradorArreglo 
  
}
