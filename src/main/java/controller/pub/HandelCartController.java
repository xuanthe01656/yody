package controller.pub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.ProductAdmin;
import model.bean.ProductAdminDetail;
import model.dao.PublicDAO;

public class HandelCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<ProductAdminDetail> listPro = new ArrayList<>();
       
    public HandelCartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		String color = request.getParameter("acolor");
//		String size = request.getParameter("asize");
//		String sku = request.getParameter("asku");
//		int amount = Integer.parseInt(request.getParameter("aamount"));
			if(request.getParameter("amount-edit")!=null&&request.getParameter("id-product")!=null) {
				int product_id = Integer.parseInt(request.getParameter("id-product"));
				int qty = Integer.parseInt(request.getParameter("amount-edit"));
				for (ProductAdminDetail productAdminDetail : listPro) {
					if(productAdminDetail.getId()==product_id) {
						productAdminDetail.setAmount(qty);
					}
				}
				if(listPro.size()>0) {
					session.setAttribute("listPro1", listPro);
					response.getWriter().print("<a class=\"cart-wrap\" href=\""+request.getContextPath()+"/public/cart\" data-target=\"#cart-dropdown\" data-toggle=\"collapse\" aria-expanded=\"true\">\r\n"
							+ "						<img width=\"28\" height=\"28\" src=\"//bizweb.dktcdn.net/100/438/408/themes/904142/assets/icon-cart-header.svg?1681789957699\" alt=\"giỏ hàng\">\r\n"
							+ "						<span class=\"count_item count_item_pr hidden-count\">"+listPro.size()+"</span>\r\n"
							+ "						<span class=\"gio-hang\">GIỎ HÀNG</span>\r\n"
							+ "					</a>\r\n"
							+ "					\r\n"
							+ "					<div id=\"cart-dropdown\" class=\"cart-menu collapse\">\r\n"
							+ "					<div class=\"top-cart-content\">\r\n"
							+ "					<img style=\"position: absolute; top: -25px; left: 50px;\" src=\"//bizweb.dktcdn.net/100/438/408/themes/904142/assets/arrow-cart.png?1681789957699\">\r\n"
							+ "					<div class=\"CartHeaderContainer\">\r\n"
							+ "					</div>\r\n"
							+ "					</div>"
							+ "					<ul>\r\n"
							+ "		                  <li>\r\n"
							+ "		                    <table class=\"table table-striped\">\r\n"
							+ "		                      <tbody>\r\n");
					float sum_price=0;
					for (ProductAdminDetail productAdminDetail : listPro) {
						sum_price =(float) (sum_price+(productAdminDetail.getPrice()*productAdminDetail.getAmount()));
						response.getWriter().print("<tr>\r\n"
								+ "		                          <td class=\"text-center\"><a href=\"#\"><img src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+productAdminDetail.getImages()+"\" alt=\""+productAdminDetail.getName()+"\" title=\""+productAdminDetail.getName()+"\"></a></td>\r\n"
								+ "		                          <td class=\"text-left product-name\"><a href=\"#\">"+productAdminDetail.getName()+"</a>\r\n"
								+ "									<span class=\"text-left price\">Màu/Size: \""+productAdminDetail.getColor()+"\""+productAdminDetail.getSize()+"\"</span>\r\n"
								+ "                                      <input class=\"cart-qty change_amount_cart\" data-id=\""+productAdminDetail.getId()+"\" name=\"product_quantity\" min=\"1\" value=\""+productAdminDetail.getAmount()+"\" type=\"number\">\r\n"
								+ "										 <span class=\"text-left price\">"+(int)(productAdminDetail.getPrice()*productAdminDetail.getAmount())+"đ</span>\r\n"
								+ "		                          </td>\r\n"
								+ "		                          <td class=\"text-center\"><a href=\"javascript: void(0)\" data-value=\""+productAdminDetail.getId()+"\" id=\"del-cart\" class=\"close-cart del-cart\"><i class=\"fa fa-times-circle\"></i></a></td>\r\n"
								+ "		                        </tr>");
					}
					response.getWriter().print("</tr>\r\n"
							+ "		                      </tbody>\r\n"
							+ "		                    </table>\r\n"
							+ "		                  </li>\r\n"
							+ "		                  <li>\r\n"
							+ "		                    <table class=\"table\">\r\n"
							+ "		                      <tbody>\r\n"
							+ "		                        <tr>\r\n"
							+ "		                          <td class=\"text-right\"><strong>Tổng Cộng</strong></td>\r\n"
							+ "		                          <td class=\"text-right\">"+(int)sum_price+"VND</td>\r\n"
							+ "		                        </tr>\r\n"
							+ "		                      </tbody>\r\n"
							+ "		                    </table>\r\n"
							+ "		                  </li>\r\n"
							+ "							<li>\r\n"			
							+ "							<form action=\""+request.getContextPath()+"/public/cart\">\r\n"
							+ "		                      <input class=\"btn mt_10\" value=\"Xem Giỏ Hàng\" type=\"submit\">\r\n"
							+ "		                    </form>\r\n"
							+ "		                    <!--<form action=\""+request.getContextPath()+"/public/checkout\">\r\n"
							+ "		                      <input class=\"btn pull-right mt_10\" value=\"Thanh Toán\" type=\"submit\">\r\n"
							+ "		                    </form>-->\r\n"
							+ "		                  </li>\r\n"
							+ "		                </ul>"
							+ "						<script>\r\n"
							+ "		                	 $(function() {\r\n"
							+ "		 					    $('.del-cart').on('click', function() {\r\n"
							+ "		 					        var changedField = $(this);\r\n"
							+ "		 					        var id = $(this).data(\"value\");\r\n"
							+ "		 					        $.ajax({\r\n"
							+ "		 								url: '"+request.getContextPath()+"/public/handel-cart',\r\n"
							+ "		 								type: 'POST',\r\n"
							+ "		 								cache: false,\r\n"
							+ "		 								data: {aid: id, },\r\n"
							+ "		 								success: function(data){\r\n"
							+ "		 									$('.ajax-cart').html(data);\r\n"
							+ "		 								},\r\n"
							+ "		 								error: function (){\r\n"
							+ "		 									alert('Có lỗi xảy ra');\r\n"
							+ "		 								}\r\n"
							+ "		 							});\r\n"
							+ "		 					        $.ajax({\r\n"
							+ "		 								url: '"+request.getContextPath()+"/public/cart',\r\n"
							+ "		 								type: 'POST',\r\n"
							+ "		 								cache: false,\r\n"
							+ "		 								data: {aid: id, },\r\n"
							+ "		 								success: function(data){\r\n"
							+ "		 									$('.ajax-cart1').html(data);\r\n"
							+ "		 								},\r\n"
							+ "		 								error: function (){\r\n"
							+ "		 									alert('Có lỗi xảy ra');\r\n"
							+ "		 								}\r\n"
							+ "		 							});\r\n"
							+ "		 							return false;\r\n"
							+ "		 					    });\r\n"
							+ "		 					});\r\n"
							+ "						$('.change_amount_cart').on('input', function() {\r\n"
							+ "		 					        var changedField = $(this);\r\n"
							+ "		 					        var qty = $(this).val();\r\n"
							+ "		 					        var id = $(this).data(\"id\");\r\n"
							+ "		 					        \r\n"
							+ "		 					        $.ajax({\r\n"
							+ "		 								url: '"+request.getContextPath()+"/public/handel-cart',\r\n"
							+ "		 								type: 'POST',\r\n"
							+ "		 								cache: false,\r\n"
							+ "		 								data: {\r\n"
							+ "		 									'id-product': id,\r\n"
							+ "		 									'amount-edit':qty,\r\n"
							+ "		 									},\r\n"
							+ "		 								success: function(data){\r\n"
							+ "		 									$('.ajax-cart').html(data);\r\n"
							+ "		 								},\r\n"
							+ "		 								error: function (){\r\n"
							+ "		 									alert('Có lỗi xảy ra');\r\n"
							+ "		 								}\r\n"
							+ "		 							});\r\n"
							+ "									$.ajax({\r\n"
							+ "		 								url: '"+request.getContextPath()+"/public/cart',\r\n"
							+ "		 								type: 'POST',\r\n"
							+ "		 								cache: false,\r\n"
							+ "		 								data: {\r\n"
							+ "		 									'id-product': id,\r\n"
							+ "		 									'amount-edit':qty,},\r\n"
							+ "		 								success: function(data){\r\n"
							+ "		 									$('.ajax-cart1').html(data);\r\n"
							+ "		 								},\r\n"
							+ "		 								error: function (){\r\n"
							+ "		 									alert('Có lỗi xảy ra');\r\n"
							+ "		 								}\r\n"
							+ "		 							});"
							+ "		 							return false;\r\n"
							+ "		 					    });"
							+ "		                	 </script>");
				}
			}
			if(request.getParameter("acolor")!=null&&request.getParameter("asku")!=null&&request.getParameter("asize")!=null&&request.getParameter("aamount")!=null&&request.getParameter("aid")==null) {
				String color = request.getParameter("acolor");
				String size = request.getParameter("asize");
				int amount = Integer.parseInt(request.getParameter("aamount"));
				float sum_price = 0;
				String sku = request.getParameter("asku");
				PublicDAO objPlDAO = new PublicDAO();
				ProductAdminDetail objPro = objPlDAO.getProBySKUColorSize(sku, size, color, amount);
				listPro.add(objPro);
				
				if(listPro.size()>0) {
					session.setAttribute("listPro1", listPro);
					response.getWriter().print("<a class=\"cart-wrap\" href=\""+request.getContextPath()+"/public/cart\" data-target=\"#cart-dropdown\" data-toggle=\"collapse\" aria-expanded=\"true\">\r\n"
							+ "						<img width=\"28\" height=\"28\" src=\"//bizweb.dktcdn.net/100/438/408/themes/904142/assets/icon-cart-header.svg?1681789957699\" alt=\"giỏ hàng\">\r\n"
							+ "						<span class=\"count_item count_item_pr hidden-count\">"+listPro.size()+"</span>\r\n"
							+ "						<span class=\"gio-hang\">GIỎ HÀNG</span>\r\n"
							+ "					</a>\r\n"
							+ "					\r\n"
							+ "					<div id=\"cart-dropdown\" class=\"cart-menu collapse\">\r\n"
							+ "					<div class=\"top-cart-content\">\r\n"
							+ "					<img style=\"position: absolute; top: -25px; left: 50px;\" src=\"//bizweb.dktcdn.net/100/438/408/themes/904142/assets/arrow-cart.png?1681789957699\">\r\n"
							+ "					<div class=\"CartHeaderContainer\">\r\n"
							+ "					</div>\r\n"
							+ "					</div>"
							+ "					<ul>\r\n"
							+ "		                  <li>\r\n"
							+ "		                    <table class=\"table table-striped\">\r\n"
							+ "		                      <tbody>\r\n");
					for (ProductAdminDetail productAdminDetail : listPro) {
						sum_price =(float) (sum_price+(productAdminDetail.getPrice()*productAdminDetail.getAmount()));
						response.getWriter().print("<tr>\r\n"
								+ "		                          <td class=\"text-center\"><a href=\"#\"><img src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+productAdminDetail.getImages()+"\" alt=\""+productAdminDetail.getName()+"\" title=\""+productAdminDetail.getName()+"\"></a></td>\r\n"
								+ "		                          <td class=\"text-left product-name\"><a href=\"#\">"+productAdminDetail.getName()+"</a>\r\n"
								+ "									<span class=\"text-left price\">Màu/Size: \""+productAdminDetail.getColor()+"\""+productAdminDetail.getSize()+"\"</span>\r\n"
								+ "                                      <input class=\"cart-qty change_amount_cart\" data-id=\""+productAdminDetail.getId()+"\" name=\"product_quantity\" min=\"1\" value=\""+productAdminDetail.getAmount()+"\" type=\"number\">\r\n"
								+ "										 <span class=\"text-left price\">"+(int)(productAdminDetail.getPrice()*amount)+"đ</span>\r\n"
								+ "		                          </td>\r\n"
								+ "		                          <td class=\"text-center\"><a href=\"javascript: void(0)\" data-value=\""+productAdminDetail.getId()+"\" id=\"del-cart\" class=\"close-cart del-cart\"><i class=\"fa fa-times-circle\"></i></a></td>\r\n"
								+ "		                        </tr>");
					}
					response.getWriter().print("</tr>\r\n"
							+ "		                      </tbody>\r\n"
							+ "		                    </table>\r\n"
							+ "		                  </li>\r\n"
							+ "		                  <li>\r\n"
							+ "		                    <table class=\"table\">\r\n"
							+ "		                      <tbody>\r\n"
							+ "		                        <tr>\r\n"
							+ "		                          <td class=\"text-right\"><strong>Tổng Cộng</strong></td>\r\n"
							+ "		                          <td class=\"text-right\">"+(int)sum_price+"VND</td>\r\n"
							+ "		                        </tr>\r\n"
							+ "		                      </tbody>\r\n"
							+ "		                    </table>\r\n"
							+ "		                  </li>\r\n"
							+ "							<li>\r\n"			
							+ "							<form action=\""+request.getContextPath()+"/public/cart\">\r\n"
							+ "		                      <input class=\"btn mt_10\" value=\"Xem Giỏ Hàng\" type=\"submit\">\r\n"
							+ "		                    </form>\r\n"
							+ "		                    <!--<form action=\""+request.getContextPath()+"/public/checkout\">\r\n"
							+ "		                      <input class=\"btn pull-right mt_10\" value=\"Thanh Toán\" type=\"submit\">\r\n"
							+ "		                    </form>-->\r\n"
							+ "		                  </li>\r\n"
							+ "		                </ul>"
							+ "						<script>\r\n"
							+ "		                	 $(function() {\r\n"
							+ "		 					    $('.del-cart').on('click', function() {\r\n"
							+ "		 					        var changedField = $(this);\r\n"
							+ "		 					        var id = $(this).data(\"value\");\r\n"
							+ "		 					        $.ajax({\r\n"
							+ "		 								url: '"+request.getContextPath()+"/public/handel-cart',\r\n"
							+ "		 								type: 'POST',\r\n"
							+ "		 								cache: false,\r\n"
							+ "		 								data: {aid: id, },\r\n"
							+ "		 								success: function(data){\r\n"
							+ "		 									$('.ajax-cart').html(data);\r\n"
							+ "		 								},\r\n"
							+ "		 								error: function (){\r\n"
							+ "		 									alert('Có lỗi xảy ra');\r\n"
							+ "		 								}\r\n"
							+ "		 							});\r\n"
							+ "		 					        $.ajax({\r\n"
							+ "		 								url: '"+request.getContextPath()+"/public/cart',\r\n"
							+ "		 								type: 'POST',\r\n"
							+ "		 								cache: false,\r\n"
							+ "		 								data: {aid: id, },\r\n"
							+ "		 								success: function(data){\r\n"
							+ "		 									$('.ajax-cart1').html(data);\r\n"
							+ "		 								},\r\n"
							+ "		 								error: function (){\r\n"
							+ "		 									alert('Có lỗi xảy ra');\r\n"
							+ "		 								}\r\n"
							+ "		 							});\r\n"
							+ "		 							return false;\r\n"
							+ "		 					    });\r\n"
							+ "		 					});\r\n"
							+ "						$('.change_amount_cart').on('input', function() {\r\n"
							+ "		 					        var changedField = $(this);\r\n"
							+ "		 					        var qty = $(this).val();\r\n"
							+ "		 					        var id = $(this).data(\"id\");\r\n"
							+ "		 					        \r\n"
							+ "		 					        $.ajax({\r\n"
							+ "		 								url: '"+request.getContextPath()+"/public/handel-cart',\r\n"
							+ "		 								type: 'POST',\r\n"
							+ "		 								cache: false,\r\n"
							+ "		 								data: {\r\n"
							+ "		 									'id-product': id,\r\n"
							+ "		 									'amount-edit':qty,\r\n"
							+ "		 									},\r\n"
							+ "		 								success: function(data){\r\n"
							+ "		 									$('.ajax-cart').html(data);\r\n"
							+ "		 								},\r\n"
							+ "		 								error: function (){\r\n"
							+ "		 									alert('Có lỗi xảy ra');\r\n"
							+ "		 								}\r\n"
							+ "		 							});\r\n"
							+ "									$.ajax({\r\n"
							+ "		 								url: '"+request.getContextPath()+"/public/cart',\r\n"
							+ "		 								type: 'POST',\r\n"
							+ "		 								cache: false,\r\n"
							+ "		 								data: {\r\n"
							+ "		 									'id-product': id,\r\n"
							+ "		 									'amount-edit':qty,},\r\n"
							+ "		 								success: function(data){\r\n"
							+ "		 									$('.ajax-cart1').html(data);\r\n"
							+ "		 								},\r\n"
							+ "		 								error: function (){\r\n"
							+ "		 									alert('Có lỗi xảy ra');\r\n"
							+ "		 								}\r\n"
							+ "		 							});"
							+ "		 							return false;\r\n"
							+ "		 					    });"
							+ "		                	 </script>");
				}
			}else {
				if(request.getParameter("acolor")==null&&request.getParameter("asku")==null&&request.getParameter("asize")==null&&request.getParameter("aamount")==null&&request.getParameter("aid")!=null) {
					int id = Integer.parseInt(request.getParameter("aid"));
					float sum_price = 0;
					if(listPro.size()>0) {
						for (int i=0; i<listPro.size();i++) {
							if(listPro.get(i).getId()==id) {
								listPro.remove(listPro.get(i));
							}
								if(listPro.size()>0) {
									session.setAttribute("listPro1", listPro);
									response.getWriter().print("<a class=\"cart-wrap\" href=\""+request.getContextPath()+"/public/cart\" data-target=\"#cart-dropdown\" data-toggle=\"collapse\" aria-expanded=\"true\">\r\n"
											+ "						<img width=\"28\" height=\"28\" src=\"//bizweb.dktcdn.net/100/438/408/themes/904142/assets/icon-cart-header.svg?1681789957699\" alt=\"giỏ hàng\">\r\n"
											+ "						<span class=\"count_item count_item_pr hidden-count\">"+listPro.size()+"</span>\r\n"
											+ "						<span class=\"gio-hang\">GIỎ HÀNG</span>\r\n"
											+ "					</a>\r\n"
											+ "					\r\n"
											+ "					<div id=\"cart-dropdown\" class=\"cart-menu collapse\">\r\n"
											+ "					<div class=\"top-cart-content\">\r\n"
											+ "					<img style=\"position: absolute; top: -25px; left: 50px;\" src=\"//bizweb.dktcdn.net/100/438/408/themes/904142/assets/arrow-cart.png?1681789957699\">\r\n"
											+ "					<div class=\"CartHeaderContainer\">\r\n"
											+ "					</div>\r\n"
											+ "					</div>"
											+ "					<ul>\r\n"
											+ "		                  <li>\r\n"
											+ "		                    <table class=\"table table-striped\">\r\n"
											+ "		                      <tbody>\r\n");
									for (ProductAdminDetail productAdminDetail1 : listPro) {
										sum_price = (float) (sum_price+(productAdminDetail1.getPrice()*productAdminDetail1.getAmount()));
										response.getWriter().print("<tr>\r\n"
												+ "		                          <td class=\"text-center\"><a href=\"#\"><img src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+productAdminDetail1.getImages()+"\" alt=\""+productAdminDetail1.getName()+"\" title=\""+productAdminDetail1.getName()+"\"></a></td>\r\n"
												+ "		                          <td class=\"text-left product-name\"><a href=\"#\">"+productAdminDetail1.getName()+"</a>\r\n"
												+ "									<span class=\"text-left price\">Màu/Size: \""+productAdminDetail1.getColor()+"\""+productAdminDetail1.getSize()+"\"</span>\r\n"
												+ "                                      <input class=\"cart-qty change_amount_cart\" data-id=\""+productAdminDetail1.getId()+"\" name=\"product_quantity\" min=\"1\" value=\""+productAdminDetail1.getAmount()+"\" type=\"number\">\r\n"
												+ "										 <span class=\"text-left price\">"+(int)(productAdminDetail1.getPrice()*productAdminDetail1.getAmount())+"đ</span>\r\n"
												+ "		                          </td>\r\n"
												+ "		                          <td class=\"text-center\"><a href=\"javascript: void(0)\" data-value=\""+productAdminDetail1.getId()+"\" id=\"del-cart\" class=\"close-cart del-cart\"><i class=\"fa fa-times-circle\"></i></a></td>\r\n"
												+ "		                        </tr>");
									}
									response.getWriter().print("</tr>\r\n"
											+ "		                      </tbody>\r\n"
											+ "		                    </table>\r\n"
											+ "		                  </li>\r\n"
											+ "		                  <li>\r\n"
											+ "		                    <table class=\"table\">\r\n"
											+ "		                      <tbody>\r\n"
											+ "		                        <tr>\r\n"
											+ "		                          <td class=\"text-right\"><strong>Tổng Cộng</strong></td>\r\n"
											+ "		                          <td class=\"text-right\">"+(int)sum_price+"VND</td>\r\n"
											+ "		                        </tr>\r\n"
											+ "		                      </tbody>\r\n"
											+ "		                    </table>\r\n"
											+ "		                  </li>\r\n"
											+ "							<li>\r\n"
											+ "							<form action=\""+request.getContextPath()+"/public/cart\">\r\n"
											+ "		                      <input class=\"btn mt_10\" value=\"Xem Giỏ Hàng\" type=\"submit\">\r\n"
											+ "		                    </form>\r\n"
											+ "		                    <!--<form action=\""+request.getContextPath()+"/public/checkout\">\r\n"
											+ "		                      <input class=\"btn pull-right mt_10\" value=\"Thanh Toán\" type=\"submit\">\r\n"
											+ "		                    </form>-->\r\n"
											+ "		                  </li>\r\n"
											+ "		                </ul>"
											+ "							<script>\r\n"
											+ "		                	 $(function() {\r\n"
											+ "		 					    $('.del-cart').on('click', function() {\r\n"
											+ "		 					        var changedField = $(this);\r\n"
											+ "		 					        var id = $(this).data(\"value\");\r\n"
											+ "		 					        $.ajax({\r\n"
											+ "		 								url: '"+request.getContextPath()+"/public/handel-cart',\r\n"
											+ "		 								type: 'POST',\r\n"
											+ "		 								cache: false,\r\n"
											+ "		 								data: {aid: id, },\r\n"
											+ "		 								success: function(data){\r\n"
											+ "		 									$('.ajax-cart').html(data);\r\n"
											+ "		 								},\r\n"
											+ "		 								error: function (){\r\n"
											+ "		 									alert('Có lỗi xảy ra');\r\n"
											+ "		 								}\r\n"
											+ "		 							});\r\n"
											+ "		 					        $.ajax({\r\n"
											+ "		 								url: '"+request.getContextPath()+"/public/cart',\r\n"
											+ "		 								type: 'POST',\r\n"
											+ "		 								cache: false,\r\n"
											+ "		 								data: {aid: id, },\r\n"
											+ "		 								success: function(data){\r\n"
											+ "		 									$('.ajax-cart1').html(data);\r\n"
											+ "		 								},\r\n"
											+ "		 								error: function (){\r\n"
											+ "		 									alert('Có lỗi xảy ra');\r\n"
											+ "		 								}\r\n"
											+ "		 							});\r\n"
											+ "		 							return false;\r\n"
											+ "		 					    });\r\n"
											+ "		 					});\r\n"
											+ "							$('.change_amount_cart').on('input', function() {\r\n"
											+ "		 					        var changedField = $(this);\r\n"
											+ "		 					        var qty = $(this).val();\r\n"
											+ "		 					        var id = $(this).data(\"id\");\r\n"
											+ "		 					        \r\n"
											+ "		 					        $.ajax({\r\n"
											+ "		 								url: '"+request.getContextPath()+"/public/handel-cart',\r\n"
											+ "		 								type: 'POST',\r\n"
											+ "		 								cache: false,\r\n"
											+ "		 								data: {\r\n"
											+ "		 									'id-product': id,\r\n"
											+ "		 									'amount-edit':qty,\r\n"
											+ "		 									},\r\n"
											+ "		 								success: function(data){\r\n"
											+ "		 									$('.ajax-cart').html(data);\r\n"
											+ "		 								},\r\n"
											+ "		 								error: function (){\r\n"
											+ "		 									alert('Có lỗi xảy ra');\r\n"
											+ "		 								}\r\n"
											+ "		 							});\r\n"
											+ "									$.ajax({\r\n"
											+ "		 								url: '"+request.getContextPath()+"/public/cart',\r\n"
											+ "		 								type: 'POST',\r\n"
											+ "		 								cache: false,\r\n"
											+ "		 								data: {\r\n"
											+ "		 									'id-product': id,\r\n"
											+ "		 									'amount-edit':qty,},\r\n"
											+ "		 								success: function(data){\r\n"
											+ "		 									$('.ajax-cart1').html(data);\r\n"
											+ "		 								},\r\n"
											+ "		 								error: function (){\r\n"
											+ "		 									alert('Có lỗi xảy ra');\r\n"
											+ "		 								}\r\n"
											+ "		 							});"
											+ "		 							return false;\r\n"
											+ "		 					    });"
											+ "		                	 </script>");
								}else {
									response.getWriter().print("<a class=\"cart-wrap\" href=\""+request.getContextPath()+"/public/cart\" data-target=\"#cart-dropdown\" data-toggle=\"collapse\" aria-expanded=\"true\">\r\n"
											+ "						<img width=\"28\" height=\"28\" src=\"//bizweb.dktcdn.net/100/438/408/themes/904142/assets/icon-cart-header.svg?1681789957699\" alt=\"giỏ hàng\">\r\n"
											+ "							<span class=\"count_item count_item_pr hidden-count\">0</span>\r\n"
											+ "						<span class=\"gio-hang\">GIỎ HÀNG</span>\r\n"
											+ "					</a>"
											+ "					<div id=\"cart-dropdown\" class=\"cart-menu collapse\">\r\n"
											+ "					<div class=\"top-cart-content\">\r\n"
											+ "					<img style=\"position: absolute; top: -25px; left: 50px;\" src=\"//bizweb.dktcdn.net/100/438/408/themes/904142/assets/arrow-cart.png?1681789957699\">\r\n"
											+ "					<div class=\"CartHeaderContainer\">\r\n"
											+ "					</div>\r\n"
											+ "					</div>\r\n"
											+ "		                	 <ul>\r\n"
											+ "		                  <li>\r\n"
											+ "		                    	Không có sản phẩm nào hãy đặt hàng.\r\n"
											+ "		                  </li>\r\n"
											+ "		                  \r\n"
											+ "		                </ul>\r\n"
											+ "		                </div>");
								}
							}
					}else {
						response.getWriter().print("<a class=\"cart-wrap\" href=\""+request.getContextPath()+"/public/cart\" data-target=\"#cart-dropdown\" data-toggle=\"collapse\" aria-expanded=\"true\">\r\n"
								+ "						<img width=\"28\" height=\"28\" src=\"//bizweb.dktcdn.net/100/438/408/themes/904142/assets/icon-cart-header.svg?1681789957699\" alt=\"giỏ hàng\">\r\n"
								+ "							<span class=\"count_item count_item_pr hidden-count\">0</span>\r\n"
								+ "						<span class=\"gio-hang\">GIỎ HÀNG</span>\r\n"
								+ "					</a>"
								+ "					<div id=\"cart-dropdown\" class=\"cart-menu collapse\">\r\n"
								+ "					<div class=\"top-cart-content\">\r\n"
								+ "					<img style=\"position: absolute; top: -25px; left: 50px;\" src=\"//bizweb.dktcdn.net/100/438/408/themes/904142/assets/arrow-cart.png?1681789957699\">\r\n"
								+ "					<div class=\"CartHeaderContainer\">\r\n"
								+ "					</div>\r\n"
								+ "					</div>\r\n"
								+ "		                	 <ul>\r\n"
								+ "		                  <li>\r\n"
								+ "		                    	Không có sản phẩm nào hãy đặt hàng.\r\n"
								+ "		                  </li>\r\n"
								+ "		                  \r\n"
								+ "		                </ul>\r\n"
								+ "		                </div>");
					}
				}
			}
		}
}
