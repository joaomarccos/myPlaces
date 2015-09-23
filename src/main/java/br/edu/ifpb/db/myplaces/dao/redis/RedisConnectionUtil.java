package br.edu.ifpb.db.myplaces.dao.redis;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import redis.clients.jedis.Jedis;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class RedisConnectionUtil {

    private Properties properties;
    private static final String PATH = "src/main/resources/redisConfig.properties";
    private Jedis connection;

    public RedisConnectionUtil() {
        loadConfigFile();
    }

    private void loadConfigFile() {
        this.properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(PATH))) {
            this.properties.load(in);
        } catch (IOException ex) {
            Logger.getLogger(RedisConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getServer() {
        return this.properties.getProperty("server");
    }

    private int getPort() {
        return Integer.parseInt(this.properties.getProperty("port"));
    }
    
    public Jedis getConnection(){
        this.connection = new Jedis(getServer(), getPort());
        return connection;
    }
    
    public void closeConnection(){
        this.connection.close();
    }
}
