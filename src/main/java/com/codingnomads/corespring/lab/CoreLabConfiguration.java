package com.codingnomads.corespring.lab;

import org.springframework.context.annotation.*;

@Configuration
@ImportResource({"classpath*:xml-config/corespring-lab.xml"})
public class CoreLabConfiguration {

    @Bean
    @DependsOn({"splitRim"})
    @Scope("singleton")
    public Vehicle sedan() {
        return new Sedan(100);
    }

    @Bean
    @DependsOn({"electric","alloy"})
//    @Scope("prototype")
    public Vehicle tesla() {
        return new Tesla(150);
    }

    @Bean
    public Engine electric() {
        return new Engine(50);
    }

    @Bean
    public Engine diesel() {
        return new Engine(50);
    }

    @Bean
    @Primary
    public Engine petrol() {
        return new Engine(25);
    }

    @Bean
    @Scope("prototype")
    @Profile("test")
    public Driver driver() {
        return new Driver();
    }

    @Bean
    @Scope("prototype")
    @Profile("deploy")
    public Driver racingDriver() {
        return new Driver();
    }

}
