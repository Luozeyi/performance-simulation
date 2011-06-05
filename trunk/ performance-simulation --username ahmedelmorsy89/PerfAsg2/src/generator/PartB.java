package generator;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;


public class PartB {
	public static void main(String[] args) {
		double[] list = new double[100000];
		Random rand = new Random();
		double lambda = -1 / 1; 
		for(int i = 0; i < 100000; i++)
		{
			list[i] = lambda * Math.log(rand.nextDouble());
		}
		int number = 10;
		HistogramDataset dataset = new HistogramDataset();
		dataset.setType(HistogramType.RELATIVE_FREQUENCY);
		dataset.addSeries("Histogram", list, 90);
		String plotTitle = "Histogram";
		String xaxis = "number";
		String yaxis = "value";
		PlotOrientation orientation = PlotOrientation.VERTICAL;
		boolean show = false;
		boolean toolTips = false;
		boolean urls = false;
		JFreeChart chart = ChartFactory.createHistogram(plotTitle, xaxis,
				yaxis, dataset, orientation, show, toolTips, urls);
		int width = 500;
		int height = 300;
		ChartFrame frame = new ChartFrame("Function Chart", chart);
		frame.setVisible(true);
		frame.setSize(750, 750);
	}
}
