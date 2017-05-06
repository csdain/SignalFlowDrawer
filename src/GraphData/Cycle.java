package GraphData;

import java.util.ArrayList;
import java.util.HashSet;

public class Cycle {

	private ArrayList<Node> path;
	private double gain;

	public Cycle(ArrayList<Node> path, double gain) {
		this.path = path;
		this.gain = gain;
	}

	public ArrayList<Node> getPath() {
		return path;
	}

	public double getGain() {
		return gain;
	}
	
	public boolean isOutside(HashSet<Node> distinctPathNodes){
		for(Node i : path)
			if(!distinctPathNodes.contains(i))
				return true;
		return false;
	}
	
	@Override
	public boolean equals(Object cycle){
		if(((Cycle)cycle).getPath().size()!=this.path.size())return false;
		ArrayList<Node> nodes = ((Cycle)cycle).getPath() ;
		for(Node i : nodes){
			if(!path.contains(i)){
				return false;
			}
		}
		return true;
	}
}
