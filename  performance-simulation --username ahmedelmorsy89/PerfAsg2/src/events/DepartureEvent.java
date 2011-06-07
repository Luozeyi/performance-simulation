package events;

import java.util.TimerTask;
import model.Job;
import model.Simulator;
import randomgenerators.SeedsGenerators;
import randomgenerators.UniformGenerator;
import utilities.ResultsLogger;

public class DepartureEvent extends TimerTask {
	private Simulator simulator;
	private int serverId;
	private Job job;
	static UniformGenerator itemInspection = new UniformGenerator(
			SeedsGenerators.getInstance().getSeed());

	public DepartureEvent(Simulator sim, int id, Job job) {
		this.simulator = sim;
		this.serverId = id;
		this.job = job;
	}

	@Override
	public void run() {
		synchronized (simulator) {
			System.out.println("Departure event from server" + serverId + " for job"
					 + job.getId() + " at ");
			if (job.getDelay() > 0) {
				System.out.println("Job number " + job.getId() + " is going to continue " +
						"after breakdown event with time " + job.getDelay());
				this.simulator.schedule(new DepartureEvent(simulator, serverId,
						job), job.getDelay());
				job.setDelay(0);
				return;
			}
			if (this.serverId == 0) { // Dept. of server 0 is Arrival of server
										// 1
				System.out.println("Job number " + job.getId() + " is going to leave server" +
						" 0 to server 1");
				Job finished = this.simulator.getServer(serverId).dequeueJob();
				if(finished != job)
					System.err.println("ERROR");
				this.simulator.schedule(new ArrivalEvent(simulator, 1,
						job, true, false), 0);
			} else {
				this.simulator.getServer(serverId).dequeueJob();
				double state = itemInspection.generate();
				if (state >= 0.1) // state = GOOD
				{
					System.out.println("Job number " + job.getId() + " is leaving " +
						"server 1 with good state");
					ResultsLogger.getInstance().logTotalResponse(job.getId(), System.currentTimeMillis() - job.getArrivalTime());
					simulator.incGoodItems();
				}
				else { // state = BAD
					System.out.println("Job number " + job.getId() + " is leaving " +
						"server 1 with bad state");
					this.simulator.schedule(new ArrivalEvent(simulator, 0,
							job, false, false), 0);
				}
			}
			
			if (this.simulator.getServer(serverId).getQueueLen() > 0) {
				Job temp = this.simulator.getServer(serverId).getServicedJob();
				System.out.println("Job number " + temp.getId() + " is starting " +
						"at server " + serverId);
				simulator.getServer(serverId).setIdle(false);
				simulator.startService(temp, serverId);
			}
			else {
				System.out.println("Server " + serverId + " is going idle");
				simulator.getServer(serverId).setIdle(true);
			}
		}
	}
}
