package utilities;

import java.util.TimerTask;

import model.Simulator;

public class HrThroughputCalc extends TimerTask{

	private static int hour = 1;
	private Simulator sim;
	private static int lastCount = 0;
	
	public HrThroughputCalc(Simulator sim) {
		this.sim = sim;
	}
	
	@Override
	public void run() {
		int good = sim.getGoodItemsCount();
		System.out.println("getting throughput with good = " + good + " and last count = "
				+ lastCount);
		ResultsLogger.getInstance().logHourlyThroughput(hour++, good - lastCount);
		lastCount = good;
		sim.schedule(new HrThroughputCalc(sim), (long)((60 * Simulator.MINUTE)*1000));
	}

}
