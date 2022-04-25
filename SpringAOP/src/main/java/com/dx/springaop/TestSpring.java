package com.dx.springaop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        TargetObj targetObj = (TargetObj) applicationContext.getBean("target");
        targetObj.method1();
        targetObj.method2();
    }
}
