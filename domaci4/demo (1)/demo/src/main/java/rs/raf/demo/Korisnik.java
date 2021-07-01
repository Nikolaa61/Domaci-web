package rs.raf.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Korisnik {
    private String sessionId;
    private List<String> odabranaJela = new ArrayList<>();

    public Korisnik(String sessionId, List<String> odabranaJela) {
        this.sessionId = sessionId;
        this.odabranaJela = odabranaJela;
    }

    public String getSessionId() {
        return sessionId;
    }

    public List<String> getOdabranaJela() {
        return odabranaJela;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setOdabranaJela(List<String> odabranaJela) {
        this.odabranaJela = odabranaJela;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Korisnik korisnik = (Korisnik) o;
        return Objects.equals(sessionId, korisnik.sessionId);
    }
}
