/**
 * 
 */
package com.farry.concurrent.demo_020;

import java.util.concurrent.TimeUnit;
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
 */
public class ReentrantLock4 {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		
		Thread t1 = new Thread(() -> {
			try {
				lock.lock();
				System.out.println("t1 start");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
				System.out.println("t1 end");
			} catch (Exception e) {
				System.out.println("interrupted!");
			} finally {
				lock.unlock();
			}
		});
		t1.start();
		
		Thread t2 = new Thread(() -> {
			try {
				//lock.lock();
				lock.lockInterruptibly(); //可以对interrupt方法做出响应
				System.out.println("t2 start");
				TimeUnit.SECONDS.sleep(5);
				System.out.println("t2 end");
			} catch (Exception e) {
				System.out.println("interrupted!");
			} finally {
				lock.unlock();
			}
		}) ;
		t2.start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		t2.interrupt(); //打断线程2的等待
		
	}
}
