package MarineElectronics;

public class TransformAngle {
	
	public static double from_180_to_360_angle(double angle_180) {
		
		double angle_360 = 0;
		if(angle_180>=0)
			angle_360=angle_180;
		else
			angle_360 = 360 + angle_180;
		
		
		
		return angle_360;
	}

}
