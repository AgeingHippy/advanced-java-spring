package com.codingnomads.corespring.lab;

import lombok.*;
import org.springframework.beans.factory.BeanNameAware;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Wheel implements BeanNameAware {
    private int price;
    private String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
