package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class calcuserver {
	public static void main(String[] args) {
       try {
		ServerSocket server=new ServerSocket(4000);
		Socket client=server.accept();
		System.out.println("Server Connected...");
		DataInputStream dis=new DataInputStream(client.getInputStream());
		DataOutputStream dos=new DataOutputStream(client.getOutputStream());
		int operand1=0,operand2=0,operation=0,result=0;
		operation=dis.readInt();
		operand1=dis.readInt();
		operand2=dis.readInt();
		switch(operation)
		{
		 case 1:result=operand1+operand2;
		        break;
		 case 2:result=operand1-operand2;
	            break;
		 case 3:result=operand1*operand2;
	            break;
		 case 4:result=operand1/operand2;
	            break;
		 case 5:result=operand1%operand2;
	            break;
		}
		dos.writeInt(result);
        dos.flush();
        dis.close();
		dos.close();
		client.close();
		server.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
