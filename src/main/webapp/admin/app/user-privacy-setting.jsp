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
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between">
                            <div class="header-title">
                                <h4 class="card-title">Privacy Setting</h4>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="acc-privacy">
                                <div class="data-privacy">
                                    <h4 class="mb-2">Account Privacy</h4>
                                    <div class="custom-control custom-checkbox custom-control-inline">
                                        <input type="checkbox" class="custom-control-input" id="acc-private">
                                        <label class="custom-control-label privacy-status mb-2" for="acc-private">Private
                                            Account</label>
                                    </div>
                                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum
                                        has been the industry's standard dummy text ever since the 1500s, when an unknown
                                        printer took a galley of type and scrambled it to make a type specimen book</p>
                                </div>
                                <hr>
                                <div class="data-privacy">
                                    <h4 class="mb-2">Activity Status</h4>
                                    <div class="custom-control custom-checkbox custom-control-inline">
                                        <input type="checkbox" class="custom-control-input" id="activety" checked="">
                                        <label class="custom-control-label privacy-status mb-2" for="activety">Show Activity
                                            Status</label>
                                    </div>
                                    <p>It is a long established fact that a reader will be distracted by the readable content of
                                        a page when looking at its layout. The point of using Lorem Ipsum is that it has a
                                        more-or-less normal distribution of letters, as opposed to using 'Content here, content
                                        here', making it look like readable English.</p>
                                </div>
                                <hr>
                                <div class="data-privacy">
                                    <h4 class="mb-2"> Story Sharing </h4>
                                    <div class="custom-control custom-checkbox custom-control-inline">
                                        <input type="checkbox" class="custom-control-input" id="story" checked="">
                                        <label class="custom-control-label privacy-status mb-2" for="story">Allow
                                            Sharing</label>
                                    </div>
                                    <p>It is a long established fact that a reader will be distracted by the readable content of
                                        a page when looking at its layout. The point of using Lorem Ipsum is that it has a
                                        more-or-less normal distribution of letters, as opposed to using 'Content here, content
                                        here', making it look like readable English.</p>
                                </div>
                                <hr>
                                <div class="data-privacy">
                                    <h4 class="mb-2"> Photo Of You </h4>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" id="automatically" name="customRadio0" class="custom-control-input"
                                            checked="">
                                        <label class="custom-control-label" for="automatically"> Add Automatically</label>
                                    </div>
                                    <div class="custom-control custom-radio mb-2">
                                        <input type="radio" id="manualy" name="customRadio0" class="custom-control-input">
                                        <label class="custom-control-label" for="manualy"> Add Manualy</label>
                                    </div>
                                    <p>It is a long established fact that a reader will be distracted by the readable content of
                                        a page when looking at its layout. The point of using Lorem Ipsum is that it has a
                                        more-or-less normal distribution of letters, as opposed to using 'Content here, content
                                        here', making it look like readable English.</p>
                                </div>
                                <hr>
                                <div class="data-privacy">
                                    <h4 class="mb-2"> Your Profile </h4>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" id="public" name="customRadio1" class="custom-control-input"
                                            checked="">
                                        <label class="custom-control-label" for="public"> Public </label>
                                    </div>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" id="friend" name="customRadio1" class="custom-control-input">
                                        <label class="custom-control-label" for="friend"> Friend </label>
                                    </div>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" id="spfriend" name="customRadio1" class="custom-control-input">
                                        <label class="custom-control-label" for="spfriend"> Specific Friend </label>
                                    </div>
                                    <div class="custom-control custom-radio mb-2">
                                        <input type="radio" id="onlyme" name="customRadio1" class="custom-control-input">
                                        <label class="custom-control-label" for="onlyme"> Only Me </label>
                                    </div>
                                    <p>It is a long established fact that a reader will be distracted by the readable content of
                                        a page when looking at its layout. The point of using Lorem Ipsum is that it has a
                                        more-or-less normal distribution of letters, as opposed to using 'Content here, content
                                        here', making it look like readable English.</p>
                                </div>
                                <hr>
                                <div class="data-privacy">
                                    <h4 class="mb-2"> Login Notification </h4>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" id="enable" name="customRadio2" class="custom-control-input">
                                        <label class="custom-control-label" for="enable"> Enable </label>
                                    </div>
                                    <div class="custom-control custom-radio mb-2">
                                        <input type="radio" id="disable" name="customRadio2" class="custom-control-input"
                                            checked="">
                                        <label class="custom-control-label" for="disable"> Disable </label>
                                    </div>
                                    <p>It is a long established fact that a reader will be distracted by the readable content of
                                        a page when looking at its layout. The point of using Lorem Ipsum is that it has a
                                        more-or-less normal distribution of letters, as opposed to using 'Content here, content
                                        here', making it look like readable English.</p>
                                </div>
                                <hr>
                                <div class="data-privacy">
                                    <h4 class="mb-2">Privacy Help</h4>
                                    <a href="#"><i class="ri-customer-service-2-line mr-2"></i>Support</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      </div>
    </div>
    <!-- Wrapper End-->
    <%@ include file="/templates/admin/inc/footer.jsp" %>  