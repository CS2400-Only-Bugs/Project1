package main;

/**
 * @author Jayden Briones
 * @version 1.0
 * Changes made by anyone else below:
 */
public class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    /**Creates an empty bag */
    public LinkedBag(){
        firstNode = null;
        numberOfEntries = 0;
    }//end LinkedBag constructor

    /**@return the number of entries in the bag */
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }//end getCurrentSize

    /**@return true if the bag is empty, false otherwise */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }//end isEmpty

    /**Adds a new entry to the bag
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful, false otherwise */
    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry, firstNode);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }//end add

    /**Removes one unspecified entry from this bag, if possible.
     * @return either the removed entry, if the removal was successful, or null. */
    @Override
    public T remove() {
        T result = null;
        if(firstNode != null){
            result = firstNode.getData();
            firstNode = firstNode.getNext();
            numberOfEntries--;
        }
        return result;
    }//end remove

    /** Removes one occurence of a given entry from this bag, if possible.
     * @param anEntry the entry to be removed
     * @return true if the removal was successful, false otherwise. */
    @Override
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if(nodeN != null){
            nodeN.setData(firstNode.getData());
            firstNode = firstNode.getNext();
            numberOfEntries--;
            result = true;
        }
        return result;
    }//end remove

    /**Clears the bag */
    @Override
    public void clear() {
        while(!isEmpty()){
            remove();
        }
    }//end clear

    /** Counts the number of times a given entry appears in this bag
     * @param anEntry the entry to be counted
     * @return the number of times anEntry appears in the bag. */
    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        int counter = 0;
        Node currentNode = firstNode;
        while((counter < numberOfEntries) && (currentNode != null)){
            if(anEntry.equals(currentNode.getData())){
                frequency++;
            }
            counter++;
            currentNode = currentNode.getNext();
        }
        return frequency;
    }//end getFrequencyOf

    /**Tests whether the bag contains a specific entry
     * @param anEntry the entry to find
     * @return true if the bag contains anEntry, false otherwise.*/
    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while(!found && (currentNode != null)){
            if(anEntry.equals(currentNode.getData())){
                found = true;
            }else{
                currentNode = currentNode.getNext();
            }
        }
        return found;
    }//end contains

    /** Retrives all entries that are in this bag.
     * @return a newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty.*/
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while((index < numberOfEntries) && (currentNode != null)){
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNext();
        }
        return result;
    }//end toArray

    /**Union method for LinkedBag
     * @return A new bag that contains all entries from 2 bags.*/
    @Override
    public BagInterface<T> union(BagInterface<T> anotherBag) {
        LinkedBag<T> bag = new LinkedBag<>();
        for (T item : this.toArray()) {
            bag.add(item);
        }
        for(T item : anotherBag.toArray()){
            bag.add(item);
        }
        return bag;
    }//end union

    /**Intersection method for LinkedBag
     * @return A new bag that contains all entries that are in both this bag and anotherBag. */
    @Override
    public BagInterface<T> intersection(BagInterface<T> anotherBag) {
        LinkedBag<T> bag = new LinkedBag<>();
        LinkedBag<T> tempBag = new LinkedBag<>();
        for(T item : anotherBag.toArray()){
            tempBag.add(item);
        }
        for(T item : this.toArray()){
            if(tempBag.contains(item)){
                bag.add(item);
                tempBag.remove(item);
            }
        }
        return bag;
    }// end intersection

    /**Difference method for LinkedBag
     * @return A new bag that contains all entries that are in this bag but not in anotherBag.*/
    @Override
    public BagInterface<T> difference(BagInterface<T> anotherBag) {
        LinkedBag<T> bag = new LinkedBag<>();
        for (T item : this.toArray()) {
            bag.add(item);
        }
        for (T item : anotherBag.toArray()) {
            bag.remove(item);
        }
        return bag;
    } //end difference

    /**Constructors for Node
     * @return the data and next node
     * @param data the data to be stored
     * @param next the next node */
    private class Node {
        private T data;
        private Node next;

        private Node(T data){
            this(data, null);
        }

        private Node(T data, Node next){
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }
    } //end Node
    
    /**
     * Locates a given entry within this bag.
     * @param anEntry the entry to be found
     * @return a reference to the node containing the entry, or null if the entry is not in the bag.
     */
    private Node getReferenceTo(T anEntry){
        boolean found = false;
        Node currentNode = firstNode;
        while(!found && (currentNode != null)){
            if(anEntry.equals(currentNode.getData())){
                found = true;
            }else{
                currentNode = currentNode.getNext();
            }
        }
        return currentNode;
    }   //end getReferenceTo
} // end LinkedBag