package com.bugdbug.ignite.plugin.security;

import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.internal.IgniteKernal;
import org.apache.ignite.internal.processors.security.GridSecurityProcessor;
import org.apache.ignite.plugin.*;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.UUID;


/**
 * This is implementation of {@link PluginProvider} which creates instance of {@link SecurityPlugin}
 */
public class SecurityPluginProvider implements PluginProvider<SecurityPluginConfiguration> {

    private IgnitePlugin authenticationPlugin;

    public String name() {
        return "Authentication Plugin";
    }

    public String version() {
        return "1.0.1";
    }

    public String copyright() {
        return null;
    }

    public <T extends IgnitePlugin> T plugin() {
        return (T) authenticationPlugin;
    }

    public void initExtensions(PluginContext ctx, ExtensionRegistry registry) throws IgniteCheckedException {
        authenticationPlugin = new SecurityPlugin();
    }

    @Nullable
    public GridSecurityProcessor createComponent(PluginContext pluginContext, Class cls) {
        if (cls.equals(GridSecurityProcessor.class)) {
            return new SecurityProcessor(((IgniteKernal) pluginContext.grid()).context());
        }
        return null;
    }

    public CachePluginProvider createCacheProvider(CachePluginContext ctx) {
        return null;
    }

    public void start(PluginContext ctx) throws IgniteCheckedException {

    }

    public void stop(boolean cancel) throws IgniteCheckedException {

    }

    public void onIgniteStart() throws IgniteCheckedException {

    }

    public void onIgniteStop(boolean cancel) {

    }

    @Nullable
    public Serializable provideDiscoveryData(UUID nodeId) {
        return null;
    }

    public void receiveDiscoveryData(UUID nodeId, Serializable data) {

    }

    public void validateNewNode(ClusterNode node) throws PluginValidationException {

    }
}
