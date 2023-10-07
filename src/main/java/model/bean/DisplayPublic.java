package model.bean;

public class DisplayPublic {
	private int id;
	private String name;
	private String images;
	private String img_detail;
	private String detail;
	private String created_date;
	private String update_date;
	private int created_at;
	private int update_at;
	public DisplayPublic() {
		super();
	}
	public DisplayPublic(int id, String name, String images, String img_detail, String detail, String created_date,
			String update_date, int created_at, int update_at) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
		this.img_detail = img_detail;
		this.detail = detail;
		this.created_date = created_date;
		this.update_date = update_date;
		this.created_at = created_at;
		this.update_at = update_at;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getImg_detail() {
		return img_detail;
	}
	public void setImg_detail(String img_detail) {
		this.img_detail = img_detail;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public int getCreated_at() {
		return created_at;
	}
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
	public int getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(int update_at) {
		this.update_at = update_at;
	}
	@Override
	public String toString() {
		return "DisplayPublic [id=" + id + ", name=" + name + ", images=" + images + ", img_detail=" + img_detail
				+ ", detail=" + detail + ", created_date=" + created_date + ", update_date=" + update_date
				+ ", created_at=" + created_at + ", update_at=" + update_at + "]";
	}
	
}
