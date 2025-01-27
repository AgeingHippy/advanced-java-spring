/* CodingNomads (C)2024 */
package com.codingnomads.aspectorientedprogramming.lab.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingServiceAspect.class);

    @Pointcut("execution(* com.codingnomads.aspectorientedprogramming.lab.service.GreetingService.greeting(..))")
    public void greetingServiceGreetingPointcut() {
    }

    @Pointcut("execution(* com.codingnomads.aspectorientedprogramming.lab.service.GreetingService.doStuff(..))")
    public void greetingServiceDoStuffPointcut() {
    }

    @Before("greetingServiceGreetingPointcut()")
    public void greetingServiceGreetingBefore() {
        LOGGER.info("Before");
    }

    @AfterReturning(pointcut = "greetingServiceGreetingPointcut()", returning="result")
    public Object greetingServiceGreetingAfter(Object result) {
        LOGGER.info("After Returning");
        return result;
    }

    @Before("greetingServiceDoStuffPointcut()")
    public void greetingServiceDoStuffBefore() {
        LOGGER.info("Before doStuff");
    }



}
