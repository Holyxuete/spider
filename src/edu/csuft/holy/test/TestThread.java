package edu.csuft.holy.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.csuft.holy.spider.Task;

public class TestThread {
	public static void main(String[] args) {
		// 线程池
		// 固定大小
		ExecutorService pool = Executors.newFixedThreadPool(4);
		// 无线(缓存) 
//		pool = Executors.newCachedThreadPool();
		// 单个线程
//		pool = Executors.newSingleThreadExecutor();
		
		for(int i = 0; i < 100; i++) {
			pool.submit(new Task(i));
		}
	}
}
