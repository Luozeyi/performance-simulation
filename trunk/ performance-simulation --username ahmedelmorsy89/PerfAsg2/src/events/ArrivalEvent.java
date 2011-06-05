package events;

import java.util.TimerTask;

import model.Simulator;
import randomgenerators.ExponentialGenerator;

public class ArrivalEvent extends TimerTask{

	private Simulator simulator;
	private int serverId;
	
	public ArrivalEvent(Simulator sim, int id) { 
		this.simulator = sim;
		this.serverId = id;
	}
	
	@Override
	public void run() {
		//1.1 from flowchart
		//schedule next arrival event if it's the first queue
		if (this.serverId == 1) {
			long delay = ExponentialGenerator.generate();
			this.simulator.schedule(new ArrivalEvent(simulator, serverId), delay);
		}
		//check if server idle ? make it busy and schedule Service Event
		if (simulator.getServer(serverId).isIdle()) {
			simulator.getServer(serverId).setBusy();
			simulator.startService();
		} else {
			//else enqueue and Q++
			simulator.getServer(serverId).enqueueJob();
		}
	}
}
