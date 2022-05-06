package com.dx.springaop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        TargetObj targetObj = (TargetObj) applicationContext.getBean("target");
        //targetObj.method1("abc",2);
        //targetObj.method2(1,2,3,4);
//        targetObj.method3();
        //targetObj.method4("sad", 5);
        System.out.println("main method5: " + targetObj.method5(3,4));
    }
}
