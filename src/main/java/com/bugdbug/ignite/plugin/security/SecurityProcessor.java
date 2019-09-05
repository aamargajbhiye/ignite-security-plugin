package com.bugdbug.ignite.plugin.security;

import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.internal.GridKernalContext;
import org.apache.ignite.internal.processors.GridProcessorAdapter;
import org.apache.ignite.internal.processors.security.GridSecurityProcessor;
import org.apache.ignite.internal.processors.security.SecurityContext;
import org.apache.ignite.plugin.PluginConfiguration;
import org.apache.ignite.plugin.security.SecurityException;
import org.apache.ignite.plugin.security.*;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

/**
 * This is implementation of {@link GridSecurityProcessor} which  handles cluster security
 */
public class SecurityProcessor extends GridProcessorAdapter implements GridSecurityProcessor {

    private IgniteConfiguration config;
    private SecurityPluginConfiguration securityPluginConfiguration;

    /**
     * @param ctx Kernal context.
     */
    protected SecurityProcessor(GridKernalContext ctx) {
        super(ctx);
        config = ctx.config();
        PluginConfiguration[] pluginConfigurations = this.config.getPluginConfigurations();
        if (pluginConfigurations != null) {
            for (PluginConfiguration pluginConfiguration : pluginConfigurations) {
                if (pluginConfiguration instanceof SecurityPluginConfiguration) {
                    securityPluginConfiguration = (SecurityPluginConfiguration) pluginConfiguration;
                }
            }
        }
    }

    /**
     * @param node
     * @param cred
     * @return
     * @throws IgniteCheckedException
     */
    public SecurityContext authenticateNode(ClusterNode node, SecurityCredentials cred) throws IgniteCheckedException {

        SecurityCredentials userSecurityCredentials;

        if (securityPluginConfiguration != null) {
            if ((userSecurityCredentials = securityPluginConfiguration.getSecurityCredentials()) != null) {
                return userSecurityCredentials.equals(cred) ? new SecurityContextImpl() : null;
            }
            if (cred == null && userSecurityCredentials == null) {
                return new SecurityContextImpl();
            }
        }

        if (cred == null)
            return new SecurityContextImpl();

        return null;

    }

    public boolean isGlobalNodeAuthentication() {
        return false;
    }

    public SecurityContext authenticate(AuthenticationContext ctx) throws IgniteCheckedException {

        return new SecurityContextImpl(ctx);
    }

    public Collection<SecuritySubject> authenticatedSubjects() throws IgniteCheckedException {

        return Arrays.asList(new SecuritySubjectImpl());
    }

    public SecuritySubject authenticatedSubject(UUID subjId) throws IgniteCheckedException {

        return new SecuritySubjectImpl();
    }

    public void authorize(String name, SecurityPermission perm, @Nullable SecurityContext securityCtx) throws SecurityException {
        //TODO - Here we can check permissions for given cache operation
    }

    public void onSessionExpired(UUID subjId) {

    }

    public boolean enabled() {
        return true;
    }

}
