package com.spm.common;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * Created by xiangshiquan on 2017/10/14.
 */
public class RedisConfig {
    public static final String fileName = "redis.properties";
    private static PropertiesConfiguration config;

    static {
        try {
            config = new PropertiesConfiguration(fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        config.setReloadingStrategy(new FileChangedReloadingStrategy());
    }

    public static final Boolean REDIS_ISCLUSTER=config.getBoolean("redis_iscluster");
    public static final String[] REDIS_URLS=config.getStringArray("redis_urls");
    public static final Integer REDIS_PORT=config.getInteger("redis_port",0);
    public static final String REDIS_PWD=config.getString("redis_pwd");

}
