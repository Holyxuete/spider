package edu.csuft.holy.domain;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.csuft.holy.spider.Film;

/**
 * 
 * @author Zypher
 *
 */
public class App2 {
	// 海报本地存放路径
	public static String PATH = "E://Pictures//spiderPhoto//";
	
	//
	public void spider(String url, ArrayList<Film> list) {
		// 目标url
//		String url = "https://movie.douban.com/top250";
		// 输入流读取海报图片
		InputStream inputStream = null;
		// 缓冲流
		BufferedInputStream bis = null;
		// 输出流写出图片
		FileOutputStream fos = null;
		
		App2 app = new App2();
		// 利用Jsoup抓取
//		ArrayList<Film> list = new ArrayList<>();	
		try {
			Document doc = Jsoup.connect(url).get();
			// 获取当前页面所有影片的 li 标签
			Elements es = doc.select(".grid_view li");
			
			// 创建一个影片的列表
			
			for(Element e : es) {
				Film film = new Film();
				// 电影具体信息链接地址
				Element eUrl = e.selectFirst(".info a");
				
				// 具体页面URL
				Document movieUrl = Jsoup.connect(eUrl.attr("href")).get();
				
				// 电影标题
				Element title = movieUrl.selectFirst("h1 span");
				film.setTitle(title.text().toString());
				
				// 排名
				int id = Integer.parseInt(e.selectFirst(".item em").text());
				film.setId(id);
				
				// 电影相关信息
				String info = getInfor(movieUrl);
				film.setInfo(info);
				
				// 评分
				Element rating = movieUrl.selectFirst(".rating_num");
				film.setRating(Double.valueOf(rating.text()));
				
				// 评分人数
				String number = movieUrl.selectFirst(".rating_people").text().toString();
				film.setNumber(Integer.parseInt(number.substring(0, number.length() - 3)));
				
				// 上映日期 截串 例 1994-04-10
				String da = movieUrl.selectFirst("span[property='v:initialReleaseDate']").text().substring(0, 10);
				// 设置强转格式
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 字符串转日期
//				Date date = sdf.parse(da);
				film.setDate(da);
				
				// 海报路径
				String poster = e.selectFirst("img").attr("src").toString();
				film.setPoster(poster);
				
				// 国家
				// 获取第六个含 pl 类的 span 标签的前一个元素节点的前一个文本节点
				String nation = movieUrl.select(".pl").get(5).previousElementSibling().previousSibling().toString();
				String language = movieUrl.select(".pl").get(6).previousElementSibling().previousSibling().toString();;
				if(nation.contains("href")) {
					nation = movieUrl.select(".pl").get(6).previousElementSibling().previousSibling().toString();
					language = movieUrl.select(".pl").get(7).previousElementSibling().previousSibling().toString();
				}
				if(nation.contains("href")) {
					nation = movieUrl.select(".pl").get(7).previousElementSibling().previousSibling().toString();
					movieUrl.select(".pl").get(8).previousElementSibling().previousSibling().toString();
				}
				film.setNation(nation);
				film.setLanguage(language);
				
				// 语言
				// 获取第七个含 pl 类的 span 标签的前一个元素节点的前一个文本节点
				
				// 类型
				String genre = movieUrl.select("span[property='v:genre']").text();
				film.setGenre(genre);
				
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
				
//				System.out.println(film.getDate());
				// 将 film 存入 ArrayList 内
				list.add(film);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭流
			app.StreamClose(fos, bis, inputStream);
		}
	}

	/**
	 * 获取电影相关信息
	 * @param movieUrl 电影路径
	 * @return
	 */
	private static String getInfor(Document movieUrl) {
		// 获取导演
		String info = "导演 : " + movieUrl.select("a[rel='v:directedBy']").text();
		// 获取编剧
//		info += "编剧 : " + movieUrl.select("#info .attrs").get(1).text() + "\n";
		// 获取主演
//		info += "主演 : " + movieUrl.select("#info .attrs").get(2).text() + "\n";
		return info;
	}
	
	/**
	 * 流的关闭
	 * @param fos 输出流
	 * @param bis 缓冲流
	 * @param is  输入流
	 */
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









