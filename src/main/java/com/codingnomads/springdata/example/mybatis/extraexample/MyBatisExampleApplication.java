/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.extraexample;

import com.codingnomads.springdata.example.mybatis.extraexample.mappers.ChapterMapper;
import com.codingnomads.springdata.example.mybatis.extraexample.mappers.ImageMapper;
import com.codingnomads.springdata.example.mybatis.extraexample.mappers.LessonMapper;
import com.codingnomads.springdata.example.mybatis.extraexample.mappers.SectionMapper;
import com.codingnomads.springdata.example.mybatis.extraexample.models.Chapter;
import com.codingnomads.springdata.example.mybatis.extraexample.models.Image;
import com.codingnomads.springdata.example.mybatis.extraexample.models.Lesson;
import com.codingnomads.springdata.example.mybatis.extraexample.models.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class MyBatisExampleApplication implements CommandLineRunner {

    /* Before running this app, be sure to:

       * create a new empty schema in the mysql database named "mybatis"

       * execute the SQL found "database_structure.sql" on the mybatis schema

       * update the "spring.datasource.url" property in your application.properties file to
         jdbc:mysql://localhost:3306/mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

    */

    @Autowired
    ImageMapper imageMapper;

    @Autowired
    LessonMapper lessonMapper;

    @Autowired
    ChapterMapper chapterMapper;

    @Autowired
    SectionMapper sectionMapper;

    public static void main(String[] args) {
        SpringApplication.run(MyBatisExampleApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        insertData();
        viewData();

        Section section = sectionMapper.getSectionByName("Java Virtual Machine (JVM)");
        printSection(section);

        int deleteCount = sectionMapper.deleteSectionById(section.getId());
        System.out.println("deleteCount = " + deleteCount);
        section = sectionMapper.getSectionByName("Java Virtual Machine (JVM)");
        System.out.println("section deleted count " + deleteCount + " success : " + (section == null));

        Lesson lesson = lessonMapper.getLessonByName("Video: What is Vim?");
        printLesson(lesson);

        System.out.println("** POST Removal of image : " + lesson.getImageList().getFirst().getName() + " **");
        lessonMapper.deleteImageFromLesson(lesson.getId(), lesson.getImageList().getFirst().getName());
        lesson = lessonMapper.getLessonByName("Video: What is Vim?");
        printLesson(lesson);

        try {
            imageMapper.renameImage("cat5.jpg", "cat55.jpg");
        } catch (Exception e) {
            System.out.println("************ " + e.getMessage());
        }

        Image image = imageMapper.getImageByName("cat55.jpg");

    }

    private void insertData() {
        final String filePath = "./mybatisExample/lesson_data.txt";
        Section section = null;
        Chapter chapter = null;
        Lesson lesson = null;
        Image image = null;
        Path imagePath;
        String[] lineArray;
        String[] imageArray;

        //load file
        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:" + filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineArray = line.split("\\|");

                if (section == null || !lineArray[0].equals(section.getName())) {
                    //we have a new section, and hence a new chapter and lesson as well
                    section = Section.builder().name(lineArray[0]).build();
                    sectionMapper.insertNewSectionObject(section);
                    chapter = null;
                    lesson = null;
                }
                if (chapter == null || !lineArray[1].equals(chapter.getName())) {
                    //a new chapter and lesson
                    chapter = Chapter.builder().name(lineArray[1]).build();
                    chapterMapper.insertNewChapterObject(chapter, section.getId());
                    lesson = null;
                }
                if (lesson == null || !lineArray[2].equals(lesson.getName())) {
                    //a new lesson
                    lesson = Lesson.builder().name(lineArray[2]).text(lineArray[2] + " some text").build();
                    lessonMapper.insertNewLessonObject(lesson, chapter.getId());
                }
                if (lineArray.length > 3) {
                    //we have images
                    imageArray = lineArray[3].split(",");
                    for (String imageFileName : imageArray) {
                        image = imageMapper.getImageByName(imageFileName);
                        if (image == null) {
                            //insert image
                            imagePath = Paths.get(getClass().getClassLoader().getResource("./mybatisExample/" + imageFileName).toURI());
                            image = Image.builder()
                                    .name(imageFileName)
                                    .imageData(Files.readAllBytes(imagePath)).build();
                            imageMapper.insertNewImage(image.getName(), image.getImageData());
                        }
                        //link image to lesson
                        lessonMapper.addImageToLesson(lesson.getId(), image.getName());
                    }
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void viewData() {
        List<Section> sections = sectionMapper.getAllSections();
        sections.forEach(MyBatisExampleApplication::printSection);

    }

    private static void printSection(Section section) {
        System.out.println("== Section " + section.getId() + " : " + section.getName() + " ==");
        section.getChapters().forEach(MyBatisExampleApplication::printChapter);
    }

    private static void printChapter(Chapter chapter) {
        System.out.println("==== Chapter " + chapter.getId() + " : " + chapter.getName() + " ====");
        chapter.getLessons().forEach(MyBatisExampleApplication::printLesson);
    }

    private static void printLesson(Lesson lesson) {
        System.out.println("====== Lesson " + lesson.getId() + " : " + lesson.getName() + " - " + lesson.getText() + " ======");
        lesson.getImageList().forEach(MyBatisExampleApplication::printImage);
    }

    private static void printImage(Image image) {
        System.out.println("======== Image " + image.getName() + " size: " + image.getImageData().length + " ========");
    }
}
