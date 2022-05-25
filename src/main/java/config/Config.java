package config;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Properties;

public class Config {
    private Properties properties;

    public Config(){
        properties=getProperties();
    }

    private Properties getProperties(){
        Properties prop = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load properties file: " + e);
        }
        return prop;
    }
    public String getApplicationUrl(){
        return properties.getProperty("application.url");
    }
    public String getLogin(){
        return properties.getProperty("application.user");
    }
    public String getPassword(){
        return properties.getProperty("application.password");
    }
}
