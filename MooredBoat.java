package MarineElectronics;

import java.io.File;
import java.time.LocalTime;
import java.util.Timer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class MooredBoat extends BoatState {
	
	public MooredBoat(Boat boat) {
		super(boat);
		// TODO Auto-generated constructor stub
	}


	public int waterLevel;
	public float charger;
	public float thesold;
	public String name;
	
	private Clip clip;
	
	public static  String filePath = "/Users/valentinadibiase/eclipse-workspace/MarineElectronics/src/beep-05 (online-audio-converter.com).wav";
	

	public static void main(String[] args) {
		
		MooredBoat boat = new MooredBoat(null);
		
		boat.chargerHandler();

		
	}

	
	public String setName() {
	 
		return name = "Barca ormeggiata";
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
		
		if (charger < 11.5) {
			
			System.out.println("attivare un allarme");
			
		
			
         
		}
		
		if (charger < 10.5) {
			
			
			System.out.println("spegni frigorifero. attiva un allarme");
	
			

		} 
		
		if (charger > 14.5) {
			
		
			
			
		}
	}



	@Override
	public void thesoldHandler(Double thesoldValue ) {
		// TODO Auto-generated method stub
		
		if( thesold >  thesoldValue) {
			
			System.out.println("alarm is active");
			
		}
		
	}


	@Override
	public void playSound(String audioFilePath) {
		// TODO Auto-generated method stub
		
	}
	

}




