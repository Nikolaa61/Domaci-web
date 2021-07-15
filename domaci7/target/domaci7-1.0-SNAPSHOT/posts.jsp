<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP - Postovi</title>
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


<div id="container" class="container" style="width: 62.5rem; margin-top: 40px">
    <div class="container" style="width: 80rem;" id="posts">


    </div>
    <br>
    <br>


    <button id="newPost" style="margin-left: 15px" class="btn btn-primary">New Post</button>


    <br>
    <br>

    <div id="noviPost" style="display:none">
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
            <button id="savePost" class="btn btn-primary">Save Post</button>
        </div>
    </div>

    <div id="artikal" class="card-body" style="display: none">
        <h1 id="h1"></h1>
        <p id="p1">
            <%--            <%=post.getDate().get(Calendar.DATE)+"."+(post.getDate().get(Calendar.MONTH)+1)+"."+post.getDate().get(Calendar.YEAR)+"."%>--%>
            <%--            <br>--%>
            <%--            <small><%=post.getAutor()%></>--%>

        </p>
        <p id="p2"/> <%-- content --%>
        <h3>Comments</h3>
        <div id="komentari">

        </div>
        <h6>New comment</h6>

        <div class="mt-3">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>

        <div class="mt-3">
            <label for="comment">Comment</label>
            <textarea class="form-control" rows="5" id="comment" name="comment"></textarea>
        </div>

        <div class="mt-3">
            <button id="dugmic" name="dugmic" class="btn btn-primary">Comment</button>
        </div>

    </div>

</div>

<script>
    function addArtikal(post, comments) {
        const prozor = document.getElementById("artikal");
        prozor.style.display = "block";
        const naslov = document.getElementById("h1");
        naslov.innerHTML = post.title;
        const about = document.getElementById("p1");
        about.innerHTML = post.date + "<br><small>" + post.autor + "</small>";
        const content = document.getElementById("p2");
        content.innerHTML = post.content;

        const kontejner = document.getElementById('komentari');
        kontejner.innerHTML = '';
        for (const comment of comments) {
            addComment(comment);
        }


        const dugmee = document.getElementById("dugmic");

        const name = document.getElementById('name');
        const comment = document.getElementById('comment');

        dugmee.onclick = function (e) {
            console.log("DESIO SE KLIK");
            fetch('/api/comments', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    autor: name.value,
                    tekst: comment.value,
                    idPosta: post.id
                })
            }).then(response => {
                return response.json();
            }).then(commentt => {
                addComment(commentt);
                name.value = '';
                comment.value = '';
            });
        }
    }




    fetch('/api/posts', {
        method: 'GET'
    }).then(response => {
        return response.json();
    }).then(posts => {
        for (const post of posts) {
            addPostElements(post);
        }
    });

    function addComment(comment) {
        const kontejner = document.getElementById('komentari');
        const autor = document.createElement('h5');
        const sadrzaj = document.createElement('p');

        autor.innerHTML = comment.autor;
        sadrzaj.innerHTML = comment.tekst;
        kontejner.appendChild(autor);
        kontejner.appendChild(sadrzaj);
    }

    function addPostElements(post) {
        const postLinks = document.getElementById('posts');

        const linkWrapperDiv = document.createElement('div');
        linkWrapperDiv.classList.add('border');
        const h5Card = document.createElement('h5');
        h5Card.classList.add('card-title');
        h5Card.innerHTML = post.title;
        const pElem = document.createElement('p');
        pElem.classList.add('card-text');
        pElem.innerHTML = post.content.substring(0, 400) + " ...";
        const postLink = document.createElement('a');
        postLink.innerText = "Opširnije...";
        postLink.href = '/posts/' + post.id;

        linkWrapperDiv.onclick = function (e) {
            fetch('/api/posts/' + post.id, {
                method: 'GET'
            }).then(response => {
                console.log(response);
                return response.json();
            }).then(post => {
                addPost(post);
            }).catch((err) => {
                console.log(err);
            })
        }


        linkWrapperDiv.appendChild(h5Card);
        linkWrapperDiv.appendChild(pElem);
        linkWrapperDiv.appendChild(postLink);
        postLinks.appendChild(linkWrapperDiv);
    }

    function addPost(post){
        fetch('/api/comments/' + post.id, {
            method: 'GET',
            headers : {
                'Accept': 'application/json'
            }
        }).then(response => {
            return response.json();
        }).then(komentari => {
            addArtikal(post, komentari);
        }).catch((err) => {
            console.log(err);
        })
    }

    document.getElementById("newPost").addEventListener('click', function (e) {
        const noviPostForma = document.getElementById('noviPost');
        if (noviPostForma.style.display == "none") {
            noviPostForma.style.display = "block";
        } else {
            noviPostForma.style.display = "none";
        }
    })

    document.getElementById("savePost").addEventListener('click', function (e) {
        e.preventDefault();

        const autorInput = document.getElementById('author');
        const titleInput = document.getElementById('title');
        const contentInput = document.getElementById('content');

        const title = titleInput.value;
        const content = contentInput.value;
        const autor = autorInput.value;

        fetch('/api/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title: title,
                content: content,
                autor: autor
            })
        }).then(response => {
            return response.json();
        }).then(post => {
            addPostElements(post);
            autorInput.value = '';
            titleInput.value = '';
            contentInput.value = '';

            const noviPostForma = document.getElementById('noviPost');
            noviPostForma.style.display = "none";
        });
    })
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
        crossorigin="anonymous"></script>
</body>
</html>