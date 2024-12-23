package com.codingnomads.corespring.lab;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class TestDriver {
    @Autowired
    private Driver driver;

    @PostConstruct
    public void testPrint() {
        System.out.println("test");
    }
}
