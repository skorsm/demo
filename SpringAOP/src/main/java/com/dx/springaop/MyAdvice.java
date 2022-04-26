package com.dx.springaop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

public class MyAdvice {
    //通知类，抽取目标对象中的共性代码
    public void one(){
        System.out.println(1);
    }

    public void two(){
        System.out.println(123333);
    }

    public void before(){
        System.out.println("before......");
    }

    public void after(){
        System.out.println("after......");
    }

    public void afterReturning(){
        System.out.println("afterReturning......");
    }

    public void afterThrowing(){
        System.out.println("afterThrowing......");
    }

    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around......start....");
        proceedingJoinPoint.proceed();
        System.out.println("around......end....");
    }
}
