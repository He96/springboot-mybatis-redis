package com.spm.common;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;


/**
 * Created by he on 2017/10/27.
 */
public class ConfigUtil {
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

    public static String REDIES_PROJECT_NAME = config.getString("REDIES_PROJECT_NAME");
    public static String FAST_TRACKERS_IP = config.getString("FAST_TRACKERS_IP");

}
