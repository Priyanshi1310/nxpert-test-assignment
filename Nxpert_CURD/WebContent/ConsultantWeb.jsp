<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.*" %>   
<%@ page import= "com.nxpert.consultant.ConsDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consultant List</title>
</head>
<body>
<center>
        <h1>Consultant</h1>
    </center>
    
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Consultant</h2></caption>
            <tr>
                <th>ID</th>
                <th>Customer Name</th>
                <th>Consultant Name</th>
            </tr>
            <c:forEach var="consultant" items="${listConsultant}">
                <tr>
                    <td><c:out value="${consultant.id}" /></td>
                    <td><c:out value="${consultant.customer_name}" /></td>
                    <td><c:out value="${consultant.consultant_name}" /></td>
                    <td>
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