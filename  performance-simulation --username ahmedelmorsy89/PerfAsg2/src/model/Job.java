package model;

public class Job {

	private static int idCount = 1;
	
	private int id;
	private long arrivalTime;
	private long MachineServiceTime;
	private long inspectionArrivalTime;
	private long inspectionServiceTime;
	private long departureTime;
	private long delay;
	
	public Job() {
		this.id = idCount++;
	}
	
	public long getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public long getMachineServiceTime() {
		return MachineServiceTime;
	}
	public void setMachineServiceTime(long machineServiceTime) {
		MachineServiceTime = machineServiceTime;
	}
	public long getInspectionArrivalTime() {
		return inspectionArrivalTime;
	}
	public void setInspectionArrivalTime(long inspectionArrivalTime) {
		this.inspectionArrivalTime = inspectionArrivalTime;
	}
	public long getInspectionServiceTime() {
		return inspectionServiceTime;
	}
	public void setInspectionServiceTime(long inspectionServiceTime) {
		this.inspectionServiceTime = inspectionServiceTime;
	}
	public long getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(long departureTime) {
		this.departureTime = departureTime;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	public long getDelay() {
		return this.delay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
