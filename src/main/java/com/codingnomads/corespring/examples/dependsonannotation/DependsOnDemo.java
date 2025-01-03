/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.dependsonannotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependsOnDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(DependsOnDemoConfig.class);
        ctx.refresh();
        final SpringDeveloper springDeveloper = ctx.getBean(SpringDeveloper.class);
        Licence licence = ctx.getBean(Licence.class);
        System.out.println("licence.getJDK().toString() = " + licence.getJDK().toString());
        ctx.close();
    }
}
