package datastructures;

public class MyArrayList {
    private int[] data;
    private int size;

    public MyArrayList() {
        data = new int[2];
        size = 0;
    }

    public void add(int value) {
        if (size == data.length) {
            int[] bigger = new int[data.length * 2];
            for (int i = 0; i < size; i++) {
                bigger[i] = data[i];
            }
            data = bigger;
        }
        data[size] = value;
        size++;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return data[index];
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
    MyArrayList list = new MyArrayList();
    for (int i = 1; i <= 5; i++) list.add(i * 10);
    System.out.println(list.get(4));  
    System.out.println(list.size()); 
}
}
