<%@page import="model.bean.RevenueExpense"%>
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
            <div class="col-lg-4">
                <div class="card card-transparent card-block card-stretch card-height border-none">
                    <div class="card-body p-0 mt-lg-2 mt-0">
                    	<%
                    		Admin objUAd1 = (Admin) session.getAttribute("objUAd");
                    	%>
                        <h3 class="mb-3">Chào! <%=objUAd1.getFullname() %></h3>
                        <p class="mb-0 mr-4">Trang tổng quan của bạn cung cấp cho bạn các chế độ xem về hiệu suất chính hoặc quy trình kinh doanh.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="row">
                    <div class="col-lg-4 col-md-4">
                        <div class="card card-block card-stretch card-height">
                            <div class="card-body">
                                <div class="d-flex align-items-center mb-4 card-total-sale">
                                    <div class="icon iq-icon-box-2 bg-info-light">
                                        <img src="<%=request.getContextPath() %>/templates/admin/assets/images/product/1.png" class="img-fluid" alt="image">
                                    </div>
                                    <div>
                                        <p class="mb-2">Tổng Doanh Thu</p>
                                        <%
                                    		if(request.getAttribute("sumRevenue")!=null){
                                    			String sum_revenue = (String) request.getAttribute("sumRevenue");
                                    	%>
                                        <h4><%=sum_revenue %> VNĐ</h4>
                                        <%}else{
                                        	%>
                                            <h4>0 VNĐ</h4>
                                            <%
                                        } %>
                                    </div>
                                </div>                                
                                <div class="iq-progress-bar mt-2">
                                    <span class="bg-info iq-progress progress-1" data-percent="85">
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4">
                        <div class="card card-block card-stretch card-height">
                            <div class="card-body">
                                <div class="d-flex align-items-center mb-4 card-total-sale">
                                    <div class="icon iq-icon-box-2 bg-danger-light">
                                        <img src="<%=request.getContextPath() %>/templates/admin/assets/images/product/2.png" class="img-fluid" alt="image">
                                    </div>
                                    <div>
                                        <p class="mb-2">Tổng Chi Phí</p>
                                        <%
                                        	if(request.getAttribute("sumExpense")!=null){
                                        		String sum_expense = (String)request.getAttribute("sumExpense");
                                        %>
                                        <h4><%=sum_expense %> VNĐ</h4>
                                        <%}else{
                                        	 %>
                                             <h4>0 VNĐ</h4>
                                             <%
                                        } %>
                                    </div>
                                </div>
                                <div class="iq-progress-bar mt-2">
                                    <span class="bg-danger iq-progress progress-1" data-percent="70">
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4">
                        <div class="card card-block card-stretch card-height">
                            <div class="card-body">
                                <div class="d-flex align-items-center mb-4 card-total-sale">
                                    <div class="icon iq-icon-box-2 bg-success-light">
                                        <img src="<%=request.getContextPath() %>/templates/admin/assets/images/product/3.png" class="img-fluid" alt="image">
                                    </div>
                                    <div>
                                        <p class="mb-2">Sản Phẩm Đã Bán: </p>
                                       <%
                                       	if(request.getAttribute("sumQuantitySold")!=null){
                                       		String sum_quantity_sold = (String) request.getAttribute("sumQuantitySold");
                                       %>
                                        <h4><%=sum_quantity_sold %></h4>
                                        <%}else{
                                        	 %>
                                             <h4>0</h4>
                                             <%
                                        } %>
                                    </div>
                                </div>
                                <div class="iq-progress-bar mt-2">
                                    <span class="bg-success iq-progress progress-1" data-percent="75">
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="card card-block card-stretch card-height">
                    <div class="card-header d-flex justify-content-between">
                        <div class="header-title">
                            <h4 class="card-title">Tổng Quan</h4>
                        </div>                        
                        <div class="card-header-toolbar d-flex align-items-center">
                            <div class="dropdown">
                                <span class="dropdown-toggle dropdown-bg btn" id="dropdownMenuButton001"
                                    data-toggle="dropdown">
                                    Tháng này<i class="ri-arrow-down-s-line ml-1"></i>
                                </span>
                                <div class="dropdown-menu dropdown-menu-right shadow-none"
                                    aria-labelledby="dropdownMenuButton001">
                                    <a class="dropdown-item" href="#">Năm</a>
                                    <a class="dropdown-item" href="#">Tháng</a>
                                    <a class="dropdown-item" href="#">Tuần</a>
                                </div>
                            </div>
                        </div>
                    </div>                    
                    <div class="card-body">
                        <div id="layout1-chart1"></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="card card-block card-stretch card-height">
                    <div class="card-header d-flex align-items-center justify-content-between">
                        <div class="header-title">
                            <h4 class="card-title">Doanh Thu So Với Chi Phí</h4>
                        </div>
                        <div class="card-header-toolbar d-flex align-items-center">
                            <div class="dropdown">
                                <span class="dropdown-toggle dropdown-bg btn" id="dropdownMenuButton002"
                                    data-toggle="dropdown">
                                    Tháng Này<i class="ri-arrow-down-s-line ml-1"></i>
                                </span>
                                <div class="dropdown-menu dropdown-menu-right shadow-none"
                                    aria-labelledby="dropdownMenuButton002">
                                    <a class="dropdown-item" href="#">Hằng Năm</a>
                                    <a class="dropdown-item" href="#">Hằng Tháng</a>
                                    <a class="dropdown-item" href="#">Hằng Tuần</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div id="layout1-chart-2" style="min-height: 360px;"></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
            <%
            if(request.getAttribute("listProTop")!=null){ 
        		ArrayList<RevenueExpense> listProTop = (ArrayList<RevenueExpense>) request.getAttribute("listProTop");
            %>
                <div class="card card-block card-stretch card-height">
                    <div class="card-header d-flex align-items-center justify-content-between">
                        <div class="header-title">
                            <h4 class="card-title">Top Sản Phẩm</h4>
                        </div>
                        <div class="card-header-toolbar d-flex align-items-center">
                            <div class="dropdown">
                                <span class="dropdown-toggle dropdown-bg btn" id="dropdownMenuButton006"
                                    data-toggle="dropdown">
                                    Tháng Này<i class="ri-arrow-down-s-line ml-1"></i>
                                </span>
                                <div class="dropdown-menu dropdown-menu-right shadow-none"
                                    aria-labelledby="dropdownMenuButton006">
                                    <a class="dropdown-item" href="#">Năm</a>
                                    <a class="dropdown-item" href="#">Tháng</a>
                                    <a class="dropdown-item" href="#">Tuần</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <ul class="list-unstyled row top-product mb-0">
                        <%
                        	if(listProTop.size()>0){
                        		for(RevenueExpense objListPro1: listProTop){
                        %>
                            <li class="col-lg-3">
                                <div class="card card-block card-stretch card-height mb-0">
                                    <div class="card-body">
                                        <div class="bg-warning-light rounded">
                                            <img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=objListPro1.getImages() %>" class="style-img img-fluid m-auto p-3" alt="image">
                                        </div>
                                        <div class="style-text text-left mt-3">
                                            <h5 class="mb-1"><%=objListPro1.getName() %></h5>
                                            <p class="mb-0"><%=objListPro1.getQuantity_sold() %> Item</p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <%} %>
                        </ul>
                    </div>
                </div>
            </div>
            <div  class="col-lg-4 ">  
            	<div class="card card-block card-stretch card-height">
            		<div class="card card-transparent card-block card-stretch mb-4">
	                    <div class="card-header d-flex align-items-center justify-content-between">
	                        <div class="header-title">
	                            <h4 class="card-title mb-0">Mặt Hàng Tốt Nhất</h4>
	                        </div>
	                    </div>
                	</div>
	                <div id ="style-11" class="scrollbar force-overflow">
	                <% 
	                	
	                		
	                			for(RevenueExpense objProTop: listProTop){
	                %>
		                <div class="card card-block card-stretch card-height-helf ">
		                    <div class="card-body card-item-right">
		                        <div class="d-flex align-items-top">
		                            <div class="bg-warning-light rounded">
		                                <img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=objProTop.getImages() %>" class="style-img img-fluid m-auto" alt="image">
		                            </div>
		                            <div class="style-text text-left">
		                                <h5 class="mb-2"><%=objProTop.getName() %></h5>
		                                <p class="mb-2">Total Sell : <%=objProTop.getQuantity_sold() %></p>
		                                <p class="mb-0">Total Earned : <%=objProTop.getExpense() %></p>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <%}}}%>
	                </div>
            	</div>
            </div> 
            <div class="col-lg-4">  
                <div class="card card-block card-stretch card-height-helf">
                    <div class="card-body">
                        <div class="d-flex align-items-top justify-content-between">
                            <div class="">
                                <p class="mb-0">Thu Nhập</p>
                                <h5>$ 98,7800 K</h5>
                            </div>
                            <div class="card-header-toolbar d-flex align-items-center">
                                <div class="dropdown">
                                    <span class="dropdown-toggle dropdown-bg btn" id="dropdownMenuButton003"
                                        data-toggle="dropdown">
                                        Tháng Này<i class="ri-arrow-down-s-line ml-1"></i>
                                    </span>
                                    <div class="dropdown-menu dropdown-menu-right shadow-none"
                                        aria-labelledby="dropdownMenuButton003">
                                        <a class="dropdown-item" href="#">Năm</a>
                                        <a class="dropdown-item" href="#">Tháng</a>
                                        <a class="dropdown-item" href="#">Tuần</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="layout1-chart-3" class="layout-chart-1"></div>
                    </div>
                </div>
                <div class="card card-block card-stretch card-height-helf">
                    <div class="card-body">
                        <div class="d-flex align-items-top justify-content-between">
                            <div class="">
                                <p class="mb-0">Chi Phí</p>
                                <h5>$ 45,8956 K</h5>
                            </div>
                            <div class="card-header-toolbar d-flex align-items-center">
                                <div class="dropdown">
                                    <span class="dropdown-toggle dropdown-bg btn" id="dropdownMenuButton004"
                                        data-toggle="dropdown">
                                        Tháng này<i class="ri-arrow-down-s-line ml-1"></i>
                                    </span>
                                    <div class="dropdown-menu dropdown-menu-right shadow-none"
                                        aria-labelledby="dropdownMenuButton004">
                                        <a class="dropdown-item" href="#">Năm</a>
                                        <a class="dropdown-item" href="#">Tháng</a>
                                        <a class="dropdown-item" href="#">Tuần</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="layout1-chart-4" class="layout-chart-2"></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">  
                <div class="card card-block card-stretch card-height">
                    <div class="card-header d-flex justify-content-between">
                        <div class="header-title">
                            <h4 class="card-title">Tóm Tắt Đặt Hàng</h4>
                        </div>                        
                        <div class="card-header-toolbar d-flex align-items-center">
                            <div class="dropdown">
                                <span class="dropdown-toggle dropdown-bg btn" id="dropdownMenuButton005"
                                    data-toggle="dropdown">
                                    Tháng Này<i class="ri-arrow-down-s-line ml-1"></i>
                                </span>
                                <div class="dropdown-menu dropdown-menu-right shadow-none"
                                    aria-labelledby="dropdownMenuButton005">
                                    <a class="dropdown-item" href="#">Năm</a>
                                    <a class="dropdown-item" href="#">Tháng</a>
                                    <a class="dropdown-item" href="#">Tuần</a>
                                </div>
                            </div>
                        </div>
                    </div> 
                    <div class="card-body">
                        <div class="d-flex flex-wrap align-items-center mt-2">
                            <div class="d-flex align-items-center progress-order-left">
                                <div class="progress progress-round m-0 orange conversation-bar" data-percent="46">
                                    <span class="progress-left">
                                        <span class="progress-bar"></span>
                                    </span>
                                    <span class="progress-right">
                                        <span class="progress-bar"></span>
                                    </span>
                                    <div class="progress-value text-secondary">46%</div>
                                </div>
                                <div class="progress-value ml-3 pr-5 border-right">
                                    <h5>$12,6598</h5>
                                    <p class="mb-0">Trung Bình Đặt Hàng</p>
                                </div>
                            </div>
                            <div class="d-flex align-items-center ml-5 progress-order-right">
                                <div class="progress progress-round m-0 primary conversation-bar" data-percent="46">
                                    <span class="progress-left">
                                        <span class="progress-bar"></span>
                                    </span>
                                    <span class="progress-right">
                                        <span class="progress-bar"></span>
                                    </span>
                                    <div class="progress-value text-primary">46%</div>
                                </div>
                                <div class="progress-value ml-3">
                                    <h5>$59,8478</h5>
                                    <p class="mb-0">Top Đặt Hàng</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body pt-0">
                        <div id="layout1-chart-5"></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Page end  -->
    </div>
      </div>
    </div>
    <!-- Wrapper End-->
      <%@ include file="/templates/admin/inc/footer.jsp" %>  