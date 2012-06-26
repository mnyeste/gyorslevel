package com.gyorslevel.configuration;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Retrieves configuration values from key-value files.
 *
 * @author dave00
 */
public class ConfigurationBean {

    public enum ConfigurationBeanKey {

        Domain("jmx.domain", String.class), TimeOut("expiration.timeout", Long.class);
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

    public static <T> T getValue(ConfigurationBeanKey key, Class<T> type) {

        if (key == null) {
            throw new IllegalArgumentException();
        }

        try {

            Configuration config = new PropertiesConfiguration("/etc/gyorslevel/gyorslevel.cfg");

            switch (key) {
                case Domain:
                    return type.cast(config.getString(key.getKey()));
                case TimeOut:
                    return type.cast(config.getLong(key.getKey()));
                default:
                    throw new MissingConfigurationEntry(key.getKey());
            }

        } catch (ConfigurationException exception) {

            System.err.println(exception);
            throw new RuntimeException(exception);

        }

    }
}
