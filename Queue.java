import java.util.HashMap;
import java.util.LinkedList;



public class Queue {
	private LinkedList<Node> queue = new LinkedList<Node>();
	 
	
	public Queue(LinkedList<Node> nodes) {
		for (Node node : nodes) {
			this.enqueue(node);
		}
	}
	
	
	public LinkedList<Node> getQueue() {
		return this.queue;
	}
	
	
	public void enqueue(Node node) {
		this.queue.add(node);
	}
	
	
	public Node dequeue() {
		Node front = this.queue.get(0);
		this.queue.remove(0);
		
		return front;
	}
	
	
	public Node getFront() {
		return this.queue.get(0);
	}
	
	
	public Node getBack() {
		return this.queue.get(this.queue.size() - 1);
	}
	
	
	public Node getNext(HashMap<Node, Integer> distances, LinkedList<Node> visited) {
		int smallestDistance = (int)(Double.POSITIVE_INFINITY);
		Node currentChoice = null;
		
		
		for (Node node : distances.keySet()) {
			if (!visited.contains(node) && distances.get(node) < smallestDistance) {
				currentChoice = node;
				smallestDistance = distances.get(node);
			}
		}
		
		return currentChoice;
	}
	
	
	public boolean isEmpty() {
		return this.queue.size() == 0;
	}
	
	
	@Override
	public String toString() {
		String outputString = "Queue: (";
		
		
		for (Node node : this.queue) {
			outputString += node.coordinateString() + " ,";
		}
		
		outputString += "\n";
		return outputString;
	}
	
	
	

}
