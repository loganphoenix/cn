import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class udp_client
{
	public static void main(String[] args) throws IOException
	{
		int cport = 789, sport = 790;
		byte buf[] = new byte[1024];

		DatagramSocket clientsocket = new DatagramSocket(cport);
		BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
		InetAddress ia = InetAddress.getLocalHost();
		System.out.println("Client is Running... Type 'STOP' to Quit");
		while(true)
		{
			System.out.print("Client:");
			String str = new String(dis.readLine());
			buf = str.getBytes();
            DatagramPacket ds=new DatagramPacket(buf,buf.length,ia,sport);
            
			if(str.equals("STOP"))
			{
				System.out.println("Terminated...");
				clientsocket.send(ds);
				break;
			}
			clientsocket.send(ds);
            
			byte buf1[] = new byte[1024];
			DatagramPacket dp=new DatagramPacket(buf1, buf1.length);   //no need to provide the ia,port no
			clientsocket.receive(dp);
			String str2 = new String(dp.getData(), 0,dp.getLength());
			System.out.println("Server: " + str2);
		}
	}
}