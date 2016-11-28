package org.flag;
import java.awt.image.BufferedImage;


public class Flag
{
	private String country;
	private BufferedImage image;
	
	public Flag(String country, BufferedImage image)
	{
		this.country = country;
		this.image = image;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public String toString()
	{
		return country;
	}
}
