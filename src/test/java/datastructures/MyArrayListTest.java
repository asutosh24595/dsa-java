package datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {
    @Test
    void addAndGetAcrossResize() {
        MyArrayList list = new MyArrayList();
        for (int i = 1; i <= 5; i++) {
            list.add(i * 10); // starts at capacity 2, so this forces two resizes
        }
        assertEquals(5, list.size());
        assertEquals(10, list.get(0));
        assertEquals(50, list.get(4));
    }

    @Test
    void getWithInvalidIndexThrows() {
        MyArrayList list = new MyArrayList();
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }
}
