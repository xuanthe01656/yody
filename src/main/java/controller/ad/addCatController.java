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

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
		maxFileSize = 1024 * 1024 * 50, // 50 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
	)
public class addCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "templates/admin/assets/images/table/category";
       
    public addCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/admin/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-add-category.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/admin/login");
			return;
		}
		String menuCategoryDetail = request.getParameter("menucategorydetail");
		String category = request.getParameter("category");
		
		List<String> photos = UploadFileLB.uploadFile(UPLOAD_DIR, request);
		String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
		String image = StringUtils.join(photos, ",");
		MenuDAO objMDAO = new MenuDAO();
		if(menuCategoryDetail==null&&category==null&&image=="") {
			String menuCategory = request.getParameter("menucategory");
			int parent_id = 0;
			int result = objMDAO.addCatByPId(menuCategory, parent_id);
			if(result>0) {
				response.sendRedirect(request.getContextPath()+"/admin/add-cat?msg=1");
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/add-cat?msg=2");
			}
		}else {
			if(menuCategoryDetail!=null&&category==null&&image=="") {
				int parent_id = Integer.parseInt(request.getParameter("menucategory"));
				int result = objMDAO.addCatByPId(menuCategoryDetail, parent_id);
				if(result>0) {
					response.sendRedirect(request.getContextPath()+"/admin/add-cat?msg=1");
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/add-cat?msg=2");
				}
			}else {
				if(menuCategoryDetail!=null&&category!=null&&image!="") {
					int parent_id = Integer.parseInt(request.getParameter("menucategorydetail"));
					int result = objMDAO.addCatByPId(category,image, parent_id);
					if(result>0) {
						response.sendRedirect(request.getContextPath()+"/admin/add-cat?msg=1");
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/add-cat?msg=2");
					}
				}
			}
		}
	}

}
