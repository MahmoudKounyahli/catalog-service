package com.storehouse.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "storehouse")
public class StorehouseProperties {
    /**
     * A message to welcome user.
     */
    private String greeting;

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }




}
