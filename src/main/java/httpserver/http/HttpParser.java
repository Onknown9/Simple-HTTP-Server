package httpserver.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    private static final int SP = 0x20;
    private static final int CR = 0x0D;
    private static final int LF = 0x0A;


    public HttpRequest parseHttpRequest(InputStream inputStream) throws HttpParsingException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();

        try {
            parseRequestLine(reader, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseReaders(reader, request);
        parseBody(reader, request);

        return request;
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException, HttpParsingException {
        StringBuilder proccesingDataBuffer = new StringBuilder();

        boolean methodParsed = false;
        boolean requestTargetParsed = false;

        int _byte;
        while((_byte = reader.read()) >=0){
            if(_byte==CR){
                _byte=reader.read();
                if(_byte==LF){
                    LOGGER.debug("Request line VERSION to proccess: {}", proccesingDataBuffer.toString());
                    if (!methodParsed || !requestTargetParsed) {
                        throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                    }
                    return;
                }
            }
            if(_byte==SP){
                if(!methodParsed){
                    LOGGER.debug("Request line METHOD to proccess: {}", proccesingDataBuffer.toString());
                    request.setMethod(proccesingDataBuffer.toString());
                    methodParsed=true;
                }else if(!requestTargetParsed){
                    LOGGER.debug("Request line REQ TARGET to proccess: {}", proccesingDataBuffer.toString());
                    requestTargetParsed=true;
                } else {
                    throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
                proccesingDataBuffer.delete(0, proccesingDataBuffer.length());
            }else{
               proccesingDataBuffer.append((char)_byte);
               if (!methodParsed){
                   if (proccesingDataBuffer.length() > HttpMethod.MAX_LENGTH) {
                       throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED);
                   }
               }
            }
        }
    }

    private void parseReaders(InputStreamReader reader, HttpRequest request) {}

    private void parseBody(InputStreamReader reader, HttpRequest request) {}

}
