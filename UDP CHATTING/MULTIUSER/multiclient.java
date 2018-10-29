import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class multiclient
{
	public static void main(String[] args) throws IOException
	{
		int port = 9000;
		
		//to send message to server
		byte buf[] = new byte[1024];
          String str=null;
		DatagramSocket clientsocket = new DatagramSocket();
		BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
		InetAddress ia = InetAddress.getByName("localhost");      //as because it is common for all
		System.out.println("Client is Running... Type 'STOP' to Quit");
		while(true)
		{
			System.out.print("Client:");
			str = dis.readLine();
			buf = str.getBytes();
               DatagramPacket ds=new DatagramPacket(buf,buf.length,ia,port);   //server port is fixed
            
			if(str.equals("STOP"))
			{
				System.out.println("Terminated...");
				clientsocket.send(ds);
				break;
			}
			clientsocket.send(ds);
            
			//to receive from server
			byte buf1[] = new byte[1024];
			String str2=null;
		   DatagramPacket dp=new DatagramPacket(buf1, buf1.length);//no need to provide the ia,port no
			clientsocket.receive(dp);
			str2 = new String(dp.getData(), 0,dp.getLength());
			System.out.println("Server: " + str2);
		}
		clientsocket.close();
	}
}
