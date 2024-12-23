package com.codingnomads.springdata.example.dml.derivedquerymethods.myexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MyExampleApplication implements CommandLineRunner {

    @Autowired
    GenreRepo genreRepo;
    @Autowired
    PlatformRepo platformRepo;
    @Autowired
    GameRepo gameRepo;

    public static void main(String[] args) {
        SpringApplication.run(MyExampleApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        //create Genres
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.builder().name("Action").build());
        genres.add(Genre.builder().name("Fighting").build());
        genres.add(Genre.builder().name("First Person Shooter").build());
        genres.add(Genre.builder().name("Sports").build());
        genres.add(Genre.builder().name("RPG").build());
        genres.add(Genre.builder().name("Survival").build());
        genres.add(Genre.builder().name("Simulation").build());
        genres = new ArrayList<>(genreRepo.saveAll(genres));

        //create Platforms
        ArrayList<Platform> platforms = new ArrayList<>();
        platforms.add(Platform.builder().name("PC").build());
        platforms.add(Platform.builder().name("APPLE").build());
        platforms.add(Platform.builder().name("X-BOX").build());
        platforms.add(Platform.builder().name("PLAYSTATION").build());
        platforms.add(Platform.builder().name("ATARI").build());
        platforms = new ArrayList<>(platformRepo.saveAll(platforms));

        //create Games
        Game game = Game.builder()
                .name("MS Flight Sim")
                .genre(genreRepo.findFirstByName("Simulation"))
                .price(45.80)
                .build();
        game.addPlatform(platformRepo.findFirstByName("PC"));
        gameRepo.save(game);

        game = Game.builder()
                .name("X4")
                .genre(genreRepo.findFirstByName("Simulation"))
                .price(24.50)
                .build();
        game.addPlatform(platformRepo.findFirstByName("PC"));
        game.addPlatform(platformRepo.findFirstByName("APPLE"));
        gameRepo.save(game);

        game = Game.builder()
                .name("Oxygen Not Included")
                .genre(genreRepo.findFirstByName("Survival"))
                .price(27.50)
                .build();
        game.addPlatform(platformRepo.findFirstByName("PC"));
        game.addPlatform(platformRepo.findFirstByName("APPLE"));
        game.addPlatform(platformRepo.findFirstByName("PLAYSTATION"));
        gameRepo.save(game);

        game = Game.builder()
                .name("Dayz")
                .genre(genreRepo.findFirstByName("Survival"))
                .price(14.50)
                .build();
        game.addPlatform(platformRepo.findFirstByName("X-BOX"));
        game.addPlatform(platformRepo.findFirstByName("ATARI"));
        game.addPlatform(platformRepo.findFirstByName("PLAYSTATION"));
        gameRepo.save(game);

        game = Game.builder()
                .name("Halo")
                .genre(genreRepo.findFirstByName("First Person Shooter"))
                .price(55.50)
                .build();
        game.addPlatform(platformRepo.findFirstByName("X-BOX"));
        game.addPlatform(platformRepo.findFirstByName("PLAYSTATION"));
        gameRepo.save(game);

        System.out.println("======== PC Games =========");
        List<Game> pcGames = gameRepo.findByPlatforms_name("PC");

        pcGames.forEach(System.out::println);

        System.out.println("=====   X-BOX games ======");
        gameRepo.findByPlatforms(platformRepo.findFirstByName("X-BOX")).forEach(System.out::println);

        System.out.println("===== non-Simulation Games ======");
        gameRepo.findByGenreIsNot(genreRepo.findFirstByName("Simulation")).forEach(System.out::println);

        System.out.println("===== GTE && GTE ======");
        gameRepo.findByPriceGreaterThanEqualAndPriceLessThanEqual(24.50, 45.80)
                .forEach(System.out::println);

        System.out.println("===== Between ======");
        gameRepo.findByPriceBetween(24.50, 45.80)
                .forEach(System.out::println);

        System.out.println("===== GT && LT ======");
        gameRepo.findByPriceGreaterThanAndPriceLessThan(24.50, 45.80)
                .forEach(System.out::println);

        //cleanup database
        gameRepo.deleteAllInBatch();
        genreRepo.deleteAllInBatch();
        platformRepo.deleteAllInBatch();

    }
}
