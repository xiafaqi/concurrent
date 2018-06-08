/**
 * 
 */
package com.farry.concurrent.demo_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 
 * 给lists添加volalite后，t2能得到通知，但是，t2线程死循环会浪费cpu，如果不死循环，该怎么做？
 * 
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是运用这种方法，必须保证t2先执行，也就是首先让t2先监听才可以
 * 
 * 阅读下面的程序，并分析输出结果
 * 可以读到输出结果并不是t2=5时退出，而是t1结束时t2才退出
 * 
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * 
 * 阅读程序可想而知，整个过程比较繁琐
 * 
 * 可以使用Latch 门闩 替代wait notify来进行通知
 * 好处是通信方式简单，同事可以指定等待时间
 * 使用await和countdown方法替代wait和notify
 * CountDownLatch不涉及锁定，当count值为零时当前线程继续运行
 * 当不涉及同步，只涉及线程通信的时候，用synchroinzed + wait/notify就显得太重了
 * 这时应该考虑countdownlatch/cyclibarrier/semaphore
 * 
 * @author xiafaqi
 *
 */
public class MyContainer5 {

	// 添加volatile，使t2能够得到通知
	volatile List<Object> lists = new ArrayList<Object>();
	
	public void add (Object o) {
		lists.add(o);
	}
	
	public int size() {
		return lists.size();
	}
	
	public static void main(String[] args) {
		MyContainer5 c = new MyContainer5();
		
		CountDownLatch latch = new CountDownLatch(1);
		
		new Thread(() -> { // 线程一
			System.out.println("t2启动");
			if (c.size() != 5) {
				try {
					latch.await();
					//也可以指定等待时间
					//latch.await(5000, TimeUnit.SECONDS);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("t2结束");
		}, "t2").start();
		
		new Thread(() -> { // 线程一
			System.out.println("t1启动");

			for (int i = 0; i < 10; i++) {
				c.add(new Object());
				System.out.println("add " + i);
				
				if (c.size() == 5) {
					// 门阀打开，让t2得以执行
					latch.countDown();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}, "t1").start();;
	}
}
