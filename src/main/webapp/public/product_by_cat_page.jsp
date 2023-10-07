<%@page import="library.RemoveDuplicateElements"%>
<%@page import="java.util.List"%>
<%@page import="library.DefineLb2"%>
<%@page import="library.DefineLb"%>
<%@page import="model.dao.ColourDAO"%>
<%@page import="model.bean.size"%>
<%@page import="model.dao.SizeDAO"%>
<%@page import="model.bean.colur"%>
<%@page import="java.io.File"%>
<%@page import="model.bean.ProductAdmin"%>
<%@page import="model.bean.DisplayPublic"%>
<%@page import="model.dao.PublicDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>

    <!-- =====  SUB BANNER  STRAT ===== -->
                <%
                RemoveDuplicateElements remove = new RemoveDuplicateElements();
                ArrayList<ProductAdmin>listPro1 = new ArrayList<>();
                if(request.getAttribute("listPro")!=null){
                	 listPro1 = (ArrayList<ProductAdmin>)request.getAttribute("listPro");
                }
                	String images = "";
                	String color = "";
                	int id = (Integer) request.getAttribute("id");
                	Menu menu = (Menu) request.getAttribute("menu");
                %>
          <section class="bread-crumb d-md-block">
			<div class="container">
				<div class="row">
					<div class="col-12 a-left">
						<ul class="breadcrumb">
							<li class="home">
								<a href="<%=request.getContextPath() %>/public/index"><span>Trang chủ</span></a>
							</li>
							<li class="last">
								<a href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=menu.getId()%>"><strong><span> <%=menu.getName() %></span></strong></a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</section>
		
          <!-- =====  SUB BANNER END  ===== -->
    <!-- =====  CONTAINER START  ===== -->
    <div class="container">
      <!-- =====  SUB BANNER  STRAT ===== -->
      <!-- =====  SUB BANNER END  ===== -->
      <div class="row ">
      <div id="column-left" class="col-sm-4 col-lg-3 hidden-xs">
          <div id="category-menu" class="navbar collapse in mb_40" aria-expanded="true" style="" role="button">
            <div class="nav-responsive">
              <div class="heading-part">
                <h2 class="main_title">Loại Sản Phẩm</h2>
              </div>
              <ul class="nav  main-navigation collapse in">
               	<li><a>Coming Soon</a></li>
              </ul>
            </div>
          </div>
          <div class="left-special left-sidebar-widget mb_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title">Kích Thước</h2>
            </div>
            <fieldset class="checkbox-group">
				<div class="checkbox">
					<ul class="row list-group">
						<%
					Object index1 = session.getAttribute("index");
           			SizeDAO objSz= new SizeDAO();
           			ArrayList<size> listSize = objSz.getSize();
           			if(listSize.size()>0){
           				for(size objsize: listSize){
           		%>
           					<li>
								<label class="checkbox-wrapper">
									<input id="size<%=objsize.getId() %>" type="checkbox"  value=<%=objsize.getId()%> class="checkbox-input size" />
									<span class="checkbox-tile">
										<span class="checkbox-label"><%=objsize.getName() %></span>
									</span>
								</label>
								<script>
			                		$('#size<%=objsize.getId() %>').on('click', function(){
			                			$(this).attr('checked');
			                			$('#size<%=objsize.getId() %>').removeAttr('checked');
			                			if(this.checked){
			                				var size_id = $(this).val();
				                			var menu_id = <%=menu.getId()%>;
				                			$.ajax({	
				               					url: '<%=request.getContextPath()%>/public/filter-product',
				               					type: 'POST',
				               					cache: false,
				               					data: {
				               						asize_id: size_id,
				               						amenu_id: menu_id,
				               					},
				               					success: function(data){
				               						$('.ajax-show-filter-product').html(data);
				               						$('#indexs<%=index1%>').addClass('active')
				               					},
				               					error: function (){
				               						alert('Có lỗi xảy ra');
				               					},
				               			  });	
			                			}else{
			                				var size_id = $(this).val();
			                				var menu_id = <%=menu.getId()%>;
				                			$.ajax({	
				               					url: '<%=request.getContextPath()%>/public/filter-product',
				               					type: 'POST',
				               					cache: false,
				               					data: {
				               						usid: size_id,
				               						amenu_id: menu_id,
				               					},
				               					success: function(data){
				               						$('.ajax-show-filter-product').html(data);
				               						$('#indexs<%=index1%>').addClass('active')
				               					},
				               					error: function (){
				               						alert('Có lỗi xảy ra');
				               					},
				               			  });	
			                			}
			                		});
			                	</script>
							</li>
                	
                <%}} %>
                </ul>
                </div>
		 </fieldset>
          </div>
          <div class="left-special left-sidebar-widget mb_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title">Màu Sắc</h2>
            </div>
            <fieldset class="checkbox-group">
				<div class="checkbox">
           		<ul class="row list-group">
                <%
           			ColourDAO objCl= new ColourDAO();
           			ArrayList<colur> listColor = objCl.getColour();
           			if(listColor.size()>0){
           				for(colur objcolor: listColor){
           		%>
                <li>
                	<label class="checkbox-wrapper">
						<input id="color<%=objcolor.getId() %>" type="checkbox" value=<%=objcolor.getId()%> class="checkbox-input color" />
						<span class="checkbox-tile">
							<span class="checkbox-label"><%=objcolor.getName() %></span>
						</span>
					</label>
                	<script>
                		$('#color<%=objcolor.getId()%>').on('click', function(){
                			$(this).attr('checked');
                			$('#color<%=objcolor.getId() %>').removeAttr('checked');
                			if(this.checked){
                				var color_id = $(this).val();
                    			var menu_id = <%=menu.getId()%>;
                    			$.ajax({	
                   					url: '<%=request.getContextPath()%>/public/filter-product',
                   					type: 'POST',
                   					cache: false,
                   					data: {
                   						acolor_id: color_id,
                   						amenu_id: menu_id,
                   					},
                   					success: function(data){
                   						$('.ajax-show-filter-product').html(data);
	               						$('#indexs<%=index1%>').addClass('active')
                   					},
                   					error: function (){
                   						alert('Có lỗi xảy ra');
                   					},
                   			  });	
                			}else{
                				var color_id = $(this).val();
                				var menu_id = <%=menu.getId()%>;
                				$.ajax({	
                   					url: '<%=request.getContextPath()%>/public/filter-product',
                   					type: 'POST',
                   					cache: false,
                   					data: {
                   						ucid: color_id,
                   						amenu_id: menu_id,
                   					},
                   					success: function(data){
                   						$('.ajax-show-filter-product').html(data);
	               						$('#indexs<%=index1%>').addClass('active');
                   					},
                   					error: function (){
                   						alert('Có lỗi xảy ra');
                   					},
                   			  });	
                			}
                		});
                	</script>
                </li>
                <%}} %>
              	</ul>
             </div>
		 </fieldset>
          </div>
          <div class="left-special left-sidebar-widget mb_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title">Khoảng Giá(VNĐ)</h2>
            </div>
           		<ul class="nav  main-navigation collapse in">
                	<li><a>Coming Soon</a></li>
              </ul>
          </div>
        </div>
        
        <div class="col-sm-8 col-lg-9 mt_50 ajax-show-filter-product">
            <div class="row row1">
                  <%
	                	if(listPro1.size()>0){
      	                	for(ProductAdmin objPr1: listPro1){
      	                		images = objPr1.getImages();
      	                		color = objPr1.getColor();
      	                		
                      %>
                            <div class="col-sm-3 col-lg-3 mb_20">
                              <div class="image product-imageblock ">
      							<a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>">
                              		<%
                              		if(images!=null){
                              			String[] image_list = images.split(",");
                        					String applicationPath = request.getServletContext().getRealPath("");
                        					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                        					File image = new File(path+image_list[0]);
                        					if(image.exists()){
                              		%>
                              		<img id="img-des<%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[0] %>" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                              		<%}else{
                              			%>
                                  		<img id="img-des<%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		}}else{
                              			%>
                                  		<img data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		} %>
                              		 </a>
                                
                              </div>
                              <div class="caption product-detail text-center">
                                <h6 data-name="product_name" class="product-name"><a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>" title="<%=objPr1.getName() %>"><%=objPr1.getName() %></a></h6>
                                <span class="price"><span class="amount"><span class="currencySymbol"><%=(int)objPr1.getPrice()%></span>đ</span>
                                </span>
                              </div>
                              <div class="row">
                                 <%
                                 if(images!=null&&color!=null){
                                	 String[] oimage_list = images.split(",");
                          			String[] image_list1 = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
                          			String[] ocolor_list = color.split(",");
                          			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
                   					String applicationPath = request.getServletContext().getRealPath("");
                   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                   						for(int j=0; j<image_list1.length; j++){
                   					File image = new File(path+image_list1[j]);
                   					if(image.exists()){
                              		
                              %>
                                 		<div class="col-lg-3" style="padding-right: 0px; width: 40px;">
                                 			<img id="img-icon<%=objPr1.getProductSKU() %><%=j %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list1[j] %>" class="img-fluid rounded avatar-50 mr-3 img-icon" style="width: 30px; height: 30px; border-radius: 30px;" alt="image" title="<%=color_list[j]%>">
                                 		</div>
                                 		<script type="text/javascript">
									    	$( "#img-icon<%=objPr1.getProductSKU() %><%=j %>" ).click(function() {
									    		var src_icon = $("#img-icon<%=objPr1.getProductSKU() %><%=j %>").attr("src");
									    		var src_des = $("#img-des<%=objPr1.getProductSKU() %>").attr("src");
										    	var path = src_icon.substring(0,src_icon.lastIndexOf('/'));	
										    	var path1 = src_des.substring(0,src_des.lastIndexOf('/'));	
										    	var filename = src_icon.split("/").pop();
										    	var filename1 = src_des.split("/").pop();
										    	var newSrc = path1+"/"+filename;
										    	$("#img-des<%=objPr1.getProductSKU() %>").attr('src',newSrc);  
									    	});
						               </script>
                                 		<%}}} %>
                                </div>
                            </div>
                        <%}} %>
                       </div>
                       <div class="col-sm-12 col-lg-12 mt_50">
                    	<div class="row">
                            	<%
                            		int index = 0;
    								if(request.getAttribute("index")!=null){
    									 index =(Integer)request.getAttribute("index");
    								}
    									int dfNum = DefineLb.NUMBER_PER_PAGE;
                            			PublicDAO objPro = new PublicDAO();
										int numPage = (Integer)request.getAttribute("numPage");
										int number_page = DefineLb2.NUMBER_PAGE;
										int numcur = number_page + index -1;
										int totalPro = 0;
										if(request.getAttribute("total")!=null){
											totalPro = (Integer)request.getAttribute("total");
										}
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
                                        	<li class="page-item previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a class="page-link" href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=id %>&index=1">Về trang 1</a></li>
                                            <li class="page-item previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a class="page-link" href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=id %>&index=<%=index-1%>">Trang trước</a></li>
                                            <%} %>
                                             
                                            <% 
                                            	if(number_page<numPage ){
                                            		if(numcur<=numPage){                                            			
                                            			for(int i=index; i<=numcur; i++){%>
                                                        <li class="page-item <%if(i==index){%><%=atc %><%} %> " aria-controls="dataTables-example" tabindex="0"><a class="page-link" href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=id %>&index=<%=i%>"><%=i %></a></li>
                                                        <%}
                                            		}else{
                                            			
                                            			for(int i=index-2; i<=numPage; i++){
                                            			%>
                                            			
                                                        <li class="page-item <%if(i==index){%><%=atc %><%} %> " aria-controls="dataTables-example" tabindex="0"><a class="page-link" href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=id %>&index=<%=i%>"><%=i %></a></li>
                                                        <%                                                       
                                            			}
                                            		}
                                            		}else{
                                            			if(number_page>=numPage){
                                            				for(int i=1; i<=numPage; i++){%>                                 	
                                                   				 <li class="page-item <%if(i==index){%><%=atc %><%} %>" aria-controls="dataTables-example" tabindex="0"><a class="page-link" href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=id %>&index=<%=i%>"><%=i %></a></li>                                                    
                                                    <%}}}%>
                                            <%
                                            	if(index>=1&&index<numPage){
                                            %>
                                            <li class="page-item next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a class="page-link" href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=id %>&index=<%=index+1%>">Trang tiếp</a></li>
                                            <li class="page-item next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a class="page-link" href="<%=request.getContextPath()%>/public/product-by-cat?id=<%=id %>&index=<%=numPage%>">Đến trang cuối</a></li>
                                            <%} %>
                                        </ul>
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
          <%
    		List<Integer> list_size = new ArrayList<>();
    		List<Integer> list_color = new ArrayList<>();
    		for(size objS: listSize){
    			list_size.add(objS.getId());
    		};
    		for(colur objC: listColor){
    			list_color.add(objC.getId());
    		};
    		String dlist_size = list_size.toString();
    		String dlist_color = list_color.toString();
    	%>
    	
    <!-- =====  CONTAINER END  ===== -->
    <%@ include file="/templates/public/inc/footer.jsp" %>
  <a id="scrollup"></a>
  <script>
    	var dcolor_id = <%=dlist_color%>;
		var dsize_id=<%=dlist_size%>;
    	$( window ).on( "load", function() {
					var arrSize = $("input:checkbox.size:checked").map(function(){return $(this).val()}).get();
					var size_list=arrSize.toString();
					var arrColor = $("input:checkbox.color:checked").map(function(){return $(this).val()}).get();
					var color_list=arrColor.toString();
					var menu_id=<%=menu.getId()%>;
					$.ajax({
						url: '<%=request.getContextPath()%>/public/filter-product',
						type: 'POST',
						cache: false,
					    data: {
					       dsize_id: size_list,
					       dcolor_id: color_list,
					       amenu_id: menu_id,
					    },
					    success: function(data){
				            $('.ajax-show-filter-product').html(data);
							$('#indexs<%=index%>').addClass('active');
				        },
                        error: function (){
                        	alert('Có lỗi xảy ra');
                       	},
                   });
    		});
    	</script>
   </div>
</body>

</html>