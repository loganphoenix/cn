package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class trignoclient {
	public static void main(String[] args) {
		try {
			Socket client=new Socket("127.0.0.1",5000);
			System.out.println("Client Connected...");
			Scanner s=new Scanner(System.in);
			DataInputStream dis=new DataInputStream(client.getInputStream());
			DataOutputStream dos=new DataOutputStream(client.getOutputStream());
			float angle_indegrees=0;
			int choose=0;
			System.out.print("Enter the angle (in degrees):");
			angle_indegrees=s.nextFloat();
			System.out.println("#####Angles#####");
			System.out.println("1.sin");
			System.out.println("2.cos");
			System.out.println("3.tan");
			System.out.print("Choos
			e:");
			choose=s.nextInt();
			switch(choose)
			{
			case 1:dos.writeInt(choose);
			       dos.flush();
			       break;
			case 2:dos.writeInt(choose);
		           dos.flush();
		           break;
			case 3:dos.writeInt(choose);
		           dos.flush();
		           break;
			default:System.out.println("Wrong choice entered");
			}
			dos.writeFloat(angle_indegrees);
			dos.flush();
			
			float result;
			result=dis.readFloat();
			System.out.println("Result:"+result);
			client.close();
			dis.close();
			dos.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
