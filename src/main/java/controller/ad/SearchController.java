package controller.ad;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import library.DefineLb;
import library.DefineLb2;
import model.bean.Product;
import model.dao.ProductDao;

public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int dfNum = DefineLb.NUMBER_PER_PAGE;
		int number_page = DefineLb2.NUMBER_PAGE;
		ArrayList<Product> listPro = new ArrayList<>();
		ProductDao objPrDAO = new ProductDao();
		int index = 0;
		String key_search = request.getParameter("key-search");
		int key = 0;
		if(request.getParameter("key")!=null) {
			key=Integer.parseInt(request.getParameter("key"));
			}
			if(request.getParameter("index")==null) {
				index=1;
			}else {
				index= Integer.parseInt(request.getParameter("index"));
			}
		if(key==1) {
			
			String image ="";
			listPro = objPrDAO.getPagingSearch(index, key_search);
			if(listPro.size()>0) {
				response.getWriter().print("<div class=\"table-responsive rounded mb-3\">\r\n"
						+ "                <table class=\"table mb-0\">\r\n"
						+ "                    <thead class=\"bg-white text-uppercase\">\r\n"
						+ "                        <tr class=\"ligth ligth-data\">\r\n"
						+ "                            <th>Product</th>\r\n"
						+ "                            <th>Code</th>\r\n"
						+ "                            <th>Category</th>\r\n"
						+ "                            <th>Cost</th>\r\n"
						+ "                            <th>Price</th>                         \r\n"
						+ "                            <th>Suppliers</th>\r\n"
						+ "                            <th>Made In</th>\r\n"
						+ "                            <th>Quantity</th>\r\n"
						+ "                            <th>Action</th>\r\n"
						+ "                        </tr>\r\n"
						+ "                    </thead>\r\n"
						+ "                    <tbody class=\"ligth-body\">");
				for (Product product : listPro) {
					image = product.getImages();
					String[] listImages = image.split(",");
					response.getWriter().print("<tr>\r\n"
							+ "					<td>\r\n"
							+ "                    <div class=\"d-flex align-items-center\">"
							+ "						<img src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+listImages[0]+"\" class=\"img-fluid rounded avatar-50 mr-3\" alt=\"image\">"
									+ "				 <div>\r\n"
									+ "                                        <a href=\""+request.getContextPath()+"/admin/product-detail?product_code="+product.getProductSKU()+"&index="+index+"\">"+product.getName()+"</a>\r\n"
									+ "                                        <!-- <p class=\"mb-0\"><small>This is test Product</small></p> -->\r\n"
									+ "                                    </div>\r\n"
									+ "                                </div>\r\n"
									+ "                            </td>\r\n"
									+ "                            <td>"+product.getProductSKU()+"</td>\r\n"
									+ "                            <td>"+product.getMenu()+"</td>\r\n"
									+ "                            <td>"+product.getCost()+"</td>\r\n"
									+ "                            <td>"+product.getPrice()+"</td>\r\n"
									+ "                            <td>"+product.getSuppliers()+"</td>\r\n"
									+ "                            <td>"+product.getMade_in() +"</td>\r\n"
									+ "                            <td>"+product.getAmount()+"</td>\r\n"
									+ "                            <td>\r\n"
									+ "                                <div class=\"d-flex align-items-center list-action\">\r\n"
									+ "                                    <a href=\""+request.getContextPath()+"/admin/product-detail?product_code="+product.getProductSKU()+"&index="+index+"\" class=\"badge badge-info mr-2\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\" data-original-title=\"View\"> \r\n"
									+ "                                    	<i class=\"ri-eye-line mr-0\"></i>\r\n"
									+ "                                    </a>\r\n"
									+ "                                </div>\r\n"
									+ "                            </td>\r\n"
									+ "                        </tr>");}
				
				int numPage = objPrDAO.getNumberPageSearch(key_search);
				
				int numcur = number_page + index -1;
				int totalPro = objPrDAO.getNumProSearch(key_search);
				String atc = "active";
				int numPro = 0;
				 	
				if(index<numPage){
					numPro = (index-1)*dfNum+dfNum;
				}else{
					numPro = totalPro;
				}
				
						response.getWriter().print("</tbody>\r\n"
								+ "                </table>\r\n"
								+ "                </div>\r\n"
								+ "                <div class=\"row\">"
								+ "						<div class=\"col-sm-4\">\r\n"
								+ "                                    <div class=\"dataTables_info\" id=\"dataTables-example_info\" style=\"margin:27px 0px;\">Hiển thị từ "+((index-1)*dfNum+1)+" đến "+numPro +" của "+totalPro +" sản phẩm</div>\r\n"
								+ "                                </div>\r\n"
								+ "                                <div class=\"col-sm-8\" style=\"text-align: right; margin:27px 0px\">\r\n"
								+ "                                    <div class=\"dataTables_paginate paging_simple_numbers\" id=\"dataTables-example_paginate\">\r\n"
								+ "                                    \r\n"
								+ "                                        <ul class=\"pagination justify-content-end\">");
						
						if(index>1) {
							response.getWriter().print("<li class=\"page-item previous\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\"><a class=\"page-link index-search\" data-index=\"1\">Về trang 1</a></li>\r\n"
									+ "                                            <li class=\"page-item previous\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\"><a class=\"page-link index-search\" data-index=\""+(index-1)+"\">Trang trước</a></li>");
						}
						if(number_page<numPage ){
                    		if(numcur<=numPage){                                            			
                    			for(int i=index; i<=numcur; i++){
                    				response.getWriter().print("<li class=\"page-item \" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+i+"\" class=\"page-link index-search\" data-index=\""+i+"\">"+i+"</a></li>");
                    			}
                    		}else {
                    			for(int i=index-2; i<=numPage; i++){
                    				response.getWriter().print("<li class=\"page-item \" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+i+"\" class=\"page-link index-search\" data-index=\""+i+"\">"+i+"</a></li>");
                    			}
                    		}
                    	}else{
                			if(number_page>=numPage){
                				for(int i=1; i<=numPage; i++){
                					response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+i+"\" class=\"page-link index-search\" data-index=\""+i+"\">"+i+"</a></li>");
                				}
                			}
                		}
						if(index>=1&&index<numPage){
							response.getWriter().print(" <li class=\"page-item next\" aria-controls=\"dataTables-example\"  tabindex=\"0\" id=\"dataTables-example_next\"><a class=\"page-link index-search\" data-index=\""+(index+1)+"\">Trang tiếp</a></li>\r\n"
									+ "                                            <li class=\"page-item next\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_next\"><a class=\"page-link index-search\" data-index=\""+numPage+"\">Đến trang cuối</a></li>");
						}
						response.getWriter().print("</ul>\r\n"
								+ "                                    </div>\r\n"
								+ "                                </div>\r\n"
								+ "                            </div>"
								+ "<script type=\"text/javascript\">\r\n"
								+ "                            \r\n"
								+ "                    		$('.index-search').on('click', function(){\r\n"
								+ "                    				var key = "+key+";\r\n"
								+ "                        			var key_search = '"+key_search+"';\r\n"
								+ "                        			var index = $(this).data('index');\r\n"
								+ "                        			 $.ajax({\r\n"
								+ "    										url: '"+request.getContextPath()+"/admin/search',\r\n"
								+ "    										type: 'POST',\r\n"
								+ "    										cache: false,\r\n"
								+ "    										data: {\r\n"
								+ "    												key: key,\r\n"
								+ "    												'key-search': key_search,\r\n"
								+ "    												index: index,\r\n"
								+ "    											},\r\n"
								+ "    										success: function(data){\r\n"
								+ "    											$('.ajax-display-search').html(data);\r\n"
								+ "												$('#indexs"+(index)+"').addClass('active')"
								+ "    										},\r\n"
								+ "    										error: function (){\r\n"
								+ "    											alert('Có lỗi xảy ra');\r\n"
								+ "    										}\r\n"
								+ "    									});\r\n"
								+ "                    		});\r\n"
								+ "                    	</script>");
				}
			
				
			
			//request.setAttribute("listPro", listNew);
			request.setAttribute("index", index);
			request.setAttribute("Dfnum", dfNum);
			
		}
		if(key==2) {
			
		}
		if(key==3) {
			
		}
	}

	}
