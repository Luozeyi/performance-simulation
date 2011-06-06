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
	private static final double BREAKDOWN_MEAN = 1.0/10800;
	private static final double REPAIR_FROM = 240;// 8 minutes * 30 sec
	private static final double REPAIR_TO = 360;//12 minutes * 30 sec
	
	static ExponentialGenerator breakDownRand = new ExponentialGenerator(BREAKDOWN_MEAN);
	static UniformGenerator repairRand = new UniformGenerator(SeedsGenerators.getInstance().getSeed());

	public BreakDownEvent(Simulator sim, Server server) {
		this.server = server;
		this.sim = sim;
	}

	@Override
	public void run() {
		long nextBreakDown = (long)(breakDownRand.generate() * 1000);
		sim.schedule(new BreakDownEvent(this.sim, this.server), nextBreakDown);
		//scale the generated uniform rand (from 0 to 1) to (from 240 to 360)
		long repair = (long)((REPAIR_FROM + (REPAIR_FROM - REPAIR_TO) * repairRand.generate())*1000);
		Job serviced = this.server.getServicedJob();
		serviced.setDelay(repair);
	}

}
