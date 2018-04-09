<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="cart/add" method = "post">
<img alt="" src="${selectedProductImage }"> <br>
<input type = "text" name = "id" values =${selectedproduct.id}>
<input type = "text" name = "productName" values =${selectedproduct.name}>
<input type = "text" name = "price" values =${selectedproduct.price}>
<input type = "text" name = "quantity"> <br>

Product Name: ${selectedproduct.name} <br>
<input type = "submit" name = "Add to Cart">

<%-- Price: ${selectedproduct.price} <br>
Description: ${selectedproduct.description}<br>
<a href ="/cart/add?productName=${selectedproduct.name} &price=${selectedprouct.price}&quantity=1">Add to Cart</a> --%>
</form>
<%-- <img alt="" src="${imageFolder}/${selectdproduct}.PNG"> --%>
</body>
</html>