package com.example.t.myhashmap;

public interface MyMap<K, V> {
    V put(K k, V v);
    V get(K k);
}
