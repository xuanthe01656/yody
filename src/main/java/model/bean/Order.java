package model.bean;

public class Order {
	private String productName;
    private float subtotal;
    private float discount;
    private float shipping;
    private float tax;
    private float total;
 
    public Order() {
    	super();
    }
    public Order(String productName, String subtotal,String discount,
            String shipping, String tax, String total) {
        this.productName = productName;
        this.subtotal = Float.parseFloat(subtotal);
        this.discount = Float.parseFloat(discount);
        this.shipping = Float.parseFloat(shipping);
        this.tax = Float.parseFloat(tax);
        this.total = Float.parseFloat(total);
    }
 
    public String getProductName() {
        return productName;
    }
 
    public String getSubtotal() {
        return String.format("%.2f", subtotal);
    }
    public String getDiscount() {
        return String.format("%.2f", discount);
    }
    public String getShipping() {
        return String.format("%.2f", shipping);
    }
    
    public String getTax() {
        return String.format("%.2f", tax);
    }
     
    public String getTotal() {
        return String.format("%.2f", total);
    }
}
