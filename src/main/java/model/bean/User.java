package model.bean;

public class User {
	private int id;
	private String fullname;
	private String password;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String created_date;
	private String update_date;
	private String update_at;
	private int status;
	
	public User() {
		super();
	}

	public User(int id, String fullname, String password, String email, String phone, String address, String city,
			String created_date, String update_date, String update_at, int status) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.created_date = created_date;
		this.update_date = update_date;
		this.update_at = update_at;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullname=" + fullname + ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", address=" + address + ", city=" + city + ", created_date=" + created_date
				+ ", update_date=" + update_date + ", update_at=" + update_at + ", status=" + status + "]";
	}
	
}
