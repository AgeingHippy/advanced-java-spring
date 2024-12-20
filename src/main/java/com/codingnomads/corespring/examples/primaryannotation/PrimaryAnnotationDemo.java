/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.primaryannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PrimaryAnnotationDemo {
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(PrimaryAnnotationDemo.class);
        DesktopComputer desktopComputer = ctx.getBean(DesktopComputer.class);
        System.out.println(desktopComputer.toString());
        ctx.close();
    }
}
