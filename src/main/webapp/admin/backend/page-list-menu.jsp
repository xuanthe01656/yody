<%@page import="jakarta.el.ArrayELResolver"%>
<%@page import="java.io.File"%>
<%@page import="model.dao.MenuDAO"%>
<%@page import="model.bean.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <%@ include file="/templates/admin/inc/leftbar.jsp" %>
      <%@ include file="/templates/admin/inc/header.jsp" %> 
      <div class="modal fade" id="new-order" tabindex="-1" role="dialog" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered" role="document">
              <div class="modal-content">
                  <div class="modal-body">
                      <div class="popup text-left">
                          <h4 class="mb-3">New Order</h4>
                          <div class="content create-workform bg-body">
                              <div class="pb-3">
                                  <label class="mb-2">Email</label>
                                  <input type="text" class="form-control" placeholder="Enter Name or Email">
                              </div>
                              <div class="col-lg-12 mt-4">
                                  <div class="d-flex flex-wrap align-items-ceter justify-content-center">
                                      <div class="btn btn-primary mr-4" data-dismiss="modal">Cancel</div>
                                      <div class="btn btn-outline-primary" data-dismiss="modal">Create</div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>      
      <div class="content-page">
     <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="d-flex flex-wrap align-items-center justify-content-between mb-4">
                    <div>
                        <h4 class="mb-3">Category List</h4>
                        <p class="mb-0">Use category list as to describe your overall core business from the provided list.</p>
                    </div>
                    <a href="<%=request.getContextPath() %>/admin/backend/page-add-category.jsp" class="btn btn-primary add-list"><i class="las la-plus mr-3"></i>Add Category</a>
                </div>
            </div>
        </div>
        <div class="row">
        			<%
                    	MenuDAO objMDAO = new MenuDAO();
        				ArrayList<Menu> listMenuParent = objMDAO.getItemMenu();
        				int id=0;
        				int msg=0;
        				if(request.getParameter("msg")!=null){
        					msg = Integer.parseInt(request.getParameter("msg"));
        					if(msg==3){
        						%>
                    			<div><span style="background-color: yellow; color: red;">Xóa danh mục thành công!</span></div>
                    			<%
        					}else{
        						if(msg==4){
        							%>
                        			<div><span style="background-color: yellow; color: red;">Xóa danh mục không thành công!</span></div>
                        			<%
        						}
        					}
        				}
        				if(request.getParameter("id")!=null&&request.getParameter("msg")!=null){
        				 id = Integer.parseInt(request.getParameter("id"));
        				 msg = Integer.parseInt(request.getParameter("msg"));}
        					if(listMenuParent.size()>0){
                				for(Menu menuParent: listMenuParent){
                    %>
            <div class="col-lg-12">
                <div class="table-responsive rounded mb-3">
                <table class="table table-bordered mb-0">
                    <thead class="bg-white text-uppercase">
                    
                        <tr class="ligth ligth-data">
                            <th>
                            	<div id="target1" class="ajax-data<%=menuParent.getId()%>"></div>
                            	<%
                            		Admin admin = (Admin) session.getAttribute("objUAd");
                            		if(msg==1&&id==menuParent.getId()){
                            			%>
                            			<div><span style="background-color: yellow; color: red;">Cập nhật danh mục thành công!</span></div>
                            			<%
                            		}else{
                            			if(msg==2&&id==menuParent.getId()){
                            				%>
                                			<div><span style="background-color: yellow; color: red;">Cập nhật danh mục không thành công!</span></div>
                                			<%
                            			}
                            		}
                            	%>
                            	<p id="text<%=menuParent.getId() %>" class="fw-bold text-dark"><%=menuParent.getName() %></p>
                            	<Input id="text<%=menuParent.getId() %>_input" style="display:none"/>
                            	<a class="badge bg-success mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit" href="<%=request.getContextPath()%>/admin/edit-categories?id=<%=menuParent.getId()%>">
							         <i class="ri-pencil-line mr-0"></i>
							    </a>
	                             <!--<a class="badge bg-warning mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Delete" href="<%=request.getContextPath()%>/admin/delete-categories?id=<%=menuParent.getId()%>" onclick="return confirm('Nếu xóa danh mục này sẽ xóa toàn bộ danh mục có liên quan. Bạn thật sự muốn xóa ?')">
	                                 <i class="ri-delete-bin-line mr-0"></i>
	                             </a>  --> 
	                             <script type="text/javascript">
	                             $( "#text<%=menuParent.getId()%>" ).dblclick(function() {
										
							    	  $( "#text<%=menuParent.getId()%>").hide();
							    	  $( "#text<%=menuParent.getId()%>_input" ).val($( "#text<%=menuParent.getId()%>" ).html()); // Copies the text of the span to the input box.
							    	  $( "#text<%=menuParent.getId()%>_input" ).show();
							    	  $( "#text<%=menuParent.getId()%>_input" ).focus();
							    	  
							    	});
							
							    	// What to do when user changes the text of the input
							    	function textChanged(){
							
							    	  $( "#text<%=menuParent.getId()%>_input" ).hide();
							    	  $( "#text<%=menuParent.getId()%>").html($( "#text<%=menuParent.getId()%>_input" ).val()); // Copies the text of the input box to the span.
							    	  $( "#text<%=menuParent.getId()%>" ).show();
							    	  	var id=<%=menuParent.getId()%>;
							    	  	var name = $( "#text<%=menuParent.getId()%>_input" ).val();
							    	  // Here update the database
							    	  $.ajax({
                        					url: '<%=request.getContextPath()%>/admin/handel-name-categories',
                        					type: 'POST',
                        					cache: false,
                        					data: {
                        						aid: id,
                        						aname: name
                        					},
                        					
                        					success: function(data){
                        						$('.ajax-data<%=menuParent.getId()%>').html(data);
                        					},
                        					error: function (){
                        						alert('Có lỗi xảy ra');
                        					},
                        				});
							    	      
							    	}
							
							    	// On blur and on enter pressed, call the textChanged function
							    	$( "#text<%=menuParent.getId()%>_input" ).blur(textChanged);
							    	$( "#text<%=menuParent.getId()%>_input" ).keypress(function (e) {
							    	 var key = e.which;
							    	 if(key == 14)  // the enter key code
							    	  {
							    	    textChanged();
							    	    return false;  
							    	  }
							    	});
							    	
	                             </script>
                            </th>
                        </tr>
                    </thead>
                    <tbody class="ligth-body">
                        <tr>                      	
							 <th >
							 	<div class="row">
							 	<%
					         	ArrayList<Menu> listMenuChilLv1 = objMDAO.getItemChilMenu(menuParent.getId());
							 	
					        	if(listMenuChilLv1.size()>0){
					                for(Menu menuChilLv1: listMenuChilLv1){
			                 %>
								 	<div id="target2" class="col-sm-12 my-2 py-2 text-center border text-bg-primary">
								 		<div class="ajax-data<%=menuChilLv1.getId()%>"></div>
								 		<%
                            		if(msg==1&&id==menuChilLv1.getId()){
                            			%>
                            			<div><span style="background-color: yellow; color: red;">Cập nhật danh mục thành công!</span></div>
                            			<%
                            		}else{
                            			if(msg==2&&id==menuChilLv1.getId()){
                            				%>
                                			<div><span style="background-color: yellow; color: red;">Cập nhật danh mục không thành công!</span></div>
                                			<%
                            			}
                            		}
                            	%>
								 		<p id="text<%=menuChilLv1.getId() %>" class="text-dark"><%=menuChilLv1.getName() %></p>
								 		<Input id="text<%=menuChilLv1.getId() %>_input" style="display:none"/>
								 		<%
								 			if(admin.getPosition_id()==1){
								 		%>
								         <a class="badge bg-success mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit" href="<%=request.getContextPath()%>/admin/edit-categories?id=<%=menuChilLv1.getId()%>">
								            <i class="ri-pencil-line mr-0"></i>
								          </a>
		                                  <a class="badge bg-warning mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Delete" href="<%=request.getContextPath()%>/admin/delete-categories?id=<%=menuChilLv1.getId()%>" onclick="return confirm('Nếu xóa danh mục này sẽ xóa toàn bộ danh mục có liên quan. Bạn thật sự muốn xóa ?')">
		                                    <i class="ri-delete-bin-line mr-0"></i>
		                                  </a> 
		                                  <%}else{%>
		                                  	<a class="badge bg-success mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit" href="<%=request.getContextPath()%>/admin/edit-categories?id=<%=menuChilLv1.getId()%>">
								            <i class="ri-pencil-line mr-0"></i>
								          </a>
		                                  <%} %>
		                                  <script type="text/javascript">
		                                  $( "#text"+<%=menuChilLv1.getId()%> ).dblclick(function() {
												
									    	  $( "#text<%=menuChilLv1.getId()%>" ).hide();
									    	  $( "#text<%=menuChilLv1.getId()%>_input" ).val($( "#text<%=menuChilLv1.getId()%>" ).html()); // Copies the text of the span to the input box.
									    	  $( "#text<%=menuChilLv1.getId()%>_input" ).show();
									    	  $( "#text<%=menuChilLv1.getId()%>_input" ).focus();
									    	  
									    	});
									
									    	// What to do when user changes the text of the input
									    	function textChanged(){
									
									    	  $( "#text<%=menuChilLv1.getId()%>_input" ).hide();
									    	  $( "#text<%=menuChilLv1.getId()%>").html($( "#text<%=menuChilLv1.getId()%>_input" ).val()); // Copies the text of the input box to the span.
									    	  $( "#text<%=menuChilLv1.getId()%>" ).show();
									    	  	var id = <%=menuChilLv1.getId()%>;
									    	  	var name = $( "#text<%=menuChilLv1.getId()%>_input" ).val(); 
									    	  // Here update the database
									    	  $.ajax({
                              					url: '<%=request.getContextPath()%>/admin/handel-name-categories',
                              					type: 'POST',
                              					cache: false,
                              					data: {
                              						aid: id,
                              						aname: name
                              					},
                              					success: function(data){
                              						$('.ajax-data<%=menuChilLv1.getId()%>').html(data);
                              					},
                              					error: function (){
                              						alert('Có lỗi xảy ra');
                              					},
                              				});
									    	      
									    	}
									
									    	// On blur and on enter pressed, call the textChanged function
									    	$( "#text<%=menuChilLv1.getId()%>_input" ).blur(textChanged);
									    	$( "#text<%=menuChilLv1.getId()%>_input" ).keypress(function (e) {
									    	 var key = e.which;
									    	 if(key == 14)  // the enter key code
									    	  {
									    	    textChanged();
									    	    return false;  
									    	  }
									    	});
		                                  </script>
								 	</div>
								 	<%
					            	 ArrayList<Menu> listMenuChilLv2 = objMDAO.getItemChilMenu(menuChilLv1.getId());
								 	
					        	if(listMenuChilLv2.size()>0){
					                for(Menu menuChilLv2: listMenuChilLv2){
			                		 %>
								 	<div id="target3" class="col-sm-4 px-2 border fw-normal">
								 		<div class="row">
								 			<div class="col-sm-6">
								 				<div class="ajax-data<%=menuChilLv2.getId()%>"></div>
								 				<%
                            		if(msg==1&&id==menuChilLv2.getId()){
                            			%>
                            			<div><span style="background-color: yellow; color: red;">Cập nhật danh mục thành công!</span></div>
                            			<%
                            		}else{
                            			if(msg==2&&id==menuChilLv2.getId()){
                            				%>
                                			<div><span style="background-color: yellow; color: red;">Cập nhật danh mục không thành công!</span></div>
                                			<%
                            			}
                            		}
                            	%>
								 				<p id="text<%=menuChilLv2.getId()%>" class="pt-3" ><%=menuChilLv2.getName() %></p>
								 				<Input id="text<%=menuChilLv2.getId() %>_input" name="pic" style="display:none"/>
								 			</div>
								 			<div class="col-sm-3 pt-2">
								 				<% 
								 					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/category/");
								 				
								 					File image = new File(path+menuChilLv2.getImgaes());
								 					
		                  							if(image.exists()&&menuChilLv2.getImgaes()!=null){
								 				%>
								 				
								 				<img id="image<%=menuChilLv2.getId() %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/category/<%=menuChilLv2.getImgaes() %>" class="img-fluid rounded-circle avatar-50 mr-3 " style="max-width: 30px; max-height: 45px;" alt="image">
								 				<%}else{%>
								 					<img id="image<%=menuChilLv2.getId() %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/category/no-image-png-2.png" class="img-fluid rounded-circle avatar-50 mr-3" style="max-width: 30px; max-height: 45px;" alt="image">
								 				<%} %>
								 				<Input class="pt-3" type="file" id="image<%=menuChilLv2.getId() %>_input" name="pic" style="display:none"/>	
								 			</div>
								 			<div class="col-sm-3 pt-3">
								 				<%
								 			if(admin.getPosition_id()==1){
								 		%>
								         <a class="badge bg-success mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit" href="<%=request.getContextPath()%>/admin/edit-categories?id=<%=menuChilLv2.getId()%>">
								            <i class="ri-pencil-line mr-0"></i>
								          </a>
		                                  <a class="badge bg-warning mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Delete" href="<%=request.getContextPath()%>/admin/delete-categories?id=<%=menuChilLv2.getId()%>" onclick="return confirm('Nếu xóa danh mục này sẽ xóa toàn bộ danh mục có liên quan. Bạn thật sự muốn xóa ?')">
		                                    <i class="ri-delete-bin-line mr-0"></i>
		                                  </a> 
		                                  <%}else{%>
		                                  	<a class="badge bg-success mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit" href="<%=request.getContextPath()%>/admin/edit-categories?id=<%=menuChilLv2.getId()%>">
								            <i class="ri-pencil-line mr-0"></i>
								          </a>
		                                  <%} %>
								 			</div>
								 			<script type="text/javascript">
												    	$( "#text<%=menuChilLv2.getId()%>" ).dblclick(function() {
													    	  $( "#text<%=menuChilLv2.getId()%>" ).hide();
													    	  $( "#text<%=menuChilLv2.getId()%>_input" ).val($( "#text<%=menuChilLv2.getId()%>" ).html()); // Copies the text of the span to the input box.
													    	  $( "#text<%=menuChilLv2.getId()%>_input" ).show();
													    	  $( "#text<%=menuChilLv2.getId()%>_input" ).focus();
													    	  
													    	});
													
													    	// What to do when user changes the text of the input
													    	function textChanged(){
													
													    	  $( "#text<%=menuChilLv2.getId()%>_input" ).hide();
													    	  $( "#text<%=menuChilLv2.getId()%>" ).html($( "#text<%=menuChilLv2.getId()%>_input" ).val()); // Copies the text of the input box to the span.
													    	  $( "#text<%=menuChilLv2.getId()%>" ).show();
													    	      var id = <%=menuChilLv2.getId()%>;
													    	      var name = $("#text<%=menuChilLv2.getId()%>_input" ).val();
													    	  // Here update the database
													    	  $.ajax({
					                              					url: '<%=request.getContextPath()%>/admin/handel-name-categories',
					                              					type: 'POST',
					                              					cache: false,
					                              					data: {
					                              						aid: id,
					                              						aname: name
					                              					},
					                              					success: function(data){
					                              						$('.ajax-data<%=menuChilLv2.getId()%>').html(data);
					                              					},
					                              					error: function (){
					                              						alert('Có lỗi xảy ra');
					                              					},
					                              				});     
													    	}
													    	// On blur and on enter pressed, call the textChanged function
													    	$( "#text<%=menuChilLv2.getId()%>_input" ).blur(textChanged);
													    	$( "#text<%=menuChilLv2.getId()%>_input" ).keypress(function (e) {
													    	 var key = e.which;
													    	 if(key == 14)  // the enter key code
													    	  {
													    	    textChanged();
													    	    return false;  
													    	  }
													    	});

													    	$( "#image<%=menuChilLv2.getId()%>" ).dblclick(function() {
													    			$( "#image<%=menuChilLv2.getId()%>" ).hide();  
														    	  $( "#image<%=menuChilLv2.getId()%>_input" ).show();
														    	  $( "#image<%=menuChilLv2.getId()%>_input" ).focus(); 
														    	});
														    	// What to do when user changes the text of the input

														    	$( "input[type=file]#image<%=menuChilLv2.getId()%>_input" ).change(function(e) {
														    		var filename_input = e.target.files[0].name;
														    		var src = $("#image<%=menuChilLv2.getId()%>").attr("src");
															    	var path = src.substring(0,src.lastIndexOf('/'));									    		
															    	var filename = src.split("/").pop();
															    	var newSrc = path+"/"+filename_input;
															    	$("#image<%=menuChilLv2.getId()%>").attr("src",newSrc);
														    		
															    	  var formData = new FormData();
															    	  	formData.append('aid', <%=menuChilLv2.getId()%>);
															    		formData.append("pic", image<%=menuChilLv2.getId() %>_input.files[0]);
															    	  $.ajax({	
							                              					url: '<%=request.getContextPath()%>/admin/handel-images-categories',
							                              					type: 'POST',
							                              					cache: false,
							                              					contentType: false,
							                              					data: formData,
							                              					processData: false,
							                              					success: function(data){
							                              						$('.ajax-data<%=menuChilLv2.getId()%>').html(data);
							                              						
							                              					},
							                              					error: function (){
							                              						alert('Có lỗi xảy ra');
							                              					},
							                              				});
															    	  $( "#image<%=menuChilLv2.getId()%>_input" ).hide();
															    	  $( "#image<%=menuChilLv2.getId()%>" ).show();
															    	  
														    	});
														    	
														    	
										</script>						
								 		</div>	
								 		 <script type="text/javascript">
								 		$(window).load(function () {
								            var aid = id;
								            var amsg = msg;
								            if(aid==<%=menuParent.getId()%>&&amsg=1){
								            	$('body').scrollTo('#target1');
								            }else{
								            	if(aid==<%=menuParent.getId()%>&&amsg=2){
								            		$('body').scrollTo('#target1');
								            	}
								            }
								            if(aid==<%=menuChilLv1.getId()%>&&amsg=1){
								            	$('body').scrollTo('#target2');
								            }else{
								            	if(aid==<%=menuChilLv1.getId()%>&&amsg=2){
								            		$('body').scrollTo('#target2');
								            	}
								            }
								            if(aid==<%=menuChilLv2.getId()%>&&amsg=1){
								            	$('body').scrollTo('#target3');
								            }else{
								            	if(aid==<%=menuChilLv2.getId()%>&&amsg=2){
								            		$('body').scrollTo('#target3');
								            	}
								            }
								        });
								 		 </script>											     
	                              </div>
								 	<%}}} %>
							    </div>  
	                                  
							 </th>
							 
							 <%}%>
                        </tr>                                                                  	                      
                    </tbody>
                </table>
            </div>        
        </div>
        <%}} %>
    </div>
    
        <!-- Page end  -->
    </div>
    <!-- Modal Edit -->
      </div>
    </div>
    <!-- Wrapper End-->
         <%@ include file="/templates/admin/inc/footer.jsp" %> 