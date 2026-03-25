package org.esempio;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.esempio"})
public class AppConfig {
    /*
    @Bean(name="speakerService")
    @Scope(value= BeanDefinition.SCOPE_SINGLETON)
    public SpeakerService getSpeakerService() {
        //service.setRepository(getSpeakerRepository());
        //SpeakerServiceImpl service = new SpeakerServiceImpl(getSpeakerRepository());
        SpeakerServiceImpl service = new SpeakerServiceImpl();
        return service;
    }
    */

    /*
    @Bean(name="speakerRepository")
    public SpeakerRepository getSpeakerRepository() {
        return new StubSpeakerRepositoryImpl();
    }
    */
}
