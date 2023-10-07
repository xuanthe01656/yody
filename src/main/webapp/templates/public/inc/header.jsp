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
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/font-awesome.min.css" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/bootstrap.css" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/style.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/magnific-popup.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/assets/css/owl.carousel.css">
  <link rel="shortcut icon" href="<%=request.getContextPath() %>/templates/public/assets/images/favicon.png">
  <link rel="apple-touch-icon" href="<%=request.getContextPath() %>/templates/public/assets/images/apple-touch-icon.png">
  <link rel="apple-touch-icon" sizes="72x72" href="<%=request.getContextPath() %>/templates/public/assets/images/apple-touch-icon-72x72.png">
  <link rel="apple-touch-icon" sizes="114x114" href="<%=request.getContextPath() %>/templates/public/assets/images/apple-touch-icon-114x114.png">
</head>

<body>
  <!-- =====  LODER  ===== -->
  <div class="loder"></div>
  <div class="wrapper">
    <div id="subscribe-me" class="modal animated fade in" role="dialog" data-keyboard="true" tabindex="-1">
      <div class="newsletter-popup"> <img class="offer" src="<%=request.getContextPath() %>/templates/public/assets/images/newsbg.jpg" alt="offer">
        <div class="newsletter-popup-static newsletter-popup-top">
          <div class="popup-text">
            <div class="popup-title">50% <span>Giảm</span></div>
            <div class="popup-desc">
              <div>Đăng Ký và nhận giảm 50% cho lần đặt hàng tiếp theo</div>
            </div>
          </div>
          <form onsubmit="return  validatpopupemail();" method="post">
            <div class="form-group required">
              <input type="email" name="email-popup" id="email-popup" placeholder="Enter Your Email" class="form-control input-lg" required />
              <button type="submit" class="btn btn-default btn-lg" id="email-popup-submit">Đăng Ký</button>
              <label class="checkme">
                <input type="checkbox" value="" id="checkme" /> Không hiển thị lại.</label>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- =====  HEADER START  ===== -->
    <header id="header" class="sticky">
      <div class="header-top">
        <div class="container">
          <div class="row">
            <div class="col-xs-12 col-sm-4">
              <div class="header-top-left">
                <div class="contact"><a href="#">Gọi ngay !</a> <span class="hidden-xs hidden-sm hidden-md">xxxxxxxxxx</span></div>
              </div>
            </div>
            <div class="col-xs-12 col-sm-8">
              <ul class="header-top-right text-right">
                <li class="account"><a href="<%=request.getContextPath() %>/auth/login.jsp">My Account</a></li>
                <li class="language dropdown"> <span class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" role="button">Ngôn ngữ <span class="caret"></span> </span>
                  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="#">Vietnamese</a></li>
					<li><a href="#">English</a></li>
                    <li><a href="#">French</a></li>
                    <li><a href="#">German</a></li>
                  </ul>
                </li>
                <li class="currency dropdown"> <span class="dropdown-toggle" id="dropdownMenu12" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" role="button">Tiền tệ <span class="caret"></span> </span>
                  <ul class="dropdown-menu" aria-labelledby="dropdownMenu12">
                    <li><a href="#">$ VND</a></li>
					<li><a href="#">€ Euro</a></li>
                    <li><a href="#">£ Pound Sterling</a></li>
                    <li><a href="#">$ US Dollar</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="header">
        <div class="container">
          <div class="row">
		  <div class="navbar-header col-xs-6 col-sm-1"> <a class="navbar-brand" href="<%=request.getContextPath() %>/public/index.jsp"> <img alt="YODY" src="<%=request.getContextPath() %>/templates/public/assets/images/logo.svg"> </a> </div>
            <div class="col-xs-12 col-sm-4">
              <div class="main-search">
                <input id="search-input" name="search" value="" placeholder="Search" class="form-control input-lg" autocomplete="off" type="text">
                <span class="input-group-btn">
              <button type="button" class="btn btn-default btn-lg"><i class="fa fa-search"></i></button>
              </span> </div>
            </div>
            <div class="col-xs-6 col-sm-4 shopcart" style="width: 670px;">
              <div id="cart" class="btn-group btn-block mtb_40">
                <button type="button" class="btn" data-target="#cart-dropdown" data-toggle="collapse" aria-expanded="true"><span id="shippingcart">Giỏ hàng</span><span id="cart-total">Mặt hàng (0)</span> </button>
              </div>
              <div id="cart-dropdown" class="cart-menu collapse">
                <ul style="width: 320px;">
                  <li>
                    <table class="table table-striped">
                      <tbody>
                        <tr>
                          <td class="text-center"><a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/product/70x84.jpg" alt="iPod Classic" title="iPod Classic"></a></td>
                          <td class="text-left product-name"><a href="#">Polo</a> <span class="text-left price">200.000VND</span>
                            <input class="cart-qty" name="product_quantity" min="1" value="1" type="number">
                          </td>
                          <td class="text-center"><a class="close-cart"><i class="fa fa-times-circle"></i></a></td>
                        </tr>
                        <tr>
                          <td class="text-center"><a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/product/70x84.jpg" alt="iPod Classic" title="iPod Classic"></a></td>
                          <td class="text-left product-name"><a href="#">Polo2</a> <span class="text-left price">200.000VND</span>
                            <input class="cart-qty" name="product_quantity" min="1" value="1" type="number">
                          </td>
                          <td class="text-center"><a class="close-cart"><i class="fa fa-times-circle"></i></a></td>
                        </tr>
                      </tbody>
                    </table>
                  </li>
                  <li>
                    <table class="table">
                      <tbody>
                        <tr>
                          <td class="text-right"><strong>Tổng Cộng</strong></td>
                          <td class="text-right">400.000VND</td>
                        </tr>
                        <!--<tr>
                          <td class="text-right"><strong>Eco Tax (-2.00)</strong></td>
                          <td class="text-right">VND2.00</td>
                        </tr>-->
                        <tr>
                          <td class="text-right"><strong>VAT (20%)</strong></td>
                          <td class="text-right">80.000VND</td>
                        </tr>
                        <tr>
                          <td class="text-right"><strong>Tổng</strong></td>
                          <td class="text-right">480.000VND</td>
                        </tr>
                      </tbody>
                    </table>
                  </li>
                  <li>
                    <form action="<%=request.getContextPath() %>/public/cart_page.jsp">
                      <input class="btn pull-left mt_10" value="Xem Giỏ Hàng" type="submit">
                    </form>
                    <form action="<%=request.getContextPath() %>/public/checkout_page.jsp">
                      <input class="btn pull-right mt_10" value="Thanh Toán" type="submit">
                    </form>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <nav class="navbar">
            <p>Menu</p>
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse"> <span class="i-bar"><i class="fa fa-bars"></i></span></button>
            <div class="collapse navbar-collapse js-navbar-collapse">
              <ul id="menu" class="nav navbar-nav">
                <li class="dropdown mega-dropdown"> <a href="<%=request.getContextPath() %>/public/category_page.jsp" class="dropdown-toggle" data-toggle="dropdown">NỮ</a>
					<ul class="dropdown-menu mega-dropdown-menu row">
                    <li class="col-md-3">
                      <ul>
                        <li class="dropdown-header">Áo Nữ</li>
                        <li><a href="#">Áo Polo</a></li>
                        <li><a href="#">Áo Thun</a></li>
                        <li><a href="#">Áo Khoát</a></li>
                        <li><a href="#">Áo Sơ Mi</a></li>
                        <li><a href="#">Áo Chống Nắng</a></li>
                        <li><a href="#">Áo Len</a></li>
						<li><a href="#">Áo Nỉ</a></li>
                      </ul>
                    </li>
                    <li class="col-md-3">
                      <ul>
                        <li class="dropdown-header">Quần Nữ</li>
                        <li><a href="#">Quần Jean</a></li>
                        <li><a href="#">Quần Âu</a></li>
                        <li><a href="#">Quần Kaki</a></li>
                        <li><a href="#">Quần Short</a></li>
                        <li><a href="#">Quần Nỉ</a></li>
                      </ul>
                    </li>
                    <li class="col-md-3">
                      <ul>
                        <li class="dropdown-header">Đồ Thể Thao Nữ</li>
						<li><a href="#">Áo Thun Thể Thao</a></li>
                        <li><a href="#">Áo Polo Thể Thao</a></li>
                        <li><a href="#">Bộ Thể Thao</a></li>
                      </ul>
                    </li>
                    <li class="col-md-3">
                      <ul>
                        <li id="myCarousel" class="carousel slide" data-ride="carousel">
                          <div class="carousel-inner">
                            <div class="item active"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/menu-banner1.jpg" class="img-responsive" alt="Banner1"></a></div>
                            <!-- End Item -->
                            <div class="item"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/menu-banner2.jpg" class="img-responsive" alt="Banner1"></a></div>
                            <!-- End Item -->
                            <div class="item"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/menu-banner3.jpg" class="img-responsive" alt="Banner1"></a></div>
                            <!-- End Item -->
                          </div>
                          <!-- End Carousel Inner -->
                        </li>
                        <!-- /.carousel -->
                      </ul>
                    </li>
                  </ul>
				</li>
                <li class="dropdown mega-dropdown"> <a href="<%=request.getContextPath() %>/public/category_page.jsp" class="dropdown-toggle" data-toggle="dropdown">NAM</a>
					<ul class="dropdown-menu mega-dropdown-menu row">
                    <li class="col-md-3">
                      <ul>
                        <li class="dropdown-header">Áo Nam</li>
                        <li><a href="#">Áo Polo</a></li>
                        <li><a href="#">Áo Thun</a></li>
                        <li><a href="#">Áo Khoát</a></li>
                        <li><a href="#">Áo Sơ Mi</a></li>
                        <li><a href="#">Áo Len</a></li>
                        <li><a href="#">Áo Nỉ</a></li>
						<li><a href="#">Áo Vest</a></li>
                      </ul>
                    </li>
                    <li class="col-md-3">
                      <ul>
                        <li class="dropdown-header">Quần Nam</li>
                        <li><a href="#">Quần Jeans</a></li>
                        <li><a href="#">Quần Âu</a></li>
                        <li><a href="#">Quần Kaki</a></li>
                        <li><a href="#">Quần Short</a></li>
                      </ul>
                    </li>
                    <li class="col-md-3">
                      <ul>
                        <li class="dropdown-header">Đồ Thể Thao Nam</li>
						<li><a href="#">Áo Thun Thể Thao</a></li>
                        <li><a href="#">Áo Polo Thể Thao</a></li>
                        <li><a href="#">Quần Thể Thao</a></li>
						<li><a href="#">Bộ Thể Thao</a></li>
                      </ul>
                    </li>
                    <li class="col-md-3">
                      <ul>
                        <li id="myCarousel" class="carousel slide" data-ride="carousel">
                          <div class="carousel-inner">
                            <div class="item active"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/menu-banner1.jpg" class="img-responsive" alt="Banner1"></a></div>
                            <!-- End Item -->
                            <div class="item"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/menu-banner2.jpg" class="img-responsive" alt="Banner1"></a></div>
                            <!-- End Item -->
                            <div class="item"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/menu-banner3.jpg" class="img-responsive" alt="Banner1"></a></div>
                            <!-- End Item -->
                          </div>
                          <!-- End Carousel Inner -->
                        </li>
                        <!-- /.carousel -->
                      </ul>
                    </li>
                  </ul>
				</li>
                <li class="dropdown mega-dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">TRẺ EM </a>
                  <ul class="dropdown-menu mega-dropdown-menu row">
                    <li class="col-md-3">
                      <ul>
                        <li class="dropdown-header">Áo Trẻ Em</li>
                        <li><a href="#">Áo Polo</a></li>
                        <li><a href="#">Áo Thun</a></li>
                        <li><a href="#">Áo Khoát</a></li>
                        <li><a href="#">Áo Sơ Mi</a></li>
                        <li><a href="#">Áo Len</a></li>
						<li><a href="#">Áo Nỉ</a></li>
                      </ul>
                    </li>
                    <li class="col-md-3">
                      <ul>
                        <li class="dropdown-header">Pant</li>
                        <li><a href="#">Quần Jean</a></li>
                        <li><a href="#">Quần Kaki</a></li>
                        <li><a href="#">Quần Short</a></li>
                        <li><a href="#">Quần Nỉ</a></li>
						<li><a href="#">Quần Legging</a></li>
                      </ul>
                    </li>
                    <li class="col-md-3">
                      <ul>
                        <li class="dropdown-header">Đồ Thể Thao Trẻ Em</li>
						<li><a href="#">Áo Thun Thể Thao</a></li>
                        <li><a href="#">Áo Polo Thể Thao</a></li>
                        <li><a href="#">Quần Thể Thao</a></li>
						<li><a href="#">Bộ Thể Thao</a></li>
                      </ul>
                    </li>
                    <li class="col-md-3">
                      <ul>
                        <li id="myCarousel" class="carousel slide" data-ride="carousel">
                          <div class="carousel-inner">
                            <div class="item active"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/menu-banner1.jpg" class="img-responsive" alt="Banner1"></a></div>
                            <!-- End Item -->
                            <div class="item"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/menu-banner2.jpg" class="img-responsive" alt="Banner1"></a></div>
                            <!-- End Item -->
                            <div class="item"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/menu-banner3.jpg" class="img-responsive" alt="Banner1"></a></div>
                            <!-- End Item -->
                          </div>
                          <!-- End Carousel Inner -->
                        </li>
                        <!-- /.carousel -->
                      </ul>
                    </li>
                  </ul>
				</li>
                <li> <a href="#">BỘ SƯU TẬP</a></li>
                <li> <a href="#">ĐỒNG PHỤC</a></li>
                <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">Pages </a>
                  <ul class="dropdown-menu">
                    <li> <a href="<%=request.getContextPath() %>/public/contact_us.jsp">Liên Hệ</a></li>
                    <li> <a href="<%=request.getContextPath() %>/public/cart_page.jsp">Giỏ Hàng</a></li>
                    <li> <a href="<%=request.getContextPath() %>/public/checkout_page.jsp">Thanh Toán</a></li>
                    <li> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp">Chi Tiết Trang</a></li>
                    <li> <a href="<%=request.getContextPath() %>/public/single_blog.jsp">Bài Đăng</a></li>
                  </ul>
                </li>
              </ul>
            </div>
            <!-- /.nav-collapse -->
          </nav>
        </div>
      </div>
	  <script>
		/*window.onscroll = function() {myFunction()};
		var header = document.getElementById("header");
		var sticky = header.offsetTop;
		function myFunction() {
		  if (window.pageYOffset > sticky) {
			header.classList.add("sticky");
		  } else {
			header.classList.remove("sticky");
		  }
		}*/
		</script>
    </header>
    <!-- =====  HEADER END  ===== -->