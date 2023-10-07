package controller.pub;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.DefineLb;
import library.DefineLb2;
import library.RemoveDuplicateElements;
import model.bean.Menu;
import model.bean.ProductAdmin;
import model.dao.PublicDAO;

public class FilterProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Integer> list_size_id = new ArrayList<Integer>();
	private List<Integer> list_color_id = new ArrayList<Integer>();
	
    public FilterProductController() {
        super();
    }
    public boolean existId(List<Integer> list_id, int id) {
    	for (Integer object : list_id) {
			if(object==id) {
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
		PublicDAO objPDAO = new PublicDAO();
		RemoveDuplicateElements remove = new RemoveDuplicateElements();
		List<Integer> menu_id=new ArrayList<>();
		int index = 0;
		if(request.getParameter("index")==null) {
			index=1;
		}else {
			index= Integer.parseInt(request.getParameter("index"));
		}
		
		if(request.getParameter("amenu_id")!=null) {
			if(menu_id.size()>0) {
				for (Integer integer : menu_id) {
					if(integer!=Integer.parseInt(request.getParameter("amenu_id"))) {
						menu_id.add(Integer.parseInt(request.getParameter("amenu_id")));
					}
				}
			}else {
				menu_id.add(Integer.parseInt(request.getParameter("amenu_id")));
			}
		}
		if(request.getParameter("amenu")!=null) {
			String menu = request.getParameter("amenu");
			ArrayList<Menu> objMenu = objPDAO.getItemByNameMenu(menu);
			for (Menu menu2 : objMenu) {
				menu_id.add(menu2.getId());
			}
		}
		
		if(request.getParameter("alistmenu_id")!=null) {
			String menuid=request.getParameter("alistmenu_id");
			String newMenuid = menuid.substring(1).replace("]", "").replace(" ", "");
			List<String> listmenuid = new ArrayList<String>(Arrays.asList(newMenuid.split(",")));
			menu_id = listmenuid.stream()
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
		}
		
		int total_num_page=0;
		int  getpronum = 0;
		String images = "";
    	String color = "";
		int dfNum = DefineLb.NUMBER_PER_PAGE;
		
		int number_page = DefineLb2.NUMBER_PAGE;
		int numcur = number_page + index-1;
		if("".equals(request.getParameter("dsize_id"))==true&&"".equals(request.getParameter("dcolor_id"))==true) {
			if(list_size_id.size()>0) {
				list_size_id.removeAll(list_size_id);
			}
			if(list_color_id.size()>0) {
				list_color_id.removeAll(list_color_id);
			}
		}
		
		if(request.getParameter("asize_id")!=null) {
			int size_id = Integer.parseInt(request.getParameter("asize_id"));
			list_size_id.add(size_id);
		}
		
		if(request.getParameter("acolor_id")!=null) {
			int color_id = Integer.parseInt(request.getParameter("acolor_id"));
			list_color_id.add(color_id);
		}
		if(request.getParameter("usid")!=null) {
			int usid = Integer.parseInt(request.getParameter("usid"));
			for(int i =0; i<list_size_id.size(); i++) {
				if(list_size_id.get(i)==usid) {
					list_size_id.remove(list_size_id.get(i));
				}
			}
		}
		if(request.getParameter("ucid")!=null) {
			int ucid = Integer.parseInt(request.getParameter("ucid"));
			for(int i =0; i<list_color_id.size(); i++) {
				if(list_color_id.get(i)==ucid) {
					list_color_id.remove(list_color_id.get(i));
				}
			}
		}
		
		if(request.getParameter("size_list")!=null&&"".equals(request.getParameter("size_list"))==false) {
			String size_list = request.getParameter("size_list");
			List<String >list_size_id1 = new ArrayList<String>(Arrays.asList(size_list.split(",")));
			list_size_id = list_size_id1.stream()
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
		}
		if(request.getParameter("color_list")!=null&&"".equals(request.getParameter("color_list"))==false) {
			String color_list = request.getParameter("color_list");
			List<String >list_color_id1 = new ArrayList<String>(Arrays.asList(color_list.split(",")));
			list_color_id = list_color_id1.stream()
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
		}
		if(list_size_id.size()>0&&list_color_id.size()>0) {
			ArrayList<ProductAdmin> listProAd = new ArrayList<>();
			for(int i=0; i<list_size_id.size();i++) {
				for(int j=0; j<list_color_id.size();j++) {
					for(int k=0; k<menu_id.size();k++) {
						ArrayList<ProductAdmin> listProByColorSize = objPDAO.getItemByColorSize(menu_id.get(k),list_size_id.get(i), list_color_id.get(j));
						if(listProByColorSize.size()>0) {
							listProAd.addAll(listProByColorSize);
						}
					}
				}
			}
			total_num_page = listProAd.size()/DefineLb.NUMBER_PER_PAGE;
			if(listProAd.size()%DefineLb.NUMBER_PER_PAGE!=0) {
				total_num_page++;
			}
			int numPage = total_num_page;
			int totalPro = listProAd.size();
			int numPro = 0;
			if(index<numPage){
				numPro = (index-1)*dfNum+dfNum;
			}else{
				numPro = totalPro;
			}
			if(listProAd.size()>DefineLb.NUMBER_PER_PAGE&&listProAd.size()<DefineLb.NUMBER_PER_PAGE*index) {
				getpronum = listProAd.size();
			}else {
				if(listProAd.size()<DefineLb.NUMBER_PER_PAGE) {
					getpronum = listProAd.size();
				}else {
					getpronum = DefineLb.NUMBER_PER_PAGE*index;
				}
			}
			
			if(listProAd.size()>0) {
			session.setAttribute("index", index);
			response.getWriter().print("<div class=\"row row1\">");
			for(int i = ((index-1)*DefineLb.NUMBER_PER_PAGE); i<getpronum; i++) {
				images = listProAd.get(i).getImages();
				color = listProAd.get(i).getColor();
				response.getWriter().print("<div class=\"col-sm-3 col-lg-3 mb_20\">\r\n"
						+ "                      <div class=\"image product-imageblock \">\r\n"
						+ "      					<a href=\""+request.getContextPath()+"/public/product-detail?sku="+listProAd.get(i).getProductSKU()+"\">");
				if(images!=null){
          			String[] image_list = images.split(",");
    					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
    					File image = new File(path+image_list[0]);
    					if(image.exists()){
    						response.getWriter().print("<img id=\"img-des"+listProAd.get(i).getProductSKU()+"\" data-name=\"product_image\" src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+image_list[0]+"\" alt=\""+listProAd.get(i).getName()+"\" title=\""+listProAd.get(i).getName()+"\" style=\"width: 220px; height: 293px;\" class=\"img-responsive\">");
    					}
    			}
				response.getWriter().print("</a>\r\n"
						+ "                                \r\n"
						+ "                              </div>\r\n"
						+ "                              <div class=\"caption product-detail text-center\">\r\n"
						+ "                                <h6 data-name=\"product_name\" class=\"product-name\"><a href=\""+request.getContextPath()+"/public/product-detail?sku="+listProAd.get(i).getProductSKU()+"\" title=\"Casual Shirt With Ruffle Hem\">"+listProAd.get(i).getName()+"</a></h6>\r\n"
						+ "                                <span class=\"price\"><span class=\"amount\"><span class=\"currencySymbol\">"+(int)listProAd.get(i).getPrice()+"</span>đ</span>\r\n"
						+ "                                </span>\r\n"
						+ "                              </div>\r\n"
						+ "                              <div class=\"row\">");
				if(images!=null&&color!=null){
         			String[] oimage_list = images.split(",");
         			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
         			String[] ocolor_list = color.split(",");
         			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
   						for(int j=0; j<image_list.length; j++){
   					File image = new File(path+image_list[j]);
   					if(image.exists()){
   						response.getWriter().print("<div class=\"col-lg-3\" style=\"padding-right: 0px; width: 40px;\">\r\n"
   								+ "                                 			<img id=\"img-icon"+(listProAd.get(i).getProductSKU()+j)+"\" src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+image_list[j]+"\" class=\"img-fluid rounded avatar-50 mr-3 img-icon\" style=\"width: 30px; height: 30px; border-radius: 30px;\" alt=\"image\" title=\""+color_list[j]+"\">\r\n"
   								+ "                                 		</div>\r\n"
   								+ "                                 		<script type=\"text/javascript\">\r\n"
   								+ "									    	$( \"#img-icon"+(listProAd.get(i).getProductSKU()+j)+"\" ).click(function() {\r\n"
   								+ "									    		let src_icon = $(\"#img-icon"+(listProAd.get(i).getProductSKU()+j)+"\").attr(\"src\");\r\n"
   								+ "									    		let src_des = $(\"#img-des"+listProAd.get(i).getProductSKU()+"\").attr(\"src\");\r\n"
   								+ "										    	let path = src_icon.substring(0,src_icon.lastIndexOf('/'));	\r\n"
   								+ "										    	let path1 = src_des.substring(0,src_des.lastIndexOf('/'));	\r\n"
   								+ "										    	let filename = src_icon.split(\"/\").pop();\r\n"
   								+ "										    	let filename1 = src_des.split(\"/\").pop();\r\n"
   								+ "										    	let newSrc = path1+\"/\"+filename;\r\n"
   								+ "										    	$(\"#img-des"+listProAd.get(i).getProductSKU()+"\").attr('src',newSrc);  \r\n"
   								+ "									    	});\r\n"
   								+ "						               </script>\r\n");
   									}}
   						response.getWriter().print( "</div>\r\n"
   								+ "					</div>\r\n");
									}}
   						response.getWriter().print( "</div>\r\n"
   								+ "						<div class=\"col-sm-12 col-lg-12 mt_50\">\r\n"
   								+ "                    	<div class=\"row\">"
   								+ "						<div class=\"col-sm-4\">\r\n"
   								+ "                          <div class=\"dataTables_info\" id=\"dataTables-example_info\" style=\"margin:27px 0px;\">Hiển thị từ "+((index-1)*dfNum+1)+" đến "+numPro+" của "+totalPro+" sản phẩm</div>\r\n"
   								+ "                          </div>\r\n"
   								+ "                          <div class=\"col-sm-8\" style=\"text-align: right; margin:27px 0px\">\r\n"
   								+ "                           <div class=\"dataTables_paginate paging_simple_numbers\" id=\"dataTables-example_paginate\">\r\n"
   								+ "                                 <ul class=\"pagination justify-content-end\">");
   							if(index>1){
   								response.getWriter().print("<li class=\"page-item previous\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\"><a id=\"index1\" class=\"page-link\" data-index=\"1\">Về trang 1</a></li>\r\n"
   										+ "                <li class=\"page-item previous\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\"><a id=\"indexx"+(index-1)+"\" class=\"page-link\"\" data-index =\""+(index-1)+"\">Trang trước</a></li>\r\n"
   										+ "					<script>"
   										+ "						$('#index1').on('click', function(){\r\n"
                   						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let size_list=arrSize.toString();\r\n"
                   						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let color_list=arrColor.toString();\r\n"
                   						+ "                        		let listmenu='"+menu_id.toString()+"';\r\n"
                   						+ "                        		let index = $(this).data('index');\r\n"
                   						+ "                        		$.ajax({\r\n"
                   						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                   						+ "                        			type: 'POST',\r\n"
                   						+ "                        		    cache: false,\r\n"
                   						+ "                        			data: {\r\n"
                   						+ "                        		    size_list: size_list,\r\n"
                   						+ "                        			color_list: color_list,\r\n"
                   						+ "                        			alistmenu_id: listmenu_id,\r\n"
                   						+ "                        			index: index,\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        		success: function(data){\r\n"
                   						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                   						+ "									$('#indexs1').addClass('active');"
                   						+ "                        	    },\r\n"
                   						+ "                        		error: function (){\r\n"
                   						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        	});\r\n"
                   						+ "						});"
                   						+ "						$('#indexx"+(index-1)+"').on('click', function(){\r\n"
                   						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let size_list=arrSize.toString();\r\n"
                   						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let color_list=arrColor.toString();\r\n"
                   						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                   						+ "                        		let index = $(this).data('index');\r\n"
                   						+ "                        		$.ajax({\r\n"
                   						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                   						+ "                        			type: 'POST',\r\n"
                   						+ "                        		    cache: false,\r\n"
                   						+ "                        			data: {\r\n"
                   						+ "                        		    size_list: size_list,\r\n"
                   						+ "                        			color_list: color_list,\r\n"
                   						+ "                        			alistmenu_id: listmenu_id,\r\n"
                   						+ "                        			index: index,\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        		success: function(data){\r\n"
                   						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                   						+ "									$('#indexs"+(index-1)+"').addClass('active');"
                   						+ "                        	    },\r\n"
                   						+ "                        		error: function (){\r\n"
                   						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        	});\r\n"
                   						+ "						});"
                   						+ "			</script>");
   							}
   							if(number_page<numPage ){
                        		if(numcur<=numPage){                                            			
                        			for(int k=index; k<=numcur; k++){
                        				response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                           						+ "		 			<script>\r\n"
                           						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                           						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let size_list=arrSize.toString();\r\n"
                           						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let color_list = arrColor.toString();\r\n"
                           						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                           						+ "                        		let index = $(this).data('index');\r\n"
                           						+ "                        		$.ajax({\r\n"
                           						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                           						+ "                        			type: 'POST',\r\n"
                           						+ "                        		    cache: false,\r\n"
                           						+ "                        			data: {\r\n"
                           						+ "                        		    size_list: size_list,\r\n"
                           						+ "                        			color_list: color_list,\r\n"
                           						+ "                        			alistmenu_id: listmenu_id,\r\n"
                           						+ "                        			index: index,\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        		success: function(data){\r\n"
                           						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                           						+ "								$(this).removeClass('active');"
                               					+ "								$('#indexs"+k+"').addClass('active');"
                           						+ "                        	    },\r\n"
                           						+ "                        		error: function (){\r\n"
                           						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        	});\r\n"
                           						+ "	});"
                           						+ "               </script>");  
                        		}}else{
                        			for(int k=index-2; k<=numPage; k++){
                        				response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                           						+ "		 			<script>\r\n"
                           						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                           						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let size_list=arrSize.toString();\r\n"
                           						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let color_list=arrColor.toString();\r\n"
                           						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                           						+ "                        		let index = $(this).data('index');\r\n"
                           						+ "                        		$.ajax({\r\n"
                           						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                           						+ "                        			type: 'POST',\r\n"
                           						+ "                        		    cache: false,\r\n"
                           						+ "                        			data: {\r\n"
                           						+ "                        		    size_list: size_list,\r\n"
                           						+ "                        			color_list: color_list,\r\n"
                           						+ "                        			alistmenu_id: listmenu_id,\r\n"
                           						+ "                        			index: index,\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        		success: function(data){\r\n"
                           						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                           						+ "								$(this).removeClass('active');"
                               					+ "								$('#indexs"+k+"').addClass('active');"
                           						+ "                        	    },\r\n"
                           						+ "                        		error: function (){\r\n"
                           						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        	});\r\n"
                           						+ "	});"
                           						+ "               </script>");                                                     
                        			}
                        		}}else{
                        			if(number_page>=numPage){
                        				for(int k=1; k<=numPage; k++){                               	
                        					response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                               						+ "		 			<script>\r\n"
                               						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                               						+ "								let size_list=arrSize.toString();\r\n"
                               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                               						+ "								let color_list=arrColor.toString();\r\n"
                               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                               						+ "                        		let index = $(this).data('index');\r\n"
                               						+ "                        		$.ajax({\r\n"
                               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                               						+ "                        			type: 'POST',\r\n"
                               						+ "                        		    cache: false,\r\n"
                               						+ "                        			data: {\r\n"
                               						+ "                        		    size_list: size_list,\r\n"
                               						+ "                        			color_list: color_list,\r\n"
                               						+ "                        			alistmenu_id: listmenu_id,\r\n"
                               						+ "                        			index: index,\r\n"
                               						+ "                        		},\r\n"
                               						+ "                        		success: function(data){\r\n"
                               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                               						+ "								$(this).removeClass('active');"
                                   					+ "								$('#indexs"+k+"').addClass('active');"
                               						+ "                        	    },\r\n"
                               						+ "                        		error: function (){\r\n"
                               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                               						+ "                        		},\r\n"
                               						+ "                        	});\r\n"
                               						+ "	});"
                               						+ "               </script>");                                                  
                                }}}
                        	if(index>=1&&index<numPage){
                            response.getWriter().print("<li class=\"page-item next\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_next\"><a id=\"indexss"+(index+1)+"\" class=\"page-link\" data-index =\""+(index+1)+"\">Trang tiếp</a></li>\r\n"
                            		+ "	                <li class=\"page-item next\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_next\"><a id =\"indexlast"+numPage+"\" class=\"page-link\" data-index =\""+numPage+"\">Đến trang cuối</a></li>"
                            		+ "					<script>"
									+ "						$('#indexss"+(index+1)+"').on('click', function(){\r\n"
               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let size_list=arrSize.toString();\r\n"
               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let color_list=arrColor.toString();\r\n"
               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
               						+ "                        		let index = $(this).data('index');\r\n"
               						+ "                        		$.ajax({\r\n"
               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
               						+ "                        			type: 'POST',\r\n"
               						+ "                        		    cache: false,\r\n"
               						+ "                        			data: {\r\n"
               						+ "                        		    size_list: size_list,\r\n"
               						+ "                        			color_list: color_list,\r\n"
               						+ "                        			alistmenu_id: listmenu_id,\r\n"
               						+ "                        			index: index,\r\n"
               						+ "                        		},\r\n"
               						+ "                        		success: function(data){\r\n"
               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
               						+ "									$('#indexs"+(index+1)+"').addClass('active');"
               						+ "                        	    },\r\n"
               						+ "                        		error: function (){\r\n"
               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
               						+ "                        		},\r\n"
               						+ "                        	});\r\n"
               						+ "						});"
               						+ "						$('#indexlast"+numPage+"').on('click', function(){\r\n"
               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let size_list=arrSize.toString();\r\n"
               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let color_list=arrColor.toString();\r\n"
               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
               						+ "                        		let index = $(this).data('index');\r\n"
               						+ "                        		$.ajax({\r\n"
               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
               						+ "                        			type: 'POST',\r\n"
               						+ "                        		    cache: false,\r\n"
               						+ "                        			data: {\r\n"
               						+ "                        		    size_list: size_list,\r\n"
               						+ "                        			color_list: color_list,\r\n"
               						+ "                        			alistmenu_id: listmenu_id,\r\n"
               						+ "                        			index: index,\r\n"
               						+ "                        		},\r\n"
               						+ "                        		success: function(data){\r\n"
               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
               						+ "									$('#indexs"+numPage+"').addClass('active');"
               						+ "                        	    },\r\n"
               						+ "                        		error: function (){\r\n"
               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
               						+ "                        		},\r\n"
               						+ "                        	});\r\n"
               						+ "						});"
               						+ "			</script>");
                        		} 
                        	response.getWriter().print("</ul>\r\n"
                        			+ "                    </div>\r\n"
                        			+ "                </div>\r\n"
                        			+ "            </div>\r\n"
                        			+ "    </div>");
   					}
		}
		if(list_size_id.size()>0&&list_color_id.size()==0) {
			ArrayList<ProductAdmin> listProAd = new ArrayList<>();
			for(int i=0; i<list_size_id.size();i++) {
				for(int j=0; j<menu_id.size();j++) {
					ArrayList<ProductAdmin> listProByColorSize = objPDAO.getItemBySizeMenu(menu_id.get(j),list_size_id.get(i));
					if(listProByColorSize.size()>0) {
						listProAd.addAll(listProByColorSize);
					}
				}
			}
			total_num_page = listProAd.size()/DefineLb.NUMBER_PER_PAGE;
			if(listProAd.size()%DefineLb.NUMBER_PER_PAGE!=0) {
				total_num_page++;
			}
			
			int numPage = total_num_page;
			int totalPro = listProAd.size();
			int numPro = 0;
			if(index<numPage){
				numPro = (index-1)*dfNum+dfNum;
			}else{
				numPro = totalPro;
			}
			if(listProAd.size()>DefineLb.NUMBER_PER_PAGE&&listProAd.size()<DefineLb.NUMBER_PER_PAGE*index) {
				getpronum = listProAd.size();
			}else {
				if(listProAd.size()<DefineLb.NUMBER_PER_PAGE) {
					getpronum = listProAd.size();
				}else {
					getpronum = DefineLb.NUMBER_PER_PAGE*index;
				}
			}
			
			if(listProAd.size()>0) {
				session.setAttribute("index", index);
				response.getWriter().print("<div class=\"row row1\">");
				for(int i = ((index-1)*DefineLb.NUMBER_PER_PAGE); i<getpronum; i++) {
					images = listProAd.get(i).getImages();
					color = listProAd.get(i).getColor();
					response.getWriter().print("<div class=\"col-sm-3 col-lg-3 mb_20\">\r\n"
							+ "                      <div class=\"image product-imageblock \">\r\n"
							+ "      					<a href=\""+request.getContextPath()+"/public/product-detail?sku="+listProAd.get(i).getProductSKU()+"\">");
					if(images!=null){
	          			String[] image_list = images.split(",");
	    					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
	    					File image = new File(path+image_list[0]);
	    					if(image.exists()){
	    						response.getWriter().print("<img id=\"img-des"+listProAd.get(i).getProductSKU()+"\" data-name=\"product_image\" src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+image_list[0]+"\" alt=\""+listProAd.get(i).getName()+"\" title=\""+listProAd.get(i).getName()+"\" style=\"width: 220px; height: 293px;\" class=\"img-responsive\">");
	    					}
	    			}
					response.getWriter().print("</a>\r\n"
							+ "                                \r\n"
							+ "                              </div>\r\n"
							+ "                              <div class=\"caption product-detail text-center\">\r\n"
							+ "                                <h6 data-name=\"product_name\" class=\"product-name\"><a href=\""+request.getContextPath()+"/public/product-detail?sku="+listProAd.get(i).getProductSKU()+"\" title=\"Casual Shirt With Ruffle Hem\">"+listProAd.get(i).getName()+"</a></h6>\r\n"
							+ "                                <span class=\"price\"><span class=\"amount\"><span class=\"currencySymbol\">"+(int)listProAd.get(i).getPrice()+"</span>đ</span>\r\n"
							+ "                                </span>\r\n"
							+ "                              </div>\r\n"
							+ "                              <div class=\"row\">");
					if(images!=null&&color!=null){
						String[] oimage_list = images.split(",");
	         			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
	         			String[] ocolor_list = color.split(",");
	         			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
	   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
	   						for(int j=0; j<image_list.length; j++){
	   					File image = new File(path+image_list[j]);
	   					if(image.exists()){
	   						response.getWriter().print("<div class=\"col-lg-3\" style=\"padding-right: 0px; width: 40px;\">\r\n"
	   								+ "                                 			<img id=\"img-icon"+(listProAd.get(i).getProductSKU()+j)+"\" src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+image_list[j]+"\" class=\"img-fluid rounded avatar-50 mr-3 img-icon\" style=\"width: 30px; height: 30px; border-radius: 30px;\" alt=\"image\" title=\""+color_list[j]+"\">\r\n"
	   								+ "                                 		</div>\r\n"
	   								+ "                                 		<script type=\"text/javascript\">\r\n"
	   								+ "									    	$( \"#img-icon"+(listProAd.get(i).getProductSKU()+j)+"\" ).click(function() {\r\n"
	   								+ "									    		let src_icon = $(\"#img-icon"+(listProAd.get(i).getProductSKU()+j)+"\").attr(\"src\");\r\n"
	   								+ "									    		let src_des = $(\"#img-des"+listProAd.get(i).getProductSKU()+"\").attr(\"src\");\r\n"
	   								+ "										    	let path = src_icon.substring(0,src_icon.lastIndexOf('/'));	\r\n"
	   								+ "										    	let path1 = src_des.substring(0,src_des.lastIndexOf('/'));	\r\n"
	   								+ "										    	let filename = src_icon.split(\"/\").pop();\r\n"
	   								+ "										    	let filename1 = src_des.split(\"/\").pop();\r\n"
	   								+ "										    	let newSrc = path1+\"/\"+filename;\r\n"
	   								+ "										    	$(\"#img-des"+listProAd.get(i).getProductSKU()+"\").attr('src',newSrc);  \r\n"
	   								+ "									    	});\r\n"
	   								+ "						               </script>\r\n");
	   									}}
	   						response.getWriter().print( "</div>\r\n"
	   								+ "					</div>\r\n");
					}}
	   						response.getWriter().print( "</div>\r\n"
	   								+ "						<div class=\"col-sm-12 col-lg-12 mt_50\">\r\n"
	   								+ "                    	<div class=\"row\">"
	   								+ "						<div class=\"col-sm-4\">\r\n"
	   								+ "                          <div class=\"dataTables_info\" id=\"dataTables-example_info\" style=\"margin:27px 0px;\">Hiển thị từ "+((index-1)*dfNum+1)+" đến "+numPro+" của "+totalPro+" sản phẩm</div>\r\n"
	   								+ "                          </div>\r\n"
	   								+ "                          <div class=\"col-sm-8\" style=\"text-align: right; margin:27px 0px\">\r\n"
	   								+ "                           <div class=\"dataTables_paginate paging_simple_numbers\" id=\"dataTables-example_paginate\">\r\n"
	   								+ "                                 <ul class=\"pagination justify-content-end\">");
	   						if(index>1){
   								response.getWriter().print("<li class=\"page-item previous\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\"><a id=\"index1\" class=\"page-link\" data-index=\"1\">Về trang 1</a></li>\r\n"
   										+ "                <li class=\"page-item previous\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\"><a id=\"indexx"+(index-1)+"\" class=\"page-link\"\" data-index =\""+(index-1)+"\">Trang trước</a></li>\r\n"
   										+ "					<script>"
   										+ "						$('#index1').on('click', function(){\r\n"
                   						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let size_list=arrSize.toString();\r\n"
                   						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let color_list=arrColor.toString();\r\n"
                   						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                   						+ "                        		let index = $(this).data('index');\r\n"
                   						+ "                        		$.ajax({\r\n"
                   						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                   						+ "                        			type: 'POST',\r\n"
                   						+ "                        		    cache: false,\r\n"
                   						+ "                        			data: {\r\n"
                   						+ "                        		    size_list: size_list,\r\n"
                   						+ "                        			color_list: color_list,\r\n"
                   						+ "                        			alistmenu_id: listmenu_id,\r\n"
                   						+ "                        			index: index,\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        		success: function(data){\r\n"
                   						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                   						+ "									$('#indexs1').addClass('active');"
                   						+ "                        	    },\r\n"
                   						+ "                        		error: function (){\r\n"
                   						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        	});\r\n"
                   						+ "						});"
                   						+ "						$('#indexx"+(index-1)+"').on('click', function(){\r\n"
                   						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let size_list=arrSize.toString();\r\n"
                   						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let color_list=arrColor.toString();\r\n"
                   						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                   						+ "                        		let index = $(this).data('index');\r\n"
                   						+ "                        		$.ajax({\r\n"
                   						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                   						+ "                        			type: 'POST',\r\n"
                   						+ "                        		    cache: false,\r\n"
                   						+ "                        			data: {\r\n"
                   						+ "                        		    size_list: size_list,\r\n"
                   						+ "                        			color_list: color_list,\r\n"
                   						+ "                        			alistmenu_id: listmenu_id,\r\n"
                   						+ "                        			index: index,\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        		success: function(data){\r\n"
                   						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                   						+ "									$('#indexs"+(index-1)+"').addClass('active');"
                   						+ "                        	    },\r\n"
                   						+ "                        		error: function (){\r\n"
                   						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        	});\r\n"
                   						+ "						});"
                   						+ "			</script>");
   							}
   							if(number_page<numPage ){
                        		if(numcur<=numPage){                                            			
                        			for(int k=index; k<=numcur; k++){
                        				response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                           						+ "		 			<script>\r\n"
                           						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                           						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let size_list=arrSize.toString();\r\n"
                           						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let color_list=arrColor.toString();\r\n"
                           						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                           						+ "                        		let index = $(this).data('index');\r\n"
                           						+ "                        		$.ajax({\r\n"
                           						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                           						+ "                        			type: 'POST',\r\n"
                           						+ "                        		    cache: false,\r\n"
                           						+ "                        			data: {\r\n"
                           						+ "                        		    size_list: size_list,\r\n"
                           						+ "                        			color_list: color_list,\r\n"
                           						+ "                        			alistmenu_id: listmenu_id,\r\n"
                           						+ "                        			index: index,\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        		success: function(data){\r\n"
                           						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                           						+ "								$(this).removeClass('active');"
                               					+ "								$('#indexs"+k+"').addClass('active');"
                           						+ "                        	    },\r\n"
                           						+ "                        		error: function (){\r\n"
                           						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        	});\r\n"
                           						+ "	});"
                           						+ "               </script>");  
                        		}}else{
                        			for(int k=index-2; k<=numPage; k++){
                        				response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                           						+ "		 			<script>\r\n"
                           						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                           						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let size_list=arrSize.toString();\r\n"
                           						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let color_list=arrColor.toString();\r\n"
                           						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                           						+ "                        		let index = $(this).data('index');\r\n"
                           						+ "                        		$.ajax({\r\n"
                           						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                           						+ "                        			type: 'POST',\r\n"
                           						+ "                        		    cache: false,\r\n"
                           						+ "                        			data: {\r\n"
                           						+ "                        		    size_list: size_list,\r\n"
                           						+ "                        			color_list: color_list,\r\n"
                           						+ "                        			alistmenu_id: listmenu_id,\r\n"
                           						+ "                        			index: index,\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        		success: function(data){\r\n"
                           						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                           						+ "								$(this).removeClass('active');"
                               					+ "								$('#indexs"+k+"').addClass('active');"
                           						+ "                        	    },\r\n"
                           						+ "                        		error: function (){\r\n"
                           						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        	});\r\n"
                           						+ "	});"
                           						+ "               </script>");                                                     
                        			}
                        		}}else{
                        			if(number_page>=numPage){
                        				for(int k=1; k<=numPage; k++){                               	
                        					response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                               						+ "		 			<script>\r\n"
                               						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                               						+ "								let size_list=arrSize.toString();\r\n"
                               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                               						+ "								let color_list=arrColor.toString();\r\n"
                               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                               						+ "                        		let index = $(this).data('index');\r\n"
                               						+ "                        		$.ajax({\r\n"
                               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                               						+ "                        			type: 'POST',\r\n"
                               						+ "                        		    cache: false,\r\n"
                               						+ "                        			data: {\r\n"
                               						+ "                        		    size_list: size_list,\r\n"
                               						+ "                        			color_list: color_list,\r\n"
                               						+ "                        			alistmenu_id: listmenu_id,\r\n"
                               						+ "                        			index: index,\r\n"
                               						+ "                        		},\r\n"
                               						+ "                        		success: function(data){\r\n"
                               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                               						+ "								$(this).removeClass('active');"
                                   					+ "								$('#indexs"+k+"').addClass('active');"
                               						+ "                        	    },\r\n"
                               						+ "                        		error: function (){\r\n"
                               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                               						+ "                        		},\r\n"
                               						+ "                        	});\r\n"
                               						+ "	});"
                               						+ "               </script>");                                                   
                                }}}
                        	if(index>=1&&index<numPage){
                            response.getWriter().print("<li class=\"page-item next\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_next\"><a id=\"indexss"+(index+1)+"\" class=\"page-link\" data-index =\""+(index+1)+"\">Trang tiếp</a></li>\r\n"
                            		+ "	                <li class=\"page-item next\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_next\"><a id = \"indexlast"+numPage+"\" class=\"page-link\" data-index =\""+numPage+"\">Đến trang cuối</a></li>"
                            		+ "					<script>"
									+ "						$('#indexss"+(index+1)+"').on('click', function(){\r\n"
               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let size_list=arrSize.toString();\r\n"
               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let color_list=arrColor.toString();\r\n"
               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
               						+ "                        		let index = $(this).data('index');\r\n"
               						+ "                        		$.ajax({\r\n"
               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
               						+ "                        			type: 'POST',\r\n"
               						+ "                        		    cache: false,\r\n"
               						+ "                        			data: {\r\n"
               						+ "                        		    size_list: size_list,\r\n"
               						+ "                        			color_list: color_list,\r\n"
               						+ "                        			alistmenu_id: listmenu_id,\r\n"
               						+ "                        			index: index,\r\n"
               						+ "                        		},\r\n"
               						+ "                        		success: function(data){\r\n"
               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
               						+ "									$('#indexs"+(index+1)+"').addClass('active');"
               						+ "                        	    },\r\n"
               						+ "                        		error: function (){\r\n"
               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
               						+ "                        		},\r\n"
               						+ "                        	});\r\n"
               						+ "						});"
               						+ "						$('#indexlast"+numPage+"').on('click', function(){\r\n"
               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let size_list=arrSize.toString();\r\n"
               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let color_list=arrColor.toString();\r\n"
               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
               						+ "                        		let index = $(this).data('index');\r\n"
               						+ "                        		$.ajax({\r\n"
               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
               						+ "                        			type: 'POST',\r\n"
               						+ "                        		    cache: false,\r\n"
               						+ "                        			data: {\r\n"
               						+ "                        		    size_list: size_list,\r\n"
               						+ "                        			color_list: color_list,\r\n"
               						+ "                        			alistmenu_id: listmenu_id,,\r\n"
               						+ "                        			index: index,\r\n"
               						+ "                        		},\r\n"
               						+ "                        		success: function(data){\r\n"
               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
               						+ "									$('#indexs"+numPage+"').addClass('active');"
               						+ "                        	    },\r\n"
               						+ "                        		error: function (){\r\n"
               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
               						+ "                        		},\r\n"
               						+ "                        	});\r\n"
               						+ "						});"
               						+ "			</script>");
                        		} 
	                        	response.getWriter().print("</ul>\r\n"
	                        			+ "                    </div>\r\n"
	                        			+ "                </div>\r\n"
	                        			+ "            </div>\r\n"
	                        			+ "    </div>");
	   					}
		}
		if(list_size_id.size()==0&&list_color_id.size()>0) {
			ArrayList<ProductAdmin> listProAd = new ArrayList<>();
			for(int i=0; i<list_color_id.size();i++) {
				for(int j=0; j<menu_id.size(); j++) {
					ArrayList<ProductAdmin> listProByColorSize = objPDAO.getItemByColorMenu(menu_id.get(j),list_color_id.get(i));
					if(listProByColorSize.size()>0) {
						for (ProductAdmin productAdmin : listProByColorSize) {
							listProAd.add(productAdmin);
						}
					}
				}
			}
			total_num_page = listProAd.size()/DefineLb.NUMBER_PER_PAGE;
			if(listProAd.size()%DefineLb.NUMBER_PER_PAGE!=0) {
				total_num_page++;
			}
			int numPage = total_num_page;
			int totalPro = listProAd.size();
			int numPro = 0;
			if(index<numPage){
				numPro = (index-1)*dfNum+dfNum;
			}else{
				numPro = totalPro;
			}
			if(listProAd.size()>DefineLb.NUMBER_PER_PAGE&&listProAd.size()<DefineLb.NUMBER_PER_PAGE*index) {
				getpronum = listProAd.size();
			}else {
				if(listProAd.size()<DefineLb.NUMBER_PER_PAGE) {
					getpronum = listProAd.size();
				}else {
					getpronum = DefineLb.NUMBER_PER_PAGE*index;
				}
			}
			
			if(listProAd.size()>0) {
				session.setAttribute("index", index);
				response.getWriter().print("<div class=\"row row1\">");
				for(int i = ((index-1)*DefineLb.NUMBER_PER_PAGE); i<getpronum; i++) {
					images = listProAd.get(i).getImages();
					color = listProAd.get(i).getColor();
					response.getWriter().print("<div class=\"col-sm-3 col-lg-3 mb_20\">\r\n"
							+ "                      <div class=\"image product-imageblock \">\r\n"
							+ "      					<a href=\""+request.getContextPath()+"/public/product-detail?sku="+listProAd.get(i).getProductSKU()+"\">");
					if(images!=null){
	          			String[] image_list = images.split(",");
	    					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
	    					File image = new File(path+image_list[0]);
	    					if(image.exists()){
	    						response.getWriter().print("<img id=\"img-des"+listProAd.get(i).getProductSKU()+"\" data-name=\"product_image\" src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+image_list[0]+"\" alt=\""+listProAd.get(i).getName()+"\" title=\""+listProAd.get(i).getName()+"\" style=\"width: 220px; height: 293px;\" class=\"img-responsive\">");
	    					}
	    			}
					response.getWriter().print("</a>\r\n"
							+ "                              </div>\r\n"
							+ "                              <div class=\"caption product-detail text-center\">\r\n"
							+ "                                <h6 data-name=\"product_name\" class=\"product-name\"><a href=\""+request.getContextPath()+"/public/product-detail?sku="+listProAd.get(i).getProductSKU()+"\" title=\"Casual Shirt With Ruffle Hem\">"+listProAd.get(i).getName()+"</a></h6>\r\n"
							+ "                                <span class=\"price\"><span class=\"amount\"><span class=\"currencySymbol\">"+(int)listProAd.get(i).getPrice()+"</span>đ</span>\r\n"
							+ "                                </span>\r\n"
							+ "                              </div>\r\n"
							+ "                              <div class=\"row\">");
					if(images!=null&&color!=null){
						String[] oimage_list = images.split(",");
	         			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
	         			String[] ocolor_list = color.split(",");
	         			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
	   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
	   						for(int j=0; j<image_list.length; j++){
	   					File image = new File(path+image_list[j]);
	   					if(image.exists()){
	   						response.getWriter().print("<div class=\"col-lg-3\" style=\"padding-right: 0px; width: 40px;\">\r\n"
	   								+ "                                 			<img id=\"img-icon"+(listProAd.get(i).getProductSKU()+j)+"\" src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+image_list[j]+"\" class=\"img-fluid rounded avatar-50 mr-3 img-icon\" style=\"width: 30px; height: 30px; border-radius: 30px;\" alt=\"image\" title=\""+color_list[j]+"\">\r\n"
	   								+ "                                 		</div>\r\n"
	   								+ "                                 		<script type=\"text/javascript\">\r\n"
	   								+ "									    	$( \"#img-icon"+(listProAd.get(i).getProductSKU()+j)+"\" ).click(function() {\r\n"
	   								+ "									    		let src_icon = $(\"#img-icon"+(listProAd.get(i).getProductSKU()+j)+"\").attr(\"src\");\r\n"
	   								+ "									    		let src_des = $(\"#img-des"+listProAd.get(i).getProductSKU()+"\").attr(\"src\");\r\n"
	   								+ "										    	let path = src_icon.substring(0,src_icon.lastIndexOf('/'));	\r\n"
	   								+ "										    	let path1 = src_des.substring(0,src_des.lastIndexOf('/'));	\r\n"
	   								+ "										    	let filename = src_icon.split(\"/\").pop();\r\n"
	   								+ "										    	let filename1 = src_des.split(\"/\").pop();\r\n"
	   								+ "										    	let newSrc = path1+\"/\"+filename;\r\n"
	   								+ "										    	$(\"#img-des"+listProAd.get(i).getProductSKU()+"\").attr('src',newSrc);  \r\n"
	   								+ "									    	});\r\n"
	   								+ "						               </script>\r\n");
	   									}
	   					}
	   						response.getWriter().print("</div></div>");
	   						}
					}
	   						response.getWriter().print( "</div>\r\n"
	   								+ "						<div class=\"col-sm-12 col-lg-12 mt_50\">\r\n"
	   								+ "                    	<div class=\"row\">"
	   								+ "						<div class=\"col-sm-4\">\r\n"
	   								+ "                          <div class=\"dataTables_info\" id=\"dataTables-example_info\" style=\"margin:27px 0px;\">Hiển thị từ "+((index-1)*dfNum+1)+" đến "+numPro+" của "+totalPro+" sản phẩm</div>\r\n"
	   								+ "                          </div>\r\n"
	   								+ "                          <div class=\"col-sm-8\" style=\"text-align: right; margin:27px 0px\">\r\n"
	   								+ "                           <div class=\"dataTables_paginate paging_simple_numbers\" id=\"dataTables-example_paginate\">\r\n"
	   								+ "                                 <ul class=\"pagination justify-content-end\">");
	   						if(index>1){
   								response.getWriter().print("<li class=\"page-item previous\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\"><a id=\"index1\" class=\"page-link\" data-index=\"1\">Về trang 1</a></li>\r\n"
   										+ "                <li class=\"page-item previous\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\"><a id=\"indexx"+(index-1)+"\" class=\"page-link\"\" data-index =\""+(index-1)+"\">Trang trước</a></li>\r\n"
   										+ "					<script>"
   										+ "						$('#index1').on('click', function(){\r\n"
                   						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let size_list=arrSize.toString();\r\n"
                   						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let color_list=arrColor.toString();\r\n"
                   						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                   						+ "                        		let index = $(this).data('index');\r\n"
                   						+ "                        		$.ajax({\r\n"
                   						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                   						+ "                        			type: 'POST',\r\n"
                   						+ "                        		    cache: false,\r\n"
                   						+ "                        			data: {\r\n"
                   						+ "                        		    size_list: size_list,\r\n"
                   						+ "                        			color_list: color_list,\r\n"
                   						+ "                        			alistmenu_id: listmenu_id,\r\n"
                   						+ "                        			index: index,\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        		success: function(data){\r\n"
                   						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                   						+ "									$('#indexs1').addClass('active');"
                   						+ "                        	    },\r\n"
                   						+ "                        		error: function (){\r\n"
                   						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        	});\r\n"
                   						+ "						});"
                   						+ "						$('#indexx"+(index-1)+"').on('click', function(){\r\n"
                   						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let size_list=arrSize.toString();\r\n"
                   						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let color_list=arrColor.toString();\r\n"
                   						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                   						+ "                        		let index = $(this).data('index');\r\n"
                   						+ "                        		$.ajax({\r\n"
                   						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                   						+ "                        			type: 'POST',\r\n"
                   						+ "                        		    cache: false,\r\n"
                   						+ "                        			data: {\r\n"
                   						+ "                        		    size_list: size_list,\r\n"
                   						+ "                        			color_list: color_list,\r\n"
                   						+ "                        			alistmenu_id: listmenu_id,\r\n"
                   						+ "                        			index: index,\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        		success: function(data){\r\n"
                   						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                   						+ "									$('#indexs"+(index-1)+"').addClass('active');"
                   						+ "                        	    },\r\n"
                   						+ "                        		error: function (){\r\n"
                   						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        	});\r\n"
                   						+ "						});"
                   						+ "			</script>");
   							}
   							if(number_page<numPage ){
                        		if(numcur<=numPage){                                            			
                        			for(int k=index; k<=numcur; k++){
                        				response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                           						+ "		 			<script>\r\n"
                           						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                           						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let size_list=arrSize.toString();\r\n"
                           						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let color_list=arrColor.toString();\r\n"
                           						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                           						+ "                        		let index = $(this).data('index');\r\n"
                           						+ "                        		$.ajax({\r\n"
                           						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                           						+ "                        			type: 'POST',\r\n"
                           						+ "                        		    cache: false,\r\n"
                           						+ "                        			data: {\r\n"
                           						+ "                        		    size_list: size_list,\r\n"
                           						+ "                        			color_list: color_list,\r\n"
                           						+ "                        			alistmenu_id: listmenu_id,\r\n"
                           						+ "                        			index: index,\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        		success: function(data){\r\n"
                           						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                           						+ "								$(this).removeClass('active');"
                               					+ "								$('#indexs"+k+"').addClass('active');"
                           						+ "                        	    },\r\n"
                           						+ "                        		error: function (){\r\n"
                           						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        	});\r\n"
                           						+ "	});"
                           						+ "               </script>"); 
                        		}}else{
                        			for(int k=index-2; k<=numPage; k++){
                        				response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                           						+ "		 			<script>\r\n"
                           						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                           						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let size_list=arrSize.toString();\r\n"
                           						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let color_list=arrColor.toString();\r\n"
                           						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                           						+ "                        		let index = $(this).data('index');\r\n"
                           						+ "                        		$.ajax({\r\n"
                           						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                           						+ "                        			type: 'POST',\r\n"
                           						+ "                        		    cache: false,\r\n"
                           						+ "                        			data: {\r\n"
                           						+ "                        		    size_list: size_list,\r\n"
                           						+ "                        			color_list: color_list,\r\n"
                           						+ "                        			alistmenu_id: listmenu_id,\r\n"
                           						+ "                        			index: index,\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        		success: function(data){\r\n"
                           						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                           						+ "								$(this).removeClass('active');"
                               					+ "								$('#indexs"+k+"').addClass('active');"
                           						+ "                        	    },\r\n"
                           						+ "                        		error: function (){\r\n"
                           						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        	});\r\n"
                           						+ "	});"
                           						+ "               </script>");                                                  
                        			}
                        		}}else{
                        			if(number_page>=numPage){
                        				for(int k=1; k<=numPage; k++){                               	
                        					response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                               						+ "		 			<script>\r\n"
                               						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                               						+ "								let size_list=arrSize.toString();\r\n"
                               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                               						+ "								let color_list=arrColor.toString();\r\n"
                               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                               						+ "                        		let index = $(this).data('index');\r\n"
                               						+ "                        		$.ajax({\r\n"
                               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                               						+ "                        			type: 'POST',\r\n"
                               						+ "                        		    cache: false,\r\n"
                               						+ "                        			data: {\r\n"
                               						+ "                        		    size_list: size_list,\r\n"
                               						+ "                        			color_list: color_list,\r\n"
                               						+ "                        			alistmenu_id: listmenu_id,\r\n"
                               						+ "                        			index: index,\r\n"
                               						+ "                        		},\r\n"
                               						+ "                        		success: function(data){\r\n"
                               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                               						+ "								$(this).removeClass('active');"
                                   					+ "								$('#indexs"+k+"').addClass('active');"
                               						+ "                        	    },\r\n"
                               						+ "                        		error: function (){\r\n"
                               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                               						+ "                        		},\r\n"
                               						+ "                        	});\r\n"
                               						+ "						});"
                               						+ "               </script>");                                             
                                }}}
                        	if(index>=1&&index<numPage){
                            response.getWriter().print("<li class=\"page-item next\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_next\"><a id=\"indexss"+(index+1)+"\" class=\"page-link\" data-index =\""+(index+1)+"\">Trang tiếp</a></li>\r\n"
                            		+ "	                <li class=\"page-item next\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_next\"><a id = \"indexlast"+numPage+"\" class=\"page-link\" data-index =\""+numPage+"\">Đến trang cuối</a></li>"
                            		+ "					<script>"
									+ "						$('#indexss"+(index+1)+"').on('click', function(){\r\n"
               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let size_list=arrSize.toString();\r\n"
               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let color_list=arrColor.toString();\r\n"
               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
               						+ "                        		let index = $(this).data('index');\r\n"
               						+ "                        		$.ajax({\r\n"
               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
               						+ "                        			type: 'POST',\r\n"
               						+ "                        		    cache: false,\r\n"
               						+ "                        			data: {\r\n"
               						+ "                        		    size_list: size_list,\r\n"
               						+ "                        			color_list: color_list,\r\n"
               						+ "                        			alistmenu_id: listmenu_id,\r\n"
               						+ "                        			index: index,\r\n"
               						+ "                        		},\r\n"
               						+ "                        		success: function(data){\r\n"
               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
               						+ "									$('#indexs"+(index+1)+"').addClass('active');"
               						+ "                        	    },\r\n"
               						+ "                        		error: function (){\r\n"
               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
               						+ "                        		},\r\n"
               						+ "                        	});\r\n"
               						+ "						});"
               						+ "						$('#indexlast"+numPage+"').on('click', function(){\r\n"
               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let size_list=arrSize.toString();\r\n"
               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let color_list=arrColor.toString();\r\n"
               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
               						+ "                        		let index = $(this).data('index');\r\n"
               						+ "                        		$.ajax({\r\n"
               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
               						+ "                        			type: 'POST',\r\n"
               						+ "                        		    cache: false,\r\n"
               						+ "                        			data: {\r\n"
               						+ "                        		    size_list: size_list,\r\n"
               						+ "                        			color_list: color_list,\r\n"
               						+ "                        			alistmenu_id: listmenu_id,\r\n"
               						+ "                        			index: index,\r\n"
               						+ "                        		},\r\n"
               						+ "                        		success: function(data){\r\n"
               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
               						+ "									$('#indexs"+numPage+"').addClass('active');"
               						+ "                        	    },\r\n"
               						+ "                        		error: function (){\r\n"
               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
               						+ "                        		},\r\n"
               						+ "                        	});\r\n"
               						+ "						});"
               						+ "			</script>");
                        		} 
	                        	response.getWriter().print("</ul>\r\n"
	                        			+ "                    </div>\r\n"
	                        			+ "                </div>\r\n"
	                        			+ "            </div>\r\n"
	                        			+ "    </div>");
	   					}
		}
		if(list_size_id.size()==0&&list_color_id.size()==0) {
			ArrayList<ProductAdmin> listProAd = new ArrayList<>();
			for(int i=0; i<menu_id.size(); i++) {
				ArrayList<ProductAdmin> listProAdByMenuId = objPDAO.getItemByMenu(menu_id.get(i));
				if(listProAdByMenuId.size()>0) {
					listProAd.addAll(listProAdByMenuId);
				}
			}
			
			total_num_page = listProAd.size()/DefineLb.NUMBER_PER_PAGE;
			if(listProAd.size()%DefineLb.NUMBER_PER_PAGE!=0) {
				total_num_page++;
			}
			int numPage = total_num_page;
			int totalPro = listProAd.size();
			int numPro = 0;
			if(index<numPage){
				numPro = (index-1)*dfNum+dfNum;
			}else{
				numPro = totalPro;
			}
			if(listProAd.size()>DefineLb.NUMBER_PER_PAGE&&listProAd.size()<DefineLb.NUMBER_PER_PAGE*index) {
				getpronum = listProAd.size();
			}else {
				if(listProAd.size()<DefineLb.NUMBER_PER_PAGE) {
					getpronum = listProAd.size();
				}else {
					getpronum = DefineLb.NUMBER_PER_PAGE*index;
				}
			}
			
			if(listProAd.size()>0) {
				session.setAttribute("index", index);
				response.getWriter().print("<div class=\"row row1\">");
				for(int i = ((index-1)*DefineLb.NUMBER_PER_PAGE); i<getpronum; i++) {
					images = listProAd.get(i).getImages();
					color = listProAd.get(i).getColor();
					response.getWriter().print("<div class=\"col-sm-3 col-lg-3 mb_20\">\r\n"
							+ "                      <div class=\"image product-imageblock \">\r\n"
							+ "      					<a href=\""+request.getContextPath()+"/public/product-detail?sku="+listProAd.get(i).getProductSKU()+"\">");
					if(images!=null){
	          			String[] image_list = images.split(",");
	    					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
	    					File image = new File(path+image_list[0]);
	    					if(image.exists()){
	    						response.getWriter().print("<img id=\"img-des"+listProAd.get(i).getProductSKU()+"\" data-name=\"product_image\" src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+image_list[0]+"\" alt=\""+listProAd.get(i).getName()+"\" title=\""+listProAd.get(i).getName()+"\" style=\"width: 220px; height: 293px;\" class=\"img-responsive\">");
	    					}
	    			}
					response.getWriter().print("</a>\r\n"
							+ "                                \r\n"
							+ "                              </div>\r\n"
							+ "                              <div class=\"caption product-detail text-center\">\r\n"
							+ "                                <h6 data-name=\"product_name\" class=\"product-name\"><a href=\""+request.getContextPath()+"/public/product-detail?sku="+listProAd.get(i).getProductSKU()+"\" title=\"Casual Shirt With Ruffle Hem\">"+listProAd.get(i).getName()+"</a></h6>\r\n"
							+ "                                <span class=\"price\"><span class=\"amount\"><span class=\"currencySymbol\">"+(int)listProAd.get(i).getPrice()+"</span>đ</span>\r\n"
							+ "                                </span>\r\n"
							+ "                              </div>\r\n"
							+ "                              <div class=\"row\">");
					if(images!=null&&color!=null){
						String[] oimage_list = images.split(",");
	         			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
	         			String[] ocolor_list = color.split(",");
	         			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
	   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
	   						for(int j=0; j<image_list.length; j++){
	   					File image = new File(path+image_list[j]);
	   					if(image.exists()){
	   						response.getWriter().print("<div class=\"col-lg-3\" style=\"padding-right: 0px; width: 40px;\">\r\n"
	   								+ "                                 			<img id=\"img-icon"+(listProAd.get(i).getProductSKU()+j)+"\" src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+image_list[j]+"\" class=\"img-fluid rounded avatar-50 mr-3 img-icon\" style=\"width: 30px; height: 30px; border-radius: 30px;\" alt=\"image\" title=\""+color_list[j]+"\">\r\n"
	   								+ "                                 		</div>\r\n"
	   								+ "                                 		<script type=\"text/javascript\">\r\n"
	   								+ "									    	$( \"#img-icon"+(listProAd.get(i).getProductSKU()+j)+"\" ).click(function() {\r\n"
	   								+ "									    		let src_icon = $(\"#img-icon"+(listProAd.get(i).getProductSKU()+j)+"\").attr(\"src\");\r\n"
	   								+ "									    		let src_des = $(\"#img-des"+listProAd.get(i).getProductSKU()+"\").attr(\"src\");\r\n"
	   								+ "										    	let path = src_icon.substring(0,src_icon.lastIndexOf('/'));	\r\n"
	   								+ "										    	let path1 = src_des.substring(0,src_des.lastIndexOf('/'));	\r\n"
	   								+ "										    	let filename = src_icon.split(\"/\").pop();\r\n"
	   								+ "										    	let filename1 = src_des.split(\"/\").pop();\r\n"
	   								+ "										    	let newSrc = path1+\"/\"+filename;\r\n"
	   								+ "										    	$(\"#img-des"+listProAd.get(i).getProductSKU()+"\").attr('src',newSrc);  \r\n"
	   								+ "									    	});\r\n"
	   								+ "						               </script>\r\n");
	   									}}
	   						response.getWriter().print( "</div>\r\n"
	   								+ "					</div>\r\n");
					}}
	   						response.getWriter().print( "</div>\r\n"
	   								+ "						<div class=\"col-sm-12 col-lg-12 mt_50\">\r\n"
	   								+ "                    	<div class=\"row\">"
	   								+ "						<div class=\"col-sm-4\">\r\n"
	   								+ "                          <div class=\"dataTables_info\" id=\"dataTables-example_info\" style=\"margin:27px 0px;\">Hiển thị từ "+((index-1)*dfNum+1)+" đến "+numPro+" của "+totalPro+" sản phẩm</div>\r\n"
	   								+ "                          </div>\r\n"
	   								+ "                          <div class=\"col-sm-8\" style=\"text-align: right; margin:27px 0px\">\r\n"
	   								+ "                           <div class=\"dataTables_paginate paging_simple_numbers\" id=\"dataTables-example_paginate\">\r\n"
	   								+ "                                 <ul class=\"pagination justify-content-end\">");
	   						if(index>1){
   								response.getWriter().print("<li class=\"page-item previous\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\"><a id=\"index1\" class=\"page-link\" data-index=\"1\">Về trang 1</a></li>\r\n"
   										+ "                <li class=\"page-item previous\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\"><a id=\"indexx"+(index-1)+"\" class=\"page-link\"\" data-index =\""+(index-1)+"\">Trang trước</a></li>\r\n"
   										+ "					<script>"
   										+ "						$('#index1').on('click', function(){\r\n"
                   						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let size_list=arrSize.toString();\r\n"
                   						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let color_list=arrColor.toString();\r\n"
                   						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                   						+ "                        		let index = $(this).data('index');\r\n"
                   						+ "                        		$.ajax({\r\n"
                   						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                   						+ "                        			type: 'POST',\r\n"
                   						+ "                        		    cache: false,\r\n"
                   						+ "                        			data: {\r\n"
                   						+ "                        		    size_list: size_list,\r\n"
                   						+ "                        			color_list: color_list,\r\n"
                   						+ "                        			alistmenu_id: listmenu_id,\r\n"
                   						+ "                        			index: index,\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        		success: function(data){\r\n"
                   						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                   						+ "									$('#indexs1').addClass('active');"
                   						+ "                        	    },\r\n"
                   						+ "                        		error: function (){\r\n"
                   						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        	});\r\n"
                   						+ "						});"
                   						+ "						$('#indexx"+(index-1)+"').on('click', function(){\r\n"
                   						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let size_list=arrSize.toString();\r\n"
                   						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                   						+ "								let color_list=arrColor.toString();\r\n"
                   						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                   						+ "                        		let index = $(this).data('index');\r\n"
                   						+ "                        		$.ajax({\r\n"
                   						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                   						+ "                        			type: 'POST',\r\n"
                   						+ "                        		    cache: false,\r\n"
                   						+ "                        			data: {\r\n"
                   						+ "                        		    size_list: size_list,\r\n"
                   						+ "                        			color_list: color_list,\r\n"
                   						+ "                        			alistmenu_id: listmenu_id,\r\n"
                   						+ "                        			index: index,\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        		success: function(data){\r\n"
                   						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
                           				+ "									$('#indexs"+(index-1)+"').addClass('active');"
                   						+ "                        	    },\r\n"
                   						+ "                        		error: function (){\r\n"
                   						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                   						+ "                        		},\r\n"
                   						+ "                        	});\r\n"
                   						+ "						});"
                   						+ "			</script>");
   							}
   							if(number_page<numPage ){
                        		if(numcur<=numPage){                                            			
                        			for(int k=index; k<=numcur; k++){
                        				response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                           						+ "		 			<script>\r\n"
                           						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                           						+ "								let activeElement = $(this).parent('#index"+k+"');"
                           						+ "                        		let arrSize = $('input:checkbox.size:checked').map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let size_list=arrSize.toString();\r\n"
                           						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let color_list=arrColor.toString();\r\n"
                           						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                           						+ "                        		let index = $(this).data('index');\r\n"
                           						+ "                        		$.ajax({\r\n"
                           						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                           						+ "                        			type: 'POST',\r\n"
                           						+ "                        		    cache: false,\r\n"
                           						+ "                        			data: {\r\n"
                           						+ "                        		    size_list: size_list,\r\n"
                           						+ "                        			color_list: color_list,\r\n"
                           						+ "                        			alistmenu_id: listmenu_id,\r\n"
                           						+ "                        			index: index,\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        		success: function(data){\r\n"
                           						+ "                        		$('.ajax-show-filter-product').html(data);\r\n"
                           						+ "								$('#indexs"+k+"').addClass('active');"
                           						+ "								$(this).removeClass('active');"
                           						+ "                        	    },\r\n"
                           						+ "                        		error: function (){\r\n"
                           						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        	});\r\n"
                           						+ "	});"
                           						+ "               </script>");  
                        		}}else{
                        			for(int k=index-2; k<=numPage; k++){
                        				response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                           						+ "		 			<script>\r\n"
                           						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                           						+ "							let activeElement = $(this).parent('#index"+k+"')"
                           						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let size_list=arrSize.toString();\r\n"
                           						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                           						+ "								let color_list=arrColor.toString();\r\n"
                           						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                           						+ "                        		let index = $(this).data('index');\r\n"
                           						+ "                        		$.ajax({\r\n"
                           						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                           						+ "                        			type: 'POST',\r\n"
                           						+ "                        		    cache: false,\r\n"
                           						+ "                        			data: {\r\n"
                           						+ "                        		    size_list: size_list,\r\n"
                           						+ "                        			color_list: color_list,\r\n"
                           						+ "                        			alistmenu_id: listmenu_id,\r\n"
                           						+ "                        			index: index,\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        		success: function(data){\r\n"
                           						+ "                        		$('.ajax-show-filter-product').html(data);\r\n"
                           						+ "									$('#indexs"+k+"').addClass('active');"
                           						+ "									$(this).removeClass('active');"
                           						+ "                        	    },\r\n"
                           						+ "                        		error: function (){\r\n"
                           						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                           						+ "                        		},\r\n"
                           						+ "                        	});\r\n"
                           						+ "	});"
                           						+ "               </script>");                                                     
                        			}
                        		}}else{
                        			if(number_page>=numPage){
                        				for(int k=1; k<=numPage; k++){                               	
                        					response.getWriter().print(" <li class=\"page-item\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a id=\"indexs"+k+"\" class=\"page-link page-number\" data-index=\""+k+"\">"+k+"</a></li>\r\n"
                               						+ "		 			<script>\r\n"
                               						+ "                      $('#indexs"+k+"').on('click', function(){\r\n"
                               						+ "								let activeElement = $(this).parent('#indexs"+k+"');"
                               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
                               						+ "								let size_list=arrSize.toString();\r\n"
                               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
                               						+ "								let color_list=arrColor.toString();\r\n"
                               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
                               						+ "                        		let index = $(this).data('index');\r\n"
                               						+ "                        		$.ajax({\r\n"
                               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
                               						+ "                        			type: 'POST',\r\n"
                               						+ "                        		    cache: false,\r\n"
                               						+ "                        			data: {\r\n"
                               						+ "                        		    size_list: size_list,\r\n"
                               						+ "                        			color_list: color_list,\r\n"
                               						+ "                        			alistmenu_id: listmenu_id,\r\n"
                               						+ "                        			index: index,\r\n"
                               						+ "                        		},\r\n"
                               						+ "                        		success: function(data){\r\n"
                               						+ "                        		$('.ajax-show-filter-product').html(data);\r\n"
                               						+ "									$('#indexs"+k+"').addClass('active');"
                               						+ "									$(this).removeClass('active');"
                               						+ "                        	    },\r\n"
                               						+ "                        		error: function (){\r\n"
                               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
                               						+ "                        		},\r\n"
                               						+ "                        	});\r\n"
                               						+ "	});"
                               						+ "               </script>");                                                  
                                }}}
                        	if(index>=1&&index<numPage){
                            response.getWriter().print("<li class=\"page-item next\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_next\"><a id=\"indexss"+(index+1)+"\" class=\"page-link\" data-index =\""+(index+1)+"\">Trang tiếp</a></li>\r\n"
                            		+ "	                <li class=\"page-item next\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_next\"><a id = \"indexlast"+numPage+"\" class=\"page-link\" data-index =\""+numPage+"\">Đến trang cuối</a></li>"
                            		+ "					<script>"
									+ "						$('#indexss"+(index+1)+"').on('click', function(){\r\n"
               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let size_list=arrSize.toString();\r\n"
               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let color_list=arrColor.toString();\r\n"
               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
               						+ "                        		let index = $(this).data('index');\r\n"
               						+ "                        		$.ajax({\r\n"
               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
               						+ "                        			type: 'POST',\r\n"
               						+ "                        		    cache: false,\r\n"
               						+ "                        			data: {\r\n"
               						+ "                        		    size_list: size_list,\r\n"
               						+ "                        			color_list: color_list,\r\n"
               						+ "                        			alistmenu_id: listmenu_id,\r\n"
               						+ "                        			index: index,\r\n"
               						+ "                        		},\r\n"
               						+ "                        		success: function(data){\r\n"
               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
               						+ "									$('#indexs"+(index+1)+"').addClass('active');"
               						+ "                        	    },\r\n"
               						+ "                        		error: function (){\r\n"
               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
               						+ "                        		},\r\n"
               						+ "                        	});\r\n"
               						+ "						});"
               						+ "						$('#indexlast"+numPage+"').on('click', function(){\r\n"
               						+ "                        		let arrSize = $(\"input:checkbox.size:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let size_list=arrSize.toString();\r\n"
               						+ "                        		let arrColor = $(\"input:checkbox.color:checked\").map(function(){return $(this).val()}).get();\r\n"
               						+ "								let color_list=arrColor.toString();\r\n"
               						+ "                        		let listmenu_id='"+menu_id.toString()+"';\r\n"
               						+ "                        		let index = $(this).data('index');\r\n"
               						+ "                        		$.ajax({\r\n"
               						+ "                        			url: '"+request.getContextPath()+"/public/filter-product',\r\n"
               						+ "                        			type: 'POST',\r\n"
               						+ "                        		    cache: false,\r\n"
               						+ "                        			data: {\r\n"
               						+ "                        		    size_list: size_list,\r\n"
               						+ "                        			color_list: color_list,\r\n"
               						+ "                        			alistmenu_id: listmenu_id,\r\n"
               						+ "                        			index: index,\r\n"
               						+ "                        		},\r\n"
               						+ "                        		success: function(data){\r\n"
               						+ "                        			$('.ajax-show-filter-product').html(data);\r\n"
               						+ "									$('#indexs"+numPage+"').addClass('active');"
               						+ "                        	    },\r\n"
               						+ "                        		error: function (){\r\n"
               						+ "                        		     alert('Có lỗi xảy ra');\r\n"
               						+ "                        		},\r\n"
               						+ "                        	});\r\n"
               						+ "						});"
               						+ "			</script>");
                        		} 
	                        	response.getWriter().print("</ul>\r\n"
	                        			+ "                    </div>\r\n"
	                        			+ "                </div>\r\n"
	                        			+ "            </div>\r\n"
	                        			+ "    </div>");
	   					}
		}
	}
}
