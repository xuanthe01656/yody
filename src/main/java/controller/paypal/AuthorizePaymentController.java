package controller.paypal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Order;
import model.bean.User;

import java.io.IOException;
import com.paypal.base.rest.PayPalRESTException;


public class AuthorizePaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AuthorizePaymentController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		 	String product = request.getParameter("product");
	        String subtotal = request.getParameter("subtotal");
	        String shipping = request.getParameter("shipping");
	        String tax = request.getParameter("tax");
	        String total = request.getParameter("total");
	         
	        Order order = new Order(product, subtotal, "0",shipping, tax, total);
	        User objUs = new User();
	        if(session.getAttribute("objU")!=null) {
	        	objUs = (User) session.getAttribute("objU");
	        }
	        try {
	            PaymentServices paymentServices = new PaymentServices();
	            String approvalLink = paymentServices.authorizePayment(order, objUs);
	            response.sendRedirect(approvalLink);
	             
	        } catch (PayPalRESTException ex) {
	            request.setAttribute("errorMessage", ex.getMessage());
	            ex.printStackTrace();
	            request.getRequestDispatcher("/paypal/error.jsp").forward(request, response);
	        }
	}

}
