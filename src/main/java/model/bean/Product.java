package model.bean;

public class Product {
	private int id;
	private String productSKU;
	private String name;
	private String menu;
	private double price;
	private double cost;
	private int amount;
	private String suppliers;
	private String made_in;
	private String images;
	private Menu Lmenu;
	private Suppliers Lsuppliers;
	private Country Lmade_in;
	public Product() {
		super();
	}
	public Product(int id, String productSKU, String name, String menu, double price, double cost, int amount,
			String suppliers, String made_in, String images, Menu lmenu, Suppliers lsuppliers, Country lmade_in) {
		super();
		this.id = id;
		this.productSKU = productSKU;
		this.name = name;
		this.menu = menu;
		this.price = price;
		this.cost = cost;
		this.amount = amount;
		this.suppliers = suppliers;
		this.made_in = made_in;
		this.images = images;
		Lmenu = lmenu;
		Lsuppliers = lsuppliers;
		Lmade_in = lmade_in;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
	public Menu getLmenu() {
		return Lmenu;
	}
	public void setLmenu(Menu lmenu) {
		Lmenu = lmenu;
	}
	public Suppliers getLsuppliers() {
		return Lsuppliers;
	}
	public void setLsuppliers(Suppliers lsuppliers) {
		Lsuppliers = lsuppliers;
	}
	public Country getLmade_in() {
		return Lmade_in;
	}
	public void setLmade_in(Country lmade_in) {
		Lmade_in = lmade_in;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productSKU=" + productSKU + ", name=" + name + ", menu=" + menu + ", price="
				+ price + ", cost=" + cost + ", amount=" + amount + ", suppliers=" + suppliers + ", made_in=" + made_in
				+ ", images=" + images + ", Lmenu=" + Lmenu + ", Lsuppliers=" + Lsuppliers + ", Lmade_in=" + Lmade_in
				+ "]";
	}
	
}
