<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Customer</title>
</head>
<body>
	<h2>
		<a href="/new">Add New Customer</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Customer</a>
    </h2>
    <div align="center">
        <c:if test="${customer != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${customer == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    
                    <c:if test="${customer == null}">
                        Add New customer
                    </c:if>
                </h2>
            </caption>
                <c:if test="${customer != null}">
                    <input type="hidden" name="id" value="<c:out value='${customer.id}' />" />
                </c:if>           
            <tr>
                <th>ID: </th>
                <td>
                    <input type="text" name="title" size="45"
                            value="<c:out value='${customer.id}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="author" size="45"
                            value="<c:out value='${customer.name}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>