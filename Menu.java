import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;




class ButtonListener implements ActionListener{
	private JTextField gridSizeInput;
	private JTextField squareSizeInput;
	
	public ButtonListener(JTextField gridSizeInput, JTextField squareSizeInput) {
		this.gridSizeInput = gridSizeInput;
		this.squareSizeInput = squareSizeInput;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String gridSize = this.gridSizeInput.getText();
		String squareSize = this.squareSizeInput.getText();
		
		int gridSizeNumber;
		int squareSizeNumber;
		
		
		if (!gridSize.equals("") && !squareSize.equals("")) {
			gridSizeNumber = Integer.parseInt(gridSize);
			squareSizeNumber = Integer.parseInt(squareSize);
		}else {
			return;
		}
		
		
		MazeGenerator maze = new MazeGenerator(gridSizeNumber, gridSizeNumber);
		maze.generateMaze(false, 1, gridSizeNumber - 1, 1, gridSizeNumber - 1);
		maze.generateEndpoints();
		int[][] mazeGrid = maze.getGrid();
		
		JFrame mazeFrame = new JFrame();
		mazeFrame.setTitle("Generated Maze");
		mazeFrame.setSize((gridSizeNumber + 10) * squareSizeNumber, (gridSizeNumber + 10) * squareSizeNumber);
		mazeFrame.setResizable(false);
		
		DrawingBoard drawingPanel = new DrawingBoard(mazeGrid, squareSizeNumber);
		mazeFrame.add(drawingPanel);
		
		mazeFrame.setVisible(true);
	}
}



public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	
	
	public Font inputFont = new Font("Arial", Font.PLAIN, 20);
	
	public Menu() {
		setBackground(Color.black);
		setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Maze Generator", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setForeground(Color.white);
		add(title, BorderLayout.NORTH);
		
		JPanel inputsSection = new JPanel();
		inputsSection.setLayout(null);
		inputsSection.setBackground(Color.black);
		add(inputsSection, BorderLayout.CENTER);
		
		JLabel gridSizeLbl = new JLabel("Grid Size: ");
		gridSizeLbl.setForeground(Color.white);
		gridSizeLbl.setFont(inputFont);
		gridSizeLbl.setBounds(100, 100, 100, 50);
		inputsSection.add(gridSizeLbl);
		
		JTextField gridSize = new JTextField(2);
		gridSize.setBackground(Color.white);
		gridSize.setFont(inputFont);
		gridSize.setBounds(320, 105, 100, 30);
		inputsSection.add(gridSize);
		
		JLabel squareSizeLbl = new JLabel("Square Size: ");
		squareSizeLbl.setForeground(Color.white);
		squareSizeLbl.setFont(inputFont);
		squareSizeLbl.setBounds(100, 200, 130, 50);
		inputsSection.add(squareSizeLbl);
		
		JTextField squareSize = new JTextField(2);
		squareSize.setBackground(Color.white);
		squareSize.setFont(inputFont);
		squareSize.setBounds(320, 205, 100, 30);
		inputsSection.add(squareSize);
		
		
		JButton generateButton = new JButton("Generate Maze");
		generateButton.setBackground(Color.gray);
		generateButton.setForeground(Color.white);
		generateButton.setFont(this.inputFont);
		generateButton.setBounds(180, 420, 200, 50);
		generateButton.addActionListener(new ButtonListener(gridSize, squareSize));
		inputsSection.add(generateButton);
	}
}
