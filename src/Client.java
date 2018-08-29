import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client{
	static ArrayList<Socket> sockets = new ArrayList();
	public static void main (String args[]) {
		String line;
	      try{
	    	  int cont=1;
	    	  while(cont<4) {
	    		  ClientConnection cconnection = new ClientConnection(cont);
	    		  cont++;
	    	  }
	      }
	      finally {
	    	  try {
				Thread.sleep(800000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  System.out.println("Sockets cerrados");
	      }
	}
}

