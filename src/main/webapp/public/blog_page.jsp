<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>
    <!-- =====  CONTAINER START  ===== -->
    <div class="container">
      <div class="row ">
        <!-- =====  BANNER STRAT  ===== -->
        <div class="col-sm-12">
          <div class="breadcrumb ptb_20">
            <h1>Blog</h1>
            <ul>
              <li><a href="index.jsp">Home</a></li>
              <li class="active">Blog</li>
            </ul>
          </div>
        </div>
        <!-- =====  BREADCRUMB END===== -->
        <div id="column-left" class="col-sm-4 col-lg-3 hidden-xs">
          <div id="category-menu" class="navbar collapse in mb_40" aria-expanded="true" style="" role="button">
            <div class="nav-responsive">
              <div class="heading-part">
                <h2 class="main_title">Top category</h2>
              </div>
              <ul class="nav  main-navigation collapse in">
                <li><a href="#">Appliances</a></li>
                <li><a href="#">Mobile Phones</a></li>
                <li><a href="#">Tablet PC & Accessories</a></li>
                <li><a href="#">Consumer Electronics</a></li>
                <li><a href="#">Computers & Networking</a></li>
                <li><a href="#">Electrical & Tools</a></li>
                <li><a href="#">Apparel</a></li>
                <li><a href="#">Bags & Shoes</a></li>
                <li><a href="#">Toys & Hobbies</a></li>
                <li><a href="#">Watches & Jewelry</a></li>
                <li><a href="#">Home & Garden</a></li>
                <li><a href="#">Health & Beauty</a></li>
                <li><a href="#">Outdoors & Sports</a></li>
              </ul>
            </div>
          </div>
          <div class="left_banner left-sidebar-widget mt_30 mb_40"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/left1.jpg" alt="Left Banner" class="img-responsive" /></a> </div>
          <div class="blog-category left-sidebar-widget mb_50">
            <div class="heading-part mb_20 ">
              <h2 class="main_title">Blog Category</h2>
            </div>
            <ul>
              <li><a href="#">Appliances</a></li>
              <li><a href="#">Mobile Phones</a></li>
              <li><a href="#">Tablet PC & Accessories</a></li>
              <li><a href="#">Consumer Electronics</a></li>
              <li><a href="#">Computers & Networking</a></li>
              <li><a href="#">Electrical & Tools</a></li>
              <li><a href="#">Apparel</a></li>
              <li><a href="#">Bags & Shoes</a></li>
              <li><a href="#">Toys & Hobbies</a></li>
              <li><a href="#">Watches & Jewelry</a></li>
              <li><a href="#">Home & Garden</a></li>
              <li><a href="#">Health & Beauty</a></li>
              <li><a href="#">Outdoors & Sports</a></li>
            </ul>
          </div>
          <div class="left-blog left-sidebar-widget mb_50">
            <div class="heading-part mb_20 ">
              <h2 class="main_title">Latest Post</h2>
            </div>
            <div id="left-blog">
              <div class="row ">
                <div class="blog-item mb_20">
                  <div class="post-format col-xs-4">
                    <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_01.jpg"  alt="YODY"></a></div>
                  </div>
                  <div class="post-info col-xs-8 ">
                    <h5> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h5>
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                  </div>
                </div>
                <div class="blog-item mb_20">
                  <div class="post-format col-xs-4">
                    <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_02.jpg"  alt="YODY"></a></div>
                  </div>
                  <div class="post-info col-xs-8 ">
                    <h5> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h5>
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                  </div>
                </div>
                <div class="blog-item mb_20">
                  <div class="post-format col-xs-4">
                    <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_03.jpg"  alt="YODY"></a></div>
                  </div>
                  <div class="post-info col-xs-8 ">
                    <h5> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h5>
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-8 col-lg-9 mtb_20">
          <div class="row">
            <div class="three-col-blog text-left">
              <div class="blog-item col-md-6 mb_30">
                <div class="post-format">
                  <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_04.jpg"  alt="YODY"></a></div>
                  <div class="post-type"><i class="fa fa-music" aria-hidden="true"></i></div>
                </div>
                <div class="post-info mtb_20 ">
                  <h3 class="mb_10"> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h3>
                  <p>Aliquam egestas pellentesque est. Etiam a orci Nulla id enim feugiat ligula ullamcorper scelerisque. Morbi eu luctus nisl.</p>
                  <div class="details mtb_20">
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                    <div class="more pull-right"> <a href="single_blog.jsp">Read more <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></div>
                  </div>
                </div>
              </div>
              <div class="blog-item col-md-6 mb_30">
                <div class="post-format">
                  <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_05.jpg"  alt="YODY"></a></div>
                  <div class="post-type"><i class="fa fa-music" aria-hidden="true"></i></div>
                </div>
                <div class="post-info mtb_20 ">
                  <h3 class="mb_10"> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h3>
                  <p>Aliquam egestas pellentesque est. Etiam a orci Nulla id enim feugiat ligula ullamcorper scelerisque. Morbi eu luctus nisl.</p>
                  <div class="details mtb_20">
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                    <div class="more pull-right"> <a href="single_blog.jsp">Read more <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></div>
                  </div>
                </div>
              </div>
              <div class="blog-item col-md-6 mb_30">
                <div class="post-format">
                  <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_06.jpg"  alt="YODY"></a></div>
                  <div class="post-type"><i class="fa fa-music" aria-hidden="true"></i></div>
                </div>
                <div class="post-info mtb_20 ">
                  <h3 class="mb_10"> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h3>
                  <p>Aliquam egestas pellentesque est. Etiam a orci Nulla id enim feugiat ligula ullamcorper scelerisque. Morbi eu luctus nisl.</p>
                  <div class="details mtb_20">
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                    <div class="more pull-right"> <a href="single_blog.jsp">Read more <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></div>
                  </div>
                </div>
              </div>
              <div class="blog-item col-md-6 mb_30">
                <div class="post-format">
                  <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_07.jpg"  alt="YODY"></a></div>
                  <div class="post-type"><i class="fa fa-music" aria-hidden="true"></i></div>
                </div>
                <div class="post-info mtb_20 ">
                  <h3 class="mb_10"> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h3>
                  <p>Aliquam egestas pellentesque est. Etiam a orci Nulla id enim feugiat ligula ullamcorper scelerisque. Morbi eu luctus nisl.</p>
                  <div class="details mtb_20">
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                    <div class="more pull-right"> <a href="single_blog.jsp">Read more <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></div>
                  </div>
                </div>
              </div>
              <div class="blog-item col-md-6 mb_30">
                <div class="post-format">
                  <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_08.jpg"  alt="YODY"></a></div>
                  <div class="post-type"><i class="fa fa-music" aria-hidden="true"></i></div>
                </div>
                <div class="post-info mtb_20 ">
                  <h3 class="mb_10"> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h3>
                  <p>Aliquam egestas pellentesque est. Etiam a orci Nulla id enim feugiat ligula ullamcorper scelerisque. Morbi eu luctus nisl.</p>
                  <div class="details mtb_20">
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                    <div class="more pull-right"> <a href="single_blog.jsp">Read more <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></div>
                  </div>
                </div>
              </div>
              <div class="blog-item col-md-6 mb_30">
                <div class="post-format">
                  <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_01.jpg"  alt="YODY"></a></div>
                  <div class="post-type"><i class="fa fa-music" aria-hidden="true"></i></div>
                </div>
                <div class="post-info mtb_20 ">
                  <h3 class="mb_10"> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h3>
                  <p>Aliquam egestas pellentesque est. Etiam a orci Nulla id enim feugiat ligula ullamcorper scelerisque. Morbi eu luctus nisl.</p>
                  <div class="details mtb_20">
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                    <div class="more pull-right"> <a href="single_blog.jsp">Read more <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></div>
                  </div>
                </div>
              </div>
              <div class="blog-item col-md-6 mb_30">
                <div class="post-format">
                  <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_02.jpg"  alt="YODY"></a></div>
                  <div class="post-type"><i class="fa fa-music" aria-hidden="true"></i></div>
                </div>
                <div class="post-info mtb_20 ">
                  <h3 class="mb_10"> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h3>
                  <p>Aliquam egestas pellentesque est. Etiam a orci Nulla id enim feugiat ligula ullamcorper scelerisque. Morbi eu luctus nisl.</p>
                  <div class="details mtb_20">
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                    <div class="more pull-right"> <a href="single_blog.jsp">Read more <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></div>
                  </div>
                </div>
              </div>
              <div class="blog-item col-md-6 mb_30">
                <div class="post-format">
                  <div class="thumb post-img"><a href="single_blog.jsp"> <img src="<%=request.getContextPath() %>/templates/public/assets/images/blog/blog_img_03.jpg"  alt="YODY"></a></div>
                  <div class="post-type"><i class="fa fa-music" aria-hidden="true"></i></div>
                </div>
                <div class="post-info mtb_20 ">
                  <h3 class="mb_10"> <a href="single_blog.jsp">Fashions fade, style is eternal</a> </h3>
                  <p>Aliquam egestas pellentesque est. Etiam a orci Nulla id enim feugiat ligula ullamcorper scelerisque. Morbi eu luctus nisl.</p>
                  <div class="details mtb_20">
                    <div class="date pull-left"> <i class="fa fa-calendar" aria-hidden="true"></i>11 May 2017 </div>
                    <div class="more pull-right"> <a href="single_blog.jsp">Read more <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="pagination-nav text-center mtb_20">
            <ul>
              <li><a href="#"><i class="fa fa-angle-left"></i></a></li>
              <li class="active"><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
            </ul>
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
  <script src="<%=request.getContextPath() %>/templates/public/assets/js/custom.js"></script>
</body>

</html>