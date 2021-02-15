package edu.escuelaing.arep.httpserver;

import edu.escuelaing.arep.picosparkweb.PicoSparkServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {
    int port;
    Map<String, PicoSparkServer> routesToprocessors=new HashMap();
    public void startServer(int httpServer)throws IOException {
        port=httpServer;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+getPort());
            System.exit(1);
        }
        boolean running=true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir en puerto"+getPort()+"...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            outputLine=validOkResponse();
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public void setPort(int port) {
        this.port=port;
    }
    public int getPort(){
        return port;
    }

    public void registerProcessor(String path, PicoSparkServer picoSparkServer) {
        routesToprocessors.put(path,picoSparkServer);
    }
    public String validOkResponse(){
        return "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<meta charset=\"UTF-8\">\n"
                        + "<title>Title of the document</title>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<h1>Mi propio mensaje</h1>\n"
                        + "</body>\n"
                        + "</html>\n";
    }
}
