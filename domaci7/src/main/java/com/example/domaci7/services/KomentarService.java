package com.example.domaci7.services;

import com.example.domaci7.entities.Komentar;
import com.example.domaci7.repositories.komentar.KomentarRepository;

import javax.inject.Inject;
import java.util.List;

public class KomentarService {

    @Inject
    private KomentarRepository komentarRepository;

    public Komentar addKomentar(Komentar komentar){
        return this.komentarRepository.addKomentar(komentar);
    }

    public List<Komentar> allKoments(Integer idPosta){ return this.komentarRepository.allKoments(idPosta);}

}
