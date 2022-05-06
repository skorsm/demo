#一、SpringAOP 相关概念
##1. 连接点(Joinpoint)
    连接点指类中的方法
##2. 切入点(Pointcut)
    切入点指被抽取了共性功能的方法
    切入点一定是连接点，连接点不一定是切入点
##3. 通知(Advice)
    通知就是抽取的共性功能组成的代码逻辑，被封装在某个类的某个方法中，被抽取的共性代码
    可能来自于被抽取方法的任何位置。例如切入点代码逻辑的前面、中间或者后面，被抽取的代码
    在切入点中的具体位置，称为通知类别。
##4. 引入(Introduction)
    通知仅表示切入点被抽取的代码逻辑，对于切入点所在的类，如果存在有共性的成员变量或者
    成员方法，通知将无法进行描述。AOP提供引入机制，将共性功能的成员进行加入。
    引入机制可以为类添加额外的成员变量或者成员方法
    引入机制是在编译期或类加载期完成的
##5. 目标对象(TargetObject)
    目标对象指包含切入点的类的对象
##6. AOP代理(AOP Proxy)
    切入点所在的类对象执行切入点方法时，需要将原始的共性功能（通知）加入，此时通过代理的
    形式创建类对象，并完成共性功能（通知）的加入，上述过程称为AOP代理。
    AOP代理的意义是将被抽取共性功能的类对象创建出来，同时将共性功能（通知）加入，完成原始
    的完整操作的执行过程。
##7. 切面(Aspect)
    切面是一个设计概念，指切入点与通知的匹配模式，换句话说指被抽取了共性功能的方法（切入点）
    与被抽取了共性功能（通知）封装的对象对应的绑定关系。
    程序设计时，可以设置多个切面，用来描述切入点与通知之间的关系。
##8. 织入(Weaving)
    通过AOP代理，完成了切入点与通知的融合，并组成了完整的代码逻辑，将通知加入到切入点对应
    位置的动作称为织入。
    织入是一个动态过程，不对应任何代码，可以理解为动态的运行过程。

#二、切入点表达式
    用来匹配切入点方法
    格式：execution(切入点表达式)
***例如***
```xml
<aop:config>
        <!--配置切面-->

        <aop:aspect ref="advice">
            <!--配置切面，把通知类中的方法和目标类中的方法进行关联-->
            <!--切入点表达式，要把方法的包名和类名都带上，方法修饰符可以省略-->
            <aop:before method="one" pointcut="execution(public void com.dx.springaop.TargetObj.method1())" />
        </aop:aspect>
    </aop:config>
```

##通配符：*
###1、可以匹配返回值类型
```xml
<!--匹配返回值类型-->
<aop:before method="one" pointcut="execution(* com.dx.springaop.TargetObj.method1())" />
```
###2、可以匹配包名
```xml
<!--匹配包名-->
<aop:before method="one" pointcut="execution(* *.*.springaop.TargetObj.method1())" />
```
```xml
<!--"*.."表示匹配任意层级的包名匹配-->
<aop:before method="one" pointcut="execution(* *..TargetObj.method1())" />
```
###3、可以匹配类名
```xml
<!--"*"号可以表示任意开头、结尾或者中间的字符串-->
<!--"Tar*"表示以Tar开头的类名-->
<aop:before method="one" pointcut="execution(* *..Tar*.method1())"/>
```
###4、可以匹配方法名
```xml
<!--"*"号可以表示任意开头、结尾或者中间的字符串-->
<!--"met*"表示以Tar开头的方法名-->
<aop:before method="one" pointcut="execution(* *..Tar*.met*())"/>
```
###5、可以匹配参数
```xml
<!--".."号可以表示任意多个参数(包括0个).也可以直接与关键字联用或者单用关键字.还可以与"*"联用，三者可以排列组合使用-->
<aop:before method="one" pointcut="execution(* *..Tar*.met*(int,*,..))"/>
```

##切入点配置方式
###1、局部切入点
```xml
<aop:config>
        <!--配置切面-->
        <aop:aspect ref="advice">
            <!--配置切面，把通知类中的方法和目标类中的方法进行关联-->
            <!--切入点表达式，要把方法的包名和类名都带上，方法修饰符可以省略-->
            <aop:before method="one" pointcut="execution(public void com.dx.springaop.TargetObj.method1())" />
        </aop:aspect>
    </aop:config>
```
###2、切面间共享切入点
```xml
<aop:config>
    <!--切面间共享切入点-->
    <aop:pointcut id="abc" expression="execution(public void com.dx.springaop.TargetObj.method3())"/>
    <aop:aspect ref="advice">
        <aop:before method="one" pointcut-ref="abc"/>
    </aop:aspect>
    <aop:aspect ref="advice">
        <aop:before method="two" pointcut-ref="abc"/>
    </aop:aspect>
</aop:config>
```
###3、切面内共享切入点
```xml
<aop:config>
<!--切面内共享切入点-->
    <aop:aspect ref="advice">
        <aop:pointcut id="abc" expression="execution(* *..TargetObj.method3())"/>
        <aop:before method="two" pointcut-ref="abc"/>
        <aop:before method="one" pointcut-ref="abc"/>
    </aop:aspect>
</aop:config>
```

#三、AOP通知类型
    通知类型就是通知位置的意思
###1、before：前置通知（应用：各种校验）
    在方法执行前执行
    该方法中出现了异常，不会影响前置通知的执行
```xml
<aop:aspect ref="advice">
    <aop:pointcut id="abc" expression="execution(* *..TargetObj.method3())"/>
    <!--前置通知-->
    <aop:before method="before" pointcut-ref="abc"/>
</aop:aspect>
```
###2、after：后置通知（应用：清理现场）
    在方法执行后执行
    方法执行完毕后执行，无论方法中是否出现异常都会执行
```xml
<aop:aspect ref="advice">
    <aop:pointcut id="abc" expression="execution(* *..TargetObj.method3())"/>
    <!--后置通知-->
    <aop:after method="after" pointcut-ref="abc"/>
</aop:aspect>
```
###3、afterReturning：返回后处理（应用：常规数据处理）
    方法执行完毕后执行，如果方法中抛出异常，无法执行
```xml
<aop:aspect ref="advice">
    <aop:pointcut id="abc" expression="execution(* *..TargetObj.method3())"/>
   <!--返回后通知-->
    <aop:after-returning method="afterReturning" pointcut-ref="abc"/>
</aop:aspect>
```
###4、afterThrowing：抛出异常后通知（应用：包装异常信息）
    方法抛出异常后执行，如果方法没有抛出异常，无法执行
```xml
<aop:aspect ref="advice">
    <aop:pointcut id="abc" expression="execution(* *..TargetObj.method3())"/>
    <!--抛出异常后通知-->
    <aop:after-throwing method="afterThrowing" pointcut-ref="abc"/>
</aop:aspect>
```
###5、around：环绕通知（应用：十分强大，可以做任何事情）
    方法执行前后分别执行，如果方法中有异常，末尾的通知就不执行了
```xml
<aop:aspect ref="advice">
    <aop:pointcut id="abc" expression="execution(* *..TargetObj.method3())"/>
    <!--环绕通知-->
    <aop:around method="around" pointcut-ref="abc"/>
</aop:aspect>
```
***注意：*** 要想使用环绕通知，必须有一个调用切入点方法的动作。此时在对应通知类中需要传入ProceedingJoinPoint类的参数代表切入点方法，执行proceed()方法代表切入点方法执行了。例如：
```java
 public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around......start....");
        proceedingJoinPoint.proceed();
        System.out.println("around......end....");
}
```

#四、AOP执行相对顺序
    AOP通知类型存在5种，其中分为在方法前执行和方法后执行。
    方法前执行：before、around；
    方法后执行：after、afterReturning、around；
###1、同一个切面中通知的相对顺序
    前置通知的执行顺序和在配置文件中配置的顺序一致；
    后置通知的执行顺序也和在配置文件中配置的顺序一致；
    总之，先配置先执行
###2、不同切面中通知的相对顺序
    前置通知的执行顺序和在配置文件中配置的顺序一致；
    后置通知的配置顺序和执行顺序相反，越是最后配置的越先执行，越先配置的越后执行。

#AOP通知获取匹配方法的参数
###1、通过给通知添加JoinPoint形参来获得被匹配方法的参数(around通知是ProceedingJoinPoint形参)，改参数位于形参的第一位，使用对应的JoinPoint的getArgs()获取对应的值。例如：
```xml
<aop:pointcut id="abc" expression="execution(* *..TargetObj.method1(..))"/>
<!--切面内共享切入点-->
<aop:aspect ref="advice">
    <!--前置通知-->
    <aop:around method="around" pointcut-ref="abc"/>
    <aop:before method="before" pointcut-ref="abc"/>

    <!--后置通知-->
    <aop:after method="after" pointcut-ref="abc"/>
    <!--返回后通知-->
</aop:aspect>
```
```java
public void before(JoinPoint joinPoint){
    Object[] args = joinPoint.getArgs();
    for (Object obj : args){
        System.out.println("before：" + obj);
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
```
###2、AOP通知还可以使用切入点表达式指定通知参数名，参数的名称为通知中用于获取参数值的形参名称，例如：
```xml
<aop:pointcut id="method4" expression="execution(* com.dx.springaop.TargetObj.method4(..)) &amp;&amp; args(a,b)"/>
<aop:aspect ref="advice">
    <aop:before method="before2" pointcut-ref="method4"/>
</aop:aspect>
```
```java
public void before2(String a, int b){
    System.out.println("before2 args: " + a + ",args: " + b);
    System.out.println("before......");
}
```

#AOP通知修改目标对象方法参数(只有around通知的ProceedingJoinPoint 形参可以在修改形参后再重新调用切入点方法), 例：
```xml
<aop:config>
    <aop:pointcut id="method5" expression="execution(* com.dx.springaop.TargetObj.method5(..))"/>
    <aop:aspect ref="advice">
        <aop:around method="around2" pointcut-ref="method5"/>
    </aop:aspect>
</aop:config>
```
```java
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
```

#AOP获取目标对象方法返回值
###1.afterReturning通知：
```xml
<aop:config>
    <aop:pointcut id="method5" expression="execution(* com.dx.springaop.TargetObj.method5(..))"/>
    <aop:aspect ref="advice">
        <!--需要配置一个返回值参数returning-->
        <aop:after-returning method="afterReturning1" pointcut-ref="method5" returning="rst"/>
    </aop:aspect>
</aop:config>
```
```java
public void afterReturning1(Object rst){
    System.out.println("afterReturning......result: " + rst);
}
```
###2.around通知：
```xml
<aop:config>
    <aop:pointcut id="method5" expression="execution(* com.dx.springaop.TargetObj.method5(..))"/>
    <aop:aspect ref="advice">
        <aop:around method="around2" pointcut-ref="method5"/>
    </aop:aspect>
</aop:config>
```
```java
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
```

#AOP通知获取异常
```xml
<aop:config>
    <aop:aspect ref="advice">
        <!--需要配置一个异常参数throwing-->
        <aop:after-throwing method="afterThrowing1" pointcut-ref="method5" throwing="e"/>
    </aop:aspect>
</aop:config>
```
```java
public void afterThrowing1(Throwable e){
    e.printStackTrace();
    System.out.println("afterThrowing......");
}
```