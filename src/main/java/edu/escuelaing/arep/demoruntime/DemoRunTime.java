package edu.escuelaing.arep.demoruntime;

import java.io.IOException;

import static edu.escuelaing.arep.picosparkweb.PicoSpark.*;

public class DemoRunTime {
    public static void  main(String[] args) throws IOException {
        port(getPort());
        get("/hello","Hello word!");
        startServer();
    }

    private static int getPort() {
      if(System.getenv("PORT")!=null){
          return Integer.parseInt(System.getenv("PORT"));
      }
      return 3478;
    }
}
