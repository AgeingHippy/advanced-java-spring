package com.codingnomads.ioc.lab.initial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.codingnomads.ioc.lab.initial")
public class CodingNomadConfiguration {
    @Bean
    public Framework framework() {
        return Framework.builder().name("Spring Boot").version("3.2").build();
    }

    @Bean
    public IDE ide() {
        return IDE.builder().name("Intellij Idea").version("2023.4").build();
    }

    @Bean
    public JDK jdk() {
        return JDK.builder().name("OpenJDK").version("17").build();
    }

    @Bean
    public Repository repository() {
        return Repository.builder().name("GitHub").build();
    }

    @Bean
    public Lubricant lubricant() {
        return Lubricant.builder().name("Red Bull").build();
    }
}
