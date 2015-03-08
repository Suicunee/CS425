import java.io.*;
import java.net.*;
import java.util.*;

public class CentralServer {
	
	public static int portNumber = 4444; 	
	protected Socket socket;
	public Thread t;	
	
	public static void main(String[] args) {
	    int i=0;

	    try{
	      ServerSocket listener = new ServerSocket(portNumber);
	      Socket server;

	      while(true){
	        doComms connection;
	    	server = listener.accept();
	        doComms conn_c= new doComms(server);
	        Thread t = new Thread(conn_c);
	        t.start();
	      }
	    } catch (IOException ioe) {
	      System.out.println("IOException on socket listen: " + ioe);
	      ioe.printStackTrace();
	    }
	  }
	
	

}
