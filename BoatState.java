package MarineElectronics;

public abstract class BoatState {
	
	protected Boat boat;
	
	public BoatState(Boat boat) {
		
		this.boat = boat;
	}
	
	public abstract String setName();
	public abstract void playSound(String audioFilePath);
	public abstract void waterLevelHandler();
	public abstract void chargerHandler();
	public abstract void thesoldHandler(Double thesoldValue);



}
