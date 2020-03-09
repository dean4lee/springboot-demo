package demo.springboot.atomikos.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import javax.sql.XADataSource;

@Configuration
public class DataSourceConfig {

    /**
     * 创建Druid的XA连接池
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.one")
    public XADataSource druidXADataSource1(){
        return new DruidXADataSource();
    }

    /**
     * 创建Atomikos数据源
     * 注解@DependsOn("druidXADataSource1")，在名为druidXADataSource1的bean实例化后加载当前bean
     * @param xaDataSource
     * @return
     */
    @Bean
    @DependsOn("druidXADataSource1")
    @Primary
    public DataSource dataSource1(@Qualifier("druidXADataSource1") XADataSource xaDataSource) {
        //这里的AtomikosDataSourceBean使用的是spring提供的
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setXaDataSource(xaDataSource);
        return dataSource;
    }


    @Bean
    @ConfigurationProperties("spring.datasource.druid.two")
    public XADataSource druidXADataSource2(){
        return new DruidXADataSource();
    }

    @Bean
    @DependsOn("druidXADataSource2")
    public DataSource dataSource2(@Qualifier("druidXADataSource2") XADataSource xaDataSource) {
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setXaDataSource(xaDataSource);
        return dataSource;
    }
}
