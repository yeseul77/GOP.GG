package com.gg.gop.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;


@MapperScan(basePackages="com.gg.gop.dao")
public class MyBatisConfig {
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception{
	 SqlSessionFactoryBean sqlSessionFactory=new SqlSessionFactoryBean();
	 sqlSessionFactory.setDataSource(datasource);
	 sqlSessionFactory.setTypeAliasesPackage("com.gg.gop.dto");
	 return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}


}
