package HanlonSim;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class Window{
	public JFrame frame;
	public JLayeredPane lpane = new JLayeredPane();
	Window(int width, int height, String title, Game game)
	{
		frame = new JFrame(title);	
		frame.setSize(new Dimension(width,height));
		frame.add(game);
		frame.addKeyListener(game);
		frame.setFocusable(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
