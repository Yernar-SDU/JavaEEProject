<%@ page import="java.util.ArrayList" %>
<%@ page import="databean.UserBets" %>
<%@ page import="org.genericdao.RollbackException" %>
<%@ page import="databean.FootballMatch" %>
<%@ page import="model.UserBetsDAO" %>
<%@ page import="model.FootballMatchDAO" %>
<%@ page import="org.genericdao.MatchArg" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Main</title> <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

<style type="text/css">
	.img{
		width: 100px;
		height: 100px;
	}	
</style>

</head>
<body>
	
<div class="d-flex  align-items-center fixed-top" style="width: 100%; height: 60px; background-color: #79b1c5">
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
	<p style="margin-right: 20px; padding: 6px; margin-top: 10px;" class="btn-dark">Balance: ${userInfo.balance}tg</p>
	<%}%>
	<div class="d-flex justify-content-center" style="width: 10%;">

		<%if(request.getSession().getAttribute("user")!=null){%>
		<form action="profile.do" method="get">
			<button style="margin-right: 20px;" name="action" type="submit" value="Login" class="btn btn-primary">Profile</button>
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


<div class="d-flex flex-column justify-content-center align-items-center" style="width: 100%; margin-top: 80px;">
	<%
		FootballMatchDAO footballMatchDAO = (FootballMatchDAO) request.getAttribute("footballMatchDAO");
		UserBetsDAO userBetsDAO = (UserBetsDAO) request.getAttribute("userBetsDAO");
		UserBets[] userBets = (UserBets[]) session.getAttribute("histories");
		ArrayList<FootballMatch> footballMatches = new ArrayList<FootballMatch>();
		try{
			for (int i = 0; i < footballMatchDAO.getCount(); i++) {
				footballMatches.add(footballMatchDAO.read(i+1));
			}
		}catch (RollbackException ex){}
		int j = 0;
		int match_id = 0;
	%>
	<c:forEach var="history" items="${histories}">
		<%
			match_id = userBets[j].getMatch_id();
			FootballMatch footballMatch = footballMatchDAO.read(match_id);
		%>
		<div class="d-flex flex-column justify-content-center" style="width: 500px">
			<p style="text-align: center;">${history.date}</p>

			<div class="d-flex justify-content-around" style="width: 500px;">
				<img src="../img/<%=footballMatch.getFootballClubName1()%>.png" class="img">
				<p style="align-self: center; font-size: 30px"><%=footballMatch.getScore1()%> : <%=footballMatch.getScore2()%></p>
				<img src="../img/<%=footballMatch.getFootballClubName2()%>.png" class="img">
			</div>
			<div class="d-flex justify-content-between" style="padding: 5px 30px;">
				<h4><%=footballMatch.getFootballClubName1()%></h4>
				<h4><%=footballMatch.getFootballClubName2()%></h4>
			</div>
			<div class="d-flex justify-content-between">
				<p>Bet: ${history.amount}</p>

				<%
					if(footballMatch.getStatus().equals("Done")){
					String result = "Win";
					UserBets userBet = userBets[j];
					int cf_position = userBet.getCoef_position();
					int pos;
					if(footballMatch.getScore1() == footballMatch.getScore2()){
						pos = 2;
					}else if(footballMatch.getScore1() < footballMatch.getScore2()){
						pos = 3;
					}else {
						pos = 1;
					}

					if(pos != cf_position){
						result = "Lose";
					}
					String bet_res;
					double cef;
					if(cf_position == 1){
						bet_res = "WIN1 : " + footballMatch.getCoef1();
						cef = footballMatch.getCoef1();}
					else if(cf_position == 2){
						bet_res = "DRAW : " + footballMatch.getCoef2();
						cef = footballMatch.getCoef2();
					}
					else {
						bet_res = "WIN2 : " + footballMatch.getCoef3();
						cef = footballMatch.getCoef3();
					}
				%>
				<%if(result.equals("Lose")){%>
				<p style="background-color: red"><%=result%></p>
				<%}else{%>
				<p style="background-color: lightgreen"><%=result%></p>
				<%}%>

			</div>
			<div class="d-flex justify-content-between">
				<p ><%=bet_res%></p>
				<%if(result.equals("Lose")){%>
				<p style="background-color: red">-<%=userBets[j].getAmount()%></p>
				<%}else{%>
				<p style="background-color: lightgreen">+<%=userBets[j].getAmount() * cef%></p>
				<%}%>
			</div>
			<%} else {%>
				<p style="background-color: lightgreen">The match hasn't finished</p>
			<%}
				j++;%>
		</div>
	</c:forEach>

	<hr style="width:60%;text-align:left;margin-left:0">
</div>



</body>
</html>