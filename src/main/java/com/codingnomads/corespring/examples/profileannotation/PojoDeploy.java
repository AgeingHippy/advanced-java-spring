package com.codingnomads.corespring.examples.profileannotation;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("deploy")
public class PojoDeploy {
    public PojoDeploy() {
        System.out.println("pojoDeploy constructed");
    }
}
