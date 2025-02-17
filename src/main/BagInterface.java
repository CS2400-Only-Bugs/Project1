package main;

/** An interface that describes the operations of a bag of objects. */
public interface BagInterface<T>
{
	/** Gets the current number of entries in this bag.
		@return  The integer number of entries currently in the bag. */
	public int getCurrentSize();

	/** Sees whether this bag is empty.
		@return  True if the bag is empty, or false if not. */
	public boolean isEmpty();

	/** Adds a new entry to this bag.
	    @param newEntry  The object to be added as a new entry.
	    @return  True if the addition is successful, or false if not. */
	public boolean add(T newEntry);

	/** Removes one unspecified entry from this bag, if possible.
        @return  Either the removed entry, if the removal was successful, or null. */
	public T remove();

	/** Removes one occurrence of a given entry from this bag, if possible.
        @param anEntry  The entry to be removed.
        @return  True if the removal was successful, or false if not. */
    public boolean remove(T anEntry);

	/** Removes all entries from this bag. */
	public void clear();

	/** Counts the number of times a given entry appears in this bag.
		@param anEntry  The entry to be counted.
		@return  The number of times anEntry appears in the bag. */
	public int getFrequencyOf(T anEntry);

	/** Tests whether this bag contains a given entry.
		@param anEntry  The entry to find.
		@return  True if the bag contains anEntry, or false if not. */
	public boolean contains(T anEntry);

	/** Retrieves all entries that are in this bag.
		@return  A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
	public T[] toArray();

	/** Combines two collections into a new collection 
		@param anotherBag  The bag that is to be added.
		@return  A new bag that contains all entries from this bag and anotherBag. */
    public BagInterface<T> union(BagInterface<T> anotherBag);

	/**  Creates a new collection from the overlapping entries of two collections 
         Note: If x is found 5 times in bag 1 and 3 times in bag 2, the new bag will contain x 3 times.
		@param anotherBag  The bag that is to be compared.
		@return  A new bag that contains all entries that are in both this bag and anotherBag. */
	public BagInterface<T> intersection(BagInterface<T> anotherBag);
	
	/**  Creates a new collection from the entries that would be left in this bag after removing the entries that are also in anotherBag.
		 Note: If x is found 5 times in bag 1 and 3 times in bag 2, the new bag will contain x 2 times.
		@param anotherBag  The bag that is to be compared.
		@return  A new bag that contains all entries that are in this bag but not in anotherBag. */
	public BagInterface<T> difference(BagInterface<T> anotherBag);
} // end BagInterface