package model.bean;

public class Suppliers {
	private int id;
	private int suppliers_code;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String tax_code;
	private String note;
	private String created;
	private String update;
	private int user_init;
	private int user_upd;
		
	public Suppliers() {
		super();
	}

	public Suppliers(int id, int suppliers_code, String name, String phone, String email, String address,
			String tax_code, String note, String created, String update, int user_init, int user_upd) {
		super();
		this.id = id;
		this.suppliers_code = suppliers_code;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.tax_code = tax_code;
		this.note = note;
		this.created = created;
		this.update = update;
		this.user_init = user_init;
		this.user_upd = user_upd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSuppliers_code() {
		return suppliers_code;
	}

	public void setSuppliers_code(int suppliers_code) {
		this.suppliers_code = suppliers_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public int getUser_init() {
		return user_init;
	}

	public void setUser_init(int user_init) {
		this.user_init = user_init;
	}

	public int getUser_upd() {
		return user_upd;
	}

	public void setUser_upd(int user_upd) {
		this.user_upd = user_upd;
	}

	@Override
	public String toString() {
		return "Suppliers [id=" + id + ", suppliers_code=" + suppliers_code + ", name=" + name + ", phone=" + phone
				+ ", email=" + email + ", address=" + address + ", tax_code=" + tax_code
				+ ", note=" + note + ", created=" + created + ", update=" + update + ", user_init=" + user_init
				+ ", user_upd=" + user_upd + "]";
	}
	
	
}
