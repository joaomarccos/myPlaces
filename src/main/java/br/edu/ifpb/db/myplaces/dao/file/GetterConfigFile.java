package br.edu.ifpb.db.myplaces.dao.file;

import java.io.File;
import java.nio.file.Path;

/**
 *
 * @author joaomarcos
 */
public class GetterConfigFile {
    private static final String PATH = "/config.properties";
    
    public Path getPath() {
        File file = new File(getClass().getResource(PATH).getPath());
        return file.toPath();
    }
}
