import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.sound.sampled.UnsupportedAudioFileException;

import de.quippy.javamod.mixer.Mixer;
import de.quippy.javamod.multimedia.MultimediaContainer;
import de.quippy.javamod.multimedia.MultimediaContainerManager;
import de.quippy.javamod.multimedia.mod.ModContainer;
import de.quippy.javamod.system.Helpers;
import de.quippy.javamod.main.JavaModMainBase;
import de.quippy.javamod.system.Log;

public class RadicalIT {
	
    boolean loaded = false;
    boolean playing = false;
    String s;
    
	private static class PlayThread extends Thread
	{
		private Mixer currentMixer;
		private MultimediaContainer multimediaContainer;
		
		public PlayThread(URL modUrl)
		{
			super();

			try
			{
				Properties props = new Properties();
				props.setProperty(ModContainer.PROPERTY_PLAYER_ISP, "3");
				props.setProperty(ModContainer.PROPERTY_PLAYER_STEREO, "2");
				props.setProperty(ModContainer.PROPERTY_PLAYER_WIDESTEREOMIX, "FALSE");
				props.setProperty(ModContainer.PROPERTY_PLAYER_NOISEREDUCTION, "FALSE");
				props.setProperty(ModContainer.PROPERTY_PLAYER_NOLOOPS, "1");
				props.setProperty(ModContainer.PROPERTY_PLAYER_MEGABASS, "TRUE");
				props.setProperty(ModContainer.PROPERTY_PLAYER_BITSPERSAMPLE, "16");			
				props.setProperty(ModContainer.PROPERTY_PLAYER_FREQUENCY, "48000");
				props.setProperty(ModContainer.PROPERTY_PLAYER_MSBUFFERSIZE, "250");
				MultimediaContainerManager.configureContainer(props);
				multimediaContainer = MultimediaContainerManager.getMultimediaContainer(modUrl);
				
				
			} catch (UnsupportedAudioFileException e) {
				System.out.println("Error initializing JavaMod engine!");
				System.out.println("File " + modUrl + " is not supported.");
				e.printStackTrace();
				System.exit(3);
			}
		}
		
		
		@Override
		public void run()
		{
			currentMixer = multimediaContainer.createNewMixer();
			currentMixer.startPlayback();
		}
	}
	private static PlayThread playerThread;
    
	public RadicalIT(String fn)
    {
		loaded = true;
		
		try {
			Helpers.registerAllClasses();
	        URL modUrl = new File(fn).toURI().toURL();

	        playerThread = new PlayThread(modUrl);
	        
		} catch (ClassNotFoundException e) {
			System.out.println("Error initializing JavaMod engine!");
			System.out.println("The JavaMod library is missing.");
			e.printStackTrace();
			System.exit(3);
		} catch (MalformedURLException e) {
			System.out.println("Error initializing JavaMod engine!");
			System.out.println("Bad file name " + fn + " is causing errors.");
			e.printStackTrace();
			System.exit(3);
		}
    }
	
	public void playIT()
	{
		playerThread.start();
		playing = true;
	}
	
	public void stopIT()
	{
		playing = false;
		playerThread.suspend();
	}
	
	public void unloadIT()
	{
		loaded = false;
		playerThread.stop();
		//playerThread = null;
	}
	
	public void resumeIT()
	{
		playing = true;
		playerThread.resume();
	}
}
