package com.dx.springaop;


import org.aspectj.lang.JoinPoint;
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

    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object obj : args){
            System.out.println("before：" + obj);
        }
        System.out.println("before......");
    }

    public void before2(String a, int b){
        System.out.println("before2 args: " + a + ",args: " + b);
        System.out.println("before......");
    }

    public void before3(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++){
            args[i] = (int)args[i] + 10;
        }
        System.out.println("before......");
    }

    public void after(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object obj : args){
            System.out.println("after：" + obj);
        }
        System.out.println("after......");
    }

    public void afterReturning(){
        System.out.println("afterReturning......");
    }
    public void afterReturning1(Object rst){
        System.out.println("afterReturning......result: " + rst);
    }

    public void afterThrowing(){
        System.out.println("afterThrowing......");
    }

    public void afterThrowing1(Throwable e){
        e.printStackTrace();
        System.out.println("afterThrowing......");
    }

    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around......start....");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object obj : args){
            System.out.println("around before：" + obj);
        }
        proceedingJoinPoint.proceed();
        args = proceedingJoinPoint.getArgs();
        for (Object obj : args){
            System.out.println("around after：" + obj);
        }
        System.out.println("around......end....");
    }

    public int around2(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around......start....");
        Object[] args = proceedingJoinPoint.getArgs();
        for (int i = 0; i < args.length; i++){
            args[i] = (int)args[i] + 10;
        }
        int rst = (Integer) proceedingJoinPoint.proceed(args);
        System.out.println("around2 result: " + rst);
        System.out.println("around......end....");

        return rst;
    }
}
