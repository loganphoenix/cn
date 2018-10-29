package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class trignoserver {
	public static void main(String[] args) {
      try {
		ServerSocket server=new ServerSocket(5000);
		Socket client=server.accept();
		System.out.println("Server Connected...");
		DataInputStream dis=new DataInputStream(client.getInputStream());
		DataOutputStream dos=new DataOutputStream(client.getOutputStream());
		int op;
		op=dis.readInt();
		float angle,result=0;
		angle=dis.readFloat();
		switch(op)
		{
		case 1:result=(float) Math.sin(angle);
		       break;
		case 2:result=(float)Math.cos(angle);
		       break;
		case 3:result=(float)Math.tan(angle);
		       break;
		}
		dos.writeFloat(result);
		dos.flush();
		client.close();
		dis.close();
		dos.close();
		server.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
