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
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextFieldSensorPosition implements ActionListener{
	
	
	JTextField tf1;
	JButton  coordinates;
	JLabel longitude = new JLabel();
	JLabel latitude = new JLabel();
	
	JLabel longitudeTitle = new JLabel();
	JLabel latitudeTitle = new JLabel();
	JLabel directionBoatTitle = new JLabel();
	JLabel directionBoat = new JLabel();
	JLabel directionBoatCompass = new JLabel();
	
	Component sensorPosition = new SinglePart("Sensore velocità");
	
	

	JFrame appFrameSensorPosition = new JFrame("SensorPosition");
	
	public static String longitudeAddressOne;
	public static String latitudeAddressOne;
	
	public static double directionBoatDegree;
	public static String directionBoatCompassDegree;
	
	

	public TextFieldSensorPosition() {
		
		
		// TODO Auto-generated constructor stub
	    tf1=new JTextField("insert a place");
	    tf1.setBounds(50,50,150,20); 



        
        coordinates = new JButton("Calculate longitude and latitude");
        coordinates.setBounds(25, 180, 300, 20);
        coordinates.addActionListener(this);
        
    
        longitude.setBounds(130, 50, 150, 100); 
        latitude.setBounds(130, 50, 150, 150); 
       
        longitudeTitle.setText("Longitude: ");
        longitudeTitle.setBounds(50, 50, 100, 100); 
       
        latitudeTitle.setText("Latitude: ");
        latitudeTitle.setBounds(50, 50, 100, 150); 
        
        directionBoatTitle.setText("Direction boat: ");
        directionBoatTitle.setBounds(50, 50, 100, 200); 
        
        
        directionBoat.setBounds(150, 50, 150, 200); 
        
        directionBoatCompass.setBounds(300, 50, 150, 200);
        appFrameSensorPosition.add(tf1);


        appFrameSensorPosition.add(longitude);
        appFrameSensorPosition.add(latitude);
        
        appFrameSensorPosition.add(longitudeTitle);
        appFrameSensorPosition.add(latitudeTitle);
        
        appFrameSensorPosition.add(directionBoatTitle);
        appFrameSensorPosition.add(directionBoat);
        appFrameSensorPosition.add(directionBoatCompass);

        appFrameSensorPosition.add(coordinates);
        
        appFrameSensorPosition.setSize(350,250);  
        appFrameSensorPosition.setLayout(null);  
        appFrameSensorPosition.setVisible(true); 
        appFrameSensorPosition.setLocation(150, 540);
        
        
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

        
    	String s1Address = tf1.getText();


        
        if(e.getSource() == coordinates) {
        	

        	try {
        		
				longitudeAddressOne = findPlaceLongitude(s1Address);
				
				longitude.setText(longitudeAddressOne);
				

				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	

			try {
				
				latitudeAddressOne = findPlaceLatitude(s1Address);
				
				latitude.setText(latitudeAddressOne);
				 
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}


        }
        
       directionBoatDegree = sensorPosition.directionBoat(latitudeAddressOne,longitudeAddressOne);
        directionBoat.setText(Double.toString(directionBoatDegree));
        

        
        directionBoatCompassDegree = sensorPosition.directionBoatCompass(directionBoatDegree);
        directionBoatCompass.setText(directionBoatCompassDegree );
        
        
        
   
        
        try {
			writeFile2();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
      
        
        
        


	}
	

        
    	public static void main(String[] args) throws IOException {
    		
    		// TODO Auto-generated method stub
    	new TextFieldSensorPosition();
    	
    	
    		
    		
        
    		}  


 	   private static String findPlaceLongitude( String address) throws IOException {
	        // INSERT YOU URL HERE
	        String urlStr = "https://script.google.com/macros/s/AKfycbzSlaM4Lk2IYiGfBbwKK6ibgLLlQ34XLX7uTgKwhX-0rixGtWG4ouXE3W6_0_QIIO17TA/exec" +
	                "?q=" + URLEncoder.encode(address, "UTF-8");
	  
	        URL url = new URL(urlStr);
	        StringBuilder response = new StringBuilder();
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        return response.toString();
	    }
	   
	   
	   private static String findPlaceLatitude( String address) throws IOException {
	        // INSERT YOU URL HERE
	        String urlStr = "https://script.google.com/macros/s/AKfycbzCiU3O5eFSRVGMVNVE8CaTd4yHrqIZxZnkSUXSePkNCtiFEfD2QYAyNMddv-Yv1v1cMw/exec" +
	                "?q=" + URLEncoder.encode(address, "UTF-8");
	  
	        URL url = new URL(urlStr);
	        StringBuilder response = new StringBuilder();
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        return response.toString();
	    }
	   
	   
		// Listato 3. Scrittura di un file con BufferWriter
		public static void writeFile2() throws IOException {
		 
			File file = new File("/Users/valentinadibiase/Desktop/MarineElectronics/src/position.txt"); //initialize File object and passing path as argument  
			FileWriter fw = new FileWriter(file);
		    BufferedWriter bw = new BufferedWriter(fw);
			boolean result;  
			try   
			{  
			result = file.createNewFile();  //creates a new file  

			 
			 bw.write("Longitude" + longitudeAddressOne + "\n");
			 bw.write("Latitude" + latitudeAddressOne + "\n");
			 bw.write("Direction:" + directionBoatDegree + " " + directionBoatCompassDegree );
			 
			 
			 

			 
			 bw.flush();
			 bw.close();
			 
			if(result)      // test if successfully created a new file  
			{  
			System.out.println("file created "+file.getCanonicalPath()); //returns the path string  
			}  
			else  
			{  
			System.out.println("File already exist at location: "+file.getCanonicalPath());  
			}  
			}   
			catch (IOException e)   
			{  
			e.printStackTrace();    //prints exception if any  
			}

		}


}








