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

<!-- =====  SUB BANNER  STRAT ===== -->

	<div class="page-content-account">
		<div class="container">
			<div class="d-group position-relative">
				<div id="signupLoading" class="d-none page-signup-loading justify-content-center align-items-center">
					<img width="100" height="100" src="//bizweb.dktcdn.net/100/438/408/themes/904142/assets/rolling.svg?1681788739635" alt="loading">
				</div>
				<div class="right-col">
					<div class="group-login group-log">
						<div class="mb-5 d-block page-signup-text">
						Chào mừng bạn đến với Yody!
						</div>
						<h1 class="d-block">
						<span class="page-signup-title page-signup-title-darkblue">ĐĂNG</span>
						<span class="page-signup-title page-signup-title-yellow">KÝ</span>
						</h1>
						<%
						if(request.getParameter("msg")!=null){
			        		int msg = Integer.parseInt(request.getParameter("msg"));
			        		if(msg==1){
			        			%>
			        			<div><span style="background-color: yellow; color: red;">Đăng ký thành công!</span></div>
			        			<script>
			        				 window.location="<%=request.getContextPath()%>/auth/public/login";
			        			</script>
			        			<%
			        		}else{
			        			if(msg==2){
			        			%>
			        			<div><span style="background-color: yellow; color: red;">Đăng ký thất bại thất bại!</span></div>
			        			<%
			        				}else{
			                			if(msg==3){
			                    			%>
			                    			<div><span style="background-color: yellow; color: red;">Email đã tồn tại!</span></div>
			                    			<%
			                    				}
			                    			}
			        			}
			        		}
						%>
						<form method="post" action="<%=request.getContextPath() %>/auth/public/sign-up" id="customer_register" accept-charset="UTF-8"><input name="FormType" type="hidden" value="customer_register"><input name="utf8" type="hidden" value="true"><input type="hidden" id="Token-93fc9631b14e44cc89c9aec42ea7e070" name="Token" value="03AKH6MRGc5u7ruJWROCp3NTTSQ-wrXe2J-oWWntq9dTRqXX35SDKpp3JoUfh4vJNScjXHReRrIZ8-eR6SF2wcaWnxn8Q3wSYQW6eLFG4QbzGf-UNLPI4Zza8AofLNjzdBrE1YSpkhUxpAGdQus7t-I6Fa8RivnoMQZ6bu-7-sNQDkOYKmTw5IlqkbWu9ITV_Yw9rjJDBSLvmx8zIT2B-Wh0SQlj2QwWzDG6G2wtskraKTeTKnvhHlGs8L6-gVoMc4WDtb9fXH7faRYwdkUqs2WbwuE1AUQN_viEiH390A7WCrHn0kzU3y7W8Mbu6u0JN3tZuwwaWT5673XA7EifWdCSDsWG4fbevHjKvcugi8w1ifa3GCq5jcz4kTm0uSqmIo6i6k25JyytL2fB5QzWNRIv1_hsSjl_byoptwyj8vO7ovEhY9DDw2pAlPMcQIV8RV9B5qDEhSJutJzMR2zkfzw-kYT6Aoh4ItqepXupZ6A-VX0wyKKqJXS5VjJatxkAAb7xvkJu5oaJ6Hdf0F06JIuYSL7Fd-sJ9mvSPYIr_00xUhkLg35FdDz3OsK44x82AOUsaL_urayNoTPtex1r04cDUYfzyzpYaT3Q"><script src="https://www.google.com/recaptcha/api.js?render=6Ldtu4IUAAAAAMQzG1gCw3wFlx_GytlZyLrXcsuK"></script><script>grecaptcha.ready(function() {grecaptcha.execute("6Ldtu4IUAAAAAMQzG1gCw3wFlx_GytlZyLrXcsuK", {action: "customer_register"}).then(function(token) {document.getElementById("Token-93fc9631b14e44cc89c9aec42ea7e070").value = token});});</script>
							<div id="responseErrors" class="d-none">
							</div>
							<div id="frmErrorText" class="error page-signup-error d-none flex-column justflex-columnify-content-center align-items-center flex-column">
							</div>
							<fieldset class="form-group">
								<input type="text" class="form-control form-control-lg mb-1" value="" name="fullname" id="full_name" placeholder="Họ và tên">
								<div class="page-signup-error px-1">
								</div>
							</fieldset>
							<fieldset class="form-group">
								<input placeholder="Số điện thoại" type="text" id="phone" class="form-control form-control-comment mb-1 form-control-lg" name="phone">
								<div class="page-signup-error px-1">
								</div>
							</fieldset>
							<fieldset class="form-group">
								<input type="text" class="form-control form-control-lg mb-1" value="" name="email" id="iptEmail" placeholder="Địa chỉ email">
								<div class="page-signup-error px-1">
								</div>
								<div class="ajax-data1"></div>
								<script type="text/javascript">
								$(function() {
								    $('#iptEmail').on('input', function() {
								        var changedField = $(this);
								        var email = $("#iptEmail").val();
								                    
								        $.ajax({
											url: '<%=request.getContextPath()%>/auth/public/email-handel',
											type: 'POST',
											cache: false,
											data: {aemail: email},
											success: function(data){
												$('.ajax-data1').html(data);
											},
											error: function (){
												alert('Có lỗi xảy ra');
											},
										});
										return false;
								    });
								});
								</script>
							</fieldset>
							<fieldset class="form-group">
								<input type="password" class="form-control form-control-lg mb-1" value="" name="password" id="password" placeholder="Mật khẩu">
								<span class="showpass"></span>
								<div class="page-signup-error px-1">
								</div>
							</fieldset>
							<div class="page-signup-actions">
								<button id="btnSubmit" class="btn-login btn-reg w-100" value="Đăng ký" type="submit">Đăng ký</button>
							</div>
							<div class="block social-login--facebooks d-block d-md-none">
								<div class="a-center page-signup-or">
									<div class="page-signup-or-item d-inline-block px-1 bg-white">Hoặc đăng ký bằng</div>
								</div>
								<script>function loginFacebook(){var a={client_id:"947410958642584",redirect_uri:"https://store.mysapo.net/account/facebook_account_callback",state:JSON.stringify({redirect_url:window.location.href}),scope:"email",response_type:"code"},b="https://www.facebook.com/v3.2/dialog/oauth"+encodeURIParams(a,!0);window.location.href=b}function loginGoogle(){var a={client_id:"997675985899-pu3vhvc2rngfcuqgh5ddgt7mpibgrasr.apps.googleusercontent.com",redirect_uri:"https://store.mysapo.net/account/google_account_callback",scope:"email profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile",access_type:"online",state:JSON.stringify({redirect_url:window.location.href}),response_type:"code"},b="https://accounts.google.com/o/oauth2/v2/auth"+encodeURIParams(a,!0);window.location.href=b}function encodeURIParams(a,b){var c=[];for(var d in a)if(a.hasOwnProperty(d)){var e=a[d];null!=e&&c.push(encodeURIComponent(d)+"="+encodeURIComponent(e))}return 0==c.length?"":(b?"?":"")+c.join("&")}</script>
								<div class="d-flex justify-content-center page-signup-social-wrapper">
									<div class="page-signup-social">
										<a href="javascript:void(0)" class="social-login--google" onclick="loginGoogle()"><img width="129px" height="37px" alt="google-login-button" src="//bizweb.dktcdn.net/100/438/408/themes/904142/assets/ic_btn_google.svg?1681788739635" class="bg-white" style="height: 48px; width: 120px; border: 1px solid rgb(240, 240, 240); border-radius: 500px;"></a>
									</div>
									<div class="page-signup-social">
										<a href="javascript:void(0)" class="social-login--facebook" onclick="loginFacebook()"><img width="129px" height="37px" alt="facebook-login-button" src="//bizweb.dktcdn.net/100/438/408/themes/904142/assets/ic_btn_facebook.svg?1681788739635" class="bg-white" style="height: 48px; width: 120px; border: 1px solid rgb(240, 240, 240); border-radius: 500px;"></a>
									</div>
								</div>
								<div class="page-signup-redirect-login page-signup-text">
									<span>Bạn đã có tài khoản?</span>
									<a href="<%=request.getContextPath()%>/auth/public/login ">Đăng nhập ngay!</a>
								</div>
							</div>
						</form>
					</div>
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