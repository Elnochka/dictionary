package com.epam.rd.july2019.dictionary.core;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class OrderedMapImpl<K extends Comparable<K>, V> implements OrderedMap<K, V> {

    private int size = 0;
    @SuppressWarnings("unchecked")
    private Map.Entry<K, V>[] kvEntry = new Map.Entry[1];

    @Override
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (kvEntry[i] != null) {
                if (kvEntry[i].key.equals(key)) {
                    return kvEntry[i].value;
                }
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        boolean insert = true;
        for (int i = 0; i < size; i++) {
            if (kvEntry[i].key.equals(key)) {
                kvEntry[i].value = value;
                insert = false;
            }
        }
        if (insert) {
            ensureCapacity();
            kvEntry[size++] = new Map.Entry<K, V>(key, value);
        }

    }

    private void ensureCapacity() {
        if (size == kvEntry.length) {
            int newSize = kvEntry.length * 2;
            kvEntry = Arrays.copyOf(kvEntry, newSize);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        Iterator<Map.Entry<K, V>> curIterator = new Iterator<Map.Entry<K, V>>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && kvEntry[currentIndex] != null;
            }

            @Override
            public  Map.Entry<K, V> next() {
                return kvEntry[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return curIterator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedMapImpl<?, ?> that = (OrderedMapImpl<?, ?>) o;
        return size == that.size &&
                Arrays.equals(kvEntry, that.kvEntry);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(kvEntry);
        return result;
    }

    @Override
    public String toString() {
        return "OrderedMapImpl{" +
                "size=" + size +
                ", kvEntry=" + Arrays.toString(kvEntry) +
                '}';
    }
}
