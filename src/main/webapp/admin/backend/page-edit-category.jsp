<%@page import="java.io.File"%>
<%@page import="model.dao.MenuDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Menu"%>
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
                            <h4 class="card-title">Edit category</h4>
                        </div>
                    </div>
                    <div class="card-body">
                    <%
                            	MenuDAO objMDAO = new MenuDAO();                          	
                            	Menu objM = (Menu) request.getAttribute("objMByID");%>
                        <form id="form" class="form" action="<%=request.getContextPath() %>/admin/edit-categories?id=<%=objM.getId() %>" method="post" enctype="multipart/form-data">
                            <div class="row"> 
                            	
                            
                            	<div class="col-md-12">
                                    <div class="form-group">
                                        <label>Old Category</label>
                                        <p><%=objM.getName() %></p>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                     <div class="form-group">
                                     <div class="ajax-dataname"></div>
                                       	<label for="newcat">New Category *</label>
                                        <input type="text" id="newcat" class="form-control"  name="newcat"> 
                                        <label for="newcat" class="error" style="color: red;"></label>
                                	</div>
                                	<script type="text/javascript">
                                	$(function() {
                            		    $('#newcat').on('input', function() {
                            		        var changedField = $(this);
                            		        var name = $("#newcat").val();
                            		        var parent_id = <%=objM.getParentID()%> 
                            		                    
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
                             	 
                             	<script type="text/javascript">
                             	
                             	jQuery(document).ready(function() {
                             		var id = <%=objM.getParentID()%>;
                             		if(id==0){
                             			 // Capitalize string - convert textbox user entered text to uppercase
                                    	jQuery('#newcat').keyup(function() {
                                    		$(this).val($(this).val().toUpperCase());
                                    	});
                             		}else{
                             		// Capitalize string every 1st chacter of word to uppercase
                             			jQuery('#newcat').keyup(function() 
                                            	{
                                            		var str = jQuery('#newcat').val();
                                                   
                                            		
                                            		var spart = str.split(" ");
                                            		for ( var i = 0; i < spart.length; i++ )
                                            		{
                                            			var j = spart[i].charAt(0).toUpperCase();
                                            			spart[i] = j + spart[i].substr(1);
                                            		}
                                                  jQuery('#newcat').val(spart.join(" "));
                                            	
                                           	});
                                       
                             		}
                            
                            	
                            	// Capitalize string first character to uppercase
                            	//jQuery('#txtcapital').keyup(function() {
                            		//var caps = jQuery('#txtcapital').val(); 
                            		//caps = caps.charAt(0).toUpperCase() + caps.slice(1);
                                    //jQuery('#txtcapital').val(caps);
                            	//});
                             		
                            	
                             	});
                             
                             	
                            	
                             	</script>                           
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Image</label>
                                        <%
                                        String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/category/");
						 				
					 					File image = new File(path+objM.getImgaes());
					 					
              							if(image.exists()&&objM.getImgaes()!=null){
                                        %>
                                        <img id="image<%=objM.getId() %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/category/<%=objM.getImgaes() %>" class="img-fluid rounded-circle avatar-50 mr-3" style="max-width: 30px; max-height: 45px;" alt="image">
                                        <%} %>
                                        <input type="file" class="form-control image-file" name="pic" accept="image/*">
                                    </div>
                                </div>                                                
                            </div>                            
                            <button type="submit" class="btn btn-primary mr-2">Edit category</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Page end  -->
    </div>
     </div>
    <script>
    <!--  $(".form").validate({
			
			rules:{
				newcat:{
					required: true,
					minlength: 2,
					maxlength: 50,
				}	
			},
  		messages:{
				newcat: {
					required: " Hãy nhập vào category.",
					minlength: " Hãy nhập vào nhiều hơn 2 ký tự và ít hơn 50 ký tự.",
					maxlength: " Hãy nhập vào ít hơn 50 ký tự.",
				}
  		},
  	}); -->
  	
     </script>
    </div>
    <!-- Wrapper End-->
         <%@ include file="/templates/admin/inc/footer.jsp" %> 