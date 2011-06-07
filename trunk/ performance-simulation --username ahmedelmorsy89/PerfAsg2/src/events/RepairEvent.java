package events;

import java.util.TimerTask;

import model.Job;
import model.Server;
import model.Simulator;

public class RepairEvent extends TimerTask {

	private Simulator sim;
	private Server server;

	public RepairEvent(Simulator sim, Server server) {
		this.sim = sim;
		this.server = server;
	}

	@Override
	public void run() {
		synchronized (sim) {
			System.out.println("Server " + server.getId() +" is now repaired ");
			this.server.setBrokedown(false);
			if (this.server.getQueueLen() > 0 && this.server.isIdle()) {
				this.server.setIdle(false);
				Job temp = this.server.getServicedJob();
				System.out.println("Server " + server.getId() +" is starting new job "
						+ temp.getId());
				this.sim.startService(temp, this.server.getId());
			}
		}
	}

}
