package com.anryus.room.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws RuntimeException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        assertApplicationContext();
        return SpringContextHolder.applicationContext;
    }

    public static <T> T getBean(String beanName){
        assertApplicationContext();
        return (T)SpringContextHolder.applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> typeName){
        assertApplicationContext();
        return applicationContext.getBean(typeName);
    }

    private static void assertApplicationContext(){
        Assert.notNull(applicationContext, "applicationContext");
    }
}

