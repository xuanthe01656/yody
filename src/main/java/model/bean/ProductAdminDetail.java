package model.bean;

public class ProductAdminDetail {
	private int id;
	private String productSKU;
	private String name;
	private String color;
	private String size;
	private int amount;
	private int quantity_sold;
	private double cost;
	private double price;
	private String outstanding_feature;
	private String detail;
	private int discount;
	private String images;
	private String list_images;
	private int view;
	private String category_menu;
	private String category_menu_detail;
	private String category;
	private String suppliers;
	private String country;
	private colur Lcolor;
	private size Lsize;
	private Menu Lmenu;
	private Suppliers Lsuppliers;
	private Country Lmade_in;
	
	
	public ProductAdminDetail() {
		super();
	}


	public ProductAdminDetail(int id, String productSKU, String name, String color, String size, int amount,
			int quantity_sold, double cost, double price, String outstanding_feature, String detail, int discount,
			String images, String list_images, int view, String category_menu, String category_menu_detail,
			String category, String suppliers, String country, colur lcolor, model.bean.size lsize, Menu lmenu,
			Suppliers lsuppliers, Country lmade_in) {
		super();
		this.id = id;
		this.productSKU = productSKU;
		this.name = name;
		this.color = color;
		this.size = size;
		this.amount = amount;
		this.quantity_sold = quantity_sold;
		this.cost = cost;
		this.price = price;
		this.outstanding_feature = outstanding_feature;
		this.detail = detail;
		this.discount = discount;
		this.images = images;
		this.list_images = list_images;
		this.view = view;
		this.category_menu = category_menu;
		this.category_menu_detail = category_menu_detail;
		this.category = category;
		this.suppliers = suppliers;
		this.country = country;
		Lcolor = lcolor;
		Lsize = lsize;
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

	public String getCategory_menu() {
		return category_menu;
	}

	public void setCategory_menu(String category_menu) {
		this.category_menu = category_menu;
	}

	public String getCategory_menu_detail() {
		return category_menu_detail;
	}

	public void setCategory_menu_detail(String category_menu_detail) {
		this.category_menu_detail = category_menu_detail;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(String suppliers) {
		this.suppliers = suppliers;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public colur getLcolor() {
		return Lcolor;
	}

	public void setLcolor(colur lcolor) {
		Lcolor = lcolor;
	}

	public size getLsize() {
		return Lsize;
	}

	public void setLsize(size lsize) {
		Lsize = lsize;
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
		return "ProductAdminDetail [id=" + id + ", productSKU=" + productSKU + ", name=" + name + ", color=" + color
				+ ", size=" + size + ", amount=" + amount + ", quantity_sold=" + quantity_sold + ", cost=" + cost
				+ ", price=" + price + ", outstanding_feature=" + outstanding_feature + ", detail=" + detail
				+ ", discount=" + discount + ", images=" + images + ", list_images=" + list_images + ", view=" + view
				+ ", category_menu=" + category_menu + ", category_menu_detail=" + category_menu_detail + ", category="
				+ category + ", suppliers=" + suppliers + ", country=" + country + ", Lcolor=" + Lcolor + ", Lsize="
				+ Lsize + ", Lmenu=" + Lmenu + ", Lsuppliers=" + Lsuppliers + ", Lmade_in=" + Lmade_in + "]";
	}
	
}
