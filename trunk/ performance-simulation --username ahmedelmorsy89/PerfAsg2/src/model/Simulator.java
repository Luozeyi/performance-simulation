package model;

import java.util.Timer;
import java.util.TimerTask;

import randomgenerators.ExponentialGenerator;
import randomgenerators.UniformGenerator;

import events.DepartureEvent;

public class Simulator {
	
	private Server machiningCenter;
	private Server inspectionStation;
	private Timer timer;
	private UniformGenerator uniformRand;
	private ExponentialGenerator expRand;
	
	public Simulator() {
		this.machiningCenter = new Server(1);
		this.inspectionStation = new Server(2);
		this.timer = new Timer();
		int seed = 1;
		this.uniformRand = new UniformGenerator(seed);
	}

	public Server getServer(int i) {
		if (i == 1)
			return machiningCenter;
		else
			return inspectionStation;
	}

	public Timer getTimer() {
		return timer;
	}

	public void schedule(TimerTask departureEvent, long delay) {
		this.timer.schedule(departureEvent, delay);
	}
	
	public void startService() {
		long delay = uniformRand.generate();
		schedule(new DepartureEvent(), delay);
	}
}