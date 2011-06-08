package events;

import java.util.TimerTask;

import model.Job;
import model.Simulator;
import randomgenerators.ExponentialGenerator;
import utilities.ResultsLogger;

public class ArrivalEvent extends TimerTask {

	private Simulator simulator;
	private int serverId;
	private Job job;
	private boolean newItem;
	private static final double ARRIVAL_MEAN = 1.0 / Simulator.MINUTE;
	
	public static ExponentialGenerator itemArrival = new ExponentialGenerator(
			ARRIVAL_MEAN);;

	public ArrivalEvent(Simulator sim, int id, Job job, boolean newItem, boolean schedule) {
		this.simulator = sim;
		this.serverId = id;
		this.job = job;
		this.newItem = newItem;
		if (schedule) {
			long delay = (long) (itemArrival.generate() * 1000);
			this.simulator.schedule(this, delay);
			ResultsLogger.getInstance().logInterArrival(serverId, delay);
		}
	}

	@Override
	public void run() {
		synchronized (simulator) {
			// 1.1 from flowchart
			// schedule next arrival event if it's the first queue
			System.out.println("Job " + job.getId() + " arrived at server" + serverId + "at " + job.getArrivalTime());
			if (this.newItem) {
				if (this.serverId == 0) {
					long delay = (long) (itemArrival.generate() * 1000);
					if(! this.simulator.isStop())
					{
						Job temp = new Job();
						temp.setArrivalTime(System.currentTimeMillis() + delay);
						this.simulator.schedule(new ArrivalEvent(simulator,
								serverId, temp, true, false), delay);
						ResultsLogger.getInstance().logInterArrival(serverId, delay);
					}
				} else {
					job.setInspectionArrivalTime(System.currentTimeMillis());
					ResultsLogger.getInstance().logInterArrival(serverId, job.getInspectionArrivalTime() - simulator.getServer(serverId).getLastArrival());
				}
			}
			// check if server idle ? make it busy and schedule Service Event
			if (simulator.getServer(serverId).isIdle()
					&& !simulator.getServer(serverId).isBrokedown()) {
				System.out.println("Job " + job.getId() + " started at server" + serverId);
				simulator.getServer(serverId).setIdle(false);
				simulator.getServer(serverId).enqueueJob(job);
				simulator.startService(job, serverId);
			} else {
				System.out.println("Job " + job.getId() + " enqueued at server" + serverId +
						" as we have " + simulator.getServer(serverId).getQueueLen());
				// else enqueue and Q++
				simulator.getServer(serverId).enqueueJob(job);
			}
		}
	}
}
