<%@page import="model.bean.Status"%>
<%@page import="model.dao.StatusDAO"%>
<%@page import="library.RemoveDuplicateElements"%>
<%@page import="java.util.List"%>
<%@page import="model.dao.OrdersDAO"%>
<%@page import="model.bean.OrderDetail"%>
<%@page import="model.bean.Address"%>
<%@page import="model.dao.AddressDAO"%>
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
          
          <section class="bread-crumb d-none d-md-block">
			<div class="container">
				<div class="row">
					<div class="col-12 a-left">
						<ul class="breadcrumb">
							<li><a href="<%=request.getContextPath() %>/public/index">Trang Chủ</a></li>
              				<li><a href="<%=request.getContextPath()%>/public/customer">Tài Khoản</a></li>
              				<li class="active">Tài Khoản</li>
						</ul>
					</div>
				</div>
			</div>
		</section>
		
          <!-- =====  SUB BANNER END  ===== -->
    <!-- =====  CONTAINER START  ===== -->
    <section class="signup page_customer_account">
    <div class="container">
    
      <!-- =====  SUB BANNER  STRAT ===== -->
      <!-- =====  SUB BANNER END  ===== -->
      <%
      	User objU = new User();
      	if(request.getAttribute("objU")!=null){
      		objU = (User) request.getAttribute("objU");
      	}
      		String address = objU.getAddress();
    		String[] listAddress = address.split("!"); 
      %>
      <div class="row row2">
      <div class="col-left-ac">
          <div class="block-account">
			<div class="info info-1">
				<img src="//bizweb.dktcdn.net/100/438/408/themes/904142/assets/account_ava.jpg?1681726130231" alt="nguyen the">
				<p><%=objU.getFullname() %></p>
				<a class="click_logout" href="<%=request.getContextPath() %>/auth/public/logout">Đăng xuất</a>
			</div>
			<ul>
				<li>
					<a disabled="disabled" id="my-account" class="title-info active">Tài khoản của tôi</a>
				</li>
				<li>
					<a class="title-info" id="my-order">Đơn hàng của tôi</a>
				</li>
				<li>
					<a class="title-info" id="change-password">Đổi mật khẩu</a>
				</li>
				<li>
					<a class="title-info" id="address">Sổ địa chỉ</a>
				</li>
			</ul>
		</div>
     </div>
     <div class="col-right-ac">
		<h1 class="title-head margin-top-0">Thông tin cá nhân<button id="edit-personal" class="btn-edit-addr btn btn-primarys btn-more" type="button" >Sửa thông tin</button></h1>
		<div class="form-signup name-account m992 ajax-change-info">
			<p><strong>Họ và tên:</strong> <%=objU.getFullname() %></p>
			<p><strong>Địa chỉ email:</strong> <%=objU.getEmail() %></p>
		</div>
		<div id="card-body" class="card-body" style="display: none;">
		<h1>Sửa Thông Tin</h1>	
			 <form id="form-change-info" action="<%=request.getContextPath() %>/admin/update-personal" method="Post" data-toggle="validator">
                            <div class="row"> 
                                <div class="col-md-6">                      
                                    <div class="form-group">
                                        <label>Name *</label>
                                        <input type="text" name="fullname" id="name_info" class="form-control field__input" value="<%=objU.getFullname() %>" placeholder="Enter Name" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Email *</label>
                                        <input type="text" id="email_info" name="email" class="form-control field__input" value="<%=objU.getEmail() %>" placeholder="Enter Email" required>
                                    	<div class="ajax-email-info"></div>
                                    </div>
                                </div>   
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Phone Number *</label>
                                        <input type="text" name="phone" id="phone_info" class="form-control field__input" value="<%=objU.getPhone() %>" placeholder="Enter Phone No" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                </div>              
                            <button type="button" id="change-info" class="btn btn-primary mr-2">Cập nhật</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </form>
		</div>
		<div id="display-order" class="card-body ajax-order" style="display: none;">
		<%
			OrdersDAO objOdDAO = new OrdersDAO();
			StatusDAO statusDAO = new StatusDAO();
			ArrayList<Status> list_status = statusDAO.getItem();
        	ArrayList<OrderDetail> listOrderByOrderCode = objOdDAO.getListOrderByUser(objU.getId());
        	RemoveDuplicateElements remove = new RemoveDuplicateElements();
        	ArrayList<OrderDetail> listOrderByCode = new ArrayList<>();
        	List<String> olist_order_code = new ArrayList<>();
        	List<String> list_order_code = new ArrayList<>();
        	if(listOrderByOrderCode.size()>0) {
				for (OrderDetail objPD : listOrderByOrderCode) {
					olist_order_code.add(objPD.getOder_code());
				}
        	}
        	if(olist_order_code.size()>0) {
        		list_order_code = remove.remove_duplicate_elements_string_list(olist_order_code);
				for(String string: list_order_code) {
					listOrderByCode = objOdDAO.getListOrderByOrderCode(string);
		%>
			<div class="body-order-body col-lg-12">
                <div class="body_body_main mb-3">
		            <%
			            if(listOrderByCode.size()>0) {
							int total =0;
							int discount = 0;
							int transport = 0;
							total =(int) listOrderByCode.get(0).getTotal();
							transport = (int) listOrderByCode.get(0).getShipping();
							if(listOrderByCode.get(0).getDiscount()>0){
								discount = (int)listOrderByCode.get(0).getDiscount();
							}
							for (OrderDetail objPD: listOrderByCode){
					%>
		            <div class="body_body_head mb-3 col-sm-12 row">
			             <div class="body_body_left col-sm-4">
			             	<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=objPD.getImages() %>" alt="<%=objPD.getProductName() %>" title="<%=objPD.getProductName()%>">
			             </div>
			            <div class="body_body_right col-sm-8">
				            <p class="text-left product-name"><%=objPD.getProductName()%></p>
				            <span class="text-left color_size"><%=objPD.getColor() %>/<%=objPD.getSize() %></span>
				            <p class="quantity_order">X<%=objPD.getQty() %></p>
				            <span class="text-left price_order"><%=(int)objPD.getPrice()%>đ</span>
				            <span class="text-left subtotal_one"><span class="subtotal_two">Subtotal: </span><%=(int)(objPD.getPrice()*objPD.getQty())%>đ</span>
			            </div>
		            </div>
		           <%}%>
		           	</div>
					       <div class="col-sm-12">
					                    <table class="table">
					                      <tbody>
					                        <tr>
					                        	<td class="text-right">Discount</td>
					                        	<td class="text-right"><%=discount %> đ</td>
					                        	<td class="text-right">Transports</td>
					                        	<td class="text-right"><%=transport%> đ</td>
					                          <td class="text-right"><strong>Total</strong></td>
					                          <td class="text-right"><%=total %> đ</td>
					                        </tr>
					                         <tr>
					                         	<%
					                         		if(listOrderByCode.get(0).getStatus()==1){
					                         	%>
					                        	<th class="text-center" colspan="6"><button type="button" value="<%=listOrderByCode.get(0).getOder_code() %>" class="cancel_order">Hủy Đơn</button></th>
					                        	<%}else{
					                        		if(listOrderByCode.get(0).getStatus()==6){
					                        		%>
					                        		<th class="text-center" colspan="6"><button type="button">Đã Hủy</button><br><br><button type="button" value="<%=listOrderByCode.get(0).getOder_code() %>" class="rep_order">Đặt Lại</button></th>
					                        		<%
					                        	}else{
					                        		%>
					                        		<th class="text-center" colspan="6"><button type="button">Đang Vận Chuyển</button></th>
					                        		<%
					                        	}}%>
					                        </tr>
					                        <tr>
					                        	<th class="text-center" colspan="6"><button type="button" id="help_order">Liên hệ chăm sóc khách hàng</button></th>
					                        </tr>
					                      </tbody>
					                </table>
			                </div>
			        </div>
		           <%}%>       
		       
        <%}}%>
		</div>
		<div id="password-change" class="card-body" style="display: none;">
		<h1>Đổi Mật Khẩu</h1>
			 <form id="form-change-password" method="Post" data-toggle="validator">
			 <div class="ajax-change-password"></div>
                   <div class="row"> 
                        <div class="col-md-6">                      
                            <div class="form-group">
                                 <label>Mật Khẩu Cũ</label>
                                 <input type="password" id="old_password" name="old_password" class="form-control field__input" value="" placeholder="Enter Old Password" required>
                              	<div class="ajax-check-password"></div>
                              	<div class="help-block with-errors"></div>
                           </div>
                           <div class="form-group">
                                 <label>Mật Khẩu Mới</label>
                                 <input type="password" id="new_password" name="new_password" class="form-control field__input" value="" placeholder="Enter New Password" required>
                              	<div class="help-block with-errors"></div>
                           </div>
                           <div class="form-group">
                                 <label>Nhập Lại Mật Khẩu</label>
                                 <input type="password" id="re_password" name="re_password" class="form-control field__input" value="" placeholder="Enter Re-Password" required>
                              	<div class="help-block with-errors"></div>
                           </div>
                        </div>   
                    </div>              
              		<button type="button" id="btn-change-password" class="btn btn-primary mr-2">Cập nhật</button>
                    <button type="reset" class="btn btn-danger">Reset</button>
            </form>
		</div>
		<div id="address-list" class="card-body" style="display: none;">
			<h1>Sổ Địa Chỉ</h1>
			<div class="col-lg-12">
                <div class="table-responsive rounded mb-3">
                <table class="data-table table mb-0 tbl-server-info">
                    <thead class="bg-white text-uppercase">
                        <tr class="ligth ligth-data">
                        	<th scope="col"></th>
                            <th scope="col">Name</th>
                            <th scope="col">Phone No.</th>
                            <th scope="col" colspan="4">Address</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody class="ligth-body">
                    <%
                    if(listAddress.length>0){
                		for(int i=0; i<listAddress.length; i++){
                			String addressi = listAddress[i];
                			String [] arradsress = addressi.split("-");
                    %>
                        <tr>
                        	<th scope="row">Địa Chỉ <%=i+1 %></th>
                        	<%for(int j=0; j<arradsress.length; j++){ %>
                            <td><%=arradsress[j] %></td>
                            <%} %>
                             <td>
                                <div class="d-flex align-items-center list-action">
                                    <a class="badge bg-success mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit"
                                        href="<%=request.getContextPath()%>/admin/update-personal?id=<%=objU.getId()%>"><i class="ri-pencil-line mr-0"></i></a>
                                </div>
                            </td>
                        </tr>
                        <%}}%>
                    </tbody>
                </table>
                </div>
		</div>
	 </div>
     </div>
    </div>
    </section>
    <script type="text/javascript">
		$('#edit-personal').on('click', function(){
			$('#card-body').toggle();
			$('#password-change').hide();
			$('#address-list').hide();
			$('#display-order').hide();
		});
		$('#change-password').on('click', function(){
			$('#password-change').toggle();
			$('#card-body').hide();
			$('#address-list').hide();
			$('#display-order').hide();
		});
		$('#address').on('click', function(){
			$('#address-list').toggle();
			$('#card-body').hide();
			$('#password-change').hide();
			$('#display-order').hide();
		});
		$('#my-account').on('click', function(){
			$('#card-body').hide();
			$('#password-change').hide();
			$('#address-list').hide();
			$('#display-order').hide();
		});
		$('ul li .title-info').click(function(){
		    $('li .title-info').removeClass("active");
		    $(this).addClass("active");
		});
		$('#form-change-password').validate({
			rules:{
			
				old_password:{
					required: true,
					minlength: 6,
					maxlength: 13,
					},
				new_password:{
					required: true,
					minlength: 6,
					maxlength: 13,
				},
				re_password:{
					required: true,
					minlength: 6,
					maxlength: 13,
					equalTo: "#new_password"
				},
			},
			messages:{
				email:{
					required: 'Nhập vào email của bạn.',
					email: 'Hãy nhập đúng định dạng.',
				},
				old_password:{
					required: 'Nhập vào mật khẩu cũ.',
					minlength: 'Nhập ít nhất 6 ký tự.',
					maxlength: 'Nhập tối da 13 ký tự.',
					},
				new_password:{
					required: 'Nhập vào mật khẩu mới.',
					minlength: 'Nhập ít nhất 6 ký tự.',
					maxlength: 'Nhập tối da 13 ký tự.',
				},
				re_password:{
					required: 'Nhập lại mật khẩu mới.',
					minlength: 'Nhập ít nhất 6 ký tự.',
					maxlength: 'Nhập tối da 13 ký tự.',
					equalTo: 'Mật khẩu chưa chính xác.'
				},
			},
		});
		$('#form-change-info').validate({
			rules:{
				email:{
					required: true,
					email: true,
				},
				
			},
			messages:{
				email:{
					required: 'Nhập vào email của bạn.',
					email: 'Hãy nhập đúng định dạng.',
				},
			},
		});
		$('#old_password').on('input', function(){
   		 var changedField = $(this);
		     var old_password = $("#old_password").val(); 
		     var id = <%=objU.getId()%>
		        $.ajax({
					url: '<%=request.getContextPath()%>/public/update-personal',
					type: 'POST',
					cache: false,
					data: {
							aid: id,
							aold_password: old_password,
						},
					success: function(data){
						$('.ajax-check-password').html(data);
					},
					error: function (){
						alert('Có lỗi xảy ra');
					},
				});
				return false;
   	});
		
		$('#email_info').on('blur', function(){
	   		 var changedField = $(this);
			     var email = $("#email_info").val(); 
			     var id = <%=objU.getId()%>
			        $.ajax({
						url: '<%=request.getContextPath()%>/public/update-personal',
						type: 'POST',
						cache: false,
						data: {
								aid: id,
								aemail: email,
							},
						success: function(data){
							$('.ajax-email-info').html(data);
						},
						error: function (){
							alert('Có lỗi xảy ra');
						},
					});
					return false;
	   	});
		$('#change-info').on('click', function(){
			if($('#form-change-info').valid()==true){
				var changedField = $(this);
			     var name = $("#name_info").val(); 
			     var email = $('#email_info').val();
			     var phone = $('#phone_info').val();
			     var id = <%=objU.getId()%>
			        $.ajax({
						url: '<%=request.getContextPath()%>/public/update-personal',
						type: 'POST',
						cache: false,
						data: {
								aid: id,
								aname: name,
								email: email,
								aphone: phone,
							},
						success: function(data){
							$('.ajax-change-info').html(data);
							$('#card-body').hide();
						},
						error: function (){
							alert('Có lỗi xảy ra');
						},
					});
					return false;
			}else{
				$('#form-change-password').valid();
			}
		});
		$('#btn-change-password').on('click', function(){
			if($('#form-change-password').valid()==true){
				var changedField = $(this);
			     var old_password = $("#old_password").val(); 
			     var new_password = $('#new_password').val();
			     var id = <%=objU.getId()%>
			        $.ajax({
						url: '<%=request.getContextPath()%>/public/update-personal',
						type: 'POST',
						cache: false,
						data: {
								aid: id,
								password: old_password,
								anew_password: new_password,
							},
						success: function(data){
							$('.ajax-change-password').html(data);
							$('#form-change-password').trigger("reset");
						},
						error: function (){
							alert('Có lỗi xảy ra');
						},
					});
					return false;
			}else{
				$('#form-change-password').valid();
			}
		});
		$('#my-order').on('click', function(){
			$('#card-body').hide();
			$('#password-change').hide();
			$('#address-list').hide();
			$('#display-order').show();
		});
		$('.cancel_order').on('click', function(){
			var order_code = $(this).val();
			 $.ajax({
					url: '<%=request.getContextPath()%>/admin/cancel-order',
					type: 'POST',
					cache: false,
					data: {
							order_code: order_code,
						},
					success: function(data){
						$('.ajax-order').html(data);
					},
					error: function (){
						alert('Có lỗi xảy ra');
					},
				});
				return false;
		});
	</script>
          <!-- =====  PRODUCT TAB  END ===== -->
 
    <!-- =====  CONTAINER END  ===== -->
    <%@ include file="/templates/public/inc/footer.jsp" %>
  <a id="scrollup"></a>
   </div>
</body>

</html>