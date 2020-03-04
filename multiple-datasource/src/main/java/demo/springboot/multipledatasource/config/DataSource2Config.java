package demo.springboot.multipledatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "demo.springboot.multipledatasource.dao2", sqlSessionTemplateRef="twoSqlSessionTemplate")
public class DataSource2Config {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.two")
    public DataSource twoDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory twoSqlSessionFactory(@Qualifier("twoDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/two/*.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager oneTransactionManager(@Qualifier("twoDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate twoSqlSessionTemplate(@Qualifier("twoSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
