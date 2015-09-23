package br.edu.ifpb.db.myplaces.dao.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Marcos F <joaomarccos.ads@gmail.com>
 */
public class MongoDBConnectionUtil {

    private Properties properties;
    private static final String PATH = "src/main/resources/config.properties";
    private MongoClient mongoClient;
    private MongoDatabase db;

    public MongoDBConnectionUtil() {
        loadConfigFile();
    }

    private void loadConfigFile() {
        this.properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(PATH))) {            
            this.properties.load(in);
        } catch (IOException ex) {
            Logger.getLogger(MongoDBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);            
        }
    }

    public MongoDatabase getConnection() {
        if (this.db == null) {
            this.mongoClient = new MongoClient(getHost(), getPort());
            this.db = this.mongoClient.getDatabase(getDB());
        }
        return this.db;
    }

    public void close() {
        this.mongoClient.close();
    }

    private String getHost() {
        return this.properties.getProperty("mongodb.host");
    }

    private int getPort() {
        return Integer.parseInt(this.properties.getProperty("mongodb.port"));
    }

    private String getDB() {
        return this.properties.getProperty("mongodb.dbname");
    }

}
