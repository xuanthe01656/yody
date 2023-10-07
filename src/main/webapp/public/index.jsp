<%@page import="library.RemoveDuplicateElements"%>
<%@page import="model.bean.colur"%>
<%@page import="java.io.File"%>
<%@page import="model.bean.ProductAdmin"%>
<%@page import="model.bean.DisplayPublic"%>
<%@page import="model.dao.PublicDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>

    <!-- =====  BANNER STRAT  ===== -->
    <div class="container banner mt_20">
      <div class="main-banner owl-carousel">
        <div class="item"><a href="#"><img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgbanner/slider_1.jpg" alt="Main Banner" class="img-responsive" /></a></div>
        <div class="item"><a href="#"><img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgbanner/slider_3.jpg" alt="Main Banner" class="img-responsive" /></a></div>
      	<div class="item"><a href="<%=request.getContextPath() %>/public/show-more-product?id=7"><img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgbanner/slider_4.jpg" alt="Main Banner" class="img-responsive" /></a></div>
      </div>
    </div>
    <!-- =====  BANNER END  ===== -->
    <!-- =====  SUB BANNER  STRAT ===== -->
          <div class="row">
            <div class="cms_banner">
              <div class="col-sm-12 mt_10">
                <div id="subbanner3">
                  <div class="sub-img"> <a href="#"><img src="<%=request.getContextPath() %>/templates/public/assets/images/sub6.jpg" alt="Sub Banner3" class="img-responsive"></a></div>
                </div>
              </div>
            </div>
          </div>
          <!-- =====  SUB BANNER END  ===== -->
    <!-- =====  CONTAINER START  ===== -->
    <div class="container">
      <!-- =====  SUB BANNER  STRAT ===== -->
      <!-- =====  SUB BANNER END  ===== -->
      <div class="row ">
      <%
      	RemoveDuplicateElements remove = new RemoveDuplicateElements();
      	if(request.getAttribute("listPl")!=null){
      		ArrayList<DisplayPublic> listPl = (ArrayList<DisplayPublic>)request.getAttribute("listPl");
      %>
        <div class="col-sm-12 mtb_10">
       <%
       	if(listPl.size()>0){
       %>
          <!-- =====  PRODUCT TAB  ===== -->
         	<div id="product-tab" class="mt_50" style="display: none;">
            <div class="heading-part mb_10 ">
              <h2 class="main_title"><%=listPl.get(0).getName() %></h2>
              <div class="nav text-right">
	              <a href="<%=request.getContextPath() %>/public/show-more-product?id=1">Xem Thêm</a>	          
           	  </div>
            </div>
            <div class="clearfix box">
            	<a class="banner" href="<%=request.getContextPath() %>/public/show-more-product?id=1">
					<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgpublic/<%=listPl.get(0).getImages() %>" width="191" height="419" alt="icon flash">
				</a>
				<div class="tab-content">  		 
              <div class="tab-pane active" id="nArrivals">
                <div class="nArrivals owl-carousel">
                <%
                	if(request.getAttribute("listFS")!=null){
	                	ArrayList<ProductAdmin> listPro1 = (ArrayList<ProductAdmin>)request.getAttribute("listFS");
	                	String images = "";
	                	String color = "";
	                	if(listPro1.size()>0){
                		for(ProductAdmin objPr1: listPro1){
              			images = objPr1.getImages();
              			color  = objPr1.getColor();
          %>
            <div class="product-grid mtb_10 mtb_10">
              <div class="item">
                <div class="mb_30">
                  <div class="image product-imageblock"> 
                  	<a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>"> 
                  		<%
                  		if(images!=null){
                  			String[] image_list = images.split(",");
            					String applicationPath = request.getServletContext().getRealPath("");
            					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
            					File image = new File(path+image_list[0]);
            					if(image.exists()){
                  		%>
                  		<img id="img-des<%=listPl.get(0).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[0] %>" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                  		<%}else{
                  			%>
                      		<img id="img-des<%=listPl.get(0).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                      		<%
                  		}}else{
                  			%>
                      		<img data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                      		<%
                  		} %>
                  	</a>
                  </div>
                  <div class="caption product-detail text-center">
                    <h6 data-name="product_name" class="product-name"><a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>" title="Casual Shirt With Ruffle Hem"><%=objPr1.getName() %></a></h6>
                    <span class="price"><span class="amount"><span class="currencySymbol"><%=(int)objPr1.getPrice() %></span>đ</span>
                    </span>
                  </div>
                  
                     <div class="row">
                     <%
                     if(images!=null&&color!=null){
                    	 String[] oimage_list = images.split(",");
              			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
              			String[] ocolor_list = color.split(",");
              			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
       					String applicationPath = request.getServletContext().getRealPath("");
       					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
       						for(int j=0; j<image_list.length; j++){
       					File image = new File(path+image_list[j]);
       					if(image.exists()){
       						
                  %>
                     		<div class="col-lg-3" style="padding-right: 0px; width: 40px;">
                     			<img id="img-icon<%=listPl.get(0).getId()%><%=objPr1.getProductSKU() %><%=j %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[j] %>" class="img-fluid rounded avatar-50 mr-3 img-icon" style="width: 30px; height: 30px; border-radius: 30px;" alt="image" title="<%=color_list[j]%>">
                     		</div>
                     		<script type="text/javascript">
									    	$( "#img-icon<%=listPl.get(0).getId()%><%=objPr1.getProductSKU() %><%=j %>" ).click(function() {
									    		var src_icon = $("#img-icon<%=listPl.get(0).getId()%><%=objPr1.getProductSKU() %><%=j %>").attr("src");
									    		var src_des = $("#img-des<%=listPl.get(0).getId()%><%=objPr1.getProductSKU() %>").attr("src");
										    	var path = src_icon.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename = src_icon.split("/").pop();
										    	var path1 = src_des.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename1 = src_des.split("/").pop();
										    	var newSrc = path1+"/"+filename;
										    	$("#img-des<%=listPl.get(0).getId()%><%=objPr1.getProductSKU() %>").attr('src',newSrc);  
									    	});
									    	
                    			</script>
                     		<%}}} %>
                    </div>
                </div>
              </div>
            
            </div>
            <%}}}} %>
                </div>
              </div>
            </div>
            </div>
          </div>
          <!-- =====  PRODUCT TAB  END ===== -->
          <!-- =====  PRODUCT TAB  ===== -->
         <div id="product-tab" class="mt_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title"><%=listPl.get(1).getName() %></h2>
              <div class="nav text-right">
	              <a href="<%=request.getContextPath() %>/public/show-more-product?id=2">Xem Thêm</a>	          
           	  </div>
            </div>
            <div class="clearfix box">
            	<a class="banner" href="<%=request.getContextPath() %>/public/show-more-product?id=2">
					<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgpublic/<%=listPl.get(1).getImages() %>" width="191" height="419" alt="icon flash">
				</a>
				<div class="tab-content">  		 
              <div class="tab-pane active" id="nArrivals">
                <div class="nArrivals owl-carousel">
                 <%
                	if(request.getAttribute("listProNew")!=null){
	                	ArrayList<ProductAdmin> listPro1 = (ArrayList<ProductAdmin>)request.getAttribute("listProNew");
	                	String images = "";
	                	String color = "";
	                	if(listPro1.size()>0){
	                		for(ProductAdmin objPr1: listPro1){
      	                		images = objPr1.getImages();
                      			color = objPr1.getColor();
                      %>
                        <div class="product-grid mtb_10">
                          <div class="item">
                            <div class="mb_30">
                              <div class="image product-imageblock">
      							<a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>">
                              		<%
                              		if(images!=null){
                              			String[] image_list = images.split(",");
                        					String applicationPath = request.getServletContext().getRealPath("");
                        					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                        					File image = new File(path+image_list[0]);
                        					if(image.exists()){
                              		%>
                              		<img id="img-des<%=listPl.get(1).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[0] %>" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                              		<%}else{
                              			%>
                                  		<img id="img-des<%=listPl.get(1).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		}}else{
                              			%>
                                  		<img data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		} %>
                              		 </a>
                                
                              </div>
                              <div class="caption product-detail text-center">
                                <h6 data-name="product_name" class="product-name"><a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>" title="Casual Shirt With Ruffle Hem"><%=objPr1.getName() %></a></h6>
                                <span class="price"><span class="amount"><span class="currencySymbol"><%=(int)objPr1.getPrice() %></span>đ</span>
                                </span>
                              </div>
                              <div class="row">
                                 <%
                                 if(images!=null&&color!=null){
                                	 String[] oimage_list = images.split(",");
                          			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
                          			String[] ocolor_list = color.split(",");
                          			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
                   					String applicationPath = request.getServletContext().getRealPath("");
                   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                   						for(int j=0; j<image_list.length; j++){
                   					File image = new File(path+image_list[j]);
                   					if(image.exists()){
                              		
                              %>
                                 		<div class="col-lg-3" style="padding-right: 0px; width: 40px;">
                                 			<img id="img-icon<%=listPl.get(1).getId()%><%=objPr1.getProductSKU() %><%=j %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[j] %>" class="img-fluid rounded avatar-50 mr-3 img-icon" style="width: 30px; height: 30px; border-radius: 30px;" alt="image" title="<%=color_list[j]%>">
                                 		</div>
                                 		<script type="text/javascript">
									    	$( "#img-icon<%=listPl.get(1).getId()%><%=objPr1.getProductSKU() %><%=j %>" ).click(function() {
									    		var src_icon = $("#img-icon<%=listPl.get(1).getId()%><%=objPr1.getProductSKU() %><%=j %>").attr("src");
									    		var src_des = $("#img-des<%=listPl.get(1).getId()%><%=objPr1.getProductSKU() %>").attr("src");
										    	var path = src_icon.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename = src_icon.split("/").pop();
										    	var path1 = src_des.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename1 = src_des.split("/").pop();
										    	var newSrc = path1+"/"+filename;
										    	$("#img-des<%=listPl.get(1).getId()%><%=objPr1.getProductSKU() %>").attr('src',newSrc);  
									    	});
									    	
                    					</script>
                                 		<%}}} %>
                                </div>
                            </div>
                          </div>
                        </div>
                        <%}}} %>
                </div>
              </div>
            </div>
            </div>
          </div>
          
          <div id="product-tab" class="mt_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title"><%=listPl.get(2).getName() %></h2>
              <div class="nav text-right">
	              <a href="<%=request.getContextPath() %>/public/show-more-product?id=3">Xem Thêm</a>	          
           	  </div>
            </div>
            <div class="clearfix box">
            	<a class="banner" href="<%=request.getContextPath() %>/public/show-more-product?id=3">
					<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgpublic/<%=listPl.get(2).getImages() %>" width="191" height="419" alt="icon flash">
				</a>
				<div class="tab-content">  		 
              <div class="tab-pane active" id="nArrivals">
                <div class="nArrivals owl-carousel">
                
                  <%
                	if(request.getAttribute("listProNew")!=null){
	                	ArrayList<ProductAdmin> listPro1 = (ArrayList<ProductAdmin>)request.getAttribute("listProNew");
	                	String images = "";
	                	String color = "";
	                	if(listPro1.size()>0){
      	                	for(ProductAdmin objPr1: listPro1){
      	                		images = objPr1.getImages();
      	                		color = objPr1.getColor();
                      %>
                        <div class="product-grid mtb_10">
                          <div class="item">
                            <div class="mb_30">
                              <div class="image product-imageblock">
      							<a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>">
                              		<%
                              		if(images!=null){
                              			String[] image_list = images.split(",");
                        					String applicationPath = request.getServletContext().getRealPath("");
                        					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                        					File image = new File(path+image_list[0]);
                        					if(image.exists()){
                              		%>
                              		<img id="img-des<%=listPl.get(2).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[0] %>" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                              		<%}else{
                              			%>
                                  		<img id="img-des<%=listPl.get(2).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		}}else{
                              			%>
                                  		<img data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		} %>
                              		 </a>
                                
                              </div>
                              <div class="caption product-detail text-center">
                                 <h6 data-name="product_name" class="product-name"><a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>" title="Casual Shirt With Ruffle Hem"><%=objPr1.getName() %></a></h6>
                                <span class="price"><span class="amount"><span class="currencySymbol"><%=(int)objPr1.getPrice() %></span>đ</span>
                                </span>
                              </div>
                              <div class="row">
                                 <%
                                 if(images!=null&&color!=null){
                                	 String[] oimage_list = images.split(",");
                          			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
                          			String[] ocolor_list = color.split(",");
                          			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
                   					String applicationPath = request.getServletContext().getRealPath("");
                   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                   						for(int j=0; j<image_list.length; j++){
                   					File image = new File(path+image_list[j]);
                   					if(image.exists()){
                              		
                              %>
                                 		<div class="col-lg-3" style="padding-right: 0px; width: 40px;">
                                 			<img id="img-icon<%=listPl.get(2).getId()%><%=objPr1.getProductSKU() %><%=j %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[j] %>" class="img-fluid rounded avatar-50 mr-3 img-icon" style="width: 30px; height: 30px; border-radius: 30px;" alt="image" title="<%=color_list[j]%>">
                                 		</div>
                                 		<script type="text/javascript">
									    	$( "#img-icon<%=listPl.get(2).getId()%><%=objPr1.getProductSKU() %><%=j %>" ).click(function() {
									    		var src_icon = $("#img-icon<%=listPl.get(2).getId()%><%=objPr1.getProductSKU() %><%=j %>").attr("src");
									    		var src_des = $("#img-des<%=listPl.get(2).getId()%><%=objPr1.getProductSKU() %>").attr("src");
										    	var path = src_icon.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename = src_icon.split("/").pop();
										    	var path1 = src_des.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename1 = src_des.split("/").pop();
										    	var newSrc = path1+"/"+filename;
										    	$("#img-des<%=listPl.get(2).getId()%><%=objPr1.getProductSKU() %>").attr('src',newSrc);  
									    	});
									    	
                    					</script>
                                 		<%}}} %>
                                </div>
                            </div>
                          </div>
                          
                        </div>
                        <%}}} %>
                </div>
              </div>
            </div>
            </div>
          </div>
          <div id="product-tab" class="mt_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title"><%=listPl.get(3).getName() %></h2>
             <div class="nav text-right">
	              <a href="<%=request.getContextPath() %>/public/show-more-product?id=4">Xem Thêm</a>	          
           	  </div>
            </div>
            <div class="clearfix box">
            	<a class="banner" href="<%=request.getContextPath() %>/public/show-more-product?id=4">
					<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgpublic/<%=listPl.get(3).getImages() %>" width="191" height="419" alt="icon flash">
				</a>
				<div class="tab-content">  		 
              <div class="tab-pane active" id="nArrivals">
                <div class="nArrivals owl-carousel">
                  	 <%
                	if(request.getAttribute("listPoloShirt")!=null){
	                	ArrayList<ProductAdmin> listPro1 = (ArrayList<ProductAdmin>)request.getAttribute("listPoloShirt");
	                	String images = "";
	                	String color = "";
	                	if(listPro1.size()>0){
      	                	for(ProductAdmin objPr1: listPro1){
      	                		images = objPr1.getImages();
      	                		color = objPr1.getColor();
                      %>
                        <div class="product-grid mtb_10">
                          <div class="item">
                            <div class="mb_30">
                              <div class="image product-imageblock">
      							<a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>">
                              		<%
                              		if(images!=null){
                              			String[] image_list = images.split(",");
                        					String applicationPath = request.getServletContext().getRealPath("");
                        					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                        					File image = new File(path+image_list[0]);
                        					if(image.exists()){
                              		%>
                              		<img id="img-des<%=listPl.get(3).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[0] %>" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                              		<%}else{
                              			%>
                                  		<img id="img-des<%=listPl.get(3).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		}}else{
                              			%>
                                  		<img  data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="" title="" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		} %>
                              		 </a>
                                
                              </div>
                              <div class="caption product-detail text-center">
                                <h6 data-name="product_name" class="product-name"><a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>" title="Casual Shirt With Ruffle Hem"><%=objPr1.getName() %></a></h6>
                                <span class="price"><span class="amount"><span class="currencySymbol"><%=(int)objPr1.getPrice() %></span>đ</span>
                                </span>
                              </div>
                              <div class="row">
                                 <%
                                 if(images!=null&&color!=null){
                                	 String[] oimage_list = images.split(",");
                          			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
                          			String[] ocolor_list = color.split(",");
                          			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
                         			int index = 0;
                   					String applicationPath = request.getServletContext().getRealPath("");
                   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                   						for(int j=0; j<image_list.length; j++){
                   							index=j;
                   					File image = new File(path+image_list[j]);
                   					if(image.exists()){
                              		
                              %>
                                 		<div class="col-lg-3" style="padding-right: 0px; width: 40px;">
                                 			<img id="img-icon<%=listPl.get(3).getId()%><%=objPr1.getProductSKU() %><%=j %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[j] %>" class="img-fluid rounded avatar-50 mr-3 img-icon" style="width: 30px; height: 30px; border-radius: 30px;" alt="image" title="<%=color_list[j]%>">
                                 		</div>
                                 		 <script type="text/javascript">
									    	$( "#img-icon<%=listPl.get(3).getId()%><%=objPr1.getProductSKU() %><%=index %>" ).click(function() {
									    		var src_icon = $("#img-icon<%=listPl.get(3).getId()%><%=objPr1.getProductSKU() %><%=index %>").attr("src");
									    		var src_des = $("#img-des<%=listPl.get(3).getId()%><%=objPr1.getProductSKU() %>").attr("src");
										    	var path = src_icon.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename = src_icon.split("/").pop();
										    	var path1 = src_des.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename1 = src_des.split("/").pop();
										    	var newSrc = path1+"/"+filename;
										    	$("#img-des<%=listPl.get(3).getId()%><%=objPr1.getProductSKU() %>").attr('src',newSrc);  
										    	
									    	});
									    	
						                 </script>
                                 		<%}}} %>
                                </div>
                            </div>
                          </div>
                         
                        </div>
                        <%}}} %>
                </div>        
              </div>
            </div>
            </div>
          </div>
          <div id="product-tab" class="mt_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title"><%=listPl.get(4).getName() %></h2>
              <div class="nav text-right">
	              <a href="<%=request.getContextPath() %>/public/show-more-product?id=5">Xem Thêm</a>	          
           	  </div>
            </div>
            <div class="clearfix box">
            	<a class="banner" href="<%=request.getContextPath() %>/public/show-more-product?id=5">
					<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgpublic/<%=listPl.get(4).getImages() %>" width="191" height="419" alt="icon flash">
				</a>
				<div class="tab-content">  		 
              <div class="tab-pane active" id="nArrivals">
                <div class="nArrivals owl-carousel">
                
                  <%
                	if(request.getAttribute("listJean")!=null){
	                	ArrayList<ProductAdmin> listPro1 = (ArrayList<ProductAdmin>)request.getAttribute("listJean");
	                	String images = "";
	                	String color = "";
	                	if(listPro1.size()>0){
      	                	for(ProductAdmin objPr1: listPro1){
      	                		images = objPr1.getImages();
      	                		color = objPr1.getColor();
                      %>
                        <div class="product-grid mtb_10">
                          <div class="item">
                            <div class="mb_30">
                              <div class="image product-imageblock">
      							<a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>">
                              		<%
                              		if(images!=null){
                              			String[] image_list = images.split(",");
                        					String applicationPath = request.getServletContext().getRealPath("");
                        					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                        					File image = new File(path+image_list[0]);
                        					if(image.exists()){
                              		%>
                              		<img id="img-des<%=listPl.get(4).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[0] %>" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                              		<%}else{
                              			%>
                                  		<img id="img-des<%=listPl.get(4).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		}}else{
                              			%>
                                  		<img data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		} %>
                              		 </a>
                                
                              </div>
                              <div class="caption product-detail text-center">
                                 <h6 data-name="product_name" class="product-name"><a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>" title="Casual Shirt With Ruffle Hem"><%=objPr1.getName() %></a></h6>
                                <span class="price"><span class="amount"><span class="currencySymbol"><%=(int)objPr1.getPrice() %></span>đ</span>
                                </span>
                              </div>
                              <div class="row">
                                 <%
                                 if(images!=null&&color!=null){
                                	 String[] oimage_list = images.split(",");
                          			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
                          			String[] ocolor_list = color.split(",");
                          			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
                   					String applicationPath = request.getServletContext().getRealPath("");
                   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                   						for(int j=0; j<image_list.length; j++){
                   					File image = new File(path+image_list[j]);
                   					if(image.exists()){
                              		
                              %>
                                 		<div class="col-lg-3" style="padding-right: 0px; width: 40px;">
                                 			<img id="img-icon<%=listPl.get(4).getId()%><%=objPr1.getProductSKU() %><%=j %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[j] %>" class="img-fluid rounded avatar-50 mr-3 img-icon" style="width: 30px; height: 30px; border-radius: 30px;" alt="image" title="<%=color_list[j]%>">
                                 		</div>
                                 		<script type="text/javascript">
									    	$( "#img-icon<%=listPl.get(4).getId()%><%=objPr1.getProductSKU() %><%=j %>" ).click(function() {
									    		var src_icon = $("#img-icon<%=listPl.get(4).getId()%><%=objPr1.getProductSKU() %><%=j %>").attr("src");
									    		var src_des = $("#img-des<%=listPl.get(4).getId()%><%=objPr1.getProductSKU() %>").attr("src");
										    	var path = src_icon.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename = src_icon.split("/").pop();
										    	var path1 = src_des.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename1 = src_des.split("/").pop();
										    	var newSrc = path1+"/"+filename;
										    	$("#img-des<%=listPl.get(4).getId()%><%=objPr1.getProductSKU() %>").attr('src',newSrc);  
									    	});
                    					</script>
                                 		<%}}} %>
                                </div>
                            </div>
                          </div>
                        </div>
                        <%}}} %>
                  </div>
                </div>
              </div>
            </div>
            </div>
          <div id="product-tab" class="mt_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title"><%=listPl.get(5).getName() %></h2>
              <div class="nav text-right">
	              <a href="<%=request.getContextPath() %>/public/show-more-product?id=6" >Xem Thêm</a>	          
           	  </div>
            </div>
            <div class="clearfix box">
            	<a class="banner" href="<%=request.getContextPath() %>/public/show-more-product?id=6">
					<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgpublic/<%=listPl.get(5).getImages() %>" width="191" height="419" alt="icon flash">
				</a>
				<div class="tab-content">  		 
              <div class="tab-pane active" id="nArrivals">
                <div class="nArrivals owl-carousel">
                
                  <%
                	if(request.getAttribute("listOffice")!=null){
	                	ArrayList<ProductAdmin> listPro1 = (ArrayList<ProductAdmin>)request.getAttribute("listOffice");
	                	String images = "";
	                	String color = "";
	                	if(listPro1.size()>0){
      	                	for(ProductAdmin objPr1: listPro1){
      	                		images = objPr1.getImages();
      	                		color = objPr1.getColor();
                      %>
                        <div class="product-grid mtb_10">
                          <div class="item">
                            <div class="mb_30">
                              <div class="image product-imageblock">
      							<a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>">
                              		<%
                              		if(images!=null){
                              			String[] image_list = images.split(",");
                        					String applicationPath = request.getServletContext().getRealPath("");
                        					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                        					File image = new File(path+image_list[0]);
                        					if(image.exists()){
                              		%>
                              		<img id="img-des<%=listPl.get(5).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[0] %>" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                              		<%}else{
                              			%>
                                  		<img id="img-des<%=listPl.get(5).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		}}else{
                              			%>
                                  		<img data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		} %>
                              		 </a>
                                
                              </div>
                              <div class="caption product-detail text-center">
                                <h6 data-name="product_name" class="product-name"><a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>" title="Casual Shirt With Ruffle Hem"><%=objPr1.getName() %></a></h6>
                                <span class="price"><span class="amount"><span class="currencySymbol"><%=(int)objPr1.getPrice() %></span>đ</span>
                                </span>
                              </div>
                              <div class="row">
                                 <%
                                 if(images!=null&&color!=null){
                                	 String[] oimage_list = images.split(",");
                          			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
                          			String[] ocolor_list = color.split(",");
                          			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
                   					String applicationPath = request.getServletContext().getRealPath("");
                   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                   						for(int j=0; j<image_list.length; j++){
                   					File image = new File(path+image_list[j]);
                   					if(image.exists()){
                              		
                              %>
                                 		<div class="col-lg-3" style="padding-right: 0px; width: 40px;">
                                 			<img id="img-icon<%=listPl.get(5).getId()%><%=objPr1.getProductSKU() %><%=j %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[j] %>" class="img-fluid rounded avatar-50 mr-3 img-icon" style="width: 30px; height: 30px; border-radius: 30px;" alt="image" title="<%=color_list[j]%>">
                                 		</div>
                                 		<script type="text/javascript">
									    	$( "#img-icon<%=listPl.get(5).getId()%><%=objPr1.getProductSKU() %><%=j %>" ).click(function() {
									    		var src_icon = $("#img-icon<%=listPl.get(5).getId()%><%=objPr1.getProductSKU() %><%=j %>").attr("src");
									    		var src_des = $("#img-des<%=listPl.get(5).getId()%><%=objPr1.getProductSKU() %>").attr("src");
										    	var path = src_icon.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename = src_icon.split("/").pop();
										    	var path1 = src_des.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename1 = src_des.split("/").pop();
										    	var newSrc = path1+"/"+filename;
										    	$("#img-des<%=listPl.get(5).getId()%><%=objPr1.getProductSKU() %>").attr('src',newSrc);  
									    	});
                    					</script>
                                 		<%}}} %>
                                </div>
                            </div>
                          </div>
                        </div>
                        <%}}} %>
                  </div>
                </div>
              </div>
            </div>
            </div>
          <div id="product-tab" class="mt_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title"><%=listPl.get(6).getName() %></h2>
              <div class="nav text-right">
	              <a href="<%=request.getContextPath() %>/public/show-more-product?id=7">Xem Thêm</a>	          
           	  </div>
            </div>
            <div class="clearfix box">
            	<a class="banner" href="<%=request.getContextPath() %>/public/show-more-product?id=7">
					<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgpublic/<%=listPl.get(6).getImages() %>" width="191" height="419" alt="icon flash">
				</a>
				<div class="tab-content">  		 
              <div class="tab-pane active" id="nArrivals">
                <div class="nArrivals owl-carousel">
                
                  <%
                	if(request.getAttribute("listSport")!=null){
	                	ArrayList<ProductAdmin> listPro1 = (ArrayList<ProductAdmin>)request.getAttribute("listSport");
	                	String images = "";
	                	String color = "";
	                	if(listPro1.size()>0){
      	                	for(ProductAdmin objPr1: listPro1){
      	                		images = objPr1.getImages();
      	                		color = objPr1.getColor();
                      %>
                        <div class="product-grid mtb_10">
                          <div class="item">
                            <div class="mb_30">
                              <div class="image product-imageblock">
      							<a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>">
                              		<%
                              		if(images!=null){
                              			String[] image_list = images.split(",");
                        					String applicationPath = request.getServletContext().getRealPath("");
                        					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                        					File image = new File(path+image_list[0]);
                        					if(image.exists()){
                              		%>
                              		<img id="img-des<%=listPl.get(6).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[0] %>" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                              		<%}else{
                              			%>
                                  		<img id="img-des<%=listPl.get(6).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		}}else{
                              			%>
                                  		<img data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		} %>
                              		 </a>
                                
                              </div>
                              <div class="caption product-detail text-center">
                                 <h6 data-name="product_name" class="product-name"><a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>" title="Casual Shirt With Ruffle Hem"><%=objPr1.getName() %></a></h6>
                                <span class="price"><span class="amount"><span class="currencySymbol"><%=(int)objPr1.getPrice() %></span>đ</span>
                                </span>
                              </div>
                              <div class="row">
                                 <%
                                 if(images!=null&&color!=null){
                                	 String[] oimage_list = images.split(",");
                          			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
                          			String[] ocolor_list = color.split(",");
                          			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
                   					String applicationPath = request.getServletContext().getRealPath("");
                   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                   						for(int j=0; j<image_list.length; j++){
                   					File image = new File(path+image_list[j]);
                   					if(image.exists()){
                              		
                              %>
                                 		<div class="col-lg-3" style="padding-right: 0px; width: 40px;">
                                 			<img id="img-icon<%=listPl.get(6).getId()%><%=objPr1.getProductSKU() %><%=j %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[j] %>" class="img-fluid rounded avatar-50 mr-3 img-icon" style="width: 30px; height: 30px; border-radius: 30px;" alt="image" title="<%=color_list[j]%>">
                                 		</div>
                                 		<script type="text/javascript">
									    	$( "#img-icon<%=listPl.get(6).getId()%><%=objPr1.getProductSKU() %><%=j %>" ).click(function() {
									    		var src_icon = $("#img-icon<%=listPl.get(6).getId()%><%=objPr1.getProductSKU() %><%=j %>").attr("src");
									    		var src_des = $("#img-des<%=listPl.get(6).getId()%><%=objPr1.getProductSKU() %>").attr("src");
										    	var path = src_icon.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename = src_icon.split("/").pop();
										    	var path1 = src_des.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename1 = src_des.split("/").pop();
										    	var newSrc = path1+"/"+filename;
										    	$("#img-des<%=listPl.get(6).getId()%><%=objPr1.getProductSKU() %>").attr('src',newSrc);  
									    	});
                    					</script>
                                 		<%}}} %>
                                </div>
                            </div>
                          </div>
                        </div>
                        <%}}} %>
                  </div>
                </div>
              </div>
            </div>
            </div>
          <div id="product-tab" class="mt_50">
            <div class="heading-part mb_10 ">
              <h2 class="main_title"><%=listPl.get(7).getName() %></h2>
              <div class="nav text-right">
	              <a href="<%=request.getContextPath() %>/public/show-more-product?id=8" >Xem Thêm</a>	          
           	  </div>
            </div>
            <div class="clearfix box">
            	<a class="banner" href="<%=request.getContextPath() %>/public/show-more-product?id=8">
					<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/imgpublic/<%=listPl.get(7).getImages() %>" width="191" height="419" alt="icon flash">
				</a>
				<div class="tab-content">  		 
              <div class="tab-pane active" id="nArrivals">
                <div class="nArrivals owl-carousel">
                
                  <%
                	if(request.getAttribute("listAccessories")!=null){
	                	ArrayList<ProductAdmin> listPro1 = (ArrayList<ProductAdmin>)request.getAttribute("listAccessories");
	                	String images = "";
	                	String color="";
	                	if(listPro1.size()>0){
      	                	for(ProductAdmin objPr1: listPro1){
      	                		images = objPr1.getImages();
      	                		color = objPr1.getColor();
                      %>
                        <div class="product-grid mtb_10">
                          <div class="item">
                            <div class="mb_30">
                              <div class="image product-imageblock">
      							<a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>">
                              		<%
                              		if(images!=null){
                              			String[] image_list = images.split(",");
                        					String applicationPath = request.getServletContext().getRealPath("");
                        					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                        					File image = new File(path+image_list[0]);
                        					if(image.exists()){
                              		%>
                              		<img id="img-des<%=listPl.get(7).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[0] %>" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                              		<%}else{
                              			%>
                                  		<img id="img-des<%=listPl.get(7).getId()%><%=objPr1.getProductSKU() %>" data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		}}else{
                              			%>
                                  		<img data-name="product_image" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/no-image-png-2.png" alt="<%=objPr1.getName() %>" title="<%=objPr1.getName() %>" style="width: 220px; height: 293px;" class="img-responsive">
                                  		<%
                              		} %>
                              		 </a>
                                
                              </div>
                              <div class="caption product-detail text-center">
                                 <h6 data-name="product_name" class="product-name"><a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>" title="Casual Shirt With Ruffle Hem"><%=objPr1.getName() %></a></h6>
                                <span class="price"><span class="amount"><span class="currencySymbol"><%=(int)objPr1.getPrice() %></span>đ</span>
                                </span>
                              </div>
                              <div class="row">
                                 <%
                                 if(images!=null&&color!=null){
                                	 String[] oimage_list = images.split(",");
                          			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
                          			String[] ocolor_list = color.split(",");
                          			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
                   					String applicationPath = request.getServletContext().getRealPath("");
                   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                   						for(int j=0; j<image_list.length; j++){
                   					File image = new File(path+image_list[j]);
                   					if(image.exists()){
                              		
                              %>
                                 		<div class="col-lg-3" style="padding-right: 0px; width: 40px;">
                                 			<img id="img-icon<%=listPl.get(7).getId()%><%=objPr1.getProductSKU() %><%=j %>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[j] %>" class="img-fluid rounded avatar-50 mr-3 img-icon" style="width: 30px; height: 30px; border-radius: 30px;" alt="image" title="<%=color_list[j]%>">
                                 		</div>
                                 		<script type="text/javascript">
									    	$( "#img-icon<%=listPl.get(7).getId()%><%=objPr1.getProductSKU() %><%=j %>" ).click(function() {
									    		var src_icon = $("#img-icon<%=listPl.get(7).getId()%><%=objPr1.getProductSKU() %><%=j %>").attr("src");
									    		var src_des = $("#img-des<%=listPl.get(7).getId()%><%=objPr1.getProductSKU() %>").attr("src");
										    	var path = src_icon.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename = src_icon.split("/").pop();
										    	var path1 = src_des.substring(0,src_icon.lastIndexOf('/'));									    		
										    	var filename1 = src_des.split("/").pop();
										    	var newSrc = path1+"/"+filename;
										    	$("#img-des<%=listPl.get(7).getId()%><%=objPr1.getProductSKU() %>").attr('src',newSrc);  
									    	});
                    					</script>
                                 		<%}}} %>
                                </div>
                            </div>
                          </div>
                        </div>
                        <%}}} %>
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
          <%} %>
        </div>
      </div>
    </div>
    </div>
    
    <!-- =====  CONTAINER END  ===== -->
    <%@ include file="/templates/public/inc/footer.jsp" %>
  <a id="scrollup"></a>
   </div>
</body>

</html>