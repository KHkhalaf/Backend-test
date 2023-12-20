package com.backend.quiz.Aspect;

import com.backend.quiz.BackendApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private Object principal;
    private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);

    @Pointcut(value = "execution(* com.backend.quiz.services.SaleOperationService.editSaleOperation(..))")
    public void loggingPointCut(){

    }

    @Around(value = "loggingPointCut()")
    public Object loggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        this.principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();

        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();

        // Before
        Object[] args = proceedingJoinPoint.getArgs();
        logger.info("Inside {} class {} method, with request : {} >> By \"{}\"", className, methodName, mapper.writeValueAsString(args), userName);

        // After
        Object response =  proceedingJoinPoint.proceed();
        logger.info("Inside {} class {} method, with response : {} >> By \"{}\"", className, methodName, mapper.writeValueAsString(response), userName);

        return response;
    }
}
