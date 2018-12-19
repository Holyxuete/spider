package edu.csuft.holy.test;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.csuft.holy.domain.Test;
import edu.csuft.holy.spider.Film;

public class domain {
	public static void main(String[] args) {
		// 含4个线程的线程池
		ExecutorService pool = Executors.newFixedThreadPool(4);
		
		// 存放所有Film的集合
		ArrayList<Film> list = new ArrayList<>();
		
		// 目标url
		String url = "https://movie.douban.com/top250?start=";
		for(int i = 0; i < 10; i++) {
			String path = String.format("%s%d", url,(25 * i));
			// 开启线程
			pool.submit(new Test(path, list));
		}
		// 线程池关闭
		pool.shutdown();
		
		// 如果线程池未关闭，延时1秒
		while(!pool.isTerminated()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println(list.size());
		// 写入本地路径
		String file = "E://Pictures//film.csv";
//		file = "file.csv";
		// 排序
		Collections.sort(list);
		
		// 写入本地
		try (FileWriter out = new FileWriter(file)) {
			for(Film film : list) {
				out.write(film.toCSV());
			}
			System.out.println("successful");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
