package org.flag;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import org.MainFrame;


public class FlagLoader extends SwingWorker<List<Flag>, Flag>
{
	private static final String IMAGE_DIRECTORY = "pics";
	
	private MainFrame frame;
	
	public FlagLoader(MainFrame frame)
	{
		this.frame = frame;
	}
	
	@Override
	protected List<Flag> doInBackground() throws Exception
	{
		File dir = new File(IMAGE_DIRECTORY);
		List<Flag> flags = new ArrayList<>();
		
		//check if directory doesn't exist
		if(!dir.exists())
		{
			System.out.println("Could not find directory '" + IMAGE_DIRECTORY + "' to load images from");
			return flags;
		}
		
		//iterate through all files in the directory, and create a Flag object for each
		File[] files = dir.listFiles();
		
		for(File f : files)
		{
			final String NAME = f.getName();
			try
			{
				publish(new Flag(NAME.replaceAll("\\..*", ""), ImageIO.read(f)));
			}
			catch(IOException e)
			{
				e.printStackTrace();
				System.out.println("FlagLoader had problems processing file with name " + NAME);
			}
		}
		
		return flags;
	}
	
	@Override
	protected void process(List<Flag> chunks)
	{
		frame.addFlag(chunks.get(chunks.size() - 1));
	}
	
	@Override
	public void done()
	{
		System.out.println("FLAG LOADER DONE!");
	}

}
