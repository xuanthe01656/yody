package controller.pub;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.UploadFileLB;
import model.bean.CommentRate;
import model.dao.CommentRateDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class ReCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		CommentRateDAO chilCmtDAO = new CommentRateDAO();
//		int id = Integer.parseInt(request.getParameter("aid"));
//		String sku = request.getParameter("asku");
//		String fullname = request.getParameter("afullname");
//		String comment = request.getParameter("acomment");
//		String email = request.getParameter("aemail");
//		String phone = request.getParameter("aphone");
//		int user_id = Integer.parseInt(request.getParameter("auser_id"));
//		System.out.println(id+","+sku+","+fullname+","+comment+","+email+","+phone+","+user_id);
		if("".equals(request.getParameter("aphone"))==false) {
			int id = Integer.parseInt(request.getParameter("aid"));
			String sku = request.getParameter("asku");
			String fullname = request.getParameter("afullname");
			String comment = request.getParameter("acomment");
			String email = request.getParameter("aemail");
			String phone = request.getParameter("aphone");
			int user_id = Integer.parseInt(request.getParameter("auser_id"));
			int result= chilCmtDAO.addCmtChil(id, sku, fullname, email,comment, phone, user_id);
			int like_comment = 0;
			if(result>0) {
				ArrayList<CommentRate> listCmtChil = chilCmtDAO.getCmtChil(sku, id);
				if(listCmtChil.size()>0) {
					for (CommentRate chilCmt : listCmtChil) {
						like_comment = chilCmt.getLike_coment();
						response.getWriter().print("<h4>"+chilCmt.getName()+"</h4>\r\n"
								+ "                    								<p>"+chilCmt.getComment()+"</p>\r\n"
								+ "                    								<div class=\"sapo-review-actions\" style=\"color: #030d78\">\r\n"
								+ "		                    							<ul>\r\n"
								+ "								                    		<li class=\"sapo-review-useful\">\r\n"
								+ "									                    		<a id=\"like"+chilCmt.getId()+"\" class=\"ajax-show-likecomment"+chilCmt.getId()+"\" title=\"Hữu ích\" href=\"javascript: void(0);\">\r\n"
								+ "									                    		<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
								+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path>\r\n"
								+ "									                    	</svg>\r\n");
								if(like_comment>0) {
																response.getWriter().print(""+like_comment+" Hữu ích</a>\r\n");
								}else {
																response.getWriter().print("Hữu ích</a>\r\n");
								}
								response.getWriter().print("								                    		</li>\r\n"
								+ "								                    		<li class=\"sapo-review-reportreview\">\r\n"
								+ "									                    		<a id=\"report"+chilCmt.getId()+"\" title=\"Báo cáo sai phạm\" href=\"javascript: void(0);\">\r\n"
								+ "									                    		<svg class=\"icon-warning\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\">\r\n"
								+ "									                    		<path d=\"M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z\"></path>\r\n"
								+ "									                    		<path d=\"M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z\"></path>\r\n"
								+ "									                    		</svg>Báo cáo sai phạm</a>\r\n"
								+ "								                    		</li>\r\n"
								+ "		                    							</ul>\r\n"
								+ "                    								</div>"
								+ "														<script type=\"text/javascript\">\r\n"
								+ "                    	    						var ip_address;\r\n"
								+ "                    	    						$.getJSON(\"https://api.ipify.org?format=json\", function(data) {\r\n"
								+ "                    		    			            // Setting text of element P with id gfg\r\n"
								+ "                    		    			            ip_address = data.ip;\r\n"
								+ "                    		    			           // $(\"#ipadd\").html(ipaddress);\r\n"
								+ "                    		    			        })\r\n"
								+ "                    								$('#like"+chilCmt.getId()+"').on('click', function(){\r\n"
								+ "                    									var user_id = "+user_id+";\r\n"
								+ "                    				  						if(user_id>0){\r\n"
								+ "                    				  							$(this).addClass('active');\r\n"
								+ "                    				  							var id = "+chilCmt.getId()+";\r\n"
								+ "                           	                			  	 $.ajax({	\r\n"
								+ "                           	                					url: '"+request.getContextPath()+"/public/show-like-comment',\r\n"
								+ "                           	                					type: 'POST',\r\n"
								+ "                           	                					cache: false,\r\n"
								+ "                           	                					data: {\r\n"
								+ "                           	                						aid: id,\r\n"
								+ "                           	                						aip_address: ip_address,\r\n"
								+ "                           	                									\r\n"
								+ "                           	                					},\r\n"
								+ "                           	                					success: function(data){\r\n"
								+ "                           	                						$('.ajax-show-likecomment"+chilCmt.getId()+"').html(data);\r\n"
								+ "                           	                					},\r\n"
								+ "                           	                					error: function (){\r\n"
								+ "                           	                						alert('Có lỗi xảy ra');\r\n"
								+ "                           	                					},\r\n"
								+ "                           	                			  });	\r\n"
								+ "                    				  						}else{\r\n"
								+ "                    				  							alert(\"Vui lòng đăng nhập!\");\r\n"
								+ "                    				  						}\r\n"
								+ "                    	                		});\r\n"
								+ "                    								</script>");
					}
				}
			}
		}else {
			if("".equals(request.getParameter("aphone"))==true) {
				int id = Integer.parseInt(request.getParameter("aid"));
				String sku = request.getParameter("asku");
				String fullname = request.getParameter("afullname");
				String comment = request.getParameter("acomment");
				String email = request.getParameter("aemail");
				int user_id = Integer.parseInt(request.getParameter("auser_id"));
				int like_comment = 0;
				int result= chilCmtDAO.addCmtChil(id, sku, fullname, email,comment, "", user_id);
				if(result>0) {
					ArrayList<CommentRate> listCmtChil = chilCmtDAO.getCmtChil(sku, id);
					if(listCmtChil.size()>0) {
						for (CommentRate chilCmt : listCmtChil) {
							like_comment = chilCmt.getLike_coment();
							response.getWriter().print("<h4>"+chilCmt.getName()+"</h4>\r\n"
									+ "                    								<p>"+chilCmt.getComment()+"</p>\r\n"
									+ "                    								<div class=\"sapo-review-actions\" style=\"color: #030d78\">\r\n"
									+ "		                    							<ul>\r\n"
									+ "								                    		<li class=\"sapo-review-useful\">\r\n"
									+ "									                    		<a id=\"like"+chilCmt.getId()+"\" class=\"ajax-show-likecomment"+chilCmt.getId()+"\" title=\"Hữu ích\" href=\"javascript: void(0);\">\r\n"
									+ "									                    		<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
									+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path>\r\n"
									+ "									                    	</svg>\r\n");
								if(like_comment>0) {
																	response.getWriter().print(""+like_comment+" Hữu ích</a>\r\n");
								}else {
																	response.getWriter().print("Hữu ích</a>\r\n");
}
																	response.getWriter().print("</li>\r\n"
									+ "								                    		<li class=\"sapo-review-reportreview\">\r\n"
									+ "									                    		<a id=\"report"+chilCmt.getId()+"\" title=\"Báo cáo sai phạm\" href=\"javascript: void(0);\">\r\n"
									+ "									                    		<svg class=\"icon-warning\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\">\r\n"
									+ "									                    		<path d=\"M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z\"></path>\r\n"
									+ "									                    		<path d=\"M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z\"></path>\r\n"
									+ "									                    		</svg>Báo cáo sai phạm</a>\r\n"
									+ "								                    		</li>\r\n"
									+ "		                    							</ul>\r\n"
									+ "                    								</div>"
									+ "													<script type=\"text/javascript\">\r\n"
									+ "                    	    						var ip_address;\r\n"
									+ "                    	    						$.getJSON(\"https://api.ipify.org?format=json\", function(data) {\r\n"
									+ "                    		    			            // Setting text of element P with id gfg\r\n"
									+ "                    		    			            ip_address = data.ip;\r\n"
									+ "                    		    			           // $(\"#ipadd\").html(ipaddress);\r\n"
									+ "                    		    			        })\r\n"
									+ "                    								$('#like"+chilCmt.getId()+"').on('click', function(){\r\n"
									+ "                    									var user_id = "+user_id+";\r\n"
									+ "                    				  						if(user_id>0){\r\n"
									+ "                    				  							$(this).addClass('active');\r\n"
									+ "                    				  							var id = "+chilCmt.getId()+";\r\n"
									+ "                           	                			  	 $.ajax({	\r\n"
									+ "                           	                					url: '"+request.getContextPath()+"/public/show-like-comment',\r\n"
									+ "                           	                					type: 'POST',\r\n"
									+ "                           	                					cache: false,\r\n"
									+ "                           	                					data: {\r\n"
									+ "                           	                						aid: id,\r\n"
									+ "                           	                						aip_address: ip_address,\r\n"
									+ "                           	                									\r\n"
									+ "                           	                					},\r\n"
									+ "                           	                					success: function(data){\r\n"
									+ "                           	                						$('.ajax-show-likecomment"+chilCmt.getId()+"').html(data);\r\n"
									+ "                           	                					},\r\n"
									+ "                           	                					error: function (){\r\n"
									+ "                           	                						alert('Có lỗi xảy ra');\r\n"
									+ "                           	                					},\r\n"
									+ "                           	                			  });	\r\n"
									+ "                    				  						}else{\r\n"
									+ "                    				  							alert(\"Vui lòng đăng nhập!\");\r\n"
									+ "                    				  						}\r\n"
									+ "                    	                		});\r\n"
									+ "                    								</script>");
						}
					}
				}
			}
		}
		
	}

}
