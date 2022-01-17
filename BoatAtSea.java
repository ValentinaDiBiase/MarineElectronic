package MarineElectronics;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class BoatAtSea extends BoatState {
	
	public BoatAtSea(Boat boat) {
		super(boat);
		// TODO Auto-generated constructor stub
	}

	public int waterLevel;
	public float charger;
	public float thesold;
	public String name;
	

	private Clip clip;
	
	public static String filePath = "/Users/valentinadibiase/eclipse-workspace/MarineElectronics/src/beep-05 (online-audio-converter.com).wav";
	
	public static void main(String[] args) {
		
	BoatAtSea boat = new BoatAtSea(null);
	boat.chargerHandler();
	

	}
	
	
	public String setName() {
		 
		return name = "Barca in navigazione";
		
	}

	

	public void playSound(String audioFilePath)  {
		
		
		AudioInputStream audioInputStream;
		
		
		try {
			
		audioInputStream = AudioSystem.getAudioInputStream(new File(audioFilePath).getAbsoluteFile());
		
		
	    clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(new File(audioFilePath)));
		clip.start();

		
		} catch (Exception ex) {
			
			ex.printStackTrace(System.out);
		}
		
		
		
	}
	

	

	public void waterLevelHandler() {
		
		if  ( waterLevel > 5 ) {
			
			System.out.println("attivare un pompa di sentina.");
		
		} else {
			
			System.out.println("livello a regime. Livello attuale dell'acqua:  " +  waterLevel);
		}
		
			
	}
	
	public void chargerHandler() {
		

		

		
		System.out.println("carica della batteria:  " + charger );
		
		if (charger < 11.5 ) {
			
			System.out.println("attivare un allarme");
			

			
			playSound(filePath);
		
			
		}
		
		
		 if (charger < 10.5) {
			
			
			System.out.println("spegni frigorifero. attiva un allarme");
			
	
			
			playSound(filePath);
		
			
		} 
		 
		 if (charger > 14.5) {
			
			
			System.out.println("attivare un allarme");
			

			
			playSound(filePath);
		
			
			
		} 
	}


	
	@Override
	public void thesoldHandler(Double thesoldValue ) {
		// TODO Auto-generated method stub
		thesold = (float) 2.4;
		if( thesold >  thesoldValue) {
			
			System.out.println("alarm is active");
			
		}
		
	}

}
