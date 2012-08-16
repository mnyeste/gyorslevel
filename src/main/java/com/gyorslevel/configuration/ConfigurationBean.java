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

        Domain("gyorslevel.domain", String.class),
        TimeOut("expiration.timeout", Long.class),
        ContextFolder("context.folder", String.class),
        JamesHost("james.host", String.class);
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

        try {

            Configuration config = new PropertiesConfiguration("/etc/gyorslevel/gyorslevel.cfg");

            switch (key) {
                case ContextFolder:
                case JamesHost:
                case Domain:
                    return type.cast(config.getString(key.getKey()));
                case TimeOut:
                    return type.cast(config.getLong(key.getKey()));
                default:
                    throw new MissingConfigurationEntry(key.getKey());
            }

        } catch (ConfigurationException exception) {

            logger.error(exception);
            throw new RuntimeException(exception);

        }

    }
    
    public static void setValue(String key, Object value)
    {
        configValues.put(key, value);
    }
    
}
