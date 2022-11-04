import java.util.HashMap;

public class Node {
	private HashMap<Node, Integer> edges= new HashMap<Node, Integer>();
	private int xCoor;
	private int yCoor;
	private Node parent;
	
	
	public Node(int xCoor, int yCoor) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
	}
	
	
	public Node getParent() {
		return this.parent;
	}
	
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	
	public int getX() {
		return this.xCoor;
	}
	
	
	public int getY() {
		return this.yCoor;
	}
	
	
	public String coordinateString() {
		return this.xCoor + ", " + this.yCoor;
	}
	
	
	public HashMap<Node, Integer> getEdges(){
		return this.edges;
	}
	
	
	public void linkNode(Node node, int weight) {
		this.edges.put(node, weight);
	}
	
	
	@Override
	public String toString() {
		String connections = "";
		
		for (Node node : edges.keySet()) {
			connections += node.coordinateString() + ": " + edges.get(node) + "\n";
		}
		
		return "Node<" + this.coordinateString() + ">" + "\n" + connections + "\n";
	}
}

