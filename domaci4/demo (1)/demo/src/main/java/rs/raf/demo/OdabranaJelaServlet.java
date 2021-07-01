package rs.raf.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet(name = "odabranaJela", value = "/odabrana-jela")
public class OdabranaJelaServlet extends HttpServlet {
    String lozinka;
    public void init() {
        try{
            Scanner sc = new Scanner(new File("E:\\Downloads\\demo (1)\\password.txt"));
            lozinka = sc.nextLine();
            sc.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tmp = req.getQueryString().split("=")[1];
        String pw = tmp.substring(1, tmp.length()-1);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if (req.getParameter("lozinka") != null && req.getParameter("lozinka").equals(lozinka)){
            Map<String, Integer> ponedeljak = new HashMap<>();
            for(Korisnik k : RucakServlet.korisnici){
                if(!ponedeljak.containsKey(k.getOdabranaJela().get(0))){
                    ponedeljak.put(k.getOdabranaJela().get(0), 1);
                }else{
                    ponedeljak.put(k.getOdabranaJela().get(0), ponedeljak.get(k.getOdabranaJela().get(0)) + 1);
                }
            }
            Map<String, Integer> utorak = new HashMap<>();
            for(Korisnik k : RucakServlet.korisnici){
                if(!utorak.containsKey(k.getOdabranaJela().get(1))){
                    utorak.put(k.getOdabranaJela().get(1), 1);
                }else{
                    utorak.put(k.getOdabranaJela().get(1), utorak.get(k.getOdabranaJela().get(1)) + 1);
                }
            }
            Map<String, Integer> sreda = new HashMap<>();
            for(Korisnik k : RucakServlet.korisnici){
                if(!sreda.containsKey(k.getOdabranaJela().get(2))){
                    sreda.put(k.getOdabranaJela().get(2), 1);
                }else{
                    sreda.put(k.getOdabranaJela().get(2), sreda.get(k.getOdabranaJela().get(2)) + 1);
                }
            }
            Map<String, Integer> cetvrtak = new HashMap<>();
            for(Korisnik k : RucakServlet.korisnici){
                if(!cetvrtak.containsKey(k.getOdabranaJela().get(3))){
                    cetvrtak.put(k.getOdabranaJela().get(3), 1);
                }else{
                    cetvrtak.put(k.getOdabranaJela().get(3), cetvrtak.get(k.getOdabranaJela().get(3)) + 1);
                }
            }
            Map<String, Integer> petak = new HashMap<>();
            for(Korisnik k : RucakServlet.korisnici){
                if(!petak.containsKey(k.getOdabranaJela().get(4))){
                    petak.put(k.getOdabranaJela().get(4), 1);
                }else{
                    petak.put(k.getOdabranaJela().get(4), petak.get(k.getOdabranaJela().get(4)) + 1);
                }
            }

            out.println("<html><body>");
            out.println("<form method=POST action=/odabrana-jela>");
            out.println("<input type=\"submit\" value=\"Ocisti\"></form>");
            out.println("<h1>Ponedeljak</h1><br>");
            for (Map.Entry<String,Integer> entry : ponedeljak.entrySet()){
                out.println(entry.getKey() +"        " + entry.getValue() +"<br>");
            }

            out.println("<h1>Utorak</h1><br>");
            for (Map.Entry<String,Integer> entry : utorak.entrySet()){
                out.println(entry.getKey() +"        " + entry.getValue() +"<br>");
            }

            out.println("<h1>Sreda</h1><br>");
            for (Map.Entry<String,Integer> entry : sreda.entrySet()){
                out.println(entry.getKey() +"        " + entry.getValue() +"<br>");
            }

            out.println("<h1>Cetvrtak</h1><br>");
            for (Map.Entry<String,Integer> entry : cetvrtak.entrySet()){
                out.println(entry.getKey() +"        " + entry.getValue() +"<br>");
            }

            out.println("<h1>Petak</h1><br>");
            for (Map.Entry<String,Integer> entry : petak.entrySet()){
                out.println(entry.getKey() +"        " + entry.getValue() +"<br>");
            }

            out.println("</body></html>");
        } else{
            out.println("<html><body><h1>POGRESNA LOZINKA</h1>");
            out.println("</body></html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RucakServlet.korisnici.clear();
    }
}
