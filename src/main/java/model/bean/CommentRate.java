package model.bean;

public class CommentRate {
	private int id;
	private String product_sku;
	private String comment;
	private String name;
	private String email;
	private String phone;
	private int like_coment;
	private String ip_like;
	private String id_user_like;
	private String images;
	private String rate;
	private String user_id;
	private int parent_id;
	public CommentRate() {
		super();
	}
	public CommentRate(int id, String product_sku, String comment, String name, String email, String phone,
			int like_coment, String ip_like, String id_user_like, String images, String rate, String user_id,
			int parent_id) {
		super();
		this.id = id;
		this.product_sku = product_sku;
		this.comment = comment;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.like_coment = like_coment;
		this.ip_like = ip_like;
		this.id_user_like = id_user_like;
		this.images = images;
		this.rate = rate;
		this.user_id = user_id;
		this.parent_id = parent_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct_sku() {
		return product_sku;
	}
	public void setProduct_sku(String product_sku) {
		this.product_sku = product_sku;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getLike_coment() {
		return like_coment;
	}
	public void setLike_coment(int like_coment) {
		this.like_coment = like_coment;
	}
	public String getIp_like() {
		return ip_like;
	}
	public void setIp_like(String ip_like) {
		this.ip_like = ip_like;
	}
	public String getId_user_like() {
		return id_user_like;
	}
	public void setId_user_like(String id_user_like) {
		this.id_user_like = id_user_like;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	@Override
	public String toString() {
		return "CommentRate [id=" + id + ", product_sku=" + product_sku + ", comment=" + comment + ", name=" + name
				+ ", email=" + email + ", phone=" + phone + ", like_coment=" + like_coment + ", ip_like=" + ip_like
				+ ", id_user_like=" + id_user_like + ", images=" + images + ", rate=" + rate + ", user_id=" + user_id
				+ ", parent_id=" + parent_id + "]";
	}
	
}
