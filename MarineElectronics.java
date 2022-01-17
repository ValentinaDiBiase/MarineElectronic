package MarineElectronics;


import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.Font;

import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class MarineElectronics implements ActionListener{
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		Component sensorSpeed = new SinglePart("Sensore velocità");
		Component sensorWind = new SinglePart("Sensore vento");

      
	        EventQueue.invokeLater(
	                new Runnable() {
	              
	                  public void run() {  

	                	  
	                	   	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	                	   	double screenwidth = d.getWidth();
	                	  	double screenheight = d.getHeight();
	                	    	
	                	   	int width  = 400;
	                	   	int height = 400;
	                	  
	                	 JFrame appFrameSensorWind = new JFrame("SensorWind");

	                      DrawPolygons windArrow = new DrawPolygons();
	                      
	                     windArrow.setPreferredSize(new Dimension(width,height));
	                     
	                     

	                     
	          		   Font f2 = new Font("Helvetica", Font.BOLD, 35);

	          		   JLabel labelTrueWind = new JLabel();

	          		   labelTrueWind.setBounds(160, 230, 90, 100);
	          		 
	          		 
	          		   labelTrueWind.setFont(f2);
	          		   labelTrueWind.setVisible(false);
	          		   
	          				   JLabel labelApparentWind = new JLabel();
	          				   labelApparentWind.setBounds(160, 230, 90, 100);
	          				   
	          				   labelApparentWind.setFont(f2);
	          				   labelApparentWind.setVisible(false);
	          				   
	          				   
	          						   appFrameSensorWind.add(labelApparentWind);
	          						   appFrameSensorWind.add(labelTrueWind);
	          						   
	          						   
	          							JFrame frameLocation = new JFrame("Location wind");
	          							JButton searchWind = new JButton();
	          							JTextField location = new JTextField();
	          						   
	          							
	          					    	frameLocation.setBounds(20, 20, 200, 90);
	          					    	frameLocation.setLocation(400, 800);
	          					    	frameLocation.setVisible(true);
	          					    	
	          					    	searchWind.setBounds(120, 20, 50, 20);
	          					    	searchWind.setText("enter");
	          					    	frameLocation.add(searchWind);
	          					    	
	          					    	location.setBounds(20, 20, 100, 20);
	          					    	frameLocation.add(location);
	          					    	
	          					    	 searchWind.addActionListener(new ActionListener() {
	          								
	          								@Override
	          								public void actionPerformed(ActionEvent e) {
	          						   
	          	                     String API_KEY = "88499badd0b72c6ebd912745b1365293";
	         	             		
	          	   			     	String LOCATION = "";
	          					
	          					    LOCATION = location.getText();
	         	             		
	         	             		String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";
	         	             		
	         	             		
	         	             		try {
	         	             			
	         	             			StringBuilder result = new StringBuilder();
	         	             			URL url = new URL(urlString);
	         	             			URLConnection conn = url.openConnection();
	         	             			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         	             			String line;
	         	             			
	         	             			while ((line = rd.readLine())  != null) {
	         	             				
	         	             				result.append(line);
	         	             			}
	         	             			
	         	             			rd.close();
	         	             			
	         	             		//	System.out.println(result);
	         	             			sensorSpeed.speedBoat = 3.4;
	         	             		
	         	             		  Map<String, Object> respMap = jsonToMap(result.toString());
	         	             		  Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
	         	             			
	         	             			
	         	             			System.out.println("Wind speed: " + windMap.get("speed"));
	         	             			System.out.println("Wind Angle " + windMap.get("deg"));
	         	             			
	         	             			sensorWind.trueWindSpeed = (double) windMap.get("speed");
	         	             			sensorWind.directionWind = (double) windMap.get("deg");
	         	             			
	         	             			labelTrueWind.setText(" " + df2.format(sensorWind.trueWindSpeed));
	         	             			labelApparentWind.setText(" " + df2.format(sensorWind.trueWindSpeed + sensorSpeed.speedBoat ));
	         	             	
	         	             			
	         	             		} catch (IOException e1) {
	         	             			
	         	             			System.out.println(e1.getMessage());
	         	             		}
	                     
	          								}
	          							});
	                   JPanel panel = new JPanel();
	             	    
	             	    
	                 	   JButton trueWind = new JButton("True Wind");  
	                 		 
	                 		    
	                 		    panel.add(trueWind);
	                 		    
	                 		    
	                 		    trueWind.addActionListener(e -> {
	                 		        if (!labelTrueWind.isVisible()) {
	                 		        	labelApparentWind.setVisible(false);
	                 		        	labelTrueWind.setVisible(true);
	                 		        	
	                 		        }
	                 		    });
	                 		    
	                 		    
	                 		    JButton apparentWind = new JButton("Apparent Wind");  
	                 		
	                 		    
	                 		    panel.add(apparentWind);
	                 		    
	                 			

	                 		    apparentWind.addActionListener(e -> {
	                 		        if (!labelApparentWind.isVisible()) {
	                 		        	labelTrueWind.setVisible(false);
	                 		        	labelApparentWind.setVisible(true);
	                 		        	
	                 		        }
	                 		    });
	                 		 
	                 	    appFrameSensorWind.add(panel, BorderLayout.SOUTH);
	                      
	                    appFrameSensorWind.getContentPane().add(windArrow);
	                      
	                      
	                        appFrameSensorWind.setLocation
	                          ( (int)(screenwidth/2-width/2), 
	                           (int)(screenheight/2-height/2) 
	                           );
	                         
	                         appFrameSensorWind.pack();
	                         appFrameSensorWind.setVisible(true);
	                     	 appFrameSensorWind.setResizable(false);
	                         appFrameSensorWind.setLocation(600, 500);
	  
	            		  
	            		  TextFieldSensorPosition sensorPosition = new TextFieldSensorPosition();
	            		  


	            			SensorSpeed speed = new SensorSpeed();


	            			
						try {
							StatusFrameBoat statusBoat = new StatusFrameBoat();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						  try {
							TextFieldSensorSeaCurrent sensorSeaCurrent = new TextFieldSensorSeaCurrent();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	            			
	                  } // run
	                  
	              } //  Runnable
	            );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

		
	}
	
	public static Map<String, Object> jsonToMap(String str){
		
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
		
		return map;
	}

}





















