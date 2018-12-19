package edu.csuft.holy.domain;

import java.util.ArrayList;

import edu.csuft.holy.spider.Film;

public class Test implements Runnable{
	String u;
	ArrayList<Film> list;
	
	public Test(String u, ArrayList<Film> list) {
		super();
		this.u = u;
		this.list = list;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		App2 app = new App2();
		String name = Thread.currentThread().getName();
		System.out.println(name + "线程，开始 : "  + u);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		app.spider(u,list);
		System.out.println(name + "线程，完成 : "  + u);
	}
}
