package GraphData;

public class Node {
	
	private String Label ;
	
	public Node(String label){
		this.Label = label ; 
	}

	public String getLabel() {
		return Label;
	}
	
	@Override
	public boolean equals(Object node){
		return Label.equals(((Node) node).getLabel());
	}
	
	@Override
	public int hashCode(){
		return Label.hashCode();
	}
	
}
