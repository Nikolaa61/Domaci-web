package com.example.Domaci6.servlets;

import com.example.Domaci6.models.Komentar;
import com.example.Domaci6.models.Post;
import com.example.Domaci6.repository.IPostRepository;
import com.example.Domaci6.repository.InMemoryPostRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SinglePostServlet", value = "/posts/*")
public class SinglePostServlet extends HttpServlet {
    private IPostRepository postRepository;

    @Override
    public void init() throws ServletException {
        this.postRepository = InMemoryPostRepository.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int id = Integer.parseInt(request.getPathInfo().substring(1));
        Post post = this.postRepository.find(id);

        request.setAttribute("post", post);

        request.getRequestDispatcher("/singlePost.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        Post post = this.postRepository.find(id);

        String name = req.getParameter("name");
        String comment = req.getParameter("comment");

        Komentar komentar = new Komentar(name, comment);
        post.getKomentari().add(komentar);

        req.setAttribute("post", post);
        req.getRequestDispatcher("/singlePost.jsp").forward(req, resp);
    }
}
