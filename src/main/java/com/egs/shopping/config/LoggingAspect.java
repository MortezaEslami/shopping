package com.egs.shopping.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)" +
            " || within(@org.springframework.stereotype.Service *)")
    public void springBeanPointcut() {
    }

    @Pointcut("within(com.egs.shopping.controller..*)" +
            " || within(com.egs.shopping.service..*)")
    public void applicationBeanPointcut() {
    }

    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }


    @Around("applicationBeanPointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = logger(joinPoint);
        if (log.isDebugEnabled()) {
            log.debug("Request Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Request Exit: {}() with result = {}", joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            throw e;
        }
    }
}