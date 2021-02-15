package edu.escuelaing.arep.picosparkweb;

import edu.escuelaing.arep.httpserver.HttpServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PicoSparkServer implements Processor{
    private int serverPort=36000;
    Map<String,String> functions=new HashMap();
    HttpServer hserver=new HttpServer();
    private static PicoSparkServer _instance=new PicoSparkServer();

    private PicoSparkServer(){
        hserver.registerProcessor("/Apps",this);
    }
    public static PicoSparkServer getInstance() {
        return _instance;
    }

    public void get(String route,String body){
        functions.put(route,body);
    }

    public void startServer(){
        try {
            hserver.startServer(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void port(int serverPort) {
        this.serverPort=serverPort;
    }

    @Override
    public String handle(String path) {
        if(functions.containsKey(path)){
            return validOkHttpHeader()+functions.get(path);
        }
        return validErrorHttpHeader()+"Error";
    }

    private String validErrorHttpHeader() {
        return "HTTP/1.1 404 Not Found\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Error</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>I found and error</h1>\n"
                + "</body>\n"
                + "</html>\n";
    }

    private String validOkHttpHeader() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";
    }

}
