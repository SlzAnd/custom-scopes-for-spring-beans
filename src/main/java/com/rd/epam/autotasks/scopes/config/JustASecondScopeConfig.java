package com.rd.epam.autotasks.scopes.config;

import customscopes.postprocessors.JustASecondBeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JustASecondScopeConfig {

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new JustASecondBeanFactoryPostProcessor();
    }
}
