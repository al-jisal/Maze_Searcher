    /* Name: Desmond Frimpong
     * Date: February 27, 2023
     * File: LinkedList.java
     * 
     * this class implement the LinkedList data structure, implementing 
     * stacksand iterable interface
     */

import java.util.ArrayList;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>, Queue<T>, Stack<T>{
    
    /**************** inner class for Node *************************/
    private static class Node<T>{
        // the fields for the Node class
        T data;
		Node<T> next;
		Node<T> prev;

        // constructor for the Node class if next and prev are not known
        public Node(T data) {
			this(data, null, null);
		}

        // constructor for the Node class if next and prev are known
        public Node(T data, Node<T> next, Node<T> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}


        public T getData(){
            return data;
        }


        public void setNext( Node<T> n){
            next = n;
        }


        public Node<T> getNext(){
            return next;
        }

        public Node<T> getPrev(){
			return prev;
		}

		public void setPrev(Node<T> prev){
			this.prev = prev;
		}

    }
    /***************************************************************/

    /********* inner class for Iterable and other methods   ********/
    private class LLIterator implements Iterator<T>{
        Node<T> next;

        public LLIterator(Node<T> head){
            next = head;
        }

        public boolean hasNext(){
            if (next == null){
                return false;
            } else {
                return true;
            }
        }

        public T next() {
            T toReturn = next.getData();
            next = next.getNext();
            return toReturn;
        }

        public void remove(){

        }
    }


    public Iterator<T> iterator(){
        return new LLIterator(this.head);
    }


    public ArrayList<T> toArrayList(){
        ArrayList<T> toArrayList = new ArrayList<T>();
        toArrayList.add(this.head.getData());
        for(int i = 0; i < size() - 1; i++){
            this.head = head.getNext();
            toArrayList.add(this.head.getData());
        }
        return toArrayList;
    }
    
    /****************************************************************/

    /*************** methods for the Queue interface ******************/

    // returns the next item in line to be polled
    public T peek(){
        return get(0);
    }

    // removes the first item in the line
    public T poll(){
        return removeFirst();
    }

    // adds an item to the end of the list
    public void offer(T item){
        addLast(item);
    }

    /*******************************************************************/

    /****************** methods for the Stack interface ****************/

    // returns the first item in the stack
    public T pop(){
        return removeFirst();
    }

    // adds an item to the start of the stack
    public void push(T item){
        addFirst(item);
    }

    /*******************************************************************/

    // fislds of the LinkedList class
    int size;
    Node<T> head;
    Node<T> tail;

    // this constructs the LinkedList class
    public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

    // returns the size of the list
    public int size(){
        return size;
    }

    // makes and adds a Node to the LinkedList
    public void addFirst(T data) {
		if(size() == 0){
			head = new Node<T>(data);
			tail = head;
			size++;
			return;
		}
		Node<T> newNode = new Node<T>(data, head, null);
		head.prev = newNode;
		head = newNode;
		size++;
	}

    // adds a node to the end of the linkedlist
    public void addLast(T data) {
		if(size() == 0){
			tail = new Node<T>(data);
			head = tail;
			size++;
			return;
		}
		Node<T> newNode = new Node<T>(data, null, tail);

		tail.next = newNode;
		tail = newNode;
		size++;
	}
    
    // inserts a node at a specified index
    public void add(int index, T item){
        if(index < 0 || index > size){
            System.out.println("index out of bounds");
            return;
        }
        if(index == 0){
            addFirst(item);
            return;
        }
        Node<T> newNode = new Node<T>(item);
        Node<T> walker = head;
        Node<T> holder;

        for(int i = 0; i < index-1; i++){
            walker = walker.getNext();
        }
        holder = walker.getNext();
        walker.setNext(newNode);
        newNode.setNext(holder);
        size++;
    }

    // clears the LinkedList
    public void clear(){
        size = 0;
        head = null;
        tail = null;
    }

    // checks if an item is in the list
    public boolean contains(Object o){
        Node<T> walker = head;
        for(int i = 0; i < size; i++){
            if(walker.getData().equals(o)){
                return true;
            }
            else{
                walker = walker.getNext();
            }
        }
        return false;
    }

    // checks if an objecct is a LinkedList and is equal to my LinkedList
    public boolean equals(Object o){
        if(!( o instanceof LinkedList)){
            return false;
        }
        LinkedList<T> oAsALinkedList = (LinkedList<T>) o;
        
        if(oAsALinkedList.size() != size()){
            return false;
        }

        Node<T> walker = head;
        Node<T> otherWalker = oAsALinkedList.head;
        for(int i = 0; i < size; i++){
            // check if each Node's data in order are equal
            // if not, return false
            if(!(walker.getData().equals(otherWalker.getData()))){
                return false;
            }
            walker = walker.getNext();
            otherWalker = otherWalker.getNext();  
        }
        return true;
    }


    // this returns the data of the node at the specified index
    public T get(int index){
        if(index < 0 || index > size()){
            System.out.println("index out of bound");
            return null;
        }
       
        Node<T> walker = head;
        for(int i = 0; i < index; i++){
            walker = walker.getNext();
        }
        return walker.getData();
    }


    //checks if the list is empty
    public boolean isEmpty(){
        return size() == 0;
    }


    //removes the first item in the list
    public T removeFirst(){
		if(size() == 0){
			System.out.println("this LinkedList has no elements");
			return null;
		}
		if(size() == 1){
			Node<T> holder = head;
			head = null;
			tail = null;
			size--;
			return holder.getData();
		}

		Node<T> holder = head;

		head = head.next;
		head.prev = null;
		size--;
		return holder.getData();
	}


    //removes the item at the specified index from the list
    public T remove(int index){
        // does this work if index is 0?
        if(index >= size()){
            System.out.println("index out of bound");
            return null;
        }
        Node<T> toRemove;
        Node<T> walker = head;
        Node<T> holder;

        if(index == 0){
            toRemove = head;
            head = head.next;
            return toRemove.getData();
        }
        for(int i = 0; i < index - 1; i++){
            walker = walker.getNext();
        }
        toRemove = walker.getNext();
        holder = toRemove.getNext();
        walker.setNext(holder);
        return toRemove.getData();
    }


    // removes the last node in the linkedlist
    public T removeLast(){
		if(size() == 0){
			System.out.println("this LinkedList has no elements");
			return null;
		}
		if(size() == 1){
			Node<T> holder = head;
			head = null;
			tail = null;
			size--;
			return holder.getData();
		}

		Node<T> holder = tail;

		tail = tail.prev;
		tail.next = null;
		size--;
		return holder.getData();
	}


    //string representation of the list
    public String toString(){
        String str = "[ ";
        Node<T> walker = head;
        if(size()==0){
            return "[ ]";
        }
        str += walker.getData();
        for(int i = 0; i < size() - 1; i++){
            walker = walker.getNext();
            str += ", " + walker.getData();
        }
        str += " ]";
        return str;
    }
}
