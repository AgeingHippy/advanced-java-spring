package com.codingnomads.ioc.examples.dependencylookup;

public class AlternativeGreetingProvider implements GreetingProvider{
    @Override
    public String getGreeting() {
        return "Here's an alternative greeting!!";
    }
}
