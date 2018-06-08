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
 * @author xiafaqi
 *
 *
 * 使用ReentrantLock可以完成通用的功能
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用synchronized锁定如果遇到异常，jvm会自动释放锁，但是lock必须要手动释放锁，因此常在finally中进行锁的释放
 */
public class ReentrantLock2 {

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
	
	public void test2() {
		lock.lock();
		System.out.println("execute test2......");
	}
	
	public static void main(String[] args) {
		ReentrantLock2 r1 = new ReentrantLock2();
		
		new Thread(r1::test1).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new Thread(r1::test2).start();
	}
}
