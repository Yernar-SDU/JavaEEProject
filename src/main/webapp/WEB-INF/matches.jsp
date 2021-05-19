<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matches</title>
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
            <button name="action" value="submit" type="submit" class="btn btn-outline-dark">Create</button>
        </form>
        <form action="matches.do" method="get" style="margin-bottom: 15px;">
            <button name="action" value="submit" type="submit" class="btn btn-outline-dark">Matches</button>
        </form>
        <form action="users.do" method="get">
            <button name="action" value="submit" type="submit" class="btn btn-outline-dark">Users</button>
        </form>
    </div>

    <div class="d-flex flex-column" style="width: 85%;">
        <div class="d-flex justify-content-end align-items-end" style="padding: 20px;">
            <form action="login.do" method="get">
                <button type="submit" class="btn btn-outline-dark">Logout</button>
            </form>
        </div>

        <div class="justify-content-center align-items-center" style="width: 1070px; margin: 20px 70px;  padding: 20px; background-color: white; border-radius: 15px;">
            <p>Matches</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="row">#</th>
                    <th scope="col">First team</th>
                    <th scope="col">Second team</th>
                    <th scope="col">W1</th>
                    <th scope="col">X</th>
                    <th scope="col">W2</th>
                    <th scope="col">League</th>
                    <th scope="col">Score</th>
                    <th scope="col">Status</th>
                    <th scope="col">Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="match" items="${matches}">
                    <tr>
                        <td>${match.id}</td>
                        <td>${match.footballClubName1}</td>
                        <td>${match.footballClubName2}</td>
                        <td>${match.coef1}</td>
                        <td>${match.coef2}</td>
                        <td>${match.coef3}</td>
                        <td>${match.cupName}</td>
                        <td>${match.score1} - ${match.score2}</td>
                        <td>${match.status}</td>
                        <td>
                            <form id="delete" method="post" action="deleteMatch.do">
                                <input type="hidden" name="id" value="${match.id}">
                                <button type="submit" class="btn btn-outline-dark">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>