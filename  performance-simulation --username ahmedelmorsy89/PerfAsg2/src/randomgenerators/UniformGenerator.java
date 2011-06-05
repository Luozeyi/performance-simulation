package randomgenerators;

import utilities.HistogrameDrawble;

public class UniformGenerator {


	private int current;
	private final int m = 2311;
	private final int a = 13;

	public UniformGenerator(int seed) {
		this.current = seed;
	}

	public int generate() {

		current = a * current % m;
		return current;
	}

	public boolean isUniformaly()
	{
		UniformGenerator generator = new UniformGenerator(0);
		int[] hash = new int[10];
		for(int i = 0 ; i < 1000;i++)
		{
			int cat = (generator.generate()/10);
			if(cat < 10)
				hash[cat]++;
		}
		
		double d = 0;
		for (int i = 0 ; i < 10 ; i++)
			d += ((hash[i]-100)*(hash[i]-100))/(double)100;
		
		// chi square of alpha = 0.1 and K = 10    chi (0.9 , 9) = 14.68
		return 14.68 < d ;
		
	}

	public boolean isFullSequance() {
		for (int i = 1; i < m - 1; i++)
			if (Math.pow(a, i) % m == 1)
				return false;
		return true;
	}

	public static void main(String[] args) {

		UniformGenerator x = new UniformGenerator(1);
		System.out.println(x.isUniformaly());
		int n = 10000;
		double []list = new double[n];
		for (int i = 0; i < n; i++) {
			list[i] = ((double)x.generate());
		}
		
		HistogrameDrawble.draw(list);
	

	}


}
