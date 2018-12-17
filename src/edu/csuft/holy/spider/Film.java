package edu.csuft.holy.spider;

import java.util.Date;

/**
 *  影片
 * @author Zypher
 *
 */
public class Film {
	/**
	 *  影片标题
	 */
	private String title;
	/**
	 *  相关信息
	 */
	private String info;
	/**
	 *  评分
	 */
	private double rating;
	/**
	 *  评分人数
	 */
	private int number;
	/**
	 * 排名
	 */
	private int id;
	/**
	 * 海报网络路径
	 */
	private String poster;
	/**
	 * 海报本地存放路径
	 */
	private String posterPath;
	/**
	 *  上映日期
	 */
	private Date date;
	/**
	 *  上映国家/地区
	 */
	private String nation;
	/**
	 * 语言
	 */
	private String language;
	/**
	 *  类型
	 * @return
	 */
	private String genre;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getPosterPath() {
		return posterPath;
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Film [title=" + title + ", info=" + info + ", rating=" + rating + ", number=" + number + ", id=" + id
				+ ", poster=" + poster + ", posterPath=" + posterPath + ", date=" + date + ", nation=" + nation
				+ ", language=" + language + ", genre=" + genre + "]";
	}
	
}
