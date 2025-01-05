/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.resultsandresult;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ResultsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultsDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(SongMapper songMapper, PaintingMapper paintingMapper) {
        return (args) -> {
            // notice the setter names have changed to match Java naming conventions
            Song song1 = new Song();
            song1.setName("Minnesota, WI");
            song1.setAlbumName("Bon Iver");
            song1.setArtistName("Bon Iver");
            song1.setSongLength(232);

            Song song2 = new Song();
            song2.setName("Post Humorous");
            song2.setAlbumName("Orca");
            song2.setArtistName("Gus Dapperton");
            song2.setSongLength(279);

            songMapper.insertNewSong(song1);
            songMapper.insertNewSong(song2);

            Song song3 = songMapper.getSongById(song1.getId());
            System.out.println(song3.toString());

            //delete all songs
            songMapper.deleteSongById(song1.getId());
            songMapper.deleteSongById(song2.getId());

            //Paintings
            Painting painting = Painting.builder()
                    .name("The Battle of Anghiari")
                    .artistName("Leonardo da Vinci")
                    .medium("Mural painting")
                    .yearCreated(1504)
                    .build();
            paintingMapper.insert(painting);

            paintingMapper.insert(Painting.builder()
                    .name("Leda and the Swan")
                    .artistName("Leonardo da Vinci")
                    .medium("Oil on canvas")
                    .yearCreated(1508)
                    .build());

            paintingMapper.insert(Painting.builder()
                    .name("Dunes")
                    .artistName("Vincent Van Gogh")
                    .medium("Oil on paper")
                    .yearCreated(1882)
                    .build());

            paintingMapper.insert(Painting.builder()
                    .name("Girl in the Woods")
                    .artistName("Vincent Van Gogh")
                    .medium("Oil on canvas")
                    .yearCreated(1882)
                    .build());

            paintingMapper.insert(Painting.builder()
                    .name("Le petit picador jaune")
                    .artistName("Pablo Picasso")
                    .medium("Oil on wood")
                    .yearCreated(1890)
                    .build());

            paintingMapper.insert(Painting.builder()
                    .name("Portrait de la tante Pepa")
                    .artistName("Pablo Picasso")
                    .medium("Oil on canvas")
                    .yearCreated(1896)
                    .build());

            System.out.println("== get by id ==");
            Painting painting1 = paintingMapper.getById(painting.getId());
            System.out.println(painting1);

            System.out.println("== get all ==");
            List<Painting> paintings = paintingMapper.getAll();
            paintings.forEach(System.out::println);

            System.out.println("== get all by artist ==");
            paintings = paintingMapper.getAllByArtist("Pablo Picasso");
            paintings.forEach(System.out::println);

            System.out.println("== UPDATE name ==");
            System.out.println(painting);
            painting.setName(painting.getName() + " (Alt)");
            paintingMapper.update(painting);
            painting = paintingMapper.getById(painting.getId());
            System.out.println(painting);

            System.out.println("== attempt to delete not found ==");
            System.out.println("Delete count (expect 0) = " + paintingMapper.deleteById(-1L));

            System.out.println("== DELETE ALL ==");
            System.out.println("Delete count (expect " + paintingMapper.getCount() +") = " + paintingMapper.deleteAll());

            System.out.println("Paintings remaining: "+ paintingMapper.getCount());
            
        };
    }
}
