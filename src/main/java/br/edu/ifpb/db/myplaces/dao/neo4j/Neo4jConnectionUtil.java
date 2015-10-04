package br.edu.ifpb.db.myplaces.dao.neo4j;

import br.edu.ifpb.db.myplaces.dao.file.GetterConfigFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaomarcos
 */
public class Neo4jConnectionUtil {

    private Properties properties;
    private Connection connection;

    public Neo4jConnectionUtil() {
        loadConfigFile();
    }

    private void loadConfigFile() {
        this.properties = new Properties();
        try (InputStream in = Files.newInputStream(new GetterConfigFile().getPath())) {
            this.properties.load(in);
        } catch (IOException ex) {
            Logger.getLogger(Neo4jConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            return this.connection;
        }
        Class.forName("org.neo4j.jdbc.Driver");
        this.connection = DriverManager.getConnection(getURL(), getUser(), getPassword());            
        return this.connection;
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    private String getURL() {
        return this.properties.getProperty("neo4j.url");
    }

    private String getUser() {
        return this.properties.getProperty("neo4j.user");
    }

    private String getPassword() {
        return this.properties.getProperty("neo4j.password");
    }

}
