package com.codingnomads.springdata.example.dml.usingqueryannotation;

import com.codingnomads.springdata.example.dml.usingqueryannotation.models.SoilType;
import com.codingnomads.springdata.example.dml.usingqueryannotation.repositories.SoilTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SoilTypeService {
    @Autowired
    SoilTypeRepo soilTypeRepo;

    public void addSoilTypes() {
        List<SoilType> soilTypes = new ArrayList<>();

        soilTypes.add(SoilType.builder().name("chalky").ph(2.4).dry(true).build());
        soilTypes.add(SoilType.builder().name("clay").ph(4.3).dry(false).build());
        soilTypes.add(SoilType.builder().name("loamy").ph(5.8).dry(false).build());
        soilTypes.add(SoilType.builder().name("peaty").ph(8.2).dry(false).build());
        soilTypes.add(SoilType.builder().name("sandy").ph(3.0).dry(true).build());
        soilTypes.add(SoilType.builder().name("silty").ph(7.1).dry(false).build());

        soilTypeRepo.saveAllAndFlush(soilTypes);
    }

    public void doSoilStuff() {

        System.out.println("=== select all soiltypes ===");
        List<SoilType> soilTypes = soilTypeRepo.findAllJPA();
        soilTypes.forEach(System.out::println);

        System.out.println("=== select soiltype by name ===");
        SoilType soilType = soilTypeRepo.findByNameJPA("peaty");
        System.out.println(soilType);

        System.out.println("=== select soiltype in PH range ===");
        soilTypes = soilTypeRepo.findAllInRangePhJPA(3.0, 7.0);
        soilTypes.forEach(System.out::println);

        System.out.println("=== select soiltype in PH range paged ===");
        Pageable pageable = PageRequest.of(0, 2);
        Page<SoilType> soilTypePage = soilTypeRepo.findAllInRangePhPagedJPA(3.0, 7.0, pageable);
        while (soilTypePage.hasContent()) {
            soilTypePage.getContent().forEach(System.out::println);
            pageable = pageable.next();
            soilTypePage = soilTypeRepo.findAllInRangePhPagedJPA(3.0, 7.0, pageable);
        }

        System.out.println("=== select soiltype in PH range paged and sorted DESC ===");
        Sort sort = Sort.by(Sort.Direction.DESC,"name");
        pageable = PageRequest.of(0, 2, sort);
        soilTypePage = soilTypeRepo.findAllInRangePhPagedJPA(3.0, 7.0, pageable);

        while (soilTypePage.hasContent()) {
            soilTypePage.getContent().forEach(System.out::println);
            pageable = pageable.next();
            soilTypePage = soilTypeRepo.findAllInRangePhPagedJPA(3.0, 7.0, pageable);
        }

        soilTypes = soilTypeRepo.findAllByDryJPA(true);
        System.out.println("=== select dry soiltype ===");
        soilTypes = soilTypeRepo.findAllByDryJPA(true);
        soilTypes.forEach(System.out::println);


    }

    public void deleteAll() {
        soilTypeRepo.deleteAll();
    }

}
