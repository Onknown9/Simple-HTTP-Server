package org.server;


import httpserver.config.Configuration;
import httpserver.config.ConfigurationManager;
import httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;



public class Main {

    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);
        public static void main(String[] args) {

        LOGGER.info("Server is running");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using port: {}", conf.getPort());
        LOGGER.info("Using webroot: {}", conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}