import javax.swing.JFrame;

public class Main extends JFrame{
	private static final long serialVersionUID = 1L;

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Maze Generator");
		setSize(550, 600);
		setResizable(false);
		
		Menu menu = new Menu();
		add(menu);
	}
	
	
	public static void main(String[] args) {
		Main app = new Main();
		app.setVisible(true);
	}
}
