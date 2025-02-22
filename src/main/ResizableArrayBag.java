package main;

public class ResizableArrayBag<T> implements BagInterface<T> {
    int numberOfEntries;
    int capacity;
    T[] bag;
    boolean integrityOK = false;
    int maxCapacity = 10000;

    /**
     * Constructor for ResizableArrayBag
     * @param desiredCapacity desired capacity of bag
     */
    @SuppressWarnings("unchecked")
    public ResizableArrayBag(int desiredCapacity) {
        if (desiredCapacity <= maxCapacity && desiredCapacity >= 0) {
            numberOfEntries = 0;
            capacity = desiredCapacity;
            T[] tempBag = (T[]) new Object[capacity];
            bag = tempBag;
            integrityOK = true;
        } else if (desiredCapacity < 0) {
            throw new IllegalStateException("Attempt to create a bag " +
                "whose capacity is negative");
        } else {
            throw new IllegalStateException("Attempt to create a bag " +
                    "whose capacity exceeds allowed maximum of " + maxCapacity);
        }
    }

    /**
     * default constructor if user doesn't specify capacity
     */
    @SuppressWarnings("unchecked")
    public ResizableArrayBag() {
        numberOfEntries = 0;
        capacity = 10;
        T[] tempBag = (T[]) new Object[capacity];
        bag = tempBag;
        integrityOK = true;
    }

    /**
     * Ensures integrity of bag. Throws Security Exception if integrity is not okay
     */
    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    }

    /**
     * @return the number of entries in the bag 
     */
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    /**
     * @return  true if the bag is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        if (numberOfEntries == 0) {
            return true;
        }
        return false;
    }

    /**
     * Adds a new entry to the bag
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful, false otherwise 
     */    
    @Override
    public boolean add(T newEntry) {
        checkIntegrity();
        if (numberOfEntries < capacity) {
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
            return true;
        } else {
            doubleCapacity();
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
            return true;
        }
    }

    /**
     * Doubles the capacity of the bag. If capacity is 0, sets capacity to 1
     */
    private void doubleCapacity() {
        int newCapacity;
        if (capacity == 0) {
            newCapacity = 1;
            bag = java.util.Arrays.copyOf(bag, newCapacity);
        } else {
            newCapacity = 2 * capacity;
            checkCapacity(newCapacity);
            bag = java.util.Arrays.copyOf(bag, newCapacity);
            capacity = newCapacity;
        }
    }

    /**
     * Checks if the new capacity of the bag will exceed the max capacity
     * Will throw an error if newCapacity exceeds the maxCapacity
     */
    private void checkCapacity(int newCapacity) {
        if (newCapacity > maxCapacity) {
            throw new IllegalStateException("Attempt to create bag exceeding maximum capacity of " + maxCapacity);
        }
    }

    /**
     * Removes one unspecified entry from this bag, if possible.
     * @return either the removed entry, if the removal was successful, or null. 
     */    
    @Override
    public T remove() {
        if (bag.length == 0) {
            return null;
        } else {
            T temp = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
            return temp;
        }
    }

    /** 
     * Removes one occurence of a given entry from this bag, if possible.
     * @param anEntry the entry to be removed
     * @return true if the removal was successful, false otherwise. 
     */
    @Override
    public boolean remove(T anEntry) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) {
                bag[i] = bag[numberOfEntries - 1];
                bag[numberOfEntries - 1] = null;
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all entries from this bag 
     */
    @Override
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }

    /** 
     * Counts the number of times a given entry appears in this bag
     * @param anEntry the entry to be counted
     * @return the number of times anEntry appears in the bag. 
     */
    @Override
    public int getFrequencyOf(T anEntry) {
        int counter = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Tests whether the bag contains a specific entry
     * @param anEntry the entry to find
     * @return true if the bag contains anEntry, false otherwise.
     */
    @Override
    public boolean contains(T anEntry) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) {
                return true;
            }
        }
        return false;
    }

    /** 
     * Retrives all entries that are in this bag.
     * @return a newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty.
     */
    @Override
    public T[] toArray() {
        T[] temp;
        temp = java.util.Arrays.copyOf(bag, numberOfEntries);
        return temp;
    }

    /**
     * Union method for LinkedBag
     * @param anotherBag The bag to be compared to the original bag
     * @return A new bag that contains all entries from 2 bags.
     */
    public BagInterface<T> union(BagInterface<T> anotherBag) {
        ResizableArrayBag<T> bag = new ResizableArrayBag<T>();

        T[] thisArray = toArray();
        for (T item : thisArray) {
            bag.add(item);
        }

        T[] anotherArray = anotherBag.toArray();
        for (T item : anotherArray) {
            bag.add(item);
        }
        return bag;
    }

    /**
     * Intersection method for LinkedBag
     * @param anotherBag The bag to be compared to the original bag
     * @return A new bag that contains all entries that are in both this bag and anotherBag. 
     */
    public BagInterface<T> intersection(BagInterface<T> anotherBag) {
        ResizableArrayBag<T> bag = new ResizableArrayBag<T>();
        ResizableArrayBag<T> tempBag = new ResizableArrayBag<T>();

        T[] anotherArray = anotherBag.toArray();
        for (T item : anotherArray) {
            tempBag.add(item);
        }

        T[] thisArray = toArray();
        for (T item : thisArray) {
            if (tempBag.contains(item)) {
                bag.add(item);
                tempBag.remove(item); // Ensure frequency count is maintained
            }
        }

        return bag;
    }

    /**
     * Difference method for LinkedBag
     * @param anotherBag The bag to be compared to the original bag
     * @return A new bag that contains all entries that are in this bag but not in anotherBag.
     */
    public BagInterface<T> difference(BagInterface<T> anotherBag) {
        ResizableArrayBag<T> bag = new ResizableArrayBag<T>();

        T[] thisArray = toArray();
        for (T item : thisArray) {
            bag.add(item);
        }

        T[] anotherArray = anotherBag.toArray();
        for (T item : anotherArray) {
            bag.remove(item);
        }

        return bag;
    }
}