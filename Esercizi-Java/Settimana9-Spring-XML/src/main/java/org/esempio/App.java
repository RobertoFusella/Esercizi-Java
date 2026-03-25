package org.esempio;



import org.esempio.Service.SpeakerService;
import org.esempio.Service.SpeakerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main (String args []) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //SpeakerService service = new SpeakerServiceImpl();
        SpeakerService service = appContext.getBean("speakerService",SpeakerServiceImpl.class);

        System.out.println(service.findAll().get(0).getFirstName());
    }
}