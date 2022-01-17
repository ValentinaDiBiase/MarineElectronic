package MarineElectronics;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Iterator;




import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;


import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.*;

public class TextFieldSensorSeaCurrent  implements ActionListener{
	
	JTextField tf1,tf2;
	
	JButton seaCurrentIntensityButton;
	
	JFrame appFrameSensorSea​​Current = new JFrame("Sensore Corrente Marina");
	
	JLabel labelVelocity = new JLabel();
	JLabel directionSeaCurrentTitle = new JLabel();
	JLabel speedSeaCurrentTitle = new JLabel();
	JLabel speedWindTitle = new JLabel();
	
	static JLabel directionSeaCurrent = new JLabel();
	static JLabel speedSeaCurrent = new JLabel();
	
	

	Component sensorSpeed = new SinglePart("Sensore velocità");
	Component sensorWind = new SinglePart("Sensore vento");
	
	static double seaCurrentVelocity = 0;

	
	static double windVelocity = 0;
	static double phi = 0;
	static double seaCurrentIntensity = 0;

	
	static String latitude;
	static String longitude;
	

	
	

	
	
public TextFieldSensorSeaCurrent() throws ProtocolException, IOException {
	

    tf1=new JTextField("Longitude:");  
    tf1.setBounds(50,60, 200,30);
    
    

	
	appFrameSensorSea​​Current.add(tf1);
	
	tf2=new JTextField("Latitude:");  
	tf2.setBounds(50,100, 200,30);
	
	appFrameSensorSea​​Current.add(tf2);
	

	
	
	
	
	
		// TODO Auto-generated constructor stub
	directionSeaCurrentTitle.setText("Direction sea current: ");
	directionSeaCurrentTitle.setBounds(50,130,250,20);
	
	speedSeaCurrentTitle.setText("Speed sea current: ");
	speedSeaCurrentTitle.setBounds(50,150,250,20);
	

	directionSeaCurrent.setBounds(190,130,250,20);

	
	speedSeaCurrent.setBounds(170,150,250,20);

	
	
	
	
		

        
        
        seaCurrentIntensityButton = new JButton("calculate intensity of current sea");
        seaCurrentIntensityButton.setBounds(20, 250, 300, 20); 
        seaCurrentIntensityButton.addActionListener(this);
        

        appFrameSensorSea​​Current.add(directionSeaCurrentTitle);
        appFrameSensorSea​​Current.add(speedSeaCurrentTitle);
        appFrameSensorSea​​Current.add(speedWindTitle);
        
        appFrameSensorSea​​Current.add(directionSeaCurrent);
        appFrameSensorSea​​Current.add(speedSeaCurrent);
    
        
        
        appFrameSensorSea​​Current.add(seaCurrentIntensityButton);
        
        appFrameSensorSea​​Current.setSize(400,400);  
        appFrameSensorSea​​Current.setLayout(null);  
        appFrameSensorSea​​Current.setVisible(true); 
        appFrameSensorSea​​Current.setLocation(150, 100);
        

        
        
        

		

		 

		 


		
		
		seaCurrentIntensityButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				longitude = tf1.getText();
				latitude = tf2.getText();	

		   

			

		  
			URL urlcon = null;
			try {
				urlcon = new URL("https://api.stormglass.io/v2/weather/point?lat=" + latitude + "&lng=" + longitude + "&params=currentSpeed,currentDirection");
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			HttpsURLConnection connection = null;
			try {
				connection = (HttpsURLConnection) urlcon.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				connection.setRequestMethod("GET");
			} catch (ProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}


		String apiKey = "8bb7674e-34c6-11ec-9f71-0242ac130002-8bb76816-34c6-11ec-9f71-0242ac130002";

		connection.setRequestProperty("Authorization", apiKey);

	
			try {
				System.out.println(connection.getResponseCode());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
			
			StringBuilder result = new StringBuilder();

			
			
			BufferedReader	rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));


			String line;
			
		
				while ((line = rd.readLine())  != null) {
					
					result.append(line);
					
					System.out.println(line);
					
					JSONParser parser = new JSONParser();
					
					 try{
						 
						 String jsonString = line;
						 
						 System.out.println(jsonString);
						 
						 JSONObject obj = new JSONObject(jsonString);
						 
						 JSONArray arr = (JSONArray) ((Object) obj.get("number"));
						 for (int i = 0; i < ((CharSequence) arr).length(); i++) {
							 
						     System.out.println(arr.get(i));
						 
					 }
						 

					    }catch(ParseException pe) {
							
					       System.out.println("position: " + pe.getPosition());
					       System.out.println(pe);
					    }
					
					
				}
			
				
				rd.close();
				

			
			
			
			  Map<String, String> respMap = jsonToMap(result.toString());
			  

			  

			  Map<String, String> seaCurrentMap = jsonToMap(respMap.get("currentSpeed").toString());
			  Map<String, String> seaCurrentDirectionMap = jsonToMap(respMap.get("currentDirection").toString());
			  

			  
			  directionSeaCurrent.setText(" " + seaCurrentDirectionMap.get("meto"));
			  speedSeaCurrent.setText(" " + seaCurrentMap.get("meto"));
			  

			  
			  
				} catch (IOException e1) {
					
					System.out.println(e1.getMessage());
				}
			}
			
			}); 
		

		try {
			
			writeFile2( "latitude " ,  Float.parseFloat(latitude) );
			
		} catch (IOException e4) {
				// TODO Auto-generated catch block
			e4.printStackTrace();
		}
			

		try {
			
			writeFile2( "longitude " ,  Float.parseFloat(longitude) );
			
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}		

}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		new TextFieldSensorSeaCurrent();
		

	}


	
	
	// Listato 3. Scrittura di un file con BufferWriter
	public static void writeFile2(String name, float value) throws IOException {
	 
		File file = new File("/Users/valentinadibiase/Desktop/MarineElectronics/src/current.txt"); //initialize File object and passing path as argument  
		FileWriter fw = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(fw);
		boolean result;  
		try   
		{  
		result = file.createNewFile();  //creates a new file  

		 

		 bw.write("longitude: " + Float.parseFloat(longitude) + "\n");
		 bw.write("latitude: " +  Float.parseFloat(latitude) + "\n");
		
		 bw.write("sea current velocity: " + (float) Double.parseDouble(speedSeaCurrent.getText()) + "\n");
		 bw.write("sea current direction: " + (float) Double.parseDouble(directionSeaCurrent.getText()) + "\n");

		 
		 
		 
		 bw.flush();
		 bw.close();
		 
		if(result)      // test if successfully created a new file  
		{  
		System.out.println("file created "+ file.getCanonicalPath()); //returns the path string  
		}  
		else  
		{  
		System.out.println("File already exist at location: "+ file.getCanonicalPath());  
		}  
		}   
		catch (IOException e)   
		{  
		e.printStackTrace();    //prints exception if any  
		}

	}

	
	public static Map<String, String> jsonToMap(String str){
		
		Map<String, String> map = new Gson().fromJson(str, new TypeToken<HashMap<String, String>>() {}.getType());
		
		return map;
	}
	
    public static void printMap(Map<String, Object> map) {
        for (String key : map.keySet()) {
            System.out.println("map.get(\"" + key + "\") = " + map.get(key));
        }
        }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}




