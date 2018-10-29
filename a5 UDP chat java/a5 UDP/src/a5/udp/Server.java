
package a5.udp;

import java.net.*;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) {
		Server s = new Server();
		s.connectToClient();
	}
	public void connectToClient() {
		int port;
		String str1="";
		String str2="";
		try {
			DatagramSocket ss = new DatagramSocket(9876);
			
			
			InetAddress ipa = InetAddress.getByName("localhost");
			
			while(true) {
				byte []recieveData = new byte[1024];
				byte []sendData = new byte[1024];
				DatagramPacket dp = new DatagramPacket(recieveData,recieveData.length);
				ss.receive(dp);
				str2= new String(dp.getData());
				if(str2.equalsIgnoreCase("stop"))
					break;
				System.out.println("Client : "+str2);
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter message to send to client");
				str1 = sc.nextLine();
				sendData = str1.getBytes();
				System.out.println("Server : "+str1);
				port = dp.getPort(); // get port no. of client
				DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipa,port);
				ss.send(sendPacket);
				if(str1.equalsIgnoreCase("stop"))
					break;
				str2="";
			}
			ss.close();
			System.out.println("Server Stopped");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
