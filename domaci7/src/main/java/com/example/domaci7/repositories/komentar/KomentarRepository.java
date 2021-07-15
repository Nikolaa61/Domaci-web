package com.example.domaci7.repositories.komentar;

import com.example.domaci7.entities.Komentar;

import java.util.List;

public interface KomentarRepository {
    public Komentar addKomentar(Komentar komentar);
    public List<Komentar> allKoments(Integer idPosta);
}
