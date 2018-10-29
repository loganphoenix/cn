import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class udp_server
{
	public static void main(String[] args) throws IOException
	{
		int cport = 789,sport=790;
		byte buf[] = new byte[1024];
		DatagramSocket serversocket = new DatagramSocket(sport);
		DatagramPacket dp = new DatagramPacket(buf,buf.length);
		BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Server is Running...");
		while(true)
		{
			serversocket.receive(dp);
			String str = new String(dp.getData(), 0,dp.getLength());
			if(str.equals("STOP"))
			{
				System.out.println("Terminated...");
				break;
			}
			System.out.println("Client: " + str);

			byte buf1[] = new byte[1024];
			InetAddress ia = InetAddress.getLocalHost();
			System.out.print("Server:");
			String str1 = new String(dis.readLine());
			buf1 = str1.getBytes();
			DatagramPacket ds=new DatagramPacket(buf1,buf1.length, ia,cport);
			serversocket.send(ds);
		}
	}
}