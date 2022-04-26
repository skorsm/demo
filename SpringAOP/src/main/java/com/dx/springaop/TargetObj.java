package com.dx.springaop;
//连接点，切入点
public class TargetObj {
    //切入点
    public void method1(int a, int b){
        //System.out.println(1);
        System.out.println(2);
        System.out.println(3);
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
}
