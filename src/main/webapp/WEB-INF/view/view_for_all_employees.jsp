<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Nelfy
  Date: 12/22/2022
  Time: 3:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h3>Information for all employees</h3>

<security:authorize access="hasRole('HR')">
<input type="button" value="Salary"
       onclick="window.location.href = 'hr_info'"><br>
Only For HR staff<br>
</security:authorize>
<security:authorize access="hasRole('MANAGER')">
<input type="button" value="Performance"
       onclick="window.location.href='manager_info'"><br>
Only For Managers<br>
</security:authorize>
</body>
</html>
