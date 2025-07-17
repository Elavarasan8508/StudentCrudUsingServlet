<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

        <c:choose>
            <c:when test="${student != null}">
                <form action="update" method="post">
            </c:when>
            <c:otherwise>
                <form action="insert" method="post">
            </c:otherwise>
        </c:choose>

        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:choose>
                        <c:when test="${student != null}">Edit Student</c:when>
                        <c:otherwise>Add New Student</c:otherwise>
                    </c:choose>
                </h2>
            </caption>

            <c:if test="${student != null}">
                <input type="hidden" name="id" value="<c:out value='${student.id}'/>" />
            </c:if>

            <tr>
                <th>Name</th>
                <td>
                    <input type="text" name="name" size="50" pattern="[A-Za-z\s]+" 
                    title="Only letters and spaces allowed"
                        value="<c:out value='${student.name}'/>" required />
                </td>
            </tr>

            <tr>
                <th>Age</th>
                <td>
                    <input type="number" name="age" size="45"
                        min="1" max="100"
                        title="Only numbers allowed"
                        value="<c:out value='${student.age}'/>" required />
                </td>
            </tr>

            <tr>
                <th>College</th>
                <td>
                    <input type="text" name="college" size="50"
                        pattern="[A-Za-z\s]+" 
                        title="Only letters and spaces allowed"
                        value="<c:out value='${student.college}'/>" required />
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
