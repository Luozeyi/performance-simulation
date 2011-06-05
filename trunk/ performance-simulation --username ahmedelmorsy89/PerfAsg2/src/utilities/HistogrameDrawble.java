package utilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

public class HistogrameDrawble {



	public static  void  draw(double list[])
	{
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
