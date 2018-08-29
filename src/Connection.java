import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread {

	//Flujo de datos hacia los consumidores
	DataInputStream cin;
	DataOutputStream cout;
	//Flujo de datos hacia el publisher
	DataInputStream pin;
	DataOutputStream pout;
	
	//Sockets
	Socket publisherSocket;
    Socket c1Socket;
    Socket c2Socket;
    Socket c3Socket;
	
   public Connection (Socket publisherSocket){
      try {
		  ServerSocket listenSocketc1 = new ServerSocket(5984);
		  ServerSocket listenSocketc2 = new ServerSocket(5985);
		  ServerSocket listenSocketc3 = new ServerSocket(5986);
		  c1Socket = listenSocketc1.accept();
		  c2Socket = listenSocketc2.accept();
		  c3Socket = listenSocketc3.accept();
		  
		  this.publisherSocket = publisherSocket;
		  //	  Se crean flujos de entrada y salida para el editor
		  pin = new DataInputStream(publisherSocket.getInputStream());
		  pout =new DataOutputStream(publisherSocket.getOutputStream());
		  this.start();
	} catch(IOException e){
	      System.out.println("Connection:"+e.getMessage());
	}
   }
   public void run() {
	      try {	                                      // an echo server
	    	  while(true) {
//	    		  El servidor lee lo que envía publisher
	    		  String data = pin.readUTF();   
	    		  System.out.println("Received from publisher: "+data);
//	    		  Se hace un flujo de datos hacia el publisher, no funciona sin eso
	    		  pout =new DataOutputStream(publisherSocket.getOutputStream());
	    		  pout.writeUTF("pang");
//	    		  Se crea el socket hacia c1
	    		  cout =new DataOutputStream(c1Socket.getOutputStream());
//	    		  El servidor escribe mediante el flujo a c1
	    		  cout.writeUTF(data);
//	    		  Se crea el socket hacia c2
	    		  cout =new DataOutputStream(c2Socket.getOutputStream());
//	    		  El servidor escribe mediante el flujo a c2
	    		  cout.writeUTF(data);
//	    		  Se crea el socket hacia c3
	    		  cout =new DataOutputStream(c3Socket.getOutputStream());
//	    		  El servidor escribe mediante el flujo a c3
	    		  cout.writeUTF(data);
	    		  System.out.println("Mensajes enviados");
	    	  }
	      } catch (EOFException e){
		     System.out.println("EOF:"+e.getMessage());
	      } catch(IOException e){
		     System.out.println("readline:"+e.getMessage());
	      } finally{
	      try {
	    	  c1Socket.close(); 
	    	  c2Socket.close(); 
	    	  c3Socket.close(); 
	      }catch (IOException e){/*close failed*/}}
	   } // end 
}
