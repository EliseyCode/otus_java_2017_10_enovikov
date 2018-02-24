package cache;

import java.util.Map;

public interface CacheEngine<K, V> {
    void put(K key, Value<V> value);

    V get(K key);

    int getHitCount();

    int getMissCount();

    void cancel();

    Map<String,Object> getStat();
}
