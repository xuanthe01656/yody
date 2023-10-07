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
         <div class="row row-eq-height">
            <div class="col-md-12">
               <div class="row">
                  <div class="col-md-12">
                     <div class="card iq-border-radius-20">
                        <div class="card-body">
                           <div class="row">
                              <div class="col-md-12 mb-3">
                                 <h5 class="text-primary card-title"><i class="ri-pencil-fill"></i> Compose Message</h5>
                              </div>
                           </div>
                           <form class="email-form">
                              <div class="form-group row">
                                 <label for="multiple" class="col-sm-2 col-form-label">To:</label>
                                 <div class="col-sm-10">
                                    <select  id="multiple" class="js-states form-control" multiple>
                                       <option value="Petey Cruiser">Petey Cruiser</option>
                                       <option value="Bob Frapples">Bob Frapples</option>
                                       <option value="Barb Ackue">Barb Ackue</option>
                                       <option value="Greta Life">Greta Life</option>
                                    </select>
                                 </div>
                              </div>
                              <div class="form-group row">
                                 <label for="multiple2" class="col-sm-2 col-form-label">Cc:</label>
                                 <div class="col-sm-10">
                                    <select  id="multiple2" class="js-states form-control" multiple>
                                       <option value="Brock Lee">Brock Lee</option>
                                       <option value="Rick O'Shea">Rick O'Shea</option>
                                       <option value="Cliff Hanger">Cliff Hanger</option>
                                       <option value="Barb Dwyer">Barb Dwyer</option>
                                    </select>
                                 </div>
                              </div>
                              <div class="form-group row">
                                 <label for="subject" class="col-sm-2 col-form-label">Subject:</label>
                                 <div class="col-sm-10">
                                    <input type="text"  id="subject" class="form-control" placeholder="Subject">
                                 </div>
                              </div>
                              <div class="form-group row">
                                 <label for="subject" class="col-sm-2 col-form-label">Your Message:</label>
                                 <div class="col-md-10">
                                    <textarea class="textarea form-control" rows="5">Next, use our Get Started docs to setup Tiny!</textarea>
                                 </div>
                              </div>
                              <div class="form-group row align-items-center">
                                 <div class="d-flex flex-grow-1 align-items-center send-buttons">
                                    <div class="send-btn pl-3">
                                       <button type="submit" class="btn btn-primary">Send</button>
                                    </div>
                                    <div class="send-panel">
                                       <label class="ml-2 bg-primary-light rounded" for="file"> <input type="file" id="file" style="display: none"> <a><i class="ri-attachment-line text-primary"></i> </a> </label>
                                       <label class="ml-2 bg-primary-light rounded"> <a href="javascript:void(0);"> <i class="ri-map-pin-user-line text-primary"></i></a>  </label>
                                       <label class="ml-2 bg-primary-light rounded"> <a href="javascript:void(0);"> <i class="ri-drive-line text-primary"></i></a>  </label>
                                       <label class="ml-2 bg-primary-light rounded"> <a href="javascript:void(0);"> <i class="ri-camera-line text-primary"></i></a>  </label>
                                       <label class="ml-2 bg-primary-light rounded"> <a href="javascript:void(0);"> <i class="ri-user-smile-line text-primary"></i></a>  </label>
                                    </div>
                                 </div>
                                 <div class="d-flex mr-3 align-items-center">
                                    <div class="send-panel float-right">
                                       <label class="ml-2 mb-0 bg-primary-light rounded" ><a href="javascript:void(0);"><i class="ri-settings-2-line text-primary"></i></a></label>
                                       <label class="ml-2 mb-0 bg-primary-light rounded"><a href="javascript:void(0);">  <i class="ri-delete-bin-line text-primary"></i></a>  </label>
                                    </div>
                                 </div>
                              </div>
                           </form>
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