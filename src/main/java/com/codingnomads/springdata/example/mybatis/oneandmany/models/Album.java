package com.codingnomads.springdata.example.mybatis.oneandmany.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {
    private Long id;
    private Artist artist;
    private String name;
    private String year;
    private List<Song> songs;
}
