import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

//TODO:
//add borders to grid
//fix screen size issue
//implement a star algorithm to solve maze

public class DrawingBoard extends JPanel {
	private static final long serialVersionUID = 1L;
	private final int[][] grid;
	private final int squareSize;
	private Graph nodeGraph;

	public DrawingBoard(int[][] grid, int squareSize) {
		this.grid = grid;
		this.squareSize = squareSize;
		this.getShortestPath();

		setBackground(Color.black);
		setFocusable(true);
	}

	public void getShortestPath() {
		this.nodeGraph = new Graph();
		
		Node startingNode = null;
		Node targetNode = null;
		
		//look for starting node and end node
		for (int i = 0; i < grid.length; i ++) {
			if (grid[0][i] == 2) {
				startingNode = new Node(i, 0);
			}
			
			if (grid[grid.length - 1][i] == 2) {
				targetNode = new Node(i, grid.length - 1);
			}
		}
		
		
		this.nodeGraph.addNode(startingNode);
		
		//add relevant nodes to graph data structure
		for (int y = 1; y < this.grid.length - 1; y++) {
			for (int x = 1; x < this.grid[y].length - 1; x++) {
				if (grid[y][x] != 1 && grid[y][x] != 2) {
					Node newNode = new Node(x, y);
					this.nodeGraph.addNode(newNode);
				}
			}
		}
		
		this.nodeGraph.addNode(targetNode);
		
		Node currentNode = this.nodeGraph.dijkstras(targetNode);

		while (currentNode != null) {
			grid[currentNode.getY()][currentNode.getX()] = 3;
			currentNode = currentNode.getParent();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.updateFrame(g);
	}

	public void updateFrame(Graphics g) {
		Graphics2D graphicsManager = (Graphics2D) g;

		for (int i = 0; i < this.grid.length; i++) {
			for (int x = 0; x < this.grid[i].length; x++) {
				if (grid[i][x] == 3) {
					graphicsManager.setColor(Color.red);
				} else if (grid[i][x] == 2) {
					graphicsManager.setColor(Color.green);
				} else if (i == 0 || x == 0 || i == grid.length - 1 || x == grid.length - 1) {
					graphicsManager.setColor(Color.blue);
				} else if (grid[i][x] == 1) {
					graphicsManager.setColor(Color.white);
				} else {
					graphicsManager.setColor(Color.black);
				}

				graphicsManager.fillRect(x * this.squareSize, i * this.squareSize, this.squareSize, this.squareSize);
			}
		}

		Toolkit.getDefaultToolkit().sync();
		repaint();
	}
}
