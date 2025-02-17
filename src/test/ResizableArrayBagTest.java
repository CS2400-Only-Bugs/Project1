package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.ResizableArrayBag;

public class ResizableArrayBagTest {
    ResizableArrayBag<Integer> bag1 = new ResizableArrayBag<Integer>();
    ResizableArrayBag<Integer> bag2 = new ResizableArrayBag<Integer>();
    ResizableArrayBag<Integer> emptyBag = new ResizableArrayBag<Integer>();
    ResizableArrayBag<Integer> nullBag = null;

    @BeforeEach
    void setUp() {
        bag1.add(1);
        bag1.add(1);
        bag1.add(2);
        bag1.add(3);
        bag1.add(4);

        bag2.add(1);
        bag2.add(2);
        bag2.add(3);
        bag2.add(4);
        bag2.add(5);
        bag2.add(6);
        bag2.add(7);
    }

    @Test
    @DisplayName("Bag Union Bag")
    void testUnion1() {
        ResizableArrayBag<Integer> temp = new ResizableArrayBag<Integer>();
        temp.add(1);
        temp.add(1);
        temp.add(2);
        temp.add(3);
        temp.add(4);
        temp.add(1);
        temp.add(2);
        temp.add(3);
        temp.add(4);
        temp.add(5);
        temp.add(6);
        temp.add(7);
        assertEquals(temp, bag1.union(bag2), "Standard Union Test");
    }

    @Test
    @DisplayName("Bag Union Empty Bag")
    void testUnion2() {
        assertEquals(bag1, bag1.union(emptyBag), "Bag Union Empty Bag");
    }

    @Test
    @DisplayName("Bag Union Null Bag")
    void testUnion3() {
        assertEquals(bag1, bag1.union(nullBag), "Bag Union Null Bag");
    }

    @Test
    @DisplayName("Empty Bag Union Bag")
    void testUnion4() {
        assertEquals(bag2, emptyBag.union(bag2), "Empty Bag Union Bag");
    }

    @Test
    @DisplayName("Null Bag Union Bag")
    void testUnion5() {
        assertEquals(bag2, nullBag.union(bag2), "Null Bag Union Bag");
    }

    @Test
    @DisplayName("Empty Bag Union Null Bag")
    void testUnion6() {
        assertEquals(emptyBag, emptyBag.union(nullBag), "Empty Bag Union Null Bag");
    }

    @Test
    @DisplayName("Bag Intersection Bag")
    void testIntersection1() {
        ResizableArrayBag<Integer> temp = new ResizableArrayBag<Integer>();
        temp.add(1);
        temp.add(2);
        temp.add(3);
        temp.add(4);
        assertEquals(temp, bag1.intersection(bag2), "Standard Intersection Test");
    }

    @Test
    @DisplayName("Bag Intersection Empty Bag")
    void testIntersection2() {
        assertEquals(emptyBag, bag1.intersection(emptyBag), "Bag Intersection Empty Bag");
    }

    @Test
    @DisplayName("Bag Intersection Null Bag")
    void testIntersection3() {
        assertEquals(emptyBag, bag1.intersection(nullBag), "Bag Intersection Null Bag");
    }

    @Test
    @DisplayName("Empty Bag Intersection Bag")
    void testIntersection4() {
        assertEquals(emptyBag, emptyBag.intersection(bag2), "Empty Bag Intersection Bag");
    }

    @Test
    @DisplayName("Null Bag Intersection Bag")
    void testIntersection5() {
        assertEquals(emptyBag, nullBag.intersection(bag2), "Null Bag Intersection Bag");
    }

    @Test
    @DisplayName("Empty Bag Intersection Null Bag")
    void testIntersection6() {
        assertEquals(emptyBag, emptyBag.intersection(nullBag), "Empty Bag Intersection Null Bag");
    }

    @Test
    @DisplayName("Bag Difference Bag")
    void testDifference1() {
        ResizableArrayBag<Integer> temp = new ResizableArrayBag<Integer>();
        temp.add(1);
        temp.add(5);
        temp.add(6);
        temp.add(7);
        assertEquals(temp, bag2.difference(bag1), "Standard Difference Test");
    }

    @Test
    @DisplayName("Bag Difference Empty Bag")
    void testDifference2() {
        assertEquals(bag1, bag1.difference(emptyBag), "Bag Difference Empty Bag");
    }

    @Test
    @DisplayName("Bag Difference Null Bag")
    void testDifference3() {
        assertEquals(bag1, bag1.difference(nullBag), "Bag Difference Null Bag");
    }

    @Test
    @DisplayName("Empty Bag Difference Bag")
    void testDifference4() {
        assertEquals(emptyBag, emptyBag.difference(bag2), "Empty Bag Difference Bag");
    }

    @Test
    @DisplayName("Null Bag Difference Bag")
    void testDifference5() {
        assertEquals(emptyBag, nullBag.difference(bag2), "Null Bag Difference Bag");
    }

    @Test
    @DisplayName("Empty Bag Difference Null Bag")
    void testDifference6() {
        assertEquals(emptyBag, emptyBag.difference(nullBag), "Empty Bag Difference Null Bag");
    }
}
