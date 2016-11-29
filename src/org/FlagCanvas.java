package org;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class FlagCanvas extends Canvas
{
	private static final long serialVersionUID = 6125063081933276396L;
	private static final int CIRCLE_DIAMETER = 30;

	private int circleX, circleY;
	
	public void paint(Graphics g)
	{
		
	}
	
	public void calculateX(int sliderVal)
	{
		double percent = sliderVal / 100.0;
		
		//x = (width of canvas * percent slider is at) - (circle radius)
		circleX = (int)Math.round(getWidth() * percent) - (CIRCLE_DIAMETER / 2);
	}
	
	public void calculateY(int sliderVal)
	{
		double percent = Math.abs(sliderVal - 100) / 100.0;
		
		//y = (height of canvas * percent slider is at) - (circle radius)
		circleY = (int)Math.round(getHeight() * percent) - (CIRCLE_DIAMETER / 2);
	}
}
