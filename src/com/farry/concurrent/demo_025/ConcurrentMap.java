/**
 * 
 */
package com.farry.concurrent.demo_025;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913 
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskiplistmap
 * 
 * @author xiafaqi
 *
 */
public class ConcurrentMap {

	public static void main(String[] args) {
		Map<String, String> map = new ConcurrentHashMap<>();
//		Map<String, String> map = new ConcurrentSkipListMap<>(); // 高并发并且排序
		
//		Map<String, String> map = new Hashtable<>();
//		Map<String, String> map = new HashMap<>();
		
		Random random = new Random();
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					map.put("a" + random.nextInt(100000), "a" + random.nextInt(100000));
				}
				latch.countDown();
			});
		}
		
		Arrays.asList(threads).forEach(t->t.start());
		try {
			latch.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
