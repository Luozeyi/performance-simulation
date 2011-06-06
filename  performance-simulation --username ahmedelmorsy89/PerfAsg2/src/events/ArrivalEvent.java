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
	private static final double ARRIVAL_MEAN = 1.0/30;
	static ExponentialGenerator itemArrival = new ExponentialGenerator(ARRIVAL_MEAN);;

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
			long delay = (long)(itemArrival.generate() * 1000);
			Job temp = new Job();
			temp.setArrivalTime(System.currentTimeMillis() + delay);
			this.simulator.schedule(new ArrivalEvent(simulator, serverId,
				temp , true), delay);
		} else {
			job.setInspectionArrivalTime(System.currentTimeMillis());
		}
		// check if server idle ? make it busy and schedule Service Event
		if (simulator.getServer(serverId).isIdle()) {
			simulator.getServer(serverId).setIdle(false);
			simulator.startService(job, serverId);
		} else {
			// else enqueue and Q++
			simulator.getServer(serverId).enqueueJob(job);
		}
	}
}
