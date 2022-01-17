package MarineElectronics;

import java.util.Vector;
import java.util.Enumeration;

public class CompoundPart extends Component{
	
	private Vector children;


    public CompoundPart(String name) {
        super(name);
 //       this.latitude  = latitude;
 //       this.longitude = longitude;
    }
	
  

	@Override
	public void describe() {
		
		System.out.println("Component: " + name);
		System.out.println("Composed by: ");
		System.out.println("{");
		
		
		int vLength = children.size();
		
		for(int i = 0; i < vLength; i++) {
			
			Component c = (Component) children.get(i);
			c.describe();
			
		}
		
		System.out.println("}");
		// TODO Auto-generated method stub
		
	}
	
	public void add(Component c) throws SinglePartException {
		
		children.addElement(c);
		
	}
	
	public void remove(Component c) throws SinglePartException {
		
		children.removeElement(c);
	}
	
	public Component getChild(int n) {
		
		return (Component) children.elementAt(n);
	}







	@Override
	public double speedBoatLog(double km, double hour) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public double directionBoat(String latitude, String longitude) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String directionBoatCompass(double degree) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public double speedBoatDv(double seaCurrentVelocity, double speedBoatlog) {
		// TODO Auto-generated method stub
		return 0;
	}




}









//@Override
//public double trueWindSpeed(double apparentWindSpeed,double speedBoat) {
	// TODO Auto-generated method stub
	
//	double trueWindSpeed = apparentWindSpeed - speedBoat + 0.2 * speedBoat;
	
//	return trueWindSpeed;
//}

//@Override
//public double apparentWindSpeed(double trueWindSpeed,double speedBoat) {
	// TODO Auto-generated method stub
//	double apparentWindSpeed = trueWindSpeed + speedBoat;
	
//	return apparentWindSpeed;
//}



//@Override
//public double speedBoatLog(double km, double hour) {
	// TODO Auto-generated method stub
//	double nm = 1.852;
//	double nauticalMiles = (km * nm);
	
//	double knot = nauticalMiles / hour;
	
//	return knot;
//}



//@Override
//public double directionBoat(String latitude, String longitude) {
	// TODO Auto-generated method stub
	
//	double lat = Double.parseDouble(latitude);
//	double lng = Double.parseDouble(longitude);
	
//	double degree = Math.atan2(lat, lng) * 180 / Math.PI;

	
	
//	return degree;
//}



//@Override
//public double trueWindDirection(double apparentWindSpeed, double speedBoat, double trueWindSpeed) {
	// TODO Auto-generated method stub

	
//	double trueWindDirection =( Math.pow(apparentWindSpeed, 2.0) - Math.pow(trueWindSpeed, 2.0) - Math.pow(speedBoat, 2.0)) /( 2 * trueWindSpeed * speedBoat);
	
//	trueWindDirection = Math.acos(trueWindDirection) * 180/ Math.PI;
	
//	return trueWindDirection;
//}



//@Override
//public double speedBoatDv(double seaCurrentVelocity, double speedBoatlog) {
	// TODO Auto-generated method stub
	
//	double dvSpeed = seaCurrentVelocity + speedBoatlog;
//	return dvSpeed;
//}



//@Override
//public String directionBoatCompass(double degree) {
	// TODO Auto-generated method stub
	
//	String directionName = null;
	
   //   if( degree > 315.0 && degree <= 0.0) {
			
	//		directionName = "nord";
	//		System.out.println(directionName);
		
	//	}
		
	//  if( degree > 0.0 && degree <= 45.0) {
			
	//		directionName = "nord - est";
	//		System.out.println(directionName);
		
	//	}

	//	if( degree > 45.0 &&  degree <= 90.0) {
			
	//		directionName = "est";
	//		System.out.println(directionName);
		
	//	}
		
	//	if( degree > 90.0 && degree <= 135.0) {
			
	//		directionName = " sud - est";
	//		System.out.println(directionName);
		
//		}
		
//		if( degree > 135.0 && degree <= 180.0) {
			
//			directionName = " sud ";
//			System.out.println(directionName);
		
//		}
		
//		if( degree > 180.0 && degree <= 225.0) {
			
//			directionName = " sud - ovest ";
//			System.out.println(directionName);
		
//		}
		
//		if( degree > 225.0 && degree <= 270.0) {
			
//			directionName = " ovest ";
//			System.out.println(directionName);
		
//		}
		
//		if( degree > 270.0 && degree <= 315.0) {
			
//			directionName = " nord - ovest ";
//			System.out.println(directionName);
		
//		}
//	return directionName;
//}


//@Override
//public double distanceTo(Component that) {
	// TODO Auto-generated method stub
//     double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
//      double lat1 = Math.toRadians(this.latitude);
//      double lon1 = Math.toRadians(this.longitude);
//      double lat2 = Math.toRadians(that.latitude);
//      double lon2 = Math.toRadians(that.longitude);

    // great circle distance in radians, using law of cosines formula
//      double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
//                             + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

    // each degree on a great circle of Earth is 60 nautical miles
//      double nauticalMiles = 60 * Math.toDegrees(angle);
//       double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
   
//       return statuteMiles;
    
//}
