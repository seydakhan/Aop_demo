package com.example.aop_demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class ParameterLoggerAspect {

    private static final Logger logger = Logger.getLogger("ParameterLoggerAspect");
    @Before("@annotation(com.example.aop_demo.annotations.LogParameters)")
    public void logParameters(JoinPoint joinPoint) {
        List paramList = removeNullsFromParamList(joinPoint.getArgs());
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        if(CollectionUtils.isEmpty(paramList)) {
            logger.log(Level.INFO, className + "." + methodName + "() has zero args");
            return;
        }
        String params = Arrays.toString(paramList.stream().filter(p -> StringUtils.hasText(p.toString())).toArray());
        logger.log(Level.INFO, className + "." + methodName + "() has args: " + params);
    }

    private List removeNullsFromParamList(Object[] paramList){
        List list = new ArrayList<>(Arrays.asList(paramList));
        list.removeAll(Collections.singleton(null));
        return list;
    }
}
