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
<%@ include file="/templates/public/inc/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script
	src="<%=request.getContextPath() %>/templates/public/assets/js/owl.carousel.min.js"></script>
<script
	src="<%=request.getContextPath() %>/templates/public/assets/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath() %>/templates/public/assets/js/jquery.magnific-popup.js"></script>
<script
	src="<%=request.getContextPath() %>/templates/public/assets/js/jquery.firstVisitPopup.js"></script>
<script
	src="<%=request.getContextPath() %>/templates/public/assets/js/custom.js"></script>
<!-- =====  SUB BANNER  STRAT ===== -->

<div class="page-content-account">
	<div class="container">
		<div class="d-group">
			<div class="left-col">
				<div class="group-login group-log">
				<h1 class="d-none d-md-block"><span>ĐĂNG</span> NHẬP</h1>
				<div class="acc-top-mb d-block d-md-none">
				<span style="display: block; margin-bottom: 48px; color: #595959; font-weight: 400;">Chào mừng bạn đến với Yody!</span>
				<h1><span>ĐĂNG</span> NHẬP</h1>
				<%
			                	if(request.getParameter("msg")!=null){
			                		int msg = Integer.parseInt(request.getParameter("msg"));
			                		if(msg==1){
			                			%>
			                			<p class="error">Sai Email hoặc mật khẩu!!<a href="<%=request.getContextPath() %>/resetpassword" Style="color: green;">Quên mật khẩu?</a><p>
			                			<%
			                		}else{
			                			if(msg==2){
				                			%>
				                			<p class="error">Tài khoản không tồn tại !!. <a href="<%=request.getContextPath() %>/register" Style="color: green;">Tạo tài khoản?</a><p>
				                			<%
				                		}
			                		}
			                	}
			                %>
				</div>
				<form method="post" action="<%=request.getContextPath() %>/auth/public/login" id="customer_login" accept-charset="UTF-8"><input name="FormType" type="hidden" value="customer_login"><input name="utf8" type="hidden" value="true">
					<p id="errorData">
					</p>
					<fieldset class="form-group">
						<input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,63}$" class="form-control form-control-lg" value="" name="email" id="customer_email" placeholder="Email" oninput="inputFunctionEmail()">
						<span id="errorEmailText" class="errorEmailText"></span>
					</fieldset>
					<fieldset class="form-group">
						<input type="password" class="form-control form-control-lg" value="" name="password" id="customer_password" placeholder="Mật khẩu" oninput="inputFunctionPass()">
						<span class="showpass"></span>
						<span id="errorPassText" class="errorPassText"></span>
					</fieldset>
					<button class="btn-login btn-login-form" type="submit" value="Đăng nhập" style="width:100%;margin-bottom:16px">Đăng nhập</button>
					<p class="d-block d-md-none forgot-mb"><a href="#" onclick="showRecoverPasswordForm();return false;" style="font-weight: 600">Quên mật khẩu</a></p>
				</form>
				<p class="forgot"><a class="d-none d-md-block" href="#" onclick="showRecoverPasswordForm();return false;">Quên mật khẩu</a></p>
				<p class="loginOr"><span>Hoặc đăng nhập bằng</span></p>
				<div class="block social-login--facebooks">
					<!--<script>function loginFacebook(){var a={client_id:"947410958642584",redirect_uri:"https://store.mysapo.net/account/facebook_account_callback",state:JSON.stringify({redirect_url:window.location.href}),scope:"email",response_type:"code"},b="https://www.facebook.com/v3.2/dialog/oauth"+encodeURIParams(a,!0);window.location.href=b}function loginGoogle(){var a={client_id:"997675985899-pu3vhvc2rngfcuqgh5ddgt7mpibgrasr.apps.googleusercontent.com",redirect_uri:"https://store.mysapo.net/account/google_account_callback",scope:"email profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile",access_type:"online",state:JSON.stringify({redirect_url:window.location.href}),response_type:"code"},b="https://accounts.google.com/o/oauth2/v2/auth"+encodeURIParams(a,!0);window.location.href=b}function encodeURIParams(a,b){var c=[];for(var d in a)if(a.hasOwnProperty(d)){var e=a[d];null!=e&&c.push(encodeURIComponent(d)+"="+encodeURIComponent(e))}return 0==c.length?"":(b?"?":"")+c.join("&")}</script>-->
					<div class="d-flex justify-content-center page-signup-social-wrapper">
						<div class="page-signup-social">
							<a href="https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost:8082/yody/auth/public/login-google&response_type=code&client_id=472038623800-qq9ac8me6to17ur356s14e3due4sqpmi.apps.googleusercontent.com&approval_prompt=force" class="social-login--google"><img width="129px" height="37px" alt="google-login-button" src="//bizweb.dktcdn.net/100/438/408/themes/904142/assets/ic_btn_google.svg?1681726130231" class="bg-white" style="height: 48px; width: 120px; border: 1px solid rgb(240, 240, 240); border-radius: 500px;"></a>
						</div>
						<div class="page-signup-social">
							<a href="javascript:void(0)" class="social-login--facebook" onclick="loginFacebook()"><img width="129px" height="37px" alt="facebook-login-button" src="//bizweb.dktcdn.net/100/438/408/themes/904142/assets/ic_btn_facebook.svg?1681726130231" class="bg-white" style="height: 48px; width: 120px; border: 1px solid rgb(240, 240, 240); border-radius: 500px;"></a>
						</div>
					</div>
				</div>
				<div class="register">
					Bạn chưa có tài khoản? <a href="<%=request.getContextPath() %>/auth/public/sign-up">Đăng ký ngay!</a>
				</div>
				</div>
				<!-- <div class="group-login group-recover d-none">
					<h2>QUÊN MẬT KHẨU</h2>
					<p class="description">Nếu bạn quên mật khẩu, vui lòng nhập địa chỉ email đã đăng ký của bạn. Chúng tôi sẽ gửi cho bạn một liên kết để đặt lại mật khẩu.</p>
					<form method="post" action="/account/recover" id="recover_customer_password" accept-charset="UTF-8"><input name="FormType" type="hidden" value="recover_customer_password"><input name="utf8" type="hidden" value="true">
						<fieldset class="form-group">
						<input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,63}$" class="form-control form-control-lg" value="" name="Email" id="recover-email" placeholder="Nhập email" required="">
						</fieldset>
						<a href="#" class="btn-ref" onclick="hideRecoverPasswordForm();return false;">HỦY</a>
						<input class="btn-login" type="submit" value="TIẾP TỤC">
					</form>
				</div> -->
			</div>
		</div>
	</div>
</div>
<!-- =====  PRODUCT TAB  END ===== -->

<!-- =====  CONTAINER END  ===== -->
<%@ include file="/templates/public/inc/footer.jsp"%>
<a id="scrollup"></a>
</div>
</body>

</html>