package tw.org.iii.classes;

import javax.swing.JFrame;


public class snakejava {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(450,200, 850, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		SnakePanel panel = new SnakePanel();
		frame.add(panel);
		
		frame.setVisible(true);
	}
	
} 