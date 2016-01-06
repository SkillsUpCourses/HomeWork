    <%@ page language="java" contentType="text/html; charset=UTF-8"  
        pageEncoding="UTF-8"%>  
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
    <html>  
    <head>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
    <title>All Contacts details</title>  
    </head>  
    <body>  
    <center>  
      
    <div style="color: teal;font-size: 30px"> User Details</div>  
      
      
    <form:form method="POST" action="/NaZviazku/contacts"> 
    <c:if test="${!empty user}">  
    <table border="1" bgcolor="black" width="600px">  
    <tr style="background-color: teal;color: white;text-align: center;" height="40px">  
    <td>User Id</td>  
    <td>First Name</td>  
    <td>Last Name</td>  
    <td>Phone Number</td>  
    <td>email</td>  
    </tr>  
    <c:forEach items="${user}" var="user">  
    <tr style="background-color:white;color: black;text-align: center;" height="30px" >  
    <td><c:out value="${user.id}"/></td>  
    <td><c:out value="${user.firstName}"/></td>  
    <td><c:out value="${user.lastName}"/></td>  
    <td><c:out value="${user.phone}"/></td>  
    <td><c:out value="${user.email}"/></td>  
    </tr>  
    </c:forEach>  
    </table>  
    </c:if>  
    </form:form>
    </center>  
    </body>  
    </html>  