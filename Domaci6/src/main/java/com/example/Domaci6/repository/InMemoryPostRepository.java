package com.example.Domaci6.repository;

import com.example.Domaci6.models.Post;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryPostRepository implements IPostRepository{
    private static InMemoryPostRepository instance = null;
    private int id = 3;
    private static final List<Post> posts = new CopyOnWriteArrayList<>();

    private InMemoryPostRepository(){
        posts.add(new Post("Nikola Boskovic", "Jedan od najboljih Samsungovih telefona odlazi u zasluženu penziju",
                "S8 je pokrenuo revoluciju Galaxy uređaja, sa širokim ekranom od ivice do ivice, sa minimalnim okvirima što ga je činilo jednim od najlepših telefona u tom trenutku\nČetiri godine nakon lansiranja, Samsung Galaxy S8 je dobio poslednje bezbednosno ažuriranje. Galaxy S8 serija je bila Samsungov trijumf kada se pojavila na tržištu 2017. godine, a model je tada skrenuo pažnju svojim smelim dizajnom koji je privukao brojne korisnike.\n" +
                        "\n" +
                        "S8 je pokrenuo revoluciju Galaxy uređaja, sa širokim ekranom od ivice do ivice, sa minimalnim okvirima što ga je činilo jednim od najlepših telefona u tom trenutku. Pored estetike, uređaj je ponudio odličan hardver i sjajnu kameru, u kombinaciji sa izuzetno sofisticiranom softverskom implementacijom, podseća The Verge.\nSamsung je nedavno uveo politiku pružanja četvorogodišnjeg bezbednosnog ažuriranja Galaxy uređajima, uključujući i modele S i A-serije. Premium uređaji imaju tendenciju da dobijaju mesečna ažuriranja, bar tokom prvih nekoliko godina, prenosi PC Press.\n" +
                        "\nSmatra se da je ovakav potez jedna od najboljih smernica podrške u mobilnoj industriji koja je u rangu sa Apple-ovim dugogodišnjim rasporedom ažuriranja. Dok su S8 i S8+ dostigli kraj perioda podrške, S8 Active je još uvek na rasporedu za kvartalna ažuriranja, a S8 Lite je na dvogodišnjem rasporedu.",
                new GregorianCalendar(2021, 5, 25), 0));
        posts.add(new Post("Pera Peric", "Hakeri imaju nov način za prisluškivanje Androida, stručnjaci savetuju da što pre ažurirate telefone", "Bezbednosni stručnjaci su otkrili maliciozni softver instaliran u modemu američke kompanije Qualcomm, što bi moglo da utiče na 40 odsto Android korisnika\nZbog novog, nedavno otkirvenog, bezbednosnog propusta kod Android uređaja, oko polovine svih korisnika moglo bi da se nađe na meti hakera, koji bi mogli da slušaju njihove razgovore ili da čitaju njihove poruke.\n" +
                "\n" +
                "Brojni skorašnji napadi na Adroid uređaje vršeni su putema lažnih ili malicioznih aplikacija, koje bi po instaliranju slale informacije sa uređaja. Ova nova vrsta napada, međutim, deluje mnogo ozbiljnije, pošto instalira određene komponente u sam uređaj.\nBezbednosni stručnjaci kompanije Check Point otkrili su maliciozni softver instaliran u modemu američke kompanije Qualcomm. Njihove komponente koriste telefoni Samsung, OnePlus i Google, pa se procenjuje da bi propust mogao da utiče na 40 odsto korisnika Android telefona.\n" +
                "\n" +
                "Za ranjivost su odgovorni Qualcommovi MSM modemi (Mobile Station Modems), serija čipova za mobilne uređaje zadužena za prijem i slanje internet signala. Istraga je pokazala da propust može da omogući kontrolisanje uređaja \"sa strane\".", new GregorianCalendar(2021, 3, 20), 1));
        posts.add(new Post("Marko Markovic", "Ovaj gedžet će vam uskoro govoriti da li smete da sednete za volan posle popijene čaše pića", "Senzori za merenje šećera u krvi, srčanog pritiska i nivoa alkohola u krvi, mogli bi uskoro da se nađu nekom od budućih modela Apple Watch pametnih satova\nNaime, britanska kompanija Rockley Photonics je, u svom nedavnom izveštaju, potvrdila da je njen najveći klijent u poslednje dve godine upravo kompanija Apple, sa kojom će i dalje nastaviti saradnju na novim proizvodima.\n" +
                "\n" +
                "Njhov rad na razvoju senzora za merenje šećera u krvi, krvnog pritiska i nivoa alkohola u krvi, ukazuje da bi se bar jedan ili više takvih senzora mogli naći u nekom od budućih modela pametnih satova sa logoom zagrižene jabuke.\n" +
                "\n" +
                "Američka kompanija želi da njen pametni sat postane pouzdaniji senzorski uređaj za praćenje zdravlja, posebno kada je reč o merenju šećera u krvi koji ne zahteva upotrebu igala.Ukoliko bi Apple Watch dobio ovaj napredni senzorski sistem, taj pametni sat bi bio u prednosti u odnosu na konkurenciju, budući da bi pacijenti sa šećernom bolesti dobili novi neinvazivni prenosni dijagnostički uređaj koji bi im unapredio kvalitet života.\nOsim toga, senzori za merenje srčanog pritiska i nivoa alkohola u krvi pomogli bi mnogim vozačima da sami sebi odgovore na pitanje da li smeju posle popijene čaše pića da sednu za volan bez straha od policije ili mogućeg izazivanja saobraćajne nezgode.",
                new GregorianCalendar(2021, 1, 10), 2));
    }

    public static InMemoryPostRepository getInstance(){
        if (instance == null){
            synchronized (InMemoryPostRepository.class){
                if(instance == null){
                    instance = new InMemoryPostRepository();
                }
            }
        }

        return  instance;
    }
    public List<Post> all() {
        List<Post> postList = new ArrayList<>();
        posts.iterator().forEachRemaining(e -> {
            postList.add(e);
        });

        return postList;
    }

    public void insert(Post post) {
        posts.add(post);
        post.setId(id);
        id++;
    }

    @Override
    public Post find(int id) {
        Iterator itr = posts.iterator();

        while (itr.hasNext()){
            Post p = (Post) itr.next();
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }
}
