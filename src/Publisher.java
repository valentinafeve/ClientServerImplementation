import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Publisher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket socket;
			String publication;
			socket = new Socket("127.0.0.1",5982);
//			Se crean los canales de difusión
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out;
//			El sistema inicia cuando el publicador escribe
			Scanner scanner = new Scanner(System.in);
			publication=scanner.nextLine();
//			Se leen por consola publicaciones. 
			while(publication!="exit") {
//				Se envía la cadena UTF por el flujo de salida hacia el servidor
				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(publication);
				publication=scanner.nextLine();
			}
			socket.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
