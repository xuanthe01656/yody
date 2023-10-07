package model.bean;

public class Status {
	private int id;
	private String status_ad;
	private String status_cus;
	private String note;
	
	public Status() {
		super();
	}

	public Status(int id, String status_ad, String status_cus, String note) {
		super();
		this.id = id;
		this.status_ad = status_ad;
		this.status_cus = status_cus;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus_ad() {
		return status_ad;
	}

	public void setStatus_ad(String status_ad) {
		this.status_ad = status_ad;
	}

	public String getStatus_cus() {
		return status_cus;
	}

	public void setStatus_cus(String status_cus) {
		this.status_cus = status_cus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", status_ad=" + status_ad + ", status_cus=" + status_cus + ", note=" + note + "]";
	}
	
}
