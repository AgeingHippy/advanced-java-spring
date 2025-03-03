/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.mapperslesson;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Song {

    private Long id;

    private String name;

    // allowed to be null if single
    private String album_name;

    private String artist_name;

    // song length in seconds
    private int song_length;
}
