import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection extends Thread {

	int id;
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
    
   public ClientConnection (int id){
      try {
    	  //Cada cliente crea su propio socket al mismo puerto
    	  this.id=id;
    	  this.clientSocket = new Socket("127.0.0.1",5983+id);
    	  //Cada cliente crea su propio flujo de datos
		  in = new DataInputStream(clientSocket.getInputStream());
		  out =new DataOutputStream(clientSocket.getOutputStream());
		  this.start();
	} catch(IOException e){
	      System.out.println("Connection:"+e.getMessage());
	}
   }
   public void run() {
	      try {	                                      
	    	  while(true) {
	    		  System.out.println("Client c"+id+" is waiting for a message...");
	    		  in = new DataInputStream(clientSocket.getInputStream());
	    		  String data = in.readUTF();   // read a line of data from the stream
	    		  System.out.println("Client c"+id+" has received from publisher: "+data);
	    		  out.writeUTF("pang");
	    	  }
	      } catch (EOFException e){
		     System.out.println("EOF:"+e.getMessage());
	      } catch(IOException e){
		     System.out.println("readline:"+e.getMessage());
	      } finally{
	      try {
		        clientSocket.close(); 
	      }catch (IOException e){/*close failed*/}}
	   } // end 
}