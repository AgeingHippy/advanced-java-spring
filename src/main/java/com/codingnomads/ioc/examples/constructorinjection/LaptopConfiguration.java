/* CodingNomads (C)2024 */
package com.codingnomads.ioc.examples.constructorinjection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.codingnomads.ioc.examples.constructorinjection")
public class LaptopConfiguration {

    @Bean
    public Processor processor() {
        return new Processor(8, "i9");
    }

    @Bean
    public OS os() {
        return new OS("ubuntu");
    }

    @Bean
    public Monitor monitor() {
        return new Monitor("Liyama");
    }

    @Bean
    public GPU gpu() {
        return new GPU("GeForce GT 730");
    }

    @Bean
    public SoundCard soundCardGeneric() {
        return new SoundCard("Generic 123");
    }

    @Bean
    public SoundCard soundCardPro() {
        return new SoundCard("Professional 567");
    }
}
