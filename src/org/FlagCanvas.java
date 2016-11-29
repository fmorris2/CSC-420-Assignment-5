package org;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import org.flag.Flag;

public class FlagCanvas extends JPanel
{
	private static final long serialVersionUID = 6125063081933276396L;
	private static final int CROSS_D = 12; //needs to be even
	private static final int HORIZ_OFFSET = 4;
	private static final int VERTICAL_OFFSET = 6;
	
	private int flagX, flagY, crosshairX, crosshairY;
	private int flagWidth, flagHeight;
	private int horizontalSlider, verticalSlider;
	private MainFrame frame;
	
	public FlagCanvas(MainFrame frame)
	{
		this.frame = frame;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//set appropriate flag dimensions based on canvas size
		flagWidth = getWidth() / 2;
		flagHeight = getHeight() / 2;
		calculateX();
		calculateY();
		
		Flag f = frame.getCurrentFlag();
		if(f == null) //draw crosshairs
		{
			g.drawLine(crosshairX, 0, crosshairX, getHeight()); //Vertical line
			g.drawLine(0, crosshairY, getWidth(), crosshairY); //Horizontal line
			g.drawOval(crosshairX - CROSS_D / 2, crosshairY - CROSS_D / 2, CROSS_D, CROSS_D);
		}
		else if(flagWidth > 0 && flagHeight > 0)
		{
			Image scaledImage = f.getImage().getScaledInstance(flagWidth, flagHeight, Image.SCALE_SMOOTH);
			g.drawImage(scaledImage, flagX, flagY, null);
		}
	}
	
	public void calculateX()
	{
		double percent = horizontalSlider / 100.0;
		System.out.println(horizontalSlider);
		
		//x = (width of canvas * percent slider is at) - (circle radius)
		crosshairX = (int)Math.round(getWidth() * percent) + ((horizontalSlider > 35) ? (int)(percent * 100 / 9): (int)(percent * 100 / -6));
		flagX = crosshairX - (flagWidth / 2);
	}
	
	public void calculateY()
	{
		double percent = Math.abs(verticalSlider - 100) / 100.0;
		
		//y = (height of canvas * percent slider is at) - (circle radius)
		crosshairY = (int)Math.round(getHeight() * percent) + 6;
		flagY = crosshairY - (flagHeight / 2);
	}
	
	public void setHorizontalSlider(int i)
	{
		horizontalSlider = i;
	}
	
	public void setVerticalSlider(int i)
	{
		verticalSlider = i;
	}
}
