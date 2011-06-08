package model;

import java.util.Timer;
import java.util.TimerTask;
import randomgenerators.SeedsGenerators;
import randomgenerators.UniformGenerator;
import utilities.HrThroughputCalc;
import utilities.ResultsLogger;
import events.ArrivalEvent;
import events.BreakDownEvent;
import events.DepartureEvent;

public class Simulator {
	public static final double MINUTE = 0.5; 
	private Server machiningCenter;
	private Server inspectionStation;
	private Timer timer;
	private long startTime;
	
	private int goodItemsCount;
	private boolean stop;
	
	static UniformGenerator[] serverServiceTime = 
	{ new UniformGenerator(SeedsGenerators.getInstance().getSeed()), new UniformGenerator(SeedsGenerators.getInstance().getSeed())};
	
	private static final double[] SERVER_Service_FROM = {0.65 * MINUTE, 0.75 * MINUTE};
	private static final double[] SERVER_Service_TO = {0.7 * MINUTE, 0.8 * MINUTE};
	
	public Simulator() {
		this.machiningCenter = new Server(0);
		this.inspectionStation = new Server(1);
		this.timer = new Timer();
		this.goodItemsCount = 0;
	}

	public void start() {
		this.startTime = System.currentTimeMillis();
		Job temp = new Job();
		new ArrivalEvent(this, 0, temp, true, true);
		new BreakDownEvent(this, machiningCenter, true);
		new BreakDownEvent(this, inspectionStation, true);
		timer.schedule(new HrThroughputCalc(this),  (long)(60 * MINUTE * 1000));
	}
	
	public Server getServer(int i) {
		if (i == 0)
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
		System.out.println("Job number " + job.getId() + " will be serviced at " +
				serverId + " for time " + delay);
		ResultsLogger.getInstance().logServiceTime(serverId, delay);
		schedule(new DepartureEvent(this, serverId, job), delay);
	}
	
	public synchronized void incGoodItems() {
		goodItemsCount++;
	}

	public synchronized int getGoodItemsCount() {
		return goodItemsCount;
	}

	public long getStartTime() {
		return startTime;
	}

	public void stop() {
		this.stop = true;
	}

	public boolean isStop() {
		return stop;
	}
}
