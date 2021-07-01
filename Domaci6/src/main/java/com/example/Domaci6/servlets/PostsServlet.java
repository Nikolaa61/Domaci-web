package com.example.Domaci6.servlets;

import com.example.Domaci6.models.Post;
import com.example.Domaci6.repository.IPostRepository;
import com.example.Domaci6.repository.InMemoryPostRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PostsServlet", value = {"/", "/posts"})
public class PostsServlet extends HttpServlet {

    private IPostRepository postRepository;

    @Override
    public void init() throws ServletException {
        this.postRepository = InMemoryPostRepository.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("posts", this.postRepository.all());

        request.getRequestDispatcher("/posts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String autor = req.getParameter("author");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Post post = new Post(autor, title, content);

        postRepository.insert(post);

        resp.sendRedirect(getServletContext().getContextPath() + "/posts");

    }
}
