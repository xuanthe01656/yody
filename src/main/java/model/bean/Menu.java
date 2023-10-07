package model.bean;

public class Menu {
	private int id;
	private String name;
	private String imgaes;
	private int parentID;
	
	public Menu() {
		super();
	}
	
	public Menu(int id, String name, String imgaes, int parentID) {
		super();
		this.id = id;
		this.name = name;
		this.imgaes = imgaes;
		this.parentID = parentID;
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
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	
	public String getImgaes() {
		return imgaes;
	}
	public void setImgaes(String imgaes) {
		this.imgaes = imgaes;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", imgaes=" + imgaes + ", parentID=" + parentID + "]";
	}
	
	
}
