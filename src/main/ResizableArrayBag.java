package main;
public class ResizableArrayBag<T> implements BagInterface<T> {
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

@Override
public int getCurrentSize() {
    throw new UnsupportedOperationException("Unimplemented method 'getCurrentSize'");
}

@Override
public boolean isEmpty() {
    throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
}

@Override
public boolean add(T newEntry) {
    throw new UnsupportedOperationException("Unimplemented method 'add'");
}

@Override
public T remove() {
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
}

@Override
public boolean remove(T anEntry) {
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
}

@Override
public void clear() {
    throw new UnsupportedOperationException("Unimplemented method 'clear'");
}

@Override
public int getFrequencyOf(T anEntry) {
    throw new UnsupportedOperationException("Unimplemented method 'getFrequencyOf'");
}

@Override
public boolean contains(T anEntry) {
    throw new UnsupportedOperationException("Unimplemented method 'contains'");
}

@Override
public T[] toArray() {
    throw new UnsupportedOperationException("Unimplemented method 'toArray'");
}
}