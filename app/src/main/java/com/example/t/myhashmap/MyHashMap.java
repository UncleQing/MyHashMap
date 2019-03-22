package com.example.t.myhashmap;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyHashMap<K, V> implements MyMap<K, V> {
    //数组长度,默认16
    private int arrySize;
    //负载因子，默认0.75
    private double factor;
    //已使用数组数
    private int useSize;
    //链表数组
    private Entry<K, V>[] tables;

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int arrySize, double factor) {
        if (arrySize < 0){
            throw new IllegalArgumentException("数组长度错误:" + arrySize);
        }

        if (factor < 0){
            throw new IllegalArgumentException("负载因子错误:" + factor);
        }

        this.arrySize = arrySize;
        this.factor = factor;

        tables = new Entry[arrySize];

    }

    @Override
    public V put(K k, V v) {
        if (useSize > arrySize * factor){
            up2Size();
        }

        int index = getIndex(k, tables.length);
        if (tables[index] == null){
            //没有元素直接存
            tables[index] = new Entry<>(k, v, null);
            useSize ++;
        }else {
            //哈希冲突，链表法
            Entry<K, V> entry = tables[index];
            Entry<K, V> temp = entry;
            while (temp != null){
                if (k == temp.getK() || k.equals(temp.getK())){
                    //Hashcode相同，key相同，不存入
                    Log.e("error", "key相同:" + k);
                    return temp.getV();
                }
                temp = temp.getNext();
            }
            tables[index] = new Entry<>(k, v, entry);
            useSize ++;
        }

        Log.e("put", "当前使用容量:" + useSize + "/" + arrySize);
        return null;
    }

    private int getIndex(K k, int length) {
        //根据当前长度对哈希值取模
        return hash(k.hashCode()) & (length - 1);
    }

    private int hash(int hashcode){
        return Math.abs(hashcode ^ (hashcode << 3) ^ (hashcode >> 7));
    }

    /**
     * 扩容
     */
    private void up2Size() {
        List<Entry<K, V>> entryList = new ArrayList<>();
        for (Entry<K, V> entry : tables){
            if (entry != null) {
                addLinkEntry(entry, entryList);
            }
        }
        if (entryList.size() > 0){
            arrySize *= 2;
            useSize = 0;
            tables = new Entry[arrySize];

            for (Entry<K, V> entry : entryList){
                entry.setNext(null);
                put(entry.getK(), entry.getV());
            }
        }
    }

    /**
     * 遍历链表追加到list
     * @param entry
     * @param entryList
     */
    private void addLinkEntry(Entry<K, V> entry, List<Entry<K, V>> entryList) {
        entryList.add(entry);
        if (entry.getNext() != null){
            addLinkEntry(entry.getNext(), entryList);
        }
    }



    @Override
    public V get(K k) {
        int index = getIndex(k, arrySize);
        Entry<K, V> entry = tables[index];
        return findEntryByK(k, entry);
    }

    private V findEntryByK(K k, Entry<K, V> entry) {
        if (k == entry.getK() || k.equals(entry.getK())){
            return entry.getV();
        }else if (entry.getNext() != null){
            return findEntryByK(k, entry.getNext());
        }
        return null;
    }
}
