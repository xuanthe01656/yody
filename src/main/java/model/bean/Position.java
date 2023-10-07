package model.bean;

public class Position {
	private int id;
	private String position;
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Position(int id, String position) {
		super();
		this.id = id;
		this.position = position;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Position [id=" + id + ", position=" + position + "]";
	}
	
	
}
