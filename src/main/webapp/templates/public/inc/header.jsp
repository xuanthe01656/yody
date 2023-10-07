<%@page import="model.bean.ProductAdminDetail"%>
<%@page import="model.bean.User"%>
<%@page import="jakarta.websocket.Session"%>
<%@page import="model.bean.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.MenuDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if (gte IE 9)|!(IE)]><!-->
<html>
<!--<![endif]-->

<head>
  <!-- =====  BASIC PAGE NEEDS  ===== -->
  <meta charset="utf-8">
  <title>YODY - Mặc Mỗi Ngày, Thoải Mái Mỗi Ngày</title>
  <!-- =====  SEO MATE  ===== -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="distribution" content="global">
  <meta name="revisit-after" content="2 Days">
  <meta name="robots" content="ALL">
  <meta name="rating" content="8 YEARS">
  <meta name="Language" content="en-us">
  <meta name="GOOGLEBOT" content="NOARCHIVE">
  <!-- =====  MOBILE SPECIFICATION  ===== -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="viewport" content="width=device-width">
  <!-- =====  CSS  ===== -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/vendor/@fortawesome/fontawesome-free/css/all.min.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/vendor/line-awesome/dist/line-awesome/css/line-awesome.min.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/vendor/remixicon/fonts/remixicon.css">
      
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/font-awesome.min.css" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/bootstrap.css" />
 
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/style.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/style.scss">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/magnific-popup.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/owl.carousel.css">
  <link rel="shortcut icon" href="<%=request.getContextPath() %>/templates/public/assets/images/favicon.png">
  <link rel="apple-touch-icon" href="<%=request.getContextPath() %>/templates/public/assets/images/apple-touch-icon.png">
  <link rel="apple-touch-icon" sizes="72x72" href="<%=request.getContextPath() %>/templates/public/assets/images/apple-touch-icon-72x72.png">
  <link rel="apple-touch-icon" sizes="114x114" href="<%=request.getContextPath() %>/templates/public/assets/images/apple-touch-icon-114x114.png">
  
  <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/assets/css/intlTelInput.css">
   <script src="<%=request.getContextPath() %>/templates/public/assets/js/jquery/jquery-3.6.1.min.js" ></script>
   <script src="<%=request.getContextPath() %>/templates/admin/assets/js/ckeditor/ckeditor.js"></script>
    <!-- Use as a Vanilla JS plugin -->
	<script src="<%=request.getContextPath() %>/templates/public/assets/js/intlTelInput.min.js"></script>
	<!-- Use as a jQuery plugin -->
	
	<!-- <script src="https://code.jquery.com/jquery-latest.min.js"></script>-->
	<script src="<%=request.getContextPath() %>/templates/public/assets/js/intlTelInput-jquery.min.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/owl.carousel.min.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/map.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/mail.js"></script>
<script src="<%=request.getContextPath() %>/templates/public/assets/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/jquery.magnific-popup.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/jquery.firstVisitPopup.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/jquery.MultiFile.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js" ></script>
   <script src="<%=request.getContextPath() %>/templates/public/assets/js/jquery-validation-1.19.5/dist/jquery.validate.min.js"></script>
 	<script src="<%=request.getContextPath() %>/templates/public/assets/js/custom.js"></script>
</head>

<body>
  <!-- =====  LODER  ===== -->
  <div class="loder"></div>
  <div class="wrapper">
    <!-- =====  HEADER START  ===== -->
      <div id="header" class="header" >
        <div class="container" >
          <div class="row">
			  <div class="navbar-header col-sm-1"> <a class="navbar-brand" href="<%=request.getContextPath() %>/public/index"> <img alt="YODY" src="<%=request.getContextPath() %>/templates/public/assets/images/logo.svg"> </a> </div>
	           <div class="main-search">
	                <input id="search-input" name="search" value="" placeholder="Search" class="form-control input-lg" autocomplete="off" type="text">
	               	<span class="input-group-btn">
	              		<button type="button" class="btn btn-default btn-lg"><i class="fa fa-search"></i></button>
	              	</span> 
	           </div>
	            <div class="list-contact">					
					<span class="text-free">FREE</span>
					<a href="tel:18002086" class="phone"><b>1800 2086</b> </a>
					<a href="#" class="map">Tìm <span class="number">xxx</span> cửa hàng</a>
				</div>
          </div>
          <div style="display: flex; height: 40px">
         
          	<nav class="navbar">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse"> <span class="i-bar"><i class="fa fa-bars"></i></span></button>
            <div class="collapse navbar-collapse js-navbar-collapse">
              <ul id="menu" class="nav navbar-nav" style="display: flex;">
              	<li class="nav-item ">
					<a class="a-img" href="#" title="SALE 50%">SALE 50%</a>
				</li>
				 <%
				 MenuDAO objM = new MenuDAO();
					ArrayList<Menu> listMenu = objM.getItemMenu();
					if(listMenu.size()>0){
						for(Menu menu: listMenu){
				%>
                <li class="dropdown mega-dropdown"> <a id="catagory" href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=menu.getId()%>" class="dropdown-toggle" ><%=menu.getName() %></a>
                
                	<ul class="dropdown-menu mega-dropdown-menu row">
	                    <li class="cat_menu">
	                      <ul>
	                      <%
                	ArrayList<Menu> listChilMenu = objM.getItemChilMenu(menu.getId());
                	if(listChilMenu.size()>0){
                		for(Menu chilMenu: listChilMenu){
                %>
	                        <li class="display-col"><a href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=chilMenu.getId()%>" title="<%=chilMenu.getName() %>" class="a3"><%=chilMenu.getName() %></a>
	                        
	                       
		                        <ul class="dropdown-header">
		                         <%
	                				ArrayList<Menu> listChilMenu1 = objM.getItemChilMenu(chilMenu.getId());
	                				if(listChilMenu.size()>0){
	                				for(Menu chilMenu1: listChilMenu1){
                				%>
		                        	<li><a href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=chilMenu1.getId()%>"><%=chilMenu1.getName() %></a></li>
		                        	  <%}} %>
		                        </ul>
		                    </li>
		                      <%}} %>            
	                      </ul>
	                    </li>
	                    <li> <span class="nav-indicator"></span></li>    
	                    <li class="panner">
			                      <ul>
			                        <li id="myCarousel" class="carousel slide" data-ride="carousel">
			                          <div class="carousel-inner">
			                            <div class="item active"> <a href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=menu.getId()%>"><img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/category/<%=menu.getImgaes() %>" class="img-responsive" alt="<%=menu.getName() %>"></a></div>
			                            <!-- End Item -->
			                          </div>
			                          <!-- End Carousel Inner -->
			                        </li>
			                        <!-- /.carousel -->
			                      </ul>
			                  </li>		    
                    </ul>
                    
                 </li>
                 <%}} %> 
                   <%
					MenuDAO objM1 = new MenuDAO();
					ArrayList<Menu> listMenu1 = objM.getItemMenuM();
					if(listMenu1.size()>0){
						for(Menu menu1: listMenu1){
				%>
                <li class="dropdown1"> <a id="catagory" href="#" class="dropdown-toggle" ><%=menu1.getName() %></a>
                
                	<ul class="dropdown-content">
	                    <li class="cat_menu1">
	                      <ul>
	                      <%
                	ArrayList<Menu> listChilMenu1 = objM1.getItemChilMenuM(menu1.getId());
                	if(listChilMenu1.size()>0){
                		for(Menu chilMenu1: listChilMenu1){
                %>
	                        <li class=""><a href="#" title="<%=chilMenu1.getName() %>" class="a4"><%=chilMenu1.getName() %></a>
	                        
	                       
		                        <ul class="item_small child">
		                         <%
	                				ArrayList<Menu> listChilMenu2 = objM1.getItemChilMenuM(chilMenu1.getId());
	                				if(listChilMenu2.size()>0){
	                				for(Menu chilMenu2: listChilMenu2){
                				%>
		                        	<li><a href="#"><%=chilMenu2.getName() %></a></li>
		                        	  <%}} %>
		                        </ul>
		                    </li>
		                      <%}} %>            
	                      </ul>
	                    </li>
	                    <li> <span class="nav-indicator"></span></li>
                    </ul>
                    
                 </li>
                 <%}} %>   
              </ul>            
            </div>
            <!-- /.nav-collapse -->
          </nav>
           <div class="topbar-bottom__right">
				<div class="cart-drop d-lg-flex ajax-cart">
					<a class="cart-wrap" href="<%=request.getContextPath() %>/public/cart" data-target="#cart-dropdown" data-toggle="collapse" aria-expanded="true">
						<img width="28" height="28" src="//bizweb.dktcdn.net/100/438/408/themes/904142/assets/icon-cart-header.svg?1681789957699" alt="giỏ hàng">
						<%
							ArrayList<ProductAdminDetail> listPro =new ArrayList<>();
							if(session.getAttribute("listPro1")!=null){
								listPro = (ArrayList<ProductAdminDetail>) session.getAttribute("listPro1");}
							int amount_cart = listPro.size();
						%>
						<span class="count_item count_item_pr hidden-count"><%=amount_cart %></span>
						<span class="gio-hang">GIỎ HÀNG</span>
					</a>
					<%
						
							float sum_price=0;
						%>
					<div id="cart-dropdown" class="cart-menu collapse">
					<div class="top-cart-content">
					<img style="position: absolute; top: -25px; left: 50px;" src="//bizweb.dktcdn.net/100/438/408/themes/904142/assets/arrow-cart.png?1681789957699">
					<div class="CartHeaderContainer">
					</div>
					</div>
		                <ul>
		                  <li>
		                    <table class="table table-striped">
		                      <tbody>
		                      <%
								if(listPro.size()>0){
									for(ProductAdminDetail objPD: listPro){
										sum_price = (float) (sum_price+(objPD.getPrice()*objPD.getAmount()));
								%>	
									
		                        <tr>
		                        
		                          <td class="text-center"><a href="#"><img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=objPD.getImages() %>" alt="<%=objPD.getName() %>" title="<%=objPD.getName()%>"></a></td>
		                          <td class="text-left product-name"><a href="#"><%=objPD.getName() %></a> 
		                          	<span class="text-left price"><%=objPD.getColor() %>/<%=objPD.getSize() %></span>
		                            <input class="cart-qty change_amount_cart" id="change_amount_cart<%=objPD.getId() %>" data-id="<%=objPD.getId() %>" name="product_quantity" min="1" value="<%=objPD.getAmount() %>" type="number">
		                            <span class="text-left price"><%=(int)((objPD.getPrice() * objPD.getAmount()))%>đ</span>
		                          </td>
		                          <td class="text-center"><a href="javascript: void(0)" data-value="<%=objPD.getId() %>" id="del-cart" class="close-cart del-cart"><i class="fa fa-times-circle"></i></a></td>
		                          
		                        </tr>
		                        <%}}else{
		                	%>
		                		<tr>
		                          <td class="text-center" colspan="4">Không có sản phẩm nào hãy đặt hàng</td>
		                        </tr>
		                	<%}%>
		                      </tbody>
		                    </table>
		                  </li>
		                  <li>
		                    <table class="table">
		                      <tbody>
		                        <tr>
		                          <td class="text-right"><strong>Tổng Cộng</strong></td>
		                          <td class="text-right"><%=(int)sum_price %></td>
		                        </tr>
		                      </tbody>
		                    </table>
		                  </li>
		                  </ul>
		                 <ul>
		                  <li>
		                    <form action="<%=request.getContextPath() %>/public/cart">
		                      <input class="btn mt_10" value="Xem Giỏ Hàng" type="submit">
		                    </form>
		                  </li>
		                </ul>
		                	</div>
		                	
		                	 <script>
		                	 $(function() {
		 					    $('.del-cart').on('click', function() {
		 					        var changedField = $(this);
		 	
		 					        var id = $(this).data("value");
		 					        $.ajax({
		 								url: '<%=request.getContextPath()%>/public/handel-cart',
		 								type: 'POST',
		 								cache: false,
		 								data: {aid: id, },
		 								success: function(data){
		 									$('.ajax-cart').html(data);
		 								},
		 								error: function (){
		 									alert('Có lỗi xảy ra');
		 								}
		 							});
		 					       $.ajax({
		 								url: '<%=request.getContextPath()%>/public/cart',
		 								type: 'POST',
		 								cache: false,
		 								data: {aid: id, },
		 								success: function(data){
		 									$('.ajax-cart1').html(data);
		 								},
		 								error: function (){
		 									alert('Có lỗi xảy ra');
		 								}
		 							});
		 							return false;
		 					    });
		 					   $('.change_amount_cart').on('input', function() {
		 					        var changedField = $(this);
		 					        var qty = $(this).val();
		 					        var id = $(this).data("id");
		 					        
		 					        $.ajax({
		 								url: '<%=request.getContextPath()%>/public/handel-cart',
		 								type: 'POST',
		 								cache: false,
		 								data: {
		 									'id-product': id,
		 									'amount-edit':qty,
		 									},
		 								success: function(data){
		 									$('.ajax-cart').html(data);
		 								},
		 								error: function (){
		 									alert('Có lỗi xảy ra');
		 								}
		 							});
		 					       $.ajax({
		 								url: '<%=request.getContextPath()%>/public/cart',
		 								type: 'POST',
		 								cache: false,
		 								data: {
		 									'id-product': id,
		 									'amount-edit':qty,},
		 								success: function(data){
		 									$('.ajax-cart1').html(data);
		 								},
		 								error: function (){
		 									alert('Có lỗi xảy ra');
		 								}
		 							});
		 							return false;
		 					    });
		 					});
		                	 
		                	 </script>
             	</div>
				<script type="text/javascript">
					
             				$(".cart-wrap").parent('.cart-drop').hover(
             				  function() {
             				    $(this).children('.collapse').collapse('show');
             				  }, function() {
             				    $(this).children('.collapse').collapse('hide');
             				  }
             				);
             				
             		 </script>
				<div class="header-tool d-lg-flex">
					<div class="user">
					<%
						
						if(session.getAttribute("objU")!=null){
							User objU = (User) session.getAttribute("objU");
					%>
						<div class="user-login dropdown1">
							<a class="register collapsed dropdown-toggle" data-target="#user-dropdown" data-toggle="collapse" href="<%=request.getContextPath() %>/public/personal?id=<%=objU.getId()%>">CÁ NHÂN</a>
							<ul id="user-dropdown" class="collapse dropdown-content">
					            <li><a href="<%=request.getContextPath() %>/public/personal?id=<%=objU.getId()%>">Trang Cá Nhân</a></li>
					            <li><a href="<%=request.getContextPath() %>/auth/public/logout">Logout</a></li>
				            </ul>
						</div>
						
						<%}else{
							%>
							<div class="user-login">
								<a class="register" href="<%=request.getContextPath() %>/auth/public/sign-up">ĐĂNG KÝ</a>/
								<a class="login" href="<%=request.getContextPath() %>/auth/public/login">ĐĂNG NHẬP</a>
							</div>
							<%
						} %>
					</div>
					<div class="category-action">
						<svg width="22" height="20" viewBox="0 0 22 20" fill="none" xmlns="http://www.w3.org/2000/svg"></svg>
					</div>
				</div>
			</div>
          </div>
          
        </div>
      </div>
    <!-- =====  HEADER END  ===== -->