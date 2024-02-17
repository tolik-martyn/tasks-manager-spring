package com.example.tasksmanager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserActionLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(UserActionLoggingAspect.class);

    @AfterReturning(pointcut = "@annotation(com.example.tasksmanager.aspect.TrackUserAction)", returning = "result")
    public void logUserAction(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        for (Object arg : args) {
            params.append(arg).append(", ");
        }
        if (!params.isEmpty()) {
            params.setLength(params.length() - 2);
        }
        String className = joinPoint.getTarget().getClass().getName();
        logger.info("User action - Method: {} in Class: {} with Parameters: {}", methodName, className, params.toString());
    }
}