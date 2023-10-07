<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <%@ include file="/templates/admin/inc/leftbar.jsp" %>      
         <%@ include file="/templates/admin/inc/header.jsp" %> 
   <div class="ajax-change-info">    
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
               <div class="card car-transparent">
                  <div class="card-body p-0">
                     <div class="profile-image position-relative">
                        <img src="<%=request.getContextPath() %>/templates/admin/assets/images/page-img/profile.png" class="img-fluid rounded w-100" alt="profile-image">
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <%
         	Admin objAdmin = new Admin();
         	if(session.getAttribute("objUAd")!=null){
         		objAdmin =(Admin) session.getAttribute("objUAd");
         	}
         %>
         <div class="row m-sm-0 px-3">            
            <div class="col-lg-4 card-profile">
            <div class="content-page">
            	<div class="card card-block card-stretch card-height">
                  <div class="card-body">
                     <div class="d-flex align-items-center mb-3">
                        <div class="profile-img position-relative">
                           <img src="<%=request.getContextPath() %>/templates/admin/assets/images/user/<%=objAdmin.getImages() %>" class="img-fluid rounded avatar-110" alt="profile-image">
                        </div>
                        <div class="ml-3">
                           <h4 class="mb-1"><%=objAdmin.getUsername() %></h4>
                           <p class="mb-2"><%=objAdmin.getPosition_detail() %></p>
                        </div>
                     </div>
                     <ul class="list-inline p-0 m-0">
                        <li class="mb-2">
                           <div class="d-flex align-items-center">
                              <svg class="svg-icon mr-3" height="16" width="16" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                 <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                 <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                              </svg>
                              <p class="mb-0"><%=objAdmin.getAddress() %></p>   
                           </div>
                        </li>
                        <li class="mb-2">
                           <div class="d-flex align-items-center">
                              <svg class="svg-icon mr-3" height="16" width="16" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                 <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                              </svg>
                              <p class="mb-0"><%=objAdmin.getPhone() %></p>   
                           </div>
                        </li>
                        <li>
                           <div class="d-flex align-items-center">
                              <svg class="svg-icon mr-3" height="16" width="16" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                 <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                              </svg>
                              <p class="mb-0"><%=objAdmin.getEmail() %></p>   
                           </div>
                        </li>
                     </ul>
                  </div>
               </div>
            </div>
            </div>
            <div class="col-lg-8 card-profile">
            	<div class="content-page">
		      <div class="container-fluid">
		         <div class="row">
		            <div class="col-lg-12">
		               <div class="card">
		                  <div class="card-body p-0">
		                     <div class="iq-edit-list usr-edit">
		                        <ul class="iq-edit-profile d-flex nav nav-pills">
		                           <li class="col-md-3 p-0">
		                              <a class="nav-link active" data-toggle="pill" href="#personal-information">
		                              Personal Information
		                              </a>
		                           </li>
		                           <li class="col-md-3 p-0">
		                              <a class="nav-link" data-toggle="pill" href="#chang-pwd">
		                              Change Password
		                              </a>
		                           </li>
		                           <li class="col-md-3 p-0">
		                              <a class="nav-link" data-toggle="pill" href="#emailandsms">
		                              Email and SMS
		                              </a>
		                           </li>
		                           <li class="col-md-3 p-0">
		                              <a class="nav-link" data-toggle="pill" href="#manage-contact">
		                              Manage Contact
		                              </a>
		                           </li>
		                        </ul>
		                     </div>
		                  </div>
		               </div>
		            </div>
		            <div class="col-lg-12">
		               <div class="iq-edit-list-data">
		                  <div class="tab-content">
		                     <div class="tab-pane fade active show" id="personal-information" role="tabpanel">
		                        <div class="card">
		                           <div class="card-header d-flex justify-content-between">
		                              <div class="iq-header-title">
		                                 <h4 class="card-title">Personal Information</h4>
		                              </div>
		                           </div>
		                           <div class="card-body">
		                              <form class="form-admin" id="form-change-info-admin" action="<%=request.getContextPath() %>/admin/profile" method="post" data-toggle="validator" enctype="multipart/form-data">
		                                 <div class="form-group row align-items-center">
		                                    <div class="col-md-12">
		                                       <div class="profile-img-edit">
		                                          <div class="crm-profile-img-edit">
		                                             <img class="crm-profile-pic rounded-circle avatar-100" id="display_avata" src="<%=request.getContextPath() %>/templates/admin/assets/images/user/<%=objAdmin.getImages()%>" alt="profile-pic">
		                                             <div class="crm-p-image bg-primary">
		                                                <i class="las la-pen upload-button"></i>
		                                                <input class="file-upload" type="file" id="avata" name="pic" accept="image/*">
		                                             </div>
		                                          </div>                                          
		                                       </div>
		                                    </div>
		                                 </div>
		                                 <div class=" row align-items-center">
		                                    <div class="form-group col-sm-6">
		                                       <label for="fullname">Full Name:</label>
		                                       <input type="text" class="form-control" id="fullname" name="fullname" value="<%=objAdmin.getFullname() %>" required>
		                                    </div>
		                                    <div class="form-group col-sm-6">
		                                       <label for="username">User Name:</label>
		                                       <input type="text" class="form-control" id="username" name="username" disabled value="<%=objAdmin.getUsername() %>" required>
		                                    </div>
		                                    <div class="form-group col-sm-6">
		                                       <label for="phone">Phone:</label>
		                                       <input type="text" class="form-control" id="phone" name="phone" value="<%=objAdmin.getPhone() %>" required>
		                                    </div>
		                                    <div class="form-group col-sm-6">
		                                       <label for="email">Email:</label>
		                                       <input type="text" class="form-control" id="email" name="email" value="<%=objAdmin.getEmail() %>" required>
		                                       <div class="ajax-check-email"></div>
		                                    </div>
		                                    <div class="form-group col-sm-6">
		                                       <label for="city">City:</label>
		                                       <input type="text" class="form-control" id="city" value="<%=objAdmin.getAddress() %>" required>
		                                    </div>
		                                 </div>
		                                 <button type="button" id="btn-change-profile-admin" class="btn btn-primary mr-2">Update</button>
		                                 <button type="reset" class="btn iq-bg-danger">Cancel</button>
		                              </form>
		                           </div>
		                        </div>
		                     </div>
		                     <div class="tab-pane fade" id="chang-pwd" role="tabpanel">
		                        <div class="card">
		                           <div class="card-header d-flex justify-content-between">
		                              <div class="iq-header-title">
		                                 <h4 class="card-title">Change Password</h4>
		                              </div>
		                           </div>
		                           <div class="card-body">
		                              <form class="form-admin" id="form-change-password-admin" action="<%=request.getContextPath() %>/admin/profile" method="post" data-toggle="validator">
		                              <div class="ajax-change-password"></div>
		                                 <div class="form-group">
		                                 <div class="ajax-check-password"></div>
		                                    <label for="cpass">Current Password:</label>
		                                    <a href="javascript:void();" class="float-right">Forgot Password</a>
		                                    <input type="Password" class="form-control" id="cpass" name="cpass" value="" required>
		                                 </div>
		                                 <div class="form-group">
		                                    <label for="npass">New Password:</label>
		                                    <div class="ajax-check-password-new"></div>
		                                    <input type="Password" class="form-control" id="npass" name="npass" value="" required>
		                                 </div>
		                                 <div class="form-group">
		                                    <label for="vpass">Verify Password:</label>
		                                    <div class="ajax-check-password-vpass"></div>
		                                    <input type="Password" class="form-control" id="vpass" name="vpass" value="" required>
		                                 </div>
		                                 <button type="button" id="btn-change-password-admin" class="btn btn-primary mr-2">Submit</button>
		                                 <button type="reset" class="btn iq-bg-danger">Cancel</button>
		                              </form>
		                           </div>
		                        </div>
		                     </div>
		                     <div class="tab-pane fade" id="emailandsms" role="tabpanel">
		                        <div class="card">
		                           <div class="card-header d-flex justify-content-between">
		                              <div class="iq-header-title">
		                                 <h4 class="card-title">Email and SMS</h4>
		                              </div>
		                           </div>
		                           <div class="card-body">
		                              <form>
		                                 <div class="form-group row align-items-center">
		                                    <label class="col-md-3" for="emailnotification">Email Notification:</label>
		                                    <div class="col-md-9 custom-control custom-switch">
		                                       <input type="checkbox" class="custom-control-input" id="emailnotification" checked="">
		                                       <label class="custom-control-label" for="emailnotification"></label>
		                                    </div>
		                                 </div>
		                                 <div class="form-group row align-items-center">
		                                    <label class="col-md-3" for="smsnotification">SMS Notification:</label>
		                                    <div class="col-md-9 custom-control custom-switch">
		                                       <input type="checkbox" class="custom-control-input" id="smsnotification" checked="">
		                                       <label class="custom-control-label" for="smsnotification"></label>
		                                    </div>
		                                 </div>
		                                 <div class="form-group row align-items-center">
		                                    <label class="col-md-3" for="npass">When To Email</label>
		                                    <div class="col-md-9">
		                                       <div class="custom-control custom-checkbox">
		                                          <input type="checkbox" class="custom-control-input" id="email01">
		                                          <label class="custom-control-label" for="email01">You have new notifications.</label>
		                                       </div>
		                                       <div class="custom-control custom-checkbox">
		                                          <input type="checkbox" class="custom-control-input" id="email02">
		                                          <label class="custom-control-label" for="email02">You're sent a direct message</label>
		                                       </div>
		                                       <div class="custom-control custom-checkbox">
		                                          <input type="checkbox" class="custom-control-input" id="email03" checked="">
		                                          <label class="custom-control-label" for="email03">Someone adds you as a connection</label>
		                                       </div>
		                                    </div>
		                                 </div>
		                                 <div class="form-group row align-items-center">
		                                    <label class="col-md-3" for="npass">When To Escalate Emails</label>
		                                    <div class="col-md-9">
		                                       <div class="custom-control custom-checkbox">
		                                          <input type="checkbox" class="custom-control-input" id="email04">
		                                          <label class="custom-control-label" for="email04"> Upon new order.</label>
		                                       </div>
		                                       <div class="custom-control custom-checkbox">
		                                          <input type="checkbox" class="custom-control-input" id="email05">
		                                          <label class="custom-control-label" for="email05"> New membership approval</label>
		                                       </div>
		                                       <div class="custom-control custom-checkbox">
		                                          <input type="checkbox" class="custom-control-input" id="email06" checked="">
		                                          <label class="custom-control-label" for="email06"> Member registration</label>
		                                       </div>
		                                    </div>
		                                 </div>
		                                 <button type="submit" class="btn btn-primary mr-2">Submit</button>
		                                 <button type="reset" class="btn iq-bg-danger">Cancel</button>
		                              </form>
		                           </div>
		                        </div>
		                     </div>
		                     <div class="tab-pane fade" id="manage-contact" role="tabpanel">
		                        <div class="card">
		                           <div class="card-header d-flex justify-content-between">
		                              <div class="iq-header-title">
		                                 <h4 class="card-title">Manage Contact</h4>
		                              </div>
		                           </div>
		                           <div class="card-body">
		                              <form>
		                                 <div class="form-group">
		                                    <label for="cno">Contact Number:</label>
		                                    <input type="text" class="form-control" id="cno" value="<%=objUAd.getPhone()%>">
		                                 </div>
		                                 <div class="form-group">
		                                    <label for="email">Email:</label>
		                                    <input type="text" class="form-control" id="email-info" value="<%=objUAd.getEmail()%>">
		                                 </div>
		                                 <div class="form-group">
		                                    <label for="url">Url:</label>
		                                    <input type="text" class="form-control" id="url" value="https://getbootstrap.com">
		                                 </div>
		                                 <button type="submit" class="btn btn-primary mr-2">Submit</button>
		                                 <button type="reset" class="btn iq-bg-danger">Cancel</button>
		                              </form>
		                           </div>
		                        </div>
		                     </div>
		                  </div>
		               </div>
		            </div>
		         </div>
		      </div>
		      </div>
            </div>
         </div>
      </div>
      </div>
      	<script type="text/javascript">
      	$('#cpass').on('input', function(){
 	   		 var changedField = $(this);
 			     var cpass = $("#cpass").val(); 
 			     var id = <%=objUAd.getId()%>
 			        $.ajax({
 						url: '<%=request.getContextPath()%>/admin/handel-info',
 						type: 'POST',
 						cache: false,
 						data: {
 								aid: id,
 								acpass: cpass,
 							},
 						success: function(data){
 							$('.ajax-check-password').html(data);
 						},
 						error: function (){
 							alert('Có lỗi xảy ra');
 						},
 					});
 					return false;
 	   	});
      	$('#npass').on('input', function(){
	   		 var changedField = $(this);
			     var cpass = $("#cpass").val(); 
			     var npass = $("#npass").val();
			     var id = <%=objUAd.getId()%>
			        $.ajax({
						url: '<%=request.getContextPath()%>/admin/handel-info',
						type: 'POST',
						cache: false,
						data: {
								aid: id,
								ccpass: cpass,
								cnpass: npass,
							},
						success: function(data){
							$('.ajax-check-password-new').html(data);
						},
						error: function (){
							alert('Có lỗi xảy ra');
						},
					});
					return false;
	   	});
      	$('#vpass').on('input', function(){
	   		 var changedField = $(this);
	   		var npass = $("#npass").val(); 
			     var vpass = $("#vpass").val();
			     var id = <%=objUAd.getId()%>
			        $.ajax({
						url: '<%=request.getContextPath()%>/admin/handel-info',
						type: 'POST',
						cache: false,
						data: {
								aid:id,
								bnpass: npass,
								bvpass: vpass,
							},
						success: function(data){
							$('.ajax-check-password-vpass').html(data);
						},
						error: function (){
							alert('Có lỗi xảy ra');
						},
					});
					return false;
	   	});
 			
 			$('#email').on('blur', function(){
 		   		 var changedField = $(this);
 				     var email = $("#email").val(); 
 				     var id = <%=objUAd.getId()%>
 				        $.ajax({
 							url: '<%=request.getContextPath()%>/admin/handel-info',
 							type: 'POST',
 							cache: false,
 							data: {
 									aid: id,
 									'check-email': email,
 								},
 							success: function(data){
 								$('.ajax-check-email').html(data);
 							},
 							error: function (){
 								alert('Có lỗi xảy ra');
 							},
 						});
 						return false;
 		   	});
 			$('#form-change-info-admin').validate({
 	 			 rules:{
 	 				 fullname:{
 	 					 required: true,
 	 					 minlength: 6,
 	 				 },
 	 				 email:{ 
 	 					required: true,
 						email: true,
 	 				 },
 	 				 phone:{
 	 					 required: true,
 	 					 minlength: 10,
 	 				 },
 	 				 city:{
 	 					 required: true,
 	 				 },
 	 			 },
 	 			 messages:{
 	 				 fullname:{
 	 					required:'Nhập vào tên.',
 	 					minlength:'Nhập tối thiểu 6 ký tự.',
 	 				 },
 	 				email:{
 						required: 'Nhập vào email của bạn.',
 						email: 'Hãy nhập đúng định dạng.',
 					},
 					phone:{
 						required:'Nhập vào số điện thoại.',
 						minlength: 'Số điện thoại phải 10 ký tự.',
 					},
 					city:{
 						required:'Nhập vào thành phố.',
 					},
 	 			 },
 	 			 
 	 		 });
 			$('#form-change-password-admin').validate({
				 rules:{
					 cpass:{
						 required: true,
						 minlength: 6,
					 },
					npass:{
						 required: true,
						 minlength: 6,
						maxlength:13,
					 },
					vpass:{
						 required: true,
						 minlength: 6,
						maxlength:13,
						equalTo: "#npass"
					 },
				 },
			messages:{
					cpass:{
						required: 'Nhập vào mật khẩu cũ.',
						minlength: 'Nhập ít nhất 6 ký tự.',
						},
					npass:{
						required: 'Nhập vào mật khẩu mới.',
						minlength: 'Nhập ít nhất 6 ký tự.',
						maxlength: 'Nhập tối da 13 ký tự.',
					},
					vpass:{
						required: 'Nhập lại mật khẩu mới.',
						minlength: 'Nhập ít nhất 6 ký tự.',
						maxlength: 'Nhập tối da 13 ký tự.',
						equalTo: 'Mật khẩu chưa chính xác.'
					},
			}, 
 			});
 			$( "input[type=file]#avata" ).change(function(e) {
	    		var filename_input = e.target.files[0].name;
	    		var src = $("#display_avata").attr("src");
		    	var path = src.substring(0,src.lastIndexOf('/'));									    		
		    	var filename = src.split("/").pop();
		    	var newSrc = path+"/"+filename_input;
		    	$("#display_avata").attr("src",newSrc);
		    	});
 			$('#btn-change-profile-admin').on('click', function(){
 				//if($('#form-change-info-admin').valid()==true){
 					var formData = new FormData();
					formData.append('aid', <%=objUAd.getId()%>);
					formData.append('fullname', $("#fullname").val());
					formData.append('email', $('#email').val());
					formData.append('phone', $('#phone').val());
					formData.append('city', $('#city').val());
					formData.append("pic1", avata.files[0]);
 				        $.ajax({
 							url: '<%=request.getContextPath()%>/admin/profile',
 							type: 'POST',
 							cache: false,
 							contentType: false,
          					data: formData,
          					processData: false,
 							success: function(data){
 								$('.ajax-change-info').html(data);
 							},
 							error: function (){
 								alert('Có lỗi xảy ra');
 							},
 						});
 						return false;
 				//}else{
 					//$('#form-change-info-admin').valid();
 				//}
 			});
 			$('#btn-change-password-admin').on('click', function(){
 				//if($('#form-change-password-admin').valid()==true){
 					
 					var changedField = $(this);
 				     var cpass = $('#cpass').val(); 
 				     var npass = $('#npass').val();
 				     var id = <%=objUAd.getId()%>
 				        $.ajax({
 							url: '<%=request.getContextPath()%>/admin/handel-info',
 							type: 'POST',
 							cache: false,
 							data: {
 									aid: id,
 									cpass: cpass,
 									npass: npass,
 								},
 							success: function(data){
 								$('.ajax-change-password').html(data);
 								$('#form-change-password-admin').trigger("reset");
 							},
 							error: function (){
 								alert('Có lỗi xảy ra');
 							},
 						});
 						return false;
 				//}else{
 					//$('#form-change-password-admin').valid();
 				//}
 			});
      		function setInputFilter(textbox, inputFilter) {
      		  ["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(function(event) {
      		    textbox.addEventListener(event, function() {
      		      if (inputFilter(this.value)) {
      		        this.oldValue = this.value;
      		        this.oldSelectionStart = this.selectionStart;
      		        this.oldSelectionEnd = this.selectionEnd;
      		      } else if (this.hasOwnProperty("oldValue")) {
      		        this.value = this.oldValue;
      		        this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
      		      } else {
      		        this.value = "";
      		      }
      		    });
      		  });
      		};
      		setInputFilter(document.getElementById("phone"), function(value) {
      		  return /^\d*$/.test(value); });
		</script>
		</div>
    </div>
    <!-- Wrapper End-->
     <%@ include file="/templates/admin/inc/footer.jsp" %>