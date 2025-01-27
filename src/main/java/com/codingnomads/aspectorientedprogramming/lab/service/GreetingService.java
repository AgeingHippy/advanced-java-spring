/* CodingNomads (C)2024 */
package com.codingnomads.aspectorientedprogramming.lab.service;

import com.codingnomads.aspectorientedprogramming.lab.aspect.Printable;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Printable
    public String greeting() {
        return "Hello Spring Developer!";
    }

    @Printable
    public void doStuff(String stuff) {
        System.out.println("Do Stuff Here to " + stuff + "!!");
    }

}
