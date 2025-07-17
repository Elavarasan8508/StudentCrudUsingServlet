<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Student Form Application</title>
</head>
<body>
<div align="center">
<h1>Student Management</h1>

<h2>
<a href="new">Add new Student</a>
&nbsp;&nbsp;&nbsp;
<a href="list">List all Students</a>
</h2>
</div>

<div align="center">
<table border="1" cellpading="5">
<caption><h2>List of Books</h2></caption>
<tr>
<th>ID</th>
<th>NAME</th>
<th>AGE</th>
<th>COLLEGE</th>
<th>ACTIONS</th>
</tr>
<c:forEach var="student" items="${studentlist}">
<tr>
<td><c:out value="${student.id }" /></td>
<td><c:out value="${student.name }" /></td>
<td><c:out value="${student.age }" /></td>
<td><c:out value="${student.college }" /></td>
<td>
<a href="edit?id=<c:out value='${student.id }'/>">Edit</a>
&nbsp;&nbsp;&nbsp;
<a href="delete?id=<c:out value='${student.id}'/>" }>Delete </a>
</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>