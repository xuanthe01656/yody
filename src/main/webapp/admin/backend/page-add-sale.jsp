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
      </div>      <div class="content-page">
     <div class="container-fluid add-form-list">
        <div class="row">
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-header d-flex justify-content-between">
                        <div class="header-title">
                            <h4 class="card-title">Add Sale</h4>
                        </div>
                    </div>
                    <div class="card-body">
                        <form action="page-list-returns.jsp" data-toggle="validator">
                            <div class="row">                                  
                                <div class="col-md-6">                      
                                    <div class="form-group">
                                        <label>Date *</label>
                                        <input type="text" class="form-control" placeholder="Date">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Reference No *</label>
                                        <input type="text" class="form-control" placeholder="Enter Reference No" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Biller *</label>
                                        <select name="type" class="selectpicker form-control" data-style="py-0">
                                            <option>Test Biller</option>
                                        </select>
                                    </div> 
                                </div>  
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Customer *</label>
                                        <input type="text" class="form-control" placeholder="Enter Customer Name" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>   
                                <div class="col-md-6"> 
                                    <div class="form-group">
                                        <label>Order Tax *</label>
                                        <select name="type" class="selectpicker form-control" data-style="py-0">
                                            <option>No Text</option>
                                            <option>GST @5%</option>
                                            <option>VAT @10%</option>
                                        </select>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Order Discount</label>
                                        <input type="text" class="form-control" placeholder="Discount">
                                    </div>
                                </div> 
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Shipping</label>
                                        <input type="text" class="form-control" placeholder="Shpping">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Attach Document</label>
                                        <input type="file" class="form-control image-file" name="pic" accept="image/*">
                                    </div>
                                </div>
                                <div class="col-md-6"> 
                                    <div class="form-group">
                                        <label>Sale Status *</label>
                                        <select name="type" class="selectpicker form-control" data-style="py-0">
                                            <option>Completed</option>
                                            <option>Pending</option>
                                        </select>
                                    </div>
                                </div> 
                                <div class="col-md-6"> 
                                    <div class="form-group">
                                        <label>Payment Status *</label>
                                        <select name="type" class="selectpicker form-control" data-style="py-0">
                                            <option>Pending</option>
                                            <option>Due</option>
                                            <option>Paid</option>
                                        </select>
                                    </div>
                                </div> 
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Sale Note *</label>
                                        <div id="quill-tool">
                                            <button class="ql-bold" data-toggle="tooltip" data-placement="bottom" title="Bold"></button>
                                            <button class="ql-underline" data-toggle="tooltip" data-placement="bottom" title="Underline"></button>
                                            <button class="ql-italic" data-toggle="tooltip" data-placement="bottom" title="Add italic text <cmd+i>"></button>
                                            <button class="ql-image" data-toggle="tooltip" data-placement="bottom" title="Upload image"></button>
                                            <button class="ql-code-block" data-toggle="tooltip" data-placement="bottom" title="Show code"></button>
                                        </div>
                                        <div id="quill-toolbar">
                                        </div>
                                    </div>
                                </div> 
                            </div>                            
                            <button type="submit" class="btn btn-primary mr-2">Add Sale</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Page end  -->
    </div>
      </div>
    </div>
    <!-- Wrapper End-->
        <%@ include file="/templates/admin/inc/footer.jsp" %> 