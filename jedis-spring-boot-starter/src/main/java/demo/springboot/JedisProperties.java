package demo.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dean.lee
 */
//获取配置文件中的属性
@ConfigurationProperties(prefix = "jedis")
public class JedisProperties {

    private int database = 0;
    private String host = "localhost";
    private String password = "";
    private int port = 6379;

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
