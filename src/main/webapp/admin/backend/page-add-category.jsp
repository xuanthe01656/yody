<%@page import="model.bean.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.MenuDAO"%>
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
      </div>      <div class="content-page">
     <div class="container-fluid add-form-list">
        <div class="row">
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-header d-flex justify-content-between">
                        <div class="header-title">
                            <h4 class="card-title">Add category</h4>
                        </div>
                    </div>
                    
                    <div class="card-body">
                    <%
                    if(request.getParameter("msg")!=null){
                		int msg = Integer.parseInt(request.getParameter("msg"));
                		if(msg==1){
                			%>
                			<div><span style="background-color: yellow; color: green;">Thêm danh mục thành công!</span></div>
                			<%
                		}else{
                			if(msg==2){
                			%>
                			<div><span style="background-color: yellow; color: red;">Thêm danh mục thất bại!</span></div>
                			<%
                			}
                		}
                    }
                    	%>
                    <div class="row">
                    		<button type="button" id="btn-add" class="btn btn-primary my-2">Add Menu Category</button>
                    		<div class="col-md-12" id="form-show" style="display: none;">
                    		<form class="form" id="form" action="<%=request.getContextPath() %>/admin/add-cat" method="post" enctype="multipart/form-data" data-toggle="validator">
                            <div class="row">  
                            	<div class="col-md-12">
                                    <div class="form-group">
                                        <label>Category *</label>
                                        <div class="ajax-dataname"></div>
                                        <input type="text" id="menucategory" class="form-control" name = "menucategory" value="" placeholder="Enter your category">
                                        <label for="menucategory" class="error" style="color: red;"></label>
                                    </div>
                                </div>                                
                                <script type="text/javascript">
                                  $(".form").validate({
                        			
                        			rules:{
                        				menucategory:{
                        					required: true,
                        					minlength: 2,
                        					maxlength: 50,
                        				}	
                        			},
                          		messages:{
                          			menucategory: {
                        					required: " Hãy nhập vào category.",
                        					minlength: " Hãy nhập vào nhiều hơn 2 ký tự và ít hơn 50 ký tự.",
                        					maxlength: " Hãy nhập vào ít hơn 50 ký tự.",
                        				}
                          		},
                          	});
                                $(function() {
                                	jQuery('#menucategory').keyup(function() {
                                		$(this).val($(this).val().toUpperCase());
                                	});
                        		    $('#menucategory').on('keyup', function() {                      		    	
                            		        var changedField = $(this);
                            		        var name = $("#menucategory").val();
                            		        var parent_id = 0;                           		                    
                            		        $.ajax({
                            					url: '<%=request.getContextPath()%>/admin/handel-name-category-ajax',
                            					type: 'POST',
                            					cache: false,
                            					data: {
                            						aparent_id: parent_id,
                            						aname: name},
                            					success: function(data){
                            						$('.ajax-dataname').html(data);
                            					},
                            					error: function (){
                            						alert('Có lỗi xảy ra');
                            					},
                            				});
                            				return false;
                            		    });
                        		});
                                </script>                                               
                            </div>                            
                            <button type="submit" class="btn btn-primary mr-2">Add category</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </form>
                        </div>
                        
                        
                            <button type="button" id="btn-add2" class="btn btn-danger my-2">Add Menu Category Detail</button>
                            <div class="col-md-12" id="form-show2" style="display: none;">
                            <%
                            MenuDAO objMDAO = new MenuDAO();
                            %>
                            <form class="form1" id="form2" action="<%=request.getContextPath() %>/admin/add-cat" method="post" enctype="multipart/form-data" data-toggle="validator">
                            <div class="row">                              
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Category Parent</label>
                                        
                                        <select id="menucategory2" name="menucategory" class="form-select" data-style="py-0">
	                                        	<option value="">--Chọn Category--</option>
	                                        	<%
	                                        	ArrayList<Menu> listMP = objMDAO.getItemMenu();
	                                        	
	                                        	if(listMP.size()>0){
	                                        		for(Menu menu: listMP){
	                                        	%>
	                                            <option value="<%=menu.getId()%>"><%=menu.getName() %></option>
	                                            <% }}%>
	                                        </select>
	                                        <label for="menucategory2" class="error" style="color: red;"></label>
                                    </div>
                                    
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Category *</label>
                                        <div class="ajax-dataname2"></div>
                                        <input id="menucategorydetail" name="menucategorydetail" value="" class="form-control" data-style="py-0" placeholder="Input your category">
                                        <label for="menucategorydetail" class="error" style="color: red;"></label>
                                    </div>
                                    <script type="text/javascript">
                                    $(".form1").validate({
                            			
                            			rules:{
                            				menucategorydetail:{
                            					required: true,
                            					minlength: 2,
                            					maxlength: 50,
                            				},
                            				menucategory:{
                            					required: true,
                            				}
                            			},
                              		messages:{
                              			menucategorydetail: {
                            					required: " Hãy nhập vào category.",
                            					minlength: " Hãy nhập vào nhiều hơn 2 ký tự và ít hơn 50 ký tự.",
                            					maxlength: " Hãy nhập vào ít hơn 50 ký tự.",
                            				},
                            				menucategory:{
                            					required: "Vui lòng chọn",
                            				}
                              		},
                              	});
                                $(function() {
                                	jQuery('#menucategorydetail').keyup(function() 
                                        	{
                                        		var str = jQuery('#menucategorydetail').val();
                                        		var spart = str.split(" ");
                                        		for ( var i = 0; i < spart.length; i++ )
                                        		{
                                        			var j = spart[i].charAt(0).toUpperCase();
                                        			spart[i] = j + spart[i].substr(1);
                                        		}
                                              jQuery('#menucategorydetail').val(spart.join(" "));
                                        	
                                       	});
                        		    $('#menucategorydetail').on('keyup', function() {                      		    	
                            		        var changedField = $(this);
                            		        var name = $("#menucategorydetail").val();
                            		        var parent_id = $("#menucategory2").val();                           		                    
                            		        $.ajax({
                            					url: '<%=request.getContextPath()%>/admin/handel-name-category-ajax',
                            					type: 'POST',
                            					cache: false,
                            					data: {
                            						aparent_id: parent_id,
                            						aname: name},
                            					success: function(data){
                            						$('.ajax-dataname2').html(data);
                            					},
                            					error: function (){
                            						alert('Có lỗi xảy ra');
                            					},
                            				});
                            				return false;
                            		    });
                        		});
                                </script>             
                                </div>                                                    
                            </div>                            
                            <button type="submit" class="btn btn-primary mr-2">Add category</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </form>
                        </div>
                        
                        
                            <button type="button" id="btn-add3" class="btn btn-success my-2">Add Category</button>
                            <div class="col-md-12" id="form-show3" style="display: none;">
                            <form class="form2" id="form3" action="<%=request.getContextPath() %>/admin/add-cat" method="post" enctype="multipart/form-data" data-toggle="validator" >
                            <div class="row">  
                            	
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Menu Category</label>
                                        
                                        <select id="menu" name="menucategory" class="form-select" data-style="py-0">
	                                        	<option value="">--Chọn Category--</option>
	                                        	<%	                                        	
	                                        	ArrayList<Menu> listMP1 = objMDAO.getItemMenu();
	                                        	if(listMP1.size()>0){
	                                        		for(Menu menu1: listMP1){	
	                                        	%>
	                                            <option value="<%=menu1.getId()%>"><%=menu1.getName() %></option>
	                                            <% }}%>
	                                        </select>
	                                        <label for="menu" class="error" style="color: red;"></label>
                                    </div>
                                    <script type="text/javascript">
		                                        $(function() {
		                                        	
		                                		    $('#menu').on('change', function() {
		                                		        var changedField = $(this);
		                                		        var menu = $("#menu").val();       
		                                		        $.ajax({
		                                					url: '<%=request.getContextPath()%>/handelMenu',
		                                					type: 'POST',
		                                					cache: false,
		                                					data: {amenu: menu},
		                                					success: function(data){
		                                						$('.ajax-data').html(data);
		                                					},
		                                					error: function (){
		                                						alert('Có lỗi xảy ra');
		                                					},
		                                				});
		                                				return false;
		                                		    });
		                                		});
	                                        </script>
                                </div>         
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Menu Category Detail</label>
                                        
                                        <select id="menucategorydetail2" name="menucategorydetail" class="form-select ajax-data" data-style="py-0">
	                                        	
	                                    </select>
	                                    <label for="menucategorydetail2" class="error" style="color: red;"></label>
                                    </div>
                                </div> 
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Category *</label>
                                        <div class="ajax-dataname3"></div>
                                        <input id="category1" name="category" value="" class="form-control" data-style="py-0" placeholder="Input your category">
                                        <label for="category1" class="error" style="color: red;"></label>
                                    </div>
                                    <script type="text/javascript">
										$(".form2").validate({
                            			
                            			rules:{
                            				category:{
                            					required: true,
                            					minlength: 2,
                            					maxlength: 50,
                            				},
                            				menucategorydetail2:{
                            					required: true,
                            				},
                            				menucategory:{
                            					required: true,
                            				}
                            			},
                              		messages:{
                              			category: {
                            					required: " Hãy nhập vào category.",
                            					minlength: " Hãy nhập vào nhiều hơn 2 ký tự và ít hơn 50 ký tự.",
                            					maxlength: " Hãy nhập vào ít hơn 50 ký tự.",
                            				},
                            				menucategorydetail2:{
                            					required: "Vui lòng chọn",
                            				},
                            				menucategory:{
                            					required: "Vui lòng chọn",
                            				}
                              		},
                              	});
		                                $(function() {
		                                	jQuery('#category1').keyup(function() 
		                                        	{
		                                        		var str = jQuery('#category1').val();
		                                        		var spart = str.split(" ");
		                                        		for ( var i = 0; i < spart.length; i++ )
		                                        		{
		                                        			var j = spart[i].charAt(0).toUpperCase();
		                                        			spart[i] = j + spart[i].substr(1);
		                                        		}
		                                              jQuery('#category1').val(spart.join(" "));
		                                        	
		                                       	});
		                        		    $('#category1').on('keyup', function() {                      		    	
		                            		        var changedField = $(this);
		                            		        var name = $("#category1").val();
		                            		        var parent_id = $("#menucategorydetail2").val();                           		                    
		                            		        $.ajax({
		                            					url: '<%=request.getContextPath()%>/admin/handel-name-category-ajax',
		                            					type: 'POST',
		                            					cache: false,
		                            					data: {
		                            						aparent_id: parent_id,
		                            						aname: name
		                            						},
		                            					success: function(data){
		                            						$('.ajax-dataname3').html(data);
		                            					},
		                            					error: function (){
		                            						alert('Có lỗi xảy ra');
		                            					},
		                            				});
		                            				return false;
		                            		    });
		                        		});
                                </script>             
                                </div>                                      
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Image</label>
                                        <input type="file" class="form-control image-file" name="pic" accept="image/*" required title="Hãy Chọn File">
                                        <label for="pic" class="error" style="color: red;" ></label>
                                    </div>
                                </div>                                                
                            </div>                            
                            <button type="submit" class="btn btn-primary mr-2">Add category</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </form>
                        </div>
                    </div> 
                    </div>
                </div>
                <script>
                $(document).ready(function(){
                	$( "#btn-add" ).click(function() {
                		$( "#form-show" ).toggle();
                		$( "#form-show2" ).hide();
                		$( "#form-show3" ).hide();
                	});
                	$( "#btn-add2" ).click(function() {
                		$( "#form-show" ).hide();
                		$( "#form-show2" ).toggle();
                		$( "#form-show3" ).hide();
                  	});
                	$( "#btn-add3" ).click(function() {
                		$( "#form-show" ).hide();
                		$( "#form-show2" ).hide();
                		$( "#form-show3" ).toggle();
                  	});
                	});
                	
                </script>
            </div>
        </div>
        <!-- Page end  -->
    </div>
     </div>
    </div>
    <!-- Wrapper End-->
         <%@ include file="/templates/admin/inc/footer.jsp" %> 