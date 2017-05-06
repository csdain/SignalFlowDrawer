package GraphData;

import java.util.ArrayList;

public class ConnectedCycle {

	private int rank;
	private double gain = 1;
	ArrayList<Cycle> cycles;
	ArrayList<Integer> indeces;

	public ConnectedCycle(ArrayList<Cycle> cycles , ArrayList<Integer> indeces) {
		this.cycles = cycles;
		this.rank = cycles.size();
		this.indeces = indeces;
		calculateGain();
	}

	private void calculateGain(){
		for(Cycle i : cycles)
			gain *= i.getGain();
	}

	public int getRank() {
		return rank;
	}

	public double getGain() {
		return gain;
	}
	
	public ArrayList<Integer> getIndeces(){
		return indeces;
	}

	public ArrayList<Cycle> getCycles() {
		return cycles;
	}
	
}
