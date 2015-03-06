import java.io.*;
import java.net.*;

public class Server{
	
	public int portNumber;
	protected Socket socket;
	public int maxDelay;
	public int numNode;
	public Thread delay, server, client;
	
	public ServerSocket ss;
	
	public Server(int portNumber, int maxDelay, int numNode){
		this.portNumber = portNumber;
		this.maxDelay  = maxDelay;
		this.numNode = numNode;
		
	}
	
	public void run(){
		try{
			ss = new ServerSocket(portNumber);
			while(true){
				socket = ss.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				
				//new Handler(socket);
			}
		} catch(IOException e){
			if (ss != null && !ss.isClosed()){
				try{
					ss.close();
				} catch(IOException e1){
					e1.printStackTrace(System.err);
				}
			}
		}
		 
	
	}
}
