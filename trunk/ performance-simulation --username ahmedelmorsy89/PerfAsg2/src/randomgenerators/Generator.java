package randomgenerators;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class Generator {

	private static int seed = 0;
	protected ArrayList<Integer> seedsArr;
	protected File file;

	public Generator() {
		seedsArr = new ArrayList<Integer>();
		read();

	}

	private void read() {
		try {

			FileInputStream fstream = new FileInputStream(file);
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

	public abstract double generate();

	public abstract int generateS();

	public int getSeed() {

		return seedsArr.get(seed++);
	}

	public abstract int getMaxLength();
}
