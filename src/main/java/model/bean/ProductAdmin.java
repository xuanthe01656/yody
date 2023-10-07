package model.bean;

public class ProductAdmin {
	private int id;
	private String productSKU;
	private String name;
	private String color;
	private String size;
	private String menu;
	private double price;
	private double cost;
	private double promotional_price;
	private int amount;
	private int quantity_sold;
	private String suppliers;
	private String made_in;
	private String images;
	public ProductAdmin() {
		super();
	}
	public ProductAdmin(int id, String productSKU, String name, String color, String size, String menu, double price,
			double cost, double promotional_price, int amount, int quantity_sold, String suppliers, String made_in,
			String images) {
		super();
		this.id = id;
		this.productSKU = productSKU;
		this.name = name;
		this.color = color;
		this.size = size;
		this.menu = menu;
		this.price = price;
		this.cost = cost;
		this.promotional_price = promotional_price;
		this.amount = amount;
		this.quantity_sold = quantity_sold;
		this.suppliers = suppliers;
		this.made_in = made_in;
		this.images = images;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getPromotional_price() {
		return promotional_price;
	}
	public void setPromotional_price(double promotional_price) {
		this.promotional_price = promotional_price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getQuantity_sold() {
		return quantity_sold;
	}
	public void setQuantity_sold(int quantity_sold) {
		this.quantity_sold = quantity_sold;
	}
	public String getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(String suppliers) {
		this.suppliers = suppliers;
	}
	public String getMade_in() {
		return made_in;
	}
	public void setMade_in(String made_in) {
		this.made_in = made_in;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "ProductAdmin [id=" + id + ", productSKU=" + productSKU + ", name=" + name + ", color=" + color
				+ ", size=" + size + ", menu=" + menu + ", price=" + price + ", cost=" + cost + ", promotional_price="
				+ promotional_price + ", amount=" + amount + ", quantity_sold=" + quantity_sold + ", suppliers="
				+ suppliers + ", made_in=" + made_in + ", images=" + images + "]";
	}
	
}
