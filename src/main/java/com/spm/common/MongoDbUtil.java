package com.spm.common;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by luochaojun on 2017/10/25.
 */
public class MongoDbUtil {
    /*public static final String fileName = "mongoDB.properties";
    private static PropertiesConfiguration config;
    public static MongoDatabase mongoDatabase;

    static {
        try {
            config = new PropertiesConfiguration(fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        config.setReloadingStrategy(new FileChangedReloadingStrategy());
    }

    public static final boolean MONGODB_ISCLUSTER = config.getBoolean("mongodb_iscluster");
    public static final String MONGODB_URLS = config.getString("mongodb_urls");
    public static final Integer MONGODB_PORT = config.getInt("mongodb_port");
    public static final String MONGODB_DATABASE = config.getString("mongodb_database");
    public static final String MONGODB_PASSWORD = config.getString("mongodb_password");
    public static final String MONGODB_USERNAME = config.getString("mongodb_username");
    public static final String MONGODB_HOST = config.getString("mongodb_host");

    static {
        if (MONGODB_ISCLUSTER) {
            List<ServerAddress> addresses = new ArrayList<>();
            for (String str : MONGODB_URLS.split("T")) {
                addresses.add(new ServerAddress(str.split(":")[0].trim(), Integer.parseInt(str.split(":")[1].trim())));
            }
            MongoClient mongoClient = new MongoClient(addresses);
            mongoDatabase = mongoClient.getDatabase(MONGODB_DATABASE);
        } else {
            ServerAddress address = new ServerAddress(MONGODB_HOST, MONGODB_PORT);
            MongoCredential credential = MongoCredential.createCredential(MONGODB_USERNAME, MONGODB_DATABASE, MONGODB_PASSWORD.toCharArray());
            MongoClient mongoClient = new MongoClient(address);
            mongoDatabase = mongoClient.getDatabase(MONGODB_DATABASE);
            //return db;
        }
    }*/
}
