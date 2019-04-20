package com.sas.brd;

import com.sas.brd.model.InitBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        for (int i = 0; i < 10; i++) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(InitBean.class);
//            builder.addConstructorArgValue("InitBean"+i);
            builder.addPropertyValue("name", "hahaha" + i);
            BeanDefinition definition = builder.getBeanDefinition();
            registry.registerBeanDefinition("InitBean"+i,definition);
        }
    }
}
