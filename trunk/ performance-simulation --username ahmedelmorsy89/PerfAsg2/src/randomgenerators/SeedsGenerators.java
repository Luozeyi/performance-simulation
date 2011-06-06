package randomgenerators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class SeedsGenerators {

	public static final int UniformG = 1 << 2;
	public static final int ExponentialG = 1 << 3;

	public void generate(int type, int periodLenght, int nSeeds) {
		Generator g = null;
		int[] seedsArr = new int[nSeeds];
		switch (type) {
		case UniformG:
			g = new UniformGenerator(1);
			break;
		case ExponentialG:
			g = new ExponentialGenerator();
			break;
		}
		int number = -1;
		for (int i = 0; i < g.getMaxLength(); i++) {
			number = g.generateS();
			if (i % periodLenght == 0) {
				seedsArr[i / periodLenght] = number;
			}
		}

		writeProbFile(seedsArr, type);
	}

	private void writeProbFile(int[] seedsArr, int type) {
		File file = null;
		switch (type) {
		case UniformG:
			file = new File("unifromSeed.txt");
			break;
		case ExponentialG:
			file = new File("exponential.txt");
			break;
		}

		try {
			// Create file
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			for (int i = 0; i < seedsArr.length; i++) {
				out.write(seedsArr[i]+"\n");

			}


			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}


	}
}
