<%@page import="model.bean.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Check Out</title>
	<style type="text/css">
		table { border: 0; }
		table td { padding: 10px; }
	</style>
</head>
<body>
<div align="center">
	<h1>Check Out</h1>
	<br/>
	<%
		Order objOrder = new Order();
		if(request.getAttribute("objOrder")!=null){
			objOrder = (Order) request.getAttribute("objOrder");
		}
	%>
	<form action="<%=request.getContextPath() %>/paypal/authorize-payment" method="post">
	<table>
		<tr>
			<td>Product/Service:</td>
			<td><textarea name="product"> <%=objOrder.getProductName() %></textarea></td>
		</tr>
		<tr>
			<td>Sub Total:</td>
			<td><input type="text" name="subtotal" value="<%=objOrder.getSubtotal() %>" /></td>
		</tr>
		<tr>
			<td>Shipping:</td>
			<td><input type="text" name="shipping" value="<%=objOrder.getShipping() %>" /></td>
		</tr>
		<tr>
			<td>Tax:</td>
			<td><input type="text" name="tax" value="<%=objOrder.getTax() %>" /></td>
		</tr>		
		<tr>
			<td>Total Amount:</td>
			<td><input type="text" name="total" value="<%=objOrder.getTotal() %>" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="Checkout" />
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>