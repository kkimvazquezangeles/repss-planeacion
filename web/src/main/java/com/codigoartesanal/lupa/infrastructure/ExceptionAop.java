package com.codigoartesanal.lupa.infrastructure;

import com.codigoartesanal.lupa.exception.TokenException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by betuzo on 12/05/15.
 */
@Aspect
@Component
public class ExceptionAop {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAop.class);

    @Autowired
    HttpServletResponse response;

    @Pointcut("execution(* com.codigoartesanal.lupa.repositories.*Repository.*(..))")
    public void repositoryLayer() {
    }

    @Pointcut("execution(* com.codigoartesanal.lupa.services.*Service.*(..))")
    public void servicesLayer() {
    }

    @Pointcut("execution(* com.codigoartesanal.lupa.controller.*Controller.*(..))")
    public void controllerLayer() {
    }

    @Pointcut("execution(* com.codigoartesanal.lupa.controller.UserTokenController.*(..))")
    public void userTokenControllerLayer() {
    }

    @AfterThrowing(pointcut = "controllerLayer()", throwing= "error")
    public void throwingControllerMethod(JoinPoint joinPoint, Throwable error) throws Throwable{
        logger.info("Controller " + joinPoint.getTarget().getClass()
                + " Method " + joinPoint.getSignature().getName()
                + " error in " + error.getLocalizedMessage());
    }

    @AfterThrowing(pointcut = "servicesLayer()", throwing= "error")
    public void throwingServiceMethod(JoinPoint joinPoint, Throwable error) throws Throwable{
        logger.info("Services " + joinPoint.getTarget().getClass()
                + " Method " + joinPoint.getSignature().getName()
                + " error in " + error.getLocalizedMessage());
    }

    @AfterThrowing(pointcut = "repositoryLayer()", throwing= "error")
    public void throwingDAOMethod(JoinPoint joinPoint, Throwable error) throws Throwable{
        logger.info("Repository " + joinPoint.getTarget().getClass()
                + " Method " + joinPoint.getSignature().getName()
                + " error in " + error.getLocalizedMessage());
    }

    @AfterThrowing(
            pointcut = "userTokenControllerLayer())",  throwing= "error")
    public void throwingUserTokenController(JoinPoint joinPoint, Throwable error) {
        if (error.getClass().equals(TokenException.class)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
