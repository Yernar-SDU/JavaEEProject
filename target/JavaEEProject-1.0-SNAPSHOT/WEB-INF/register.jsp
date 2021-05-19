<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>Sign Up</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>

<style type="text/css">
	h1{
		border-bottom: 6px solid #333;
		padding-bottom: 10px;
		color: #333;
	}

	*{
	text-decoration: none;
	margin: 0;
	padding: 0;
	font-family: sans-serif;
	box-sizing: border-box;
}

	.card{/*
		padding: 40px 20px;*/
		border-radius: 10px;
		background: #f1f1f1;
		width: 600px;
		height: 550px;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%,-50%);
	}

	.textbox{
		width: 220px;
		overflow: hidden;
		font-size: 20px;
		padding: 8px 0;
		margin: 8px 0;
		border-bottom: 1px solid #333;
	}

	.textbox i{
		color: #333;
		width: 26px;
		text-align: center;
	}

	.textbox input{
		border: none;
		background: none;
		outline: none;
		color: #333;
		font-size: 14px;
		width: 50%;
		margin: 0 10px;
	}



</style>

<body style="min-height: 10vh; background: linear-gradient(60deg,#3498db,#8e44ad)">

<div class="d-flex flex-column justify-content-center align-items-center card">
	<h1 style="color: #111">Create your Account</h1>
	<form action="createaccountaction.do" method="post" class="d-flex justify-content-around" style="width: 100%; margin: 20px;">
		<div class="d-flex flex-column">
			<c:forEach var="field" items="${form.visibleFields}">
   		        <div class="form-group">
                       <label id="${field.label}">${field.label}</label>
                       <input id="${field.name}" type="${field.type}" name="${field.name}" value="${field.value}"/>
                    </div>
                    <div>
                    <p style="color:red">
                        ${field.error}
                    </p>
   		        </div>
            </c:forEach>

			<div class="check">
				<input type="checkbox">	
				<label>Accept the terms and policies</label>
			</div>
			<form>
			<button name="action" value="submit" type="submit" class="btn btn-primary" style="margin-top: 8px;">Sign Up</button>
			</form>
		</div>
	</form>
</div>

</body>
</html>