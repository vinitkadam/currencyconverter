import com.google.gson.*;

import java.net.*;
import java.io.*;  

class MyServer{  
	public static void main(String args[])throws Exception{  
		ServerSocket ss=new ServerSocket(3333);  
		Socket s=ss.accept();  
		DataInputStream din=new DataInputStream(s.getInputStream());  
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
		
		dout.writeUTF("Welcome\nYou can select on of the following currencies as base currency\nAUD\nBGN\nBLR\nCAD\nCHF\nCNY\nCZK\nDKK\nEUR\nGBP\nHKD\nHRK\nHUF\nIDR\nILS\nINR\nISK\nJPY\nKRW\nMXN\nMYR\nNOK\nNZD\nPHP\nPLN\nRON\nRUB\nSEK\nSGD\nTHB\nTRY\nZAR");  
		String base="",amount="";  
		while(!base.equals("stop")){  
			base=din.readUTF();
			amount=din.readUTF();
			System.out.println("Base="+base);
			System.out.println("Amount="+amount);  
			// str2=br.readLine();
			// dout.writeUTF(str2);  
			// dout.flush();
			
			String sURL = "https://api.fixer.io/latest?base="+base;
			URL url = new URL(sURL);
		    HttpURLConnection request = (HttpURLConnection) url.openConnection();
		    request.connect();

		    // Convert to a JSON object to print data
		    JsonParser jp = new JsonParser(); //from gson
		    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
		    JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
		    JsonObject rates = rootobj.get("rates").getAsJsonObject();
		    String inr = rates.get("INR").getAsString();
		    System.out.println("INR Value="+inr);
		    System.out.println("sending result");
		    float result = Float.parseFloat(inr.trim()) * Float.parseFloat(amount.trim());
		    System.out.println(result);
		    dout.writeUTF(Float.toString(result));
		    dout.flush();
		}  
		din.close();  
		s.close();  
		ss.close();  
	}
}

// String sURL = "http://freegeoip.net/json/"; //just a string

//     // Connect to the URL using java's native library
//     URL url = new URL(sURL);
//     HttpURLConnection request = (HttpURLConnection) url.openConnection();
//     request.connect();

//     // Convert to a JSON object to print data
//     JsonParser jp = new JsonParser(); //from gson
//     JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
//     JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
//     zipcode = rootobj.get("zip_code").getAsString();