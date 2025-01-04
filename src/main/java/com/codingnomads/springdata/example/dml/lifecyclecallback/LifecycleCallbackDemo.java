/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.lifecyclecallback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class LifecycleCallbackDemo {

    public static void main(String[] args) {
        SpringApplication.run(LifecycleCallbackDemo.class);
    }

    @Bean
    public CommandLineRunner runStuff(PrintEntityRepository printEntityRepository) {
        return (args) -> {
            // put your logic here
            //create a few PrintEntities
            System.out.println("=== CREATE ONE ENTITY AND SAVE ===");
            PrintEntity printEntity1 = PrintEntity.builder().value("A").build();
            printEntityRepository.save(printEntity1);

            System.out.println("=== CREATE 3 ENTITIES ===");
            ArrayList<PrintEntity> printEntities = new ArrayList<>();
            printEntities.add( PrintEntity.builder().value("B").build());
            printEntities.add( PrintEntity.builder().value("C").build());
            printEntities.add( PrintEntity.builder().value("D").build());

            System.out.println("=== SAVE 3 ENTITIES ===");
            printEntityRepository.saveAll(printEntities).forEach(System.out::println);

            System.out.println("=== refresh these entities ===");
            printEntities = (ArrayList<PrintEntity>) printEntityRepository.findAllById(printEntities.stream().map(PrintEntity::getId).toList());

            System.out.println("=== UPDATE 1 ENTITY AND SAVE ===");
            printEntity1.setValue("AA");
            printEntityRepository.save(printEntity1);
            System.out.println("printEntity1 = " + printEntity1);

            System.out.println("=== FETCH 1 ENTITY ===");
            PrintEntity printEntity2 = printEntityRepository.findById(printEntities.get(1).getId()).get();
            System.out.println("printEntity2 = " + printEntity2);
            System.out.println("== MODIFY ==");
            printEntity2.setValue("CC");
            System.out.println("printEntity2 = " + printEntity2);
            System.out.println("printEntities2(1) = " + printEntities.get(1).toString());
            System.out.println("== SAVE ==");
            printEntityRepository.save(printEntity2);
            System.out.println("printEntity2 = " + printEntity2);
            System.out.println("printEntities2(1) = " + printEntities.get(1).toString());
            System.out.println("== REMOVE ==");
            printEntityRepository.delete(printEntity2);
            System.out.println("printEntity2 = " + printEntity2);
            System.out.println("printEntities2(1) = " + printEntities.get(1).toString());

            //cleanup
            System.out.println("=== Process CLEANUP (Delete remaining entities)===");
            printEntityRepository.deleteAll();

        };
    }
}
