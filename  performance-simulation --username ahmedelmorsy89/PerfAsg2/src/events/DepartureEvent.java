package events;

import java.util.TimerTask;
import model.Job;
import model.Simulator;
import randomgenerators.UniformGenerator;

public class DepartureEvent extends TimerTask {
	private Simulator simulator;
	private int serverId;
	private Job job;
	static UniformGenerator itemInspection = new UniformGenerator(0);

	public DepartureEvent(Simulator sim, int id, Job job) {
		this.simulator = sim;
		this.serverId = id;
		this.job = job;
	}

	@Override
	public void run() {
		if(job.getDelay() > 0)
		{
			this.simulator.schedule(new DepartureEvent(simulator, serverId,
					job), job.getDelay());
			job.setDelay(0);
			return;
		}
		if (this.serverId == 1) { // Dept. of server 1 is Arrival of server 2
			this.simulator.getServer(serverId).dequeueJob();

			if (this.simulator.getServer(serverId).getQueueLen() > 0) {
				simulator.getServer(serverId).setIdle(false);
				simulator.startService(job, serverId);
			} else
				simulator.getServer(serverId).setIdle(true);
				this.simulator.schedule(new ArrivalEvent(simulator, 2, new Job(),
						true), 0);
		} else {
			this.simulator.getServer(serverId).dequeueJob();
			double state = itemInspection.generate();
			if (state >= 0.9) // state = GOOD
				;
			else { // state = BAD
				this.simulator.schedule(new ArrivalEvent(simulator, 1,
						new Job(), false), 0);
			}
		}
	}
}
