package model.bean;

public class Discount {
	private int id;
	private int category_id;
	private String discount_code;
	private int reduced_price;
	private String created_date;
	private String date_end;
	private Menu menu;
	
	public Discount() {
		super();
	}
	public Discount(int id, int category_id, String discount_code, int reduced_price, String created_date,
			String date_end, Menu menu) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.discount_code = discount_code;
		this.reduced_price = reduced_price;
		this.created_date = created_date;
		this.date_end = date_end;
		this.menu = menu;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getDiscount_code() {
		return discount_code;
	}
	public void setDiscount_code(String discount_code) {
		this.discount_code = discount_code;
	}
	public int getReduced_price() {
		return reduced_price;
	}
	public void setReduced_price(int reduced_price) {
		this.reduced_price = reduced_price;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	@Override
	public String toString() {
		return "Discount [id=" + id + ", category_id=" + category_id + ", discount_code=" + discount_code
				+ ", reduced_price=" + reduced_price + ", created_date=" + created_date + ", date_end=" + date_end
				+ ", menu=" + menu + "]";
	}
	
}
