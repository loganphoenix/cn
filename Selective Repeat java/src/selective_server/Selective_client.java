/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selective_server;
import java.io.*;
import java.net.*;

/**
 *
 * @author chira
 */
public class Selective_client {
public static void main(String[] args)throws IOException
	{
		Socket s=new Socket("localhost",3000);
		DataInputStream dis=new DataInputStream(s.getInputStream());
		DataOutputStream dos=new DataOutputStream(s.getOutputStream());
		int p=dis.read();
		int arr[]=new int[p];
		int i;
		for(i=0;i<p;i++)
			arr[i]=dis.read();
		arr[2]=-1;
		for(i=0;i<p;i++)
		{
			if(arr[i]==-1)
			{
				System.out.println("Requesting for frame no. " + (i+1) + " to be sent again");
				dos.write(i);
				dos.flush(); 
			}
		}
		arr[2]=dis.read();
		System.out.println("Complete frame:");
		for(i=0;i<p;i++)
			System.out.print(arr[i]+"\t");
		dos.close();
		dis.close();
		s.close();
	}    
}
