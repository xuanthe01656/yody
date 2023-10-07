<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>
    <!-- =====  BANNER STRAT  ===== -->
    <div class="container banner mt_20">
      <div class="main-banner owl-carousel">
        <div class="item"><a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/main_banner1.jpg" alt="Main Banner" class="img-responsive" /></a></div>
        <div class="item"><a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/main_banner2.jpg" alt="Main Banner" class="img-responsive" /></a></div>
      </div>
    </div>
    <!-- =====  BANNER END  ===== -->
    <!-- =====  CONTAINER START  ===== -->
    <div class="container">
      <!-- =====  SUB BANNER  STRAT ===== -->
      <div class="row">
        <div class="cms_banner mt_10">
          <div class="col-xs-4 mt_10">
            <div id="subbanner1" class="sub-hover">
              <div class="sub-img"><a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/sub1.jpg" alt="Sub Banner1" class="img-responsive"></a></div>
              <div class="bannertext text-center">
                <button class="btn mb_10 cms_btn">Xem Sản Phẩm</button>
                <h2>Hats & collapse</h2>
                <p class="mt_10">Wide veriety of sizes,colors</p>
              </div>
            </div>
            <div id="subbanner2" class="sub-hover mt_20">
              <div class="sub-img"><a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/sub2.jpg" alt="Sub Banner2" class="img-responsive"></a></div>
              <div class="bannertext text-center">
                <button class="btn mb_10 cms_btn">Xem Sản Phẩm</button>
                <h2>Buy Scarfs</h2>
                <p class="mt_10">Shop collection of designer</p>
              </div>
            </div>
          </div>
          <div class="col-xs-4 mt_10">
            <div id="subbanner3" class="sub-hover">
              <div class="sub-img"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/sub3.jpg" alt="Sub Banner3" class="img-responsive"></a></div>
            </div>
          </div>
          <div class="col-xs-4 mt_10">
            <div id="subbanner4" class="sub-hover">
              <div class="sub-img"><a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/sub4.jpg" alt="Sub Banner4" class="img-responsive"></a></div>
              <div class="bannertext text-center">
                <button class="btn mb_10 cms_btn">Xem Sản Phẩm</button>
                <h2>Handbags</h2>
                <p class="mt_10">Bags for men & women only</p>
              </div>
            </div>
            <div id="subbanner5" class="sub-hover mt_20">
              <div class="sub-img"><a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/sub5.jpg" alt="Sub Banner5" class="img-responsive"></a></div>
              <div class="bannertext text-center">
                <button class="btn mb_10 cms_btn">Xem Sản Phẩm</button>
                <h2>Footware</h2>
                <p class="mt_10">Over 400 luxury designers</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- =====  SUB BANNER END  ===== -->
      <div class="row ">
        <div class="col-sm-12 mtb_10">
          <!-- =====  PRODUCT TAB  ===== -->
          <div id="product-tab" class="mt_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title">Đồ Thời Trang</h2>
            </div>
            <ul class="nav text-right">
              <li class="active"> <a href="#nArrivals" data-toggle="tab">Điểm Đến Yêu Thích</a> </li>
              <li><a href="#Bestsellers" data-toggle="tab">Bán Chạy Nhất</a> </li>
              <li><a href="#Featured" data-toggle="tab">Đặc Sắc</a> </li>
            </ul>
            <div class="tab-content clearfix box">
              <div class="tab-pane active" id="nArrivals">
                <div class="nArrivals owl-carousel">
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product9.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product9-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm vào giỏ hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product1-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm vào giỏ hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product3.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product3-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product5.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product5-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product7.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product7-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product9.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product9-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="tab-pane" id="Bestsellers">
                <div class="Bestsellers owl-carousel">
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product1-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product3.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product3-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product5.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product5-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product6.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product6-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product8.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product8-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                           <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product10.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product10-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="tab-pane" id="Featured">
                <div class="Featured owl-carousel">
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product2.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product2-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product4.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product4-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product6.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product6-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product8.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product8-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product10.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product10-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                            <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="product-grid">
                    <div class="item">
                      <div class="product-thumb  mb_30">
                        <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product2.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product2-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                          <div class="button-group text-center">
                           <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                          </div>
                        </div>
                        <div class="caption product-detail text-center">
                          <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                          <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                          <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- =====  PRODUCT TAB  END ===== -->
          <!-- =====  SUB BANNER  STRAT ===== -->
          <div class="row">
            <div class="cms_banner mt_50">
              <div class="col-sm-12 mt_10">
                <div id="subbanner3" class="sub-hover">
                  <div class="sub-img"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/sub6.jpg" alt="Sub Banner3" class="img-responsive"></a></div>
                </div>
              </div>
            </div>
          </div>
          <!-- =====  SUB BANNER END  ===== -->
          <!-- =====  PRODUCT TAB  ===== -->
          <div id="product-tab" class="mt_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title">Sản Phẩm Thời Trang</h2>
            </div>
            <div class="tab-content clearfix box">
              <div class="tab-pane active" id="nArrivals">
                <div class="tab-pane" id="Featured">
                  <div class="Featured owl-carousel">
                    <div class="product-grid">
                      <div class="item">
                        <div class="product-thumb  mb_30">
                          <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product2.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product2-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                            <div class="button-group text-center">
                              <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                            </div>
                          </div>
                          <div class="caption product-detail text-center">
                            <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                            <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                            <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                            </span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="product-grid">
                      <div class="item">
                        <div class="product-thumb  mb_30">
                          <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product4.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product4-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                            <div class="button-group text-center">
                              <div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
                            <div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
                            <div class="compare"><a href="#"><span>So Sánh</span></a></div>
                            <div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                            </div>
                          </div>
                          <div class="caption product-detail text-center">
                            <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                            <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                            <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                            </span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="product-grid">
                      <div class="item">
                        <div class="product-thumb  mb_30">
                          <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product6.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product6-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                            <div class="button-group text-center">
								<div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
								<div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
								<div class="compare"><a href="#"><span>So Sánh</span></a></div>
								<div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                            </div>
                          </div>
                          <div class="caption product-detail text-center">
                            <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                            <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                            <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                            </span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="product-grid">
                      <div class="item">
                        <div class="product-thumb  mb_30">
                          <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product8.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product8-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                            <div class="button-group text-center">
								<div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
								<div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
								<div class="compare"><a href="#"><span>So Sánh</span></a></div>
								<div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                            </div>
                          </div>
                          <div class="caption product-detail text-center">
                            <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                            <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                            <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                            </span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="product-grid">
                      <div class="item">
                        <div class="product-thumb  mb_30">
                          <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product10.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product10-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                            <div class="button-group text-center">
								<div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
								<div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
								<div class="compare"><a href="#"><span>So Sánh</span></a></div>
								<div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                            </div>
                          </div>
                          <div class="caption product-detail text-center">
                            <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                            <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                            <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                            </span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="product-grid">
                      <div class="item">
                        <div class="product-thumb  mb_30">
                          <div class="image product-imageblock"> <a href="<%=request.getContextPath() %>/public/product_detail_page.jsp"> <img data-name="product_image" src="<%=request.getContextPath() %>/templates/public/assets/images/product/product2.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/product/product2-1.jpg" alt="iPod Classic" title="iPod Classic" class="img-responsive"> </a>
                            <div class="button-group text-center">
								<div class="wishlist"><a href="#"><span>Danh Sách Yêu Thích</span></a></div>
								<div class="quickview"><a href="#"><span>Xem Nhanh</span></a></div>
								<div class="compare"><a href="#"><span>So Sánh</span></a></div>
								<div class="add-to-cart"><a href="#"><span>Thêm Vào Giỏ Hàng</span></a></div>
                            </div>
                          </div>
                          <div class="caption product-detail text-center">
                            <div class="rating"> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span> <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-x"></i></span> </div>
                            <h6 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem">New LCDScreen and HD Vide..</a></h6>
                            <span class="price"><span class="amount"><span class="currencySymbol">VND</span>xx.xxx</span>
                            </span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- =====  PRODUCT TAB  END ===== -->
          <!-- =====  Blog ===== -->
          <div id="Blog" class="mt_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title">Tin Mới Nhất</h2>
            </div>
            <div class="blog-contain box">
              <div class="blog owl-carousel ">
                <div class="item">
                  <div class="box-holder">
                    <div class="thumb post-img"><a href="#"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_01.jpg" alt="YODY"> </a>
                      <div class="date-time text-center">
                        <div class="day"> 11</div>
                        <div class="month">Aug</div>
                      </div>
                      <div class="post-image-hover"> </div>
                      <div class="post-info">
                        <h6 class="mb_10 text-uppercase"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp">Thời trang chỉ là nhất thời, phong cách mới là mãi mãi</a> </h6>
                        <div class="view-blog">
                          <div class="write-comment pull-left"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp"> 0 Bình luận</a> </div>
                          <div class="read-more pull-right"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp"> <i class="fa fa-link"></i> Xem Thêm</a> </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="item">
                  <div class="box-holder">
                    <div class="thumb post-img"><a href="#"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_02.jpg" alt="YODY"> </a>
                      <div class="date-time text-center">
                        <div class="day"> 11</div>
                        <div class="month">Aug</div>
                      </div>
                      <div class="post-image-hover"> </div>
                      <div class="post-info">
                        <h6 class="mb_10 text-uppercase"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp">Thời trang chỉ là nhất thời, phong cách mới là mãi mãi</a> </h6>
                        <div class="view-blog">
                          <div class="write-comment pull-left"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp"> 0 Bình Luận</a> </div>
                          <div class="read-more pull-right"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp"> <i class="fa fa-link"></i> Xem Thêm</a> </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="item">
                  <div class="box-holder">
                    <div class="thumb post-img"><a href="#"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_01.jpg" alt="YODY"> </a>
                      <div class="date-time text-center">
                        <div class="day"> 11</div>
                        <div class="month">Aug</div>
                      </div>
                      <div class="post-image-hover"> </div>
                      <div class="post-info">
                        <h6 class="mb_10 text-uppercase"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp">Thời trang chỉ là nhất thời, phong cách mới là mãi mãi</a> </h6>
                        <div class="view-blog">
                          <div class="write-comment pull-left"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp"> 0 Bình luận</a> </div>
                          <div class="read-more pull-right"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp"> <i class="fa fa-link"></i> Xem Thêm</a> </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="item">
                  <div class="box-holder">
                    <div class="thumb post-img"><a href="#"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_02.jpg" alt="YODY"> </a>
                      <div class="date-time text-center">
                        <div class="day"> 11</div>
                        <div class="month">Aug</div>
                      </div>
                      <div class="post-image-hover"> </div>
                      <div class="post-info">
                        <h6 class="mb_10 text-uppercase"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp">Thời trang chỉ là nhất thời, phong cách mới là mãi mãi</a> </h6>
                        <div class="view-blog">
                          <div class="write-comment pull-left"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp"> 0 Bình luận</a> </div>
                          <div class="read-more pull-right"> <a href="<%=request.getContextPath() %>/public/single_blog.jsp"> <i class="fa fa-link"></i> Xem Thêm</a> </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- =====  Blog end ===== -->
          </div>
        </div>
      </div>
      <div id="brand_carouse" class="ptb_50 text-center">
        <div class="type-01">
          <div class="row">
            <div class="col-sm-12">
              <div class="brand owl-carousel ptb_20">
                <div class="item text-center"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/brand/brand1.png" alt="Disney" class="img-responsive" /></a> </div>
                <div class="item text-center"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/brand/brand2.png" alt="Dell" class="img-responsive" /></a> </div>
                <div class="item text-center"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/brand/brand3.png" alt="Harley" class="img-responsive" /></a> </div>
                <div class="item text-center"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/brand/brand4.png" alt="Canon" class="img-responsive" /></a> </div>
                <div class="item text-center"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/brand/brand5.png" alt="Canon" class="img-responsive" /></a> </div>
                <div class="item text-center"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/brand/brand6.png" alt="Canon" class="img-responsive" /></a> </div>
                <div class="item text-center"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/brand/brand7.png" alt="Canon" class="img-responsive" /></a> </div>
                <div class="item text-center"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/brand/brand8.png" alt="Canon" class="img-responsive" /></a> </div>
                <div class="item text-center"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/brand/brand9.png" alt="Canon" class="img-responsive" /></a> </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="newsletters mb_50">
        <div class="row">
          <div class="col-sm-6">
            <div class="news-head pull-left">
              <h2>ĐĂNG KÝ ĐỂ NHẬN BẢN TIN</h2>
              <div class="new-desc">Hãy là người đầu tiên biết về Hàng mới của chúng tôi và hơn thế nữa!</div>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="news-form pull-right">
              <form onsubmit="return validatemail();" method="post">
                <div class="form-group required">
                  <input name="email" id="email" placeholder="Enter Your Email" class="form-control input-lg" required="" type="email">
                  <button type="submit" class="btn btn-default btn-lg">Đăng Ký</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- =====  CONTAINER END  ===== -->
    <%@ include file="/templates/public/inc/footer.jsp" %>
    </div>
  <a id="scrollup"></a>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/jQuery_v3.1.1.min.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/owl.carousel.min.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/jquery.magnific-popup.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/jquery.firstVisitPopup.js"></script>
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/custom.js"></script>
</body>

</html>