import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class multiserver
{
	public static void main(String[] args) throws IOException
	{
	   int port=9000;
	   
		//to receive from client
		String str=null;
		byte buf[] = new byte[1024];
		DatagramSocket serversocket = new DatagramSocket(port);
		DatagramPacket dp = new DatagramPacket(buf,buf.length);
		BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Server is Running...");
		while(true)
		{
			serversocket.receive(dp);
			str = new String(dp.getData(), 0,dp.getLength());
			//if(str.equals("STOP"))
			//{
			//	System.out.println("Terminated...");
			//	break;
			//}
			System.out.println("Client: " + str);

			//to send to client
			byte buf1[] = new byte[1024];
			String str1=null;
			if(!str.equals("STOP"))
			{
			 InetAddress ia = dp.getAddress();    //get the address of received packet
			 int sport=dp.getPort();                     //get the port no of received packet
			 System.out.print("Server:");
			 str1 = new String(dis.readLine());
			 buf1 = str1.getBytes();
			 DatagramPacket ds=new DatagramPacket(buf1,buf1.length, ia,sport);
			 serversocket.send(ds);
			}
		}
		//serversocket.close();
	}
}
