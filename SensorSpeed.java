package MarineElectronics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;





public class SensorSpeed extends Frame implements ActionListener{
	
	Component sensorSpeed = new SinglePart("Sensore velocità");
	
	
	
	JFrame appFrameSensorSpeedLog = new JFrame();
	JFrame appFrameSensorSpeed = new JFrame();
	
	JFrame frameSpeed = new JFrame();
	

	 JTextField tf1, tf2, tf3, tf4, tf5, tf6;
	
	JButton distance;
	JButton velocity;
	
	public static double statuteMiles;
	public static double speedLog;
	public static double speed;
	public static double lat1;
	public static double lon1;
	public static double lat2;
	public static double lon2;
	public static double time;
	
    JLabel speedLogLabel = new JLabel();
	JLabel speedLabel = new JLabel();
	
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");

	
	Font font = new Font("alarm clock", Font.PLAIN, 100);
	



	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	//	System.out.println(sensorSpeed.accelerometer());

		new  SensorSpeed() ;

	}
	
	public  SensorSpeed() {
		// TODO Auto-generated constructor stub
		
		
		frameSpeed.setBounds(50, 50, 350, 240);
		frameSpeed.setLayout(null);  
		frameSpeed.setVisible(true);
		frameSpeed.setLocation(1200, 310);
		
		
	    tf1=new JTextField("Longitude");
	    tf1.setBounds(20,20,100,30); 
	    
	    tf2=new JTextField("Latitude");
	    tf2.setBounds(150,20,100,30);
	    
	    tf5=new JTextField("Longitude");
	    tf5.setBounds(20,60,100,30); 
	    
	    tf6=new JTextField("Latitude");
	    tf6.setBounds(150,60,100,30);
	    
	    tf3=new JTextField("Distance");
	    tf3.setBounds(20,100,100,30);
	    
	    tf4=new JTextField("Time");
	    tf4.setBounds(150,100,100,30);
	    
	    

	    speedLogLabel.setLocation(90, 340);
	    
	    speedLogLabel.setFont(font);
	    
	    
	   // speedLogLabel.setText("ciao");
	  //  speedLabel.setText("ciao");
	    speedLabel.setLocation(20, 20);
	    speedLabel.setFont(font);
	   
	    
	    
	    
	    

        distance = new JButton("Calculate distance");
        distance.setBounds(20, 140, 200, 20);
        distance.addActionListener(this);
        
        velocity = new JButton("Calculate velocity");
        velocity.setBounds(20, 180, 200, 20);
        velocity.addActionListener(this);
	    
	    
	    frameSpeed.add(tf1);
	    frameSpeed.add(tf2);
	    frameSpeed.add(tf3);
	    frameSpeed.add(tf4);
	    frameSpeed.add(tf5);
	    frameSpeed.add(tf6);
	    
	    frameSpeed.add(distance);
	    frameSpeed.add(velocity);
	    
	    

	    
	    appFrameSensorSpeedLog.add(speedLogLabel);
	    appFrameSensorSpeed.add(speedLabel);
	    
	    appFrameSensorSpeedLog.setSize(500, 200);
	    appFrameSensorSpeedLog.setVisible(true);
	    appFrameSensorSpeedLog.setTitle("SensorSpeedLog");
	    appFrameSensorSpeedLog.setLocation(1100, 600);
	    appFrameSensorSpeedLog.setResizable(false);
	    
	    appFrameSensorSpeed.setSize(500, 200);
	    appFrameSensorSpeed.setVisible(true);
	    appFrameSensorSpeed.setTitle("SensorSpeed");
	    appFrameSensorSpeed.setLocation(1100, 810);
	    appFrameSensorSpeed.setResizable(false);
	  
	    
	    

	    //add allow listener
		distance.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
	        	
	        	double latitudeOne = Double.parseDouble(tf1.getText());
	        	double longitudeOne =  Double.parseDouble(tf2.getText());
	        	
	        	double latitudeTwo = Double.parseDouble(tf5.getText());
	        	double longitudeTwo =  Double.parseDouble(tf6.getText());
	        	

	        	  lat1 = Math.toRadians(latitudeOne);
	            lon1 = Math.toRadians(longitudeOne);
	             lat2 = Math.toRadians(latitudeTwo);
	            lon2 = Math.toRadians(longitudeTwo);

	          // great circle distance in radians, using law of cosines formula
	            double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
	                                  + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

	          // each degree on a great circle of Earth is 60 nautical miles
	            double nauticalMiles = 60 * Math.toDegrees(angle);
	            statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
	         
	            tf3.setText(String.valueOf(statuteMiles));
	            
	            try {
					writeFile2("latitude one: ", (float) lat1);
					writeFile2("latitude two: ", (float) lat2);
					writeFile2("longitude one: ", (float) lon1);
					writeFile2("longitude two: ", (float) lon2);
					writeFile2("statute miles: ", (float) statuteMiles );
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		
	        }

	    });
		
		
		velocity.addActionListener(new ActionListener() {
			
			 @Override
		        public void actionPerformed(ActionEvent e) {
				 
				 time = Double.parseDouble(tf4.getText());
				 
					speedLog = sensorSpeed.speedBoatLog(statuteMiles,time );

				 
				 speed = sensorSpeed.speedBoatDv(5.4, speedLog);
				 
				 
				 speedLogLabel.setText(df2.format(speedLog ));
				 
				 speedLabel.setText(df2.format(speed));
				 

					
					try {
						
						writeFile2("time", (float) time);
						writeFile2("speed log", (float) speedLog);
						writeFile2("speed ", (float) speed);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					System.out.println(speedLog);
					System.out.println( speed);

				 
			 }
			
		});
		
		

		

		
		
		
	
		
		
	}
	
	
	
	

	
	
	// Listato 3. Scrittura di un file con BufferWriter
	public static void writeFile2(String name, float value) throws IOException {
	 
		File file = new File("/Users/valentinadibiase/Desktop/MarineElectronics/src/speed.txt"); //initialize File object and passing path as argument  
		FileWriter fw = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(fw);
		boolean result;  
		try   
		{  
		result = file.createNewFile();  //creates a new file  

		 
		 bw.write("speed log: " + speedLog +  "\n");
		 bw.write("speed:" + speed +  "\n");
		 bw.write("latitude one: " + (float) lat1 + "\n");
		 bw.write("latitude two: " + (float) lat2 + "\n" );
		 bw.write("longitude one: " + (float) lon1 + "\n");
		 bw.write("longitude two: " + (float) lon2 + "\n");
		 bw.write("statute miles: " + (float) statuteMiles + "\n" );
		 bw.write("time" +  (float) time);
		 bw.flush();
		 bw.close();
		 
		if(result)      // test if successfully created a new file  
		{  
		System.out.println("file created " + file.getCanonicalPath()); //returns the path string  
		}  
		else  
		{  
		System.out.println("File already exist at location: " + file.getCanonicalPath());  
		}  
		}   
		catch (IOException e)   
		{  
		e.printStackTrace();    //prints exception if any  
		}
	

	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



}







