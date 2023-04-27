package com.sas.springcloudconfigclientdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@RefreshScope
@Data
@Configuration
@ConfigurationProperties(prefix = "skywinds.reimport-source")
/**
 * 注意：
 * 这里写的默认值，只有在yml中没有配置相关字段的时候才能生效；否则还是会被yml中替换掉；
 */
public class YMLConfig {
    @Data
    public static class RangeConfig {
        public Integer min = 100;
        public Integer max = 200;
        // 不建议小写,否则配置为空时，会启动不了；
        // public boolean consumerSwitch = true;
        // 配置为空时，取默认值 true；
        public Boolean consumerSwitch = true;
    }

    Map<String, RangeConfig> range;
    // Map中的key删除后，刷新不会生效，除非在配置类上加@RefreshScope注解
    Integer year;

    Integer age = 10;
}

