package org.esempio;

import org.esempio.Util.CalendarFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

/*
 @Configuration:
 - Dice a Spring: questa classe definisce bean
 - È equivalente a un XML di configurazione
*/
@Configuration

/*
 @ComponentScan:
 - Dice a Spring: scansiona questo package
 - Trova automaticamente:
     @Service
     @Repository
     @Component
*/
@ComponentScan({"org.esempio"})
public class AppConfig {

    /*
     @Bean:
     - metodo che produce un bean
     - Spring esegue questo metodo e salva il risultato

     name="cal":
     - nome del bean nel container
    */
    @Bean(name = "cal")
    public CalendarFactory calFactory(){

        CalendarFactory factory = new CalendarFactory();

        // modifica stato interno PRIMA che venga usato
        factory.addDays(2);

        return factory;
    }

    /*
     Questo bean restituisce un Calendar

     ATTENZIONE:
     - usa il FactoryBean
     - getObject() restituisce l'oggetto reale

     Quindi Spring registra:
     → un bean di tipo Calendar
    */
    @Bean
    public Calendar cal() throws Exception{
        return calFactory().getObject();
    }
}