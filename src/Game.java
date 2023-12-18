import java.awt.Dimension;
import javax.swing.*; //contains window creator JFrame
public class Game {

	public static void main(String[] args) throws Exception {
		int width=600;
		int height=width;
		
		JFrame frame=new JFrame("Snake");
		frame.setVisible(true);
		frame.setSize(width,height);
		frame.setLocationRelativeTo(null);	
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(600,600));		
		
		SnakeGame snakeGame=new SnakeGame(width,height);
		frame.add(snakeGame);
//		frame.getContentPane().
		frame.pack(); //actual size of the game space
		snakeGame.requestFocus(); // to focus on the game...i.e,like cursor		
	}

}
