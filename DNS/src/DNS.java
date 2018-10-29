import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Scanner;
public class DNS {
	Scanner sc = null;
	public DNS() {
		sc = new Scanner(System.in);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DNS d = new DNS();
		int choice;
		do {
			System.out.println("Enter 1. IP Address 2. URL 0. Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 0:
				System.exit(0);
				break;
			case 1:
				d.getURL();	
				break;
			case 2:
				d.getIP();
				break;
			default:
				System.out.println("Enter proper choice");
				break;
			}
		} while (true);
	}
	public void getURL() {
		String ip;
		System.out.println("Enter  IP address");
		ip = sc.nextLine();
		try {
			InetAddress inet = InetAddress.getByName(ip);
			System.out.println(inet.getHostName());
		} catch (Exception e) {
			System.out.println("URL Not Found");
			e.printStackTrace();
		}
	}
	public void getIP() {
		String url;
		System.out.println("Enter URL ");
		url =sc.nextLine();
		try {
			InetAddress inet = InetAddress.getByName(url);
			System.out.println(inet.getHostAddress());
		} catch (Exception e) {
			System.out.println("IP Not Found");
			e.printStackTrace();
		}
	}
}
