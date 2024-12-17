package com.codingnomads.corespring.lab;

import lombok.ToString;

@ToString
public abstract class MotorVehicle implements Vehicle {
    protected int mass;
    protected Engine engine;
    protected Wheel wheel;

    public MotorVehicle(int mass) {
        if (mass <=0) {
            throw new IllegalStateException("Vehicle mass must be greater than 0");
        }
        this.mass = mass;
    }

    @Override
    public int getSpeed() {
        return engine.getHorsepower() * 100 /mass;
    }

}
