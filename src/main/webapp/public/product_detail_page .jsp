<%@page import="java.util.Arrays"%>
<%@page import="library.RemoveDuplicateElements"%>
<%@page import="model.dao.CommentRateDAO"%>
<%@page import="model.bean.CommentRate"%>
<%@page import="java.io.File"%>
<%@page import="model.bean.ProductAdminDetail"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>
    <!-- =====  CONTAINER START  ===== -->
    <div class="container">
    <%
    	RemoveDuplicateElements remove = new RemoveDuplicateElements();
    	User objU = new User();
    	if(request.getAttribute("listPro")!=null){
    		 listPro = (ArrayList<ProductAdminDetail>) request.getAttribute("listPro");
    		String color="";
    		String size="";
    %>
      <div class="row ">
        <!-- =====  BANNER STRAT  ===== -->
        <div class="col-sm-12">
          <div class="breadcrumb ptb_20">
            <ul>
              <li><a href="<%=request.getContextPath() %>/public/index">Trang Chủ</a></li>
              <li><a href="<%=request.getContextPath()%>/public/product-by-cat?cat=<%=listPro.get(0).getCategory_menu_detail() %>"><%=listPro.get(0).getCategory_menu_detail() %></a></li>
              <li class="active"><%=listPro.get(0).getName() %></li>
            </ul>
          </div>
        </div>
        <!-- =====  BREADCRUMB END===== -->
        <div class="col-sm-12 col-lg-12 mtb_20">
          <div class="row mt_10 ">
            <div class="col-md-8 ajax-images">
            	<%
            		String images = listPro.get(0).getList_images();
            		if(images!=null){
            			String[] oimage_list = images.split(",");
             			String[] image_list = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
             			String[] ocolor_list = color.split(",");
             			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
    					String applicationPath = request.getServletContext().getRealPath("");
    					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
    					for(int i =0; i<image_list.length; i++){
    					File image = new File(path+image_list[i]);
    					if(image.exists()){
            		
            	%>
              <div class="col-md-6 mtb_10"><a class="thumbnails"> <img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list[i]%>" alt="" /></a></div>
           <%}}} %>
            </div>
           
            <div class="right-bar-order col-md-4 prodetail caption product-thumb">
             <form id="form-cart">
              <h4 data-name="product_name" class="product-name"><a href="#" title="Casual Shirt With Ruffle Hem"><%=listPro.get(0).getName() %></a></h4>
              <div class="product-top clearfix align-items-center mb-1">
              	<span class="variant-sku" itemprop="sku" content=""><%=listPro.get(0).getProductSKU() %></span>
				<span> | </span>
				<%
					int amount = 0;
					int quantity_sold = 0;
					String sku = listPro.get(0).getProductSKU();
                	CommentRateDAO objCmtRDAO = new CommentRateDAO();
                	ArrayList<CommentRate> listCmt = objCmtRDAO.getCmtBySKU(sku);
                	ArrayList<CommentRate> listCmtR5 = objCmtRDAO.getCmtRate(sku, "5");
                	
					
					for(ProductAdminDetail objPD: listPro){
						amount = amount + objPD.getAmount();
						quantity_sold = quantity_sold+objPD.getQuantity_sold();
					}
					
				%>
				<span class="number-product-sold">Đã Bán <%=quantity_sold %></span><span> | </span>
				<span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span>
                <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span>
                <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span>
                <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span>
                <span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i><i class="fa fa-star fa-stack-1x"></i></span>
				<span>(<%if(listCmtR5.size()>0){out.print(listCmtR5.size());}else{out.print(0);}; %>)</span>
			   </div>
              <span class="price mb_20"><span class="amount"><span class="currencySymbol"><%=(int)listPro.get(0).getPrice() %></span>đ</span></span>
              <hr>
              <div id="product">
                <div class="form-group">
                  <div class="row">
                    <div class="col-md-12 mb_50">
                    	<label>Màu Sắc: <span id="show-color"></span> </label>
                    	<label for="color" class="error"></label>
                    	<div class="row color">
		                      <%
                       			String[] olist_color = new String[listPro.size()];
                    			String[] oimages_list = new String[listPro.size()];
                    			
               		 			for(int i =0; i<listPro.size();i++){
               			 			olist_color[i]=listPro.get(i).getColor();
               			 			oimages_list[i]=listPro.get(i).getImages();
               					}
               		 		
               		 		String[] list_color = remove.remove_duplicate_elements_string(olist_color, olist_color.length);
               		 		String[] images_list = remove.remove_duplicate_elements_string(oimages_list, oimages_list.length);
               		 		for(int j=0; j<list_color.length; j++){
           				%>
		                		<div class="img-color">
		                			<input id="<%=list_color[j].replaceAll(" ", "")%>" name="color"  type="radio" data-color="<%=list_color[j]%>" value="<%=list_color[j]%>" required>
		                			<label for="<%=list_color[j].replaceAll(" ", "")%>"  id="color-<%=list_color[j].replaceAll(" ", "")%>" class="list-group-item text-center"  title="<%=list_color[j]%>" >
		                				<span style="background-image: url(<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=images_list[j] %>); background-size:50px;background-repeat:no-repeat;background-position: center;"></span>
		                			</label>
		                		</div>
		                		<script type="text/javascript">
		                		
		                		$('#color-<%=list_color[j].replaceAll(" ", "")%>').on('click', function(){
		                			$('#<%=list_color[j].replaceAll(" ", "")%>').attr('checked', 'checked');
		                			$('#show-color').html($('#<%=list_color[j].replaceAll(" ", "")%>').val());
	            					var changedField = $(this);
	     					        var color = $("#<%=list_color[j].replaceAll(" ", "")%>").val();
	     					        var sku = <%=listPro.get(0).getProductSKU() %>
	     					                    
	     					        $.ajax({
	     								url: '<%=request.getContextPath()%>/public/handel-product-detail',
	     								type: 'POST',
	     								cache: false,
	     								data: {
	     									asku: sku,
	     									acolor: color,
	     								},
	     								success: function(data){
	     									$('.ajax-size').html(data);
	     								},
	     								error: function (){
	     									alert('Có lỗi xảy ra');
	     								},
	     								
	     									});
	     					       if($('#<%=list_color[j].replaceAll(" ", "")%>').prop('checked')==false){
		                				var changedField = $(this);
	         					        var color = $("#<%=list_color[j].replaceAll(" ", "")%>").val();
	         					        var sku = <%=listPro.get(0).getProductSKU() %>
		                				$.ajax({
	         								url: '<%=request.getContextPath()%>/public/handel-product-detail',
	         								type: 'POST',
	         								cache: false,
	         								data: {
	         									askudp: sku,
	         									acolordp: color,
	         								},
	         								success: function(data){
	         									$('.ajax-images').html(data);
	         								},
	         								error: function (){
	         									alert('Có lỗi xảy ra');
	         								},
	         							});
		                			}
	     					      if($('#<%=list_color[j].replaceAll(" ", "")%>').prop('checked')==true){
		                				var changedField = $(this);
	         					        var color = $("#<%=list_color[j].replaceAll(" ", "")%>").val();
	         					        var sku = <%=listPro.get(0).getProductSKU() %>
		                				$.ajax({
	         								url: '<%=request.getContextPath()%>/public/handel-product-detail',
	         								type: 'POST',
	         								cache: false,
	         								data: {
	         									askudp: sku,
	         									acolordp: color,
	         								},
	         								success: function(data){
	         									$('.ajax-images').html(data);
	         								},
	         								error: function (){
	         									alert('Có lỗi xảy ra');
	         								},
	         							});
		                			}
		                		});
		                		
		                			</script>
		                <%}%>
                   </div>
                   	<label>Kích Thước: <span id="show-size"></span></label>
                   	<label for="size" class="error"></label>
                    <div class="col-md-12 mb_50">
                     <div class="row color ajax-size">
                       <%
                       String[] olist_size = new String[listPro.size()];
               		 	for(int i =0; i<listPro.size();i++){
               			 olist_size[i]=listPro.get(i).getSize();
               			 }
               		 	String[] list_size = remove.remove_duplicate_elements_string(olist_size, olist_size.length);
               		 	for(int j=0; j<list_size.length; j++){
           				%>
               		 <div class="img-color bg-secondary">
               		 	<input id="<%=list_size[j]%>" name="size"  type="radio" value="<%=list_size[j]%>" required>
               		 	<label for="<%=list_size[j]%>" title="<%=list_size[j]%>" id="size-<%=list_size[j]%>" class="list-group-item text-center"><%=list_size[j]%></label>
               		 </div>
               		 <script type="text/javascript">
		                	$('#size-<%=list_size[j]%>').on('click', function(){
		                			$('#show-size').html($('#<%=list_size[j]%>').val());
		                	});
		              </script>
		              <%} %>
                	</div>
                    </div>
                  </div>
                </div>
                <div class="qty mt_30">
	                <label>Qty</label>
	                <label for="quantity" class="error"></label>
	                  <div class="row">
	                  	 <div class="col-md-6 input-group1">
						  <div class="input-group-prepend">
						    <button type="button" class="btn btn-outline-secondary btn-minus">
						      <i class="fa fa-minus"></i>
						    </button>
						  </div>
					  		<input class="form-control quantity" id="amount" min="1" name="quantity" value="1" type="number">
						  <div class="input-group-append">
						    <button type="button" class="btn btn-outline-secondary btn-plus">
						      <i class="fa fa-plus"></i>
						    </button>
						  </div>
					</div>
				 <div class="col-md-6 btn-mua">
					<div id="add-to-cart-wrapper" class="add-to-cart-wrapper">
						<button type="button" id="btn-add-to-cart" class="btn-cart btn_buy add_to_cart btn-add-to-cart">Thêm vào giỏ hàng</button>
					</div>
				</div>
                  </div>
                 <script type="text/javascript">
                 $('.btn-plus, .btn-minus').on('click', function(e) {
					  const isNegative = $(e.target).closest('.btn-minus').is('.btn-minus');
					  const input = $(e.target).closest('.input-group1').find('input');
					  if (input.is('input')) {
					    input[0][isNegative ? 'stepDown' : 'stepUp']()
					  }
					});
                 $(function() {
                	 $('#form-cart').validate({
             			
             	   		ignore:[], //validate cho Textarea.
             				rules:{
             					'color':{
             						required: true,
             					},
             					'size':{
             						required: true,
             					},
             					'quantity':{
             						required: true,
             					},
             				},
             				
             				messages:{
             					'color':{
             						required: " Hãy Chọn Màu.",
             					},
             					'size':{
             						required: " Hãy Chọn size",
             					},
             					'quantity':{
             						required: " Hãy chọn số lượng.",
             					},
             				},
             	      });
					    $('#btn-add-to-cart').on('click', function() {
					    	if( $('#form-cart').valid()==true){
					    		var changedField = $(this);
						        var color = $("#show-color").text();
						        var size = $("#show-size").text();
						        var amount = $("#amount").val();
						        var sku = <%=listPro.get(0).getProductSKU() %>
						                    
						        $.ajax({
									url: '<%=request.getContextPath()%>/public/handel-cart',
									type: 'POST',
									cache: false,
									data: {
										asku: sku,
										acolor: color,
										asize: size,
										aamount: amount,
										
									},
									success: function(data){
										$('.ajax-cart').html(data);
									},
									error: function (){
										alert('Có lỗi xảy ra');
									},
								});
								return false;
					    	}else{
					    		 $('#form-cart').valid();
					    	}
					});
                 });
                 </script>
                </div>
               
              </div>
            </div>
            </form>
            </div>
            
            <div class="col-md-8">
              <div id="exTab5" class="mtb_30">
                <ul class="nav nav-tabs">
                  <li class="active"> <a href="#1c" data-toggle="tab">Đặc Tính Nổi Bậc</a> </li>
                   <li><a href="#2c" data-toggle="tab">Chi Tiết Sản Phẩm</a> </li>
                   <%
                  
                   if(listCmt.size()>0){
                   %>
                  <li><a href="#3c" data-toggle="tab">Đánh giá (<%=listCmt.size() %>)</a> </li>
                 <%}else{
                	 %>
                     <li><a href="#3c" data-toggle="tab">Đánh giá (0)</a> </li>
                    <%
                 } %>
                </ul>
                <div class="tab-content ">
                  <div class="tab-pane active" id="1c">
                    <p><%=listPro.get(0).getOutstanding_feature() %></p>
                  </div>
                  <div class="tab-pane" id="2c">
                    <p><%=listPro.get(0).getDetail() %></p>
                  </div>
                  <div class="tab-pane" id="3c">
                  	<div id="reviewer">
                  	<h4 class="mt_20 mb_30"><button class="form-control f-btn" type="button" onclick="showReviewer()" >Gửi đánh giá của bạn</button></h4>
                  		<form class="form-horizontal" id="formcomment" method="post"  enctype="multipart/form-data">
	                      
	                      <div id="reviewershow">
	                      <%
	                      	if(session.getAttribute("objU")!=null){
	                      		 objU = (User) session.getAttribute("objU");
	                      %>
	                      	<div class="row1">
		                      <div class="col-sm-12">
		                          <label class="control-label">Đánh giá của bạn về sản phẩm:</label>
		                          <label for="rating" class="error" style="font-size:12px; color: red"></label>
		                          <div class="rating">
		                          	
									  <input type="radio" name="rating" value="5" id="5"><label for="5">☆</label>
									  <input type="radio" name="rating" value="4" id="4"><label for="4">☆</label>
									  <input type="radio" name="rating" value="3" id="3"><label for="3">☆</label>
									  <input type="radio" name="rating" value="2" id="2"><label for="2">☆</label>
									  <input type="radio" name="rating" value="1" id="1"><label for="1">☆</label>
									</div>
		                        </div>
								<div class="col col-sm-12">
									<input type="text" class="form-control" name ="fullname" id="fullname" value="<%=objU.getFullname() %>" placeholder="Tên">
									<label for="fullname" class="error"></label>
								</div>
								<div class="col col-sm-6">
									<input type="text" class="form-control" name="email" id="email" value="<%=objU.getEmail() %>" placeholder="Email">
									<label for="email" class="error"></label>
								</div>
								<div class="col col-sm-6">
									<input type="text" class="form-control" name="phone" id="phone" value="<%if(objU.getPhone()!=null){out.print(objU.getPhone());}; %>" placeholder="Số điện thoại(nếu có)">
								</div>
								<div class="col col-sm-12 texarea">
									<label for="reviewer" class="error"></label>
									 <textarea class="form-control" id="reviewer-cmt" name="reviewer" placeholder="Nhập đánh giá của bạn"></textarea>
								</div>
								<div class="custom-file col-sm-12 filerv">
	    							<input type="file" name="pic" class="form-control" id="customFile" multiple="multiple" >
	    							<div id="myList"></div>
	    							
	  							</div>
			                      <div class="form-group required col-sm-12">
			                          <div class="buttons pull-right">
			                            <input type="button" id="comment-rv" class="btn f-btn1" value="Comment">
			                          </div>
			                      </div>
		                      </div>
		                      <%}else{
		                    	  %>
		                    	  	<div class="row1">
		                      <div class="col-sm-12">
		                          <label class="control-label">Đánh giá của bạn về sản phẩm:</label>
		                          <label for="rating" class="error" style="font-size:12px; color: red"></label>
		                          <div class="rating">
		                          	
									  <input type="radio" name="rating" value="5" id="5"><label for="5">☆</label>
									  <input type="radio" name="rating" value="4" id="4"><label for="4">☆</label>
									  <input type="radio" name="rating" value="3" id="3"><label for="3">☆</label>
									  <input type="radio" name="rating" value="2" id="2"><label for="2">☆</label>
									  <input type="radio" name="rating" value="1" id="1"><label for="1">☆</label>
								</div>
		                        </div>
								<div class="col col-sm-12">
									<input type="text" class="form-control" name="fullname" id="fullname" value="" placeholder="Tên">
									<label for="fullname" class="error"></label>
								</div>
								<div class="col col-sm-6">
									<input type="text" class="form-control" name="email" id="email" value="" placeholder="Email">
									<label for="email" class="error"></label>
								</div>
								<div class="col col-sm-6">
									<input type="text" class="form-control" name="phone" id="phone" value="" placeholder="Số điện thoại(nếu có)">
								</div>
								<div class="col col-sm-12 texarea">
									<label for="reviewer-cmt" class="error"></label>
									 <textarea class="form-control" id="reviewer-cmt" name="reviewer" placeholder="Nhập đánh giá của bạn"></textarea>
								</div>
								<div class="custom-file col-sm-12 filerv">
	    							<input type="file" name="pic" class="form-control" id="customFile" multiple="multiple" >
	  								<div id="myList" class="row1"></div>
	    							
	  							</div>
			                      <div class="form-group required col-sm-12">
			                          <div class="buttons pull-right">
			                            <input type="button" id="comment-rv" class="btn f-btn1" value="Comment">
			                          </div>
			                      </div>
		                      </div>
		                    	  <%
		                      } %>
	                      </div>
	                    </form>
                  	</div>
                    <div class="show-comment">
                    	<%
                    		if(listCmt.size()>0){
                    			for(CommentRate objCmtR: listCmt){
							
                    	%>
                    	<div>
                    		<h4><%=objCmtR.getName() %></h4>
                    		<p><%=objCmtR.getComment() %></p>
                    		<div class="sapo-review-actions" style="color: #030d78">
	                    		<ul>
		                    		<li class="sapo-review-reply">
		                    			<a onclick="showRepleyCmt<%=objCmtR.getId() %>()" title="Gửi trả lời" data-value="<%=objCmtR.getId() %>"  href="javascript: void(0);">Gửi trả lời</a>
		                    		</li>
		                    		<li class="sapo-review-useful">
			                    		<a id="like<%=objCmtR.getId() %>" class="ajax-show-likecomment<%=objCmtR.getId() %>" title="Hữu ích" href="javascript: void(0);">
			                    		<svg class="icon-useful" version="1.1" viewBox="0 0 30 30" xml:space="preserve" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
			                    		<path d="M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z"></path>
			                    	
			                    		</svg><%if(objCmtR.getLike_coment()>0){out.print(objCmtR.getLike_coment());} %> Hữu ích</a>
		                    		</li>
		                    		<li class="sapo-review-reportreview">
			                    		<a id="report<%=objCmtR.getId() %>" title="Báo cáo sai phạm" href="javascript: void(0);">
			                    		<svg class="icon-warning" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 48 48">
			                    		<path d="M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z"></path>
			                    		<path d="M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z"></path>
			                    		</svg>Báo cáo sai phạm</a>
		                    		</li>
	                    		</ul>
                    		</div>
                    		<div class="sapo-review-images">
	                    		<ul>
	                    			<%
		                    			if(objCmtR.getImages()!=null){
		                    				String imagesRv = objCmtR.getImages();
		                    				String[] list_images_rv = imagesRv.split(",");
		                    				if(list_images_rv.length>0){
		                    					for(int i =1; i<list_images_rv.length; i++){
		                    		%>
		                    		<li>
		                    			
			                    		<a data-fancybox="gallery" href="">
			                    			<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=list_images_rv[i]%>">
			                    		</a>
		                    		</li>
		                    		<%}}}%>
	                    		</ul>
                    		</div>
                    		<div class="show-recomment" id="show-recomment<%=objCmtR.getId()%>">
                    			<%
                    				ArrayList<CommentRate> listCmtChil = objCmtRDAO.getCmtChil(objCmtR.getProduct_sku(), objCmtR.getId());
                    					if(listCmtChil.size()>0){
                    						for(CommentRate objCmtChil: listCmtChil){
                    							%>
                    								<h4><%=objCmtChil.getName() %></h4>
                    								<p><%=objCmtChil.getComment() %></p>
                    								<div class="sapo-review-actions" style="color: #030d78">
		                    							<ul>
								                    		<li class="sapo-review-useful">
									                    		<a id="like<%=objCmtChil.getId() %>" class="ajax-show-likecomment<%=objCmtChil.getId() %>" title="Hữu ích" href="javascript: void(0);">
									                    		<svg class="icon-useful" version="1.1" viewBox="0 0 30 30" xml:space="preserve" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
									                    		<path d="M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z"></path>
									                    	
									                    		</svg><%if(objCmtChil.getLike_coment()>0){out.print(objCmtChil.getLike_coment());} %> Hữu ích</a>
								                    		</li>
								                    		<li class="sapo-review-reportreview">
									                    		<a id="report<%=objCmtChil.getId() %>" title="Báo cáo sai phạm" href="javascript: void(0);">
									                    		<svg class="icon-warning" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 48 48">
									                    		<path d="M40,40H8c-0.717,0-1.377-0.383-1.734-1.004c-0.356-0.621-0.354-1.385,0.007-2.004l16-28C22.631,8.378,23.289,8,24,8s1.369,0.378,1.728,0.992l16,28c0.361,0.619,0.363,1.383,0.007,2.004S40.716,40,40,40z"></path>
									                    		<path d="M22,34.142c0-0.269,0.047-0.515,0.143-0.746c0.094-0.228,0.229-0.426,0.403-0.592c0.171-0.168,0.382-0.299,0.624-0.393c0.244-0.092,0.518-0.141,0.824-0.141c0.306,0,0.582,0.049,0.828,0.141c0.25,0.094,0.461,0.225,0.632,0.393c0.175,0.166,0.31,0.364,0.403,0.592C25.953,33.627,26,33.873,26,34.142c0,0.27-0.047,0.516-0.143,0.74c-0.094,0.225-0.229,0.419-0.403,0.588c-0.171,0.166-0.382,0.296-0.632,0.392C24.576,35.954,24.3,36,23.994,36c-0.307,0-0.58-0.046-0.824-0.139c-0.242-0.096-0.453-0.226-0.624-0.392c-0.175-0.169-0.31-0.363-0.403-0.588C22.047,34.657,22,34.411,22,34.142 M25.48,30h-2.973l-0.421-12H25.9L25.48,30z"></path>
									                    		</svg>Báo cáo sai phạm</a>
								                    		</li>
		                    							</ul>
                    								</div>
                    								<script type="text/javascript">
                    	    						var ip_address;
                    	    						$.getJSON("https://api.ipify.org?format=json", function(data) {
                    		    			            // Setting text of element P with id gfg
                    		    			            ip_address = data.ip;
                    		    			           // $("#ipadd").html(ipaddress);
                    		    			        })
                    								$('#like<%=objCmtChil.getId()%>').on('click', function(){
                    									var user_id = <%if(session.getAttribute("objU")!=null){
                    				  						objU = (User) session.getAttribute("objU");
                    				  							out.print(objU.getId());
                    				  						}else{
                    				  							out.print(0);
                    				  						}%>;
                    				  						if(user_id>0){
                    				  							$(this).addClass('active');
                    				  							var id = <%=objCmtChil.getId()%>;
                           	                			  	 $.ajax({	
                           	                					url: '<%=request.getContextPath()%>/public/show-like-comment',
                           	                					type: 'POST',
                           	                					cache: false,
                           	                					data: {
                           	                						aid: id,
                           	                						aip_address: ip_address,
                           	                									
                           	                					},
                           	                					success: function(data){
                           	                						$('.ajax-show-likecomment<%=objCmtChil.getId()%>').html(data);
                           	                					},
                           	                					error: function (){
                           	                						alert('Có lỗi xảy ra');
                           	                					},
                           	                			  });	
                    				  						}else{
                    				  							alert("Vui lòng đăng nhập!");
                    				  						}
                    	                		});
                    								</script>
                    							<%
                    						}
                    					}
                    			%>
                    			
                    		</div>
                    		<div class="re-comment" id="re-comment<%=objCmtR.getId() %>" data-value="<%=objCmtR.getId() %>">
                    			<%
                    			
	                      	if(session.getAttribute("objU")!=null){
	                      		objU = (User) session.getAttribute("objU");
	                      %>
                    			<form id="re-form<%=objCmtR.getId()%>" class="re-form">
                    				<div class="row1">
									    <div class="col colm-3">
									    <label for="re-name" class="error"></label>
									      <input type="text" class="form-control" name="re-name" id="re-name<%=objCmtR.getId()%>" value="<%=objU.getFullname() %>" placeholder="Tên">
									    </div>
									    <div class="col colm-3">
									    <label for="re-email" class="error"></label>
									      <input type="text" class="form-control" name="re-email" id="re-email<%=objCmtR.getId()%>" value="<%=objU.getEmail() %>" placeholder="Email">
									    </div>
									    <div class="col colm-3">
									      <input type="text" class="form-control" id="re-phone<%=objCmtR.getId()%>" name="re-phone" value="<%if(objU.getPhone()!=null){out.print(objU.getPhone());}; %>" placeholder="Số điện thoại(nếu có)">
									    </div>
									    <div class="col col-sm-10">
									    	<label for="re-comment" class="error"></label>
									      <input type="text" class="form-control" id="re-comments<%=objCmtR.getId()%>" name="re-comment" placeholder="Nhập trả lời của bạn">
									    </div>
									    <div class="col col-sm-2">
									      <button class="form-control" id="recomment<%=objCmtR.getId()%>" type="button">Trả Lời</button>
									    </div>
									</div>
                    			</form>
                    			<%}else{
                    				%>
                        			<form id="re-form<%=objCmtR.getId()%>" class="re-form">
                        				<div class="row1">
    									    <div class="col colm-3">
    									    <label for="re-name" class="error"></label>
    									      <input type="text" class="form-control" name="re-name" id="re-name<%=objCmtR.getId()%>" placeholder="Tên">
    									      
    									    </div>
    									    <div class="col colm-3">
    									    <label for="re-email" class="error"></label>
    									      <input type="text" class="form-control" name="re-email" id="re-email<%=objCmtR.getId()%>" placeholder="Email">
    									    </div>
    									    <div class="col colm-3">
    									      <input type="text" class="form-control" id="re-phone<%=objCmtR.getId()%>" name="re-phone" placeholder="Số điện thoại(nếu có)">
    									    </div>
    									    <div class="col col-sm-10">
    									    	<label for="re-comment" class="error"></label>
    									      <input type="text" class="form-control" name="re-comment" id="re-comments<%=objCmtR.getId()%>" placeholder="Nhập trả lời của bạn">
    									    </div>
    									    <div class="col col-sm-2">
    									      <button class="form-control" id="recomment<%=objCmtR.getId()%>" type="button">Trả Lời</button>
    									    </div>
    									</div>
                        			</form>
                        			<%
                    			} %>
                    		</div>
                    		<script type="text/javascript">
                    		$('#re-form<%=objCmtR.getId()%>').validate({
                    			
                    	   		ignore:[], //validate cho Textarea.
                    				rules:{
                    					're-name':{
                    						required: true,
                    						minlength:6,
                    					},
                    					're-email':{
                    						required: true,
                    						email: true,
                    					},
                    					're-comment':{
                    						required: true,
                    						minlength:10,
                    					},
                    				},
                    				
                    				messages:{
                    					're-name':{
                    						required: " Hãy nhập vào tên.",
                    						minlength:" Hãy nhập nhiều hơn 6 ký tự .",
                    					},
                    					're-email':{
                    						required: " Hãy nhập vào email",
                    						email:" Hãy nhập đúng định dạng.",
                    					},
                    					're-comment':{
                    						required: " Hãy nhập vào trả lời của bạn.",
                    						minlength:" Hãy nhập nhiều hơn 10 ký tự.",
                    					},
                    				},
                    	      });
                    		$('#recomment<%=objCmtR.getId()%>').on('click', function(){
                    			$('#re-form<%=objCmtR.getId()%>').valid();
                    				if($('#re-form<%=objCmtR.getId()%>').valid()==true){
                    					var id = <%=objCmtR.getId()%>;
                    					var sku = '<%=listPro.get(0).getProductSKU()%>';
                    			  		var comment = $("#re-comments<%=objCmtR.getId()%>").val();
                    			  		var fullname = $("#re-name<%=objCmtR.getId()%>").val();
                    			  		var email = $("#re-email<%=objCmtR.getId()%>").val();
                    			  		var phone = $("#re-phone<%=objCmtR.getId()%>").val();
                    			  		var user_id = <%if(session.getAttribute("objU")!=null){
                    			  						objU = (User) session.getAttribute("objU");
                    			  							out.print(objU.getId());
                    			  						}else{
                    			  							out.print(0);
                    			  						}%>;
                    			  		
                    			  	 			$.ajax({	
                    								url: '<%=request.getContextPath()%>/public/show-recomment',
                    								type: 'POST',
                    								cache: false,
                    								data: {
                    									aid: id,
                    									asku: sku,
                    									acomment: comment,
                    									afullname: fullname,
                    									aemail: email,
                    									aphone: phone,
                    									auser_id: user_id,
                    								},
                    								success: function(data){
                    									$('#show-recomment<%=objCmtR.getId()%>').html(data);
                    									$('#re-comment<%=objCmtR.getId()%>').hide();
                    								},
                    								error: function (){
                    									alert('Có lỗi xảy ra');
                    								},
                    			  			});
                    				}else{
                    					$('#re-form<%=objCmtR.getId()%>').valid();
                    				}
                    			  		
                    		});
                    		function showRepleyCmt<%=objCmtR.getId()%>(){
	    							$('#re-comment<%=objCmtR.getId()%>').toggle();
	    						};
	    					var ip_address;
	    					$.getJSON("https://api.ipify.org?format=json", function(data) {
	    			            // Setting text of element P with id gfg
	    			            ip_address = data.ip;
	    			           // $("#ipadd").html(ipaddress);
	    			        })
	    					$('#like<%=objCmtR.getId()%>').on('click', function(){
	    						var user_id = <%if(session.getAttribute("objU")!=null){
			  						objU = (User) session.getAttribute("objU");
			  							out.print(objU.getId());
			  						}else{
			  							out.print(0);
			  						}%>;
			  						if(user_id>0){
			  							$(this).addClass('active');
			  							var id = <%=objCmtR.getId()%>;
	                    			  	 $.ajax({	
	                    					url: '<%=request.getContextPath()%>/public/show-like-comment',
	                    					type: 'POST',
	                    					cache: false,
	                    					data: {
	                    						aid: id,
	                    						aip_address: ip_address,
	                    					},
	                    					success: function(data){
	                    						$('.ajax-show-likecomment<%=objCmtR.getId()%>').html(data);
	                    					},
	                    					error: function (){
	                    						alert('Có lỗi xảy ra');
	                    					},
	                    			  });	
			  						}else{
			  							alert("Vui lòng đăng nhập");
			  						}
	    					});		
                    		</script>
                    	</div>
                    	<%}} %>
                    </div>
                  </div>
                  
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="heading-part text-center mb_10">
                <h2 class="main_title mt_50">Gợi Ý Cho Bạn</h2>
              </div>
              <div class="related_pro box">
                <div class="nArrivals owl-carousel mb_50 "> 
                <%
                	if(request.getAttribute("listPAD")!=null){
	                	ArrayList<ProductAdminDetail> listPro2 = (ArrayList<ProductAdminDetail>)request.getAttribute("listPAD");
	                	String images1 = "";
	                	String color1 = "";
	                	if(listPro2.size()>0){
      	                	for(ProductAdminDetail objPr1: listPro2){
      	                		images1 = objPr1.getImages();
      	                		color1 = objPr1.getColor();
                      %>
                            <div class="product-grid col-md-12 mtblr_20">
                            	<div class="item">
                					<div class="mb_30">
                              <div class="image product-imageblock ">
      							<a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>">
                              		<%
                              		if(images!=null){
                              			String[] image_list = images1.split(",");
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
                                <h6 data-name="product_name" class="product-name"><a href="<%=request.getContextPath() %>/public/product-detail?sku=<%=objPr1.getProductSKU() %>" title="Casual Shirt With Ruffle Hem"><%=objPr1.getName() %></a></h6>
                                <span class="price"><span class="amount"><span class="currencySymbol"><%=objPr1.getPrice() %></span>đ</span>
                                </span>
                              </div>
                              <div class="row">
                                 <%
                                 if(images!=null&&color!=null){
                                	 String[] oimage_list = images1.split(",");
                          			String[] image_list2 = remove.remove_duplicate_elements_string(oimage_list, oimage_list.length);
                          			String[] ocolor_list = color1.split(",");
                          			String[] color_list = remove.remove_duplicate_elements_string(ocolor_list, ocolor_list.length);
                   					String applicationPath = request.getServletContext().getRealPath("");
                   					String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
                   						for(int j=0; j<image_list2.length; j++){
                   					File image = new File(path+image_list2[j]);
                   					if(image.exists()){
                              		
                              %>
                                 		<div class="col-lg-3" style="padding-right: 0px; width: 40px;">
                                 			<img id="img-icon<%=objPr1.getProductSKU() %><%=j%>" src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=image_list2[j] %>" class="img-fluid rounded avatar-50 mr-3 img-icon" style="width: 30px; height: 30px; border-radius: 30px;" alt="image" title="<%=color_list[j]%>">
                                 		</div>
                                 		<script type="text/javascript">
									    	$( "#img-icon<%=objPr1.getProductSKU() %><%=j%>" ).click(function() {
									    		var src_icon = $("#img-icon<%=objPr1.getProductSKU() %><%=j%>").attr("src");
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
                           </div>
                           </div>
                        <%}}} %>
                  </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <script type="text/javascript">
      //$('#customFile').MultiFile({
  		//list: '#myList',
  		//max: 3,
  		//STRING: {
  		  //    file: '<em title="Click to remove" onclick="$(this).parent().prev().click()">$file</em>',
  		    //  remove: '<i class="fa fa-times-circle" style="position: absolute;"></i>'
  		    //}
  	    //});
      
      $('#formcomment').validate({
			
   		ignore:[], //validate cho Textarea.
			rules:{
				rating:{
					required: true,
				},
				fullname :{
					required: true,
					minlength:6,
				},
				email:{
					required: true,
					email: true,
					},
				reviewer:{
					required: true,
					minlength:10,
				},
			},
			
			messages:{
				rating:{
					required: "Đánh giá của bạn.",
				},
				fullname:{
					required: " Hãy nhập vào tên.",
					minlength:" Hãy nhập nhiều hơn 6 ký tự .",
				},
				email:{
					required: " Hãy nhập vào email",
					email:" Hãy nhập đúng định dạng.",
				},
				reviewer:{
					required: " Hãy nhập vào đánh giá của bạn.",
					minlength:" Hãy nhập nhiều hơn 10 ký tự.",
				},
			},
      });
$('#comment-rv').on('click', function(){
	$('#formcomment').valid();
		if($('#formcomment').valid()==true){
			var sku = '<%=listPro.get(0).getProductSKU()%>';
	  		var comment = $("#reviewer-cmt").val();
  	  		var rate = $(".rating input[name=rating]").val();
	  		var fullname = $("#fullname").val();
	  		var email = $("#email").val();
	  		var phone = $("#phone").val();
	  		var ins = customFile.files.length;
	  		var user_id = <%if(session.getAttribute("objU")!=null){
	  							objU = (User) session.getAttribute("objU");
	  							out.print(objU.getId());
	  						}else{
	  							out.print(0);
	  						}%>;
	  		var formData = new FormData();
	  		
	  		formData.append('asku', sku);
	  	  	formData.append('arate', rate);
	  	  	formData.append('acomment', comment);
	  	  	formData.append('afullname', fullname);
	  	  	formData.append('aemail', email);
	  	  	formData.append('aphone', phone);
	  	  	formData.append('auser_id', user_id);
	  	  	for (var x = 0; x < ins; x++) {
	  	  	formData.append("pic", customFile.files[x]);
				};
	  	 			$.ajax({	
						url: '<%=request.getContextPath()%>/public/show-comment',
						type: 'POST',
						cache: false,
						contentType: false,
						data: formData,
						processData: false,
						success: function(data){
							$('.show-comment').html(data);
							$('#reviewershow').hide();
							
						},
						error: function (){
							alert('Có lỗi xảy ra');
						},
	  			});
		}else{
			$('#formcomment').valid();
		}		
			});

      </script>
      <%} %>
    </div>
    <script>
    	function showReviewer(){
    		$('#reviewershow').toggle();
    	};
        
    </script>
    <!-- =====  CONTAINER END  ===== -->
    <%@ include file="/templates/public/inc/footer.jsp" %>
  </div>
  <a id="scrollup"></a>
</body>

</html>