package GraphData;

import java.util.ArrayList;

public class DirectPath {

	ArrayList<Node> path ;
	double gain ;
	
	public DirectPath(ArrayList<Node> path, double gain) {
		this.path = new ArrayList<>(path) ;
		this.gain = gain ;
	}

	public ArrayList<Node> getPath() {
		return path;
	}

	public double getGain() {
		return gain;
	}
}
