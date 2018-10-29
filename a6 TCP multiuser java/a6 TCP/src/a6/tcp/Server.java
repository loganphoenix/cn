/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a6.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(6969);
        Socket s;

        while (true) {
            s = ss.accept();
            new Thread(new Listener(s)).start();
        }

    }

}

class Listener implements Runnable {

    private Socket s;
    private String string;

    Listener(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        System.out.println("Connecting to " + Thread.currentThread().getName());
        Scanner scanner = new Scanner(System.in);

        try {
            DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());

            while (true) {
                string = dataInputStream.readUTF();

                System.out.println(Thread.currentThread().getName() + " says : " + string);

                System.out.println("Reply..");
                string = scanner.nextLine();

                if (string.toLowerCase().equals("stop")) {
                    return;
                }

                dataOutputStream.writeUTF(string);

                dataOutputStream.flush();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
