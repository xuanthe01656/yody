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
          <!-- =====  SUB BANNER END  ===== -->
    <!-- =====  CONTAINER START  ===== -->
<section class="main-cart-page main-container col1-layout 11 ajax-cart1">
	<%
		int msg = 0;
	String order_code = "";
		if(request.getParameter("msg")!=null){
			msg = Integer.parseInt(request.getParameter("msg"));
			order_code = request.getParameter("order");
		}
		if(msg==1){
			%>
		       <div style="text-align: center; padding-bottom: 20px;">
		       		<span style="background-color: yellow; color: green;">Bạn đã đặt hàng thành công với mã đơn là: <%=order_code %> . Sau 30-60 phút chúng tôi sẽ liên hệ lại để hoàn tất quá trình đăt hàng và giao hàng. Xin cảm ơn quý khách đã tin tưởng và đặt hàng ở shop chúng tôi!!!</span>
		       		<form action="<%=request.getContextPath()%>/public/list-order" method="get">
		       			<input type="text" name="order_code" value="<%=order_code%>" style="display: none;">
		       			<button id="check-my-order">Xem đơn hàng của bạn</button>
		       		</form>
		       		
		       </div>
		       
		    <%
		}
		%>
		<%
		if(session.getAttribute("listPro1")!=null){
			listPro = (ArrayList<ProductAdminDetail>) session.getAttribute("listPro1");
			float sum_price1 = 0; 
		%>
	<div class="main container cartpcstyle">
		<div class="wrap_background_aside margin-bottom-40">
			<div class="cart-page">
				<div class="drawer__inner">
					<div class="CartPageContainer">
	<form action="<%=request.getContextPath() %>/public/checkout-page" method="post" class="cart cartpage container">
		<div class="row1">
			<div class="col-12 col-xl-8 order-1 order-xl-1">
				<div class="ajaxcart__inner ajaxcart__inner--has-fixed-footer cart_body items">
					<p class="title"><span class="text-uppercase">Giỏ hàng</span><span class="total-cart">(<span class="count_item_pr"><%=listPro.size() %></span>) Sản phẩm<span></span></span></p>
					<div class="cart-header-info d-none d-md-flex">
						<div>Sản phẩm</div><div>Đơn giá</div><div>Số lượng</div><div>Tổng tiền</div>
					</div>				
					<div class="items-available">
						<div class="ajaxcart__row cart-item  ">
							<%
							if(listPro.size()>0){
								for(ProductAdminDetail objPr: listPro){
									sum_price1 =(int) (sum_price1+(objPr.getPrice()*objPr.getAmount()));
							%>
							<div class="ajaxcart__product cart_product" data-line="1">
								<a href="#" class="ajaxcart__product-image cart_image" title="<%=objPr.getName()%>"><img src="<%=request.getContextPath() %>/templates/admin/assets/images/table/product/<%=objPr.getImages()%>" alt="<%=objPr.getName()%>"></a>
								<div class="grid__item cart_info">
									<div class="ajaxcart__product-name-wrapper cart_name">
										<a href="#" class="ajaxcart__product-name h4" title="<%=objPr.getName()%>"><%=objPr.getName()%></a>
										<span class="ajaxcart__product-meta variant-title d-none d-md-block"><%=objPr.getColor() %> / <%=objPr.getSize() %></span>

									</div>
									<div class="grid grid-price-1">
										<div class="grid__item one-half text-center cart_prices">
												<span class="cart-price "><%=(int)objPr.getPrice() %>đ</span>
										</div>
									</div>
									<div class="grid grid-qty">
											<span class="ajaxcart__product-meta variant-title-mobile d-md-none"><%=objPr.getColor() %> / <%=objPr.getSize() %></span>
										<div class="grid__item one-half cart_select">
											<div class="ajaxcart__qty input-group-btn input-group2">
												<button type="button" class="ajaxcart__qty-adjust ajaxcart__qty--minus items-count btn-minus<%=objPr.getId() %>" data-id="" aria-label="-">
													-
												</button>
												<input type="text" name="amount" class="ajaxcart__qty-num number-sidebar edit_amount" data-id="<%=objPr.getId() %>" id="qty<%=objPr.getId() %>" value="<%=objPr.getAmount() %>" min="0" data-id="" type="number" >
												<button type="button" class="ajaxcart__qty-adjust ajaxcart__qty--plus items-count btn-plus<%=objPr.getId() %>" data-id=""  aria-label="+">
													+			
												</button>
											</div>
										</div>
									</div>
									<div class="grid grid-price grid-price-2">
										<p class="remove-cart"><a class="cart__btn-remove remove-item-cart ajaxifyCart--remove" id="del-cart<%=objPr.getId()%>"  href="javascript: void(0)" data-valuecart="<%=objPr.getId()%>" ></a></p>
									</div>
									</div>
									<script>
									$('.btn-plus<%=objPr.getId() %>').on('click', function(e) {
											var num = parseInt($('#qty<%=objPr.getId() %>').val());
												var newNum = num+1;
												$('#qty<%=objPr.getId() %>').val(newNum);
										});
									$('.btn-minus<%=objPr.getId() %>').on('click', function(e) {
										var num = parseInt($('#qty<%=objPr.getId() %>').val());
										if(num>=1){
											var newNum = num-1;
											$('#qty<%=objPr.getId() %>').val(newNum);
										}
									});
										$(function() {
										    $('#del-cart<%=objPr.getId()%>').on('click', function() {
										        var changedField = $(this);
										        var id = $(this).data("valuecart");
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
										    });
										    $('.edit_amount').on('change', function() {
										        var changedField = $(this);
										        var id = $(this).data("id");
										        var qty = $(this).val();
										        $.ajax({
													url: '<%=request.getContextPath()%>/public/cart',
													type: 'POST',
													cache: false,
													data: {
														'id-product': id,
														'amount-edit':qty,
														},
													success: function(data){
														$('.ajax-cart1').html(data);
													},
													error: function (){
														alert('Có lỗi xảy ra');
													}
												});
										        $.ajax({
													url: '<%=request.getContextPath()%>/public/handel-cart',
													type: 'POST',
													cache: false,
													data: {
														'id-product': id,
														'amount-edit':qty, },
													success: function(data){
														$('.ajax-cart').html(data);
													},
													error: function (){
														alert('Có lỗi xảy ra');
													}
												});
										    });
										});
								</script>
								</div>
								<%} %>
							</div>
					</div>
				</div>	
			</div>
			<div class="col-12 col-xl-4 order-3 order-xl-2 ajaxcart__wrap-footer">
				<div class="ajaxcart__footer ajaxcart__footer--fixed cart-footer">
					<div class="ajaxcart__subtotal">
						<div class="cart__subtotal">
							<div class="cart__col-6">Tổng đơn hàng (Tạm tính) :</div>
							<div class="text-right cart__totle"><span class="total-price"><%=(int)sum_price1 %></span></div>
						</div>
					</div>
					<div class="cart__btn-proceed-checkout-dt">
						<button type="submit" class="button btn btn-default cart__btn-proceed-checkout" id="btn-proceed-checkout" title="Thanh toán"><span class="text-payment">Đặt Hàng</span> (<span class="count_item_pr"><%=listPro.size() %></span>)</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	
<%}else{
	%>
		<p>Không có đơn hàng hãy đặt hàng</p>
	<%
}}else{
	%>
	<div class="main container cartpcstyle">
		<div class="wrap_background_aside margin-bottom-40">
			<div class="cart-page">
				<div class="drawer__inner">
					<div class="CartPageContainer">
						<form action="<%=request.getContextPath() %>/public/list-order" method="post" class="cart cartpage container">
							<div class="row1">
								<div class="col-12 col-xl-8 order-1 order-xl-1">
									<div class="ajaxcart__inner ajaxcart__inner--has-fixed-footer cart_body items">
										<p class="title"><span class="text-uppercase">Giỏ hàng</span><span class="total-cart">(<span class="count_item_pr">0</span>) Sản phẩm<span></span></span></p>
										<div class="cart-header-info d-none d-md-flex">
											<div>Sản phẩm</div><div>Đơn giá</div><div>Số lượng</div><div>Tổng tiền</div>
										</div>				
										<div class="items-available">
											<div class="ajaxcart__row cart-item  ">
												<p>Không có đơn hàng hãy đặt hàng</p>
											</div>
										</div>
									</div>
								</div>
								<div class="col-12 col-xl-4 order-3 order-xl-2 ajaxcart__wrap-footer">
									<div class="ajaxcart__footer ajaxcart__footer--fixed cart-footer">
										<div class="ajaxcart__subtotal">
											<div class="cart__subtotal">
												<div class="cart__col-6">Tra cứu đơn hàng: </div>
												<div class="text-right cart__totle"><input type="text" name="order_code" value="" required="required"></div>
											</div>
										</div>
										<div class="cart__btn-proceed-checkout-dt">
											<button type="submit" class="button btn btn-default cart__btn-proceed-checkout" id="btn-proceed-checkout" title="Thanh toán"><span class="text-payment">Tra Cứu</span></button>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<%
} %>
	<script>
		function getRndInteger(min, max) {
			  return Math.floor(Math.random() * (max - min + 1) ) + min;
			}
		if(!localStorage.getItem("count")) {
			localStorage.setItem("count", getRndInteger(80,150))
		}
		var x = localStorage.getItem("count")
		$("#count-customer").html(x);
	</script>
			</div>
				</div>
			</div>
		</div>
	</div>
</section>
          <!-- =====  PRODUCT TAB  END ===== -->
 
    <!-- =====  CONTAINER END  ===== -->
    <%@ include file="/templates/public/inc/footer.jsp" %>
  <a id="scrollup"></a>
   </div>
</body>

</html>