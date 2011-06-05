package utilities;
import java.util.Random;


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
		HistogrameDrawble.draw(list);
		
	}
}
