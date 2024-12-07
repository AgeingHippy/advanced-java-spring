package com.codingnomads.springfundamentals.example;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MyRandomService {

    public int someRandomNumber(int range) {
        return new Random().nextInt(range);
    }
}
