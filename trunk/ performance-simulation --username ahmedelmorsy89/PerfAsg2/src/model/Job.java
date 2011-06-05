package model;

public class Job {

	private long arrivalTime;
	private long MachineServiceTime;
	private long inspectionArrivalTime;
	private long inspectionServiceTime;
	private long departureTime;
	
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
	
	
	
}
