package randomgenerators;

import utilities.HistogrameDrawble;

public class UniformGenerator implements Generator {

	private int current;
	private final static int k = 18;
	private final static int m = (int) Math.pow(2, k);
	
	private final static int a = 11;

	public UniformGenerator(int seed) {
		this.current = seed;
	}

	public int generateS() {

		current = a * current % m;
		return current;
	}

	public double generate() {

		current = a * current % m;
		return ((double) current) / m;
	}

	public boolean isUniformaly() {
		UniformGenerator generator = new UniformGenerator(0);
		int[] hash = new int[10];
		for (int i = 0; i < 1000; i++) {
			int cat = (generator.generateS() / 10);
			if (cat < 10)
				hash[cat]++;
		}

		double d = 0;
		for (int i = 0; i < 10; i++)
			d += ((hash[i] - 100) * (hash[i] - 100)) / (double) 100;

		// chi square of alpha = 0.1 and K = 10 chi (0.9 , 9) = 14.68
		return 14.68 < d;

	}

	public static int getMaxLength() {
		return (int) Math.pow(2, k-2);
	}

	public boolean isFullSequance() {
		for (int i = 1; i < m - 1; i++)
			if (Math.pow(a, i) % m == 1)
				return false;
		return true;
	}

	public static void main(String[] args) {

		UniformGenerator x = new UniformGenerator(5);
		System.out.println(x.isUniformaly());
		int n = 60000;
		double[] list = new double[n];
		for (int i = 0; i < n; i++) {
			list[i] = ((double) x.generateS());
		}

		HistogrameDrawble.draw(list);

	}

}
