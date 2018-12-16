package edu.csuft.holy.spider;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	// 海报本地存放路径
	static public String PATH = "E://Pictures//spiderPhoto//";
	
	//
	public static void main(String[] args) {
		// 目标url
		String url = "https://movie.douban.com/top250";
		// 输入流读取海报图片
		InputStream inputStream = null;
		// 缓冲流
		BufferedInputStream bis = null;
		// 输出流写出图片
		FileOutputStream fos = null;
		
		App app = new App();
		// 利用Jsoup抓取
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements es = doc.select(".grid_view");
			
			// 创建一个影片的列表
			ArrayList<Film> list = new ArrayList<>();
			
			for(Element e : es) {
				Film film = new Film();
				// 电影具体信息链接地址
				Element eUrl = e.select(".info a").first();
				
				// 具体页面URL
				Document movieUrl = Jsoup.connect(eUrl.attr("href")).get();
				
				// 电影标题
				Element title = movieUrl.select("h1").first();
				film.setTitle(title.text().toString());
				
				// 排名
				int id = Integer.parseInt(e.select(".item em").first().text());
				film.setId(id);
				
				// 电影相关信息
//				Element in = movieUrl.select(".info").first();
//				System.out.println(in.text());
				
				// 评分
				Element rating = movieUrl.select(".rating_num").first();
				film.setRating(Double.valueOf(rating.text()));
				
				// 评分人数
				String number = movieUrl.select(".rating_people").first().text().toString();
				film.setNumber(Integer.parseInt(number.substring(0, number.length() - 3)));
				
				// 上映日期
				Element d = movieUrl.select("span[property='v:initialReleaseDate']").first();
				// 截串 例 1994-04-10
				String da = d.text().toString().substring(0, 10);
				// 字符串转日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(da);
				film.setDate(date);
				
				// 海报路径
				String poster = e.select("img").first().attr("src").toString();
				film.setPoster(poster);
				
				// 通过创建连接
				URL posterUrl = new URL(poster);
				// 通过url打开链接并获取输入流
				inputStream = posterUrl.openConnection().getInputStream();
				// 将输入流信息放入缓冲流中提升读写速度
				bis = new BufferedInputStream(inputStream);
				
				byte[] buf = new byte[1024];
				int size = 0;
				// 输出流 生成文件
				fos = new FileOutputStream(PATH + id + ".jpg");
				// 边读边写
				while((size = bis.read(buf)) != -1) {
					fos.write(buf, 0, size);
				}
				film.setPosterPath(PATH + id + ".jpg");
				System.out.println(film);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			app.StreamClose(fos, bis, inputStream);
		}
	}
	
	// 流的关闭
	public void StreamClose(FileOutputStream fos, BufferedInputStream bis, InputStream is) {
		if(fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(bis != null) {
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}









