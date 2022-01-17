package MarineElectronics;

import java.util.TimerTask;

public class Time extends TimerTask{
	
	public int countdown = 5;
	
	public void run() {
		// TODO Auto-generated method stub
		
		if (countdown > 0) {
			
			System.out.println(countdown);
			countdown--;
			
		} else {
			
			System.exit(0);
		}
		
	}

}
