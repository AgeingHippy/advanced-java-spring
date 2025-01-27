package com.codingnomads.aspectorientedprogramming.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MuckyDuckAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MuckyDuckAspect.class);

    @Pointcut("@annotation(MuckyDuck)")
    public void muckyDuckPointcut() {};

    @Before("muckyDuckPointcut()")
    public void doMuckyDuckPointcutStuff(JoinPoint joinPoint) {
        LOGGER.info("Doing doMuckyDuckPointcutStuff on: "+ joinPoint.getSignature().getName());
    }
}
