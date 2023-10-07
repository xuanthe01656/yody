package controller.pub;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.OrderDetail;
import model.bean.User;
import model.dao.OrdersDAO;
import model.dao.UserDAO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdatePersonalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public boolean equalsEmail(String email, ArrayList<User> listUs) {
    	for (User objUs : listUs) {
			if(email.equals(objUs.getEmail())) {
				return true;
			}
		}
    	return false;
    	
    }
    public UpdatePersonalController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		int id =0;
		if(request.getParameter("aid")!=null) {
			id=Integer.parseInt(request.getParameter("aid"));
		}
		UpdatePersonalController objUpdate  = new UpdatePersonalController();
		UserDAO objUsDAO = new UserDAO();
		User objUsById = objUsDAO.getUserByID(id);
		ArrayList<User> listUsUnById = objUsDAO.getListUs(id);
		User objUs = new User();
		if(request.getParameter("oid")!=null) {
			ArrayList<OrderDetail> listOrderByCode = new ArrayList<>();
			OrdersDAO objOdDAO = new OrdersDAO();
			int oid = Integer.parseInt(request.getParameter("oid"));
			ArrayList<OrderDetail> listOrderByOrderCode = objOdDAO.getListOrderByUser(oid);
			List<String> list_order_code = new ArrayList<>();
			if(listOrderByOrderCode.size()>0) {
				for (OrderDetail objPD : listOrderByOrderCode) {
					list_order_code.add(objPD.getOder_code());
				}
				if(list_order_code.size()>0) {
						for(String string: list_order_code) {
							listOrderByCode = objOdDAO.getListOrderByOrderCode(string);
							if(listOrderByCode.size()>0) {
								int total =0;
					              int discount = 0;
					              int transport = 0;
								total =(int) listOrderByCode.get(0).getTotal();
								transport = (int) listOrderByCode.get(0).getShipping();
								if(listOrderByCode.get(0).getDiscount()>0){
								discount = (int)listOrderByCode.get(0).getDiscount();
								response.getWriter().print("<div class=\"body-order-body col-lg-12\">\r\n"
										+ "                <div class=\"body_body_main mb-3\">");
								for(OrderDetail objPD :listOrderByCode ) {
									response.getWriter().print("<div class=\"body_body_head mb-3 col-sm-12 row\">\r\n"
											+ "			             <div class=\"body_body_left col-sm-4\">\r\n"
											+ "			             	<img src=\""+request.getContextPath() +"/templates/admin/assets/images/table/product/"+objPD.getImages()+"\" alt=\""+objPD.getProductName()+"\" title=\""+objPD.getProductName()+"\">\r\n"
											+ "			             </div>\r\n"
											+ "			            <div class=\"body_body_right col-sm-8\">\r\n"
											+ "				            <p class=\"text-left product-name\">"+objPD.getProductName()+"</p>\r\n"
											+ "				            <span class=\"text-left color_size\">"+objPD.getColor() +"/"+objPD.getSize()+"</span>\r\n"
											+ "				            <p class=\"quantity_order\">X"+objPD.getQty()+"</p>\r\n"
											+ "				            <span class=\"text-left price_order\">"+(int)objPD.getPrice()+"đ</span>\r\n"
											+ "				            <span class=\"text-left subtotal_one\"><span class=\"subtotal_two\">Subtotal: </span>"+(int)objPD.getPrice()+"đ</span>\r\n"
											+ "			            </div>\r\n"
											+ "		            </div>");
								}
								response.getWriter().print("</div>\r\n"
										+ "		       <div class=\"col-sm-12\">\r\n"
										+ "		                    <table class=\"table\">\r\n"
										+ "		                      <tbody>\r\n"
										+ "		                        <tr>\r\n"
										+ "		                        	<td class=\"text-right\">Discount</td>\r\n"
										+ "		                        	<td class=\"text-right\">"+discount+" đ</td>\r\n"
										+ "		                        	<td class=\"text-right\">Transports</td>\r\n"
										+ "		                        	<td class=\"text-right\">"+transport+" đ</td>\r\n"
										+ "		                          <td class=\"text-right\"><strong>Total</strong></td>\r\n"
										+ "		                          <td class=\"text-right\">"+total+"> đ</td>\r\n"
										+ "		                        </tr>\r\n"
										+ "		                         <tr>\r\n"
										+ "		                        	<th class=\"text-center\" colspan=\"6\"><button type=\"button\" id=\"confirm_order\">Confirm Order</button></th>\r\n"
										+ "		                        </tr>\r\n"
										+ "		                      </tbody>\r\n"
										+ "		                </table>\r\n"
										+ "                </div>\r\n"
										+ "        	</div>");
							}
						}else {
							response.getWriter().print("<p class=\"text-left product-name\">Không có đơn hàng</p>\r\n");
						}
					}
				}else {
					response.getWriter().print("<p class=\"text-left product-name\">Không có đơn hàng</p>\r\n");
				}
			}else {
				response.getWriter().print("<p class=\"text-left product-name\">Không có đơn hàng</p>\r\n");
			}
		}
		if(request.getParameter("email")!=null&&request.getParameter("aname")!=null&&request.getParameter("aphone")!=null) {
			String name = request.getParameter("aname");
			String email = request.getParameter("email");
			String phone = request.getParameter("aphone");
			String update_date = sdf.format(date);
			String update_at = objUsById.getUpdate_at()+","+request.getParameter("aid");
			objUs = new User(id, name,"", email, phone, "", "", "", update_date, update_at, 0);
			int result = objUsDAO.updateInfo(id, objUs);
			if(result>0) {
				User objUserNew = objUsDAO.getUserByID(id);
				response.getWriter().print("<div class=\"col-md-12\"><script>alert('Thành công!');</script></div>\r\n"
						+ "<p><strong>Họ và tên:</strong>"+objUserNew.getFullname()+"</p>\r\n"
						+ "			<p><strong>Địa chỉ email:</strong> "+objUserNew.getEmail()+"</p>");
			}else {
				response.getWriter().print("<div class=\"col-md-12\"><p style=\"color: red\">Cập nhật thông tin thất bại</p></div>"
						+ "<p><strong>Họ và tên:</strong> "+objUsById.getFullname()+"</p>\r\n"
						+ "			<p><strong>Địa chỉ email:</strong> "+objUsById.getEmail()+"</p>");
			}
		}
		if(request.getParameter("password")!=null&&request.getParameter("anew_password")!=null) {
			String password = request.getParameter("password");
			String new_password = request.getParameter("anew_password");
			String update_date = sdf.format(date);
			String update_at = objUsById.getUpdate_at()+","+request.getParameter("aid");
			if(password.equals(objUsById.getPassword())==true) {
				objUs = new User(id, "",new_password, "", "", "", "", "", update_date, update_at, 0);
				int result = objUsDAO.updatePassword(id, objUs);
				if(result>0) {
					response.getWriter().print("<script>alert('Thành công!');</script>");
				}else {
					response.getWriter().print("<p style=\"color: red\">Cập nhật password thất bại.</p>");
				}
			}else {
				response.getWriter().print("<p style=\"color: red\">Cập nhật password thất bại do mật khẩu cũ không chính xác.</p>");
			}
		}
		if(request.getParameter("aemail")!=null) {
			String email = request.getParameter("aemail");
			if(objUpdate.equalsEmail(email, listUsUnById)) {
				response.getWriter().print("<p style=\"color: red\">Email đã tồn tại</p>");
			}else {
				response.getWriter().print("<p style=\"color: green\">Email được phép dùng</p>");
			}
		}
		if(request.getParameter("aold_password")!=null) {
			String password = request.getParameter("aold_password");
			if(password.equals(objUsById.getPassword())==false) {
				response.getWriter().print("<p style=\"color: red\">Password chưa chính xác. Kiểm tra lại.</p>");
			}
		}
	}

}
