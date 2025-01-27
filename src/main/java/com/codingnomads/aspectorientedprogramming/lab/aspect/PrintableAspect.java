package com.codingnomads.aspectorientedprogramming.lab.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class PrintableAspect {

    @Pointcut("@annotation(Printable)")
    public void printablePointcut() {}

    @Before("printablePointcut()")
    public void doPrintablePointcut(JoinPoint joinPoint) {
        System.out.println("@Printable annotation called by : " +
                joinPoint.getSignature().getName() +
                "with parameters : " +
                Arrays.toString(joinPoint.getArgs()));
    }


}
