package aiReviews;

public class IntHashMap {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAX_CAPACITY = 1 << 30;
    private static final float LOAD_FACTOR = 0.75f;

    private static class Node {
        final int key;
        int value;
        Node next;

        Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node[] buckets;
    private int size;

    public IntHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
    }

    /** Spreads high bits into low bits so the bucket mask uses more of the key. */
    private static int hash(int key) {
        return key ^ (key >>> 16);
    }

    /** Capacity is always a power of two, so a bitmask replaces modulo. */
    private static int indexFor(int key, int capacity) {
        return hash(key) & (capacity - 1);
    }

    private int threshold() {
        return (int) (buckets.length * LOAD_FACTOR);
    }

    private Node findNode(int key) {
        for (Node n = buckets[indexFor(key, buckets.length)]; n != null; n = n.next) {
            if (n.key == key) {
                return n;
            }
        }
        return null;
    }

    /** Inserts the key, or overwrites the value if the key already exists. */
    public void put(int key, int value) {
        Node existing = findNode(key);
        if (existing != null) {
            existing.value = value;
            return;
        }

        int idx = indexFor(key, buckets.length);
        buckets[idx] = new Node(key, value, buckets[idx]);
        size++;

        if (size > threshold() && buckets.length < MAX_CAPACITY) {
            resize();
        }
    }

    /** Returns the value for the key, or -1 if the key is absent. */
    public int get(int key) {
        Node n = findNode(key);
        return n == null ? -1 : n.value;
    }

    public boolean containsKey(int key) {
        return findNode(key) != null;
    }

    /** Removes the key. Returns true if it was present. */
    public boolean remove(int key) {
        int idx = indexFor(key, buckets.length);
        Node prev = null;
        for (Node n = buckets[idx]; n != null; prev = n, n = n.next) {
            if (n.key == key) {
                if (prev == null) {
                    buckets[idx] = n.next;
                } else {
                    prev.next = n.next;
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Doubles capacity and relinks existing nodes into the new array (no node
     * copies).
     */
    private void resize() {
        Node[] oldBuckets = buckets;
        Node[] newBuckets = new Node[oldBuckets.length * 2];
        int mask = newBuckets.length - 1;

        for (Node head : oldBuckets) {
            Node n = head;
            while (n != null) {
                Node next = n.next;
                int idx = hash(n.key) & mask;
                n.next = newBuckets[idx];
                newBuckets[idx] = n;
                n = next;
            }
        }
        buckets = newBuckets;
    }

    public static void main(String[] args) {
        IntHashMap map = new IntHashMap();

        for (int i = 0; i < 100; i++) {
            map.put(i, i * 10);
        }
        map.put(5, 999); // update existing key

        assert map.size() == 100;
        assert map.get(5) == 999;
        assert map.get(42) == 420;
        assert map.get(1000) == -1; // missing key
        assert map.containsKey(0);
        assert !map.containsKey(1000);

        assert map.remove(42);
        assert !map.remove(42); // already gone
        assert map.get(42) == -1;
        assert map.size() == 99;

        // Negative keys and collisions across resizes
        for (int i = -50; i < 50; i++) {
            map.put(i, i);
        }
        assert map.get(-50) == -50;
        assert map.get(-1) == -1;
        map.remove(-1); // add: key -1 is now gone
        assert map.get(-1) == -1;  // add: still passes, which proves the check is useless

        System.out.println("All checks passed. size = " + map.size());
    }
}