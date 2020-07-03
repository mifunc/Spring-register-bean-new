# 如何注册Spring Bean
- BeanDefinition
- 外部单体对象(对象的生命周期并不由Spring来直接管理)

**Rumenz.java**

```java
package com.rumenz;
public class Rumenz {
    public void print(){
        System.out.println(".......");
    }
}

```

## BeanDefinition(BeanDefinitionRegistry)

```java
package com.rumenz;

public class DemoApplication {
  
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        registerBean(ac,"rumenz");
        //启动Spring上下文
        ac.refresh();
        //依赖查找
        Rumenz rumenz = (Rumenz) ac.getBean("rumenz");
        rumenz.print();
        //关闭Spring上下文
        ac.close();
    }

    private static void registerBean(BeanDefinitionRegistry reg, String beanName){
        BeanDefinitionBuilder bd=BeanDefinitionBuilder.genericBeanDefinition(Rumenz.class);
        reg.registerBeanDefinition(beanName,bd.getBeanDefinition());
    }

}

```

## 外部单体对象(对象的生命周期并不由Spring来直接管理)

```java
package com.rumenz;

public class DemoApplication {
    public static  void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        //自己new一个对象
        Rumenz r=new Rumenz();
        ConfigurableListableBeanFactory beanFactory = ac.getBeanFactory();
        //注册一个对象
        beanFactory.registerSingleton("rumenz",r);
        //启动Spring上下文
        ac.refresh();
        //依赖查找
        Rumenz rumenz = (Rumenz) ac.getBean("rumenz");
        rumenz.print();
        //关闭Spring上下文
        ac.close();
    }

}

```
源码:https://github.com/mifunc/Spring-register-bean-new
