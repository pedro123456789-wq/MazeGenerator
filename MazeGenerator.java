import java.util.LinkedList;
import java.util.Random;

public class MazeGenerator {
	private final int width;
	private final int height;
	private Random randomizer = new Random();
	private int[][] grid;

	public MazeGenerator(int height, int width) {
		this.width = width;
		this.height = height;
		this.grid = new int[height][width];
	}

	public void generateMaze(boolean isVertical, int leftBound, int rightBound, int bottomBound, int topBound) {
		if (rightBound - leftBound < 2 || topBound - bottomBound < 2) {
			return;
		}
		
		
		if (isVertical) {
			int x = randomizer.nextInt(rightBound - leftBound) + leftBound;
			int gapY = randomizer.nextInt(topBound - bottomBound) + bottomBound;

			for (int y = bottomBound; y < topBound; y++) {
				if (y != gapY) {
					grid[y][x] = 1;
				}
			}
			
			generateMaze(false, leftBound, x, bottomBound, gapY - 1);
			generateMaze(false, leftBound, x, gapY + 1, topBound);
			generateMaze(false, x, rightBound, bottomBound, gapY - 1);
			generateMaze(false, x, rightBound, gapY + 1, topBound);
			
		} else {
			int y = randomizer.nextInt(topBound - bottomBound) + bottomBound;
			int gapX = randomizer.nextInt(rightBound - leftBound) + leftBound;

			for (int x = leftBound; x < rightBound; x++) {
				if (x != gapX) {
					this.grid[y][x] = 1;
				}
			}
			
			generateMaze(true, leftBound, gapX - 1, y, topBound);
			generateMaze(true, gapX + 1, rightBound, y, topBound);
			generateMaze(true, leftBound, gapX - 1, bottomBound, y);
			generateMaze(true, gapX + 1, rightBound, bottomBound, y);
		}
	}
	
	
	public void generateEndpoints() {
		LinkedList<Integer> topAvailableIndexes = new LinkedList<Integer>();
		LinkedList<Integer> bottomAvailableIndexes = new LinkedList<Integer>();
		
		for (int i = 0; i < this.grid.length; i ++) {
			if (this.grid[1][i] == 0) {
				topAvailableIndexes.add(i);
			} 
			
			if (this.grid[this.grid.length - 2][i] == 0) {
				bottomAvailableIndexes.add(i);
			}
		}
		
		
		int entranceIndex = this.randomizer.nextInt(topAvailableIndexes.size() - 1);
		this.grid[0][topAvailableIndexes.get(entranceIndex)] = 2;
		
		int exitIndex = this.randomizer.nextInt(bottomAvailableIndexes.size() - 1);
		this.grid[this.grid.length - 1][bottomAvailableIndexes.get(exitIndex)] = 2;
	}
	
	
	public int[][] getGrid(){
		return this.grid;
	}
	
	
	public int getHeight() {
		return this.height;
	}
	
	
	public int getWidth() {
		return this.width;
	}

	
	@Override
	public String toString() {
		String outputString = "";

		for (int i = 0; i < this.grid.length; i++) {
			String subString = "";

			for (int x = 0; x < this.grid[i].length; x++) {
				subString += this.grid[i][x] + "|";
			}

			outputString += subString + "\n";
		}

		return outputString;
	}
}
