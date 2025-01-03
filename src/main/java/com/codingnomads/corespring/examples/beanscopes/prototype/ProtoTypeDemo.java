/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.beanscopes.prototype;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProtoTypeDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(PrototypeDemoConfig.class);
        ctx.refresh();

        SpringBean springBean1 = ctx.getBean(SpringBean.class);
        System.out.println("Hash code: " + springBean1.hashCode());

        SpringBean springBean2 = ctx.getBean(SpringBean.class);
        System.out.println("Hash code: " + springBean2.hashCode());

        MyPrototype myPrototype1 = ctx.getBean(MyPrototype.class);
        MyPrototype myPrototype2 = ctx.getBean(MyPrototype.class);

        System.out.println("myPrototype1 hash: " + myPrototype1.hashCode());
        System.out.println("myPrototype2 hash: " + myPrototype2.hashCode());

        ctx.close();
    }
}
