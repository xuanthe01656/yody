package model.bean;

public class productDetail {
	private int id;
	private String productSKU;
	private String name;
	private int color_id;
	private int size_id;
	private int amount;
	private double cost;
	private double price;
	private String outstanding_feature;
	private String detail;
	private int discount;
	private String images;
	private String list_images;
	private int view;
	private int menu_c1_id;
	@Override
	public String toString() {
		return "productDetail [id=" + id + ", productSKU=" + productSKU + ", name=" + name + ", color_id=" + color_id
				+ ", size_id=" + size_id + ", amount=" + amount + ", cost=" + cost + ", price=" + price
				+ ", outstanding_feature=" + outstanding_feature + ", detail=" + detail + ", discount=" + discount
				+ ", images=" + images + ", list_images=" + list_images + ", view=" + view + ", menu_c1_id="
				+ menu_c1_id + ", menu_c2_id=" + menu_c2_id + ", menu_id=" + menu_id + ", suppliers_id=" + suppliers_id
				+ ", country_id=" + country_id + ", created_at=" + created_at + ", update_at=" + update_at
				+ ", created_date=" + created_date + ", is_detete=" + is_detete + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductSKU() {
		return productSKU;
	}
	public void setProductSKU(String productSKU) {
		this.productSKU = productSKU;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getColor_id() {
		return color_id;
	}
	public void setColor_id(int color_id) {
		this.color_id = color_id;
	}
	public int getSize_id() {
		return size_id;
	}
	public void setSize_id(int size_id) {
		this.size_id = size_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOutstanding_feature() {
		return outstanding_feature;
	}
	public void setOutstanding_feature(String outstanding_feature) {
		this.outstanding_feature = outstanding_feature;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getList_images() {
		return list_images;
	}
	public void setList_images(String list_images) {
		this.list_images = list_images;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getMenu_c1_id() {
		return menu_c1_id;
	}
	public void setMenu_c1_id(int menu_c1_id) {
		this.menu_c1_id = menu_c1_id;
	}
	public int getMenu_c2_id() {
		return menu_c2_id;
	}
	public void setMenu_c2_id(int menu_c2_id) {
		this.menu_c2_id = menu_c2_id;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getSuppliers_id() {
		return suppliers_id;
	}
	public void setSuppliers_id(int suppliers_id) {
		this.suppliers_id = suppliers_id;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public int getCreated_at() {
		return created_at;
	}
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
	public int getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(int update_at) {
		this.update_at = update_at;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public int getIs_detete() {
		return is_detete;
	}
	public void setIs_detete(int is_detete) {
		this.is_detete = is_detete;
	}
	public productDetail(int id, String productSKU, String name, int color_id, int size_id, int amount, double cost,
			double price, String outstanding_feature, String detail, int discount, String images, String list_images,
			int view, int menu_c1_id, int menu_c2_id, int menu_id, int suppliers_id, int country_id, int created_at,
			int update_at, String created_date, int is_detete) {
		super();
		this.id = id;
		this.productSKU = productSKU;
		this.name = name;
		this.color_id = color_id;
		this.size_id = size_id;
		this.amount = amount;
		this.cost = cost;
		this.price = price;
		this.outstanding_feature = outstanding_feature;
		this.detail = detail;
		this.discount = discount;
		this.images = images;
		this.list_images = list_images;
		this.view = view;
		this.menu_c1_id = menu_c1_id;
		this.menu_c2_id = menu_c2_id;
		this.menu_id = menu_id;
		this.suppliers_id = suppliers_id;
		this.country_id = country_id;
		this.created_at = created_at;
		this.update_at = update_at;
		this.created_date = created_date;
		this.is_detete = is_detete;
	}
	private int menu_c2_id;
	private int menu_id;
	private int suppliers_id;
	private int country_id;
	private int created_at;
	private int update_at;
	private String created_date;
	private int is_detete;
	public productDetail() {
		super();
	}
	
}
