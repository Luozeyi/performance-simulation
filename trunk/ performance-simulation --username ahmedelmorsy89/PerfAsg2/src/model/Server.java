package model;


public class Server {

	private boolean idle;
	private int queueLen;
	private int id;
	
	public Server(int i) {
		this.idle = true;
		this.queueLen = 0;
		this.id = i;
	}

	public boolean isIdle() {
		return idle;
	}

	public int getQueueLen() {
		return queueLen;
	}

	public int getId() {
		return id;
	}

	public void setBusy() {
		this.idle = false;
	}

	public void enqueueJob() {
		this.queueLen++;
	}
	
	public void dequeueJob() {
		this.queueLen--;
	}
}
