<%@page import="model.dao.AdminDAO"%>
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
      </div>      <div class="content-page">
     <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="d-flex flex-wrap align-items-center justify-content-between mb-4">
                    <div>
                        <h4 class="mb-3">User List</h4>
                        <p class="mb-0">A dashboard provides you an overview of user list with access to the most important data,<br>
                         functions and controls. </p>
                    </div>
                    <a href="<%=request.getContextPath() %>/admin/add-users" class="btn btn-primary add-list"><i class="las la-plus mr-3"></i>Add User</a>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive rounded mb-3">
                <%
                if(request.getParameter("msg")!=null){
					int msg = Integer.parseInt(request.getParameter("msg"));
					if(msg==1){
						%>
						<div><span style="background-color: yellow; color: red; font-style: italic;">Sửa User thành công!</span></div>
						<%
					}else{
						if(msg==2){
						%>
						<div><span style="background-color: yellow; color: red; font-style: italic;">Sửa User không thành công!</span></div>
						<%
						}else{
							if(msg==3){
								%>
								<div><span style="background-color: yellow; color: red; font-style: italic;">Xóa User thành công!</span></div>
								<%
							}else{
								if(msg==4){
								%>
								<div><span style="background-color: yellow; color: red; font-style: italic;">Xóa User thành công!</span></div>
								<%
								}else{
									if(msg==5){
										%>
										<div><span style="background-color: yellow; color: red; font-style: italic;">Sửa User không thành công do tên trùng!</span></div>
										<%
									}
								}
							}
						}
					}
				}
                %>
                <table class="data-table table mb-0 tbl-server-info">
                    <thead class="bg-white text-uppercase">
                        <tr class="ligth ligth-data">
                            <th>Name</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Position</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody class="ligth-body">
                    <%
                    
                    	ArrayList<Admin> listUserAdmin = new ArrayList<Admin>();
                    	AdminDAO objAdDAO = new AdminDAO();
                    	listUserAdmin = objAdDAO.getListAd();
                    	for(Admin objAD: listUserAdmin){
                    %>
                        <tr>
                            <td><%=objAD.getFullname() %></td>
                            <td><%=objAD.getUsername() %></td>
                            <td><%=objAD.getEmail() %></td>
                            <td><%=objAD.getPhone()%></td>
                            <td><%=objAD.getPosition_detail()%></td>
                            <td>Active</td>
                            <td>
                                <div class="d-flex align-items-center list-action">
                                    <a class="badge bg-success mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit"
                                        href="<%=request.getContextPath()%>/admin/edit-user?id=<%=objAD.getId()%>"><i class="ri-pencil-line mr-0"></i></a>
                                    <a class="badge bg-warning mr-2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Delete"
                                        href="<%=request.getContextPath()%>/admin/delete-user?id=<%=objAD.getId()%>" ><i onclick="return confirm('Bạn thật sự muốn xóa ?')" class="ri-delete-bin-line mr-0"></i></a>
                                </div>
                            </td>
                        </tr>
                        <%} %>
                    </tbody>
                </table>
                </div>
            </div>
        </div>
        <!-- Page end  -->
    </div>
    <!-- Modal Edit -->
    <div class="modal fade" id="edit-note" tabindex="-1" role="dialog" aria-hidden="true">
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
    </div>
      </div>
    </div>
    <!-- Wrapper End-->
         <%@ include file="/templates/admin/inc/footer.jsp" %> 