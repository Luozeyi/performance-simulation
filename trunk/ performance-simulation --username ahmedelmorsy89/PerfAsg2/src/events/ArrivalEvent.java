package events;

import java.io.ObjectInputStream.GetField;
import java.util.TimerTask;

import model.Job;
import model.Simulator;
import randomgenerators.ExponentialGenerator;
import randomgenerators.UniformGenerator;

public class ArrivalEvent extends TimerTask {

	private Simulator simulator;
	private int serverId;
	private Job job;
	private boolean newItem;
	static ExponentialGenerator itemArrival = new ExponentialGenerator();

	public ArrivalEvent(Simulator sim, int id, Job job, boolean newItem) {
		this.simulator = sim;
		this.serverId = id;
		this.job = job;
		this.newItem = newItem;
	}

	@Override
	public void run() {
		// 1.1 from flowchart
		// schedule next arrival event if it's the first queue
		if (this.serverId == 1) {
			job.setArrivalTime(System.currentTimeMillis());
			double delay = itemArrival.generate();
			this.simulator.schedule(new ArrivalEvent(simulator, serverId,
					new Job(), true), delay);
		} else {
			job.setInspectionArrivalTime(System.currentTimeMillis());
		}
		// check if server idle ? make it busy and schedule Service Event
		if (simulator.getServer(serverId).isIdle()) {
			simulator.getServer(serverId).setIdle(false);
			simulator.startService();
		} else {
			// else enqueue and Q++
			simulator.getServer(serverId).enqueueJob(job);
		}
	}
}
