package controller.ad;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import library.ExportPDF;

public class ExportPDFController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExportPDFController() {
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
			ExportPDF objExport = new ExportPDF();
			try {
				String name_file=objExport.exportPdf(request);
				String path = request.getServletContext().getRealPath("/export-pdf/");
				File open = new File(path+name_file);
				if(open.exists()) {
					 Desktop.getDesktop().open(open);
					 response.sendRedirect(request.getContextPath()+"/admin/list-order?msg=1");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
