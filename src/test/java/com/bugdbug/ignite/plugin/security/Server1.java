package com.bugdbug.ignite.plugin.security;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.plugin.security.SecurityCredentials;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Server1 {

    public static void main(String[] args) {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        SecurityCredentials securityCredentials = new SecurityCredentials("amar", "amar");
        SecurityPluginConfiguration securityPluginConfiguration = new SecurityPluginConfiguration(securityCredentials);
        igniteConfiguration.setPluginConfigurations(securityPluginConfiguration);
        Ignite ignite = Ignition.start(igniteConfiguration);

        final IgniteCache<Object, Object> cache = ignite.getOrCreateCache("temp-cache");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int i = new Random(System.currentTimeMillis()).nextInt();
                cache.put("key" + i, "val" + i);
            }
        }, 0, 1000);
    }
}
