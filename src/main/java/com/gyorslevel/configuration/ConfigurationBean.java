package com.gyorslevel.configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 * Retrieves configuration values from key-value files.
 *
 * @author dave00
 */
public class ConfigurationBean {

    private static Logger logger = Logger.getLogger(ConfigurationBean.class);

    public enum ConfigurationBeanKey {

        Domain("gyorslevel.domains", String.class),
        TimeOut("expiration.timeout", Long.class),
        ContextFolder("context.folder", String.class),
        JamesHost("james.host", String.class),
        Mode("mode", String.class);
        private String key;
        private Class type;

        ConfigurationBeanKey(String key, Class type) {
            this.key = key;
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public Class getType() {
            return type;
        }
    }
    // This can be a cache map later on
    private static Map<String, Object> configValues = new ConcurrentHashMap<String, Object>();

    public static <T> T getValue(ConfigurationBeanKey key, Class<T> type) {

        if (key == null) {
            throw new IllegalArgumentException();
        }

        Configuration config = getConfiguration();
        final String keyValue = key.getKey();

        switch (key) {
            case ContextFolder:
            case JamesHost:
            case Mode:
                return type.cast(config.getString(keyValue));
            case Domain:
                String[] stringArray = config.getStringArray(keyValue);
                return type.cast(stringArray[0]);
            case TimeOut:
                return type.cast(config.getLong(keyValue));
            default:
                throw new MissingConfigurationEntry(keyValue);
        }

    }

    public static void setValue(String key, Object value) {
        configValues.put(key, value);
    }

    private static Configuration getConfiguration() {

        final String[] places = new String[]
        {
            "gyorslevel.conf",
            "/etc/gyorslevel/gyorslevel.conf"
        };
        
        Configuration config = null;
        
        for (String place : places) {
            try {
                config = new PropertiesConfiguration(place);
                return config;
            } catch (ConfigurationException exception) {
                logger.info("Config file cannot be found at: " + place);
            }
        }

        logger.error("Configuration file missing!");
        throw new RuntimeException("Configuration file missing!");
        
    }
}
