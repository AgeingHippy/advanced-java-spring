/* CodingNomads (C)2024 */
package com.codingnomads.springtest.usingtestresttemplate.services;

import com.codingnomads.springtest.usingtestresttemplate.exception.CoffeePreferenceNotFound;
import com.codingnomads.springtest.usingtestresttemplate.models.CoffeePreference;
import com.codingnomads.springtest.usingtestresttemplate.repos.CoffeePreferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeePreferenceService {

    @Autowired
    private CoffeePreferenceRepo repo;

    public CoffeePreference insertNewCoffeePreference(CoffeePreference coffeePreference) {
        return repo.save(coffeePreference);
    }

    public CoffeePreference getCoffeePreference(Long id) throws CoffeePreferenceNotFound {
        return repo.findById(id).orElseThrow(() -> new CoffeePreferenceNotFound("CoffeePreference with id " + id + " not found"));
    }
}
