<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ page import= "com.nxpert.customer.CustDao" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer</title>
</head>
<body>
	<center>
        <h1>Customer</h1>
    </center>
    
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Customers</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            <c:forEach var="customer" items="${listCustomer}">
                <tr>
                    <td><c:out value="${customer.id}" /></td>
                    <td><c:out value="${customer.name}" /></td>
                    <td>
                        <a href="/view?id=<c:out value='${customer.id}' />">View</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/update?id=<c:out value='${customer.id}' />">Update</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${customer.id}' />">Delete</a>                      
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   

</body>
</html>