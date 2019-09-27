package com.qf.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

@Aspect
public class MylogAspect {
    @Pointcut("execution(* com.qf.dao.*(..))")
    public void log(){}

    @Before("log()")
    public void myLog(JoinPoint jp){
        System.out.println(jp.getSignature().getName()+" 于 "+ new Date() + "执行了");
    }
}
