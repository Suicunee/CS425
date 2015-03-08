import java.io.*;
import java.net.*;
import java.util.*;

public class doComms implements Runnable{
	
	private Socket centralServer;
	private String line, input;
	
	public doComms(Socket centralServer){
		this.centralServer = centralServer;
	}
	
	public D_message centralMessage;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		input = "";
		try{
			DataInputStream in = new DataInputStream(centralServer.getInputStream());
			PrintStream out = new PrintStream(centralServer.getOutputStream());
			
			while((line = in.readLine()) != null){
				centralMessage.myQueue.add(line);
				input = line;
				out.println(line);
				
			}
			System.out.println(input);
			out.println(input);
			
		} catch(IOException ioe){
			System.out.println(ioe);
			ioe.printStackTrace();
		}
		
		
	}

}
