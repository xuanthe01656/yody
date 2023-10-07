package model.bean;

public class size {
	private int id;
	private String name;
	private int created_at;
	private String created_date;
	public size() {
		super();
	}
	public size(int id, String name, int created_at, String created_date) {
		super();
		this.id = id;
		this.name = name;
		this.created_at = created_at;
		this.created_date = created_date;
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
	public int getCreated_at() {
		return created_at;
	}
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	@Override
	public String toString() {
		return "size [id=" + id + ", name=" + name + ", created_at=" + created_at + ", created_date=" + created_date
				+ "]";
	}
	
}
