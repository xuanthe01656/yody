package model.bean;

public class Orders {
	private int id;
	private String order_code;
	private String product_id;
	private String product_name;
	private int qty;
	private float total;
    private int order_user;
    private String order_date;
    private int status;
	public Orders() {
		super();
	}
	public Orders(int id, String order_code, String product_id, String product_name, int qty, float total,
			int order_user,String order_date, int status) {
		super();
		this.id = id;
		this.order_code = order_code;
		this.product_id = product_id;
		this.product_name = product_name;
		this.qty = qty;
		this.total = total;
		this.order_user = order_user;
		this.order_date = order_date;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getOrder_user() {
		return order_user;
	}
	public void setOrder_user(int order_user) {
		this.order_user = order_user;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", order_code=" + order_code + ", product_id=" + product_id + ", product_name="
				+ product_name + ", qty=" + qty + ", total=" + total + ", order_user=" + order_user + ", status="
				+ status + "]";
	}
    
}
