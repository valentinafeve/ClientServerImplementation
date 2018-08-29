import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	   public static void main (String args[]) {
		   try{
//			   Port used by the publisher
			   int serverPort = 5982;
			   ServerSocket listenSocketp = new ServerSocket(serverPort);
			   
			   System.out.println("Recibiendo");
			   //El servidor se mantiene escuchando al publisher
			   while(true) {
				   //Se hacen dos sockets, uno hacia el publisher y otro hacia los consumidores
			      Socket publisherSocket = listenSocketp.accept();
			      Connection c = new Connection(publisherSocket);
			   }
			} catch(IOException e) {
			      System.out.println("Listen socket:"+e.getMessage());
			}
	   }
}
