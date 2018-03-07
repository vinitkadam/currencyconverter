import java.net.*;  
import java.io.*;  
class MyClient{  
	public static void main(String args[])throws Exception{  
		Socket s=new Socket("localhost",3333);  
		DataInputStream din=new DataInputStream(s.getInputStream());  
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

		String base="",amount="",str="";
		str=din.readUTF();
		System.out.println("Server says: "+str); 
		
		while(!base.equals("stop")){  
			System.out.println("Enter base currency: ");
			base=br.readLine();  
			dout.writeUTF(base);
			System.out.println("Enter amount: ");
			amount=br.readLine();
			dout.writeUTF(amount);
			String inr = din.readUTF();
			System.out.println("VALUE IN INR="+inr);
			dout.flush();
			// str=din.readUTF();  
			// System.out.println("Server says: "+str);  
		}  
		  
		dout.close();  
		s.close();  
	}
}