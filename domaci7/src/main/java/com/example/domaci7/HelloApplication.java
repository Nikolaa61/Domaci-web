package com.example.domaci7;

import com.example.domaci7.repositories.komentar.KomentarRepository;
import com.example.domaci7.repositories.komentar.MySqlKomentarRepository;
import com.example.domaci7.repositories.post.MySqlPostRepository;
import com.example.domaci7.repositories.post.PostRepository;
import com.example.domaci7.services.KomentarService;
import com.example.domaci7.services.PostService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {
    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlPostRepository.class).to(PostRepository.class).in(Singleton.class);

                this.bindAsContract(PostService.class);
            }
        };

        AbstractBinder binder1 = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlKomentarRepository.class).to(KomentarRepository.class).in(Singleton.class);

                this.bindAsContract(KomentarService.class);
            }
        };
        // MOGUCA GRESKA 2 BINDERA !!!!!!!!!!!!!!!!!!!! ******************* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        register(binder);
        register(binder1);

        // Ucitavamo resurse
        packages("com.example.domaci7.resources");
    }
}