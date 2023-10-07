<%@page import="model.bean.Position"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.PositionDAO"%>
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
                            <h4 class="card-title">Add Users</h4>
                        </div>
                    </div>
                    <div class="card-body">
                    <%
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									if(msg==1){
										%>
										<div><span style="background-color: yellow; color: red; font-style: italic;">Thêm User thành công!</span></div>
										<%
									}else{
										if(msg==2){
										%>
										<div><span style="background-color: yellow; color: red; font-style: italic;">Thêm User không thành công!</span></div>
										<%
										}
									}
								}
							%>
                        <form id="form-add-user" action="<%=request.getContextPath() %>/admin/add-users" method="Post" data-toggle="validator">
                            <div class="row"> 
                                <div class="col-md-6">                      
                                    <div class="form-group">
                                        <label>Name *</label>
                                        <input type="text" name="fullname" class="form-control" placeholder="Enter Name" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>    
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Phone Number *</label>
                                        <input type="text" name="phone" class="form-control" placeholder="Enter Phone No" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Email *</label>
                                        <input type="text" id="emailU" name="email" class="form-control" placeholder="Enter Email" required>
                                    	<div class="ajax-emailU"></div>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Gender</label>
                                        <select name="gender" class="selectpicker form-control" data-style="py-0">
                                            <option>Male</option>
                                            <option>Female</option>
                                        </select>
                                    </div>
                                </div> 
                                <div class="col-md-6">                      
                                    <div class="form-group">
                                        <label>User Name *</label>
                                        <input type="text" id="usernameU" name="username" class="form-control" placeholder="Enter User Name">
                                       <div class="ajax-usernameU"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">                      
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input type="text" name="password" class="form-control" placeholder="Enter Password" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>   
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Position</label>
                                        <select name="position" class="selectpicker form-control" data-style="py-0">
                                        <%
                                        	PositionDAO objPsDAO = new PositionDAO();
                                        	ArrayList<Position> listPosition = objPsDAO.GetPosition();
                                        	if(listPosition.size()>0){
                                        		for(Position objPs: listPosition){
                                        %>
                                            <option value="<%=objPs.getId()%>"><%=objPs.getPosition() %></option>
                                            <%}} %>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Position Detail</label>
                                       	<input type="text" name="position_detail" class="form-control" placeholder="Enter Position Detail" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>           
                            </div>                            
                            <button type="submit" class="btn btn-primary mr-2">Add User</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </form>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
            $('#form-add-user').validate({
            	ignore:[], //validate cho Textarea.
    			rules:{
    				
    				username :{
    					required: true,
    					minlength:5,
    				},
    				email:{
    					required: true,
    					email: true,
    					},
    				
    			},
    			
    			messages:{
    				username:{
    					required: " Hãy nhập vào tên.",
    					minlength:" Hãy nhập ít nhất 5 ký tự .",
    				},
    				email:{
    					required: " Hãy nhập vào email",
    					email:" Hãy nhập đúng định dạng.",
    				},
    			},
            	
            });
            	$('#emailU').on('input', function(){
            		 var changedField = $(this);
				     var email = $("#emailU").val();   
				        $.ajax({
							url: '<%=request.getContextPath()%>/admin/add-users',
							type: 'POST',
							cache: false,
							data: {
									aemail: email,
								},
							success: function(data){
								$('.ajax-emailU').html(data);
							},
							error: function (){
								alert('Có lỗi xảy ra');
							},
						});
						return false;
            	});
            	$('#usernameU').on('input', function(){
           		 var changedField = $(this);
				     var username= $('#usernameU').val();    
				        $.ajax({
							url: '<%=request.getContextPath()%>/admin/add-users',
							type: 'POST',
							cache: false,
							data: {
									ausername: username,
								},
							success: function(data){
								$('.ajax-usernameU').html(data);
							},
							error: function (){
								alert('Có lỗi xảy ra');
							},
						});
						return false;
           	});
            </script>
        </div>
        <!-- Page end  -->
    </div>
      </div>
    </div>
    <!-- Wrapper End-->
         <%@ include file="/templates/admin/inc/footer.jsp" %> 