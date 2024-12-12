/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.beanannotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BeanAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(BeanAnnotationConfig.class);
        ctx.refresh();
        SampleBean sampleBean = ctx.getBean("friendly_bean_name", SampleBean.class);
        SampleBean sampleBean2 = ctx.getBean("mySecondBean", SampleBean.class);
        System.out.println(sampleBean.hashCode());
        System.out.println(sampleBean2.hashCode());
        ctx.close();
    }
}
