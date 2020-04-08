package com.scapor.core.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Value("${spring.profiles.active:dev}")
    private String profile;
    @Pointcut("within(@org.springframework.stereotype.Component *) || within(@org.springframework.stereotype.Service *) || within(@org.springframework.stereotype.Repository  *) || within(@org.springframework.web.bind.annotation.RestController *)")
    public void beanPointcut() {
    }
    @Pointcut("within(com.scapor.repositories.user.facade..*) || within(com.scapor.services..*) || within(com.scapor.web..*)")
    public void applicationPackagePointcut() {
    }
    @Around("applicationPackagePointcut() && beanPointcut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (log.isDebugEnabled() || profile.equals("test")) {
            log.info("Enter: {}.{}() with argument[s] = {}", proceedingJoinPoint.getSignature().getDeclaringType(), proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));
        }
        try {
            Object result = proceedingJoinPoint.proceed();
            if (log.isDebugEnabled() || profile.equals("test")) {
                log.info("Exit: {}.{}() with result = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                        proceedingJoinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException ie) {
            log.error("Error: {} in {}.{}()", Arrays.toString(proceedingJoinPoint.getArgs()), proceedingJoinPoint.getSignature().getDeclaringType(), proceedingJoinPoint.getSignature().getDeclaringTypeName());
            throw ie;
        }
    }
}
