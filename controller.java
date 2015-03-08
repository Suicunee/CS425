import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.Random;



public class controller {
	public static int controller_port;
	public static int count_node;
	public static HashMap<Integer,Integer> portmap = null;
	public static HashMap<Integer,HashMap<Integer,Integer>> max_delay_map = null;
	
	
	
	public Random i;
	public static HashMap<Integer,HashMap<Integer,Channel>> channelTable;
	private static String configuration_file = "setup.txt";
	
	public static int getRandomR(boolean sendByChannel, int from, int to) {
		Random rand;
		if (sendByChannel == false)
			return 0;
		else
			rand = new Random();
			int randomNumber = rand.nextInt(max_delay_map.get(from).get(to) - 0) + 0;
	        if(randomNumber == 0) {
	            return  1;
	        }
	        else {
	            return randomNumber;
	        }
	}

	
	
	
	
	public static void startListener(int from, int to, Pair m) {
		
		try (
			Socket socket = new Socket("", portmap.get(to));
			//print delay?
		) {
			System.out.println(from+";"+to+";"+m+";");
		} catch (IOException e) {
			System.err.println("Fail to connect");
			}
	}
	
	
	
public static void main(String[] args) {
		
		
		/*
		controller_port=XX
		count_node=XX
		ports_assign=XX;XX...
		max_delay=XX,XX,XX;XX,XX,XX;...
		 */
	
		portmap = new HashMap<Integer, Integer>();
		max_delay_map= new HashMap<Integer,HashMap<Integer,Integer>>();
		try (BufferedReader fd = new BufferedReader(new FileReader(configuration_file))) {
			String line;
			line = fd.readLine();
			while (line != null) {
				String[] split = line.split("=");
				if (split[0].equals("controller_port"))
					controller_port = Integer.parseInt(split[1]);
				if (split[0].equals("count_node"))
					count_node = Integer.parseInt(split[1]);
				if (split[0].equals("port_assign")) {
					String[] subsplit = split[1].split(";");
					for (int i=0; i<subsplit.length; i++) {
						portmap.put(i, Integer.parseInt(subsplit[i]));
					}
				}
				if (split[0].equals("max_delay")) {
					String[] subsplit = split[1].split(";");
					String[] subsubsplit;
					for (int i=0; i<subsplit.length; i++) {
						max_delay_map.put(i, new HashMap<Integer,Integer>());
						subsubsplit = subsplit[i].split(",");
						for (int j=0; i<subsubsplit.length; j++)
							max_delay_map.get(i).put(j, Integer.parseInt(subsubsplit[j]));
						
						
					}
				}
				// add more parameters here
				line = fd.readLine();
			}
		}
		catch (IOException e) {
			System.err.println("file Error");
			System.exit(1);
		}


		// Initialize the channels 
		channelTable = new HashMap<Integer,HashMap<Integer,Channel>>();
		for (int i=0; i<count_node; i++) {
			channelTable.put(i, new HashMap<Integer,Channel>());
			
			for (int j=0; j<count_node; j++) {
				Channel temp;
				if ( i == j) //send to self channel
					temp = new Channel(i,j,false);
				else  // a channel with delay
					temp = new Channel(i,j,true);
				
				channelTable.get(i).put(j, temp);
				
				temp.start();
				
			}
		}
		
		

	}
}
	
	


	


