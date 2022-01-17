package MarineElectronics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javax.swing.*;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class DrawPolygons extends JPanel implements ActionListener {
	
	
	Image img = Toolkit.getDefaultToolkit().getImage("/Users/valentinadibiase/eclipse-workspace/MarineElectronics/src/imageLancetta.png");
	 
	public static double angoloLancetta = Math.toRadians(0);
	
	static Component sensorWind = new SinglePart("Sensor wind");
	static Component sensorSpeed = new SinglePart("Sensor speed");
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
     
	static JFrame appFrameSensorWind = new JFrame("SensorWind");
	
	JFrame frameLocation = new JFrame("Location direction");
	JButton searchWind = new JButton();
	JTextField location = new JTextField();

    public DrawPolygons() {

    	
   

    	frameLocation.setBounds(20, 20, 200, 90);
    	frameLocation.setLocation(400, 900);
    	frameLocation.setVisible(true);
    	
    	searchWind.setBounds(120, 20, 50, 20);
    	searchWind.setText("enter");
    	frameLocation.add(searchWind);
    	
    	location.setBounds(20, 20, 100, 20);
    	frameLocation.add(location);
    	
        Timer timer = new Timer(1000,this);
        timer.start();
        
        
        
        searchWind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int count = 0;
		    	
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
					
					//System.out.println(result);
					
				
				  Map<String, Object> respMap = jsonToMap(result.toString());
				  Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
					
					
					System.out.println("Wind speed: " + windMap.get("speed"));
					System.out.println("Wind Angle " + windMap.get("deg"));
					
					sensorWind.trueWindSpeed = (double) windMap.get("speed");
					sensorWind.directionWind = (double) windMap.get("deg");
					
			    	count ++;
			    	

			    	angoloLancetta = Math.toRadians( sensorWind.directionWind); 
			    	
			        repaint();
					
				//	labelTrueWind.setText(" " + df2.format(sensorWind.trueWindSpeed));
				//	labelApparentWind.setText(" " + df2.format(sensorWind.apparentWindSpeed(sensorWind.trueWindSpeed, sensorSpeed.speedBoat)));
			
					writeFile2("wind deg ", (float) sensorWind.directionWind);
					writeFile2("true wind ", (float) sensorWind.trueWindSpeed );
					
				} catch (IOException e1) {
					
					System.out.println(e1.getMessage());
				}
				
			}
		});
    	
    	

		
		

		

		

   //    Timer timer = new Timer(1000,this);
   //     timer.start();
        

       

    }

  // int count = 0;
    
   // public void actionPerformed(ActionEvent ae) {
    	// ... che ridisegna il JPanel ogni secondo
   // 	count ++;
    	

  //  	angoloLancetta = Math.toRadians( sensorWind.directionWind); 
    	
   //     repaint();
        
        
  //  }
    
    
	
    public void updateAngoloLancetta(double angoloLancetta_180) {
    	
    	//angoloLancetta è espresso in gradi da 0 a 360 mentre angoloLancetta_180 va da -180 a +180
    	this.angoloLancetta = Math.toRadians(TransformAngle.from_180_to_360_angle(angoloLancetta_180));
    	
    	
    	
    	 repaint();
    }
	
	@Override
	public void paint(Graphics graphics) {
		      super.paintComponent(graphics);
		      
		      Graphics2D g2d = (Graphics2D)graphics;
		      
		      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			   
		        int size = Math.min(getWidth(),getHeight());
		        int center = size/2;
		      

		      //  Toolkit t=Toolkit.getDefaultToolkit();

		      graphics.drawImage(img, center - 135, center - 135, null);
		      

		        paintLancetta(graphics);
		        
		   //   graphics.drawString(String.valueOf(sensorWind.trueWindSpeed),150, 100);
		      
		      
		      
		   }
		   
		   
	    private void paintLancetta(Graphics graphics) {
	    	
	    	Graphics2D g2d = (Graphics2D)graphics;
		      
		      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



		        int size = Math.min(getWidth(),getHeight());
		        int center = size/2;

		        g2d.rotate(angoloLancetta, center,center);
		        
		     
		        
		        g2d.setColor(Color.DARK_GRAY);
		        
		        int[] x1 = { center,center+center/16,center,center-center/16 };
		        int[] y1 = { center+center/10,center,center/3,center };
		        g2d.fill(new Polygon(x1,y1,x1.length));
	    	
	    	
	    }
	    
	    
		// Listato 3. Scrittura di un file con BufferWriter
		public static void writeFile2(String name, float value) throws IOException {
		 
			File file = new File("/Users/valentinadibiase/Desktop/MarineElectronics/src/wind.txt"); //initialize File object and passing path as argument  
			FileWriter fw = new FileWriter(file);
		    BufferedWriter bw = new BufferedWriter(fw);
			boolean result;  
			try   
			{  
			result = file.createNewFile();  //creates a new file  

			//bw.write(" " + name + ": " + String.valueOf(value) + "\n");
			// bw.write(String.valueOf());
			 bw.write("wind deg: " + (float) sensorWind.directionWind + "\n");
			 bw.write("wind speed: " + (float) sensorWind.trueWindSpeed);
			// bw.write(String.valueOf(sensorWind.trueWindSpeed);
			// bw.write(String.valueOf(angoloLancetta));
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
		
		
	public static Map<String, Object> jsonToMap(String str){
		
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
		
		return map;
	}
	   
	public static void main(String[] a) throws IOException {
		
		new DrawPolygons();

		 

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}




}












// new DrawPolygons();

//Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
// 	double screenwidth = d.getWidth();
//	double screenheight = d.getHeight();
	
// 	int width  = 400;
//	int height = 400;

	
//   new DrawPolygons();

//  windArrow.setPreferredSize(new Dimension(width,height));


//   appFrameSensorWind.getContentPane().add(windArrow);


//  appFrameSensorWind.setLocation
//  ( (int)(screenwidth/2-width/2), 
//    (int)(screenheight/2-height/2) 
//  );

//  appFrameSensorWind.pack();
//  appFrameSensorWind.setVisible(true);
//  appFrameSensorWind.setResizable(false);
//  appFrameSensorWind.setLocation(600, 500);


// istanzia un oggetto timer...
//	sensorSpeed.speedBoat = 22;
//	sensorWind.apparentWindSpeed = 20;
//sensorWind.trueWindSpeed = windMap.get("speed");

//	sensorWind.directionWind = sensorWind.trueWindDirection(sensorWind.apparentWindSpeed, sensorWind.trueWindSpeed, sensorSpeed.speedBoat);

//	System.out.println(sensorWind.directionWind);

//  labelTrueWind.setText(" " + df2.format(sensorWind.trueWindSpeed));
//   labelTrueWind.setText(" " + df2.format(sensorWind.trueWindSpeed(sensorWind.apparentWindSpeed, sensorSpeed.speedBoat)));


//  panel.setBounds(350,350,100,20); 
//  panel.setBackground(Color.gray); 
// panel.setBounds(40,80,200,200);    
// panel.setBackground(Color.gray); 



//	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
// 	double screenwidth = d.getWidth();
//	double screenheight = d.getHeight();
 	
 //	int width  = 400;
//	int height = 400;
	
	//JFrame appFrameSensorWind = new JFrame("SensorWind");
//writeFile2();

//	new DrawPolygons();

//	String API_KEY = "88499badd0b72c6ebd912745b1365293";

//	String LOCATION = "Naples";
	
//	String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";
	
	
	//try {
		
	//	StringBuilder result = new StringBuilder();
	//	URL url = new URL(urlString);
	//	URLConnection conn = url.openConnection();
	//	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	//	String line;
		
	//	while ((line = rd.readLine())  != null) {
			
	//		result.append(line);
	//	}
		
	//	rd.close();
		
	//	System.out.println(result);
		
		
    //      Map<String, Object > respMap = jsonToMap (result.toString());
          // don't need to convert from string to map again and again
     //     Map<String, Object > mainMap = (Map<String, Object >)respMap.get("main");
     //     Map<String, Object > windMap = (Map<String, Object >)respMap.get("wind");
		
	
	//  Map<String, Object> respMap = jsonToMap(result.toString());
	//  Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
		
		
	//	System.out.println("Wind speed: " + windMap.get("speed"));
	//	System.out.println("Wind Angle " + windMap.get("deg"));
		
		

		
//	} catch (IOException e) {
		
//		System.out.println(e.getMessage());
//	}


//	System.out.println(result);


//      Map<String, Object > respMap = jsonToMap (result.toString());
      // don't need to convert from string to map again and again
 //     Map<String, Object > mainMap = (Map<String, Object >)respMap.get("main");
 //     Map<String, Object > windMap = (Map<String, Object >)respMap.get("wind");













//	public static void main(String[] a)
//   {
  
        // creating object of JFrame(Window popup)
		
		
		
   //     JFrame window = new JFrame();
  
        // setting closing operation
    //    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
        // setting size of the pop window
    //    window.setBounds(30, 30, 200, 200);
  
        // setting canvas for draw
     //   window.getContentPane().add(new DrawPolygons());
  
        // set visibility
      //  window.setVisible(true);
        
        
    //    EventQueue.invokeLater(
      //          new Runnable() {
              
       //           public void run() {    
                          	
        //          	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //          	double screenwidth = d.getWidth();
        //          	double screenheight = d.getHeight();
                  	
        //          	int width  = 400;
         //         	int height = 400;
                  	
           //           JFrame frame = new JFrame("Orologio analogico");
            //          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //          DrawPolygons windArrow = new DrawPolygons();
                      
            //          windArrow .setPreferredSize(new Dimension(width,height));
                      
             //         frame.getContentPane().add(windArrow );
                      
             //         frame.setLocation
             //           ( (int)(screenwidth/2-width/2), 
              //            (int)(screenheight/2-height/2) 
              //          );
                      
              //        frame.pack();
              //        frame.setVisible(true);
                      
             //     } // run
                  
           //   } //  Runnable
          //  ); //  EventQueue.invokeLater(
        
        
        
 //   }

	
//   	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//   	double screenwidth = d.getWidth();
//  	double screenheight = d.getHeight();
    	
 //  	int width  = 400;
  // 	int height = 400;
   	
   	
//	sensorSpeed.speedBoat = 3.4;
	
//    Font f2 = new Font("Helvetica", Font.BOLD, 14);

//     JLabel labelTrueWind = new JLabel();
 //    JLabel labelApparentWind = new JLabel();
 //    JButton trueWind = new JButton("True Wind"); 
 //    JButton apparentWind = new JButton("Apparent Wind"); 
 //	 Panel panel = new Panel();
 	 
 	 

   
	 
//   labelTrueWind.setLocation(170, 250);
//   labelTrueWind.setSize(50, 40);
  

   
//   labelTrueWind.setFont(f2);
//   labelTrueWind.setVisible(false);   	
  
//    labelApparentWind.setLocation(170, 250);
	//   labelApparentWind.setSize(50, 40);
	  
	   
   
//    labelApparentWind.setFont(f2);
//	   labelApparentWind.setVisible(false);
	   
// 	trueWind.setBounds(80,350,90,30); 
// 	apparentWind.setBounds(200,350,150,30); 
	   
	 

//	appFrameSensorWind.setSize(400,400);  
//	appFrameSensorWind.setLayout(null);  
//	appFrameSensorWind.setVisible(true); 
//	appFrameSensorWind.setLocation(600, 500);

// 	appFrameSensorWind.add(panel);

//    panel.add(trueWind);

//    panel.add(apparentWind);
    
  // 	appFrameSensorWind.add(trueWind);
  //  appFrameSensorWind.add(apparentWind);
    
  //  appFrameSensorWind.add(labelTrueWind);
 //   appFrameSensorWind.add(labelApparentWind);
    
 //   appFrameSensorWind.add(panel, BorderLayout.SOUTH);
    
	//	    trueWind.addActionListener(e -> {
	//	        if (!labelTrueWind.isVisible()) {
	//	        	labelApparentWind.setVisible(false);
	//	        	labelTrueWind.setVisible(true);
		        	
	//	        }
	//	    });
		    
       //	    apparentWind.addActionListener(e -> {
     //   	  if (!labelApparentWind.isVisible()) {
     //   		   labelTrueWind.setVisible(false);
    //    		    labelApparentWind.setVisible(true);
        		        	
    //    		     }
   //         });
