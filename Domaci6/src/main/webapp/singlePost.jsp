<%--
  Created by IntelliJ IDEA.
  User: bosko
  Date: 6.5.2021.
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.temporal.TemporalAccessor" %>
<%@ page import="java.util.Calendar" %>

<jsp:useBean id="post" scope="request" class="com.example.Domaci6.models.Post"/>
<html>
<head>
    <title>Post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

</head>
<body>
<div class="container" style="width: 62.5rem;">
    <div class="card-body">
        <h1><%= post.getTitle()%>
        </h1>
        <p>
            <%=post.getDate().get(Calendar.DATE)+"."+(post.getDate().get(Calendar.MONTH)+1)+"."+post.getDate().get(Calendar.YEAR)+"."%>
            <br>
            <small><%=post.getAutor()%>
            </>

        </p>

        <%=post.getContent()%>


        <h3>Comments</h3>
        <c:forEach var="komentar" items="${post.komentari}">
            <h5>${komentar.autor}</h5>
            <p>${komentar.tekst}</p>
        </c:forEach>

        <h6>New comment</h6>
        <form method="POST" action="<%=application.getContextPath() + "/posts/" + post.getId()%>">
            <div class="mt-3">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name">
            </div>

            <div class="mt-3">
                <label for="comment">Comment</label>
                <textarea class="form-control" rows="5" id="comment" name="comment"></textarea>
            </div>

            <div class="mt-3">
                <button type="submit" class="btn btn-primary">Comment</button>
            </div>

        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
        crossorigin="anonymous"></script>

</body>
</html>
