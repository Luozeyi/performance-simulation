package model;

import java.util.Timer;
import java.util.TimerTask;

import randomgenerators.UniformGenerator;

import events.DepartureEvent;

public class Simulator {
	
	private Server machiningCenter;
	private Server inspectionStation;
	private Timer timer;
	
	public Simulator() {
		this.machiningCenter = new Server(1);
		this.inspectionStation = new Server(2);
		this.timer = new Timer();
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
		long delay = UniformGenerator.generate();
		schedule(new DepartureEvent(), delay);
	}
}
