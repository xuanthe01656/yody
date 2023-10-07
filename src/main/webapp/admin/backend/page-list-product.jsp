<%@page import="org.checkerframework.framework.qual.SubtypeOf"%>
<%@page import="java.io.File"%>
<%@page import="java.awt.Image"%>
<%@page import="model.bean.ProductAdmin"%>
<%@page import="library.DefineLb2"%>
<%@page import="library.DefineLb"%>
<%@page import="model.dao.ProductDao"%>
<%@page import="model.bean.Product"%>
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
        	<% 
            	ArrayList<Product> listPro = new ArrayList<>();
            	if(request.getAttribute("listPro")!=null){
            	listPro =(ArrayList<Product>)request.getAttribute("listPro");}
            	int index =(Integer)request.getAttribute("index");
                %>
            <div class="col-lg-12">
                <div class="d-flex flex-wrap flex-wrap align-items-center justify-content-between mb-4">
                    <div>
                        <h4 class="mb-3">Product List</h4>
                        <p class="mb-0">The product list effectively dictates product presentation and provides space<br> to list your products and offering in the most appealing way.</p>
                    </div>
                    <a href="<%=request.getContextPath() %>/admin/add-product" class="btn btn-primary add-list"><i class="las la-plus mr-3"></i>Add Product</a>
                    <div>
                    	<form id="form-search" action="<%=request.getContextPath()%>/admin/search" method="post">
                    	<p id="error-input"></p>
                    		<div class="input-group">
                    			<input type="text" id="key_search" name="key-search" required="required"><input style="display: none;" id="key" name="key" value="1"><button type="button" id="button_search"><i class="ri-search-line"></i></button>
                    		</div>
                    	</form>
                    </div>
                </div>
            </div>
            <div class="col-lg-12 ajax-display-search">
                <div class="table-responsive rounded mb-3">
                <table class="table mb-0">
                    <thead class="bg-white text-uppercase">
                        <tr class="ligth ligth-data">
                            <th>Product</th>
                            <th>Code</th>
                            <th>Category</th>
                            <th>Cost</th>
                            <th>Price</th>                         
                            <th>Suppliers</th>
                            <th>Made In</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody class="ligth-body">
                    <%
                    	String images = "";
                    	if(listPro.size()>0){
                  			for(Product objPro: listPro){
                  				images = objPro.getImages();            				
                    %>
                        <tr>
                            <!-- <td>
                                <div class="checkbox d-inline-block">
                                    <input type="checkbox" class="checkbox-input" id="checkbox2">
                                    <label for="checkbox2" class="mb-0"></label>
                                </div>
                            </td> -->
                            <td>
                                <div class="d-flex align-items-center">
                                <%
                                if(images!=null){
                  					String[] listImages = images.split(",");
                  					String applicationPath = request.getServletContext().getRealPath("");
                  					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                  					File image = new File(path+listImages[0]);
                  					if(image.exists()){
                                %>
                                   <img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=listImages[0] %>" class="img-fluid rounded avatar-50 mr-3" alt="image">
                                    <%}else{%>
                                    	<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" class="img-fluid rounded avatar-50 mr-3" alt="image">
                                   <% }}else{%>
                               	<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" class="img-fluid rounded avatar-50 mr-3" alt="image">
                               <% } %>
                                    		
                                    <div>
                                        <a href="<%=request.getContextPath() %>/admin/product-detail?product_code=<%=objPro.getProductSKU()%>&index=<%=index%>"><%=objPro.getName() %></a>
                                        <!-- <p class="mb-0"><small>This is test Product</small></p> -->
                                    </div>
                                </div>
                            </td>
                            <td><%=objPro.getProductSKU() %></td>
                            <td><%=objPro.getMenu() %></td>
                            <td><%=objPro.getCost() %></td>
                            <td><%=objPro.getPrice() %></td>
                            <td><%=objPro.getSuppliers() %></td>
                            <td><%=objPro.getMade_in() %></td>
                            <td><%=objPro.getAmount() %></td>
                            <td>
                                <div class="d-flex align-items-center list-action">
                                    <a href="<%=request.getContextPath() %>/admin/product-detail?product_code=<%=objPro.getProductSKU()%>&index=<%=index%>" class="badge badge-info mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="View"> 
                                    	<i class="ri-eye-line mr-0"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                        <%}}else{
                        	%>
                        	<tr><td colspan="9">Không có danh sách nào cả.</td></tr>
                        	<%
                        } %>
                    </tbody>
                </table>
                </div>
                <div class="row">
                            	<%
    								
    									int dfNum = DefineLb.NUMBER_PER_PAGE;//3
                            			ProductDao objPro = new ProductDao();
										int numPage = objPro.getNumberPage();//12
										int number_page = DefineLb2.NUMBER_PAGE;//3
										int numcur = number_page + index -1;//3,4
										int totalPro = objPro.getNumPro();//33
										String atc = "active";
										int numPro = 0;
										 	
										if(index<numPage){
											numPro = (index-1)*dfNum+dfNum;
										}else{
											numPro = totalPro;
										}
									
								%>
                                <div class="col-sm-4">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin:27px 0px;">Hiển thị từ <%=(index-1)*dfNum+1 %> đến <%=numPro %> của <%=totalPro %> sản phẩm</div>
                                </div>
                                <div class="col-sm-8" style="text-align: right; margin:27px 0px">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                    
                                        <ul class="pagination justify-content-end">
                                        	<%
                                        	if(index>1){
                                        	%>
                                        	<li class="page-item previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-product?index=1">Về trang 1</a></li>
                                            <li class="page-item previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-product?index=<%=index-1%>">Trang trước</a></li>
                                            <%} %>
                                             
                                            <% 
                                            	if(number_page<numPage ){
                                            		if(numcur<=numPage){                                            			
                                            			for(int i=index; i<=numcur; i++){%>
                                                        <li class="page-item <%if(i==index){%><%=atc %><%} %> " aria-controls="dataTables-example" tabindex="0"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-product?index=<%=i%>"><%=i %></a></li>
                                                        <%}
                                            		}else{
                                            			
                                            			for(int i=index-2; i<=numPage; i++){
                                            			%>
                                            			
                                                        <li class="page-item <%if(i==index){%><%=atc %><%} %> " aria-controls="dataTables-example" tabindex="0"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-product?index=<%=i%>"><%=i %></a></li>
                                                        <%                                                       
                                            			}
                                            		}
                                            		}else{
                                            			if(number_page>=numPage){
                                            				for(int i=1; i<=numPage; i++){%>                                 	
                                                   				 <li class="page-item <%if(i==index){%><%=atc %><%} %>" aria-controls="dataTables-example" tabindex="0"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-product?index=<%=i%>"><%=i %></a></li>                                                    
                                                    <%}}}%>
                                            <%
                                            	if(index>=1&&index<numPage){
                                            %>
                                            <li class="page-item next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-product?index=<%=index+1%>">Trang tiếp</a></li>
                                            <li class="page-item next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-product?index=<%=numPage%>">Đến trang cuối</a></li>
                                            <%} %>
                                        </ul>
                                    </div>
                                </div>
                            </div>
            </div>
            			<script type="text/javascript">
                            
                    		$('#button_search').on('click', function(){
                    			if($('#key_search').val()!=""){
                    				var key = $('#key').val();
                        			var key_search = $('#key_search').val();
                        			var index = <%=index%>;
                        			 $.ajax({
    										url: '<%=request.getContextPath()%>/admin/search',
    										type: 'POST',
    										cache: false,
    										data: {
    												key: key,
    												'key-search': key_search,
    												index: index,
    											},
    										success: function(data){
    											$('.ajax-display-search').html(data);
    											$('#indexs<%=index%>').addClass('active');
    										},
    										error: function (){
    											alert('Có lỗi xảy ra');
    										}
    									});
                    			}else{
                    				$( "#error-input" ).text( "Nhập vào từ khóa!" ).show().fadeOut( 1000 );
                    			}
                    		});
                    	</script>
        </div>
        <!-- Page end  -->
    </div>
      </div>
    </div>
    <!-- Wrapper End-->
         <%@ include file="/templates/admin/inc/footer.jsp" %> 