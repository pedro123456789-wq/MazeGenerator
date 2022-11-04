import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
	private LinkedList<Node> nodes = new LinkedList<Node>();

	public LinkedList<Node> getNodes() {
		return this.nodes;
	}

	public void addNode(Node node) {
		this.nodes.add(node);
	}

	public void addNodes(Node[] nodes) {
		for (Node node : nodes) {
			this.nodes.add(node);
		}
	}

	public Node getNode(int xCoor, int yCoor) {
		for (Node node : this.nodes) {
			if (node.getX() == xCoor && node.getY() == yCoor) {
				return node;
			}
		}

		return null;
	}

	private Node getNextNode(HashMap<Node, Integer> distances, LinkedList<Node> visited) {
		Node nextNode = null;
		int smallestDistance = Integer.MAX_VALUE;

		for (Node node : distances.keySet()) {
			if (!visited.contains(node)) {
				int distance = distances.get(node);

				if (distance < smallestDistance) {
					smallestDistance = distance;
					nextNode = node;
				}
			}
		}

		return nextNode;
	}

	public Node dijkstras(Node targetNode) {
		Node rootNode = this.nodes.get(0);
		LinkedList<Node> visited = new LinkedList<Node>();
		HashMap<Node, Integer> distances = new HashMap<Node, Integer>();

		
		for (Node node : this.nodes) {
			if (node == rootNode) {
				distances.put(node, 0);
			} else {
				distances.put(node, Integer.MAX_VALUE);
			}
		}
		

		// not all nodes need to be visited to complete search
		boolean isFinished = false;

		// Dijkstra's algorithm
		while (visited.size() < this.nodes.size() - 1 && !isFinished) {			
			Node currentNode = getNextNode(distances, visited);
		
			if (currentNode != null) {
				// look for nodes left and right of current node
				Node rightNode = this.getNode(currentNode.getX() + 1, currentNode.getY());
				Node leftNode = this.getNode(currentNode.getX() - 1, currentNode.getY());
				Node downNode = this.getNode(currentNode.getX(), currentNode.getY() + 1);
				Node upNode = this.getNode(currentNode.getX(), currentNode.getY() - 1);

				Node[] adjacentNodes = { rightNode, leftNode, downNode, upNode };

				for (Node node : adjacentNodes) {
					if (node != null) {
						int oldDistance = distances.get(node);
						int newDistance = distances.get(currentNode) + 1;

						if (newDistance < oldDistance) {
							distances.put(node, newDistance);
							node.setParent(currentNode);
						}
					}
				}

				visited.add(currentNode);

			} else {
				isFinished = true;
			}
		}
		
		System.out.println("Done");

		return targetNode;
	}
	

	@Override
	public String toString() {
		String outputString = "Graph</>\n";

		for (Node node : nodes) {
			outputString += node.toString();
		}

		return outputString;
	}
}
