package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Country;
import model.bean.Menu;
import model.bean.Suppliers;
import model.bean.productDetail;
import model.dao.CountryDAO;
import model.dao.MenuDAO;
import model.dao.ProductDao;
import model.dao.SuppliersDAO;

import java.io.IOException;
import java.util.ArrayList;

public class HandelProductAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public HandelProductAjaxController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("".equals(request.getParameter("asuk"))==false&& "".equals(request.getParameter("aname"))==false) {
			String id_product = request.getParameter("asuk");
			
			ProductDao objPrDAO = new ProductDao();
			MenuDAO objMDAO = new MenuDAO();
			SuppliersDAO objSpDAO = new SuppliersDAO();
			CountryDAO objCnDAO = new CountryDAO();
			productDetail objPrD = objPrDAO.getObjPr(id_product);
			if(objPrD.getName()!=null) {
				Menu objM = objMDAO.menu(objPrD);		
				Menu objM1 = objMDAO.ChilMenuLV1(objPrD);
				Menu objM2 = objMDAO.ChilMenuLV2(objPrD);	
				Country objCn = objCnDAO.country(objPrD);	
				Suppliers objSp = objSpDAO.suppliers(objPrD);
				response.getWriter().print("<div class=\"col-md-6\">\r\n"
						+ "	                                    <div class=\"form-group\">\r\n"
						+ "	                                        <label>Menu *</label>\r\n"
						+ "	                                        <select id=\"menu\" name=\"menu\" class=\"form-select form-control\" data-style=\"py-0\">\r\n"
						+ "	                                            <option value=\""+objM.getId()+"\">"+objM.getName()+"</option>\r\n"
						+ "	                                        </select>\r\n"
						+ "	                                    </div>\r\n"
						+ "	                                </div>\r\n"
						+ "	                                <div class=\"col-md-6 \">\r\n"
						+ "	                                    <div class=\"form-group\">\r\n"
						+ "	                                        <label>Category Menu*</label>\r\n"
						+ "	                                        <select name=\"categorymenu\" id=\"menu1\" class=\"form-select form-control ajax-data\" data-style=\"py-0\">\r\n"
						+ "	                                            <option value=\""+objM1.getId()+"\">"+objM1.getName()+"</option>\r\n"
						+ "	                                        </select>\r\n"
						+ "	                                    </div>\r\n"
						+ "	                                </div>\r\n"
						+ "	                                <div class=\"col-md-6\">\r\n"
						+ "	                                    <div class=\"form-group\">\r\n"
						+ "	                                        <label>Category*</label>\r\n"
						+ "	                                        <select name=\"category\" class=\"form-select form-control ajax-data1\" data-style=\"py-0\">\r\n"
						+ "	                                            <option value=\""+objM2.getId()+"\">"+objM2.getName()+"</option>\r\n"
						+ "	                                        </select>\r\n"
						+ "	                                    </div>\r\n"
						+ "	                                </div>\r\n"
						+ "	                                <div class=\"col-md-6\">\r\n"
						+ "	                                    <div class=\"form-group\">\r\n"
						+ "	                                        <label>Suppliers *</label>\r\n"
						+ "	                                        <select id=\"name\" name=\"suppliers\" class=\"form-select form-control\" data-style=\"py-0\">\r\n"
						+ "	                                            <option value=\""+objSp.getId()+"\">"+objSp.getName()+"</option>\r\n"
						+ "	                                        </select>\r\n"
						+ "	                                    </div>\r\n"
						+ "	                                </div>\r\n"
						+ "                                \r\n"
						+ "									<div class=\"col-md-12\">"
						+ "                                    <div class=\"form-group\">\r\n"
						+ "                                        <label>Made In *</label>\r\n"
						+ "                                        <select id=\"country\" name=\"country\" class=\"form-select form-control\" data-style=\"py-0\">\r\n"
						+ "                                         <option value=\""+objCn.getId()+"\">"+objCn.getName()+"</option>\r\n"
						+ "                                        </select>\r\n"
						+ "                                    </div>"
						+ "									</div>");
			}else {
					response.getWriter().print("<div class=\"col-md-6\">\r\n"
						+ "	                                    <div class=\"form-group\">\r\n"
						+ "	                                        <label>Menu *</label>\r\n"
						+ "	                                        <select id=\"menu\" name=\"menu\" class=\"form-select form-control\" data-style=\"py-0\">\r\n"
						+ "                                           <option value=\"\">--Chọn Menu--</option>\r\n");
					MenuDAO objM1 = new MenuDAO();
					ArrayList<Menu> listMenu = objM1.getItemMenu();
					if(listMenu.size()>0) {
						for (Menu menu : listMenu) {
							response.getWriter().print( "<option value=\""+menu.getId()+"\">"+menu.getName() +"</option>\r\n");
						}
					};
					response.getWriter().print(  "</select>\r\n"
						+ "	                                    </div>\r\n"
						+ "	                                    <script type=\"text/javascript\">\r\n"
						+ "		                                        $(function() {\r\n"
						+ "		                                        	\r\n"
						+ "		                                		    $('#menu').on('change', function() {\r\n"
						+ "		                                		        var changedField = $(this);\r\n"
						+ "		                                		        var menu = $(\"#menu\").val();       \r\n"
						+ "		                                		        $.ajax({\r\n"
						+ "		                                					url: '/yody/handelMenu',\r\n"
						+ "		                                					type: 'POST',\r\n"
						+ "		                                					cache: false,\r\n"
						+ "		                                					data: {amenu: menu},\r\n"
						+ "		                                					success: function(data){\r\n"
						+ "		                                						$('.ajax-data').html(data);\r\n"
						+ "		                                					},\r\n"
						+ "		                                					error: function (){\r\n"
						+ "		                                						alert('Có lỗi xảy ra');\r\n"
						+ "		                                					},\r\n"
						+ "		                                				});\r\n"
						+ "		                                				return false;\r\n"
						+ "		                                		    });\r\n"
						+ "		                                		});\r\n"
						+ "	                                        </script>\r\n"
						+ "	                                </div>\r\n"
						+ "	                                <div class=\"col-md-6 \">\r\n"
						+ "	                                    <div class=\"form-group\">\r\n"
						+ "	                                        <label>Category Menu*</label>\r\n"
						+ "	                                        <select name=\"categorymenu\" id=\"menu1\" class=\"form-select form-control ajax-data\" data-style=\"py-0\">\r\n"
						+ "	                                        </select>\r\n"
						+ "	                                    </div>\r\n"
						+ "	                                    <script type=\"text/javascript\">\r\n"
						+ "	                                   		 $(function() {\r\n"
						+ "		                                		    $('#menu1').on('change', function() {\r\n"
						+ "		                                		        var changedField = $(this);\r\n"
						+ "		                                		        var menu1 = $(\"#menu1\").val();       \r\n"
						+ "		                                		        $.ajax({\r\n"
						+ "		                                					url: '/yody/handelMenu1',"
						+ "		                                					type: 'POST',\r\n"
						+ "		                                					cache: false,\r\n"
						+ "		                                					data: {amenu1: menu1},\r\n"
						+ "		                                					success: function(data){\r\n"
						+ "		                                						$('.ajax-data1').html(data);\r\n"
						+ "		                                					},\r\n"
						+ "		                                					error: function (){\r\n"
						+ "		                                						alert('Có lỗi xảy ra');\r\n"
						+ "		                                					},\r\n"
						+ "		                                				});\r\n"
						+ "		                                				return false;\r\n"
						+ "		                                		    });\r\n"
						+ "		                                		});\r\n"
						+ "	                                        </script>\r\n"
						+ "	                                </div>\r\n"
						+ "	                                <div class=\"col-md-6\">\r\n"
						+ "	                                    <div class=\"form-group\">\r\n"
						+ "	                                        <label>Category*</label>\r\n"
						+ "	                                        <select name=\"category\" class=\"form-select form-control ajax-data1\" data-style=\"py-0\">\r\n"
						+ "	                                            \r\n"
						+ "	                                        </select>\r\n"
						+ "	                                    </div>\r\n"
						+ "	                                </div>\r\n"
						+ "	                                <div class=\"col-md-6\">\r\n"
						+ "	                                    <div class=\"form-group\">\r\n"
						+ "	                                        <label>Suppliers *</label>\r\n"
						+ "	                                        <select id=\"suppliers\" name=\"suppliers\" class=\"form-select form-control\" data-style=\"py-0\">\r\n"
						+ "	                                            <option value=\"\">--Chọn Suppliers--</option>\r\n");
						                                            	SuppliersDAO objSupDAO=new SuppliersDAO();
						                                            	ArrayList<Suppliers> listSup = objSupDAO.getSup();
						                                            	if(listSup.size()>0){
							                                            	for(Suppliers objSup: listSup){
							                                            		response.getWriter().print("<option value=\""+objSup.getId()+"\">"+objSup.getName()+"</option>\r\n");
							                                            	}
						                                            	};
						                                            	response.getWriter().print("</select>\r\n"
						+ "	                                   										 </div>\r\n"
						+ "	                                											</div>\r\n"
						+ "									<div class=\"col-md-12\">"
						+ "                                    <div class=\"form-group\">\r\n"
						+ "                                        <label>Made In *</label>\r\n"
						+ "                                        <select id=\"country\" name=\"country\" class=\"form-select form-control\" data-style=\"py-0\">\r\n"
						+ "                                        <option value=\"\">--Chọn Country--</option>\r\n");
						                                        	CountryDAO objCtrDAO = new CountryDAO();
						                                        	ArrayList<Country> listCtry = objCtrDAO.getCountry();
						                                       	if(listCtry.size()>0){
						                                       		for(Country country: listCtry){
						                                       			response.getWriter().print("<option value=\""+country.getId()+"\">"+country.getName()+"</option>\r\n");
						                                       			}
						                                       	}
						response.getWriter().print( "</select>\r\n"
						+ "                                    </div>"
						+ "									</div>");
			}
		}
	}
}
