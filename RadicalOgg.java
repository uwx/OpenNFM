import java.io.IOException;

import org.newdawn.easyogg.*;

public class RadicalOgg {
	OggClip ogg;
	public RadicalOgg(String s) {
		try {
			ogg = new OggClip(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		ogg.loop();
	}
	
	public void stop() {
		ogg.pause();
	}
	
	public void unload() {
		ogg.stop();
		ogg.close();
	}
	
	public void resume() {
		ogg.resume();
	}
}
