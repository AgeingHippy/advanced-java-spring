package com.codingnomads.corespring.examples.springbeanlifecycle;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class MyClass implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("Something quirky going on in " + name);
    }
}
