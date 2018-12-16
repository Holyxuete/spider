package edu.csuft.holy.spider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author Zypher
 *
 */
public class App {
	public static void main(String[] args) {
		// 目标url
		String url = "https://movie.douban.com/top250";
		
		// 利用Jsoup抓取
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements es = doc.select(".wrapper");
			System.out.println(es.size());
			
			// 创建一个影片的列表
			ArrayList<Film> list = new ArrayList<>();
			
			for(Element e : es) {
				Film film = new Film();
				
				Element title = e.select(".title").first();
				Element other = e.select(".other").first();
				Element descp = e.select(".bd p").first();
				Element rate = e.select(".rating_num").first();
				Element eval = e.select(".star span").last();
				film.setTitle(title.toString());
			}
//			String title = doc.title();
//			String html = doc.html();
//			String text = doc.data();
			
//			System.out.println(title);
//			System.out.println(html);
//			System.out.println(text);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}









