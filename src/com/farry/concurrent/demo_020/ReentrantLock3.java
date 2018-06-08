/**
 * 
 */
package com.farry.concurrent.demo_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
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
 */
public class ReentrantLock3 {

	Lock lock = new ReentrantLock();
	
	public void test1() {
		try {
			lock.lock();// 相当于synchronized(this)
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 必须手动释放锁
			lock.unlock();
		}
	}
	
	/**
	 * 可以使用tryLock尝试锁定，不管锁定与否，方法都将继续进行
	 * 也可以根据tryLock返回值来判定是否锁定
	 * 还可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unLock的处理，必须放到finally中
	 */
	public void test2() {
		boolean locked = false;
		
		try {
			lock.tryLock(6, TimeUnit.SECONDS);
			System.out.println("test2..." + locked);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (locked) {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		ReentrantLock3 r1 = new ReentrantLock3();
		
		new Thread(r1::test1).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new Thread(r1::test2).start();
	}
}
