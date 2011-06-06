package model;

import java.util.ArrayList;


public class Server {

	private boolean idle;
	private ArrayList<Job> queue;
	private int id;
	
	public Server(int i) {
		this.idle = true;
		this.queue = new ArrayList<Job>();
		this.id = i;
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
	}
	
	public Job dequeueJob() {
		return this.queue.remove(0);
	}

	public Job getServicedJob() {
		return this.queue.get(0);
	}
}
