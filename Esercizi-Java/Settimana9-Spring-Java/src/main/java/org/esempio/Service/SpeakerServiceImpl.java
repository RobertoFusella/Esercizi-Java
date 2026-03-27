package org.esempio.Service;

import jakarta.annotation.PostConstruct;
import org.esempio.Model.Speaker;
import org.esempio.Repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 @Service:
 - specializzazione di @Component
 - Indica logica di business (layer intermedio)
 - Spring lo registra come bean

 ("speakerService"):
 - nome del bean nel container
*/
@Service("speakerService")

/*
 @Scope(SINGLETON):
 - Una sola istanza per tutta l'app
 - È il comportamento di default in Spring

 (quindi questa annotazione è ridondante, ma didattica)
*/
@Scope(value= BeanDefinition.SCOPE_SINGLETON)

/*
 Attivo solo con profilo "dev"
*/
@Profile("dev")
public class SpeakerServiceImpl implements SpeakerService {

    /*
     Dipendenza verso il repository:
     - il service NON crea il repository
     - lo riceve da Spring (DI)
    */
    private SpeakerRepository repository;

    /*
     Costruttore vuoto:
     - Spring PUÒ usarlo
     - ma verrà ignorato se c'è un costruttore con @Autowired
    */
    public SpeakerServiceImpl() {
        System.out.println("SpeakerServiceImpl no args constructor");
    }

    /*
     COSTRUTTORE PRINCIPALE (Dependency Injection)

     @Autowired:
     - Dice a Spring: usa questo costruttore
     - Spring cerca un bean di tipo SpeakerRepository
     - Trova StubSpeakerRepositoryImpl
     - Lo passa qui automaticamente

     Questo è il modo MIGLIORE (constructor injection)
    */
    @Autowired
    public SpeakerServiceImpl(SpeakerRepository repository) {
        System.out.println("SpeakerServiceImpl repository constructor");
        this.repository = repository;
    }

    /*
     @PostConstruct:
     - Metodo eseguito DOPO:
         1. costruttore
         2. injection (@Autowired)
         3. @Value

     Serve per inizializzazioni finali
    */
    @PostConstruct
    private void initialize(){
        System.out.println("We're called after the constructors");
    }

    @Override
    public List<Speaker> findAll() {

        /*
         Il service NON fa logica qui
         → delega al repository

         FLOW:
         Controller → Service → Repository
        */
        return repository.findAll();
    }

    /*
     Setter injection (non usato)

     Se attivi @Autowired qui:
     - Spring userà questo invece del costruttore (dipende dal contesto)

     OGGI:
     - si preferisce constructor injection
    */
    //@Autowired
    public void setRepository(SpeakerRepository repository) {
        System.out.println("SpeakerServiceImpl setter");
        this.repository = repository;
    }
}