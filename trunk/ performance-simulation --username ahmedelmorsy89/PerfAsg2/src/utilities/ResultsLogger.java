package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultsLogger {

	private static ResultsLogger instance;
	private int number = 0;
	private PrintWriter interarrival[] = new PrintWriter[2];
	private PrintWriter serviceTime[] = new PrintWriter[2];
	private PrintWriter totalResponse;
	private PrintWriter queueLengths[] = new PrintWriter[2];
	private PrintWriter hourlyThroughput;
	
	private ResultsLogger()
	{
		try {
			interarrival[0] = new PrintWriter(new FileWriter("logs/" + number + "_interarrival_0.txt"));
			interarrival[1] = new PrintWriter(new FileWriter("logs/" + number + "_interarrival_1.txt"));
			serviceTime[0] = new PrintWriter(new FileWriter("logs/" + number + "_servicetime_0.txt"));
			serviceTime[1] = new PrintWriter(new FileWriter("logs/" + number + "_servicetime_1.txt"));
			queueLengths[0] = new PrintWriter(new FileWriter("logs/" + number + "_queue_0.txt"));
			queueLengths[1] = new PrintWriter(new FileWriter("logs/" + number + "_queue_1.txt"));
			totalResponse = new PrintWriter(new FileWriter("logs/" + number + "_totalresponse.txt"));
			hourlyThroughput = new PrintWriter(new FileWriter("logs/" + number + "_throughput.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ResultsLogger getInstance() {
		if (instance == null)
			instance = new ResultsLogger();
		return instance;
	}

	public void logInterArrival(int serverId, long delay) {
		interarrival[serverId].println(delay);
		interarrival[serverId].flush();
	}
	
	public void logServiceTime(int serverId, long delay) {
		serviceTime[serverId].println(delay);
		serviceTime[serverId].flush();
	}
	
	public void logTotalResponse(int jobId, long delay) {
		totalResponse.println(jobId + "," + delay);
		totalResponse.flush();
	}
	
	public void queueLength(int serverId, long period, int length) {
		queueLengths[serverId].println(period + " " + length);
		queueLengths[serverId].flush();
	}
	
	public void logHourlyThroughput(long time, double delay) {
		hourlyThroughput.println(time + "," + delay);
		hourlyThroughput.flush();
	}
}