package com.bugdbug.ignite.plugin.security;

import org.apache.ignite.plugin.PluginConfiguration;
import org.apache.ignite.plugin.security.SecurityCredentials;

/**
 * This is implementation of {@link PluginConfiguration} for {@link SecurityPlugin}
 */
public class SecurityPluginConfiguration implements PluginConfiguration {

    private SecurityCredentials securityCredentials;

    public SecurityPluginConfiguration(SecurityCredentials securityCredentials) {

        this.securityCredentials = securityCredentials;
    }

    public SecurityCredentials getSecurityCredentials() {
        return securityCredentials;
    }
}
