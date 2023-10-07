<%@page import="model.dao.OrdersDAO"%>
<%@page import="library.DefineLb2"%>
<%@page import="library.DefineLb"%>
<%@page import="model.bean.Status"%>
<%@page import="model.dao.StatusDAO"%>
<%@page import="model.bean.Orders"%>
<%@page import="java.io.File"%>
<%@page import="model.bean.ProductAdminDetail"%>
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
      </div>      <div class="content-page">
     <div class="container-fluid">
     <div class="row">
  <div>
  <%
  Admin admin = (Admin) session.getAttribute("objUAd");
  ArrayList<Orders> listOrder = new ArrayList<Orders>();
  if(request.getAttribute("listOrder")!=null){
  	listOrder = (ArrayList<Orders>)request.getAttribute("listOrder");
  	}
  int index=1;
  if(request.getAttribute("index")!=null){
	index =(Integer)request.getAttribute("index");
	}
  %>
  </div>
	</div>
        <div class="row">
            <div class="col-lg-12">
                <div class="d-flex flex-wrap flex-wrap align-items-center justify-content-between mb-4">
                    <div>
                        <h4 class="mb-3">Sale List</h4>
                    </div>
                    <div>
                    	<form action="<%=request.getContextPath()%>/admin/search" method="post">
                    		<div class="input-group">
                    			<input type="text" name="key-search" required="required"><input style="display: none;" name="key" value="2"><button><i class="ri-search-line"></i></button>
                    		</div>
                    	</form>
                    </div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive rounded mb-3">
                
                <table class="table mb-0 tbl-server-info">
                    <thead class="bg-white text-uppercase">
                        <tr class="ligth ligth-data">
                            <!-- <th>
                                <div class="checkbox d-inline-block">
                                    <input type="checkbox" class="checkbox-input" id="checkbox1">
                                    <label for="checkbox1" class="mb-0"></label>
                                </div>
                            </th> -->
                            <th>Code</th>
                            <th>Product ID</th>
                            <th>Product</th>
                            <th>Qty</th>
                            <th>Total</th>
                            <th>User ID</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody class="ligth-body">
                    <%
                    	if(listOrder.size()>0){
                  			for(Orders objOrder: listOrder){         				
                    %>
                        <tr>
                            <!-- <td>
                                <div class="checkbox d-inline-block">
                                    <input type="checkbox" class="checkbox-input" id="checkbox2">
                                    <label for="checkbox2" class="mb-0"></label>
                                </div>
                            </td> -->
                            <td><a href="<%=request.getContextPath()%>/admin/list-order-detail?index=<%=index %>&order_code=<%=objOrder.getOrder_code() %>"><%=objOrder.getOrder_code() %></a></td>
                            <td><%=objOrder.getProduct_id() %></td>
                            <td><%=objOrder.getProduct_name() %></td>
                            <td><%=objOrder.getQty() %></td>
                            <td><%=objOrder.getTotal() %></td>
                            <td><%=objOrder.getOrder_user() %></td>
                            <td>
                            	Đã Giao
                            </td>
                            <!-- <td>
                                <div class="d-flex align-items-center list-action">
                                <%
                                	if(admin.getPosition_id()==1){
                                %>
                                	<a class="badge badge-info mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="View"
                                        href="<%=request.getContextPath()%>/admin/list-order-detail?index=<%=index %>&order_code=<%=objOrder.getOrder_code() %>"><i class="ri-eye-line mr-0"></i></a>
                                    <a class="badge bg-success mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Confirm"
                                        href="<%=request.getContextPath()%>/admin/confirm-order?index=<%=index %>&id=<%=objOrder.getOrder_code()%>"><i class="ri-check-line mr-0"></i></a>
                                    
                                <%}else{%>
                                	<a class="badge badge-info mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="View"
                                        href="<%=request.getContextPath()%>/admin/list-order-detail?index=<%=index %>&order_code=<%=objOrder.getOrder_code() %>"><i class="ri-eye-line mr-0"></i></a>
                                <%} %>
                                </div>
                            </td> -->
                        </tr>
                        <%}}else{
                        	%>
                        	<tr><td colspan="14">Không có danh sách nào cả.</td></tr>
                        	<%
                        }%>
                    </tbody>
                </table>
                </div>
               <!-- <div class="row">
                            	<%
    								
    									int dfNum = DefineLb.NUMBER_PER_PAGE;
                            			OrdersDAO objOr = new OrdersDAO();
										int numPage = objOr.getNumberPage();
										int number_page = DefineLb2.NUMBER_PAGE;
										int numcur = number_page + index -1;
										int totalPro = objOr.getNumPro();
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
                                        	<li class="page-item previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-order?index=1">Về trang 1</a></li>
                                            <li class="page-item previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-order?index=<%=index-1%>">Trang trước</a></li>
                                            <%} %>
                                             
                                            <% 
                                            	if(number_page<numPage ){
                                            		if(numcur<=numPage){                                            			
                                            			for(int i=index; i<=numcur; i++){%>
                                                        <li class="page-item <%if(i==index){%><%=atc %><%} %> " aria-controls="dataTables-example" tabindex="0"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-order?index=<%=i%>"><%=i %></a></li>
                                                        <%}
                                            		}else{
                                            			
                                            			for(int i=index-2; i<=numPage; i++){
                                            			%>
                                            			
                                                        <li class="page-item <%if(i==index){%><%=atc %><%} %> " aria-controls="dataTables-example" tabindex="0"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-order?index=<%=i%>"><%=i %></a></li>
                                                        <%                                                       
                                            			}
                                            		}
                                            		}else{
                                            			if(number_page>=numPage){
                                            				for(int i=1; i<=numPage; i++){%>                                 	
                                                   				 <li class="page-item <%if(i==index){%><%=atc %><%} %>" aria-controls="dataTables-example" tabindex="0"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-order?index=<%=i%>"><%=i %></a></li>                                                    
                                                    <%}}}%>
                                            <%
                                            	if(index>=1&&index<numPage){
                                            %>
                                            <li class="page-item next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-order?index=<%=index+1%>">Trang tiếp</a></li>
                                            <li class="page-item next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a class="page-link" href="<%=request.getContextPath()%>/admin/list-order?index=<%=numPage%>">Đến trang cuối</a></li>
                                            <%} %>
                                        </ul>
                                    </div>
                                </div>
                            </div> --> 
            </div>
        </div>
        <!-- Page end  -->
    </div>
    <!-- Modal Edit -->
    <div class="modal fade" id="edit-note" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="popup text-left">
                        <div class="media align-items-top justify-content-between">                            
                            <h3 class="mb-3">Product</h3>
                            <div class="btn-cancel p-0" data-dismiss="modal"><i class="las la-times"></i></div>
                        </div>
                        <div class="content edit-notes">
                            <div class="card card-transparent card-block card-stretch event-note mb-0">
                                <div class="card-body px-0 bukmark">
                                    <div class="d-flex align-items-center justify-content-between pb-2 mb-3 border-bottom">                                                    
                                        <div class="quill-tool">
                                        </div>
                                    </div>
                                    <div id="quill-toolbar1">
                                        <p>Virtual Digital Marketing Course every week on Monday, Wednesday and Saturday.Virtual Digital Marketing Course every week on Monday</p>
                                    </div>
                                </div>
                                <div class="card-footer border-0">
                                    <div class="d-flex flex-wrap align-items-ceter justify-content-end">
                                        <div class="btn btn-primary mr-3" data-dismiss="modal">Cancel</div>
                                        <div class="btn btn-outline-primary" data-dismiss="modal">Save</div>
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
    <!-- Wrapper End-->
         <%@ include file="/templates/admin/inc/footer.jsp" %> 