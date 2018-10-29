/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a6.tcp.multiclient;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientOne {

    public static void main(String []args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("localhost", 6969);

        String string;

        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                System.out.println("Say something client : ");
                string = scanner.nextLine();

                if (string.equalsIgnoreCase("stop")) {
                    break;
                }

                dataOutputStream.writeUTF(string);

                string = dataInputStream.readUTF();
                System.out.println(string);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        socket.close();

    }


}