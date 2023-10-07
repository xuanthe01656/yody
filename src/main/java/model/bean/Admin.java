package model.bean;

public class Admin {
	private int id;
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String phone;
	private String address;
	private String position_detail;
	private String images;
	private int position_id;
	private String created_date;
	public Admin() {
		super();
	}
	public Admin(int id, String username, String password, String fullname, String email, String phone, String address, String position_detail,
		String images ,int position_id, String created_date) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.position_detail = position_detail;
		this.images = images;
		this.position_id = position_id;
		this.created_date = created_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosition_detail() {
		return position_detail;
	}
	public void setPosition_detail(String position_detail) {
		this.position_detail = position_detail;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public int getPosition_id() {
		return position_id;
	}
	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", position_detail=" + position_detail+", images=" + images+", position=" + position_id
				+ ", created_date=" + created_date + "]";
	}
	
	
}
