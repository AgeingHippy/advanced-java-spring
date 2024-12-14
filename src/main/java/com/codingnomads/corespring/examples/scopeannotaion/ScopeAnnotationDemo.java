/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.scopeannotaion;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ScopeAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ScopeAnnotationDemoConfig.class);
        ctx.refresh();
        SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);

        System.out.println("-----Hashcode of SingletonBean-----");
        System.out.println(singletonBean1.hashCode());
        System.out.println(singletonBean2.hashCode());

        final PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
        final PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);

        System.out.println("-----Hashcode of PrototypeBean-----");
        System.out.println(prototypeBean1.hashCode());
        System.out.println(prototypeBean2.hashCode());
        System.out.println();

        MyPrototype myPrototype1 = ctx.getBean(MyPrototype.class);
        MyPrototype myPrototype2 = ctx.getBean(MyPrototype.class);
        System.out.println("-----Hashcode of MyPrototype-----");
        System.out.println(myPrototype1.hashCode());
        System.out.println(myPrototype2.hashCode());
        System.out.println();

        MySingleton mySingleton1 = ctx.getBean(MySingleton.class);
        MySingleton mySingleton2 =  ctx.getBean(MySingleton.class);
        System.out.println("-----Hashcode of MySingleton-----");
        System.out.println(mySingleton1.hashCode());
        System.out.println(mySingleton2.hashCode());
        System.out.println();

        ctx.close();
    }
}
