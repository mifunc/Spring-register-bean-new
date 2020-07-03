package com.rumenz;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonBuilderUtils;


public class DemoApplication {
    public static  void main1(String[] args) throws InterruptedException {

        //
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

    public static void main(String[] args) {

        //
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
