package events;

import java.util.TimerTask;

import model.Job;
import model.Server;
import model.Simulator;
import randomgenerators.ExponentialGenerator;
import randomgenerators.SeedsGenerators;
import randomgenerators.UniformGenerator;

public class BreakDownEvent extends TimerTask {

	private Server server;
	private Simulator sim;
	private static final double BREAKDOWN_MEAN = 1.0 / (6 * 60 * Simulator.MINUTE); //6 hours * 60 min * 2 sec
	private static final double REPAIR_FROM = 8 * Simulator.MINUTE;// 8 minutes * 2 sec
	private static final double REPAIR_TO = 12 * Simulator.MINUTE;// 12 minutes * 2 sec

	static ExponentialGenerator[] breakDownRand = {new ExponentialGenerator(
			BREAKDOWN_MEAN), new ExponentialGenerator(BREAKDOWN_MEAN)};
	static UniformGenerator[] repairRand = { new UniformGenerator(SeedsGenerators
			.getInstance().getSeed()), new UniformGenerator(SeedsGenerators.getInstance().getSeed())};

	public BreakDownEvent(Simulator sim, Server server, boolean schedule) {
		this.server = server;
		this.sim = sim;
		if(schedule)
		{
			long nextBreakDown = 0;
			nextBreakDown = (long) (breakDownRand[server.getId()].generate() * 1000);
			sim.schedule(this, nextBreakDown);
			System.out.println("Next breakdown of server " + this.server.getId() + " after " + nextBreakDown);
		}
	}

	@Override
	public void run() {
		synchronized (sim) {
			this.server.setBrokedown(true);
			long nextBreakDown = 0;
			long repair = 0;
			do
			{
				nextBreakDown = (long) (breakDownRand[server.getId()].generate() * 1000);
				repair = (long) ((REPAIR_FROM + (REPAIR_FROM - REPAIR_TO)
						* repairRand[server.getId()].generate()) * 1000);
			} while(repair >= nextBreakDown);
			
			sim.schedule(new BreakDownEvent(this.sim, this.server, false),
					nextBreakDown);
			// scale the generated uniform rand (from 0 to 1) to (from 240 to
			// 360)
			if(server.isIdle())
				System.out.println("Server " + server.getId() + " is going to breakdown now " +
						"while idle for " + repair);
			else
				System.out.println("Server " + server.getId() + " is going to breakdown now " +
						"with job " + server.getServicedJob().getId() + " for " + repair);
			sim.schedule(new RepairEvent(sim, server), repair);
			if (!this.server.isIdle()) {
				Job serviced = this.server.getServicedJob();
				serviced.setDelay(repair);
				System.out.println("Job " + serviced.getId() + " is going to delay with " + repair);
			}
		}
	}

}
