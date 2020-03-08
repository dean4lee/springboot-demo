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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
//指定扫描的dao包和SqlSession实例
@MapperScan(basePackages = "demo.springboot.multipledatasource.dao1", sqlSessionTemplateRef="oneSqlSessionTemplate")
public class DataSource1Config {

    /**
     * 连接池
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource oneDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //mapper文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/one/*.xml"));
        return bean.getObject();
    }

    /**
     * 事务
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager oneTransactionManager(@Qualifier("oneDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * SqlSession实例
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate oneSqlSessionTemplate(@Qualifier("oneSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
