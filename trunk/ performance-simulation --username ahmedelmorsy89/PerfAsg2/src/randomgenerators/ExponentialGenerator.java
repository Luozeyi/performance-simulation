package randomgenerators;

import utilities.HistogrameDrawble;

public class ExponentialGenerator  implements Generator{

	private double current;
	private double lambda;
	UniformGenerator uni ;
	
	public ExponentialGenerator(double lambda) {
		this.lambda = -1/((double)lambda);
		SeedsGenerators.getInstance().generate(UniformGenerator.getMaxLength(), 4);
		uni = new UniformGenerator(SeedsGenerators.getInstance().getSeed());
	}
	
	public double generate() {
		current = lambda * Math.log(uni.generate());
		return current;
	}

	
	public int generateS() {
		return (int)generate();
	}

	public static void main(String[] args) {
		double[] list = new double[100000];
		Generator g = new ExponentialGenerator((double)1/30);
		for(int i = 0; i < 100000; i++)
		{
			list[i] = g.generate();
		}
		HistogrameDrawble.draw(list);
		
	}


}
