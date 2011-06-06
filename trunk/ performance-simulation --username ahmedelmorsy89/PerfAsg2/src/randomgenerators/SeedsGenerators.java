package randomgenerators;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SeedsGenerators {
	private int seed;
	protected ArrayList<Integer> seedsArr;
	private static SeedsGenerators instance;

	private SeedsGenerators() {
		seed = 0;
		read();
	}
	
	
	public static SeedsGenerators getInstance()
	{
		if (instance == null)
			instance = new SeedsGenerators();
		return instance;
	}
	

	public void generate(int periodLenght, int nSeeds) {
		Generator g = new UniformGenerator(1);
		seedsArr = new ArrayList<Integer>();

		int number = -1;
		int l  = UniformGenerator.getMaxLength()/nSeeds;
		for (int i = 1; i < UniformGenerator.getMaxLength(); i++) {
			number = g.generateS();
			if (i % l == 0) {
				seedsArr.add( number);
			}
		}
		writeProbFile();
	}

	private void writeProbFile() {
		File file = new File("seeds.txt");

		try {
			// Create file
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			for (int i = 0; i < seedsArr.size(); i++) {
				out.write(seedsArr.get(i) + "\n");
			}

			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}

	private void read() {
		try {
			seedsArr = new ArrayList<Integer>();
			FileInputStream fstream = new FileInputStream("seeds.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				seedsArr.add(Integer.parseInt(strLine));
			}
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}

	public int getSeed() {
		
		int n = seedsArr.get(seed);
		seed = (seed+1) %seedsArr.size();
		return n;
	}

}
