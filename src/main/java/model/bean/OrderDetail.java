package model.bean;

public class OrderDetail {
		private int id;
		private String oder_code;
		private String fullname;
		private String email;
		private String phone;
		private String address;
	 	private String productName;
	 	private String color;
	 	private String size;
	 	private int qty;
	 	private float price;
	 	private String images;
	    private float subtotal;
	    private float discount;
	    private float shipping;
	    private float tax;
	    private float total;
	    private int order_user;
	    private String order_date;
	    private int status;
		public OrderDetail() {
			super();
		}

		public OrderDetail(int id, String oder_code, String fullname, String email, String phone, String address,
				String productName, String color, String size, int qty,float price, String images, float subtotal, float discount, float shipping,
				float tax, float total, int order_user,String order_date, int status) {
			super();
			this.id = id;
			this.oder_code = oder_code;
			this.fullname = fullname;
			this.email = email;
			this.phone = phone;
			this.address = address;
			this.productName = productName;
			this.color = color;
			this.size = size;
			this.qty = qty;
			this.price = price;
			this.images=images;
			this.subtotal = subtotal;
			this.discount = discount;
			this.shipping = shipping;
			this.tax = tax;
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

		public String getOder_code() {
			return oder_code;
		}

		public void setOder_code(String oder_code) {
			this.oder_code = oder_code;
		}

		public String getFullname() {
			return fullname;
		}

		public void setFullname(String fullname) {
			this.fullname = fullname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getProductName() {
			return productName;
		}
		
		public void setProductName(String productName) {
			this.productName = productName;
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

		public int getQty() {
			return qty;
		}
		
		public void setQty(int qty) {
			this.qty = qty;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		public String getImages() {
			return images;
		}

		public void setImages(String images) {
			this.images = images;
		}

		public float getSubtotal() {
			return subtotal;
		}
		public void setSubtotal(float subtotal) {
			this.subtotal = (int)subtotal;
		}
		
		public float getDiscount() {
			return discount;
		}

		public void setDiscount(float discount) {
			this.discount = discount;
		}

		public float getShipping() {
			return shipping;
		}
		public void setShipping(float shipping) {
			this.shipping = (int)shipping;
		}
		public float getTax() {
			return tax;
		}
		public void setTax(float tax) {
			this.tax = (int)tax;
		}
		public float getTotal() {
			return total;
		}
		public void setTotal(float total) {
			this.total = (int)total;
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
			return "OrderDetail [id=" + id + ", oder_code=" + oder_code + ", fullname=" + fullname + ", email=" + email
					+ ", phone=" + phone + ", address=" + address + ", productName=" + productName + ", color=" + color
					+ ", size=" + size + ", qty=" + qty + ", subtotal=" + subtotal + ", discount=" + discount
					+ ", shipping=" + shipping + ", tax=" + tax + ", total=" + total + ", order_user=" + order_user
					+ ", status=" + status + "]";
		}
		
}
