package org.esempio;

import org.esempio.Service.SpeakerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        // Avvio del contesto Spring usando configurazione Java
        ApplicationContext appContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // Recupera il bean dal contesto
        SpeakerService service =
                appContext.getBean("speakerService", SpeakerService.class);

        // Chiama il metodo e stampa i risultati
        System.out.println(service.findAll().getFirst().getFirstName());
        System.out.println(service.findAll().getFirst().getSeedNum());
    }
}