/* CodingNomads (C)2024 */
package com.codingnomads.corespring.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackageClasses = CoreLabApplication.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.codingnomads\\.corespring\\.lab\\.complete\\..*"))
public class CoreLabApplication implements CommandLineRunner {

    @Autowired
    ApplicationContext ctx;

    public static void main(String[] args) throws Exception {
//        ApplicationContext ctx =
                SpringApplication.run(CoreLabApplication.class, args);
//        doStuff(ctx);
    }


//    public static void doStuff() throws Exception {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(CoreLabConfiguration.class);
//        ctx.refresh();
//        doStuff(ctx);
//    }

    @Override
    public void run(String[] args ) throws Exception {

        Vehicle petrolCar = ctx.getBean(Sedan.class);
        Vehicle electricCar = ctx.getBean(Tesla.class);
        Vehicle myTruck = ctx.getBean("bigTruck", Semi.class);

        System.out.println(petrolCar.toString());
        System.out.println();
        System.out.println(electricCar.toString());
        System.out.println();
        System.out.println(myTruck.toString());
        System.out.println();

        Vehicle myTruck2 = ctx.getBean(Semi.class);
        System.out.println("myTruck.hashCode = " + myTruck.hashCode());
        System.out.println("myTruck2.hashCode = " + myTruck2.hashCode());

        System.out.println("== illustrate prototype scope ==");
        Driver driver1 = ctx.getBean(Driver.class);
        Driver driver2 = ctx.getBean(Driver.class);

        System.out.println("driver1.name = " + driver1.getName());
        System.out.println("driver2.name = " + driver2.getName());
        System.out.println("driver1.hashCode = " + driver1.hashCode());
        System.out.println("driver2.hashCode = " + driver2.hashCode());

        //manually destroy prototypes
        driver1.endOfDay();
        driver2.endOfDay();


        //is driver still here?
        System.out.println("driver1.name = " + driver1.getName());

        ((ConfigurableApplicationContext) ctx).close();

        //is driver still here?
        System.out.println("driver1.name = " + driver1.getName());



    }
}
