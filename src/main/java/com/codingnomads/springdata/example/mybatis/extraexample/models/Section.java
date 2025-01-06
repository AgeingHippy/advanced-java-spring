/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.extraexample.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    private Long id;
    private String name;
    private List<Chapter> chapters;
}
