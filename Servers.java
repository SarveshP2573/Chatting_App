import java.net.*;
import java.io.*;
import java.util.*;

public class Servers
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		try{
			ServerSocket ss = new  ServerSocket(5554);		
			Socket s = ss.accept();
			DataOutputStream op = new DataOutputStream(s.getOutputStream());
			DataInputStream input = new DataInputStream(s.getInputStream());
			//Socket socket = new Socket("localhost",5555);
			Thread t1 = new Thread(()->{
				try{
					String str="";
					while(!str.equalsIgnoreCase("bye")){
						str = input.readUTF();
						System.out.println("Client: "+str);
					}
					System.out.println("Client Disconnected...");
				}catch(IOException e){
					e.printStackTrace();
				};
			});
			Thread t2 = new Thread(()->{
				try{
					String msg="";
					while(!msg.equalsIgnoreCase("bye")){
						System.out.println("You: ");
						msg = sc.nextLine();
						op.writeUTF(msg);
					}
					ss.close();
					s.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			});
			t1.start();
			t2.start();
			//socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}