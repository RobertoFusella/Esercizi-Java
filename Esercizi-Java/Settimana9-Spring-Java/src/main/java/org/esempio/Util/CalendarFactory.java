package org.esempio.Util;

import org.springframework.beans.factory.FactoryBean;
import java.util.Calendar;

// FactoryBean: serve per creare oggetti personalizzati in Spring
public class CalendarFactory implements FactoryBean<Calendar>{

    // Istanza di Calendar
    private Calendar instance = Calendar.getInstance();

    // Metodo chiamato da Spring per ottenere l'oggetto
    @Override
    public Calendar getObject() throws Exception {
        return instance;
    }

    // Tipo dell'oggetto prodotto
    @Override
    public Class<?> getObjectType() {
        return Calendar.class;
    }

    // Metodo custom per aggiungere giorni
    public void addDays(int num){
        instance.add(Calendar.DAY_OF_YEAR, num);
    }
}