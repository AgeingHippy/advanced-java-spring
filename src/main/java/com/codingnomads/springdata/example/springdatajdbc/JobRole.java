package com.codingnomads.springdata.example.springdatajdbc;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JobRole {
    private int id;
    private String Name;
    private String Grade;
    private int salary;
}
