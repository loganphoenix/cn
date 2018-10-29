package tcpftp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class client {
	public static void main(String[] args) throws IOException{
		Socket client =new Socket("localhost",50000);
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		String source_file_path=null;
		System.out.print("Enter the Source File path:");
		source_file_path=bf.readLine();
		DataOutputStream dos=new DataOutputStream(client.getOutputStream());
		dos.writeUTF(source_file_path);
		dos.flush();
		System.out.println("File Requested Successfully...");
		
		DataInputStream dis=new DataInputStream(client.getInputStream());
		int flag=dis.readInt();
		if(flag==1)
		{
		System.out.println("File Transfer Process Started....");
		String destination_path=null;
		System.out.print("Enter the destination path:");
		destination_path=bf.readLine();
		dos.writeUTF(destination_path);
		dos.flush();
		
		String Result=dis.readUTF();
        System.out.println(Result);
		}
		else if(flag==-1)
			System.out.println("Error in Requested Source File Path");
		
		client.close();
		bf.close();
		dis.close();
		dos.close();
	}
}
