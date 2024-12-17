package com.codingnomads.corespring.lab;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;


public class Driver implements BeanNameAware {
    private String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @PostConstruct
    public void startUp() {
        System.out.println("Driver "+ name + " is getting dressed.");
    }

    @PreDestroy
    public void endOfDay() {
        System.out.println("Driver "+ name + " is going home.");
    }

}
