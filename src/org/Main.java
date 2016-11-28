package org;
import java.awt.EventQueue;

public class Main
{
	private static MainFrame frame;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> {createFrame();});
	}
	
	private static void createFrame()
	{
		frame = new MainFrame();
		frame.setVisible(true);
	}

}
