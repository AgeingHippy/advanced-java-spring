/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.valueannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ValueAnnotationDemo {
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(ValueAnnotationDemo.class);
        final Nomad nomad = ctx.getBean(Nomad.class);
        System.out.println(nomad.getGreeting());
        final String output = nomad.output();
        System.out.println(output);
        nomad.getWorkingDays().forEach(System.out::println);
        System.out.println(nomad.getDatabaseValues());
        System.out.println(nomad.nomadIdentity());
        System.out.println(String.join(",",nomad.intList));
        ctx.close();
    }
}
