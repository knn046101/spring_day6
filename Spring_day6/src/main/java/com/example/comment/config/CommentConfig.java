package com.example.comment.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan({"com.example.comment.repo"})
@PropertySource("classpath:/config/dbconfig.properties")
public class CommentConfig {

	private static Logger logger = LoggerFactory.getLogger(CommentConfig.class);
	
	@Bean
	public DataSource dataSource(
			@Value("${ds.driverClassName}") String driverClassName,
			@Value("${ds.url}") String url,
			@Value("${ds.userName}") String userName,
			@Value("${ds.password}") String password,
			@Value("${ds.maxTotal}") int maxTotal,
			@Value("${ds.initialSize}") int initialSize,
			@Value("${ds.maxIdle}") int maxIdle){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(userName);
		ds.setPassword(password);
		ds.setMaxTotal(maxTotal);
		ds.setInitialSize(initialSize);
		ds.setMaxIdle(maxIdle);
		logger.trace("DataSource 생성 : {}", ds);
		return ds;
	}
	

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource ds){
	      SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	      bean.setDataSource(ds);
	      String loc ="mybatis/mybatis-config.xml";
	      bean.setConfigLocation(new ClassPathResource(loc));
	      return bean;
	}
	
	@Bean 
	SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sfb) throws Exception{
		SqlSessionTemplate template = new SqlSessionTemplate(sfb.getObject());
		return template;
	}
	
	   
}