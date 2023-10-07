<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<footer class="iq-footer">
            <div class="container-fluid">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <ul class="list-inline mb-0">
                                <li class="list-inline-item"><a href="../backend/privacy-policy.jsp">Privacy Policy</a></li>
                                <li class="list-inline-item"><a href="../backend/terms-of-service.jsp">Terms of Use</a></li>
                            </ul>
                        </div>
                        <div class="col-lg-6 text-right">
                            <span class="mr-1"><script>document.write(new Date().getFullYear())</script>©</span> <a href="#" class="">YODY</a>.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
  <script src="<%=request.getContextPath() %>/templates/admin/assets/js/backend-bundle.min.js"></script>
   <!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>-->
    <!-- Table Treeview JavaScript-->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/table-treeview.js"></script> 
    <!-- Chart Custom JavaScript 
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/customizer.js"></script>-->
    <script async src="<%=request.getContextPath() %>/templates/admin/assets/js/chart-custom.js"></script>
    <!-- app JavaScript -->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/app.js"></script> 
  </body>
</html>     