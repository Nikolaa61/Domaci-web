<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.example.Domaci6.models.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html lang="sr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Postovi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <style>
        .border {
            display: inline-block;
            padding: 10px;
            margin: 0px;
            width: 80rem;
        }
    </style>
</head>
<meta charset="utf-8"/>
<body>

<% List<Post> posts = (List<Post>) request.getAttribute("posts"); %>
<br>
<div class="container" style="width: 80rem;">
    <c:forEach var="post" items="${posts}">

        <div class="border">
            <h5 class="card-title">${post.title}</h5>
            <p class="card-text">${fn:substring(post.content, 0, 400)}</p>
            <a href="<%=application.getContextPath()%>/posts/${post.id}">Opsirnije..</a>
        </div>


    </c:forEach>
    <br>
    <br>
    <div class="mb-5">
        <form action="<%=application.getContextPath() + "/new-post"%>">
            <button type="submit" class="btn btn-primary">New Post</button>
        </form>
    </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
        crossorigin="anonymous"></script>
</body>
</html>