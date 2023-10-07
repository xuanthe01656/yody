<%@page import="model.bean.Country"%>
<%@page import="model.dao.CountryDAO"%>
<%@page import="model.dao.SuppliersDAO"%>
<%@page import="model.bean.Suppliers"%>
<%@page import="model.bean.Menu"%>
<%@page import="model.dao.MenuDAO"%>
<%@page import="model.bean.colur"%>
<%@page import="model.dao.ColourDAO"%>
<%@page import="model.bean.size"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.SizeDAO"%>
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
                            <h4 class="card-title">Add Product</h4>
                        </div>
                    </div>
                    <%
                    if(request.getParameter("msg")!=null){
                		int msg = Integer.parseInt(request.getParameter("msg"));
                		if(msg==1){
                			%>
                			<div><span style="background-color: yellow; color: red;">Thêm sản phẩm thành công!</span></div>
                			<%
                		}else{
                			if(msg==2){
                			%>
                			<div><span style="background-color: yellow; color: red;">Thêm sản phẩm thất bại!</span></div>
                			<%
                			}else{
                				if(msg==3){
                					%>
                        			<div><span style="background-color: yellow; color: red;">Cập nhật số lượng sản phẩm thành công!</span></div>
                        			<%
                				}else{
                    				if(msg==4){
                    					%>
                            			<div><span style="background-color: yellow; color: red;">Cập nhật số lượng sản phẩm thất bại!</span></div>
                            			<%
                    				}else{
                    					if(msg==5){
                    						%>
                                			<div><span style="background-color: yellow; color: red;">Vui lòng xác nhận lại mục color hoặc size. Chỉ được chọn 1 trong 2 trường liên quan đến color và size!</span></div>
                                			<%
                    					}
                    				}
                    			}
                			}
                		}
                	}
                    %>
                    <div class="ajax-data2"></div>
                    <div class="card-body">
                        <form id="form" action="<%=request.getContextPath() %>/admin/add-product" method="post" enctype="multipart/form-data">
                            
                            	<div class="row">
                            		<div class="col-md-6">
	                                    <div class="form-group">
	                                        <label>Product SKU *</label>
	                                        <input id="suk" name="suk" class="selectpicker form-control" placeholder="Enter SKU" value="" data-style="py-0" data-errors="Please Enter Name.">
	                                        <label for="suk" class="error"></label>
	                                        <div class="help-block with-errors"></div>
	                                    </div> 
                                	</div>  
	                                <div class="col-md-6">                      
	                                    <div class="form-group">
	                                        <label>Name *</label>
	                                        <input id="name" type="text" class="form-control" placeholder="Enter Name" value="" name="name" data-errors="Please Enter Name.">
	                                        <label for="name" class="error"></label>
	                                        <div class="help-block with-errors"></div>
	                                    </div>
	                                </div> 
                            	</div>
                                   <script type="text/javascript">
	                                        $(function() {
	                                        	
	                                		    $('#suk ,#name').on('blur', function() {
	                                		        var changedField = $(this);
	                                		        var suk = $("#suk").val(); 
	                                		        var name = $("#name").val(); 
	                                		        
	                                		        $.ajax({
	                                					url: '<%=request.getContextPath()%>/handel_product',
	                                					type: 'POST',
	                                					cache: false,
	                                					data: {
	                                						asuk: suk,
	                                						aname: name
	                                					},
	                                					
	                                					success: function(data){
	                                						$('.ajax-data2').html(data);
	                                					},
	                                					error: function (){
	                                						alert('Có lỗi xảy ra');
	                                					},
	                                				});
	                                				return false;
	                                		    });
	                                		});
                                        </script>
                                <div class="row ajax-handel">

                                </div>
                                <div class="row">                            
                                </div>
                                 <div class="row">
                                 	<div class="col-md-6">
                                		<div class="form-group">
	                                        <label>Colour *</label>
	                                        <select id="color" name="color" class="form-select form-control color-group" data-style="py-0">
	                                        	<option value="">--Chọn Màu--</option>
	                                        	<%
	                                        		ColourDAO objClDAO = new ColourDAO();
	                                        		ArrayList<colur> listCl = objClDAO.getColour();
	                                        		if(listCl.size()>0){
	                                        			for(colur color: listCl){
	                                        	%>
	                                            <option value="<%=color.getId()%>"><%=color.getName() %></option>
	                                            <%}} %>
	                                        </select>
	                                        <label for="color" class="error"></label><br>
	                                        <label>Hoặc<small> (Nếu chưa có ở list trên)</small></label>
	                                        <input type="text" id="color-text" name="color-text" class="form-control color-group" value="" placeholder="Nhập vào màu.">
	                                        <label for="color-text" class="error"></label>
	                                        <div class="help-block with-errors"></div>
                                    	</div>
                                	</div> 
	                                <div class="col-md-6"> 
	                                    <div class="form-group">
	                                        <label>Size *</label>
	                                        <select id="size" name="size" class="form-select form-control size-group" data-style="py-0">
	                                        <option value="">--Chọn Size--</option>
	                                        <%
	                                        	SizeDAO objSDAO = new SizeDAO();
	                                        	ArrayList<size> listSize = objSDAO.getSize();
	                                        	if(listSize.size()>0){
	                                        		for(size size: listSize){
	                                        %>                                            
	                                            <option value="<%=size.getId()%>"><%=size.getName() %></option>
	                                            <%}} %>
	                                        </select>
	                                        <label for="size" class="error"></label><br>
	                                        <label>Hoặc <small>(Nếu chưa có ở list trên)</small></label>
	                                        <input type="text" id="size-text" name="size-text" class="form-control size-group" value="" placeholder="Nhập vào size.">
	                                    	<label for="size-text" class="error"></label>
	                                    </div>
	                                </div> 
	                                <div class="col-md-12">                                    
	                                    <div class="form-group">
	                                        <label>Quantity *</label>
	                                        <input type="text" class="form-control" name ="amount" value="" placeholder="Enter Quantity" required>
	                                        <label for="amount" class="error"></label>
	                                        <div class="help-block with-errors"></div>
	                                    </div>
	                                </div>
	                                <div class="ajax-display-img-input row col-sm-12">
	                                	<div class="col-md-6">
		                                    <div class="form-group">
		                                        <label>Cost *</label>
		                                        <input type="text" class="form-control" name="cost" value="" placeholder="Enter Cost" data-errors="Please Enter Cost.">
		                                        <label for="cost" class="error"></label>
		                                        <div class="help-block with-errors"></div>
		                                    </div>
		                                </div>
		                                <div class="col-md-6">
		                                    <div class="form-group">
		                                        <label>Price *</label>
		                                        <input type="text" class="form-control" name="price" value="" placeholder="Enter Price" data-errors="Please Enter Price.">
		                                        <label for="price" class="error"></label>
		                                        <div class="help-block with-errors"></div>
		                                    </div>
		                                </div>
		                                <div class="col-md-12">
		                                    <div class="form-group">
		                                        <label>Tax Method *</label>
		                                        <select name="type" class="form-select form-control" data-style="py-0">
		                                            <option>Exclusive</option>
		                                            <option>Inclusive</option>
		                                        </select>
		                                    </div>
		                                </div>
	                                	<div class="col-md-12">
		                                    <div class="form-group">
		                                        <label>Display Image </label>
		                                        <input type="file" class="form-control image-file" name="pic1" accept="image/*">
		                                    	<label for="pic1" class="error"></label>
		                                    </div>
	                                	</div>
		                             	 <div class="col-md-12">
		                                    <div class="form-group">
		                                        <label>Image</label>
		                                        <input type="file" class="form-control image-file" name="pic" multiple="multiple" accept="image/*">
		                                    	<label for="pic" class="error"></label>
		                                    </div>
		                                </div>
		                                <div class="col-md-12">
		                                    <div class="form-group">
		                                        <label>Outstanding Feature</label>
		                                        <textarea class="form-control ckeditor" name="outstandingfeature" rows="4"></textarea>
		                                    	<label for="outstandingfeature" class="error"></label>
		                                    </div>
		                                </div>
		                                 <div class="col-md-12">
		                                    <div class="form-group">
		                                        <label>Product Details</label>
		                                        <textarea class="form-control ckeditor" name="detail" rows="4"></textarea>
		                                   		<label for="detail" class="error"></label>
		                                    </div>
		                                </div> 
	                                </div>
                                 </div>
                                                       
                            <button type="submit" class="btn btn-primary mr-2">Add Product</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
		
  		 $(function() {
   		    $('#suk ,#name').on('blur', function() {
   		        var changedField = $(this);
   		        var suk = $("#suk").val();
   		     	var name = $("#name").val(); 
   		        $.ajax({
   					url: '<%=request.getContextPath()%>/handel_product_ajax',
   					type: 'POST',
   					cache: false,
   					data: {
   						asuk: suk,
   						aname: name,
   						
   					},
   					success: function(data){
   						$('.ajax-handel').html(data);
   					},
   					error: function (){
   						alert('Có lỗi xảy ra');
   					},
   				});
   				return false;
   		    });
   		   
   		});
  		
			 $('#color ,#color-text').on('blur', function(){
				var color_text = $('#color-text').val();
				var color = $('#color').val();
		    	var sku = $('#suk').val();
		    	$.ajax({
					url: '<%=request.getContextPath()%>/admin/handel-color',
					type: 'POST',
					cache: false,
					data: {
						asku: sku,
						acolor: color,
						bcolor: color_text,
						
					},
					success: function(data){
						$('.ajax-display-img-input').html(data);
					},
					error: function (){
						alert('Có lỗi xảy ra');
					},
				});
		    	return false;
		    });
         </script>
        <!-- Page end  -->
    </div>
      </div>
    </div>
    <script>
    	jQuery.validator.setDefaults({
    	  success: "valid"
    	});
    	$('#form').validate({
    		ignore:[],
    		rules:{
    			suk:{
    				required: true,
    			},
    			name:{
    				required: true,
    			},
    			color:{
    				require_from_group: [1,".color-group"],
    			},
    			'color-text':{
    				require_from_group: [1,".color-group"],
    			},
    			size:{
    				require_from_group: [1,".size-group"],
    			},
    			'size-text':{
    				require_from_group: [1,".size-group"],
    			},
    			cost:{
    				required: true,
    			},
    			price:{
    				required: true,
    			},
    			amount:{
    				required: true,
    			},
    			pic:{
    				required: true,
    			},
    			pic1:{
    				required: true,
    			},
    			outstandingfeature:{
    				required: true,
    			},
    			detail:{
    				required: true,
    			},
    		},
    		messages:{
    			suk:{
    				required: 'Nhập vào mã sản phẩm.',
    			},
    			name:{
    				required: 'Nhập vào tên sản phẩm.',
    			},
    			color:{
    				require_from_group: "Chọn màu.",
    			},
    			'color-text':{
    				require_from_group: "Chọn màu.",
    			},
    			size:{
    				require_from_group: "Chọn size.",
    			},
    			'size-text':{
    				require_from_group: "Chọn size.",
    			},
    			cost:{
    				required: "Nhập vào chi phí.",
    			},
    			price:{
    				required: "Nhập giá bán.",
    			},
    			amount:{
    				required: "Nhập số lượng.",
    			},
    			pic:{
    				required: "Chọn file.",
    			},
    			pic1:{
    				required: "Chọn file.",
    			},
    			outstandingfeature:{
    				required: 'Nhập vào mô tả.',
    			},
    			detail:{
    				required: 'Nhập vào chi tiết.',
    			},
    			
    		},
    	});
    	CKEDITOR.on('instanceReady', function () {
    	    $.each(CKEDITOR.instances, function (instance) {
    	        CKEDITOR.instances[instance].document.on("keyup", CK_jQ);
    	        CKEDITOR.instances[instance].document.on("paste", CK_jQ);
    	        CKEDITOR.instances[instance].document.on("keypress", CK_jQ);
    	        CKEDITOR.instances[instance].document.on("blur", CK_jQ);
    	        CKEDITOR.instances[instance].document.on("change", CK_jQ);
    	    });
    	});

    	function CK_jQ() {
    	    for (instance in CKEDITOR.instances) {
    	        CKEDITOR.instances[instance].updateElement();
    	    }
    	}
    	CKEDITOR_CONFIGS = {
    		    'default': {
    		        'skin': 'moono',
    		        'toolbar': 'basic',
    		        'height': 'full',
    		        'width': 'full',
    		        'removePlugins': 'exportpdf',
    		        'toolbarCanCollapse':'true'
    		    },
    		}
    </script>
    <!-- Wrapper End-->
     <%@ include file="/templates/admin/inc/footer.jsp" %> 