import java.io.File;

import ozmod.ITPlayer;
import ozmod.PipeIn;

public class RadicalIT {
	
    boolean loaded = false;
    boolean playing = false;
    String s;
    ITPlayer itplayer;
    
	public RadicalIT(String fn)
    {
		s = fn;
		itplayer = new ITPlayer();
		itplayer.load(new PipeIn(new File(fn),1));
		loaded = true;
    }
	
	public void playIT(boolean loop)
	{
		itplayer.setLoopable(loop);
		itplayer.play();
		playing = true;
	}
	
	public void playIT()
	{
		itplayer.setLoopable(true);
		itplayer.play();
		playing = true;
	}
	
	public void stopIT() //also, unloads
	{
		itplayer.done();
		loaded = false;
		playing = false;
	}
	
	public void resumeIT()
	{
		itplayer.load(new PipeIn(new File(s),1));
		itplayer.play();
		loaded = true;
		playing = true;
	}
}
