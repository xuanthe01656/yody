package controller.pub;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.ProductAdmin;
import model.bean.ProductAdminDetail;
import model.dao.PublicDAO;

import java.io.IOException;

public class HandelDisplayProductDetailPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HandelDisplayProductDetailPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("acolor")!=null&&request.getParameter("asku")!=null&&request.getParameter("acolordp")==null&&request.getParameter("askudp")==null) {
			String color = request.getParameter("acolor");
			String sku = request.getParameter("asku");
			PublicDAO objPlDAO = new PublicDAO();
			ProductAdmin objPD = objPlDAO.getProByColorSku(color, sku);
			String size = objPD.getSize();
			String[] list_size = size.split(",");
			for(int i =0; i<list_size.length; i++) {
			response.getWriter().print(" <div class=\"img-color bg-secondary\">\r\n"
					+ "               		 	<input id=\""+list_size[i]+"\" name=\"size\"  type=\"radio\" value=\""+list_size[i]+"\">\r\n"
					+ "               		 	<label for=\""+list_size[i]+"\" title=\""+list_size[i]+"\" id=\"size-"+list_size[i]+"\" class=\"list-group-item text-center\">"+list_size[i]+"</label>\r\n"
					+ "               		 </div>\r\n"
					+ "               		 \r\n"
					+ "               		 <script type=\"text/javascript\">\r\n"
					+ "		                				$('#size-"+list_size[i]+"').on('click', function(){\r\n"
					+ "		                					\r\n"
					+ "		                					$('#show-size').html($('#"+list_size[i]+"').val());\r\n"
					+ "		                					\r\n"
					+ "		                				});\r\n"
					+ "		                			</script>");
			}
		}else {
			if(request.getParameter("acolor")==null&&request.getParameter("asku")==null&&request.getParameter("acolordp")!=null&&request.getParameter("askudp")!=null) {
				String color = request.getParameter("acolordp");
				String sku = request.getParameter("askudp");
				PublicDAO objPlDAO = new PublicDAO();
				ProductAdminDetail objPD = objPlDAO.getProBySKUColor(sku, color);
				String images = objPD.getList_images();
				String[] list_images = images.split(",");
				if(list_images.length>0) {
					for(int i =0; i<list_images.length; i++) {
						response.getWriter().print("<div class=\"col-md-6 mtb_10\"><a class=\"thumbnails\"> <img src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+list_images[i]+"\" alt=\"\" /></a></div>");
					};
				}
			}
		}
		
	}

}
