package controller.pub;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.ExportPDF;
import library.RandomLb;
import model.bean.Discount;
import model.bean.Order;
import model.bean.OrderDate;
import model.bean.OrderDetail;
import model.bean.Orders;
import model.bean.ProductAdminDetail;
import model.bean.User;
import model.dao.DiscountDAO;
import model.dao.OrdersDAO;

public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public OrderController() {
        super();
    }
    public float convert_to_usd(float vnd) {
    	float usd = vnd/24000; 
    	return usd;
    }
    public boolean existDiscount(ArrayList<Discount> listDiscount, String discount_code) {
    	for (Discount discount : listDiscount) {
			if(discount.getDiscount_code().equals(discount_code)) {
				return true;
			}
		}
    	return false;
    }
    public boolean existMenuIdDiscount(List<Integer> listMenuId, Discount discount) {
    	for (Integer integer : listMenuId) {
			if(integer==discount.getCategory_id()) {
				return true;
			}
		}
    	return false;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dayNow = sdf1.format(date);
		OrderController objOrderController = new OrderController();
		DiscountDAO objDiscountDAO = new DiscountDAO();
		OrdersDAO objOrderDAO = new OrdersDAO();
		ArrayList<ProductAdminDetail> listPro = (ArrayList<ProductAdminDetail>) session.getAttribute("listPro1");
		User objUs = new User();
		Discount objDiscount = new Discount();
		int i = 1;
		int user_id = 0;
		String email = "";
		if(session.getAttribute("objU")!=null) {
			objUs =(User) session.getAttribute("objU");
			user_id = objUs.getId();
			email = objUs.getEmail();
		}else {
			user_id = 0;
			email = "";
		}
		String payment = request.getParameter("payment");
		String transports = "";
		String discounts = "0";
		String taxs="0";
		String product_name="";
		String fullname = request.getParameter("fullname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String province = request.getParameter("province");
		String districts = request.getParameter("districts");
		String wards = request.getParameter("wards");
		if(request.getParameter("aprovince")!=null) {
			String aprovince = request.getParameter("aprovince");
			if("Đà Nẵng".equals(aprovince)) {
				response.getWriter().print("<div class=\"form-check check-form form-control field__input\" id=\"in\">\r\n"
						+ "											<div class=\"radio__input\">\r\n"
						+ "												<input class=\"form-check-input\" value=\"15000\" type=\"radio\" name=\"transport\" id=\"input_transport_fee\" checked>\r\n"
						+ "											</div>\r\n"
						+ "	  										<label class=\"form-check-label\" for=\"transport-in\">\r\n"
						+ "	    										Phí vận chuyển\r\n"
						+ "	  										</label>\r\n"
						+ "	  		  								<label class=\"form-check-label\" for=\"transport-in\">\r\n"
						+ "	  		    								15000đ\r\n"
						+ "	  		  								</label>\r\n"
						+ "										</div>");
			}else {
				response.getWriter().print("<div class=\"form-check check-form form-control field__input\" id=\"out\">\r\n"
						+ "											<div class=\"radio__input\">\r\n"
						+ "												<input class=\"form-check-input\" value=\"20000\" type=\"radio\" name=\"transport\" id=\"input_transport_fee\" checked>\r\n"
						+ "											</div>\r\n"
						+ "	  										<label class=\"form-check-label\" for=\"transport-out\">\r\n"
						+ "	    										Phí vận chuyển\r\n"
						+ "	  										</label>\r\n"
						+ "	  										<label class=\"form-check-label\" for=\"transport-out\">\r\n"
						+ "	    										20000đ\r\n"
						+ "	  										</label>\r\n"
						+ "										</div>");
			}
		}
		if(request.getParameter("adiscount_code")!=null) {
			String discount_code = request.getParameter("adiscount_code");
			ArrayList<Discount> listDiscount = objDiscountDAO.getListItem();
			if(objOrderController.existDiscount(listDiscount, discount_code)) {
				objDiscount = objDiscountDAO.getItemByCode(discount_code);
				List<Integer> listMenuId = new ArrayList<>();
				for(ProductAdminDetail objPro1: listPro) {
					listMenuId.add(objPro1.getLmenu().getId());
				}
				Set<Integer> set = new HashSet<Integer>(listMenuId);
		        List<Integer> newlistMenuId = new ArrayList<>(set);
		        if(objDiscount.getCategory_id()==0) {
		        	response.getWriter().print("<p style=\"color: green; font-style: italic;\">Mã giảm giá được phép dùng.</p>\r\n"
		        			+ "					<script>\r\n"
		        			+ "						$(document).ready(function() {"
		        			+ "							$('#apply_discount').prop('disabled', false);\r\n"
		        			+ "						});"
		        			+ "					</script>");
		        }else {
		        	if(objOrderController.existMenuIdDiscount(newlistMenuId, objDiscount)) {
		        		response.getWriter().print("<p style=\"color: green; font-style: italic;\">Mã giảm giá được phép dùng.</p>\r\n"
			        			+ "					<script>\r\n"
			        			+ "						$(document).ready(function() {"
			        			+ "							$('#apply_discount').prop('disabled', false);\r\n"
			        			+ "						});"
			        			+ "					</script>");
		        	}else {
		        		if(objOrderController.existMenuIdDiscount(newlistMenuId, objDiscount)==false) {
		        			response.getWriter().print("<p style=\"color: red; font-style: italic;\">Mã giảm giá không được áp dụng cho đơn hàng này.</p>\r\n"
				        			+ "					<script>\r\n"
				        			+ "						$(document).ready(function() {"
				        			+ "							$('#apply_discount').prop('disabled', true);\r\n"
				        			+ "						});"
				        			+ "					</script>");
		        		}
		        	}
		        }
			}else {
	        	response.getWriter().print("<p style=\"color: red; font-style: italic;\">Mã giảm giá không tồn tại.</p>\r\n"
	        			+ "					<script>\r\n"
	        			+ "						$(document).ready(function() {"
	        			+ "							$('#apply_discount').prop('disabled', true);\r\n"
	        			+ "						});"
	        			+ "					</script>");
	        }
		}
		if(request.getParameter("discount_code_true")!=null) {
			String discount_code = request.getParameter("discount_code_true");
			objDiscount = objDiscountDAO.getItemByCodeNotMeuId(discount_code);
			String date_end = objDiscount.getDate_end();
			String new_date_end = date_end.substring(10);
			Date date1 = new Date();
			Date date2 = new Date();
			try {
				date1 = sdf1.parse(dayNow);
				date2 = sdf1.parse(new_date_end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(date2.compareTo(date1)>=0) {
				
				response.getWriter().print("<div id=\"discount_line\">"+objDiscount.getReduced_price()+" đ</div>\r\n"
						+ "			<script type=\"text/javascript\">\r\n"
						+ "			$(document).ready(function() {\r\n"
						+ "				var transportfee = parseInt($('#transport-fee').text());\r\n"
						+ "				var price = parseInt($('#sum-price').text());\r\n"
						+ "				var discount = parseInt($('#discount_line').text());\r\n"
						+ "				var sum_price = transportfee+price-discount;\r\n"
						+ "				$('#total').html(sum_price);\r\n"
						+ "			});\r\n"
						+ "		</script>");
			}else {
				if(date2.compareTo(date1)<0) {
					response.getWriter().print("<div id=\"discount_line\">0 đ</div>\r\n"
							+ "			<script type=\"text/javascript\">\r\n"
							+ "			$(document).ready(function() {\r\n"
							+ "				alert('Mã giảm giá đã hết hạn sử dụng hãy dùng một mã khác.');"
							+ "				var transportfee = parseInt($('#transport-fee').text());\r\n"
							+ "				var price = parseInt($('#sum-price').text());\r\n"
							+ "				var discount = parseInt($('#discount_line').text());\r\n"
							+ "				var sum_price = transportfee+price-discount;\r\n"
							+ "				$('#total').html(sum_price);\r\n"
							+ "			});\r\n"
							+ "		</script>");
				}
			}
		}
		if("paypal".equals(payment)) {
			if(request.getParameter("discount")!=null) {
				String discounts_code = request.getParameter("discount");
				objDiscount = objDiscountDAO.getItemByCodeNotMeuId(discounts_code);
				discounts =String.valueOf(objDiscount.getReduced_price()); 
			}else {
				discounts = "0";
			}
			
			if("Đà Nẵng".equals(province)) {
				transports = request.getParameter("transport");
			}else {
				transports = request.getParameter("transport");
			}
			float discount = Float.parseFloat(discounts);
			float transport = Float.parseFloat(transports)-discount;
			float tax = Float.parseFloat(taxs);
			float transport_usd = objOrderController.convert_to_usd(transport);
			float discount_usd = objOrderController.convert_to_usd(discount);
			float tax_usd = objOrderController.convert_to_usd(tax);
			float subtotal = 0;
			
			for (ProductAdminDetail productAdminDetail : listPro) {
				subtotal = (float) ((subtotal)+(productAdminDetail.getAmount()*productAdminDetail.getPrice()));
				product_name =product_name+i+". "+ productAdminDetail.getName()+". ";
				i++;
			}
			float subtotal_usd = objOrderController.convert_to_usd(subtotal);
			float total = subtotal+tax+transport;
			float total_usd = objOrderController.convert_to_usd(total);
			Order objOder = new Order(product_name, String.valueOf(subtotal_usd), String.valueOf(discount_usd), String.valueOf(transport_usd), String.valueOf(tax_usd), String.valueOf(total_usd));
			request.setAttribute("objOrder", objOder);
			RequestDispatcher rd = request.getRequestDispatcher("/paypal/checkout.jsp");
			rd.forward(request, response);
		}
		if("COD".equals(payment)) {
			int numberOfCharactor = 8;
			RandomLb random = new RandomLb();
			String order_code = random.randomAlphaNumericUpperCase(numberOfCharactor);
			String address_shipping = fullname+"-"+phone+"-"+address+"-"+wards+"-"+districts+"-"+province;
			if(request.getParameter("discount")!=null) {
				String discounts_code = request.getParameter("discount");
				objDiscount = objDiscountDAO.getItemByCodeNotMeuId(discounts_code);
				discounts =String.valueOf(objDiscount.getReduced_price()); 
			}else {
				discounts = "0";
			}
			
			if("Đà Nẵng".equals(province)) {
				transports = request.getParameter("transport");
			}else {
				transports = request.getParameter("transport");
			}
			float discount = Float.parseFloat(discounts);
			float transport = Float.parseFloat(transports);
			float tax = Float.parseFloat(taxs);
			float subtotal = 0;
			float subtotalByOne=0;
			String product_id="";
			int qty=0;
			for (ProductAdminDetail productAdminDetail : listPro) {
				subtotal = (float) ((subtotal)+(productAdminDetail.getAmount()*productAdminDetail.getPrice()));
				product_name =product_name+i+". "+ productAdminDetail.getName()+"-"+productAdminDetail.getColor()+"-"+productAdminDetail.getSize()+"-"+productAdminDetail.getAmount()+"/";
				product_id = product_id+","+productAdminDetail.getId();
				qty = qty+productAdminDetail.getAmount();
				i++;
				
			}
			float total = subtotal+tax+transport-discount;
			String product_detail=product_name;
			
			Orders orders = new Orders(0, order_code, product_id, product_detail, qty, total, user_id,"", 0);
			int result = objOrderDAO.addOrders(orders);
			int result1 = 0;
			for(ProductAdminDetail objOrders : listPro) {
				subtotalByOne =(float) (objOrders.getAmount()*objOrders.getPrice());
				result1 =objOrderDAO.addOrderss(objOrders, order_code,fullname,email, phone, address_shipping,subtotalByOne, discount, transport, tax, total,user_id );
			}
			if(result>0&&result1>0) {
//				ArrayList<OrderDetail> listOrder = new ArrayList<OrderDetail>();
//				listOrder = objOrderDAO.getListOrderByOrderCode(order_code);
//				ExportPDF pdfFileExporter = new ExportPDF();
//
//		        Map<String, Object> data = createTestData(listOrder);
//
//		        String pdfFileName = "E:\\SimpleSolution\\order.pdf";
//		        pdfFileExporter.exportPdfFile("order-template", data, pdfFileName);
				listPro.removeAll(listPro);
				session.removeAttribute("listPro1");
				
				response.sendRedirect(request.getContextPath()+"/public/cart?msg=1&order="+order_code+"");
			}else {
				response.sendRedirect(request.getContextPath()+"/public/checkout-page?msg=2");
			}
		}
	}
//	private static Map<String, Object> createTestData(ArrayList<OrderDetail> orderItems) {
//        Map<String, Object> data = new HashMap<>();
//
//        OrderDate order = new OrderDate();
//        order.setOrderNo(orderItems.get(0).getOder_code());
//        order.setDate(new Date());
//        order.setOrderDate(new Date());
//        order.setRequestDate(new Date());
//        data.put("order", order);
//
//        data.put("orderItems", orderItems);
//
//        return data;
//    }
}
