package com.test.springtest;

import com.test.config.TestConfig;
import com.test.springtest.lru.LRUCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Map;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/5/22 上午10:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringtestApplication.class})
@Import(TestConfig.class)
public class LruTests {

    @Test
    public void testTransaction() {
        LRUCache<Integer, String> lruCache = new LRUCache(4);

        lruCache.put(1, "11");
        lruCache.put(2, "21");
        lruCache.put(3, "31");
        lruCache.put(1, "41");
        lruCache.put(2, "51");

        Iterator<Map.Entry<Integer, String>> it = lruCache.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            Integer key = entry.getKey();
            System.out.print("Key:\t" + key);
            String Value = entry.getValue();  //这个无需打印...
            System.out.println();
        }

    }
}
