/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.oneandmany;

import com.codingnomads.springdata.example.mybatis.oneandmany.mappers.AlbumMapper;
import com.codingnomads.springdata.example.mybatis.oneandmany.mappers.ArtistMapper;
import com.codingnomads.springdata.example.mybatis.oneandmany.mappers.SongMapper;
import com.codingnomads.springdata.example.mybatis.oneandmany.models.Album;
import com.codingnomads.springdata.example.mybatis.oneandmany.models.Artist;
import com.codingnomads.springdata.example.mybatis.oneandmany.models.Song;
import java.util.Collections;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OneAndManyApplication {

    /* Before running this app, be sure to:

       * create a new empty schema in the mysql database named "mybatis"

       * execute the SQL found "new-structure.sql" on the mybatis schema

       * update the "spring.datasource.url" property in your application.properties file to
         jdbc:mysql://localhost:3306/mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

    */

    public static void main(String[] args) {
        SpringApplication.run(OneAndManyApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(SongMapper songMapper, ArtistMapper artistMapper, AlbumMapper albumMapper) {
        return (args) -> {
            Artist artist1 = new Artist();
            artist1.setName("Bon Iver");
            artist1.setBio(
                    "Bon Iver is an American indie folk band founded " + "in 2006 by singer-songwriter Justin Vernon.");
            artistMapper.insertNewArtist(artist1);

            Album album1 = Album.builder().artist(artist1).name("Bon Iver").year("2011").build();
            albumMapper.insertNewAlbum(album1);
            artist1.setAlbums(Collections.singletonList(album1));

            Song song1 = new Song();
            song1.setName("Minnesota, WI");
            song1.setAlbum(album1);
            song1.setArtist(artist1);
            song1.setSongLength(232);

            Artist artist2 = new Artist();
            artist2.setName("Gus Dapperton");
            artist2.setBio("Brendan Patrick Rice, better known by his stage name Gus Dapperton, "
                    + "is an American singer and songwriter from Warwick, New York.");
            artistMapper.insertNewArtist(artist2);

            Album album2 = Album.builder().artist(artist2).name("Orca").year("2020").build();
            albumMapper.insertNewAlbum(album2);
            artist2.setAlbums(Collections.singletonList(album2));

            Song song2 = new Song();
            song2.setName("Post Humorous");
            song2.setAlbum(album2);
            song2.setArtist(artist2);
            song2.setSongLength(279);

            songMapper.insertNewSong(song1);
            songMapper.insertNewSong(song2);

            Song song3 = songMapper.getSongById(song1.getId());
            System.out.println(song3.toString());

            Artist artist3 = artistMapper.getArtistByIdWithAlbums(artist1.getId());
            System.out.println(artist3.toString());
            System.out.println(artist3.getAlbums());
        };
    }
}
