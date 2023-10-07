<%@page import="model.bean.OrderDetail"%>
<%@page import="java.io.File"%>
<%@page import="model.bean.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <%@ include file="/templates/admin/inc/leftbar.jsp" %>
      <%@ include file="/templates/admin/inc/header.jsp" %> 
      <div class="modal fade" id="new-order" tabindex="-1" role="dialog" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered" role="document">
              <div class="modal-content">
                  <div class="modal-body">
                      <div class="popup text-left">
                          <h4 class="mb-3">New Order</h4>
                          <div class="content create-workform bg-body">
                              <div class="pb-3">
                                  <label class="mb-2">Email</label>
                                  <input type="text" class="form-control" placeholder="Enter Name or Email">
                              </div>
                              <div class="col-lg-12 mt-4">
                                  <div class="d-flex flex-wrap align-items-ceter justify-content-center">
                                      <div class="btn btn-primary mr-4" data-dismiss="modal">Cancel</div>
                                      <div class="btn btn-outline-primary" data-dismiss="modal">Create</div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>      
	<div class="content-page">
     <div class="container-fluid fluid_order ">
     <div class="row">
  		<div>
  <%
  Admin admin = (Admin) session.getAttribute("objUAd");
  	int index =(Integer)request.getAttribute("index");
  	ArrayList<OrderDetail> listOrderDetail = new ArrayList<>();
  	if(request.getAttribute("listOrderDetail")!=null){
  		listOrderDetail= (ArrayList<OrderDetail>) request.getAttribute("listOrderDetail");
  	}
  %>
    <a class="btn btn-lg text-center" href="<%=request.getContextPath()%>/admin/list-order?index=<%=index %>" ><span><i style="font-size: 1em;" title="Go to back" class="fa fa-arrow-left fa-3x"></i>Go To Back</span></a>
  	</div>
	</div>
        <div class="body-order row">
            <div class="body-order-head col-lg-12">
                <div class="d-flex flex-wrap flex-wrap align-items-center justify-content-between mb-4">
                    <div>
                        <h4 class="mb-3">Order List Detail</h4>
                    </div>
                </div>
            </div>
           
            <div class="body-order-body col-lg-12">
                <div class="body_body_main mb-3">
		            <%
		              int total =0;
		              int discount = 0;
		              int transport = 0;
					if(listOrderDetail.size()>0){
					total =(int) listOrderDetail.get(0).getTotal();
					transport = (int) listOrderDetail.get(0).getShipping();
					if(listOrderDetail.get(0).getDiscount()>0){
						discount = (int)listOrderDetail.get(0).getDiscount();
					}
					for(OrderDetail objPD: listOrderDetail){
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
				            <span class="text-left subtotal_one"><span class="subtotal_two">Subtotal: </span><%=(int)objPD.getPrice()%>đ</span>
			            </div>
		            </div>
		           <%}}%>       
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
		                        	<th class="text-center" colspan="6"><button type="button" id="confirm_order">Confirm Order</button></th>
		                        </tr>
		                      </tbody>
		                </table>
                </div>
        </div>
        <script type="text/javascript">
        	$('#confirm_order').on('click', function(){
        		var orderCode = <%=listOrderDetail.get(0).getOder_code()%>;
        	});
        </script>
        <!-- Page end  -->
    </div>
    <!-- Modal Edit -->
    <!--<div class="modal fade" id="edit-note" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="popup text-left">
                        <div class="media align-items-top justify-content-between">                            
                            <h3 class="mb-3">Product</h3>
                            <div class="btn-cancel p-0" data-dismiss="modal"><i class="las la-times"></i></div>
                        </div>
                        <div class="content edit-notes">
                            <div class="card card-transparent card-block card-stretch event-note mb-0">
                                <div class="card-body px-0 bukmark">
                                    <div class="d-flex align-items-center justify-content-between pb-2 mb-3 border-bottom">                                                    
                                        <div class="quill-tool">
                                        </div>
                                    </div>
                                    <div id="quill-toolbar1">
                                        <p>Virtual Digital Marketing Course every week on Monday, Wednesday and Saturday.Virtual Digital Marketing Course every week on Monday</p>
                                    </div>
                                </div>
                                <div class="card-footer border-0">
                                    <div class="d-flex flex-wrap align-items-ceter justify-content-end">
                                        <div class="btn btn-primary mr-3" data-dismiss="modal">Cancel</div>
                                        <div class="btn btn-outline-primary" data-dismiss="modal">Save</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>-->
      </div>
    </div>
    <!-- Wrapper End-->
         <%@ include file="/templates/admin/inc/footer.jsp" %> 