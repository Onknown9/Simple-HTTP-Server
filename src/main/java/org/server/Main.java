package org.server;

import httpserver.config.Configuration;
import httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    public static void main(String[] args) {
            System.out.println("Server is running");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println(conf.getWebroot() + ": is webroot");
        System.out.println(conf.getPort() + ": is port");

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //TODO we write
            String html = "<html>" +
                    "<head>" +
                        "<title>Simple Java HTTP Server</title>" +
                    "</head>" +
                    "<body>" +
                        "<h1>Page is server using simple Java HTTP Server</h1>" +
                    "</body>" +
                    "</html>";

            final String CRLF = "\n\r"; //13 and 10

            String response =
                    "HTTP/1.1 200 OK" + CRLF + "Content-Length: " + html.getBytes().length + CRLF + CRLF + html + CRLF + CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}