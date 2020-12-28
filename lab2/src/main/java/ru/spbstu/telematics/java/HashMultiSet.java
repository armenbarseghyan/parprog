package ru.spbstu.telematics.java;
public class HashMultiSet<K> implements MultiSet<K> {
    private static int defaultcapacity = 16;
    private static double defaultLoader = 0.75;
    private Entry<K>[] table = null;
    private int size = 0;

    class Entry<K> implements MultiSet.Entry<K> {
        K k;
        int count;
        Entry next;

        public Entry(K k, int count, Entry next) {
            this.k = k;
            this.count = count;
            this.next = next;
        }

        public Entry(Entry c) {
            this.k = (K) c.k;
            this.count = (Integer) c.count;
            this.next = null;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public int getValue() {
            return count;
        }

        @Override
        public void setValue(int count) {
            this.count = count;
        }
    }

    public HashMultiSet() {
        this(defaultcapacity, defaultLoader);
    }

    public HashMultiSet(int length, double loader) {
        defaultcapacity = length;
        defaultLoader = loader;
        table = new Entry[defaultcapacity];
    }

    private int getIndex(K k) {
        int m = defaultcapacity;
        int index = k.hashCode() % m - 1;
        return (index > 0 ? index : -index) % m;
    }

    @Override
    public int put(K k) {
        int index = getIndex(k);
        int count;
        Entry<K> entry = table[index];
        if (entry == null) {
            count = 1;
            table[index] = new Entry(k, count, null);
            size++;
        } else {
            Entry node = table[index];
            count = entry.getValue() + 1;
            table[index] = new Entry<K>(k,  count, entry);
            size++;
        }
        if (size >= defaultcapacity * defaultLoader) {
            resize();
        }
        return count;
    }

    @Override
    public int get(K k) {
        int index = getIndex(k);
        if (table[index] == null) {
            return 0;
        }
        return findValue(k, table[index]);
    }

    public int findValue(K k, Entry<K> entry) {
        if (k == entry.getKey() || k.equals(entry.getKey())) {
            return entry.getValue();
        } else {
            if (entry.next != null) {
                return findValue(k, (Entry<K>) entry.next);
            }
        }
        return entry.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(K k) {
        int index = getIndex(k);
        Entry node = table[index];
        Entry prev = table[index];
        while (node != null) {
            if (node.k.equals(k)) {
                if (node == prev) {
                    table[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
            }
            prev = node;
            node = node.next;
        }
    }

    @Override
    public boolean containsKey(K k) {
        int index = getIndex(k);
        Entry node = table[index];
        while (node != null) {
            if (node.getKey().equals(k)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public Entry<K>[] resize() {
        Entry<K>[] newtable = (Entry<K>[]) new Entry[defaultcapacity << 1];
        int capacity = defaultcapacity;
        defaultcapacity = defaultcapacity << 1;
        if (table != null) {
            for (int i = 0; i < capacity; i++) {
                Entry<K> list = null;
                if (table[i] != null) {
                    Entry<K>node =table[i];
                    while(node != null){
                        int index = getIndex(node.k);
                        Entry<K> entry = newtable[index];
                        if (entry == null) {
                            newtable[index] = new Entry(node.k, node.count, null);
                        } else {
                            Entry next = newtable[index];
                            while (next != null) {
                                next = next.next;
                            }
                            newtable[index] = new Entry<K>(node.k, node.count, entry);
                        }
                        node=node.next;
                    }
                }
            }
        }
        table = newtable;
        return newtable;
    }
}