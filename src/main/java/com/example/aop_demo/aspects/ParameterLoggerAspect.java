package com.example.aop_demo.aspects;

import com.example.aop_demo.annotations.LogParameters;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
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
    @Before("@annotation(logParameters)")
    public void logParameters(JoinPoint joinPoint, LogParameters logParameters) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        List paramList = removeNullsFromParamList(joinPoint.getArgs());
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        if(CollectionUtils.isEmpty(paramList)) {
            logger.log(Level.INFO, className + "." + methodName + "() has zero args");
            return;
        }
        paramList =removeUnwantedParamsFromParamList(joinPoint.getArgs(), signature.getParameterNames(), logParameters.params());


        String params = Arrays.toString(paramList.stream().filter(p -> StringUtils.hasText(p.toString())).toArray());
        logger.log(Level.INFO, className + "." + methodName + "() has args: " + params);
    }

    private List removeNullsFromParamList(Object[] paramList){
        List list = new ArrayList<>(Arrays.asList(paramList));
        list.removeAll(Collections.singleton(null));
        return list;
    }

    private List removeUnwantedParamsFromParamList(Object[] paramList, String[] methodParams, String[] wantedParams){
        List list = new ArrayList<>(Arrays.asList(paramList));
        for(int i = 0; i < methodParams.length; i++) {
            if(!Arrays.asList(wantedParams).contains(methodParams[i])) {
                list.remove(paramList[i]);
            }
        }
        return list;
    }
}
