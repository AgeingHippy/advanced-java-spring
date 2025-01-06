/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.extraexample.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString(exclude = "imageData")
public class Image {

    private String name;
    private byte[] imageData;
}
