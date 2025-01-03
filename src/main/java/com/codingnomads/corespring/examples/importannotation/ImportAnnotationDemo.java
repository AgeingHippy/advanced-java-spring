/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.importannotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.plaf.basic.BasicListUI;

public class ImportAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ImportAnnotationConfig.class);
        ctx.refresh();
        final SpringDeveloper springDeveloper = ctx.getBean(SpringDeveloper.class);
        final Framework framework = ctx.getBean(Framework.class);
        final Biscuit biscuit= ctx.getBean(Biscuit.class);
        ctx.close();
    }
}
