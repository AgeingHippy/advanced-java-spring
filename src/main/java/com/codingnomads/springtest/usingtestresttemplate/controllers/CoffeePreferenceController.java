/* CodingNomads (C)2024 */
package com.codingnomads.springtest.usingtestresttemplate.controllers;

import com.codingnomads.springtest.usingtestresttemplate.exception.CoffeePreferenceNotFound;
import com.codingnomads.springtest.usingtestresttemplate.models.CoffeePreference;
import com.codingnomads.springtest.usingtestresttemplate.services.CoffeePreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coffee")
public class CoffeePreferenceController {

    @Autowired
    CoffeePreferenceService service;

    @PostMapping
    public ResponseEntity<?> postNewCoffeePreference(@RequestBody CoffeePreference coffeePreference) {
        try {
            CoffeePreference preference = service.insertNewCoffeePreference(coffeePreference);
            return ResponseEntity.ok()
                    .header("Location", "http://www.url.com/new/location")
                    .body(preference);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Exception(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCoffeePreference(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getCoffeePreference(id));
        } catch (CoffeePreferenceNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
