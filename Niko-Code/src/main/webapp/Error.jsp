<%-- 
    Document   : Error
    Created on : 27/02/2023, 15:09:18
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error</h1>
        <c:forEach items="${Error}"  var="mensaje">
            <h2> 
                <c:out value="${mensaje}"></c:out>  
            </h2>
        </c:forEach>
    </body>
</html>
