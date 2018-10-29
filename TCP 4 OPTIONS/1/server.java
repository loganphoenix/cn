package tcpftp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	public static void main(String[] args) throws IOException {
		ServerSocket server;
		server = new ServerSocket(50000);
		Socket client=server.accept();
		try {
			DataInputStream dis=new DataInputStream(client.getInputStream());
			String source_file_path=dis.readUTF();
			System.out.println(source_file_path);
			String filename=source_file_path.substring(source_file_path.lastIndexOf("/")+1,source_file_path.length());
			File f=new File(source_file_path);
			FileInputStream fis=new FileInputStream(source_file_path);
			byte data[]=new byte[(int)f.length()];
			if(f.exists() && f.isFile())
			{
				fis.read(data,0,data.length);
			}
			int flag=1;
			DataOutputStream dos=new DataOutputStream(client.getOutputStream());
			dos.writeInt(flag);
			dos.flush();

			String destination_path=dis.readUTF();
			String destination_file_path=destination_path+filename;
			System.out.println(destination_file_path);
			File ff=new File(destination_path);
			if(!(ff.isDirectory()))
			{
				ff.mkdirs();
			}
			FileOutputStream fos=new FileOutputStream(destination_file_path);
			fos.write(data, 0, data.length);
			fos.flush();
			dos.writeUTF("Transfer Process Successfull.....");
			dos.flush();

			server.close();
			fis.close();
			fos.close();
		} catch (IOException e ) {
			try {
				DataOutputStream dos=new DataOutputStream(client.getOutputStream());
				int flag=-1;
				dos.writeInt(flag);
				dos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
