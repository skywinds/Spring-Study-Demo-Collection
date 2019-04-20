package com.sas.brd;

import com.sas.brd.Service.AsyncService;
import com.sas.brd.model.InitBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@Import(MyImportBeanDefinitionRegistrar.class)
@EnableAsync
public class BrdApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BrdApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();

        SpringApplicationUtils.setApplicationContext(run);
        for (String beanName : beanFactory.getBeanNamesForType(InitBean.class)) {
            System.out.println(beanName);
        }

        AsyncService asyncService = beanFactory.getBean(AsyncService.class);
        for (InitBean bean : beanFactory.getBeansOfType(InitBean.class).values()) {
            System.out.println(bean.toString());
            asyncService.startJob();
        }
    }

    @EnableAsync
    @Configuration
    class TaskPoolConfig {
        @Bean("taskExecutor")
        public Executor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(20);
            executor.setQueueCapacity(200);
            executor.setKeepAliveSeconds(60);
            executor.setThreadNamePrefix("taskExecutor-");
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return executor;
        }
    }


}
