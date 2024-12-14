package com.codingnomads.corespring.examples.dependsonannotation;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public class Licence {
    @Autowired
    JDK jdk;

    public Licence() {
        System.out.println("Licence is valid");
    }

    public JDK getJDK(){
        return jdk;
    }
}
