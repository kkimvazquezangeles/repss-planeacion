package com.codigoartesanal.lupa.infrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by betuzo on 11/05/15.
 */
@Aspect
@Component
public class LogAop {
    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Pointcut("execution(* com.codigoartesanal.lupa.repositories.*Repository.*(..))")
    public void repositoryLayer() {
    }

    @Pointcut("execution(* com.codigoartesanal.lupa.services.*Service.*(..))")
    public void servicesLayer() {
    }

    @Pointcut("execution(* com.codigoartesanal.lupa.controller.*Controller.*(..))")
    public void controllerLayer() {
    }

    @Around("controllerLayer()")
    public Object aroundControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Controller " + pjp.getTarget().getClass()
                + " Method " + pjp.getSignature().getName() + " executed in " + (endTime - startTime) + " ms.");
        return retVal;
    }

    @Around("servicesLayer()")
    public Object aroundServiceMethod(ProceedingJoinPoint pjp) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Service " + pjp.getTarget().getClass()
                + " Method " + pjp.getSignature().getName() + " executed in " + (endTime - startTime) + " ms.");
        return retVal;
    }

    @Around("repositoryLayer()")
    public Object aroundDAOMethod(ProceedingJoinPoint pjp) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Repository " + pjp.getTarget().getClass()
                + " Method " + pjp.getSignature().getName() + " executed in " + (endTime - startTime) + " ms.");
        return retVal;
    }
}
