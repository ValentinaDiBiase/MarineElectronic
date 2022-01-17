package MarineElectronics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.api.SendSmsApi;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsResponse;
import com.infobip.model.SmsTextualMessage;




public class StatusFrameBoat implements ActionListener{
	
	JFrame appFrameStatusBoat = new JFrame("Status boat");
	JFrame frameStatus = new JFrame();
	JFrame smsFrame = new JFrame("SMS");
	static BoatAtAnchor boatAtAnchor = new BoatAtAnchor(null);
	static BoatAtSea boatAtSea = new BoatAtSea(null);
	static MooredBoat mooredBoat = new MooredBoat(null);
	
	
	JLabel statusTitle = new JLabel();
	JLabel boatStatus = new JLabel();
	JLabel waterLevel = new JLabel();
	JLabel theshold = new JLabel();
	JLabel charger = new JLabel();
	JLabel latitude = new JLabel();
	JLabel longitude = new JLabel();
	JLabel thesholdValueMaxWind = new JLabel();
	JLabel thesholdValueMaxPosition = new JLabel();
	
	JLabel numberLabel = new JLabel();
	JTextField number = new JTextField();
	
	JButton send = new JButton();
	

	JButton bilgePumpActuator = new JButton();
	JButton fridgeActuator = new JButton();
	JTextField tf1,tf2;
	JCheckBox checkBox1 = new JCheckBox("0");
	JCheckBox checkBox2 = new JCheckBox("1");
	JCheckBox checkBox3 = new JCheckBox("2");
	JPanel panel = new JPanel();
	
	JRadioButton r1 = new JRadioButton("boat at anchor");    
	JRadioButton r2 = new JRadioButton("boat at sea"); 
	JRadioButton r3 = new JRadioButton("moored boat");
	

	private int count = 1;
	

    public static String filePath;
    
	Font f1 = new Font("Helvetica", Font.BOLD, 20);
	Font f2 = new Font("Helvetica", Font.ITALIC, 14);
	
	public static String chargerBoatAtAnchor;
	public static String chargerBoatAtSea;
	public static String chargerBoatMoored;
	
	public static String waterLevelBoatAtAnchor;
	public static String waterLevelBoatAtSea;
	public static String waterLevelBoatMoored;
	
	
	private static final String BASE_URL = "https://xrmw53.api.infobip.com";
    private static final String API_KEY = "159c1d7556d477c6181293998d699d6b-a4135bc0-bafc-4244-b240-c6e79daa0928";

    private static final String SENDER = "MarineElectronics";
    private static String RECIPIENT = "";
    private static String MESSAGE_TEXT = "alarm";
	
    public static double thesholdValueWind = 2.4;
    public static double thesholdValuePosition = 5.4;
   
    

	public StatusFrameBoat() throws IOException  {
		
		
		
		numberLabel.setText("number: ");
		numberLabel.setBounds(2, 5, 100, 100);
		
		number.setBounds(60, 46, 120, 20);
		
		
		send.setText("send sms");
		send.setBounds(20, 20, 100, 20);
		
		
		
		
		frameStatus.setBounds(50, 50, 250, 250);
		frameStatus.setLayout(null);  
		frameStatus.setVisible(true);
		frameStatus.setLocation(1200, 50);
		
		
		smsFrame.setBounds(50, 50, 210, 100);
		smsFrame.setLayout(null);  
		smsFrame.setVisible(true);
		smsFrame.setLocation(1460, 200);
		
		smsFrame.add(numberLabel);
		smsFrame.add(number);
		smsFrame.add(send);
		
		
		r1.setBounds(10,50,200,30);    
		r2.setBounds(10,100,200,30);
		r3.setBounds(10,150,200,30);
		
		
		
		frameStatus.add(r1);
		frameStatus.add(r2);
		frameStatus.add(r3);
		
		

		appFrameStatusBoat.setSize(500, 400);
		appFrameStatusBoat.setVisible(true);
		appFrameStatusBoat.setTitle("Status boat");
		appFrameStatusBoat.setLocation(600, 50);
		appFrameStatusBoat.setResizable(false);
		
		
		statusTitle.setText("status boat ");
		statusTitle.setBounds(200,30, 250,30); 
		statusTitle.setFont(f1);
		statusTitle.setVisible(true);
		appFrameStatusBoat.add(statusTitle);
		
		
	
		boatStatus.setBounds(200, 60, 250, 30);
		boatStatus.setFont(f2);
		boatStatus.setVisible(true);
		appFrameStatusBoat.add(boatStatus);
		
		
		waterLevel.setText("waterlevel's bilgePump: ");
		waterLevel.setBounds(5, 100, 250, 30);
		waterLevel.setFont(f2);
		waterLevel.setVisible(true);
		appFrameStatusBoat.add(waterLevel);
		
		
		theshold.setText("wind theshold 's setting: ");
		theshold.setBounds(5, 140, 250, 30);
		theshold.setFont(f2);
		theshold.setVisible(true);
		appFrameStatusBoat.add(theshold);
		
		
		charger.setText("power 's level: ");
		charger.setBounds(5, 200, 250, 30);
		charger.setFont(f2);
		charger.setVisible(true);
		appFrameStatusBoat.add(charger);
		
		
		longitude.setBounds(5, 230, 250, 30);
		longitude.setFont(f2);
		longitude.setText("longitudine: -81.7948 ");
		longitude.setVisible(false);
		appFrameStatusBoat.add(longitude);
		
		latitude.setBounds(5, 250, 250, 30);
		latitude.setFont(f2);
		latitude.setText("latitudine: 26.1420");
		latitude.setVisible(false);
		appFrameStatusBoat.add(latitude);
		
		

		checkBox1.setBounds(210, 130, 50, 50);
		checkBox2.setBounds(250, 130, 50, 50);
		checkBox3.setBounds(290, 130, 50, 50);
		
		
		

		appFrameStatusBoat.add(checkBox1);
		appFrameStatusBoat.add(checkBox2);
		appFrameStatusBoat.add(checkBox3);
		

	      

	        bilgePumpActuator.setText("activate bilgePump");
	        bilgePumpActuator.setBounds(30, 300, 200, 50);
	        bilgePumpActuator.addActionListener(this);
	        appFrameStatusBoat.add(bilgePumpActuator);
	        

	       fridgeActuator.setText("activate fridge");
	       fridgeActuator.setBounds(250, 300, 200, 50);
	       fridgeActuator.addActionListener(this);
	       appFrameStatusBoat.add(fridgeActuator);
	       
	       
		    tf1=new JTextField("theshold 1");
		    tf1.setBounds(20,170,70,20); 
		    
		    tf2=new JTextField("theshold 2"); 
	        tf2.setBounds(100,170,70,20);
	        
	        thesholdValueMaxWind.setBounds(200,100 , 150, 170);
	        thesholdValueMaxWind.setFont(f2);
	        thesholdValueMaxWind.setText("thesholdValue max: " + thesholdValueWind);
	        
	        appFrameStatusBoat.add(thesholdValueMaxWind);
	        
	        thesholdValueMaxPosition.setBounds(5, 230, 190, 100);
	        thesholdValueMaxPosition.setFont(f2);
	        thesholdValueMaxPosition.setText("thesholdValue max: " + thesholdValuePosition);
	        
	        appFrameStatusBoat.add(thesholdValueMaxPosition);
	        
	        tf1.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent cl){
	               // t1.setText(" ");
	            	
	            	boatAtSea.thesoldHandler(Double.parseDouble(tf1.getText()));
	            	boatAtAnchor.thesoldHandler(Double.parseDouble(tf1.getText()));
	            	mooredBoat.thesoldHandler(Double.parseDouble(tf1.getText()));
	            	setTimer2();
	            	
	            	if (Double.parseDouble(thesholdValueMaxWind.getText()) > thesholdValueWind) {
	            	RECIPIENT = "+393454807077";
					
					 ApiClient client = initApiClient();
					 
				        SendSmsApi sendSmsApi = new SendSmsApi(client);
				 
				        SmsTextualMessage smsMessage = new SmsTextualMessage()
				                .from(SENDER)
				                .addDestinationsItem(new SmsDestination().to(RECIPIENT))
				                .text(MESSAGE_TEXT);
				 
				        SmsAdvancedTextualRequest smsMessageRequest = new SmsAdvancedTextualRequest()
				                .messages(Collections.singletonList(smsMessage));
				 
				        try {
				            SmsResponse smsResponse = sendSmsApi.sendSmsMessage(smsMessageRequest);
				            System.out.println("Response body: " + smsResponse);
				            
				        } catch (ApiException e1) {
				        	
				            System.out.println("HTTP status code: " + e1.getCode());
				            System.out.println("Response body: " + e1.getResponseBody());
				        }
	            	}
	            }
	        });
	        
	        tf2.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent cl){
	               // t1.setText(" ");

	            	
	            	boatAtSea.thesoldHandler(Double.parseDouble(tf2.getText()));
	            	boatAtAnchor.thesoldHandler(Double.parseDouble(tf2.getText()));
	            	mooredBoat.thesoldHandler(Double.parseDouble(tf2.getText()));
	            	
	            	setTimer2();
	            	
	            	if ( Double.parseDouble(tf2.getText()) > thesholdValueWind ) {
	            	
	            	RECIPIENT = "+393454807077";
					
					 ApiClient client = initApiClient();
					 
				        SendSmsApi sendSmsApi = new SendSmsApi(client);
				 
				        SmsTextualMessage smsMessage = new SmsTextualMessage()
				                .from(SENDER)
				                .addDestinationsItem(new SmsDestination().to(RECIPIENT))
				                .text(MESSAGE_TEXT);
				 
				        SmsAdvancedTextualRequest smsMessageRequest = new SmsAdvancedTextualRequest()
				                .messages(Collections.singletonList(smsMessage));
				 
				        try {
				            SmsResponse smsResponse = sendSmsApi.sendSmsMessage(smsMessageRequest);
				            System.out.println("Response body: " + smsResponse);
				            
				        } catch (ApiException e1) {
				        	
				            System.out.println("HTTP status code: " + e1.getCode());
				            System.out.println("Response body: " + e1.getResponseBody());
				        }
	            }
	        
	            }
	        });
	     


	        appFrameStatusBoat.add(tf1);
	        appFrameStatusBoat.add(tf2);
	        
			panel.setBounds(5,170,250,30);
			appFrameStatusBoat.add(panel);
			

	        filePath = "/Users/valentinadibiase/eclipse-workspace/MarineElectronics/src/beep-05 (online-audio-converter.com).wav";

	        
	        
		    //add allow listener
			r1.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {

		        	
		    		boatAtAnchor.charger =  (float) 8.2;
		    		boatAtAnchor.chargerHandler();
		    		boatAtAnchor.waterLevel = 2;
		    		boatAtAnchor.waterLevelHandler();

		    		chargerBoatAtAnchor = String.valueOf(boatAtAnchor.charger);
		    		waterLevelBoatAtAnchor = String.valueOf(boatAtAnchor.waterLevel);
		    		

		    		longitude.setVisible(true);
		    		latitude.setVisible(true);
		    		
		    		
		    		boatStatus.setText(boatAtAnchor.setName());
		    		
		    		waterLevel.setText("waterlevel's bilgePump: " + boatAtAnchor.waterLevel);
		    		
		    		charger.setText("power's level: " + boatAtAnchor.charger);
		    		
		    		if (boatAtAnchor.charger < 10.5) {
		    			
		    			fridgeActuator.setSelected(true);
		    			fridgeActuator.setText("deactivate fridge");
		    			
		    		}
		    		
		        }

		    });
			
			
			
			r2.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {

		        	
		    		boatAtSea.charger =  (float) 4.5;
		    		boatAtSea.chargerHandler();
		    		boatAtSea.waterLevel = 5;
		    		boatAtSea.waterLevelHandler();
		    		
		    		chargerBoatAtSea = String.valueOf(boatAtSea.charger);
		    		waterLevelBoatAtSea = String.valueOf(boatAtSea.waterLevel);
		    		
		    		boatStatus.setText(boatAtSea.setName());
		    		
		    		waterLevel.setText("waterlevel's bilgePump: " + boatAtSea.waterLevel);
		    		
		    		charger.setText("power's level: " + boatAtSea.charger);
		    		
		    		longitude.setVisible(false);
		    		latitude.setVisible(false);
		        	
		    		if (boatAtSea.charger < 10.5) {
		    			
		    			fridgeActuator.setSelected(true);
		    			fridgeActuator.setText("deactivate fridge");
		    			
		    		}

		    		
		        }

		    });
			
			
			
			r3.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {

		    		mooredBoat.charger = (float) 13.4;
		    		mooredBoat.chargerHandler();
		    		mooredBoat.waterLevel = 3;
		    		mooredBoat.waterLevelHandler();
		    		
		    		chargerBoatMoored = String.valueOf(mooredBoat.charger);
		    		waterLevelBoatMoored = String.valueOf(mooredBoat.waterLevel);
		    		
		    		boatStatus.setText(mooredBoat.setName());
		    		
		    		
		    		waterLevel.setText("waterlevel's bilgePump: " + mooredBoat.waterLevel);
		        	
		    		charger.setText("power's level: " + mooredBoat.charger);
		    		
		    		longitude.setVisible(false);
		    		latitude.setVisible(false);
		    		

		    		
		        }

		    });

	        checkBox2.addItemListener(new ItemListener() {

	        	@Override
	             public void itemStateChanged(ItemEvent e)  {                

	                 if (e.getStateChange() == ItemEvent.SELECTED) {
	                	 
	                     tf1.setVisible(true);
	                     tf2.setVisible(false);
	                     
	                     
	                     
	                 } else {
	                	 
	                     tf1.setVisible(false);
	                     
	                 }
 
	        	} 
	
	        	
	        });
	        
	        
	        
	        checkBox3.addItemListener(new ItemListener() {


	        	@Override
	             public void itemStateChanged(ItemEvent e) {                 

	                 if (e.getStateChange() == ItemEvent.SELECTED) {
	                	 
	                     tf1.setVisible(true);
	                     tf2.setVisible(true);
	                     
	                     
	                     
	                 } else {
	                	 
	                     tf1.setVisible(false);
	                     tf2.setVisible(false);
	                     
	                 }

	             }
	        	
	        	
	        });
	        

	        
	        fridgeActuator.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

						if (fridgeActuator.getText().equals("activate fridge")) {
							
							 
							
							fridgeActuator.setText("deactivate fridge");
						
						} else if (fridgeActuator.getText().equals("deactivate fridge")) {
							
							fridgeActuator.setText("activate fridge");
							
							
						}
						
					}
					
				
			});
	        
	     
	        
	          bilgePumpActuator.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if (bilgePumpActuator.getText().equals("activate bilgePump ")) {
						
						
						
						

			
						setTimer();
						
						boatAtSea.playSound(filePath);
						//mooredBoat.playSound(filePath);
						//boatAtAnchor.playSound(filePath);

						bilgePumpActuator.setText("deactivate bilgePump");
						
					
					} else if (bilgePumpActuator.getText().equals("deactivate bilgePump")) {
						
						bilgePumpActuator.setText("activate bilgePump");
						
						
					}
					
				}
			});
	        
	          send.addActionListener(new ActionListener() {
	  			
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						RECIPIENT = number.getText();
						
						 ApiClient client = initApiClient();
						 
					        SendSmsApi sendSmsApi = new SendSmsApi(client);
					 
					        SmsTextualMessage smsMessage = new SmsTextualMessage()
					                .from(SENDER)
					                .addDestinationsItem(new SmsDestination().to(RECIPIENT))
					                .text(MESSAGE_TEXT);
					 
					        SmsAdvancedTextualRequest smsMessageRequest = new SmsAdvancedTextualRequest()
					                .messages(Collections.singletonList(smsMessage));
					 
					        try {
					            SmsResponse smsResponse = sendSmsApi.sendSmsMessage(smsMessageRequest);
					            System.out.println("Response body: " + smsResponse);
					            
					        } catch (ApiException e1) {
					        	
					            System.out.println("HTTP status code: " + e1.getCode());
					            System.out.println("Response body: " + e1.getResponseBody());
					        }
						
						
					}
				});
				
				writeFile2(" boat at anchor charger", boatAtAnchor.charger =  (float) 8.2);
				writeFile2(" boat at sea charger", boatAtSea.charger =  (float) 8.2);
				writeFile2(" moored boat charger", mooredBoat.charger =  (float) 13.4);
				writeFile2(" boat at anchor waterlevel", (float) (boatAtAnchor.waterLevel = 2));
				writeFile2(" boat at sea waterlevel", (float) (boatAtSea.waterLevel = 5));
				writeFile2(" moored boat waterlevel", (float) (boatAtSea.waterLevel = 3));
				
				
				
				

	        

     

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		new StatusFrameBoat();

		

	}
	
	
	 private static ApiClient initApiClient() {
		 
	        ApiClient client = new ApiClient();
	 
	        client.setApiKeyPrefix("App");
	        client.setApiKey(API_KEY);
	        client.setBasePath(BASE_URL);
	 
	        return client;
	    }
	
	
	// Listato 3. Scrittura di un file con BufferWriter
	public static void writeFile2(String name, float value) throws IOException {
	 
		File file = new File("/Users/valentinadibiase/Desktop/MarineElectronics/src/status.txt"); //initialize File object and passing path as argument  
		FileWriter fw = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(fw);
		boolean result;  
		try   
		{  
			
			
		result = file.createNewFile();  //creates a new file  


		 //bw.write(" " + name + ": " + String.valueOf(value) + "\n");
		bw.write(" boat at anchor charger: " + boatAtAnchor.charger  + "\n");
		bw.write(" boat at sea charger: " + boatAtSea.charger  + "\n");
		bw.write(" moored boat charger: " + mooredBoat.charger  + "\n");
		bw.write(" boat at anchor waterlevel: " + (float) boatAtAnchor.waterLevel + "\n");
		bw.write(" boat at sea waterlevel: " + (float) boatAtSea.waterLevel + "\n");
		bw.write(" moored boat waterlevel: " + (float) boatAtSea.waterLevel );
		
		 

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
	
	public static void setTimer() {
		
	      boolean x = true;
	      long displayMinutes = 0;
	      long starttime = System.currentTimeMillis();
	      long timepassed = 0;
	      long secondspassed = 0;
	      
	   //   labelTimer.setText(displayMinutes + ":" + secondspassed);
		
		 do {

			 
	    	  try {
	    		  
		    	TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
					
			}
		   
		    timepassed=System.currentTimeMillis()-starttime;
		   
		    secondspassed=timepassed/1000;
		   
		    if(secondspassed==60)
		   {
		        secondspassed=0;
		        starttime=System.currentTimeMillis();
		        
		    }
		    

		    	if((secondspassed%60)==0) 
		    
		    		displayMinutes++;

		   
		    	
		    	System.out.println(displayMinutes + ":" + secondspassed);
		    	
		    	
		    	if(displayMinutes == 5 && secondspassed == 1) {
		    		
		    	
		    		x = false;
		    		
		    	}
		    	
			
		} while (x);
		 
	}
		 
		 
		 public static void setTimer2() {
				
		      boolean x = true;
		      long displayMinutes = 0;
		      long starttime = System.currentTimeMillis();
		      long timepassed = 0;
		      long secondspassed = 0;
		      
		   //   labelTimer.setText(displayMinutes + ":" + secondspassed);
			
			 do {

				 
		    	  try {
		    		  
			    	TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
						
				}
			   
			    timepassed=System.currentTimeMillis()-starttime;
			   
			    secondspassed=timepassed/1000;
			   
			    if(secondspassed==60)
			   {
			        secondspassed=0;
			        starttime=System.currentTimeMillis();
			        
			    }
			    

			    	if((secondspassed%60)==0) 
			    
			    		displayMinutes++;

			   
			    	
			    	System.out.println(displayMinutes + ":" + secondspassed);
			    	
			    	
			    	if(displayMinutes == 0 && secondspassed == 10) {
			    		

			    		x = false;
			    		
			    	}
			    	
				
			} while (x);
		 
		
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}

