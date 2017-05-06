package GraphData;

import java.awt.Color;

public class Edge {

	private double gain;
	private Color color;
	private Node from, to;

	public Edge(Node from, Node to, double gain) {
		this.from = from;
		this.to = to;
		this.gain = gain;
		this.color = Color.black;
	}

	public double getGain() {
		return gain;
	}

	public void setGain(double gain) {
		this.gain = gain;
	}

	public Node getFrom() {
		return from;
	}

	public Node getTo() {
		return to;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean Equal(Edge toCompare) {
		return (this.from.equals(toCompare.getFrom())
				&& this.to.equals(toCompare.getTo()));
	}

}
