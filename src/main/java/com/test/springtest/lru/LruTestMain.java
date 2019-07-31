package com.test.springtest.lru;

import java.util.Iterator;
import java.util.Map;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/5/23 下午1:48
 */
public class LruTestMain {
    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache(4);

        lruCache.put(1, "11");
        lruCache.put(2, "21");
        lruCache.put(3, "31");
        lruCache.put(1, "41");
        lruCache.put(2, "51");
        lruCache.get(3);

        Iterator<Map.Entry<Integer, String>> it = lruCache.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            Integer key = entry.getKey();
            String value = entry.getValue();  //这个无需打印...
            System.out.println("Key:\t" + key+" Value:"+value);
            }
    }
}
