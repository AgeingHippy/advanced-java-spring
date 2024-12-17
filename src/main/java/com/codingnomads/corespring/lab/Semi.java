package com.codingnomads.corespring.lab;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("bigTruck")
public class Semi extends MotorVehicle {

    @Autowired
    @Qualifier("diesel")
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Autowired
    @Qualifier("splitRim")
    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Semi() {
        super(1000);
    }

    @Override
    public String toString() {
        return "Semi(" + super.toString() + ", speed=" + getSpeed() + ")";
    }

    @PostConstruct
    public void newObject() {
        System.out.println("Singleton Semi is created");
    }

    @PreDestroy
    public void removeObject() {
        System.out.println("Singleton Semi is deleted");
    }
}
