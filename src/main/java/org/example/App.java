package org.example;

import org.example.configuration.SpringCofig;
import org.example.service.RestServise;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringCofig.class);
        RestServise service = context.getBean(RestServise.class);
        System.out.println(service.getResult());
    }
}
