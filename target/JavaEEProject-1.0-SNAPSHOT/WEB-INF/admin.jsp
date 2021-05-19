<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Create</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>

<style type="text/css">
	button{
		width: 100px;
	}

	input{
		width: 220px;
	}

</style>
<body>

<div class="d-flex" style="width: 100%; min-height: 737px;">
	<div class="d-flex flex-column align-items-center" style="width: 15%; padding-top: 50px; background-color: whitesmoke">
		<form action="create.do" method="get" style="margin-bottom: 15px;">
			<button name="action" type="submit" value="submit" class="btn btn-outline-dark">Create</button>
		</form>
		<form action="matches.do" method="get" style="margin-bottom: 15px;">
			<button name="action" type="submit" value="submit" class="btn btn-outline-dark">Matches</button>
		</form>
		<form action="users.do" method="get">
			<button name="action" type="submit" value="submit" class="btn btn-outline-dark">Users</button>
		</form>
	</div>

	<div class="d-flex flex-column" style="width: 85%;">
		<div class="d-flex justify-content-end align-items-end" style="padding: 20px;">
			<form action="login.do" method="get">
				<button type="button" class="btn btn-outline-dark">Logout</button>
			</form>
		</div>
		<div class="d-flex flex-column justify-content-center align-items-center" style="background-color: whitesmoke; width: 35%; margin-left: 300px; padding: 20px; border-radius: 10%" >
			<h2>Create</h2>
			<form action="create.do" method="post">
				<c:forEach var="field" items="${form.visibleFields}">
					<div class="form-group">
						<input id="${field.name}" type="${field.type}" name="${field.name}" value="${field.value}" placeholder="${field.label}">
					</div>
					<div>
						<p style="color:red">${field.error}</p>
					</div>
				</c:forEach>
				<button name="action" value="submit" type="submit" class="btn btn-outline-warning">Submit</button>
			</form>
		</div>
	</div>
</div>

</body>
</html>