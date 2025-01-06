/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.extraexample.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    private Long id;
    private String name;
    private String text;
    private List<Image> imageList;
}
