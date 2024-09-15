package httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {
    private Socket socket;
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

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

            LOGGER.info("Connection finished");

        }catch(IOException e) {
            LOGGER.error(e.getMessage());
        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException _) {}
                try {
                    outputStream.close();
                } catch (IOException _) {}
                try {
                    socket.close();
                } catch (IOException _) {}
            }
        }
    }
}
