/**
 * 
 */
package com.farry.concurrent.demo_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPool:固定大小的线程池
 * @author xiafaqi
 * 总结：
 * 池中线程数量固定，不会发生变化
 * 使用无界LinkedBlockingQueue,要综合考虑生产与消费能力，生产过剩，可能导致内存溢出。
 * 适用一些很稳定很固定的正规并发线程，多用于服务器
 */
public class MyFixedThreadPool {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(4);
		
		for (int i = 0; i < 50; i++) {
			pool.submit(new ThreadRunner((i + 1)));
		}
		
		pool.shutdown();
		
		System.out.println(pool);
	}
}
