package com.bugdbug.ignite.plugin.security;

import org.apache.ignite.plugin.security.SecurityPermissionSet;
import org.apache.ignite.plugin.security.SecuritySubject;
import org.apache.ignite.plugin.security.SecuritySubjectType;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * This implementation defines subject information with permissions
 */
public class SecuritySubjectImpl implements SecuritySubject {
    public UUID id() {
        return null;
    }

    public SecuritySubjectType type() {
        return null;
    }

    public Object login() {
        return null;
    }

    public InetSocketAddress address() {
        return null;
    }

    public SecurityPermissionSet permissions() {
        return new SecurityPermissionsSetImpl();
    }
}
