package controller.ad;

import jakarta.servlet.RequestDispatcher;
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
public class EditCategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "templates/admin/assets/images/table/category";
       
    public EditCategoriesController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		MenuDAO objMDAO = new MenuDAO();
		Menu objM = objMDAO.getItemById(id);
		request.setAttribute("objMByID", objM);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-edit-category.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		String trim = request.getParameter("newcat").trim();		
		if("".equals(trim)) {
			List<String> photos = UploadFileLB.uploadFile(UPLOAD_DIR, request);
			String image = StringUtils.join(photos, ",");
			String applicationPath = request.getServletContext().getRealPath("");
			String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
			int id = Integer.parseInt(request.getParameter("id"));
			MenuDAO objMDAO = new MenuDAO();
			Menu objM = objMDAO.getItemById(id);
			if(objM.getImgaes()!=null) {
				if(objM.getImgaes().equalsIgnoreCase(image)) {
					int result = objMDAO.changeImage(id, image);
					if(result>0) {
						
						response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=1");
						return;
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=2");
						return;
					}
				}else {
					File del = new File(basePath+objM.getImgaes());
					if(del.exists()) {
						del.delete();
						int result = objMDAO.changeImage(id, image);
						if(result>0) {
							
							response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=1");
							return;
						}else {
							response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=2");
							return;
						}
					}else {
						int result = objMDAO.changeImage(id, image);
						if(result>0) {
							
							response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=1");
							return;
						}else {
							response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=2");
							return;
						}
					}
				}
			}else {
				int result = objMDAO.changeImage(id, image);
				if(result>0) {
					
					response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=1");
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=2");
					return;
				}
			}
			
		}else {
			if("".equals(trim)==false) {
				List<String> photos = UploadFileLB.uploadFile(UPLOAD_DIR, request);
				String image = StringUtils.join(photos, ",");
				String applicationPath = request.getServletContext().getRealPath("");
				String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
				int id = Integer.parseInt(request.getParameter("id"));
				MenuDAO objMDAO = new MenuDAO();
				Menu objM = objMDAO.getItemById(id);
				String name =trim;
				if(image!="") {	
					if(objM.getImgaes()!=null) {
						if(objM.getImgaes().equalsIgnoreCase(image)) {
							int result = objMDAO.editCategory(id, name, image);
							if(result>0) {
								
								response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=1");
								return;
							}else {
								response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=2");
								return;
							}
						}else {
							File del = new File(basePath+objM.getImgaes());
							if(del.exists()) {
								del.delete();
								int result = objMDAO.editCategory(id, name, image);
								if(result>0) {
									
									response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=1");
									return;
								}else {
									response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=2");
									return;
								}
							}else {
								int result = objMDAO.editCategory(id, name, image);
								if(result>0) {
									
									response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=1");
									return;
								}else {
									response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=2");
									return;
								}
							}
						}
					}else {
						int result = objMDAO.editCategory(id, name, image);
						if(result>0) {
							
							response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=1");
							return;
						}else {
							response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=2");
							return;
						}
					}
					
				}else {
					if(image=="") {
						int result = objMDAO.editNameCategory(id, name);
						if(result>0) {
							
							response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=1");
							return;
						}else {
							response.sendRedirect(request.getContextPath()+"/admin/list-categories?id="+id+"&msg=2");
							return;
						}
					}
				}
			}
		}
	}
}
