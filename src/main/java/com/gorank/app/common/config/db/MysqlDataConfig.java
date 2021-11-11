package com.gorank.app.common.config.db;

import java.util.HashMap;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//DB 여러개가 필요한 경우를 대비한 서정. java bean으로 컨피그 설정 정보를 셋팅한다.
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "mysqlEntityManager",
    transactionManagerRef = "mysqlTransactionManager",
    basePackages = "com.gorank.app.stock")
public class MysqlDataConfig {

  @Autowired
  private Environment env;

  @PostConstruct
  public void init() {
    System.out.println("=============================Jpa Configuration Start");
  }

  @Primary
  @Bean
  public DataSource mysqlDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.main.datasource.driverClassName")));
    dataSource.setUrl(env.getProperty("spring.main.datasource.url"));
    dataSource.setUsername(env.getProperty("spring.main.datasource.username"));
    dataSource.setPassword(env.getProperty("spring.main.datasource.password"));
    return dataSource;
  }

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean mysqlEntityManager() {
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    localContainerEntityManagerFactoryBean.setDataSource(mysqlDataSource());
    localContainerEntityManagerFactoryBean.setPackagesToScan(new String[]{"com.gorank.app.stock.domain.entity"});
    localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());//하이버네이트 구현체 적용

    //Jpa 프로퍼티 설정
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.main.hibernate.hbm2ddl.auto"));
    properties.put("hibernate.dialect", env.getProperty("spring.main.hibernate.dialect"));
    properties.put("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
    properties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
    properties.put("hibernate.use_sql_comments", env.getProperty("spring.jpa.properties.hibernate.use_sql_comments"));
    properties.put("hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));

    localContainerEntityManagerFactoryBean.setJpaPropertyMap(properties);
    return localContainerEntityManagerFactoryBean;
  }

  @Primary
  @Bean
  public PlatformTransactionManager mysqlTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(mysqlEntityManager().getObject());
    return transactionManager;
  }
}