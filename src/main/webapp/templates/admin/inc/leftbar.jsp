<%@page import="model.bean.Admin"%>
<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/vendor/remixicon/fonts/remixicon.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/css/bootstrap/bootstrap.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/css/bootstrap/bootstrap.min.css.map">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/css/stylebackend.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/admin/assets/css/style_order.css">
      
      
 	 <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery/jquery-3.6.1.min.js"></script>
 	 <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-validation-1.19.5/dist/jquery.validate.min.js"></script>
 	 <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js" ></script>
 	 
     <!--<script src="<%=request.getContextPath() %>/templates/admin/assets/js/jQuery_v3.1.1.min.js"></script><script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>-->
    <script src="<%=request.getContextPath() %>/templates/public/assets/js/bootstrap.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js" ></script>
    
    
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/ckeditor/ckeditor.js"></script> 
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/ckeditor/config.js"></script> 
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.MultiFile.min.js"></script>
   	
     
    
     
   </head>
  <body class="  ">
    <!-- loader Start -->
    <div id="loading">
          <div id="loading-center">
          </div>
    </div>
    <!-- loader END -->
    <!-- Wrapper Start -->
    <div class="wrapper">
    <%
    	Admin objUAd = (Admin) session.getAttribute("objUAd");
    	
    %>
	<div class="iq-sidebar  sidebar-default ">
          <div class="iq-sidebar-logo d-flex align-items-center justify-content-between">
              <a href="<%=request.getContextPath() %>/admin/index" class="header-logo">
                  <img src="<%=request.getContextPath() %>/templates/admin/assets/images/logo.svg" class="img-fluid rounded-normal light-logo" alt="logo"><h5 class="logo-title light-logo ml-3"></h5>
              </a>
              <div class="iq-menu-bt-sidebar ml-0">
                  <i class="las la-bars wrapper-menu"></i>
              </div>
          </div>
          <div class="data-scrollbar" data-scroll="1">
              <nav class="iq-sidebar-menu">
                  <ul id="iq-sidebar-toggle" class="iq-menu">
                      <li class="active">
                          <a href="<%=request.getContextPath() %>/admin/index" class="svg-icon">                        
                              <svg  class="svg-icon" id="p-dash1" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <path d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z"></path><polyline points="3.27 6.96 12 12.01 20.73 6.96"></polyline><line x1="12" y1="22.08" x2="12" y2="12"></line>
                              </svg>
                              <span class="ml-4">Dashboards</span>
                          </a>
                      </li>
                      <li class=" ">
                          <a href="#product" class="collapsed" data-toggle="collapse" aria-expanded="false">
                              <svg class="svg-icon" id="p-dash2" width="20" height="20"  xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="9" cy="21" r="1"></circle><circle cx="20" cy="21" r="1"></circle>
                                  <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
                              </svg>
                              <span class="ml-4">Products</span>
                              <svg class="svg-icon iq-arrow-right arrow-active" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <polyline points="10 15 15 20 20 15"></polyline><path d="M4 4h7a4 4 0 0 1 4 4v12"></path>
                              </svg>
                          </a>
                          <ul id="product" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                              <li class="">
                                  <a href="<%=request.getContextPath() %>/admin/list-product">
                                      <i class="las la-minus"></i><span>List Product</span>
                                  </a>
                              </li>
                              <%
                                  	if(objUAd.getPosition_id()==1){
                                  %>
                              <li class="">
                                  <a href="<%=request.getContextPath() %>/admin/add-product">
                                      <i class="las la-minus"></i><span>Add Product</span>
                                  </a>
                              </li>
                              <%} %>
                          </ul>
                      </li>
                      <li class=" ">
                          <a href="#category" class="collapsed" data-toggle="collapse" aria-expanded="false">
                              <svg class="svg-icon" id="p-dash3" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
                                  <path d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path>
                              </svg>
                              <span class="ml-4">Categories</span>
                              <svg class="svg-icon iq-arrow-right arrow-active" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <polyline points="10 15 15 20 20 15"></polyline><path d="M4 4h7a4 4 0 0 1 4 4v12"></path>
                              </svg>
                          </a>
                          <ul id="category" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                          			<li class="">
                                          <a href="<%=request.getContextPath() %>/admin/list-categories">
                                              <i class="las la-minus"></i><span title="List Menu Categories">List Menu Categories</span>
                                          </a>
                                  </li>
                                  <%
                                  	if(objUAd.getPosition_id()==1){
                                  %>
                                  <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/add-cat">
                                              <i class="las la-minus"></i><span title="Add Menu">Add Category</span>
                                          </a>
                                  </li>
                       			<%} %>
                                  <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/list-cat-special">
                                              <i class="las la-minus"></i><span title="List Category Special">List Category Special</span>
                                          </a>
                                  </li>
                                  <%
                                  	if(objUAd.getPosition_id()==1){
                                  %>
                                  <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/add-categories-special">
                                              <i class="las la-minus"></i><span title="Add Menu Special">Add Category Special</span>
                                          </a>
                                  </li>
                                  <%} %>
                          </ul>
                      </li>
                      <li class=" ">
                          <a href="#order" class="collapsed" data-toggle="collapse" aria-expanded="false">
                              <svg class="svg-icon" id="p-dash4" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <path d="M21.21 15.89A10 10 0 1 1 8 2.83"></path><path d="M22 12A10 10 0 0 0 12 2v10z"></path>
                              </svg>
                              <span class="ml-4">Order</span>
                              <svg class="svg-icon iq-arrow-right arrow-active" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <polyline points="10 15 15 20 20 15"></polyline><path d="M4 4h7a4 4 0 0 1 4 4v12"></path>
                              </svg>
                          </a>
                          <ul id="order" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                                  <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/list-order">
                                              <i class="las la-minus"></i><span>List Order</span>
                                          </a>
                                  </li>
                                  
                          </ul>
                      </li>
                      <li class=" ">
                          <a href="#sale" class="collapsed" data-toggle="collapse" aria-expanded="false">
                              <svg class="svg-icon" id="p-dash4" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <path d="M21.21 15.89A10 10 0 1 1 8 2.83"></path><path d="M22 12A10 10 0 0 0 12 2v10z"></path>
                              </svg>
                              <span class="ml-4">Sale</span>
                              <svg class="svg-icon iq-arrow-right arrow-active" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <polyline points="10 15 15 20 20 15"></polyline><path d="M4 4h7a4 4 0 0 1 4 4v12"></path>
                              </svg>
                          </a>
                          <ul id="sale" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                                  <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/list-sale">
                                              <i class="las la-minus"></i><span>List Sale</span>
                                          </a>
                                  </li>
                                  
                          </ul>
                      </li>
                      <li class=" ">
                          <a href="#return" class="collapsed" data-toggle="collapse" aria-expanded="false">
                              <svg class="svg-icon" id="p-dash6" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <polyline points="4 14 10 14 10 20"></polyline><polyline points="20 10 14 10 14 4"></polyline><line x1="14" y1="10" x2="21" y2="3"></line><line x1="3" y1="21" x2="10" y2="14"></line>
                              </svg>
                              <span class="ml-4">Returns</span>
                              <svg class="svg-icon iq-arrow-right arrow-active" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <polyline points="10 15 15 20 20 15"></polyline><path d="M4 4h7a4 4 0 0 1 4 4v12"></path>
                              </svg>
                          </a>
                          <ul id="return" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                                  <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/list-returns">
                                              <i class="las la-minus"></i><span>List Returns</span>
                                          </a>
                                  </li>
                                  <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/add-return">
                                              <i class="las la-minus"></i><span>Add Return</span>
                                          </a>
                                  </li>
                          </ul>
                      </li>
                      <%
                                  	if(objUAd.getPosition_id()==1){
                                  %>
                      <li class=" ">
                          <a href="#user" class="collapsed" data-toggle="collapse" aria-expanded="false">
                              <svg class="svg-icon" id="p-dash8" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                              </svg>
                              <span class="ml-4">User</span>
                              <svg class="svg-icon iq-arrow-right arrow-active" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <polyline points="10 15 15 20 20 15"></polyline><path d="M4 4h7a4 4 0 0 1 4 4v12"></path>
                              </svg>
                          </a>
                          <ul id="user" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                                  <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/list-users">
                                              <i class="las la-minus"></i><span>Users</span>
                                          </a>
                                  </li>
                                  <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/add-users">
                                              <i class="las la-minus"></i><span>Add Users</span>
                                          </a>
                                  </li>
                                 
                          </ul>
                      </li>    
                        
                       <li class=" ">
                          <a href="<%=request.getContextPath() %>/admin/list-customers" >
                              <svg class="svg-icon" id="p-dash8" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                              </svg>
                              <span class="ml-4">Customers</span>
                              <svg class="svg-icon iq-arrow-right arrow-active" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <polyline points="10 15 15 20 20 15"></polyline><path d="M4 4h7a4 4 0 0 1 4 4v12"></path>
                              </svg>
                          </a>
                      </li>
                      <%} %>   
                      <li class=" ">
                          <a href="#suppliers" class="collapsed" data-toggle="collapse" aria-expanded="false">
                              <svg class="svg-icon" id="p-dash8" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                              </svg>
                              <span class="ml-4">Suppliers</span>
                              <svg class="svg-icon iq-arrow-right arrow-active" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <polyline points="10 15 15 20 20 15"></polyline><path d="M4 4h7a4 4 0 0 1 4 4v12"></path>
                              </svg>
                          </a>
                          <ul id="suppliers" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                                   <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/list-suppliers">
                                              <i class="las la-minus"></i><span>Suppliers</span>
                                          </a>
                                  </li>
                                  <li class="">
                                          <a href="<%=request.getContextPath() %>/admin/add-supplier">
                                              <i class="las la-minus"></i><span>Add Suppliers</span>
                                          </a>
                                  </li>
                          </ul>
                      </li>         
                    </ul>
              </nav>
              <div id="sidebar-bottom" class="position-relative sidebar-bottom">
                  <div class="card border-none">
                      <div class="card-body p-0">
                          <div class="sidebarbottom-content">
                              <div class="image"><img src="<%=request.getContextPath() %>/templates/admin/assets/images/layouts/side-bkg.png" class="img-fluid" alt="side-bkg"></div>
                              <h6 class="mt-4 px-4 body-title">Get More Feature by Upgrading</h6>
                              <button type="button" class="btn sidebar-bottom-btn mt-4">Go Premium</button>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="p-3"></div>
          </div>
          </div>