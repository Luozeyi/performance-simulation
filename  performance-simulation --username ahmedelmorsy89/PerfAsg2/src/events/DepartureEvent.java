package events;

import java.util.TimerTask;

import model.Job;
import model.Simulator;
import randomgenerators.UniformGenerator;

public class DepartureEvent extends TimerTask {
	private Simulator simulator;
	private int serverId;
	static UniformGenerator itemInspection = new UniformGenerator(0);
	
	public DepartureEvent(Simulator sim, int id) {
		this.simulator = sim;
		this.serverId = id;
	}

	@Override
	public void run() {
		if (this.serverId == 1) { // Dept. of server 1 is Arrival of server 2
			this.simulator.getServer(serverId).dequeueJob();
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
