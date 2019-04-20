spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/skywinds/spring-cloud-config-repo
          search-paths: cellInfo-service
          username: skywinds1983@gmail.com
          password: Nec_eidolon76
      label: master
    
上述配置完后，服务启动，固定的到 https://github.com/skywinds/spring-cloud-config-repo/cellInfo-service 下找文件

请求方式如下：http://localhost:8888/database-dev.yml

如果要动态区分目录来寻找配置文件，则需要改配置：
search-paths: ${profile} 或者 search-paths: {application}

这个值是从config-client上传的，ex:spring.cloud.config.profile=xxx
