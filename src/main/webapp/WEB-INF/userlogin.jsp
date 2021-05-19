<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title> <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

<style type="text/css">
	.card{
		background: #f1f1f1;
		width: 360px;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%,-50%);
		color: white;
		padding: 60px 40px; 
		border-radius: 10px;
	}
	.textbox{
		width: 100%;
		overflow: hidden;
		font-size: 20px;
		padding: 8px 0;
		margin: 8px 0;
		border-bottom: 1px solid #333;
	}

	.textbox i{
		color: #333;
		width: 26px;
		float: left;
		text-align: center;
	}
	.textbox input{
		border: none;
		background: none;
		outline: none;
		color: #333;
		font-size: 18px;
		width: 80%;
		float: left;
		margin: 0 10px;
	}

	.signup{
		margin-top:15px;
		text-align: center;
		color: #333;
	}

</style>

</head>
<body style="min-height: 10vh; background: linear-gradient(60deg,#3498db,#8e44ad)">

<div class="d-flex flex-column judtify-content-center align-items-center position-absolute card">
	<h1 style="color: #111">Login</h1>
	<form action="login.do" method="post" class="d-flex flex-column justify-content-around" style="width: 100%">
		<%List<String> list = new ArrayList<>();list.add("user");list.add("lock");int i = 0;%>
		<c:forEach var="field" items="${form.visibleFields}">
			<div class="textbox">
				<i class="fas fa-<%=list.get(i)%>"></i><%i++;%>
				<input type="text" id="${field.name}" type="${field.type}" name="${field.name}" value="${field.value}">
			</div>
			<div style="color:red" id="emailHelp" class="form-text">${field.error}</div>
		</c:forEach>

		<button value="submit" name="action" type="submit" class="btn btn-primary">Submit</button>
	</form>
	<div class="signup">
		<form action="createaccountaction.do" method="get">
		Don't have account?  <button type="submit" class="btn btn-link" style="text-decoration: none">Register</button>
		</form>
	</div>
</div>

</body>
</html>