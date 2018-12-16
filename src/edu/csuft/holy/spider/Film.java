package edu.csuft.holy.spider;

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
	 * 海报
	 */
	private String poster;
	
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
	
	@Override
	public String toString() {
		return "Film [title=" + title + ", info=" + info + ", rating=" + rating + ", number=" + number + ", id=" + id
				+ ", poster=" + poster + "]";
	}
	
}
