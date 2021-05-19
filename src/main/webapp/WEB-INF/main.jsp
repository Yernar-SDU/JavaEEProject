<%@ page import="databean.FootballMatch" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="formbean.BetForm" %>
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

</head>

<style type="text/css">
    #btn:hover{
        background-color: lightgrey;
    }

    .img{
        width: 100px;
        height: 100px;
    }

    .kf{
        font-size: 18px;
    }

    .boxed{
        display: flex;
        justify-content: space-around;
    }

    .boxed label {
        text-align: center;
        width: 100px;
        padding: 10px;
        transition: all 0.3s;
    }

    .boxed input[type="radio"] {
        display: none;
    }

    .boxed input[type="radio"]:checked + label {
        color: #fff;
        background-color: lightgreen
    }

</style>


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


<div class="d-flex" style="width: 100%;margin-top: 60px;">
    <div class="d-flex flex-column fixed-left" style=" width: 15%;left: 0; height: auto; border-right: 1; background-color: whitesmoke;min-height: 672px;">
        <form action="main.do" method="post" style="width: 100%; padding-top: 25px;">
            <div class="btn-group-vertical" style="align-items: center; width: 100%;">
                <button name="action" value="LaLiga" type="submit" class="btn">LaLiga</button>
                <button name="action" value="Liga 1" type="submit" class="btn">Liga 1</button>
                <button name="action" value="Seria A" type="submit" class="btn">Seria A</button>
                <button name="action" value="KPL" type="submit" class="btn">KPL</button>
                <button name="action" value="Bundes League" type="submit" class="btn">Bundes League</button>
                <button name="action" value="APL" type="submit" class="btn">APL</button>
                <button name="action" value="RPL" type="submit" class="btn">RPL</button>
            </div>
        </form>
    </div>
    <h3>${cup}</h3>
    <div class="d-flex flex-column justify-content-center" style="width: 85%; padding-top: 20px">
        <%int i = 0;
            FootballMatch[] footballMatches = (FootballMatch[]) session.getAttribute("footballMatches");
        %>
        <c:forEach var="match" items="${footballMatches}">
        <div class="d-flex flex-column align-items-center">
            <div class="d-flex justify-content-around" style="width: 500px;">
                <img src="../img/${match.footballClubName1}.png" class="img"/>

                <div>
                    <%
                        FootballMatch footballMatch = footballMatches[i];
                        i++;
                        if(footballMatch.getStatus().equals("Live")){
                    %>
                        <p id="${match.id}"></p>
                        <script>
                            // Set the date we're counting down to
                            var countDownDate = new Date("Jan 5, 2022 15:37:25").getTime();
                            var score1 = ${match.score1}
                            var score2 = ${match.score2}

                            // Update the count down every 1 second
                            var x = setInterval(function() {

                                // Get today's date and time
                                var now = new Date().getTime();

                                // Find the distance between now and the count down date
                                var distance = countDownDate + now;

                                // Time calculations for days, hours, minutes and seconds
                                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                                var seconds = Math.floor((distance % (1000 * 60)) / 1000);
                                if(parseInt(Math.random() * 1000) === 23){
                                    if(parseInt(Math.random() * 100) % 2 === 0){
                                        score1++
                                    }else{
                                        score2++;
                                    }
                                }

                                var score= document.getElementById("${match.id}score").innerHTML = score1 + " : " +  score2;
                                // Display the result in the element with id="demo"
                                document.getElementById("${match.id}").innerHTML =  minutes + " : " + seconds;

                                // If the count down is finished, write some text
                                if (minutes == 59) {
                                    clearInterval(x);
                                    document.getElementById("${match.id}").innerHTML = "90:00";
                                }
                            }, 1000);
                        </script>

                    <% }
                        else if(footballMatch.getStatus().equals("Done")){
                    %>
                        <p id="${match.id}">90:00</p>
                    <%}%>
                    <p id="${match.id}score" style="align-self: center; font-size: 30px">${match.score1} : ${match.score2}</p>


                </div>
                <img src="../img/${match.footballClubName2}.png" class="img"/>
            </div>
            <div class="d-flex justify-content-around" style="width: 600px;">
                <h3>${match.footballClubName1}</h3>
                <h3>${match.footballClubName2}</h3>
            </div>
            <form action="bet.do" method="get" class="d-flex flex-column" style="width: 500px">
                <div class="boxed">
<%--                    <c:forEach var="field" items="footballMatchForm">--%>
<%--                        <input type="radio" id="${match.id+match.coef1}" name="coef" value="1">--%>

<%--                    </c:forEach>--%>
                    <input type="radio" id="${match.id+match.coef1}" name="coef" value="1">
                    <label for="${match.id+match.coef1}">${match.coef1}</label>
                    <input type="radio" id="${match.id+match.coef2}" name="coef" value="2">
                    <label for="${match.id+match.coef2}">${match.coef2}</label>
                    <input type="radio" id="${match.id+match.coef3}" name="coef" value="3">
                    <label for="${match.id+match.coef3}">${match.coef3}</label>
                </div>

                <div class="d-flex justify-content-end">
                    <input type="text" name="amount" style="width: 100px;">
                    <input name="id" value="${match.id}"  style="width: 100px;" type="hidden">

                    <button name="action" type="submit" value="BET" class="btn btn-dark" style="margin-left: 5px;">BET</button>
                </div>
            </form>

            <form action="finishMatch.do" method="get">
                <button name="action" value="${match.id}" type="submit" class="btn btn-primary">Finish match</button>
            </form>
            <%
                Integer matchId = (Integer) request.getAttribute("whichMatchId");
                if(
                    request.getAttribute("whichMatchId") != null &&
                    matchId == footballMatch.getId()){%>
                <c:forEach var="error" items="${betForm.formErrors}">
                    <h3 id="${match.id}t" style="color:red"> ${error} </h3>
                </c:forEach>
            <%}%>

            </c:forEach>
            <hr style="width:60%;text-align:left;margin-left:0">
        </div>




    </div>
</div>


</body>
</html>


