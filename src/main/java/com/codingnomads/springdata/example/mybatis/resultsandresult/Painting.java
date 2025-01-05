package com.codingnomads.springdata.example.mybatis.resultsandresult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Painting {
    private Long id;
    private String name;
    private String artistName;
    private String medium;
    private int yearCreated;
}
