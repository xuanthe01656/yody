package library;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Address;
import model.dao.AddressDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AddressLb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddressLb() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getParameter("province_code")!=null&&request.getParameter("district_code")==null) {
			String code = request.getParameter("province_code");
			AddressDAO objAddressDAO = new AddressDAO();
			ArrayList<Address> listDistricts = objAddressDAO.getDistrictsByCode(code);
			if(listDistricts.size()>0) {
				response.getWriter().print("<option value=\"\">--Chọn quận huyện--</option>");
				for (Address address : listDistricts) {
					response.getWriter().print("<option value=\""+address.getName()+"\" data-code1=\""+address.getCode()+"\">"+address.getName()+"</option>");
				}
			}
		}
			if(request.getParameter("district_code")!=null&&request.getParameter("province_code")==null) {
				String code = request.getParameter("district_code");
				AddressDAO objAddressDAO = new AddressDAO();
				ArrayList<Address> listWads = objAddressDAO.getWardsByCode(code);
				if(listWads.size()>0) {
					response.getWriter().print("<option value=\"\">--Chọn phường xã--</option>");
					for (Address address : listWads) {
						response.getWriter().print("<option value=\""+address.getName()+"\" data-code2=\""+address.getCode()+"\">"+address.getName()+"</option>");
					}
				}
			}
		}
}
