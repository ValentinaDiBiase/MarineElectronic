package MarineElectronics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public abstract class Component {
	
	public String name;
	protected double latitude;
	protected double longitude;
	protected double apparentWindSpeed;
	protected double speedBoat;
	protected double trueWindSpeed;
	protected double km;
	protected double hour;
	protected double directionWind;
	protected String directionName;
	protected double directionBoat;
	
	
	

	
	public Component(String aName) {
		
		name = aName;
		
	}
	
	
	

    

    
	
	public abstract void describe();
	//public abstract double trueWindSpeed(double apparentWindSpeed,double speedBoat);
	//public abstract double apparentWindSpeed(double trueWindSpeed,double speedBoat);
	public abstract double speedBoatLog(double km, double hour);
	public abstract double directionBoat(String latitude, String longitude);
	public abstract String directionBoatCompass(double degree);
	
	//public abstract double trueWindDirection(double apparentWindSpeed,double speedBoat, double trueWindSpeed);
	public abstract double speedBoatDv(double seaCurrentVelocity, double speedBoatlog);
	
	
	
	
	public void add(Component c) throws SinglePartException {
		
		if ( this instanceof SinglePart) {
			throw new SinglePartException();
			
		}
			
	}
	
	public void remove(Component c) throws SinglePartException {
		
		if (this instanceof SinglePart) {
			
			throw new SinglePartException();
			
		}
	}
	
	public Component getChild(int n) {
		
		return null;
	}



	
	
	
	

}

//   public Component(String name, double latitude, double longitude) {
//      this.name = name;
//      this.latitude  = latitude;
//      this.longitude = longitude;
//   }
