package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import library.ExportExcel;
import model.bean.OrderDetail;
import model.dao.OrdersDAO;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;


public class ExportExcelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExportExcelController() {
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
		if(request.getParameter("export")!=null) {
			ExportExcel objExport = new ExportExcel();
			String name_file = objExport.exportOrderDetail(request);
			String path = request.getServletContext().getRealPath("/export-excel/");
			File open = new File(path+name_file);
			if(open.exists()) {
				 Desktop.getDesktop().open(open);
				 response.sendRedirect(request.getContextPath()+"/admin/list-order?msg=1");
			}
			
			
		}
	}

}
