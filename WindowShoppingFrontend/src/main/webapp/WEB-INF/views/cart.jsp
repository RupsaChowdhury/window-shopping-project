<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="cart" items="carts">
<img alt="" src="${selectedProductImage }"> <br>
Name:<input type = "text" name = "productName" values =${cart.name}>
<input type = "text" name = "price" values =${cart.price}>
<input type = "text" name = "quantity"> <br>
</c:forEach>
<c:forEach items="${products}" var ="products">
${product.name }
${product.description }
${product.price }
<img alt="" src="resorces/images/${selectedProductImage }">
</c:forEach>
</body>
</html>