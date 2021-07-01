package rs.raf.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@WebServlet(name = "rucakServlet", value = "/izaberi-jela")
public class RucakServlet extends HttpServlet {

    private String message;
    private List<String> ids = new ArrayList<>();
    public static List<Korisnik> korisnici = new ArrayList<>();
    private List<String> ponedeljak = new ArrayList<>();
    private List<String> utorak = new ArrayList<>();
    private List<String> sreda = new ArrayList<>();
    private List<String> cetvrtak = new ArrayList<>();
    private List<String> petak = new ArrayList<>();
    // Promenljive u servletu nisu thread safe!
    private int counter = 0;

    public RucakServlet() {
        System.out.println("Constructor");
    }

    public void init() {
        System.out.println("init method");
        Scanner sc = null;
        try {
            sc = new Scanner(new File("E:\\Downloads\\demo (1)\\ponedeljak.txt"));
            while (sc.hasNextLine()){
                ponedeljak.add(sc.nextLine());
            }
            sc = new Scanner(new File("E:\\Downloads\\demo (1)\\utorak.txt"));
            while (sc.hasNextLine()){
                utorak.add(sc.nextLine());
            }
            sc = new Scanner(new File("E:\\Downloads\\demo (1)\\sreda.txt"));
            while (sc.hasNextLine()){
                sreda.add(sc.nextLine());
            }
            sc = new Scanner(new File("E:\\Downloads\\demo (1)\\cetvrtak.txt"));
            while (sc.hasNextLine()){
                cetvrtak.add(sc.nextLine());
            }
            sc = new Scanner(new File("E:\\Downloads\\demo (1)\\petak.txt"));
            while (sc.hasNextLine()){
                petak.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        message = "Hello from Servlet!";
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service method");
        super.service(req, resp);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (korisnici.isEmpty() || !korisnici.contains(request.getSession().getId())){

            System.out.println("doGet method");

            response.setContentType("text/html");

            this.counter ++;

            // Hello
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Choose your food</h1><br><br>");
            out.println("<h2>Odaberite vas rucak:</h2><br>");
            out.println("<form method=POST action=/izaberi-jela>");
            out.println("Ponedeljak<br>");
            out.println(" <select id=\"Ponedeljak\" name=\"Ponedeljak\">\n" +
                    "          <option value=\"0\">"+ponedeljak.get(0)+"</option>\n" +
                    "          <option value=\"1\">"+ponedeljak.get(1)+"</option>\n" +
                    "          <option value=\"2\">"+ponedeljak.get(2)+"</option>\n" +
                    "        </select><br>");

            out.println("Utorak<br>");
            out.println(" <select id=\"utorak\" name=\"Utorak\">\n" +
                    "          <option value=\"0\">"+utorak.get(0)+"</option>\n" +
                    "          <option value=\"1\">"+utorak.get(1)+"</option>\n" +
                    "          <option value=\"2\">"+utorak.get(2)+"</option>\n" +
                    "        </select><br>");

            out.println("Sreda<br>");
            out.println(" <select id=\"sreda\" name=\"Sreda\">\n" +
                    "          <option value=\"0\">"+sreda.get(0)+"</option>\n" +
                    "          <option value=\"1\">"+sreda.get(1)+"</option>\n" +
                    "          <option value=\"2\">"+sreda.get(2)+"</option>\n" +
                    "        </select><br>");

            out.println("Cetvrtak<br>");
            out.println(" <select id=\"cetvrtak\" name=\"Cetvrtak\">\n" +
                    "          <option value=\"0\">"+cetvrtak.get(0)+"</option>\n" +
                    "          <option value=\"1\">"+cetvrtak.get(1)+"</option>\n" +
                    "          <option value=\"2\">"+cetvrtak.get(2)+"</option>\n" +
                    "        </select><br>");

            out.println("Petak<br>");
            out.println(" <select id=\"petak\" name=\"Petak\">\n" +
                    "          <option value=\"0\">"+petak.get(0)+"</option>\n" +
                    "          <option value=\"1\">"+petak.get(1)+"</option>\n" +
                    "          <option value=\"2\">"+petak.get(2)+"</option>\n" +
                    "        </select><br>");
            out.println("<button style=\"background-color:DodgerBlue;color:white;\">Potvrdite unos</button>");
            out.println("</form><br>");
            ids.add(request.getSession().getId());
        }else{
            response.sendRedirect("/napravljenaPorudzbina");
        }

//        out.println("<h2>Korisnik: " + request.getSession().getId() + "</h2>");
//        if (request.getSession().getAttribute("jmbg") != null) {
//            out.println("<h2>JMBG: " + request.getSession().getAttribute("jmbg") + "</h2>");
//        }
//        if (request.getParameter("ime") != null && request.getParameter("prezime") != null) {
//            out.println("<h2>Ime i prezime: " + request.getParameter("ime") + " " + request.getParameter("prezime"));
//        }
//        out.println("<h3>Broj poseta: " + this.counter + "</h3>");
//
//        out.println();
//
//        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        List<String> jela = new ArrayList<>();
        jela.add(ponedeljak.get(Integer.parseInt(request.getParameter("Ponedeljak"))));
        jela.add(utorak.get(Integer.parseInt(request.getParameter("Utorak"))));
        jela.add(sreda.get(Integer.parseInt(request.getParameter("Sreda"))));
        jela.add(cetvrtak.get(Integer.parseInt(request.getParameter("Cetvrtak"))));
        jela.add(petak.get(Integer.parseInt(request.getParameter("Petak"))));

        Korisnik k = new Korisnik(request.getSession().getId(), jela);
        korisnici.add(k);
        response.sendRedirect("/potvrdjenaForma");
    }

    public void destroy() {
        System.out.println("destroy method");
    }
}
