import javax.swing.JFrame;
public class Snake {

private JFrame snake = new JFrame();

public Snake(int difficulty) {
	
	snake.add(new Background(difficulty));
	snake.setVisible(true);
	snake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	snake.setTitle("snake");
	snake.setResizable(false);
	snake.pack();
	
}
}
