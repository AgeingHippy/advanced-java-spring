package com.codingnomads.corespring.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Tesla extends MotorVehicle{

    @Autowired
    @Qualifier("electric")
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Autowired
    @Qualifier("alloy")
    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Tesla(int mass) {
        super(mass);
    }

    @Override
    public String toString() {
        return "Tesla("+ super.toString()+", speed=" + getSpeed() + ")";
    }
}
