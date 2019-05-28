# ucat-boots



## 概述

ucat-boots是基于springboot的一些工具的整合,整合一些基础配置类,减少项目开发配置的复杂度,做到一次配置到处使用的效果(其实就是懒)




## 开发环境

- 操作系统：Windows 10 

- 开发工具：Intellij IDEA

- 数据库：MySQL 8.0.13

- Java SDK：Oracle JDK 1.8.152

  

## 主要技术栈

- 核心框架：Spring Boot 
- ORM 框架：mybatis plugs
- 数据库连接池：Alibaba Druid
- 数据库缓存：Spring data Redis
- 消息中间件：RocketMQ
- 接口文档引擎：Swagger2 RESTful 风格 API 文档生成
- 全文检索引擎：ElasticSearch
- 分布式链路追踪：SkyWalking



## 模块说明

-  #### ucat-boots-common

基础公共包

- #### ucat-boots-examples

  starter示例演示

  - ##### ucat-boots-example-db

    演示 ucat-boots-starter-mybatis-plugs 的使用,定义了标准的接口,实现快速增删改查

- #### ucat-boot-starter

  快速启动包集合
	
  - ##### Db starter
  
    - ###### ucat-boots-starter-db
  
      有关包含数据库的启动包,
  
      1. 使用了 Druid 作为连接池,和 spring data jdbc.
      2. 支持多数据源,初始数据源名称为default,默认使用初始数据源
      3. 使用@Db切换数据源
  
      ```yaml
      spring:
        datasource:
          # 使用阿里的Druid连接池
          type: com.alibaba.druid.pool.DruidDataSource
          driver-class-name: com.mysql.jdbc.Driver
          # 填写多数据源,当需要多数据源,multi必填,test2则是数据源的名称,然后填写数据库的url、登录名、密码和数据库名
          multi:
            test2:
              url: jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&serverTimezone=Asia/Shanghai
              username: root
              password: root
          # 填写你数据库的url、登录名、密码和数据库名
          url: jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8
          username: root
          password: root
          druid:
            # 连接池的配置信息
            # 初始化大小，最小，最大
            initial-size: 20
            min-idle: 10
            maxActive: 40
            # 配置获取连接等待超时的时间
            maxWait: 60000
            # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
            timeBetweenEvictionRunsMillis: 60000
            # 配置一个连接在池中最小生存的时间，单位是毫秒
            minEvictableIdleTimeMillis: 300000
            validationQuery: SELECT 1 FROM DUAL
            testWhileIdle: true
            testOnBorrow: false
            testOnReturn: false
            # 打开PSCache，并且指定每个连接上PSCache的大小
            poolPreparedStatements: true
            maxPoolPreparedStatementPerConnectionSize: 20
            # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
            filters: stat,wall,log4j
            # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
            connectionProperties: druid.stat.mergeSql\=true;druid.stat.slowSqlMillis\=5000
            # 配置DruidStatFilter
            web-stat-filter:
              enabled: true
              url-pattern: "/*"
              exclusions: "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"
            # 配置DruidStatViewServlet
            stat-view-servlet:
              url-pattern: "/druid/*"
              # IP白名单(没有配置或者为空，则允许所有访问)
              allow: 127.0.0.1,192.168.163.1
              # IP黑名单 (存在共同时，deny优先于allow)
              deny: 192.168.1.73
              #  禁用HTML页面上的“Reset All”功能
              reset-enable: false
              # 登录名
              login-username: admin
              # 登录密码
              login-password: 123456
      ```
    
  - ###### ucat-boots-starter-mybatis-plugs-common
    
    1. BaseDbservice 提供直面数据库的方法
    2. BaseCrudService 提供基础增删改查 业务方法
      3. AbstractBaseController 提供基础增删改查接口和返回方法
      4. 默认的id是uuid(如有不爽,请修改 AbstractBaseEntity)
      5. 默认有createtime(如有不爽,请修改 AbstractBaseEntity)
      6. 默认有updatetime(如有不爽,请修改 AbstractBaseEntity)
    
    - ###### ucat-boots-starter-mybatis-plugs
    
      集成了 mybatis plugs 和上面的db 和mybatis-plugs-common包
    
      1. 需要在springboot启动类上加上 @MapperScan 指定mapper所在的包进行扫描
      2. 拥有基础增删改查接口
    3. 默认增加的时候给createtime和updatetime赋值时间
      4. 默认修改的时候修改updatetime的时间
    
      ```yml
      mybatis-plus:
        global-config:
          db-config:
            id-type: uuid
            field-strategy: not_empty
          banner: false
        #指定实体类的包名  
      type-aliases-package: top.ucat.boots.example.db.entity
      
      ```
      
    
      
    
    
    
    
  
