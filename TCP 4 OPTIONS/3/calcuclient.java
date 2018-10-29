package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class calcuclient {
	public static void main(String[] args) {
		try {
			Socket client=new Socket("127.0.0.1",4000);
			System.out.println("Client Connected...");
			Scanner s=new Scanner(System.in);
			DataInputStream dis=new DataInputStream(client.getInputStream());
			DataOutputStream dos=new DataOutputStream(client.getOutputStream());
			int choice=0;
			int operand1=0,operand2=0;

			System.out.println("############Calculator##############");
			System.out.print("Enter operand 1:");
		    operand1=s.nextInt();
		    System.out.print("Enter operand 2:");
		    operand2=s.nextInt();
			System.out.println("1.Addition");
			System.out.println("2.Subtraction");
			System.out.println("3.Multiplication");
			System.out.println("4.Division");
			System.out.println("5.Modulo");
			System.out.print("Enter your Choice:");
			choice=s.nextInt();
			switch(choice)
			{
			case 1:dos.writeInt(choice);
			       dos.flush();
			       break;
			case 2:dos.writeInt(choice);
		           dos.flush();
		           break;
			case 3:dos.writeInt(choice);
		           dos.flush();
		           break;
			case 4:dos.writeInt(choice);
		           dos.flush();
		           break;
			case 5:dos.writeInt(choice);
		           dos.flush();
		           break;
			default:System.out.println("Wrong Choice Entered");
			}
			dos.writeInt(operand1);
		    dos.flush();
		    dos.writeInt(operand2);
		    dos.flush();
			int Result=dis.readInt();
			System.out.println("Result:"+Result);
			dis.close();
			dos.close();
			s.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
