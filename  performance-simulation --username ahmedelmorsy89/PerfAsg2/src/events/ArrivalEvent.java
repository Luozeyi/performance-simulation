package events;

import java.util.TimerTask;

import model.Job;
import model.Simulator;
import randomgenerators.ExponentialGenerator;

public class ArrivalEvent extends TimerTask{

	private Simulator simulator;
	private int serverId;
	private Job job;
	
	public ArrivalEvent(Simulator sim, int id, Job job) { 
		this.simulator = sim;
		this.serverId = id;
		this.job = job;
	}
	
	@Override
	public void run() {
		//1.1 from flowchart
		//schedule next arrival event if it's the first queue
		if (this.serverId == 1) {
			job.setArrivalTime(System.currentTimeMillis());
			long delay = ExponentialGenerator.generate();
			this.simulator.schedule(new ArrivalEvent(simulator, serverId, new Job()), delay);
		} else {
			job.setInspectionArrivalTime(System.currentTimeMillis());
		}
		//check if server idle ? make it busy and schedule Service Event
		if (simulator.getServer(serverId).isIdle()) {
			simulator.getServer(serverId).setBusy();
			simulator.startService();
		} else {
			//else enqueue and Q++
			simulator.getServer(serverId).enqueueJob(job);
		}
	}
}
