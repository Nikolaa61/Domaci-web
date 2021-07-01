package rs.raf.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "napravljenaPorudzbina", value = "/napravljenaPorudzbina")
public class NapravljenaPorudzbinaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Korisnik korisnik = null;
        for(Korisnik k : RucakServlet.korisnici){
            if (k.getSessionId().equals(req.getSession().getId())){
                korisnik = k;
            }
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Vec ste porucili:<br>" + korisnik.getOdabranaJela().get(0) +"<br>" +
                korisnik.getOdabranaJela().get(1) +"<br>" +
                korisnik.getOdabranaJela().get(2) +"<br>" +
                korisnik.getOdabranaJela().get(3) +"<br>" +
                korisnik.getOdabranaJela().get(4) +"<br>" +"</h1>");
        out.println("</body></html>");
    }
}
