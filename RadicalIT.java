import java.io.File;

import ozmod.*;

public class RadicalIT {
	
    boolean loaded = false;
    boolean playing = false;
    String s;
    ITPlayer itplayer;
    OZMod ozm = new OZMod();
    
	public RadicalIT(String fn)
    {
        ozm.initOutput();
		s = fn;
		itplayer = new ITPlayer();
		itplayer.load(new PipeIn(new File(fn),PipeIn.LITTLEENDIAN));
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
		ozm.initOutput();
		itplayer = new ITPlayer();
		itplayer.load(new PipeIn(new File(s),PipeIn.LITTLEENDIAN));
		itplayer.play();
		loaded = true;
		playing = true;
	}
}
