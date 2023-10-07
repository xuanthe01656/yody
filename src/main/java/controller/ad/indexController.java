package controller.ad;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.RevenueExpense;
import model.dao.ProductDao;

public class indexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###,###");
	public static final double nullValue = 0.0;
    public indexController() {
        super();
    }
    public String converter(double number) {
        Map<String, Double> suffixes = new HashMap<>();
        suffixes.put("K", 1000.0);
        suffixes.put("L", 100000.0);
        suffixes.put("M", 1000000.0);
        suffixes.put("Cr", 10000000.0);
        suffixes.put("B", 1000000000.0);
        suffixes.put("T", 1000000000000.0);

        for (Map.Entry<String, Double> entry : suffixes.entrySet()) {
            if (Math.abs(number) >= entry.getValue()) {
                number = number / entry.getValue();
                return DECIMAL_FORMAT.format(number) + entry.getKey();
            }
        }

        return DECIMAL_FORMAT.format(number);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		ProductDao objPrDAO = new ProductDao();
		ArrayList<RevenueExpense> listPro = objPrDAO.getRevenue();
		ArrayList<RevenueExpense> listProTop = new ArrayList<>();
		int sum_amount=0;
		int sum_quantity_sold = 0;
		int sum_revenue =0 ;
		int sum_expense =0;
		int max_amount=listPro.get(0).getAmount();
		for(int i=1; i<listPro.size(); i++) {
			if(listPro.get(i).getAmount()>max_amount) {
				max_amount = listPro.get(i).getAmount();
			}
		}
		for (RevenueExpense revenueExpense : listPro) {
			sum_amount = sum_amount+revenueExpense.getAmount();
			sum_quantity_sold = sum_quantity_sold+revenueExpense.getQuantity_sold();
			sum_revenue = (int) (sum_revenue + revenueExpense.getRevenue());
			sum_expense = (int) (sum_expense + revenueExpense.getExpense());
			if(revenueExpense.getQuantity_sold()>=50) {
				listProTop.add(revenueExpense);
			}
			
		}
		indexController objIndex = new indexController();
		String new_sum_expense = objIndex.converter(sum_expense);
		String new_sum_revenue = objIndex.converter(sum_revenue);
		String new_sumquantity_sold = objIndex.converter(sum_quantity_sold);
		request.setAttribute("sumRevenue", new_sum_revenue);
		request.setAttribute("sumExpense", new_sum_expense);
		request.setAttribute("sumQuantitySold", new_sumquantity_sold);
		request.setAttribute("listProTop", listProTop);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		doGet(request, response);
	}

}
