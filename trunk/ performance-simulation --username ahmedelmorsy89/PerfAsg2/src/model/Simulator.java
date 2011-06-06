package model;

import java.util.Timer;
import java.util.TimerTask;

import randomgenerators.SeedsGenerators;
import randomgenerators.UniformGenerator;
import events.DepartureEvent;

public class Simulator {
	
	private Server machiningCenter;
	private Server inspectionStation;
	private Timer timer;
	static UniformGenerator[] serverServiceTime = 
	{ new UniformGenerator(SeedsGenerators.getInstance().getSeed()), new UniformGenerator(SeedsGenerators.getInstance().getSeed())};
	
	private static final double[] SERVER_Service_FROM = {19.5, 22.5};
	private static final double[] SERVER_Service_TO = {21, 24};
	
	public Simulator() {
		this.machiningCenter = new Server(0);
		this.inspectionStation = new Server(1);
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
	
	public void startService(Job job, int serverId) {
		long delay = (long)((SERVER_Service_FROM[serverId] 
		                                         + (SERVER_Service_TO[serverId] 
		                                                              - SERVER_Service_FROM[serverId]) 
		                                                              * serverServiceTime[serverId].generate())*1000);
		schedule(new DepartureEvent(this, serverId, job), delay);
	}
}
