package org.server;

import httpserver.config.Configuration;
import httpserver.config.ConfigurationManager;


public class Main {
    public static void main(String[] args) {
            System.out.println("Server is running");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println(conf.getWebroot() + ": is webroot");
        System.out.println(conf.getPort() + ": is port");
    }
}