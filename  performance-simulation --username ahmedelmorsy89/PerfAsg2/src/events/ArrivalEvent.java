package events;

import java.util.Timer;
import java.util.TimerTask;

import randomgenerators.ExponentialGenerator;

public class ArrivalEvent extends TimerTask{

	private int queueNo;
	
	public ArrivalEvent(int queueNo) { 
		this.queueNo = queueNo;
	}
	
	
	@Override
	public void run() {
		//1.1 from flowchart
		//schedule next arrival event if it's the first queue
		if (queueNo == 1) {
			Timer timer = new Timer();
			long delay = ExponentialGenerator.generate();
			timer.schedule(new ArrivalEvent(1), delay);
		}
		//check if server idle ? make it busy and schedule Service Event
		
		//else enqueue and Q++
		
	}

}
