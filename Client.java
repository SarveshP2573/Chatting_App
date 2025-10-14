import java.net.*;
import java.io.*;
import java.util.*;

public class Client{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		try{
			Socket s = new Socket("localhost",5554);
			DataOutputStream output = new DataOutputStream(s.getOutputStream());
			DataInputStream input = new DataInputStream(s.getInputStream());
			System.out.println("Connected..");
			Thread t1 = new Thread(()->{
				try{
					String str="";
					while(!str.equalsIgnoreCase("bye")){
						str = input.readUTF();
						System.out.println("Server: "+str);
					}
					System.out.println("Server disconnected...");
				}catch(IOException e){
					e.printStackTrace();
				}
			});
			Thread t2 = new Thread(()->{
				try{
					String msg="";
					while(!msg.equalsIgnoreCase("bye")){
						System.out.println("You: ");
						msg = sc.nextLine();
						output.writeUTF(msg);
					}
					s.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			});
			t1.start();
			t2.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
