package edu.escuelaing.arep.picosparkweb;

import java.io.IOException;

public class PicoSpark {
    public static void get(String ruta,String body){
        PicoSparkServer pserver=PicoSparkServer.getInstance();
        pserver.get(ruta,body);
    }
    public static void port(int port){
        PicoSparkServer pserver=PicoSparkServer.getInstance();
        pserver.port(port);
    }

    public static void startServer() throws IOException {
        PicoSparkServer pserver=PicoSparkServer.getInstance();
        pserver.startServer();
    }

}
