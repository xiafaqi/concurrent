/**
 * 
 */
package com.farry.concurrent.demo_019;

import java.util.ArrayList;
import java.util.List;
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
 * @author xiafaqi
 *
 */
public class MyContainer3 {

	// 添加volatile，使t2能够得到通知
	volatile List<Object> lists = new ArrayList<Object>();
	
	public void add (Object o) {
		lists.add(o);
	}
	
	public int size() {
		return lists.size();
	}
	
	public static void main(String[] args) {
		MyContainer3 c = new MyContainer3();
		Object lock = new Object();
		
		new Thread(() -> { // 线程一
			synchronized (lock) {
				System.out.println("t2启动");
				if (c.size() != 5) {
					try {
						lock.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("t2结束");
			}
		}, "t2").start();
		
		new Thread(() -> { // 线程一
			System.out.println("t1启动");
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					c.add(new Object());
					System.out.println("add " + i);
					
					if (c.size() == 5) {
						lock.notify();
					}
					
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		}, "t1").start();;
	}
}
