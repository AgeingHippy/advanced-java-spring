/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.profileannotation;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProfileAnnotationDemo {
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(ProfileAnnotationDemo.class);
        try {
            final SpringDeveloper springDeveloper = ctx.getBean(SpringDeveloper.class);
            System.out.println("SpringDeveloper bean found");
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("SpringDeveloper bean not found");
        }
        try {
            final PojoDeploy pojoDeploy = ctx.getBean(PojoDeploy.class);
            System.out.println("PojoDeploy bean found");
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("PojoDeploy bean not found");
        }

        ctx.close();
    }
}
