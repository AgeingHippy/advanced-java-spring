package com.codingnomads.corespring.examples.scopeannotaion;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class MySingleton {
    public MySingleton() {
        System.out.println("MySingleton constructed");
    }
}
