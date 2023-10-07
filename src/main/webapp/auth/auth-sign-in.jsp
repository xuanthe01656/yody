<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <title>YODY | Admin Dashboard</title>
      
      <!-- Favicon -->
      <link rel="shortcut icon" href="<%=request.getContextPath() %>/templates/admin/assets/images/favicon.png" />
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/css/backend-plugin.min.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/css/backend.css?v=1.0.0">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/vendor/@fortawesome/fontawesome-free/css/all.min.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/vendor/line-awesome/dist/line-awesome/css/line-awesome.min.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/vendor/remixicon/fonts/remixicon.css">  </head>
  <body class=" ">
    <!-- loader Start -->
    <div id="loading">
          <div id="loading-center">
          </div>
    </div>
    <!-- loader END -->
    
      <div class="wrapper">
      <section class="login-content">
         <div class="container">
            <div class="row align-items-center justify-content-center height-self-center">
               <div class="col-lg-8">
                  <div class="card auth-card">
                     <div class="card-body p-0">
                        <div class="d-flex align-items-center auth-content">
                           <div class="col-lg-7 align-self-center">
                              <div class="p-3">
                                 <h2 class="mb-2">Sign In</h2>
                                 <p>Login to stay connected.</p>
                                  <%
			                	if(request.getParameter("msg")!=null){
			                		int msg = Integer.parseInt(request.getParameter("msg"));
			                		if(msg==1){
			                			%>
			                			<p class="error">Sai tên đăng nhập hoặc mật khẩu!!<a href="<%=request.getContextPath() %>/resetpassword" Style="color: green;">Quên mật khẩu?</a><p>
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
                                 <form action="<%=request.getContextPath() %>/auth/login" method="post">
                                    <div class="row">
                                       <div class="col-lg-12">
                                          <div class="floating-label form-group">
                                             <input class="floating-input form-control" name="username" type="text">
                                             <label>Username</label>
                                          </div>
                                       </div>
                                       <div class="col-lg-12">
                                          <div class="floating-label form-group">
                                             <input class="floating-input form-control" name="password" type="password">
                                             <label>Password</label>
                                          </div>
                                       </div>
                                       <div class="col-lg-6">
                                          <div class="custom-control custom-checkbox mb-3">
                                             <input type="checkbox" class="custom-control-input" id="customCheck1">
                                             <label class="custom-control-label control-label-1" for="customCheck1">Remember Me</label>
                                          </div>
                                       </div>
                                       <div class="col-lg-6">
                                          <a href="<%=request.getContextPath() %>/auth/recoverpwad" class="text-primary float-right">Forgot Password?</a>
                                       </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Sign In</button>
                                    <p class="mt-3">
                                       Create an Account <a href="<%=request.getContextPath() %>/auth/auth-sign-up" class="text-primary">Sign Up</a>
                                    </p>
                                 </form>
                              </div>
                           </div>
                           <div class="col-lg-5 content-right">
                              <img src="<%=request.getContextPath() %>/templates/admin/assets/images/login/01.png" class="img-fluid image-right" alt="">
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </section>
      </div>
    
    <!-- Backend Bundle JavaScript -->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/backend-bundle.min.js"></script>
    
    <!-- Table Treeview JavaScript -->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/table-treeview.js"></script>
    
    <!-- Chart Custom JavaScript -->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/customizer.js"></script>
    
    <!-- Chart Custom JavaScript -->
    <script async src="<%=request.getContextPath() %>/templates/admin/assets/js/chart-custom.js"></script>
    
    <!-- app JavaScript -->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/app.js"></script>
  </body>
</html>