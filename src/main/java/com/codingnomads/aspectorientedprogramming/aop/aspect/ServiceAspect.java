/* CodingNomads (C)2024 */
package com.codingnomads.aspectorientedprogramming.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut(value = "execution(* com.codingnomads.aspectorientedprogramming.aop.service.StudentService.*(..))")
    private void logAllStudentServiceMethods() {
    }

    @Pointcut("execution(* com.codingnomads.aspectorientedprogramming.aop.service.StudentService.saveStudent(..))")
    private void saveStudentPointcut() {
    }

    @Before("execution(* com.codingnomads.aspectorientedprogramming.aop.service.StudentService.saveStudent(String, String))")
    public void saveStudentWithStringParametersBefore(JoinPoint joinPoint) {
        LOGGER.info("Custom saveStudentWithStringParametersBefore on: " + joinPoint.getSignature() +
                " with arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before("saveStudentPointcut()")
    public void saveStudentBefore(JoinPoint joinPoint) {
        LOGGER.info("Custom saveStudentBefore on: " + joinPoint.getSignature() + " with arguments: "
                + Arrays.toString(joinPoint.getArgs()));
    }

    @After("saveStudentPointcut()")
    public void saveStudentAfter(JoinPoint joinPoint) {
        LOGGER.info("Custom saveStudentAfter on: " + joinPoint.getSignature());
    }


    @Before("logAllStudentServiceMethods()")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        LOGGER.info("Before Advice : " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.codingnomads.aspectorientedprogramming.aop.service.StudentService.fetchAllStudents())")
    public void logFetchAllStudentMethod(JoinPoint joinPoint) {
        // write any custom logic according to business requirement
        LOGGER.info("Before the execution of : " + joinPoint.getSignature());
    }

    @After("logAllStudentServiceMethods()")
    public void logAfterAdvice(JoinPoint joinPoint) {
        LOGGER.info("After Advice: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "logAllStudentServiceMethods()", returning = "students")
    public void afterReturningAdvice(JoinPoint jp, Object students) {
        LOGGER.info("After Returning Advice: " + " Method Name: = "
                + jp.getSignature().getName());
        LOGGER.info("Result: = " + students);
    }
}
