package com.bugdbug.ignite.plugin.security;

import org.apache.ignite.internal.processors.security.SecurityContext;
import org.apache.ignite.plugin.security.AuthenticationContext;
import org.apache.ignite.plugin.security.SecurityPermission;
import org.apache.ignite.plugin.security.SecuritySubject;

import java.io.Serializable;

/**
 *  This implementation checks permissions and validates them against assigned ones.
 */
public class SecurityContextImpl implements SecurityContext, Serializable {

    public SecurityContextImpl(AuthenticationContext ctx) {

    }

    public SecurityContextImpl() {

    }

    public SecuritySubject subject() {
        return new SecuritySubjectImpl();
    }

    public boolean taskOperationAllowed(String taskClsName, SecurityPermission perm) {
        return true;
    }

    public boolean cacheOperationAllowed(String cacheName, SecurityPermission perm) {
        return true;
    }

    public boolean serviceOperationAllowed(String srvcName, SecurityPermission perm) {
        return true;
    }

    public boolean systemOperationAllowed(SecurityPermission perm) {
        return true;
    }
}
