package MarineElectronics;

public class Boat {
	
	private BoatState boatState;
	
	
	public void setState( BoatState bs) {
		
		boatState = bs;
	}
	
	public void waterLevelHandler() {
		
		boatState.waterLevelHandler();
	}
	
	public void chargerHandler() {
		
		boatState.chargerHandler();
	}
	
	public String setName() {
		
		return boatState.setName();
	}
	
	//public void playSound(String audioFilePath) {
		
	//	boatState.playSound(audioFilePath);
	//}

}
