package org.flag;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import org.MainFrame;


public class FlagLoader extends SwingWorker<List<Flag>, Integer>
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
		final int NUM_FLAGS = files.length;
		
		for(File f : files)
		{
			final String NAME = f.getName();
			try
			{
				flags.add(new Flag(NAME.replaceAll("\\..*", ""), ImageIO.read(f)));
				System.out.println("Successfully loaded flag from " + NAME);
				publish((int)Math.round(((double)flags.size() / NUM_FLAGS) * 100));
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
	public void done()
	{
		System.out.println("FLAG LOADER DONE!");
		try
		{
			List<Flag> flags = get();
			for(Flag f : flags)
				frame.addFlag(f);
		}
		catch(InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
			System.out.println("get() failed in FlagLoader");
		}
	}

}
