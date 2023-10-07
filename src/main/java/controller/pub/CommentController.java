package controller.pub;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.UploadFileLB;
import model.bean.CommentRate;
import model.bean.User;
import model.dao.CommentRateDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

@MultipartConfig
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "templates/admin/assets/images/table/comment";

    public CommentController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> photos = UploadFileLB.uploadFile(UPLOAD_DIR, request);
		String list_images = StringUtils.join(photos, ",");
		CommentRateDAO objCmtRDAO = new CommentRateDAO();
		int like_comment=0;
		if("".equals(list_images)==false &&"".equals(request.getParameter("aphone"))==false) {
			String image = StringUtils.join(photos, ",");
			String sku = request.getParameter("asku");
			String fullname = request.getParameter("afullname");
			String comment = request.getParameter("acomment");
			String rate = request.getParameter("arate");
			String email = request.getParameter("aemail");
			String phone = request.getParameter("aphone");
			int user_id = Integer.parseInt(request.getParameter("auser_id"));
			
			int result = objCmtRDAO.addCmtParent(sku,fullname, comment, rate, email, phone,image, user_id);
			if(result>0) {
				ArrayList<CommentRate> listCmtBySKU = objCmtRDAO.getCmtBySKU(sku);
				if(listCmtBySKU.size()>0) {
					for (CommentRate objCmtR : listCmtBySKU) {
						like_comment = objCmtR.getLike_coment();
						response.getWriter().print("<div>\r\n"
								+ "                    		<h4>"+objCmtR.getName()+"</h4>\r\n"
								+ "                    		<p>"+objCmtR.getComment()+"</p>\r\n"
								+ "                    		<div class=\"sapo-review-actions\" style=\"color: #030d78\">\r\n"
								+ "	                    		<ul>\r\n"
								+ "		                    		<li class=\"sapo-review-reply\">\r\n"
								+ "		                    			<a onclick=\"showRepleyCmt"+objCmtR.getId()+"()\" class=\"ajax-show-likecomment"+objCmtR.getId()+"\" title=\"Gửi trả lời\" data-value=\""+objCmtR.getId()+"\"  href=\"javascript: void(0);\">Gửi trả lời</a>\r\n"
								+ "		                    		</li>\r\n"
								+ "		                    		<li class=\"sapo-review-useful\">\r\n"
								+ "			                    		<a id=\"like"+objCmtR.getId()+"\" title=\"Hữu ích\" href=\"javascript: void(0);\">\r\n"
								+ "			                    		<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
								+ "			                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
						if(like_comment>0) {
							response.getWriter().print( ""+like_comment+" Hữu ích</a>\r\n");
						}else {
							response.getWriter().print( " Hữu ích</a>\r\n");
						}
								response.getWriter().print( "		</li>\r\n"
								+ "		                    		<li class=\"sapo-review-reportreview\">\r\n"
								+ "			                    		<a id=\"report"+objCmtR.getId()+"\" title=\"Báo cáo sai phạm\" href=\"javascript: void(0);\">\r\n"
								+ "			                    		<svg class=\"icon-warning\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\">\r\n"
								+ "			                    		<path d=\"M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z\"></path>\r\n"
								+ "			                    		<path d=\"M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z\"></path>\r\n"
								+ "			                    		</svg>Báo cáo sai phạm</a>\r\n"
								+ "		                    		</li>\r\n"
								+ "	                    		</ul>\r\n"
								+ "                    		</div>\r\n"
								+ "                    		<div class=\"sapo-review-images\">\r\n"
								+ "	                    		<ul>\r\n");
																	if(objCmtR.getImages()!=null&&"".equals(objCmtR.getImages())==false){
									                    				String imagesRv = objCmtR.getImages();
									                    				String[] list_images_rv = imagesRv.split(",");
										                    				if(list_images_rv.length>0){
									                    					for(int i =0; i<list_images_rv.length; i++){
							
								response.getWriter().print("<li>\r\n"
								+ "			                    		<a data-fancybox=\"gallery\" href=\"\">\r\n"
								+ "			                    			<img src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+list_images_rv[i]+"\">\r\n"
								+ "			                    		</a>\r\n"
								+ "		                    		</li>\r\n");
									                    					}}};
								response.getWriter().print("</ul>\r\n"
								+ "                    		</div>\r\n"
								+ "							<div class=\"show-recomment\" id=\"show-recomment"+objCmtR.getId()+"\">\r\n");
								ArrayList<CommentRate> listCmtChil = objCmtRDAO.getCmtChil(sku, objCmtR.getId());
								                    					if(listCmtChil.size()>0){
								                  						for(CommentRate objCmtChil: listCmtChil){
								                  							like_comment = objCmtChil.getLike_coment();
								response.getWriter().print("<h4>"+objCmtChil.getName()+"</h4>\r\n"
								+ "                    								<p>"+objCmtChil.getComment()+"</p>\r\n"
								+ "                    								<div class=\"sapo-review-actions\" style=\"color: #030d78\">\r\n"
								+ "		                    							<ul>\r\n"
								+ "								                    		<li class=\"sapo-review-useful\">\r\n"
								+ "									                    		<a id=\"like"+objCmtChil.getId()+"\" class=\"ajax-show-likecomment"+objCmtChil.getId()+"\" title=\"Hữu ích\" href=\"javascript: void(0);\">\r\n"
								+ "									                    		<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
								+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
								if(like_comment>0) {
																response.getWriter().print( ""+like_comment+" Hữu ích</a>\r\n");
								}else {
																response.getWriter().print( " Hữu ích</a>\r\n");
								}
																response.getWriter().print( "</li>\r\n"
								+ "								                    		<li class=\"sapo-review-reportreview\">\r\n"
								+ "									                    		<a id=\"report"+objCmtChil.getId()+"\" title=\"Báo cáo sai phạm\" href=\"javascript: void(0);\">\r\n"
								+ "									                    		<svg class=\"icon-warning\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\">\r\n"
								+ "									                    		<path d=\"M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z\"></path>\r\n"
								+ "									                    		<path d=\"M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z\"></path>\r\n"
								+ "									                    		</svg>Báo cáo sai phạm</a>\r\n"
								+ "								                    		</li>\r\n"
								+ "		                    							</ul>\r\n"
								+ "                    								</div>\r\n"
								+ "													<script type=\"text/javascript\">\r\n"
								+ "                    	    						var ip_address;\r\n"
								+ "													$.getJSON(\"https://api.ipify.org?format=json\", function(data) {\r\n"
								+ "	    			            					// Setting text of element P with id gfg\r\n"
								+ "	    			            						ip_address = data.ip;\r\n"
								+ "	    			           						// $(\"#ipadd\").html(ipaddress);\r\n"
								+ "	    			        						})"
								+ "                    								$('#like"+objCmtChil.getId()+"').on('click', function(){\r\n"
										+ "												var user_id = "+user_id+";\r\n"
										+ "                    				  			if(user_id>0){"
										+ "												$(this).addClass('active');"
								+ "                    	                				var id = "+objCmtChil.getId()+";\r\n"
								+ "                    	                			  	 $.ajax({	\r\n"
								+ "                    	                					url: '"+request.getContextPath()+"/public/show-like-comment',\r\n"
								+ "                    	                					type: 'POST',\r\n"
								+ "                    	                					cache: false,\r\n"
								+ "                    	                					data: {\r\n"
								+ "                    	                						aid: id,\r\n"
								+ "                    	                						aip_address: ip_address,\r\n"
								+ "                    	                									\r\n"
								+ "                    	                					},\r\n"
								+ "                    	                					success: function(data){\r\n"
								+ "                    	                						$('.ajax-show-likecomment"+objCmtChil.getId()+"').html(data);\r\n"
								+ "                    	                					},\r\n"
								+ "                    	                					error: function (){\r\n"
								+ "                    	                						alert('Có lỗi xảy ra');\r\n"
								+ "                    	                					},\r\n"
								+ "                    	                			  });\r\n"
								+ "													}else{\r\n"
								+ "                    				  							alert(\"Vui lòng đăng nhập!\");\r\n"
								+ "                    				  						}"
								+ "                    	                		});\r\n"
								+ "                    					</script>");
								                  						}};
								response.getWriter().print("</div>\r\n"
										+ "						<div class=\"re-comment\" id=\"re-comment"+objCmtR.getId()+"\" data-value=\""+objCmtR.getId()+"\">\r\n");
									              				int id_user =0;
																if(session.getAttribute("objU")!=null){
									                      		User objU = (User) session.getAttribute("objU");
									                      		String phoneNum = "";
									                      		id_user = objU.getId();
									                      		if(objU.getPhone()!=null) {
									                      			phoneNum=objU.getPhone();
									                      		};
								response.getWriter().print("<form id=\"re-form"+objCmtR.getId()+"\" class=\"re-form\">\r\n"
								+ "                    				<div class=\"row1\">\r\n"
								+ "									    <div class=\"col colm-3\">\r\n"
								+ "											<label for=\"re-name\" class=\"error\"></label>"
								+ "									      <input type=\"text\" class=\"form-control\" id=\"re-name"+objCmtR.getId()+"\" name=\"re-name\" value=\""+objU.getFullname()+"\" placeholder=\"Tên\">\r\n"
								+ "									    </div>\r\n"
								+ "									    <div class=\"col colm-3\">\r\n"
								+ "											<label for=\"re-email\" class=\"error\"></label>"
								+ "									      <input type=\"text\" class=\"form-control\" id=\"re-email"+objCmtR.getId()+"\" name=\"re-email\" value=\""+objU.getEmail()+"\" placeholder=\"Email\">\r\n"
								+ "									    </div>\r\n"
								+ "									    <div class=\"col colm-3\">\r\n"
								+ "									      <input type=\"text\" class=\"form-control\" id=\"re-phone"+objCmtR.getId()+"\" name=\"re-phone\" value=\""+phoneNum+"\" placeholder=\"Số điện thoại(nếu có)\">\r\n"
								+ "									    </div>\r\n"
								+ "									    <div class=\"col col-sm-10\">\r\n"
								+ "										<label for=\"re-comment\" class=\"error\"></label>"
								+ "									      <input type=\"text\" class=\"form-control\" id=\"re-comments"+objCmtR.getId()+"\" name=\"re-comment\" placeholder=\"Nhập trả lời của bạn\">\r\n"
								+ "									    </div>\r\n"
								+ "									    <div class=\"col col-sm-2\">\r\n"
								+ "									      <button class=\"form-control\" id=\"recomment"+objCmtR.getId()+"\" type=\"button\">Trả Lời</button>\r\n"
								+ "									    </div>\r\n"
								+ "									</div>\r\n"
								+ "                    			</form>\r\n");
								                    			}else{
								                    				user_id=0;
								response.getWriter().print("<form id=\"re-form"+objCmtR.getId()+"\" class=\"re-form\">\r\n"
										+ "                        				<div class=\"row1\">\r\n"
										+ "    									    <div class=\"col colm-3\">\r\n"
										+ "    									    <label for=\"re-name\" class=\"error\"></label>\r\n"
										+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-name\" id=\"re-name"+objCmtR.getId()+"\" placeholder=\"Tên\">\r\n"
										+ "    									      \r\n"
										+ "    									    </div>\r\n"
										+ "    									    <div class=\"col colm-3\">\r\n"
										+ "    									    <label for=\"re-email\" class=\"error\"></label>\r\n"
										+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-email\" id=\"re-email"+objCmtR.getId()+"\" placeholder=\"Email\">\r\n"
										+ "    									    </div>\r\n"
										+ "    									    <div class=\"col colm-3\">\r\n"
										+ "    									      <input type=\"text\" class=\"form-control\" id=\"re-phone"+objCmtR.getId()+"\" name=\"re-phone\" placeholder=\"Số điện thoại(nếu có)\">\r\n"
										+ "    									    </div>\r\n"
										+ "    									    <div class=\"col col-sm-10\">\r\n"
										+ "    									    	<label for=\"re-comment\" class=\"error\"></label>\r\n"
										+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-comment\" id=\"re-comments"+objCmtR.getId()+"\" placeholder=\"Nhập trả lời của bạn\">\r\n"
										+ "    									    </div>\r\n"
										+ "    									    <div class=\"col col-sm-2\">\r\n"
										+ "    									      <button class=\"form-control\" id=\"recomment"+objCmtR.getId()+"\" type=\"button\">Trả Lời</button>\r\n"
										+ "    									    </div>\r\n"
										+ "    									</div>\r\n"
										+ "                        			</form>\r\n");
								                    			};
									response.getWriter().print("                    		</div>\r\n"
								+ "                    		<script type=\"text/javascript\">\r\n"
								+ "								$('#re-form"+objCmtR.getId()+"').validate({\r\n"
								+ "                    			\r\n"
								+ "                    	   		ignore:[], //validate cho Textarea.\r\n"
								+ "                    				rules:{\r\n"
								+ "                    					're-name':{\r\n"
								+ "                    						required: true,\r\n"
								+ "                    						minlength:6,\r\n"
								+ "                    					},\r\n"
								+ "                    					're-email':{\r\n"
								+ "                    						required: true,\r\n"
								+ "                    						email: true,\r\n"
								+ "                    					},\r\n"
								+ "                    					're-comment':{\r\n"
								+ "                    						required: true,\r\n"
								+ "                    						minlength:10,\r\n"
								+ "                    					},\r\n"
								+ "                    					\r\n"
								+ "                    				},\r\n"
								+ "                    				\r\n"
								+ "                    				messages:{\r\n"
								+ "                    					're-name':{\r\n"
								+ "                    						required: \" Hãy nhập vào tên.\",\r\n"
								+ "                    						minlength:\" Hãy nhập nhiều hơn 6 ký tự .\",\r\n"
								+ "                    					},\r\n"
								+ "                    					're-email':{\r\n"
								+ "                    						required: \" Hãy nhập vào email\",\r\n"
								+ "                    						email:\" Hãy nhập đúng định dạng.\",\r\n"
								+ "                    					},\r\n"
								+ "                    					're-comment':{\r\n"
								+ "                    						required: \" Hãy nhập vào trả lời của bạn.\",\r\n"
								+ "                    						minlength:\" Hãy nhập nhiều hơn 10 ký tự.\",\r\n"
								+ "                    					},\r\n"
								+ "                    				},\r\n"
								+ "                    	      });"
								+ "								$('#recomment"+objCmtR.getId()+"').on('click', function(){\r\n"
								+ "                    			$('#re-form"+objCmtR.getId()+"').valid();\r\n"
								+ "                    				if($('#re-form"+objCmtR.getId()+"').valid()==true){\r\n"
								+ "                    					var id = "+objCmtR.getId()+";\r\n"
								+ "                    					var sku = '"+objCmtR.getProduct_sku()+"';\r\n"
								+ "                    			  		var comment = $(\"#re-comments"+objCmtR.getId()+"\").val();\r\n"
								+ "                    			  		var fullname = $(\"#re-name"+objCmtR.getId()+"\").val();\r\n"
								+ "                    			  		var email = $(\"#re-email"+objCmtR.getId()+"\").val();\r\n"
								+ "                    			  		var phone = $(\"#re-phone"+objCmtR.getId()+"\").val();\r\n"
								+ "                    			  		var user_id = "+id_user+";\r\n"
								+ "                    			  	 			$.ajax({	\r\n"
								+ "                    								url: '"+request.getContextPath()+"/public/show-recomment',\r\n"
								+ "                    								type: 'POST',\r\n"
								+ "                    								cache: false,\r\n"
								+ "                    								data: {"
								+ "														aid: id,\r\n"
								+ "                    									asku: sku,\r\n"
								+ "                    									acomment: comment,\r\n"
								+ "														aemail: email,\r\n"
								+ "                    									afullname: fullname,\r\n"
								+ "                    									aphone: phone,\r\n"
								+ "                    									auser_id: user_id,"
								+ "															},\r\n"
								+ "                    								success: function(data){\r\n"
								+ "                    									$('#show-recomment"+objCmtR.getId()+"').html(data);\r\n"
								+ "                    									$('#re-comment"+objCmtR.getId()+"').hide();\r\n"
								+ "                    									\r\n"
								+ "                    								},\r\n"
								+ "                    								error: function (){\r\n"
								+ "                    									alert('Có lỗi xảy ra');\r\n"
								+ "                    								},\r\n"
								+ "                    			  			});\r\n"
								+ "                    				}else{\r\n"
								+ "                    					$('#re-form"+objCmtR.getId()+"').valid();\r\n"
								+ "                    				}\r\n"
								+ "                    			  		\r\n"
								+ "                    		});"
								+ "	                    		function showRepleyCmt"+objCmtR.getId()+"(){\r\n"
								+ "	    							$('#re-comment"+objCmtR.getId()+"').toggle();\r\n"
								+ "	    						};\r\n"
								+ "								var ip_address;\r\n"
								+ "	    					$.getJSON(\"https://api.ipify.org?format=json\", function(data) {\r\n"
								+ "	    			            // Setting text of element P with id gfg\r\n"
								+ "	    			            ip_address = data.ip;\r\n"
								+ "	    			           // $(\"#ipadd\").html(ipaddress);\r\n"
								+ "	    			        })\r\n"
								+ "	    					$('#like"+objCmtR.getId()+"').on('click', function(){\r\n"
										+ "								var user_id = "+user_id+";\r\n"
										+ "                    		if(user_id>0){"
										+ "								$(this).addClass('active');"
								+ "	                    				var id = "+objCmtR.getId()+";\r\n"
								+ "	                    			  	 $.ajax({	\r\n"
								+ "	                    					url: '"+request.getContextPath()+">/public/show-like-comment',\r\n"
								+ "	                    					type: 'POST',\r\n"
								+ "	                    					cache: false,\r\n"
								+ "	                    					data: {\r\n"
								+ "	                    						aid: id,\r\n"
								+ "	                    						aip_address: ip_address,\r\n"
								+ "	                    									\r\n"
								+ "	                    					},\r\n"
								+ "	                    					success: function(data){\r\n"
								+ "	                    						$('.ajax-show-likecomment"+objCmtR.getId()+"').html(data);\r\n"
								+ "	                    					},\r\n"
								+ "	                    					error: function (){\r\n"
								+ "	                    						alert('Có lỗi xảy ra');\r\n"
								+ "	                    					},\r\n"
								+ "	                    			  });\r\n"
								+ "								}else{\r\n"
								+ "                    				  	alert(\"Vui lòng đăng nhập!\");\r\n"
								+ "                    				  	}"
								+ "	                    		});"
								+ "                    		</script>\r\n"
								+ "                    	</div>");
								                    		
					}
				}
			}
		}else {
			if("".equals(list_images)==false&&"".equals(request.getParameter("aphone"))) {
				String image = StringUtils.join(photos, ",");
				String sku = request.getParameter("asku");
				String fullname = request.getParameter("afullname");
				String comment = request.getParameter("acomment");
				String rate = request.getParameter("arate");
				String email = request.getParameter("aemail");
				int user_id = Integer.parseInt(request.getParameter("auser_id"));
				int result = objCmtRDAO.addCmtParent(sku,fullname, comment, rate, email, "",image, user_id);
				if(result>0) {
					ArrayList<CommentRate> listCmtBySKU = objCmtRDAO.getCmtBySKU(sku);
					if(listCmtBySKU.size()>0) {
						for (CommentRate objCmtR : listCmtBySKU) {
							like_comment = objCmtR.getLike_coment();
							response.getWriter().print("<div>\r\n"
									+ "                    		<h4>"+objCmtR.getName()+"</h4>\r\n"
									+ "                    		<p>"+objCmtR.getComment()+"</p>\r\n"
									+ "                    		<div class=\"sapo-review-actions\" style=\"color: #030d78\">\r\n"
									+ "	                    		<ul>\r\n"
									+ "		                    		<li class=\"sapo-review-reply\">\r\n"
									+ "		                    			<a onclick=\"showRepleyCmt"+objCmtR.getId()+"()\" title=\"Gửi trả lời\" data-value=\""+objCmtR.getId()+"\"  href=\"javascript: void(0);\">Gửi trả lời</a>\r\n"
									+ "		                    		</li>\r\n"
									+ "		                    		<li class=\"sapo-review-useful\">\r\n"
									+ "			                    		<a id=\"like"+objCmtR.getId()+"\" class=\"ajax-show-likecomment"+objCmtR.getId()+"\" title=\"Hữu ích\" href=\"javascript: void(0);\">\r\n"
									+ "			                    		<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
									+ "			                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
							if(like_comment>0) {
								response.getWriter().print( ""+like_comment+" Hữu ích</a>\r\n");
							}else {
								response.getWriter().print( " Hữu ích</a>\r\n");
							}
									response.getWriter().print( "		</li>\r\n"
									+ "		                    		<li class=\"sapo-review-reportreview\">\r\n"
									+ "			                    		<a id=\"report"+objCmtR.getId()+"\" title=\"Báo cáo sai phạm\" href=\"javascript: void(0);\">\r\n"
									+ "			                    		<svg class=\"icon-warning\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\">\r\n"
									+ "			                    		<path d=\"M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z\"></path>\r\n"
									+ "			                    		<path d=\"M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z\"></path>\r\n"
									+ "			                    		</svg>Báo cáo sai phạm</a>\r\n"
									+ "		                    		</li>\r\n"
									+ "	                    		</ul>\r\n"
									+ "                    		</div>\r\n"
									+ "                    		<div class=\"sapo-review-images\">\r\n"
									+ "	                    		<ul>\r\n");
																		if(objCmtR.getImages()!=null&&"".equals(objCmtR.getImages())==false){
										                    				String imagesRv = objCmtR.getImages();
										                    				String[] list_images_rv = imagesRv.split(",");
											                    				if(list_images_rv.length>0){
										                    					for(int i =0; i<list_images_rv.length; i++){
								
									response.getWriter().print("<li>\r\n"
									+ "			                    		<a data-fancybox=\"gallery\" href=\"\">\r\n"
									+ "			                    			<img src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+list_images_rv[i]+"\">\r\n"
									+ "			                    		</a>\r\n"
									+ "		                    		</li>\r\n");
										                    					}}};
									response.getWriter().print("</ul>\r\n"
									+ "                    		</div>\r\n"
									+ "							<div class=\"show-recomment\" id=\"show-recomment"+objCmtR.getId()+"\">\r\n");
									ArrayList<CommentRate> listCmtChil = objCmtRDAO.getCmtChil(sku, objCmtR.getId());
									                    					if(listCmtChil.size()>0){
									                  						for(CommentRate objCmtChil: listCmtChil){
									                  							like_comment = objCmtChil.getLike_coment();
									response.getWriter().print("<h4>"+objCmtChil.getName()+"</h4>\r\n"
									+ "                    								<p>"+objCmtChil.getComment()+"</p>\r\n"
									+ "                    								<div class=\"sapo-review-actions\" style=\"color: #030d78\">\r\n"
									+ "		                    							<ul>\r\n"
									+ "								                    		<li class=\"sapo-review-useful\">\r\n"
									+ "									                    		<a id=\"like"+objCmtChil.getId()+"\" class=\"ajax-show-likecomment"+objCmtChil.getId()+"\" title=\"Hữu ích\" href=\"javascript: void(0);\">\r\n"
									+ "									                    		<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
									+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
									if(like_comment>0) {
																	response.getWriter().print( ""+like_comment+" Hữu ích</a>\r\n");
									}else {
																	response.getWriter().print( " Hữu ích</a>\r\n");
									}
																	response.getWriter().print("</li>\r\n"
									+ "								                    		<li class=\"sapo-review-reportreview\">\r\n"
									+ "									                    		<a id=\"report"+objCmtChil.getId()+"\" title=\"Báo cáo sai phạm\" href=\"javascript: void(0);\">\r\n"
									+ "									                    		<svg class=\"icon-warning\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\">\r\n"
									+ "									                    		<path d=\"M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z\"></path>\r\n"
									+ "									                    		<path d=\"M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z\"></path>\r\n"
									+ "									                    		</svg>Báo cáo sai phạm</a>\r\n"
									+ "								                    		</li>\r\n"
									+ "		                    							</ul>\r\n"
									+ "                    								</div>\r\n"
									+ "													<script type=\"text/javascript\">\r\n"
									+ "                    	    						var ip_address;\r\n"
									+ "                    	    						$.getJSON(\"https://api.ipify.org?format=json\", function(data) {\r\n"
									+ "                    		    			            // Setting text of element P with id gfg\r\n"
									+ "                    		    			            ip_address = data.ip;\r\n"
									+ "                    		    			           // $(\"#ipadd\").html(ipaddress);\r\n"
									+ "                    		    			        })\r\n"
									+ "                    								$('#like"+objCmtChil.getId()+"').on('click', function(){\r\n"
											+ "											alert('click');"
											+ "											var user_id = "+user_id+";\r\n"
											+ "                    				  		if(user_id>0){"
											+ "												$(this).addClass('active');"
									+ "                    	                				var id = "+objCmtChil.getId()+";\r\n"
									+ "                    	                			  	 $.ajax({	\r\n"
									+ "                    	                					url: '"+request.getContextPath()+"/public/show-like-comment',\r\n"
									+ "                    	                					type: 'POST',\r\n"
									+ "                    	                					cache: false,\r\n"
									+ "                    	                					data: {\r\n"
									+ "                    	                						aid: id,\r\n"
									+ "                    	                						aip_address: ip_address,\r\n"
									+ "                    	                									\r\n"
									+ "                    	                					},\r\n"
									+ "                    	                					success: function(data){\r\n"
									+ "                    	                						$('.ajax-show-likecomment"+objCmtChil.getId()+"').html(data);\r\n"
									+ "                    	                					},\r\n"
									+ "                    	                					error: function (){\r\n"
									+ "                    	                						alert('Có lỗi xảy ra');\r\n"
									+ "                    	                					},\r\n"
									+ "                    	                			  });\r\n"
									+ "												}else{\r\n"
									+ "                    				  				alert(\"Vui lòng đăng nhập!\");\r\n"
									+ "                    				  			}"
									+ "                    	                		});\r\n"
									+ "                    								</script>");
									                  						}};
									response.getWriter().print("</div>\r\n"
											+ "						<div class=\"re-comment\" id=\"re-comment"+objCmtR.getId()+"\" data-value=\""+objCmtR.getId()+"\">\r\n");
										              				int id_user =0;
																	if(session.getAttribute("objU")!=null){
										                      		User objU = (User) session.getAttribute("objU");
										                      		String phoneNum = "";
										                      		id_user = objU.getId();
										                      		if(objU.getPhone()!=null) {
										                      			phoneNum=objU.getPhone();
										                      		};
									response.getWriter().print("<form id=\"re-form"+objCmtR.getId()+"\" class=\"re-form\">\r\n"
									+ "                    				<div class=\"row1\">\r\n"
									+ "									    <div class=\"col colm-3\">\r\n"
									+ "											<label for=\"re-name\" class=\"error\"></label>"
									+ "									      <input type=\"text\" class=\"form-control\" id=\"re-name"+objCmtR.getId()+"\" name=\"re-name\" value=\""+objU.getFullname()+"\" placeholder=\"Tên\">\r\n"
									+ "									    </div>\r\n"
									+ "									    <div class=\"col colm-3\">\r\n"
									+ "											<label for=\"re-email\" class=\"error\"></label>"
									+ "									      <input type=\"text\" class=\"form-control\" id=\"re-email"+objCmtR.getId()+"\" name=\"re-email\" value=\""+objU.getEmail()+"\" placeholder=\"Email\">\r\n"
									+ "									    </div>\r\n"
									+ "									    <div class=\"col colm-3\">\r\n"
									+ "									      <input type=\"text\" class=\"form-control\" id=\"re-phone"+objCmtR.getId()+"\" name=\"re-phone\" value=\""+phoneNum+"\" placeholder=\"Số điện thoại(nếu có)\">\r\n"
									+ "									    </div>\r\n"
									+ "									    <div class=\"col col-sm-10\">\r\n"
									+ "										<label for=\"re-comment\" class=\"error\"></label>"
									+ "									      <input type=\"text\" class=\"form-control\" id=\"re-comments"+objCmtR.getId()+"\" name=\"re-comment\" placeholder=\"Nhập trả lời của bạn\">\r\n"
									+ "									    </div>\r\n"
									+ "									    <div class=\"col col-sm-2\">\r\n"
									+ "									      <button class=\"form-control\" id=\"recomment"+objCmtR.getId()+"\" type=\"button\">Trả Lời</button>\r\n"
									+ "									    </div>\r\n"
									+ "									</div>\r\n"
									+ "                    			</form>\r\n");
									                    			}else{
									                    				user_id=0;
									response.getWriter().print("<form id=\"re-form"+objCmtR.getId()+"\" class=\"re-form\">\r\n"
											+ "                        				<div class=\"row1\">\r\n"
											+ "    									    <div class=\"col colm-3\">\r\n"
											+ "    									    <label for=\"re-name\" class=\"error\"></label>\r\n"
											+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-name\" id=\"re-name"+objCmtR.getId()+"\" placeholder=\"Tên\">\r\n"
											+ "    									      \r\n"
											+ "    									    </div>\r\n"
											+ "    									    <div class=\"col colm-3\">\r\n"
											+ "    									    <label for=\"re-email\" class=\"error\"></label>\r\n"
											+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-email\" id=\"re-email"+objCmtR.getId()+"\" placeholder=\"Email\">\r\n"
											+ "    									    </div>\r\n"
											+ "    									    <div class=\"col colm-3\">\r\n"
											+ "    									      <input type=\"text\" class=\"form-control\" id=\"re-phone"+objCmtR.getId()+"\" name=\"re-phone\" placeholder=\"Số điện thoại(nếu có)\">\r\n"
											+ "    									    </div>\r\n"
											+ "    									    <div class=\"col col-sm-10\">\r\n"
											+ "    									    	<label for=\"re-comment\" class=\"error\"></label>\r\n"
											+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-comment\" id=\"re-comments"+objCmtR.getId()+"\" placeholder=\"Nhập trả lời của bạn\">\r\n"
											+ "    									    </div>\r\n"
											+ "    									    <div class=\"col col-sm-2\">\r\n"
											+ "    									      <button class=\"form-control\" id=\"recomment"+objCmtR.getId()+"\" type=\"button\">Trả Lời</button>\r\n"
											+ "    									    </div>\r\n"
											+ "    									</div>\r\n"
											+ "                        			</form>\r\n");
									                    			};
											response.getWriter().print("</div>\r\n"
									+ "                    		<script type=\"text/javascript\">\r\n"
									+ "								$('#re-form"+objCmtR.getId()+"').validate({\r\n"
									+ "                    			\r\n"
									+ "                    	   		ignore:[], //validate cho Textarea.\r\n"
									+ "                    				rules:{\r\n"
									+ "                    					're-name':{\r\n"
									+ "                    						required: true,\r\n"
									+ "                    						minlength:6,\r\n"
									+ "                    					},\r\n"
									+ "                    					're-email':{\r\n"
									+ "                    						required: true,\r\n"
									+ "                    						email: true,\r\n"
									+ "                    					},\r\n"
									+ "                    					're-comment':{\r\n"
									+ "                    						required: true,\r\n"
									+ "                    						minlength:10,\r\n"
									+ "                    					},\r\n"
									+ "                    					\r\n"
									+ "                    				},\r\n"
									+ "                    				\r\n"
									+ "                    				messages:{\r\n"
									+ "                    					're-name':{\r\n"
									+ "                    						required: \" Hãy nhập vào tên.\",\r\n"
									+ "                    						minlength:\" Hãy nhập nhiều hơn 6 ký tự .\",\r\n"
									+ "                    					},\r\n"
									+ "                    					're-email':{\r\n"
									+ "                    						required: \" Hãy nhập vào email\",\r\n"
									+ "                    						email:\" Hãy nhập đúng định dạng.\",\r\n"
									+ "                    					},\r\n"
									+ "                    					're-comment':{\r\n"
									+ "                    						required: \" Hãy nhập vào trả lời của bạn.\",\r\n"
									+ "                    						minlength:\" Hãy nhập nhiều hơn 10 ký tự.\",\r\n"
									+ "                    					},\r\n"
									+ "                    				},\r\n"
									+ "                    	      });"
									+ "								$('#recomment"+objCmtR.getId()+"').on('click', function(){\r\n"
									+ "                    			$('#re-form"+objCmtR.getId()+"').valid();\r\n"
									+ "                    				if($('#re-form"+objCmtR.getId()+"').valid()==true){\r\n"
									+ "                    					var id = "+objCmtR.getId()+";\r\n"
									+ "                    					var sku = '"+objCmtR.getProduct_sku()+"';\r\n"
									+ "                    			  		var comment = $(\"#re-comments"+objCmtR.getId()+"\").val();\r\n"
									+ "                    			  		var fullname = $(\"#re-name"+objCmtR.getId()+"\").val();\r\n"
									+ "                    			  		var email = $(\"#re-email"+objCmtR.getId()+"\").val();\r\n"
									+ "                    			  		var phone = $(\"#re-phone"+objCmtR.getId()+"\").val();\r\n"
									+ "                    			  		var user_id = "+id_user+";\r\n"
									+ "                    			  	 			$.ajax({	\r\n"
									+ "                    								url: '"+request.getContextPath()+"/public/show-recomment',\r\n"
									+ "                    								type: 'POST',\r\n"
									+ "                    								cache: false,\r\n"
									+ "                    								data: {"
									+ "														aid: id,\r\n"
									+ "                    									asku: sku,\r\n"
									+ "                    									acomment: comment,\r\n"
									+ "                    									afullname: fullname,\r\n"
									+ "														aemail: email,\r\n"
									+ "                    									aphone: phone,\r\n"
									+ "                    									auser_id: user_id,"
									+ "															},\r\n"
									+ "                    								success: function(data){\r\n"
									+ "                    									$('#show-recomment"+objCmtR.getId()+"').html(data);\r\n"
									+ "                    									$('#re-comment"+objCmtR.getId()+"').hide();\r\n"
									+ "                    									\r\n"
									+ "                    								},\r\n"
									+ "                    								error: function (){\r\n"
									+ "                    									alert('Có lỗi xảy ra');\r\n"
									+ "                    								},\r\n"
									+ "                    			  			});\r\n"
									+ "                    				}else{\r\n"
									+ "                    					$('#re-form"+objCmtR.getId()+"').valid();\r\n"
									+ "                    				}\r\n"
									+ "                    			  		\r\n"
									+ "                    		});"
									+ "	                    		function showRepleyCmt"+objCmtR.getId()+"(){\r\n"
									+ "	    							$('#re-comment"+objCmtR.getId()+"').toggle();\r\n"
									+ "	    						};\r\n"
									+ "							var ip_address;\r\n"
									+ "	    					$.getJSON(\"https://api.ipify.org?format=json\", function(data) {\r\n"
									+ "	    			            // Setting text of element P with id gfg\r\n"
									+ "	    			            ip_address = data.ip;\r\n"
									+ "	    			           // $(\"#ipadd\").html(ipaddress);\r\n"
									+ "	    			        })\r\n"
									+ "	    					$('#like"+objCmtR.getId()+"').on('click', function(){\r\n"
											+ "						var user_id = "+user_id+";\r\n"
											+ "                    		if(user_id>0){"
											+ "								$(this).addClass('active');"
									+ "	                    				var id = "+objCmtR.getId()+";\r\n"
									+ "	                    			  	 $.ajax({	\r\n"
									+ "	                    					url: '"+request.getContextPath()+"/public/show-like-comment',\r\n"
									+ "	                    					type: 'POST',\r\n"
									+ "	                    					cache: false,\r\n"
									+ "	                    					data: {\r\n"
									+ "	                    						aid: id,\r\n"
									+ "	                    						aip_address: ip_address,\r\n"
									+ "	                    									\r\n"
									+ "	                    					},\r\n"
									+ "	                    					success: function(data){\r\n"
									+ "	                    						$('.ajax-show-likecomment"+objCmtR.getId()+"').html(data);\r\n"
									+ "	                    					},\r\n"
									+ "	                    					error: function (){\r\n"
									+ "	                    						alert('Có lỗi xảy ra');\r\n"
									+ "	                    					},\r\n"
									+ "	                    			  });\r\n"
									+ "								}else{\r\n"
									+ "                    				alert(\"Vui lòng đăng nhập!\");\r\n"
									+ "                    			}"
									+ "	                    		});"
									+ "                    		</script>\r\n"
									+ "                    	</div>");
						}
					}
				}
			}else {
				if("".equals(list_images)&&"".equals(request.getParameter("aphone"))==false) {
					String sku = request.getParameter("asku");
					String fullname = request.getParameter("afullname");
					String comment = request.getParameter("acomment");
					String rate = request.getParameter("arate");
					String email = request.getParameter("aemail");
					String phone = request.getParameter("aphone");
					int user_id = Integer.parseInt(request.getParameter("auser_id"));
					int result = objCmtRDAO.addCmtParent(sku,fullname, comment, rate, email, phone,"", user_id);
					if(result>0) {
						ArrayList<CommentRate> listCmtBySKU = objCmtRDAO.getCmtBySKU(sku);
						if(listCmtBySKU.size()>0) {
							for (CommentRate objCmtR : listCmtBySKU) {
								like_comment = objCmtR.getLike_coment();
								response.getWriter().print("<div>\r\n"
										+ "                    		<h4>"+objCmtR.getName()+"</h4>\r\n"
										+ "                    		<p>"+objCmtR.getComment()+"</p>\r\n"
										+ "                    		<div class=\"sapo-review-actions\" style=\"color: #030d78\">\r\n"
										+ "	                    		<ul>\r\n"
										+ "		                    		<li class=\"sapo-review-reply\">\r\n"
										+ "		                    			<a onclick=\"showRepleyCmt"+objCmtR.getId()+"()\" title=\"Gửi trả lời\" data-value=\""+objCmtR.getId()+"\"  href=\"javascript: void(0);\">Gửi trả lời</a>\r\n"
										+ "		                    		</li>\r\n"
										+ "		                    		<li class=\"sapo-review-useful\">\r\n"
										+ "			                    		<a id=\"like"+objCmtR.getId()+"\" class=\"ajax-show-likecomment"+objCmtR.getId()+"\" title=\"Hữu ích\" href=\"javascript: void(0);\">\r\n"
										+ "			                    		<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
										+ "			                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
										if(like_comment>0) {
											response.getWriter().print( ""+like_comment+" Hữu ích</a>\r\n");
										}else {
											response.getWriter().print( " Hữu ích</a>\r\n");
										}
												response.getWriter().print( "</li>\r\n"
										+ "		                    		<li class=\"sapo-review-reportreview\">\r\n"
										+ "			                    		<a id=\"report"+objCmtR.getId()+"\" title=\"Báo cáo sai phạm\" href=\"javascript: void(0);\">\r\n"
										+ "			                    		<svg class=\"icon-warning\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\">\r\n"
										+ "			                    		<path d=\"M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z\"></path>\r\n"
										+ "			                    		<path d=\"M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z\"></path>\r\n"
										+ "			                    		</svg>Báo cáo sai phạm</a>\r\n"
										+ "		                    		</li>\r\n"
										+ "	                    		</ul>\r\n"
										+ "                    		</div>\r\n"
										+ "                    		<div class=\"sapo-review-images\">\r\n"
										+ "	                    		<ul>\r\n");
																			if(objCmtR.getImages()!=null&&"".equals(objCmtR.getImages())==false){
											                    				String imagesRv = objCmtR.getImages();
											                    				String[] list_images_rv = imagesRv.split(",");
												                    				if(list_images_rv.length>0){
											                    					for(int i =0; i<list_images_rv.length; i++){
									
										response.getWriter().print("<li>\r\n"
										+ "			                    		<a data-fancybox=\"gallery\" href=\"\">\r\n"
										+ "			                    			<img src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+list_images_rv[i]+"\">\r\n"
										+ "			                    		</a>\r\n"
										+ "		                    		</li>\r\n");
											                    					}}};
										response.getWriter().print("</ul>\r\n"
										+ "                    		</div>\r\n"
										+ "							<div class=\"show-recomment\" id=\"show-recomment"+objCmtR.getId()+"\">\r\n");
										ArrayList<CommentRate> listCmtChil = objCmtRDAO.getCmtChil(sku, objCmtR.getId());
										                    					if(listCmtChil.size()>0){
										                  						for(CommentRate objCmtChil: listCmtChil){
										                  							like_comment = objCmtChil.getLike_coment();
										response.getWriter().print("<h4>"+objCmtChil.getName()+"</h4>\r\n"
										+ "                    								<p>"+objCmtChil.getComment()+"</p>\r\n"
										+ "                    								<div class=\"sapo-review-actions\" style=\"color: #030d78\">\r\n"
										+ "		                    							<ul>\r\n"
										+ "								                    		<li class=\"sapo-review-useful\">\r\n"
										+ "									                    		<a id=\"like"+objCmtChil.getId()+"\" class=\"ajax-show-likecomment"+objCmtChil.getId()+"\" title=\"Hữu ích\" href=\"javascript: void(0);\">\r\n"
										+ "									                    		<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
										+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
										if(like_comment>0) {
																		response.getWriter().print( ""+like_comment+" Hữu ích</a>\r\n");
										}else {
																		response.getWriter().print( " Hữu ích</a>\r\n");
										}
																		response.getWriter().print( "</li>\r\n"
										+ "								                    		<li class=\"sapo-review-reportreview\">\r\n"
										+ "									                    		<a id=\"report"+objCmtChil.getId()+"\" title=\"Báo cáo sai phạm\" href=\"javascript: void(0);\">\r\n"
										+ "									                    		<svg class=\"icon-warning\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\">\r\n"
										+ "									                    		<path d=\"M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z\"></path>\r\n"
										+ "									                    		<path d=\"M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z\"></path>\r\n"
										+ "									                    		</svg>Báo cáo sai phạm</a>\r\n"
										+ "								                    		</li>\r\n"
										+ "		                    							</ul>\r\n"
										+ "                    								</div>\r\n"
										+ "													<script type=\"text/javascript\">\r\n"
										+ "                    	    						var ip_address;\r\n"
										+ "                    	    						$.getJSON(\"https://api.ipify.org?format=json\", function(data) {\r\n"
										+ "                    		    			            // Setting text of element P with id gfg\r\n"
										+ "                    		    			            ip_address = data.ip;\r\n"
										+ "                    		    			           // $(\"#ipadd\").html(ipaddress);\r\n"
										+ "                    		    			        })\r\n"
										+ "                    								$('#like"+objCmtChil.getId()+"').on('click', function(){\r\n"
												+ "											alert('click');"
												+ "											var user_id = "+user_id+" ;\r\n"
												+ "                    				  		if(user_id>0){"
												+ "												$(this).addClass('active');"
										+ "                    	                				var id = "+objCmtChil.getId()+";\r\n"
										+ "                    	                			  	 $.ajax({	\r\n"
										+ "                    	                					url: '"+request.getContextPath()+"/public/show-like-comment',\r\n"
										+ "                    	                					type: 'POST',\r\n"
										+ "                    	                					cache: false,\r\n"
										+ "                    	                					data: {\r\n"
										+ "                    	                						aid: id,\r\n"
										+ "                    	                						aip_address: ip_address,\r\n"
										+ "                    	                									\r\n"
										+ "                    	                					},\r\n"
										+ "                    	                					success: function(data){\r\n"
										+ "                    	                						$('.ajax-show-likecomment"+objCmtChil.getId()+"').html(data);\r\n"
										+ "                    	                					},\r\n"
										+ "                    	                					error: function (){\r\n"
										+ "                    	                						alert('Có lỗi xảy ra');\r\n"
										+ "                    	                					},\r\n"
										+ "                    	                			  });\r\n"
										+ "												}else{\r\n"
										+ "                    				  				alert(\"Vui lòng đăng nhập!\");\r\n"
										+ "                    				  			}"
										+ "                    	                		});\r\n"
										+ "                    								</script>");
										                  						}};
										response.getWriter().print("</div>\r\n"
												+ "						<div class=\"re-comment\" id=\"re-comment"+objCmtR.getId()+"\" data-value=\""+objCmtR.getId()+"\">\r\n");
											              				int id_user =0;
																		if(session.getAttribute("objU")!=null){
											                      		User objU = (User) session.getAttribute("objU");
											                      		String phoneNum = "";
											                      		id_user = objU.getId();
											                      		if(objU.getPhone()!=null) {
											                      			phoneNum=objU.getPhone();
											                      		};
										response.getWriter().print("<form id=\"re-form"+objCmtR.getId()+"\" class=\"re-form\">\r\n"
										+ "                    				<div class=\"row1\">\r\n"
										+ "									    <div class=\"col colm-3\">\r\n"
										+ "											<label for=\"re-name\" class=\"error\"></label>"
										+ "									      <input type=\"text\" class=\"form-control\" id=\"re-name"+objCmtR.getId()+"\" name=\"re-name\" value=\""+objU.getFullname()+"\" placeholder=\"Tên\">\r\n"
										+ "									    </div>\r\n"
										+ "									    <div class=\"col colm-3\">\r\n"
										+ "											<label for=\"re-email\" class=\"error\"></label>"
										+ "									      <input type=\"text\" class=\"form-control\" id=\"re-email"+objCmtR.getId()+"\" name=\"re-email\" value=\""+objU.getEmail()+"\" placeholder=\"Email\">\r\n"
										+ "									    </div>\r\n"
										+ "									    <div class=\"col colm-3\">\r\n"
										+ "									      <input type=\"text\" class=\"form-control\" id=\"re-phone"+objCmtR.getId()+"\" name=\"re-phone\" value=\""+phoneNum+"\" placeholder=\"Số điện thoại(nếu có)\">\r\n"
										+ "									    </div>\r\n"
										+ "									    <div class=\"col col-sm-10\">\r\n"
										+ "										<label for=\"re-comment\" class=\"error\"></label>"
										+ "									      <input type=\"text\" class=\"form-control\" id=\"re-comments"+objCmtR.getId()+"\" name=\"re-comment\" placeholder=\"Nhập trả lời của bạn\">\r\n"
										+ "									    </div>\r\n"
										+ "									    <div class=\"col col-sm-2\">\r\n"
										+ "									      <button class=\"form-control\" id=\"recomment"+objCmtR.getId()+"\" type=\"button\">Trả Lời</button>\r\n"
										+ "									    </div>\r\n"
										+ "									</div>\r\n"
										+ "                    			</form>\r\n");
										                    			}else{
										                    				user_id=0;
										response.getWriter().print("<form id=\"re-form"+objCmtR.getId()+"\" class=\"re-form\">\r\n"
												+ "                        				<div class=\"row1\">\r\n"
												+ "    									    <div class=\"col colm-3\">\r\n"
												+ "    									    <label for=\"re-name\" class=\"error\"></label>\r\n"
												+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-name\" id=\"re-name"+objCmtR.getId()+"\" placeholder=\"Tên\">\r\n"
												+ "    									      \r\n"
												+ "    									    </div>\r\n"
												+ "    									    <div class=\"col colm-3\">\r\n"
												+ "    									    <label for=\"re-email\" class=\"error\"></label>\r\n"
												+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-email\" id=\"re-email"+objCmtR.getId()+"\" placeholder=\"Email\">\r\n"
												+ "    									    </div>\r\n"
												+ "    									    <div class=\"col colm-3\">\r\n"
												+ "    									      <input type=\"text\" class=\"form-control\" id=\"re-phone"+objCmtR.getId()+"\" name=\"re-phone\" placeholder=\"Số điện thoại(nếu có)\">\r\n"
												+ "    									    </div>\r\n"
												+ "    									    <div class=\"col col-sm-10\">\r\n"
												+ "    									    	<label for=\"re-comment\" class=\"error\"></label>\r\n"
												+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-comment\" id=\"re-comments"+objCmtR.getId()+"\" placeholder=\"Nhập trả lời của bạn\">\r\n"
												+ "    									    </div>\r\n"
												+ "    									    <div class=\"col col-sm-2\">\r\n"
												+ "    									      <button class=\"form-control\" id=\"recomment"+objCmtR.getId()+"\" type=\"button\">Trả Lời</button>\r\n"
												+ "    									    </div>\r\n"
												+ "    									</div>\r\n"
												+ "                        			</form>\r\n");
										                    			}
												response.getWriter().print("</div>\r\n"
										+ "                    		<script type=\"text/javascript\">\r\n"
										+ "								$('#re-form"+objCmtR.getId()+"').validate({\r\n"
										+ "                    			\r\n"
										+ "                    	   		ignore:[], //validate cho Textarea.\r\n"
										+ "                    				rules:{\r\n"
										+ "                    					're-name':{\r\n"
										+ "                    						required: true,\r\n"
										+ "                    						minlength:6,\r\n"
										+ "                    					},\r\n"
										+ "                    					're-email':{\r\n"
										+ "                    						required: true,\r\n"
										+ "                    						email: true,\r\n"
										+ "                    					},\r\n"
										+ "                    					're-comment':{\r\n"
										+ "                    						required: true,\r\n"
										+ "                    						minlength:10,\r\n"
										+ "                    					},\r\n"
										+ "                    					\r\n"
										+ "                    				},\r\n"
										+ "                    				\r\n"
										+ "                    				messages:{\r\n"
										+ "                    					're-name':{\r\n"
										+ "                    						required: \" Hãy nhập vào tên.\",\r\n"
										+ "                    						minlength:\" Hãy nhập nhiều hơn 6 ký tự .\",\r\n"
										+ "                    					},\r\n"
										+ "                    					're-email':{\r\n"
										+ "                    						required: \" Hãy nhập vào email\",\r\n"
										+ "                    						email:\" Hãy nhập đúng định dạng.\",\r\n"
										+ "                    					},\r\n"
										+ "                    					're-comment':{\r\n"
										+ "                    						required: \" Hãy nhập vào trả lời của bạn.\",\r\n"
										+ "                    						minlength:\" Hãy nhập nhiều hơn 10 ký tự.\",\r\n"
										+ "                    					},\r\n"
										+ "                    				},\r\n"
										+ "                    	      });"
										+ "								$('#recomment"+objCmtR.getId()+"').on('click', function(){\r\n"
										+ "                    			$('#re-form"+objCmtR.getId()+"').valid();\r\n"
										+ "                    				if($('#re-form"+objCmtR.getId()+"').valid()==true){\r\n"
										+ "                    					var id = "+objCmtR.getId()+";\r\n"
										+ "                    					var sku = '"+objCmtR.getProduct_sku()+"';\r\n"
										+ "                    			  		var comment = $(\"#re-comments"+objCmtR.getId()+"\").val();\r\n"
										+ "                    			  		var fullname = $(\"#re-name"+objCmtR.getId()+"\").val();\r\n"
										+ "                    			  		var email = $(\"#re-email"+objCmtR.getId()+"\").val();\r\n"
										+ "                    			  		var phone = $(\"#re-phone"+objCmtR.getId()+"\").val();\r\n"
										+ "                    			  		var user_id = "+id_user+";\r\n"
										+ "                    			  	 			$.ajax({	\r\n"
										+ "                    								url: '"+request.getContextPath()+"/public/show-recomment',\r\n"
										+ "                    								type: 'POST',\r\n"
										+ "                    								cache: false,\r\n"
										+ "                    								data: {"
										+ "														aid: id,\r\n"
										+ "                    									asku: sku,\r\n"
										+ "                    									acomment: comment,\r\n"
										+ "                    									afullname: fullname,\r\n"
										+ "														aemail: email,\r\n"
										+ "                    									aphone: phone,\r\n"
										+ "                    									auser_id: user_id,"
										+ "															},\r\n"
										+ "                    								success: function(data){\r\n"
										+ "                    									$('#show-recomment"+objCmtR.getId()+"').html(data);\r\n"
										+ "                    									$('#re-comment"+objCmtR.getId()+"').hide();\r\n"
										+ "                    									\r\n"
										+ "                    								},\r\n"
										+ "                    								error: function (){\r\n"
										+ "                    									alert('Có lỗi xảy ra');\r\n"
										+ "                    								},\r\n"
										+ "                    			  			});\r\n"
										+ "                    				}else{\r\n"
										+ "                    					$('#re-form"+objCmtR.getId()+"').valid();\r\n"
										+ "                    				}\r\n"
										+ "                    			  		\r\n"
										+ "                    		});"
										+ "	                    		function showRepleyCmt"+objCmtR.getId()+"(){\r\n"
										+ "	    							$('#re-comment"+objCmtR.getId()+"').toggle();\r\n"
										+ "	    						};\r\n"
										+ "								var ip_address;\r\n"
										+ "	    					$.getJSON(\"https://api.ipify.org?format=json\", function(data) {\r\n"
										+ "	    			            // Setting text of element P with id gfg\r\n"
										+ "	    			            ip_address = data.ip;\r\n"
										+ "	    			           // $(\"#ipadd\").html(ipaddress);\r\n"
										+ "	    			        })\r\n"
										+ "	    					$('#like"+objCmtR.getId()+"').on('click', function(){\r\n"
												+ "							var user_id = "+user_id+";\r\n"
												+ "                    		if(user_id>0){"
												+ "								$(this).addClass('active');"
										+ "	                    				var id = "+objCmtR.getId()+";\r\n"
										+ "	                    			  	 $.ajax({	\r\n"
										+ "	                    					url: '"+request.getContextPath()+"/public/show-like-comment',\r\n"
										+ "	                    					type: 'POST',\r\n"
										+ "	                    					cache: false,\r\n"
										+ "	                    					data: {\r\n"
										+ "	                    						aid: id,\r\n"
										+ "	                    						aip_address: ip_address,\r\n"
										+ "	                    									\r\n"
										+ "	                    					},\r\n"
										+ "	                    					success: function(data){\r\n"
										+ "	                    						$('.ajax-show-likecomment"+objCmtR.getId()+"').html(data);\r\n"
										+ "	                    					},\r\n"
										+ "	                    					error: function (){\r\n"
										+ "	                    						alert('Có lỗi xảy ra');\r\n"
										+ "	                    					},\r\n"
										+ "	                    			  });\r\n"
										+ "								}else{\r\n"
										+ "                    				alert(\"Vui lòng đăng nhập!\");\r\n"
										+ "                    			}"
										+ "	                    		});"
										+ "                    		</script>\r\n"
										+ "                    	</div>");
							}
						}
					}
				}else {
					if("".equals(list_images)&&"".equals(request.getParameter("aphone"))) {
						
						String sku = request.getParameter("asku");
						String fullname = request.getParameter("afullname");
						String comment = request.getParameter("acomment");
						String rate = request.getParameter("arate");
						String email = request.getParameter("aemail");
						
						int user_id = Integer.parseInt(request.getParameter("auser_id"));
						int result = objCmtRDAO.addCmtParent(sku,fullname, comment, rate, email, "","", user_id);
						if(result>0) {
							ArrayList<CommentRate> listCmtBySKU = objCmtRDAO.getCmtBySKU(sku);
							if(listCmtBySKU.size()>0) {
								for (CommentRate objCmtR : listCmtBySKU) {
									like_comment = objCmtR.getLike_coment();
									response.getWriter().print("<div>\r\n"
											+ "                    		<h4>"+objCmtR.getName()+"</h4>\r\n"
											+ "                    		<p>"+objCmtR.getComment()+"</p>\r\n"
											+ "                    		<div class=\"sapo-review-actions\" style=\"color: #030d78\">\r\n"
											+ "	                    		<ul>\r\n"
											+ "		                    		<li class=\"sapo-review-reply\">\r\n"
											+ "		                    			<a onclick=\"showRepleyCmt"+objCmtR.getId()+"()\" title=\"Gửi trả lời\" data-value=\""+objCmtR.getId()+"\"  href=\"javascript: void(0);\">Gửi trả lời</a>\r\n"
											+ "		                    		</li>\r\n"
											+ "		                    		<li class=\"sapo-review-useful\">\r\n"
											+ "			                    		<a id=\"like"+objCmtR.getId()+"\" class=\"ajax-show-likecomment"+objCmtR.getId()+"\" title=\"Hữu ích\" href=\"javascript: void(0);\">\r\n"
											+ "			                    		<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
											+ "			                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
									if(like_comment>0) {
													response.getWriter().print( ""+like_comment+" Hữu ích</a>\r\n");
									}else {
													response.getWriter().print( " Hữu ích</a>\r\n");
									}
													response.getWriter().print( "</li>\r\n"
											+ "		                    		<li class=\"sapo-review-reportreview\">\r\n"
											+ "			                    		<a id=\"report"+objCmtR.getId()+"\" title=\"Báo cáo sai phạm\" href=\"javascript: void(0);\">\r\n"
											+ "			                    		<svg class=\"icon-warning\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\">\r\n"
											+ "			                    		<path d=\"M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z\"></path>\r\n"
											+ "			                    		<path d=\"M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z\"></path>\r\n"
											+ "			                    		</svg>Báo cáo sai phạm</a>\r\n"
											+ "		                    		</li>\r\n"
											+ "	                    		</ul>\r\n"
											+ "                    		</div>\r\n"
											+ "                    		<div class=\"sapo-review-images\">\r\n"
											+ "	                    		<ul>\r\n");
																				if(objCmtR.getImages()!=null&&"".equals(objCmtR.getImages())==false){
												                    				String imagesRv = objCmtR.getImages();
												                    				String[] list_images_rv = imagesRv.split(",");
													                    				if(list_images_rv.length>0){
												                    					for(int i =0; i<list_images_rv.length; i++){
										
											response.getWriter().print("<li>\r\n"
											+ "			                    		<a data-fancybox=\"gallery\" href=\"\">\r\n"
											+ "			                    			<img src=\""+request.getContextPath()+"/templates/admin/assets/images/table/product/"+list_images_rv[i]+"\">\r\n"
											+ "			                    		</a>\r\n"
											+ "		                    		</li>\r\n");
												                    					}}};
											response.getWriter().print("</ul>\r\n"
											+ "                    		</div>\r\n"
											+ "							<div class=\"show-recomment\" id=\"show-recomment"+objCmtR.getId()+"\">\r\n");
											ArrayList<CommentRate> listCmtChil = objCmtRDAO.getCmtChil(sku, objCmtR.getId());
											                    					if(listCmtChil.size()>0){
											                  						for(CommentRate objCmtChil: listCmtChil){
											                  							if(objCmtChil.getLike_coment()>0) {
											                  								like_comment =objCmtChil.getLike_coment();
											                  							}
											response.getWriter().print("<h4>"+objCmtChil.getName()+"</h4>\r\n"
											+ "                    								<p>"+objCmtChil.getComment()+"</p>\r\n"
											+ "                    								<div class=\"sapo-review-actions \" style=\"color: #030d78\">\r\n"
											+ "		                    							<ul>\r\n"
											+ "								                    		<li class=\"sapo-review-useful>\r\n"
											+ "									                    		<a id=\"like"+objCmtChil.getId()+"\" class=\"ajax-show-likecomment"+objCmtChil.getId()+"\" title=\"Hữu ích\" href=\"javascript: void(0);\">\r\n"
											+ "									                    		<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
											+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
											if(like_comment>0) {
																			response.getWriter().print( ""+like_comment+" Hữu ích</a>\r\n");
											}else {
																			response.getWriter().print( " Hữu ích</a>\r\n");
											}
																			response.getWriter().print( "</li>\r\n"
											+ "								                    		<li class=\"sapo-review-reportreview\">\r\n"
											+ "									                    		<a id=\"report"+objCmtChil.getId()+"\" title=\"Báo cáo sai phạm\" href=\"javascript: void(0);\">\r\n"
											+ "									                    		<svg class=\"icon-warning\" xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 48 48\">\r\n"
											+ "									                    		<path d=\"M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z\"></path>\r\n"
											+ "									                    		<path d=\"M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z\"></path>\r\n"
											+ "									                    		</svg>Báo cáo sai phạm</a>\r\n"
											+ "								                    		</li>\r\n"
											+ "		                    							</ul>\r\n"
											+ "                    								</div>\r\n"
											+ "													<script type=\"text/javascript\">\r\n"
											+ "                    	    						var ip_address;\r\n"
											+ "                    	    						$.getJSON(\"https://api.ipify.org?format=json\", function(data) {\r\n"
											+ "                    		    			            // Setting text of element P with id gfg\r\n"
											+ "                    		    			            ip_address = data.ip;\r\n"
											+ "                    		    			           // $(\"#ipadd\").html(ipaddress);\r\n"
											+ "                    		    			        })\r\n"
											+ "                    								$('#like"+objCmtChil.getId()+"').on('click', function(){\r\n"
													+ "												alert('click');"
													+ "												var user_id = "+user_id+";\r\n"
													+ "                    				  			if(user_id>0){"
													+ "												$(this).addClass('active');"
											+ "                    	                				var id = "+objCmtChil.getId()+";\r\n"
											+ "                    	                			  	 $.ajax({	\r\n"
											+ "                    	                					url: '"+request.getContextPath()+"/public/show-like-comment',\r\n"
											+ "                    	                					type: 'POST',\r\n"
											+ "                    	                					cache: false,\r\n"
											+ "                    	                					data: {\r\n"
											+ "                    	                						aid: id,\r\n"
											+ "                    	                						aip_address: ip_address,\r\n"
											+ "                    	                									\r\n"
											+ "                    	                					},\r\n"
											+ "                    	                					success: function(data){\r\n"
											+ "                    	                						$('.ajax-show-likecomment"+objCmtChil.getId()+"').html(data);\r\n"
											+ "                    	                					},\r\n"
											+ "                    	                					error: function (){\r\n"
											+ "                    	                						alert('Có lỗi xảy ra');\r\n"
											+ "                    	                					},\r\n"
											+ "                    	                			  });\r\n"
											+ "												}else{\r\n"
											+ "                    				  				alert(\"Vui lòng đăng nhập!\");\r\n"
											+ "                    				  			}"
											+ "                    	                		});\r\n"
											+ "                    								</script>");
											                  						}};
											response.getWriter().print("</div>\r\n"
													+ "						<div class=\"re-comment\" id=\"re-comment"+objCmtR.getId()+"\" data-value=\""+objCmtR.getId()+"\">\r\n");
												              				int id_user =0;
																			if(session.getAttribute("objU")!=null){
												                      		User objU = (User) session.getAttribute("objU");
												                      		String phoneNum = "";
												                      		id_user = objU.getId();
												                      		if(objU.getPhone()!=null) {
												                      			phoneNum=objU.getPhone();
												                      		};
											response.getWriter().print("<form id=\"re-form"+objCmtR.getId()+"\" class=\"re-form\">\r\n"
											+ "                    				<div class=\"row1\">\r\n"
											+ "									    <div class=\"col colm-3\">\r\n"
											+ "											<label for=\"re-name\" class=\"error\"></label>"
											+ "									      <input type=\"text\" class=\"form-control\" id=\"re-name"+objCmtR.getId()+"\" name=\"re-name\" value=\""+objU.getFullname()+"\" placeholder=\"Tên\">\r\n"
											+ "									    </div>\r\n"
											+ "									    <div class=\"col colm-3\">\r\n"
											+ "											<label for=\"re-email\" class=\"error\"></label>"
											+ "									      <input type=\"text\" class=\"form-control\" id=\"re-email"+objCmtR.getId()+"\" name=\"re-email\" value=\""+objU.getEmail()+"\" placeholder=\"Email\">\r\n"
											+ "									    </div>\r\n"
											+ "									    <div class=\"col colm-3\">\r\n"
											+ "									      <input type=\"text\" class=\"form-control\" id=\"re-phone"+objCmtR.getId()+"\" name=\"re-phone\" value=\""+phoneNum+"\" placeholder=\"Số điện thoại(nếu có)\">\r\n"
											+ "									    </div>\r\n"
											+ "									    <div class=\"col col-sm-10\">\r\n"
											+ "										<label for=\"re-comment\" class=\"error\"></label>"
											+ "									      <input type=\"text\" class=\"form-control\" id=\"re-comments"+objCmtR.getId()+"\" name=\"re-comment\" placeholder=\"Nhập trả lời của bạn\">\r\n"
											+ "									    </div>\r\n"
											+ "									    <div class=\"col col-sm-2\">\r\n"
											+ "									      <button class=\"form-control\" id=\"recomment"+objCmtR.getId()+"\" type=\"button\">Trả Lời</button>\r\n"
											+ "									    </div>\r\n"
											+ "									</div>\r\n"
											+ "                    			</form>\r\n");
											                    			}else{
											                    				user_id=0;
											response.getWriter().print("<form id=\"re-form"+objCmtR.getId()+"\" class=\"re-form\">\r\n"
													+ "                        				<div class=\"row1\">\r\n"
													+ "    									    <div class=\"col colm-3\">\r\n"
													+ "    									    <label for=\"re-name\" class=\"error\"></label>\r\n"
													+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-name\" id=\"re-name"+objCmtR.getId()+"\" placeholder=\"Tên\">\r\n"
													+ "    									      \r\n"
													+ "    									    </div>\r\n"
													+ "    									    <div class=\"col colm-3\">\r\n"
													+ "    									    <label for=\"re-email\" class=\"error\"></label>\r\n"
													+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-email\" id=\"re-email"+objCmtR.getId()+"\" placeholder=\"Email\">\r\n"
													+ "    									    </div>\r\n"
													+ "    									    <div class=\"col colm-3\">\r\n"
													+ "    									      <input type=\"text\" class=\"form-control\" id=\"re-phone"+objCmtR.getId()+"\" name=\"re-phone\" placeholder=\"Số điện thoại(nếu có)\">\r\n"
													+ "    									    </div>\r\n"
													+ "    									    <div class=\"col col-sm-10\">\r\n"
													+ "    									    	<label for=\"re-comment\" class=\"error\"></label>\r\n"
													+ "    									      <input type=\"text\" class=\"form-control\" name=\"re-comment\" id=\"re-comments"+objCmtR.getId()+"\" placeholder=\"Nhập trả lời của bạn\">\r\n"
													+ "    									    </div>\r\n"
													+ "    									    <div class=\"col col-sm-2\">\r\n"
													+ "    									      <button class=\"form-control\" id=\"recomment"+objCmtR.getId()+"\" type=\"button\">Trả Lời</button>\r\n"
													+ "    									    </div>\r\n"
													+ "    									</div>\r\n"
													+ "                        			</form>\r\n");
											                    			};
													response.getWriter().print("</div>\r\n"
											+ "                    		<script type=\"text/javascript\">\r\n"
											+ "								$('#re-form"+objCmtR.getId()+"').validate({\r\n"
											+ "                    			\r\n"
											+ "                    	   		ignore:[], //validate cho Textarea.\r\n"
											+ "                    				rules:{\r\n"
											+ "                    					're-name':{\r\n"
											+ "                    						required: true,\r\n"
											+ "                    						minlength:6,\r\n"
											+ "                    					},\r\n"
											+ "                    					're-email':{\r\n"
											+ "                    						required: true,\r\n"
											+ "                    						email: true,\r\n"
											+ "                    					},\r\n"
											+ "                    					're-comment':{\r\n"
											+ "                    						required: true,\r\n"
											+ "                    						minlength:10,\r\n"
											+ "                    					},\r\n"
											+ "                    					\r\n"
											+ "                    				},\r\n"
											+ "                    				\r\n"
											+ "                    				messages:{\r\n"
											+ "                    					're-name':{\r\n"
											+ "                    						required: \" Hãy nhập vào tên.\",\r\n"
											+ "                    						minlength:\" Hãy nhập nhiều hơn 6 ký tự .\",\r\n"
											+ "                    					},\r\n"
											+ "                    					're-email':{\r\n"
											+ "                    						required: \" Hãy nhập vào email\",\r\n"
											+ "                    						email:\" Hãy nhập đúng định dạng.\",\r\n"
											+ "                    					},\r\n"
											+ "                    					're-comment':{\r\n"
											+ "                    						required: \" Hãy nhập vào trả lời của bạn.\",\r\n"
											+ "                    						minlength:\" Hãy nhập nhiều hơn 10 ký tự.\",\r\n"
											+ "                    					},\r\n"
											+ "                    				},\r\n"
											+ "                    	      });"
											+ "								$('#recomment"+objCmtR.getId()+"').on('click', function(){\r\n"
											+ "                    			$('#re-form"+objCmtR.getId()+"').valid();\r\n"
											+ "                    				if($('#re-form"+objCmtR.getId()+"').valid()==true){\r\n"
											+ "                    					var id = "+objCmtR.getId()+";\r\n"
											+ "                    					var sku = '"+objCmtR.getProduct_sku()+"';\r\n"
											+ "                    			  		var comment = $(\"#re-comments"+objCmtR.getId()+"\").val();\r\n"
											+ "                    			  		var fullname = $(\"#re-name"+objCmtR.getId()+"\").val();\r\n"
											+ "                    			  		var email = $(\"#re-email"+objCmtR.getId()+"\").val();\r\n"
											+ "                    			  		var phone = $(\"#re-phone"+objCmtR.getId()+"\").val();\r\n"
											+ "                    			  		var user_id = "+id_user+";\r\n"
											+ "                    			  	 			$.ajax({	\r\n"
											+ "                    								url: '"+request.getContextPath()+"/public/show-recomment',\r\n"
											+ "                    								type: 'POST',\r\n"
											+ "                    								cache: false,\r\n"
											+ "                    								data: {"
											+ "														aid: id,\r\n"
											+ "                    									asku: sku,\r\n"
											+ "                    									acomment: comment,\r\n"
											+ "                    									afullname: fullname,\r\n"
											+ "														aemail: email,\r\n"
											+ "                    									aphone: phone,\r\n"
											+ "                    									auser_id: user_id,"
											+ "															},\r\n"
											+ "                    								success: function(data){\r\n"
											+ "                    									$('#show-recomment"+objCmtR.getId()+"').html(data);\r\n"
											+ "                    									$('#re-comment"+objCmtR.getId()+"').hide();\r\n"
											+ "                    									\r\n"
											+ "                    								},\r\n"
											+ "                    								error: function (){\r\n"
											+ "                    									alert('Có lỗi xảy ra');\r\n"
											+ "                    								},\r\n"
											+ "                    			  			});\r\n"
											+ "                    				}else{\r\n"
											+ "                    					$('#re-form"+objCmtR.getId()+"').valid();\r\n"
											+ "                    				}\r\n"
											+ "                    			  		\r\n"
											+ "                    		});"
											+ "	                    		function showRepleyCmt"+objCmtR.getId()+"(){\r\n"
											+ "	    							$('#re-comment"+objCmtR.getId()+"').toggle();\r\n"
											+ "	    						};\r\n"
											+ "							var ip_address;\r\n"
											+ "	    					$.getJSON(\"https://api.ipify.org?format=json\", function(data) {\r\n"
											+ "	    			            // Setting text of element P with id gfg\r\n"
											+ "	    			            ip_address = data.ip;\r\n"
											+ "	    			           // $(\"#ipadd\").html(ipaddress);\r\n"
											+ "	    			        })\r\n"
											+ "	    					$('#like"+objCmtR.getId()+"').on('click', function(){\r\n"
													+ "								var user_id = "+user_id+";\r\n"
													+ "                    			if(user_id>0){"
													+ "									$(this).addClass('active');"
											+ "	                    				var id = "+objCmtR.getId()+";\r\n"
											+ "	                    			  	 $.ajax({	\r\n"
											+ "	                    					url: '"+request.getContextPath()+">/public/show-like-comment',\r\n"
											+ "	                    					type: 'POST',\r\n"
											+ "	                    					cache: false,\r\n"
											+ "	                    					data: {\r\n"
											+ "	                    						aid: id,\r\n"
											+ "	                    						aip_address: ip_address,\r\n"
											+ "	                    									\r\n"
											+ "	                    					},\r\n"
											+ "	                    					success: function(data){\r\n"
											+ "	                    						$('.ajax-show-likecomment"+objCmtR.getId()+"').html(data);\r\n"
											+ "	                    					},\r\n"
											+ "	                    					error: function (){\r\n"
											+ "	                    						alert('Có lỗi xảy ra');\r\n"
											+ "	                    					},\r\n"
											+ "	                    			  });\r\n"
											+ "								}else{\r\n"
											+ "                    				alert(\"Vui lòng đăng nhập!\");\r\n"
											+ "                    			}"
											+ "	                    		});"
											+ "                    		</script>\r\n"
											+ "                    	</div>");
								}
							}
						}
					}
				}
			}
		}
	}
}
