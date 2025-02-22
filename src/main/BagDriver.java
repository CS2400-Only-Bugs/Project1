package main;

public class BagDriver {
    // demo the three methods in BagInterface

    public static void printBagContents(BagInterface<Integer> bag) {
        Object[] tempArray = bag.toArray();
        System.out.print("[");
        for (int i = 0; i < tempArray.length; i++) {
            if (i == 0) {
                System.out.print(tempArray[i]);
                continue;
            } 
            System.out.print(", " + tempArray[i]);
        }
        System.out.print("]\n");
    }

    public static void main(String[] args) {
        // Created two bags
        ResizableArrayBag<Integer> bag1 = new ResizableArrayBag<Integer>();
        LinkedBag<Integer> bag2 = new LinkedBag<Integer>();

        // Input the data for bag1
        bag1.add(1);
        bag1.add(1);
        bag1.add(2);
        bag1.add(3);
        bag1.add(4);

        // Input the data for bag2
        bag2.add(1);
        bag2.add(2);
        bag2.add(3);
        bag2.add(4);
        bag2.add(5);
        bag2.add(6);
        bag2.add(7);

        // Use of Union method
        BagInterface<Integer> unionBag = new ResizableArrayBag<Integer>();
        unionBag = bag1.union(bag2);
        System.out.println("This is if we can only use one bag.");
        printBagContents(unionBag);

        // Use of Intersection method
        BagInterface<Integer> intersectBag = new ResizableArrayBag<Integer>();
        intersectBag = bag1.intersection(bag2);
        System.out.println("This is if we only need the items that duplicate from bag1 to bag2.");
        printBagContents(intersectBag);

        // Use of Difference method
        BagInterface<Integer> differenceBag = new ResizableArrayBag<Integer>();
        differenceBag = bag1.difference(bag2);
        System.out.println("This is if we only need the items from bag1 that are not in bag2.");
        printBagContents(differenceBag);
        differenceBag = bag2.difference(bag1);
        System.out.println("This is if we only need the items from the bag2 that are not in bag1.");
        printBagContents(differenceBag);
    }
}
