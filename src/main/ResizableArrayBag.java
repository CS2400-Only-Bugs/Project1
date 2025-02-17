package main;

public class ResizableArrayBag<T> implements BagInterface<T> {
    int numberOfEntries;
    int capacity;
    T[] bag;
    boolean integrityOK = false;
    int maxCapacity = 10000;
    

    @SuppressWarnings("unchecked")
    public ResizableArrayBag(int desiredCapacity) {
        if (desiredCapacity <= maxCapacity) {
            numberOfEntries = 0;
            capacity = desiredCapacity;
            T[] tempBag = (T[]) new Object[capacity];
            bag = tempBag;
            integrityOK = true;
        } else {
            throw new IllegalStateException("Attempt to create a bag " +
                "whose capacity exceeds allowed maximum of " + maxCapacity);
        }
    }

    // default constructor if user doesn't specify capacity
    @SuppressWarnings("unchecked")
    public ResizableArrayBag() {
        numberOfEntries = 0;
        capacity = 10;
        T[] tempBag = (T[]) new Object[capacity];
        bag = tempBag;
        integrityOK = true;
    }

    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        if (numberOfEntries == 0) {
            return true;
        }
        return false;
    }

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

    private void doubleCapacity() {
        int newCapacity = 2 * capacity;
        checkCapacity(newCapacity);
        bag = java.util.Arrays.copyOf(bag, newCapacity);
        capacity = newCapacity;
    }

    private void checkCapacity(int capacity) {
        if (numberOfEntries >= capacity) {
            throw new IllegalStateException("Attempt to create bag exceeding maximum capacity of " + maxCapacity);
        }
    }

    @Override
    public T remove() {
        T temp = bag[numberOfEntries - 1];
        bag[numberOfEntries - 1] = null;
        numberOfEntries--;
        return temp;
    }

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

    @Override
    public void clear() {
        bag = java.util.Arrays.copyOf(bag, 0);
    }

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

    @Override
    public boolean contains(T anEntry) {
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public T[] toArray() {
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    /** 
     * Union method for ResizableArrayBag
     * @return A new bag that contains all entries from 2 bags. 
     */
        public BagInterface<T> union(BagInterface<T> anotherBag) {
        ResizableArrayBag<T> bag = new ResizableArrayBag<>();

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
     * Intersection method for ResizableArrayBag
     * @return A new bag that contains all entries that are in both bags. 
     */
    public BagInterface<T> intersection(BagInterface<T> anotherBag) {
        ResizableArrayBag<T> bag = new ResizableArrayBag<>();
        ResizableArrayBag<T> tempBag = new ResizableArrayBag<>();

        T[] anotherArray = anotherBag.toArray();
        for (T item : anotherArray) {
            tempBag.add(item);
        }

        T[] thisArray = toArray();
        for (T item : thisArray) {
            if (tempBag.contains(item)) {
                bag.add(item);
                tempBag.remove(item);  // Ensure frequency count is maintained
            }
        }

        return bag;
    }

    /**
     * Difference method for ResizableArrayBag
     * @return A new bag that contains all entries that are in this bag but not in anotherBag. 
     */
    public BagInterface<T> difference(BagInterface<T> anotherBag) {
        ResizableArrayBag<T> bag = new ResizableArrayBag<>();

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