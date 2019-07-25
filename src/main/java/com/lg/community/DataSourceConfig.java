package com.lg.community;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = {"classpath:jdbc.properties"})//也可以使用@ConfigurationProperties直接注入属性
public class DataSourceConfig {
    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driverClassName}")
    private String jdbcDriverClassName;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    /**
     * 配置数据库
     *
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        // 数据库驱动
        datasource.setDriverClassName(jdbcDriverClassName);
        // 相应驱动的jdbcUrl
        datasource.setUrl(jdbcUrl);
        // 数据库的用户名
        datasource.setUsername(jdbcUsername);
        // 数据库的密码
        datasource.setPassword(jdbcPassword);
        datasource.setTestWhileIdle(false);
        // 每个分区最大的连接数
        datasource.setMaxActive(20);
        // 每个分区最小的连接数
        datasource.setMinIdle(5);
        return datasource;
    }

}