<%@page import="model.dao.AddressDAO"%>
<%@page import="model.bean.Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>
    <!-- =====  CONTAINER START  ===== -->
    <div data-tg-refresh="checkout" id="checkout" class="content" data-select2-id="select2-data-checkout">
    	<%
			if(session.getAttribute("listPro1")!=null){
				 listPro = (ArrayList<ProductAdminDetail>) session.getAttribute("listPro1");
				double sum_price1 = 0.0; 
			%>
		<form id="form-checkout" class="form-checkout" method="post" action="<%=request.getContextPath() %>/public/order" data-select2-id="select2-data-checkoutForm">
			<div class="wrap" >
				<main class="main">
					<div class="row1">
					
						<div class="col-sm-8">
							<header class="main__header">
								<div class="logo logo--center">
									<a href="/">
										<img class="logo__image  logo__image--small " alt="YODY - Mặc Mỗi Ngày, Thoải Mái Mỗi Ngày" src="//bizweb.dktcdn.net/100/438/408/themes/904142/assets/checkout_logo.png?1682069657430">
									</a>
								</div>
							</header>
							<%
								int msg = 0;
								if(request.getParameter("msg")!=null){
									msg = Integer.parseInt(request.getParameter("msg"));
								}
								if(msg==2){
									%>
		                			<div><span style="background-color: yellow; color: red;">Có lỗi trong quá trình xử lý! Xin vui lòng thử lại.</span></div>
		                			<%
								}
							%>
							<div class="row1">
							<%
								if(session.getAttribute("objU")==null){
									
							%>
								<div class="col-sm-6">
								<section class="col-sm-12">
								
									<div class="form-group mt_20 layout-flex">
										<h4 >
											<label>Thông tin giao hàng</label>
										</h4>
										
										<a href="<%=request.getContextPath()%>/auth/public/login">
											<i class="fa fa-user-circle-o fa-lg"></i>
											<span>Đăng nhập </span>
										</a>
										
									</div>
									<div class="form-group">
										<div class="col-sm-12">
											<label for="fullname" class="label_field">Họ và tên</label>
										</div>
									    <input type="text" class="form-control field__input" name="fullname" id="fullname" aria-describedby="textHelp" placeholder="">
									 </div>
									 <div class="form-group input-group row1 mb-3">
									 	<div class="col-sm-10 input_text">
									 		<label for="phone2" class="label_field">Số điện thoại</label>
									  		<input type="text" id="phone2" name="phone" class="form-control bfh-phone field__input" data-country="countries_phone1" placeholder="">
  										</div>
									  	<div class="col-sm-2 input_text">
									  		<select id="phone" class="field__input"></select>	
									  	</div>	
									 </div>
									 <div class="form-group">
									 	<div class="col-sm-12">
									 		<label for="housenumber_street" class="label_field">Địa chỉ <small>(số nhà và tên đường)</small></label>
									 	</div>
									    <input type="text" class="form-control field__input" name="address" id="housenumber_street" aria-describedby="textHelp" placeholder="">
									 </div>
									 <div class="form-group">
									 	<span id="error-province" class="error"></span>
									 	<div class="col-sm-12">
									    	<label for="province" class="label_field">Tỉnh thành</label>
									 	</div>
									    <select class="form-control field__input" name="province" id="province">
									    	<option value>--Chọn tỉnh thành--</option>
									    	<%
									    		AddressDAO objAddDAO = new AddressDAO();
									    		ArrayList<Address> listProvince = objAddDAO.getProvinces();
									    			if(listProvince.size()>0){
									    				for(Address objProvince: listProvince){%>
									    					<option value="<%=objProvince.getName() %>" data-code="<%=objProvince.getCode() %>"><%=objProvince.getName() %></option>
									    					<%
									    				}
									    			}
									    	%>
									    </select>
									 </div>
									 <div class="form-group">
									 	<div class="col-sm-12">
									 		<label for="districts" class="label_field">Quận huyện</label>
									 	</div>
									 	
									 		<select class="form-control field__input ajax-districts" name="districts" id="districts">
									    		<option value="">--</option>
									    	</select>
									 	
									    
									 </div>
									 <div class="form-group">
									    <div class="col-sm-12">
									    	<label for="wards" class="label_field">Phường xã</label>
									    </div>
									    	<select class="form-control field__input ajax-wards" name="wards" id="wards">
									    		<option value="">--</option>
									    	</select>
									 </div>
								</section>
								</div>
								<%}else{
									User objU = (User) session.getAttribute("objU");
									%>
								<div class="col-sm-6">
									<section class="col-sm-12">
									<div class="form-group mt_20 layout-flex">
										<h4 >
											<label>Thông tin giao hàng</label>
										</h4>
									</div>
									<div class="form-group">
										<div class="col-sm-12">
											<label for="fullname" class="label_field">Họ và tên</label>
										</div>
									    <input type="text" class="form-control field__input" name="fullname" value="<%=objU.getFullname() %>" id="fullname" aria-describedby="textHelp" placeholder="">
									 </div>
									 <div class="form-group input-group row1 mb-3">
									 	<div class="col-sm-10 input_text">
									 		<label for="phone2" class="label_field">Số điện thoại</label>
									  		<input type="text" id="phone2" name="phone" class="form-control bfh-phone field__input" value="<%=objU.getPhone() %>" data-country="countries_phone1" placeholder="">
  										</div>
									  	<div class="col-sm-2 input_text">
									  		<select id="phone" class="field__input"></select>	
									  	</div>	
									 </div>
									 <div class="form-group">
									 	<div class="col-sm-12">
									 		<label for="housenumber_street" class="label_field">Địa chỉ <small>(số nhà và tên đường)</small></label>
									 	</div>
									    <input type="text" class="form-control field__input" name="address" value="<%if(objU.getAddress()!=null){out.print(objU.getAddress());} %>" id="housenumber_street" aria-describedby="textHelp" placeholder="">
									 </div>
									 <div class="form-group">
									 	<span id="error-province" class="error"></span>
									 	<div class="col-sm-12">
									    	<label for="province" class="label_field">Tỉnh thành</label>
									 	</div>
									    <select class="form-control field__input" name="province" id="province">
									    	<option value>--Chọn tỉnh thành--</option>
									    	<%
									    		AddressDAO objAddDAO = new AddressDAO();
									    		ArrayList<Address> listProvince = objAddDAO.getProvinces();
									    			if(listProvince.size()>0){
									    				for(Address objProvince: listProvince){%>
									    					<option value="<%=objProvince.getName() %>" data-code="<%=objProvince.getCode() %>"><%=objProvince.getName() %></option>
									    					<%
									    				}
									    			}
									    	%>
									    </select>
									 </div>
									 <div class="form-group">
									 	<div class="col-sm-12">
									 		<label for="districts" class="label_field">Quận huyện</label>
									 	</div>
									 		<select class="form-control field__input ajax-districts" name="districts" id="districts">
									    		<option value="">--</option>
									    	</select>
									 </div>
									 <div class="form-group">
									    <div class="col-sm-12">
									    	<label for="wards" class="label_field">Phường xã</label>
									    </div>
									    	<select class="form-control field__input ajax-wards" name="wards" id="wards">
									    		<option value="">--</option>
									    	</select>
									 </div>
								</section>
								</div>
									<%
								} %>
								<div class="col-sm-6">
									<section class="col-sm-12">
									<div class="form-group mt_20">
										<h4 >
											<label>Giảm Giá</label>
										</h4>
									</div>
									<div class="ajax-message-display">
									</div>
									 <div class="form-group layout-flex">
									 	<div class="col-sm-9 style-discount">
									 		<label for="discount" class="label_field">Nhập mã giảm giá <small>(Nếu có)</small></label>
									 		<input type="text" class="form-control field__input" name="discount" value="" id="discount" aria-describedby="textHelp" placeholder="">
									 	</div>
									    <input type="button" class="col-sm-3" id="apply_discount" disabled value="Áp Dụng"/>
									 </div>
								</section>
								<section class="col-sm-12">
									<div class="form-group mt_20">
										<h4 >
											<label>Vận Chuyển</label>
										</h4>
									</div>
									<div class="ajax-transport-fee">
										
									</div>
								</section>
								<section class="col-sm-12">
									<div class="form-group mt_20">
										<h4 >
											<label>Thanh Toán</label>
										</h4>
									</div>
									<label for="payment" class="error"></label>
									<span id="error-payment" class="error"></span>
									<div class="form-check check-form form-control field__input">
										<div class="radio__input">
											<input class="form-check-input" type="radio" value="paypal" name="payment" id="payment-paypal">
										</div>
  										<label class="form-check-label" for="payment-paypal">
    										Thanh toán qua Paypal
    										<br><small style="color: red; font-style: italic;">Chú Ý: Không cần nhập thông tin thanh toán. Chỉ cần chọn tỉnh thành.</small>
    									</label>
  										<label class="form-check-label" for="payment">
    										<i class="payment-icon payment-icon--paypal"></i>
  										</label>
									</div>
									<div class="form-check check-form form-control field__input mt_10 mb_10">
										<div class="radio__input">
											<input class="form-check-input" type="radio" value="COD" name="payment" id="payment-cod">
										</div>
  										<label class="form-check-label" for="payment-cod">
    										Ship COD:
    										<br><small style="color: red; font-style: italic;">Chú Ý: nhập thông tin giao hàng phải chính xác để việc giao hàng không bị gián đoạn.</small>
  										</label>
									</div>
								</section>
							</div>
							<hr>
							<div class="col-sm-12">
								<div class="main__footer unprintable">
									<p>Sau khi <b>hoàn tất đơn hàng</b> khoảng 60-90 phút (trong giờ hành chính), YODY sẽ nhanh chóng gọi điện xác nhận lại thời gian giao hàng với bạn. YODY xin cảm ơn!</p>
								</div>
							</div>
						</div>
						</div>
						<div class="col-sm-4 header_sidebar">
							<aside class="sidebar">
								<div class="mtblr_20">
									<h3 class="sidebar__title">
										Đơn hàng (<%=listPro.size() %> sản phẩm)
									</h3>
								</div>
								<div class="sidebar__content">
									<div id="order-summary" class="order-summary order-summary--is-collapsed">
										<div class="order-summary__sections">
											<div class="" style="overflow: scroll;max-height: 180px;">
												<table class="product-table">
													<caption class="visually-hidden">Chi tiết đơn hàng</caption>
													<thead class="product-table__header">
														<tr>
															<th>
																<span class="visually">Ảnh sản phẩm</span>
															</th>
															<th>
																<span class="visually">Mô tả</span>
															</th>
															<th>
																<span class="visually">Sổ lượng</span>
															</th>
															<th>
																<span class="visually">Đơn giá</span>
															</th>
														</tr>
													</thead>
													<tbody>
														<%
															if(listPro.size()>0){
																for(ProductAdminDetail objPD: listPro){
																	sum_price1 = sum_price1+(objPD.getAmount()*objPD.getPrice());
														%>
														<tr class="product">
															<td class="product__image">
																<div class="product-thumbnail">
																	<div class="product-thumbnail__wrapper" data-tg-static="">
																		<img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=objPD.getImages()%>" alt="<%=objPD.getName()%>" class="product-thumbnail__image">
																	</div>
																</div>
															</td>
															<td class="product__description">
																<span class="product__description__name">
																	<%=objPD.getName() %>
																</span>
																
																<span class="product__description__property">
																	<%=objPD.getColor() %> / <%=objPD.getSize() %>
																</span>
																
																
															</td>
															<td class="product__quantity visually-hidden"> <%=objPD.getAmount() %></td>
															<td class="product__price"><%=(int)objPD.getPrice() %></td>
														</tr>
														<%}} %>
													</tbody>
												</table>
											</div>
											
											<div class="order-summary__section order-summary__section--total-lines order-summary--collapse-element" data-define="{subTotalPriceText: '299.000đ'}" data-tg-refresh="refreshOrderTotalPrice" id="orderSummary">
												<table class="total-line-table">
													<caption class="visually-hidden">Tổng giá trị</caption>
													<thead>
														<tr>
															<td><span class="visually-hidden">Mô tả</span></td>
															<td><span class="visually-hidden">Giá tiền</span></td>
														</tr>
													</thead>
													<tbody class="total-line-table__tbody">
														<tr class="total-line total-line--subtotal">
															<th class="total-line__name">
																Tạm tính
															</th>
															<td class="total-line__price" id="sum-price"><%=(int)sum_price1 %> đ</td>
														</tr>
														<tr class="total-line total-line--subtotal">
															<th class="total-line__name">
																Giảm giá
															</th>
															<td class="total-line__price ajax-discount-display">
																<div id="discount_line">0 đ</div>
															</td>
														</tr>
														<tr class="total-line total-line--shipping-fee">
															<th class="total-line__name">
																Phí vận chuyển
															</th>
															<td class="total-line__price" id="transport-fee" data-bind="getTextShippingPrice()">20000đ</td>
														</tr>
														
													</tbody>
													<tfoot class="total-line-table__footer">
														<tr class="total-line payment-due">
															<th class="total-line__name">
																<span class="payment-due__label-total">
																	Tổng cộng
																</span>
															</th>
															<td class="total-line__price">
																<span class="payment-due__price" id="total" data-bind="getTextTotalPrice()"></span><span> đ</span>
															</td>
														</tr>
													</tfoot>
												</table>
											</div>
											<div class="order-summary__nav field__input-btn-wrapper hide-on-mobile layout-flex--row-reverse">
						
												<a href="<%=request.getContextPath() %>/public/cart" class="previous-link">
													<i class="previous-link__arrow">❮</i>
													<span class="previous-link__content">Quay về giỏ hàng</span>
												</a>
												<div class="cart__btn-proceed-checkout-dt">
													<button type="button" id="normal-checkout" style="display: block;" class="button btn btn-default cart__btn-proceed-checkout" id="btn-proceed-checkout" title="Thanh toán"><span class="text-payment">Đặt hàng</span></button>
													<button type="button" id="paypal-checkout" style="display: none;" class="button btn btn-default cart__btn-proceed-checkout" id="btn-proceed-checkout" title="Thanh toán"><span class="text-payment">Đặt hàng</span></button>
													<button type="button" id="cod-checkout" style="display: none;" class="button btn btn-default cart__btn-proceed-checkout" id="btn-proceed-checkout" title="Thanh toán"><span class="text-payment">Đặt hàng</span></button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</aside>
						</div>
					</div>
					
				</main>
			</div>
		</form>
		<%} %>
		<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
			<symbol id="spinner">
				<svg viewBox="0 0 30 30">
					<circle stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-dasharray="85%" cx="50%" cy="50%" r="40%">
						<animateTransform attributeName="transform" type="rotate" from="0 15 15" to="360 15 15" dur="0.7s" repeatCount="indefinite"></animateTransform>
					</circle>
				</svg>
			</symbol>
		</svg>
	</div>
    <!-- =====  CONTAINER END  ===== -->
    <%@ include file="/templates/public/inc/footer.jsp" %>
    </div>
  <a id="scrollup"></a>
  <script type="text/javascript">
  $(document).ready(function() {
	  $('#form-checkout').validate({
			rules:{
				fullname:{
					required: true,
					minlength: 6,
				},
				phone:{
					required: true,
					minlength:10,
					maxlength: 12,
				},
				email:{
					required: true,
					email: true,
					minlength: 6,
				},
				address:{
					required: true,
					minlength: 6,
				},
				province:{
					required: true,
				},
				districts:{
					required: true,
				},
				wards:{
					required: true,
				},
				payment:{
					required: true,
				},
			},
			messages:{
				fullname:{
					required: 'Nhập vào tên.',
					minlength: 'Nhập tối thiểu 6 ký tự',
				},
				phone:{
					required: 'Nhập vào số điện thoại.',
					minlength: 'Nhập tối thiểu 10 ký tự.' ,
					maxlength: 'Nhập tối đa 12 ký tự.' ,
				},
				address:{
					required: 'Nhập vào địa chỉ.',
					minlength: 'Nhập tối thiểu 6 ký tự.',
				},
				email:{
					required: 'Nhập vào email.',
					email: 'Hãy nhập đúng định dạng.',
					minlength: 'Nhập tối thiểu 6 ký tự.',
				},
				province:{
					required: 'Chọn tỉnh thành.',
				},
				districts:{
					required: 'Chọn quận huyện.',
				},
				wards:{
					required: 'Chọn phường xã.',
				},
				payment:{
					required: 'Chọn phương thức thanh toán.',
				},
				
			},
		});
	  var phoneInputID = "#phone";
	  var input = document.querySelector(phoneInputID);
	  var iti = window.intlTelInput(input, {
	    formatOnDisplay: true,
	    geoIpLookup: function(callback) {
	       $.get("https://ipinfo.io", function() {}, "jsonp").always(function(resp) {
	         var countryCode = (resp && resp.country) ? resp.country : "";
	         callback(countryCode);
	       });
	    },
	    hiddenInput: "full_number",
	    initialCountry: "VN",
	    separateDialCode: true,
	    utilsScript: "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/11.0.14/js/utils.js"
	  });
	
  
  	$('#province').on('change', function(){
  		var changedField = $(this);
	  var code = $(this).find(':selected').data("code");
	  $.ajax({
			url: '<%=request.getContextPath()%>/address-lb',
			type: 'POST',
			cache: false,
			data: {province_code: code, },
			success: function(data){
				$('.ajax-districts').html(data);
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
  	});
  	
	$('#districts').on('change', function(){
		var changedField = $(this);
		 var code1 = $(this).find(':selected').data("code1");
		 
		 $.ajax({
				url: '<%=request.getContextPath()%>/address-lb',
				type: 'POST',
				cache: false,
				data: {district_code: code1, },
				success: function(data){
					$('.ajax-wards').html(data);
				},
				error: function (){
					alert('Có lỗi xảy ra');
				}
			});
			return false;
  });
	var payment =  "";
	$( "#normal-checkout" ).on( "click", function() {
		if($('input[name=payment]', '#form-checkout').is(':checked')==false){
			$( "#error-payment" ).text( "Chọn phương thức thanh toán!" ).show().fadeOut( 1000 );
			}
		} );
	
	$('#form-checkout input[name=payment]').on('change', function(){
		payment = $('input[name=payment]:checked', '#form-checkout').val();
		if(payment==="COD"){
			$( "#cod-checkout" ).show();
			$( "#normal-checkout" ).hide();
			$( "#paypal-checkout" ).hide();
		}
		if(payment==="paypal"){
			$( "#cod-checkout" ).hide();
			$( "#normal-checkout" ).hide();
			$( "#paypal-checkout" ).show();
			
		}
	});
	$( "#paypal-checkout" ).on( "click", function() {
		var province = $('#province').val();
		if(province!=""){
			$('#form-checkout input, select').each(function () {
          		 $(this).rules( "remove" );
			});
		  $( "#form-checkout" ).submit();
		
		}else{
			$( "#error-province" ).text( "Chọn tỉnh thành!" ).show().fadeOut( 1000 );
		}
	});
	$( "#cod-checkout" ).on( "click", function() {
		$("#form-checkout").valid();
		if($("#form-checkout").valid()==true){
		  $( "#form-checkout" ).trigger( "submit");
		}
		} );
 
  $('#province').on('change', function(){
			var province = $('#province').val();
			
			$.ajax({
				url: '<%=request.getContextPath()%>/public/order',
				type: 'POST',
				cache: false,
				data: {aprovince: province,},
				success: function(data){
					$('.ajax-transport-fee').html(data);
					var transport_fee =parseInt($('#input_transport_fee').val());
			  		$('#transport-fee').html(transport_fee+' đ');
			  		var price = parseInt($('#sum-price').text());
			  		var discount = parseInt($('#discount_line').text());
			  		var sum_price = transport_fee+price-discount;
			  		$('#total').html(sum_price);
					
				},
				error: function (){
					alert('Có lỗi xảy ra');
				}
			});
			return false;
	});
  	
  	var transportfee = parseInt($('#transport-fee').text());
	var price = parseInt($('#sum-price').text());
	var discount = parseInt($('#discount_line').text());
	var sum_price = transportfee+price-discount;

	$('#total').html(sum_price);
  });
  $(function() {
	    $('#discount').on('keyup', function() {
	    		$(this).val($(this).val().toUpperCase());
		        var changedField = $(this);
		        var discount_code=$('#discount').val();
		        $.ajax({
					url: '<%=request.getContextPath()%>/public/order',
					type: 'POST',
					cache: false,
					data: {
						adiscount_code: discount_code,
						},
					success: function(data){
						$('.ajax-message-display').html(data);
					},
					error: function (){
						alert('Có lỗi xảy ra');
					},
				});
				return false;
		});
	    $('#apply_discount').on('click', function(){
	    	if($('#apply_discount').prop('disabled')==false){
	    		$(this).val($(this).val().toUpperCase());
		        var changedField = $(this);
		        var discount_code=$('#discount').val();
		        $.ajax({
					url: '<%=request.getContextPath()%>/public/order',
					type: 'POST',
					cache: false,
					data: {
						discount_code_true: discount_code,
						},
					success: function(data){
						$('.ajax-discount-display').html(data);
					},
					error: function (){
						alert('Có lỗi xảy ra');
					},
				});
	    	}
	    });
	});
  </script>
</body>

</html>