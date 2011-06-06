package events;

import java.util.TimerTask;

import model.Job;
import model.Server;
import randomgenerators.ExponentialGenerator;

public class BreakDownEvent extends TimerTask {

	private Server server;

	public BreakDownEvent(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		long delay = ExponentialGenerator.generate();
		Job serviced = this.server.getServicedJob();
		serviced.setDelay(delay);
	}

}