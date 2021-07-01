<%--
  Created by IntelliJ IDEA.
  User: bosko
  Date: 6.5.2021.
  Time: 03:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Novi post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

</head>
<body>
<form method="POST" action="<%=application.getContextPath() + "/posts"%>">
    <div class="container">
        <div class="m-3">
            <label for="author">Author</label>
            <input type="text" class="form-control" id="author" name="author">
        </div>

        <div class="m-3">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title">
        </div>

        <div class="m-3">
            <label for="content">Content</label>
            <textarea class="form-control" rows="5" id="content" name="content"></textarea>
        </div>

        <div class="m-3">
            <button type="submit" class="btn btn-primary">Save Post</button>
        </div>
    </div>


</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
        crossorigin="anonymous"></script>

</body>
</html>
