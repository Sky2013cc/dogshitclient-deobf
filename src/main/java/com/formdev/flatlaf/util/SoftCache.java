package com.formdev.flatlaf.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/* loaded from: target.jar:com/formdev/flatlaf/util/SoftCache.class */
public class SoftCache<K, V> implements Map<K, V> {
    private final Map<K, CacheReference<K, V>> map;
    private final ReferenceQueue<V> queue;

    public SoftCache() {
        this.queue = new ReferenceQueue<>();
        this.map = new HashMap();
    }

    public SoftCache(int initialCapacity) {
        this.queue = new ReferenceQueue<>();
        this.map = new HashMap(initialCapacity);
    }

    @Override // java.util.Map
    public int size() {
        expungeStaleEntries();
        return this.map.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        expungeStaleEntries();
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        expungeStaleEntries();
        return this.map.containsKey(key);
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public V get(Object key) {
        expungeStaleEntries();
        return getRef(this.map.get(key));
    }

    @Override // java.util.Map
    public V put(K key, V value) {
        expungeStaleEntries();
        return getRef(this.map.put(key, new CacheReference<>(key, value, this.queue)));
    }

    @Override // java.util.Map
    public V remove(Object key) {
        expungeStaleEntries();
        return getRef(this.map.remove(key));
    }

    private V getRef(CacheReference<K, V> ref) {
        if (ref != null) {
            return ref.get();
        }
        return null;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        expungeStaleEntries();
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
        expungeStaleEntries();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        expungeStaleEntries();
        return this.map.keySet();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> action) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        throw new UnsupportedOperationException();
    }

    private void expungeStaleEntries() {
        while (true) {
            Reference<? extends V> reference = this.queue.poll();
            if (reference != null) {
                this.map.remove(((CacheReference) reference).key);
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/formdev/flatlaf/util/SoftCache$CacheReference.class */
    public static class CacheReference<K, V> extends SoftReference<V> {
        final K key;

        CacheReference(K key, V value, ReferenceQueue<? super V> queue) {
            super(value, queue);
            this.key = key;
        }
    }
}
