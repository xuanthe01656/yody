<%@page import="model.bean.productDetail"%>
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
                            <h4 class="card-title">Edit Product</h4>
                        </div>
                    </div>
                    <div class="ajax-data2"></div>
                    <div class="card-body">
                    <%
                           if(request.getAttribute("objPro")!=null){
                            	productDetail objPro = (productDetail) request.getAttribute("objPro");
                            	int index =(Integer)request.getAttribute("index");
                            		
                            %>
                        <form id="form" action="<%=request.getContextPath() %>/admin/edit-product?id=<%=objPro.getId() %>&index=<%=index %>" method="post" enctype="multipart/form-data">
                            
                            	<div class="row">
                            		<div class="col-md-6">
	                                    <div class="form-group">
	                                        <label>Product SKU *</label>
	                                        <input id="suk" name="suk" class="selectpicker form-control" value="<%=objPro.getProductSKU() %>" data-style="py-0" data-errors="Please Enter Name." required>
	                                        <div class="help-block with-errors"></div>
	                                    </div> 
                                	</div>  
	                                <div class="col-md-6">                      
	                                    <div class="form-group">
	                                        <label>Name *</label>
	                                        <input id="name" type="text" class="form-control" placeholder="Enter Name" value="<%=objPro.getName() %>" name="name" data-errors="Please Enter Name." required>
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
                                
                                 <div class="row">
                                 	<div class="col-md-6">
                                 		<div class="form-group">
	                                        <label>Menu *</label>
	                                        <select id="menu" name="menu" class="form-select" data-style="py-0">
	                                            <option value="">--Chọn Menu--</option>
	                                            <%
													MenuDAO objM = new MenuDAO();
													ArrayList<Menu> listMenu = objM.getItemMenu();
													if(listMenu.size()>0){
														for(Menu menu: listMenu){
												%>
	                                            <option <%if(objPro.getMenu_id()==menu.getId()){out.print("selected");} %> value="<%=menu.getId()%>"><%=menu.getName() %></option>
	                                            <%}} %>
	                                        </select>
	                                    </div>
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
	                                
	                                <div class="col-md-6 ">
	                                    <div class="form-group">
	                                        <label>Category Menu*</label>
	                                        <select name="categorymenu" id="menu1" class="form-select ajax-data" data-style="py-0">
	                                        	<%
													ArrayList<Menu> listChil = objM.getItemChilMenu(objPro.getMenu_id());
													if(listMenu.size()>0){
														for(Menu menuChil: listChil){%>
															<option <%if(objPro.getMenu_c1_id()==menuChil.getId()){out.print("selected");} %> value="<%=menuChil.getId()%>"><%=menuChil.getName() %></option>
				                               <%}}%>	
														
	                                        </select>
	                                    </div>
	                                    <script type="text/javascript">
	                                   		 $(function() {
		                                		    $('#menu1').on('change', function() {
		                                		        var changedField = $(this);
		                                		        var menu1 = $("#menu1").val();       
		                                		        $.ajax({
		                                					url: '<%=request.getContextPath()%>/handelMenu1',
		                                					type: 'POST',
		                                					cache: false,
		                                					data: {amenu1: menu1},
		                                					success: function(data){
		                                						$('.ajax-data1').html(data);
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
	                                <div class="col-md-6">
	                                    <div class="form-group">
	                                        <label>Category*</label>
	                                        <select name="category" class="form-select ajax-data1" data-style="py-0">
	                                            <%
													ArrayList<Menu> listChil1 = objM.getItemChilMenu(objPro.getMenu_c1_id());
													if(listMenu.size()>0){
														for(Menu menuChil: listChil1){%>
															<option <%if(objPro.getMenu_c2_id()==menuChil.getId()){out.print("selected");} %> value="<%=menuChil.getId()%>"><%=menuChil.getName() %></option>
				                               <%}}%>	
	                                        </select>
	                                    </div>
	                                </div>
	                                
	                                <div class="col-md-6">
                                		<div class="form-group">
	                                        <label>Colour *</label>
	                                        <select name="color" class="form-select" data-style="py-0">
	                                        	<option>--Chọn Màu--</option>
	                                        	<%
	                                        		ColourDAO objClDAO = new ColourDAO();
	                                        		ArrayList<colur> listCl = objClDAO.getColour();
	                                        		if(listCl.size()>0){
	                                        			for(colur color: listCl){
	                                        	%>
	                                            <option <%if(objPro.getColor_id()==color.getId()){out.print("selected");} %> value="<%=color.getId()%>"><%=color.getName() %></option>
	                                            <%}} %>
	                                        </select>
	                                        <div class="help-block with-errors"></div>
                                    	</div>
                                	</div> 
	                                <div class="col-md-6"> 
	                                    <div class="form-group">
	                                        <label>Size *</label>
	                                        <select name="size" class="form-select" data-style="py-0">
	                                        <option value="">--Chọn Size--</option>
	                                        <%
	                                        	SizeDAO objSDAO = new SizeDAO();
	                                        	ArrayList<size> listSize = objSDAO.getSize();
	                                        	if(listSize.size()>0){
	                                        		for(size size: listSize){
	                                        %>                                            
	                                            <option <%if(objPro.getSize_id()==size.getId()){out.print("selected");} %> value="<%=size.getId()%>"><%=size.getName() %></option>
	                                            <%}} %>
	                                        </select>
	                                    </div>
	                                </div> 
	                                <div class="col-md-6">
	                                    <div class="form-group">
	                                        <label>Cost *</label>
	                                        <input type="text" class="form-control" name="cost" value="<%=objPro.getCost() %>" placeholder="Enter Cost" data-errors="Please Enter Cost." required>
	                                        <div class="help-block with-errors"></div>
	                                    </div>
	                                </div>
	                                <div class="col-md-6">
	                                    <div class="form-group">
	                                        <label>Price *</label>
	                                        <input type="text" class="form-control" name="price" value="<%=objPro.getPrice() %>" placeholder="Enter Price" data-errors="Please Enter Price." required>
	                                        <div class="help-block with-errors"></div>
	                                    </div>
	                                </div>
	                                <div class="col-md-6">
	                                    <div class="form-group">
	                                        <label>Suppliers *</label>
	                                        <select id="name" name="suppliers" class="form-select" data-style="py-0">
	                                            <option value="">--Chọn Suppliers--</option>
	                                            <%
	                                            	SuppliersDAO objSupDAO=new SuppliersDAO();
	                                            	ArrayList<Suppliers> listSup = objSupDAO.getSup();
	                                            	if(listSup.size()>0){
	                                            		for(Suppliers objSup: listSup){
	                                            %>
	                                            <option <%if(objPro.getSuppliers_id()==objSup.getId()){out.print("selected");} %> value="<%=objSup.getId()%>"><%=objSup.getName() %></option>
	                                            <%}} %>
	                                        </select>
	                                    </div>
	                                </div>
                                	<div class="col-md-6">
	                                    <div class="form-group">
	                                        <label>Made In *</label>
	                                        <select id="country" name="country" class="form-select" data-style="py-0">
	                                        <option value="">--Chọn Country--</option>
	                                        <%
	                                        	CountryDAO objCtrDAO = new CountryDAO();
	                                        	ArrayList<Country> listCtry = objCtrDAO.getCountry();
	                                        	if(listCtry.size()>0){
	                                        		for(Country country: listCtry){
	                                        %>
	                                            <option <%if(objPro.getCountry_id()==country.getId()){out.print("selected");} %> value="<%=country.getId()%>"><%=country.getName() %></option>
	                                         <%}} %>
	                                        </select>
	                                    </div>
                                    </div>
	                                <div class="col-md-6">                                    
	                                    <div class="form-group">
	                                        <label>Quantity *</label>
	                                        <input type="text" class="form-control" name ="amount" value="<%=objPro.getAmount() %>" placeholder="Enter Quantity" required>
	                                        <div class="help-block with-errors"></div>
	                                    </div>
	                                </div>
	                                <div class="col-md-12">
	                                    <div class="form-group">
	                                        <label>Outstanding Feature</label>
	                                        <textarea class="form-control ckeditor" name="outstandingfeature" rows="4"><%=objPro.getOutstanding_feature() %></textarea>
	                                    </div>
	                                </div>
	                                 <div class="col-md-12">
	                                    <div class="form-group">
	                                        <label>Product Details</label>
	                                        <textarea class="form-control ckeditor" name="detail" rows="4"><%=objPro.getDetail() %></textarea>
	                                    </div>
	                                </div> 
                                 </div>
                                 <%} %>                      
                            <button type="submit" class="btn btn-primary mr-2">Edit Product</button>
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
   					url: '<%=request.getContextPath() %>/handel_product_ajax',
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
         </script>
        <!-- Page end  -->
    </div>
      </div>
    </div>
    <!-- Wrapper End-->
     <%@ include file="/templates/admin/inc/footer.jsp" %> 