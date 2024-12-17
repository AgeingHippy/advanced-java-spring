package com.codingnomads.corespring.lab;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;


@Data
public class Engine implements BeanNameAware {
    private int horsepower;
    private String name;

    public Engine(int horsepower) {
        this.horsepower = horsepower;
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
