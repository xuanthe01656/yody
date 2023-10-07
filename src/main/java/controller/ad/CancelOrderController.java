package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.RemoveDuplicateElements;
import model.bean.OrderDetail;
import model.bean.Status;
import model.bean.User;
import model.dao.OrdersDAO;
import model.dao.StatusDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CancelOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CancelOrderController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		OrdersDAO orderDAO = new OrdersDAO();
		StatusDAO statusDAO = new StatusDAO();
		ArrayList<Status> list_status = statusDAO.getItem();
    	User objU = (User)session.getAttribute("objU"); 
    	RemoveDuplicateElements remove = new RemoveDuplicateElements();
    	ArrayList<OrderDetail> listOrderByCode = new ArrayList<>();
    	List<String> olist_order_code = new ArrayList<>();
    	List<String> list_order_code = new ArrayList<>();
		if(request.getParameter("order_code")!=null) {
			ArrayList<OrderDetail> listOrderByOrderCode = orderDAO.getListOrderByUser(objU.getId());
			if(listOrderByOrderCode.size()>0) {
				for (OrderDetail orderDetail : listOrderByOrderCode) {
					olist_order_code.add(orderDetail.getOder_code());
				}
			}
			if(olist_order_code.size()>0) {
				list_order_code = remove.remove_duplicate_elements_string_list(olist_order_code);
			}
			String order_code = request.getParameter("order_code");
			int status = 6;
			int result = orderDAO.UpdateStatus(order_code, status);
			if(result>0) {
				for(String string: list_order_code) {
					listOrderByCode = orderDAO.getListOrderByOrderCode(string);
					response.getWriter().print("<div class=\"body-order-body col-lg-12\">\r\n"
							+ "                <div class=\"body_body_main mb-3\">");
					 if(listOrderByCode.size()>0) {
						int total =0;
						int discount = 0;
						int transport = 0;
						total =(int) listOrderByCode.get(0).getTotal();
						transport = (int) listOrderByCode.get(0).getShipping();
						if(listOrderByCode.get(0).getDiscount()>0){
							discount = (int)listOrderByCode.get(0).getDiscount();
						}
						for (OrderDetail objPD: listOrderByCode){
							response.getWriter().print("<div class=\"body_body_head mb-3 col-sm-12 row\">\r\n"
									+ "			             <div class=\"body_body_left col-sm-4\">\r\n"
									+ "			             	<img src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+objPD.getImages()+"\" alt=\""+objPD.getProductName()+"\" title=\""+objPD.getProductName()+"\">\r\n"
									+ "			             </div>\r\n"
									+ "			            <div class=\"body_body_right col-sm-8\">\r\n"
									+ "				            <p class=\"text-left product-name\">"+objPD.getProductName()+"</p>\r\n"
									+ "				            <span class=\"text-left color_size\">"+objPD.getColor() +"/"+objPD.getSize() +"</span>\r\n"
									+ "				            <p class=\"quantity_order\">X"+objPD.getQty()+"</p>\r\n"
									+ "				            <span class=\"text-left price_order\">"+(int)objPD.getPrice()+"đ</span>\r\n"
									+ "				            <span class=\"text-left subtotal_one\"><span class=\"subtotal_two\">Subtotal: </span>"+(int)(objPD.getPrice()*objPD.getQty())+"đ</span>\r\n"
									+ "			            </div>\r\n"
									+ "		            </div>");
						}
						response.getWriter().print("</div>\r\n"
								+ "					       <div class=\"col-sm-12\">\r\n"
								+ "					                    <table class=\"table\">\r\n"
								+ "					                      <tbody>\r\n"
								+ "					                        <tr>\r\n"
								+ "					                        	<td class=\"text-right\">Discount</td>\r\n"
								+ "					                        	<td class=\"text-right\">"+discount+" đ</td>\r\n"
								+ "					                        	<td class=\"text-right\">Transports</td>\r\n"
								+ "					                        	<td class=\"text-right\">"+transport+" đ</td>\r\n"
								+ "					                          <td class=\"text-right\"><strong>Total</strong></td>\r\n"
								+ "					                          <td class=\"text-right\">"+total+" đ</td>\r\n"
								+ "					                        </tr>\r\n"
								+ "					                         <tr>");
						if(listOrderByCode.get(0).getStatus()==1){
							response.getWriter().print("<th class=\"text-center\" colspan=\"6\"><button type=\"button\" value=\""+listOrderByCode.get(0).getOder_code()+"\" class=\"cancel_order\">Hủy Đơn</button></th>");
						}else {
							if(listOrderByCode.get(0).getStatus()==6) {
								response.getWriter().print("<th class=\"text-center\" colspan=\"6\"><button type=\"button\">Đã Hủy</button><br><br><button type=\"button\" value=\""+listOrderByCode.get(0).getOder_code()+"\" class=\"rep_order\">Đặt Lại</button></th>");
							}else {
								response.getWriter().print("<th class=\"text-center\" colspan=\"6\"><button type=\"button\">Đang Vận Chuyển</button></th>");
							}
						}
						response.getWriter().print("</tr>\r\n"
								+ "					<tr>\r\n"
								+ "					    <th class=\"text-center\" colspan=\"6\"><button type=\"button\" id=\"help_order\">Liên hệ chăm sóc khách hàng</button></th>\r\n"
								+ "					 </tr>\r\n"
								+ "				</tbody>\r\n"
								+ "			</table>\r\n"
								+ "		</div>\r\n"
								+ "	 </div>");
					}
				}
				
			}
		}
	}
}
