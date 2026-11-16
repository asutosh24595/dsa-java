package datastructures;

public class MyHashMap {
    private static class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] buckets = new Node[16];
    private int size;

    private int indexFor(int key) {
        return Math.floorMod(key, buckets.length);
    }

    public void put(int key, int value) {
        int index = indexFor(key);
        Node node = buckets[index];

        while (node != null) {
            if (node.key == key) {
                node.value = value;
                return;
            }

            node = node.next;
        }

        Node newNode = new Node(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;

        size++;
        if (size > 0.75 * buckets.length) {
            resize();
        }
    }

    private Node findNode(int key) {
        int index = indexFor(key);
        Node node = buckets[index];

        while (node != null) {
            if (node.key == key) {
                return node;
            }

            node = node.next;
        }

        return null;
    }

    public int get(int key) {
        Node node = findNode(key);

        return node != null ? node.value : -1; // Returns -1 if the key is not present (ambiguous if -1 is a store
                                               // value)
    }

    public boolean containsKey(int key) {
        return findNode(key) != null;
    }

    public void remove(int key) {
        Node prevNode = null;
        int index = indexFor(key);
        Node node = buckets[index];

        while (node != null) {

            if (node.key == key) {
                if (prevNode == null) {
                    buckets[index] = node.next;
                } else {
                    prevNode.next = node.next;
                }

                size--;
                return;
            }

            prevNode = node;
            node = node.next;
        }

    }

    private void resize() {
        Node[] old = buckets;
        buckets = new Node[old.length * 2];
        size = 0;

        for (Node head : old) {
            Node node = head;
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(5, 50);
        map.put(21, 210); // collides with 5 (both bucket 5)
        System.out.println(map.get(5)); // 50: proves the chain wasn't overwritten
        System.out.println(map.get(21)); // 210
        map.put(5, 99); // update an existing key
        System.out.println(map.get(5)); // 99
        System.out.println(map.get(7)); // -1: missing key
        map.put(-7, 70); // negative key
        System.out.println(map.get(-7)); // 70
        System.out.println(map.containsKey(21)); // true
        System.out.println(map.containsKey(100)); // false

        map.put(5, 50);
        map.put(21, 210);
        map.put(37, 370);
        map.remove(21); // middle
        System.out.println(map.get(21)); // -1
        System.out.println(map.get(5) + " " + map.get(37)); // 50 370, neighbours intact
        map.remove(37); // the first node now (inserted last, so it's at the front)
        map.remove(5);
        map.remove(999); // missing key: should do nothing, no crash
        System.out.println(map.get(5)); // -1

        MyHashMap big = new MyHashMap();
        for (int i = 0; i < 100; i++)
            big.put(i, i * 10);
        boolean ok = true;
        for (int i = 0; i < 100; i++)
            if (big.get(i) != i * 10)
                ok = false;
        System.out.println(ok + " " + big.size()); // expect: true 100
    }

}
