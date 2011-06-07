package model;

import java.util.ArrayList;

import utilities.ResultsLogger;


public class Server {

	private boolean idle;
	private ArrayList<Job> queue;
	private int id;
	private boolean brokedown;
	private long lastArrival;
	private long lastQueueUpdate;
	
	public Server(int i) {
		this.idle = true;
		this.queue = new ArrayList<Job>();
		this.id = i;
		this.brokedown = false;
		this.lastArrival = System.currentTimeMillis();
	}

	public boolean isIdle() {
		return idle;
	}

	public int getQueueLen() {
		return queue.size();
	}

	public int getId() {
		return id;
	}

	public void setIdle(boolean state) {
		this.idle = state;
	}

	public void enqueueJob(Job job) {
		this.queue.add(job);
		this.lastArrival = System.currentTimeMillis();
		long now = System.currentTimeMillis();
		ResultsLogger.getInstance().queueLength(id, now - lastQueueUpdate, getQueueLen());
		this.lastQueueUpdate = now;
	}
	
	public Job dequeueJob() {
		long now = System.currentTimeMillis();
		ResultsLogger.getInstance().queueLength(id, now - lastQueueUpdate, getQueueLen());
		this.lastQueueUpdate = now;
		return this.queue.remove(0);
	}
	
	public long getLastArrival() {
		return lastArrival;
	}

	public Job getServicedJob() {
		return this.queue.get(0);
	}

	public boolean isBrokedown() {
		return brokedown;
	}

	public void setBrokedown(boolean brokedown) {
		this.brokedown = brokedown;
	}
}