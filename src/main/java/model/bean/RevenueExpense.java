package model.bean;

public class RevenueExpense {
	private String product_SKU;
	private String name;
	private String images;
	private int amount;
	private int quantity_sold;
	private double cost;
	private double price;
	private double expense;
	private double revenue;
	public RevenueExpense() {
		super();
	}
	public RevenueExpense(String product_SKU, String name, String images, int amount, int quantity_sold, double cost,
			double price, double expense, double revenue) {
		super();
		this.product_SKU = product_SKU;
		this.name = name;
		this.images = images;
		this.amount = amount;
		this.quantity_sold = quantity_sold;
		this.cost = cost;
		this.price = price;
		this.expense = expense;
		this.revenue = revenue;
	}
	public String getProduct_SKU() {
		return product_SKU;
	}
	public void setProduct_SKU(String product_SKU) {
		this.product_SKU = product_SKU;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
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
	public double getExpense() {
		return expense;
	}
	public void setExpense(double expense) {
		this.expense = expense;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	@Override
	public String toString() {
		return "RevenueExpense [product_SKU=" + product_SKU + ", name=" + name + ", images=" + images + ", amount="
				+ amount + ", quantity_sold=" + quantity_sold + ", cost=" + cost + ", price=" + price + ", expense="
				+ expense + ", revenue=" + revenue + "]";
	}
	
}
