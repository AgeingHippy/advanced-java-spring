/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.mapperslesson;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBatisDemoApplication {

    /* Before running this app, be sure to:

       * create a new empty schema in the mysql database named "mybatis"

       * execute the SQL found "songs_table.sql" on the mybatis schema

       * update the "spring.datasource.url" property in your application.properties file to
         jdbc:mysql://localhost:3306/mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

    */

    public static void main(String[] args) {
        SpringApplication.run(MyBatisDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(SongMapper songMapper) {
        return (args) -> {
            Song song1 = new Song();
            song1.setName("Minnesota, WI");
            song1.setAlbum_name("Bon Iver");
            song1.setArtist_name("Bon Iver");
            song1.setSong_length(232);

            Song song2 = new Song();
            song2.setName("Post Humorous");
            song2.setAlbum_name("Orca");
            song2.setArtist_name("Gus Dapperton");
            song2.setSong_length(279);

            songMapper.insertNewSong(song1);
            songMapper.insertNewSong(song2);

            songMapper.insertNewSong(Song.builder().name("Gasolina").artist_name("Daddy Yankee").album_name("Barrio Fino").song_length(192).build());
            songMapper.insertNewSong(Song.builder().name("Despacito").artist_name("Daddy Yankee, Luis Fonsi").album_name("Despacito & mis grandes éxitos").song_length(228).build());
            songMapper.insertNewSong(Song.builder().name("Salud y vida").artist_name("Daddy Yankee").album_name("Barrio Fino").song_length(206).build());
            songMapper.insertNewSong(Song.builder().name("Despacito").artist_name("Lim Young Woong").album_name("Love call center PART3").song_length(115).build());

            System.out.println("== Songs longer than 250 seconds ==");
            List<Song> songs = songMapper.getSongsWithLengthGreaterThan(250);
            songs.forEach(System.out::println);

            System.out.println("== Songs with length in range ==");
            songs = songMapper.getSongsWithLengthBetween(200, 260);
            songs.forEach(System.out::println);

            System.out.println("== Songs by Album and Artist ==");
            songs = songMapper.getSongsByAlbumAndArtist("Daddy Yankee","Barrio Fino");
            songs.forEach(System.out::println);

            System.out.println("== Songs by name ==");
            songs = songMapper.getSongsByName("Despacito");
            songs.forEach(System.out::println);

            System.out.println("== Songs by artist ==");
            songs = songMapper.getSongsByArtist("Daddy Yankee");
            songs.forEach(System.out::println);

            System.out.println("== Song with ID " + song1.getId() + " ==");
            Song song = songMapper.getSongById(song1.getId());
            System.out.println(song.toString());

            System.out.println("== Update Song with ID " + song.getId() + " ==");
            song.setName(song.getName()+ " mod");
            song.setArtist_name(song.getArtist_name()+ " mod");
            song.setAlbum_name(song.getAlbum_name()+ " mod");
            song.setSong_length(song.getSong_length()+60);
            songMapper.updateSong(song);
            song = songMapper.getSongById(song.getId());
            System.out.println(song.toString());

            int deleteCount = songMapper.deleteSongsByAlbumAndArtist(song.getArtist_name(), song.getAlbum_name());
            System.out.println("deleteCount (expect 1) = " + deleteCount);

            songMapper.deleteSongsByArtist(song2);

            songMapper.deleteSongById(song1.getId());

            System.out.println("== All remaining songs ==");
            songs = songMapper.getAllSongs();
            songs.forEach(System.out::println);

            songMapper.deleteAllSongs();

            System.out.println("== No of remaining songs after delete all ==");
            System.out.println("Count = " + songMapper.getAllSongs().size());

        };
    }
}
