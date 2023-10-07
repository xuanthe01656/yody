package controller.ad;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.AuthUtil;
import library.UploadFileLB;
import model.bean.Admin;
import model.bean.productDetail;
import model.dao.ProductDao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
		maxFileSize = 1024 * 1024 * 50, // 50 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
	)
public class EditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int index = Integer.parseInt(request.getParameter("index"));
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDao objProDAO = new ProductDao();
		productDetail objPro = objProDAO.getObjPro(id);
		request.setAttribute("index", index);
		request.setAttribute("objPro", objPro);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-edit-product.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		HttpSession session = request.getSession();
		Admin objU = (Admin) session.getAttribute("objU");
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date dateNow = new Date();
		
		int index = Integer.parseInt(request.getParameter("index"));
		int id = Integer.parseInt(request.getParameter("id"));
		String product_code = request.getParameter("suk");
		String name = request.getParameter("name");
		int color_id = Integer.parseInt(request.getParameter("color"));
		int size_id = Integer.parseInt(request.getParameter("size"));
		int menu_id = Integer.parseInt(request.getParameter("menu"));
		int menu_detail_id = Integer.parseInt(request.getParameter("categorymenu"));
		int category_id = Integer.parseInt(request.getParameter("category"));
		double cost = Double.parseDouble(request.getParameter("cost"));
		double price = Double.parseDouble(request.getParameter("price"));
		int suppliers_id = Integer.parseInt(request.getParameter("suppliers"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String outstanding_feature = request.getParameter("outstandingfeature");
		String detail = request.getParameter("detail");
		int country_id = Integer.parseInt(request.getParameter("country"));
		int update_at = objU.getId();
		String created_date = date.format(dateNow);
		ProductDao objPrDAO = new ProductDao();
		productDetail objPD = new productDetail(0, product_code, name, color_id, size_id, amount, cost, price, outstanding_feature, detail, 0,"","", 0, menu_detail_id, category_id, menu_id, suppliers_id, country_id, 0, update_at, created_date, 0);
		int result = objPrDAO.editProduct(objPD, id);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/product-detail?product_code="+product_code+"&index="+index+"&msg=1");
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/product-detail?product_code="+product_code+"&index="+index+"&msg=2");
		}		
			
	}

}
