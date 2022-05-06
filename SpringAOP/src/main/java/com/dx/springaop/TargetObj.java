package com.dx.springaop;
//连接点，切入点
public class TargetObj {
    //切入点
    public void method1(String a, int b){
        //System.out.println(1);
        System.out.println(a);
        System.out.println(b);
    }
    public void method2(int a, int b, int c ,int d){
        //System.out.println(1);
        System.out.println(2);
        System.out.println(3);
    }
    public void method3(){
        //System.out.println(1);
        System.out.println(2);
       // System.out.println(10/0);
        System.out.println(3);
    }

    public void method4(String a, int b){
        //System.out.println(1);
        System.out.println("method4:" + a);
        System.out.println("method4:" + b);
    }

    public int method5(int a, int b){
        int c = a + b;
        System.out.println(10/0);
        System.out.println("method5 args a: " + a + ", b: " + b + ", a+b: " + c);
        return c;
    }
}
