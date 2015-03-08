import java.io.*;
import java.net.*;
import java.util.*;

public class D_message {
	
	

		private int deliveryTime;//accept time
		private int delay;
		private Pair message;  // the raw message that contains fromNode, toNode, and messageContent
		private int from;
		private int to;
		
		Queue<String> myQueue = new LinkedList<String>();

		public D_message(int deli, int d, Pair m, int f, int t) {
			deliveryTime = deli;
			delay = d;
			message = m;
			from = f;
			to = t;
		}

		public String getMessage() {
			return message;
		}

		public void try_to_delay() {
			try {
				from.getThread_send.sleep(delay);
			} catch (InterruptedException e) {
				System.err.println("Sleeping is Interrupted. Controller failure.");
				System.exit(1);
			}
		}
	
}
