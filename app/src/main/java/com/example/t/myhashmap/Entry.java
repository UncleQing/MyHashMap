package com.example.t.myhashmap;

public class Entry<K, V> {
    private V v;
    private K k;
    private Entry<K, V> next;

    public Entry(K k, V v, Entry<K, V> next) {
        this.v = v;
        this.k = k;
        this.next = next;
    }

    public V getV() {
        return v;
    }

    public K getK() {
        return k;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }
}
