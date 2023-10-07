package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import library.UploadFileLB;
import model.bean.Menu;
import model.dao.MenuDAO;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
		maxFileSize = 1024 * 1024 * 50, // 50 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
	)
public class HandelImagesCategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "templates/admin/assets/images/table/category";
	
       
 
    public HandelImagesCategoriesController() {
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
		int id = Integer.parseInt(request.getParameter("aid"));
		List<String> photos = UploadFileLB.uploadFile(UPLOAD_DIR, request);
		String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
		String image = StringUtils.join(photos, ",");
		MenuDAO objMDAO = new MenuDAO();
		Menu objM = objMDAO.getItemById(id);
		if(objM.getImgaes()!=null) {
			if(objM.getImgaes().equalsIgnoreCase(image)) {
				int result = objMDAO.changeImage(id, image);
				if(result>0) {
					response.getWriter().print("<p style=\"color: red; background-color: yellow\">Cập nhật hình ảnh danh mục thành công</p>");
				}else {
					response.getWriter().print("<p style=\"color: red; background-color: yellow\">Cập nhật hình ảnh danh mục không thành công</p>");
				}
			}else {
				File del = new File(path+image);
					if(del.exists()) {
						del.delete();
						int result = objMDAO.changeImage(id, image);
						if(result>0) {
							response.getWriter().print("<p style=\"color: red; background-color: yellow\">Cập nhật hình ảnh danh mục thành công</p>");
						}else {
							response.getWriter().print("<p style=\"color: red; background-color: yellow\">Cập nhật hình ảnh danh mục không thành công</p>");
						}	
					}else {
						int result = objMDAO.changeImage(id, image);
						if(result>0) {
							response.getWriter().print("<p style=\"color: red; background-color: yellow\">Cập nhật hình ảnh danh mục thành công</p>");
						}else {
							response.getWriter().print("<p style=\"color: red; background-color: yellow\">Cập nhật hình ảnh danh mục không thành công</p>");
						}
					}
				}
		}else {
			int result = objMDAO.changeImage(id, image);
			if(result>0) {
				response.getWriter().print("<p style=\"color: red; background-color: yellow\">Cập nhật hình ảnh danh mục thành công</p>");
			}else {
				response.getWriter().print("<p style=\"color: red; background-color: yellow\">Cập nhật hình ảnh danh mục không thành công</p>");
			}
		}
	}

}
