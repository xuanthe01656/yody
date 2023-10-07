<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Cancelled</title>
	<script type="text/javascript">
            function Redirect() {
               window.location="<%=request.getContextPath() %>/public/index";
            }

            document.write("You will be redirected to main page in 5 sec.");
            setTimeout('Redirect()', 5000);
      </script>
</head>
<body>
<div align="center">
	<h1>Payment Cancelled</h1>
	<br/>
	<p>Click the following button, you will be redirected to home page.</p>
      <form>
         <input type="button" value="Redirect Me" onclick="Redirect();" />
      </form>
</div>
</body>
</html>