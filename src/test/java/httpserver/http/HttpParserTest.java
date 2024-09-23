package httpserver.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class HttpParserTest {

    private HttpParser parser;

    @BeforeEach
    public void beforeClass(){
        parser = new HttpParser();
    }
    private InputStream generatedValidGETTestCase(){
        String rawData = "GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Chromium\";v=\"128\", \"Not;A=Brand\";v=\"24\", \"Google Chrome\";v=\"128\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,no;q=0.6\r\n"+
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));

        return inputStream;
    }
    private InputStream generatedBadTestCaseMethodName(){
        String rawData = "GeT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,no;q=0.6\r\n"+
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));

        return inputStream;
    }
    private InputStream generatedBadTestCaseMethodName2(){
        String rawData = "GeTTTTTTT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,no;q=0.6\r\n"+
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));

        return inputStream;
    }
    private InputStream generatedBadTestCaseMethodName3(){
        String rawData = "GET / AAAAAA HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,no;q=0.6\r\n"+
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));

        return inputStream;
    }
    private InputStream generatedEmptyReqLine(){
        String rawData = "\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,no;q=0.6\r\n"+
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));

        return inputStream;
    }
    private InputStream generatedOnlyCRReturn(){
        String rawData = "GET / HTTP/1.1\r" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Chromium\";v=\"128\", \"Not;A=Brand\";v=\"24\", \"Google Chrome\";v=\"128\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,no;q=0.6\r\n"+
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));

        return inputStream;
    }
    private InputStream generatedBadHttpVersion(){
        String rawData = "GET / HTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Chromium\";v=\"128\", \"Not;A=Brand\";v=\"24\", \"Google Chrome\";v=\"128\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,no;q=0.6\r\n"+
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));

        return inputStream;
    }
    private InputStream generatedUnsupportedHttpVersion(){
        String rawData = "GET / HTTP/2.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Chromium\";v=\"128\", \"Not;A=Brand\";v=\"24\", \"Google Chrome\";v=\"128\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,no;q=0.6\r\n"+
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));

        return inputStream;
    }
    private InputStream generatedHigherHttpVersion(){
        String rawData = "GET / HTTP/1.3\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Chromium\";v=\"128\", \"Not;A=Brand\";v=\"24\", \"Google Chrome\";v=\"128\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,no;q=0.6\r\n"+
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));

        return inputStream;
    }
    @Test
    void parseHttpRequest(){
        HttpRequest request = null;
        try {
            request = parser.parseHttpRequest(generatedValidGETTestCase());
        } catch (HttpParsingException e) {
            fail(e);
        }
        assertEquals(request.getMethod(), HttpMethod.GET);
    }
    @Test
    void parseHttpRequestBadMethod(){
        try {
            HttpRequest request = parser.parseHttpRequest(generatedBadTestCaseMethodName());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED );
        }
    }
    @Test
    void parseHttpBadRequest(){
        try {
            HttpRequest request = parser.parseHttpRequest(generatedBadTestCaseMethodName2());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED );
        }
    }
    @Test
    void parseHttpBadRequestInvalidNumberOfItems(){
        try {
            HttpRequest request = parser.parseHttpRequest(generatedBadTestCaseMethodName3());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }
    @Test
    void parseEmptyRequest(){
        try {
            HttpRequest request = parser.parseHttpRequest(generatedEmptyReqLine());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }
    @Test
    void parseWithoutCR(){
        try {
            HttpRequest request = parser.parseHttpRequest(generatedOnlyCRReturn());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }
    @Test
    void parseWithBadHttpVersion() {
        try {
            HttpRequest request = parser.parseHttpRequest(generatedBadHttpVersion());
        fail();
    }catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
    }
    @Test
    void parseWithUnsupportedHttpVersion() {
        try {
            HttpRequest request = parser.parseHttpRequest(generatedUnsupportedHttpVersion());
            fail();
        }catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_505_VERSION_NOT_SUPPORTED);
        }
    }
    @Test
    void parseWithHigherHttpVersion() {
        try {
            HttpRequest request = parser.parseHttpRequest(generatedHigherHttpVersion());
        assertNotNull(request);
        assertEquals(request.getHttpVersion(), HttpVersion.HTTP_1_1);
        }
        catch (HttpParsingException e) {
            fail();
        }
    }
}