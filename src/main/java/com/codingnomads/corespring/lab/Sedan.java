package com.codingnomads.corespring.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class Sedan extends MotorVehicle {

    @Autowired  //we expect the primary engine, i.e. petrol, to be used here
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Autowired
    @Qualifier("splitRim")
    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Sedan(int mass) {
        super(mass);
    }

    @Override
    public String toString() {
        return "Sedan(" + super.toString() + ", speed=" + getSpeed() + ")";
    }

}
