package org.esempio.Repository;

import org.esempio.Model.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 @Repository:
 - Dice a Spring: "questa è una classe di accesso ai dati (DAO)"
 - Viene rilevata automaticamente grazie a @ComponentScan
 - Spring crea un OGGETTO (bean) di questa classe e lo gestisce nel container

 ("speakerRepository"):
 - Nome del bean dentro il container Spring
 - Serve quando lo recuperi manualmente o hai ambiguità

 @Profile("dev"):
 - Questo bean ESISTE solo se il profilo attivo è "dev"
 - Se non attivi il profilo → questo bean NON viene creato
*/
@Repository("speakerRepository")
@Profile("dev")
public class StubSpeakerRepositoryImpl implements SpeakerRepository {

    /*
     Questa è una dipendenza: Calendar
     Non la crei tu con "new", ma la fornisce Spring (Dependency Injection)
    */
    public Calendar cal;

    /*
     @Value + SpEL (Spring Expression Language):

     #{ ... } → indica un'espressione dinamica
     T(java.lang.Math) → accede alla classe Math
     random() → chiama Math.random()

     Risultato:
     - ogni volta che il bean viene creato
     - Spring assegna un numero casuale tra 0 e 100
    */
    @Value("#{T(java.lang.Math).random() * 100}")
    private double seedNum;

    /*
     @Autowired su setter:

     - Dice a Spring: "iniettami un oggetto Calendar qui"
     - Spring cerca un bean di tipo Calendar nel contesto
     - Lo trova in AppConfig (@Bean cal())

     Quando viene chiamato?
     DOPO la creazione dell'oggetto, prima dell'uso
    */
    @Autowired
    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    @Override
    public List<Speaker> findAll() {

        /*
         Questa è una implementazione "stub":
         - non legge da DB
         - crea dati finti a mano
        */
        List<Speaker> speakers = new ArrayList<>();

        Speaker speaker = new Speaker();
        speaker.setFirstName("Bryan");
        speaker.setLastName("Hansen");

        // usa il valore generato da Spring
        speaker.setSeedNum(seedNum);

        /*
         cal NON è null perché:
         - Spring ha già eseguito @Autowired
         - quindi il bean Calendar è stato iniettato
        */
        System.out.println("cal: " + cal.getTime());

        speakers.add(speaker);

        return speakers;
    }
}