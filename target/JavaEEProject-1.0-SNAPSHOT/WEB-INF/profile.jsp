<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Profile</title> <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

<style type="text/css">
	input{
		margin: 5px;
		padding: 5px;
	}

	.settings > button, .settings > div > button{
		width: 200px;
	}

	.password > input{
		width: 200px;
	}

	.cardinfo > input{
		width: 50px;
	}

</style>

</head>
<body>
<div class="d-flex  align-items-center fixed-top" style="width: 100%; height: 60px; background-color: #79b1c5;">
	<div class="d-flex align-items-center" style="width: 90%">
		<div class="d-flex justify-content-center align-items-center" style="padding: 10px; width: 15%">
			<p style="font-size: 20px">KokoBET</p>
		</div>
		<div class="d-flex justify-content-around" style="width: 15%">
			<form action="main.do" method="post">
				<button name="action" value="Live" type="submit" class="btn btn-primary">Live</button>
			</form>
			<form action="main.do" method="post">
				<button name="action" value="Line" type="submit" class="btn btn-primary">Line</button>
			</form>
			<form action="history.do" method="get">
				<button name="action" type="submit" value="History" class="btn btn-primary">History</button>
			</form>
		</div>
	</div>
	<%if(request.getSession().getAttribute("user")!=null){%>
	<p style="margin-right: 20px; padding: 8px; margin-top: 10px;" class="btn-dark">Balance: ${userInfo.balance}tg</p>
	<%}%>
	<div class="d-flex justify-content-center" style="width: 10%;">
		<%if(request.getSession().getAttribute("user")!=null){%>
		<form action="profile.do" method="get">
			<button style="margin-right: 20px;" name="action" type="submit" value="Profile" class="btn btn-primary">Profile</button>
		</form>
		<%} else {%>
		<form action="login.do" method="get">
			<button style="margin-right: 20px;" name="action" type="submit" value="Login" class="btn btn-primary">Login</button>
		</form>

		<%}%>

		<form action="login.do" method="get">
			<button name="action" type="submit" value="Login" class="btn btn-primary">Logout</button>
		</form>
	</div>
</div>

<div class="d-flex flex-column" style="width: 80%; margin: 100px;">
	<h1 style="margin-left: 100px;">Profile</h1>
	<div style="width: 100%;" class="d-flex justify-content-around">
		<div class="d-flex flex-column">
			<input type="text" name="username" value="Email: ${userInfo.email}" readonly="true">
			<input type="text" name="firstName" value="Name: ${userInfo.name}" readonly="true">
			<input type="text" name="lastname" value="Surname: ${userInfo.surname}" readonly="true">
			<input type="text" name="balance" value="Balance: ${userInfo.balance}tg" readonly="true">
		</div>
		<div class="d-flex flex-column settings">
			<button type="button" class="btn btn-outline-dark" id="btnDeposit">Deposit</button>
			<div class="d-flex flex-column" id="Deposit" style="display: none;">
				<form action="deposit.do" method="post">
					<c:forEach var="deposit" items="${depositForm.visibleFields}">
						<label class="form-label">${deposit.label}</label>
						<div class="d-flex">
							<input class="form-control" id="${deposit.name}" type="${deposit.type}" name="${deposit.name}" value="${deposit.value}"/><br>
							<div style="color:red" id="email" class="form-text">${deposit.error}</div>
						</div>
					</c:forEach>
					<button value="submit" name="action" type="submit" class="btn btn-primary">Get money</button>
				</form>
				<c:forEach var="error" items="${depositForm.formErrors}">
					<h3 id="${match.id}t" style="color:red"> ${error} </h3>
				</c:forEach>
			</div>
		</div>
		<div class="d-flex flex-column">
			<button type="button" style="margin-top:10px; " class="btn btn-outline-dark" id="btnChange">Change Password</button>
			<div class="d-flex flex-column password" id="Change" style="display: none">
				<form action="changePassword.do" method="post">
					<c:forEach var="field" items="${passwordForm.visibleFields}">
						<label class="form-label">${field.label}</label>
						<div class="d-flex">
							<input class="form-control" id="${field.name}" type="${field.type}" name="${field.name}" value="${field.value}"/>
						</div>
						<div style="color:red" id="emailHelp" class="form-text">${field.error}</div>
					</c:forEach>
					<button value="submit" name="action" type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>

</div>


<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
        $('#show_option').click(function(){
            $('#fund-options').slideToggle();
        });
</script>
</body>
</html>