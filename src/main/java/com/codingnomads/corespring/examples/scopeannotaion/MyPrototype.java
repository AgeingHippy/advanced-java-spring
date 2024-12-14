package com.codingnomads.corespring.examples.scopeannotaion;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class MyPrototype {
    public MyPrototype() {
        System.out.println("MyPrototype constructed");
    }
}
