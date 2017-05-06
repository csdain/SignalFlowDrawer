package GraphData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Data {

	private ArrayList<Node> Nodes;
	private ArrayList<Edge> Edges;
	private HashMap<Node, ArrayList<Edge>> list;

	private ArrayList<DirectPath> DirectPaths;
	private ArrayList<Cycle> singleCycles;
	private HashSet<Node> distinctPathsNodes;
	private HashMap<Node,Double> gainUntilNode ;
	private ArrayList<ArrayList<ConnectedCycle>> nConnectedCycles;

	public Data() {
		Nodes = new ArrayList<>();
		Edges = new ArrayList<>();
	}


	public void addEdge(Node from, Node to, Double gain) {
		Edge newEdge = new Edge(from, to, gain);
		
		addNode(from);addNode(to);
		
		for (Edge i : Edges) {
			if (i.Equal(newEdge)) {
				i.setGain(gain);
				return;
			}
		}
		Edges.add(newEdge);
	}

	public double getGain(Node start, Node end) {
		if(!Nodes.contains(start)||!Nodes.contains(end))throw new RuntimeException("make sure that both nodes exist");
		generateGraphList();
		ArrayList<Node> visited = new ArrayList<>();
		visited.add(start);
		clearData();
		dfs(start, visited, 1, end);
		removeOuterCycles();
		mergeCycles();
		double delta = calculateDelta();
		double sigmaMideltai = calculateSigmaMiDeltai();
		return sigmaMideltai/delta;
	}
	
	public ArrayList<ArrayList<ConnectedCycle>> getNCycles(){
		return nConnectedCycles;
	}
	
	public ArrayList<Cycle> getSingleCycles(){
		return singleCycles; 
	}
	
	public ArrayList<DirectPath> getDirectedPaths(){
		return DirectPaths;
	}

	public ArrayList<Node> getNodes() {
		return Nodes;
	}

	public ArrayList<Edge> getEdges() {
		return Edges;
	}
	
	private void addNode(Node addNode) {
		if (!isFound(addNode)) {
			Nodes.add(addNode);
		}
	}
	
	private double calculateSigmaMiDeltai(){
		double sigma = 0 ;
		for(int i=0 ; i<DirectPaths.size() ; i++){
			sigma += calculateMiDeltai(i) ; 
		}
		return sigma ;
	}
	
	private double calculateMiDeltai(int i){
		double deltai = 1 ; 
		for(int j=0 ; j<singleCycles.size() ; j++){
			if(!touchPath(DirectPaths.get(i),singleCycles.get(j)))
				deltai -= singleCycles.get(j).getGain();
		}
		return DirectPaths.get(i).getGain()*deltai;
	}
	
	private boolean touchPath(DirectPath p , Cycle c){
		ArrayList<Node> pathNodes = p.getPath();
		ArrayList<Node> cycleNodes = c.getPath();
		for(Node i :pathNodes)
			if(cycleNodes.contains(i))
				return true;
		return false;
	}
	
	private double calculateDelta(){
		double gain = 1 ;
		for(int i=0 ; i<singleCycles.size() ; i++){
			gain -= singleCycles.get(i).getGain() ; 
		}
		for(int i=0 ; nConnectedCycles.get(2)!= null && i<nConnectedCycles.get(2).size() ; i++){
			gain += nConnectedCycles.get(2).get(i).getGain();
		}
		for(int i=0 ; nConnectedCycles.get(3)!=null &&  i<nConnectedCycles.get(3).size() ; i++){
			gain -= nConnectedCycles.get(3).get(i).getGain();
		}
		return gain;
	}

	private boolean isFound(Node toFind) {
		for (Node i : Nodes)
			if (i.getLabel().equals(toFind.getLabel()))
				return true;
		return false;
	}

	private void mergeCycles() {
		int sz = singleCycles.size();
		nConnectedCycles = new ArrayList<>();
		for(int i=0 ; i<4 ; i++)nConnectedCycles.add(new ArrayList<>());
		// merging double cycles
		for (int i = 0; i < sz; i++) {
			ArrayList<Integer> indeces = new ArrayList<>();
			indeces.add(i+1);
			for (int j = i + 1; j < sz; j++) {
				indeces.add(j+1);
				if (isMergable(singleCycles.get(i), singleCycles.get(j))) {
					ArrayList<Cycle> cyclesToMerge = new ArrayList<>();
					cyclesToMerge.add(singleCycles.get(i));
					cyclesToMerge.add(singleCycles.get(j));
					ConnectedCycle newconn = new ConnectedCycle(cyclesToMerge,new ArrayList<>(indeces));
					nConnectedCycles.get(2).add(newconn);
				}
				indeces.remove(indeces.size()-1);
			}
			indeces.remove(indeces.size()-1);
		}
		// merging triple cycles
		for (int i = 0; i < sz; i++) {
			ArrayList<Integer> indeces = new ArrayList<>();
			indeces.add(i+1);
			for (int j = i + 1; j < sz; j++) {
				indeces.add(j+1);
				for (int k = j + 1; k < sz; k++) {
					indeces.add(k+1);
					if (isMergable(singleCycles.get(i), singleCycles.get(j))
							&& isMergable(singleCycles.get(k), singleCycles.get(j))
							&& isMergable(singleCycles.get(i), singleCycles.get(k))) {
						ArrayList<Cycle> cyclesToMerge = new ArrayList<>();
						cyclesToMerge.add(singleCycles.get(i));
						cyclesToMerge.add(singleCycles.get(j));
						cyclesToMerge.add(singleCycles.get(k));
						ConnectedCycle newconn = new ConnectedCycle(cyclesToMerge,indeces);
						nConnectedCycles.get(3).add(newconn);
					}
					indeces.remove(indeces.size()-1);
				}
				indeces.remove(indeces.size()-1);
			}
			indeces.remove(indeces.size()-1);
		}
	}

	private boolean isMergable(Cycle c1, Cycle c2) {
		ArrayList<Node> nc1 = c1.getPath();
		ArrayList<Node> nc2 = c2.getPath();
		for (Node i : nc1) {
			if (nc2.contains(i))
				return false;
		}
		return true;
	}

	private void generateGraphList() {
		list = new HashMap<>();
		for(Node i : Nodes){
			list.put(i, new ArrayList<>());
		}
		for (Edge i : Edges)
			list.get(i.getFrom()).add(i);
	}

	private void clearData() {
		DirectPaths = new ArrayList<>();
		singleCycles = new ArrayList<>();
		distinctPathsNodes = new HashSet<>();
		nConnectedCycles = new ArrayList<>();
		gainUntilNode = new HashMap<>();
	}

	private void removeOuterCycles() { // O(single cycles)
		for (int i = 0; i < singleCycles.size(); i++) {
			if (singleCycles.get(i).isOutside(distinctPathsNodes)) {
				singleCycles.remove(i);
				i--;
			}
		}
	}

	private void dfs(Node parent, ArrayList<Node> vis, double gain, Node end) { // O(nodes)
		ArrayList<Edge> childs = list.get(parent);
		for (Edge i : childs) {
			if (!vis.contains(i.getTo())) {
				vis.add(i.getTo());
				if (i.getTo().equals(end)) {
					addDistinctPathNodes(vis);
					DirectPaths.add(new DirectPath(vis, gain * i.getGain()));
				} else {
					gainUntilNode.put(i.getTo(), gain * i.getGain());
					dfs(i.getTo(), vis, gain * i.getGain(), end);
				}
				vis.remove(i.getTo());
			} else {
				ArrayList<Node> cycleNodes = getCycle(vis, i.getTo());
				Cycle toAdd = new Cycle(cycleNodes, gain * i.getGain() / gainUntilNode.get(i.getTo()));
				if(!isRepeated(toAdd))singleCycles.add(toAdd);
			}
		}
	}
	
	private boolean isRepeated(Cycle cycle){
		if(singleCycles.size()==0)return false;
		for(int i=0 ; i<singleCycles.size() ; i++){
			if(singleCycles.get(i).equals(cycle)){
				return true;
			}
		}
		return false;
	}

	private void addDistinctPathNodes(ArrayList<Node> directPath) { // O(nodes)
		for (Node i : directPath)
			distinctPathsNodes.add(i);
	}

	private ArrayList<Node> getCycle(ArrayList<Node> path, Node parent) { // O(nodes)
		ArrayList<Node> ret = new ArrayList<>();
		int beg = path.indexOf(parent);
		for (int i = beg; i < path.size(); i++)
			ret.add(path.get(i));
		ret.add(parent);
		return ret;
	}

}
