


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
      <div class="iq-comingsoon pt-5">
            <div class="container-fluid">
               <div class="row justify-content-center">
                  <div class="col-sm-8 text-center">
                     <div class="iq-comingsoon-info">
                        <a href="index.jsp">
                        <img src="<%=request.getContextPath() %>/templates/admin/assets/images/logo.svg" class="img-fluid w-25" alt="">
                        </a>
                        <h2 class="mt-4 mb-1">Stay tunned, we're launching very soon</h2>
                        <p>We are working very hard to give you the best experience possible!</p>
                        <ul class="countdown d-flex align-items-center list-inline" data-date="Feb 02 2022 20:20:22">
                          <li class="col-md-6 col-lg-3">
                            <div class="card">
                              <div class="card-body">
                                <span data-days>0</span>Days
                              </div>
                            </div>
                          </li>
                          <li class="col-md-6 col-lg-3">
                            <div class="card">
                              <div class="card-body">
                                <span data-hours>0</span>Hours
                              </div>
                            </div>
                          </li>
                          <li class="col-md-6 col-lg-3">
                            <div class="card">
                              <div class="card-body">
                                <span data-minutes>0</span>Minutes
                              </div>
                            </div>
                          </li>
                          <li class="col-md-6 col-lg-3">
                            <div class="card">
                              <div class="card-body">
                                <span data-seconds>0</span>Seconds
                              </div>
                            </div>
                          </li>
                        </ul>
                     </div>
                  </div>
               </div>
               <div class="row justify-content-center">
                  <div class="col-lg-4">
                     <form class="iq-comingsoon-form mt-5">
                        <div class="form-group">
                           <input type="email" class="form-control comming mb-0" id="exampleInputEmail1"  placeholder="Enter email address">
                           <button type="submit" class="btn btn-primary">Subscribe</button>
                        </div>
                     </form>
                  </div>
               </div>
            </div>
         </div>
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
      <script src="<%=request.getContextPath() %>/templates/admin/assets/js/countdown.js"></script>
  </body>
</html>