/**
 * 
 */
package com.farry.concurrent.demo_020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock替代synchronized
 * 本例中由于test1锁定this,只有test1执行完毕的时候，test2才能执行
 * 这里复习synchronized最原始的语义
 * 
 * @author xiafaqi
 *
 * 使用ReentrantLock可以完成通用的功能
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用synchronized锁定如果遇到异常，jvm会自动释放锁，但是lock必须要手动释放锁，因此常在finally中进行锁的释放
 * 
 * 使用ReentrantLock可以进行“尝试锁定” tryLock,这样如果无法锁定，或者在指定时间内无法锁定，线程可以决定是否等待
 * 
 * 使用ReentrantLock可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的时候，可以被打断
 * 
 * ReentrantLock还可以使用公平锁
 */
public class ReentrantLock5 extends Thread{

	private static ReentrantLock lock = new ReentrantLock(true); //参数为true表示公平锁，请对比输出结果
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "获得锁");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		ReentrantLock5 r1 = new ReentrantLock5();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1);
		t1.start();
		t2.start();
	}
	
}
