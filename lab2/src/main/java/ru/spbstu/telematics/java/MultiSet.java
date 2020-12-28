package ru.spbstu.telematics.java;
public interface MultiSet<K>{
    int put(K k);
    int get(K k);
    void remove(K k);
    boolean containsKey(K k);
    int size();
    interface Entry<K> {
        K getKey();
        int getValue();
        void setValue(int count);
    }
}
