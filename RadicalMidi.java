//package com.javacodegeeks.snippets.desktop;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import javazoom.jl.player.advanced.*;

public class RadicalMidi {

    InputStream is;
    Sequencer sequencer;
    boolean loaded = false;
    boolean playing = false;
    boolean isMp3 = false;
    String s;
    InputStream fi;
    File fl;

    PausablePlayer player;
	String filePath;
	private int pausedOnFrame;
	//private Mp3Player Mp3Player;
	
	public class Mp3Player extends Thread {
		
		
	    
	    @Override
	    public void run() {
		    //playMp3();
	    }
	}
    
    public RadicalMidi(String fn)
    {
    	if (fn.endsWith(".mp3")) {
        	pausedOnFrame = 0;
    		isMp3 = true;
    		fl = new File(fn);
    		try {
    			fi = new FileInputStream(fl);
    			player = new PausablePlayer(fi);
    		} catch (JavaLayerException | FileNotFoundException ex) {
    			System.out.println("Error loading Mp3!");
    			ex.printStackTrace();
    		}
    	} else if (fn.endsWith(".ogg")) {
    		// TODO add .ogg code
    	} else {
    		isMp3 = false;
	    	s = fn;
	    	try {
	    	fi = new FileInputStream(new File(fn));
	    	} catch(java.io.FileNotFoundException ex) {
	    		System.out.println("Midi file not found!");
	    		ex.printStackTrace();
	    	}
	    	try {
	    	// Obtains the default Sequencer connected to a default device.
	    	sequencer = MidiSystem.getSequencer();
	
	    	// Opens the device, indicating that it should now acquire any
	    	// system resources it requires and become operational.
	    	sequencer.open();
	
	    	} catch (Exception ex) {
	    		System.out.println("Error loading Midi file:");
	    		ex.printStackTrace();
	    	}
    	}
    }
    
    public void load() {
    	if (isMp3) {
    		initMp3();
    		//Mp3Player = new Mp3Player(s);
    	} else
    		loadMidi();
    }
    
    public void play() {
    	if (isMp3) {
    		/*
    		if (Mp3Player != null)
    			Mp3Player.start();
    		else {
    			Mp3Player = new Mp3Player(s);
    			Mp3Player.start();
    		}
    		*/
    		playMp3();
    	}
    	else
    		playMidi();
    }
    
    //@SuppressWarnings("deprecation")
	public void resume() {
    	if (isMp3)
    		//Mp3Player.resume();
    		resumeMp3();
    	else
    		resumeMidi();
    }
    
    //@SuppressWarnings("deprecation")
    public void stop() {
    	if (isMp3)
    		//Mp3Player.suspend();
    		stopMp3();
    	else
    		stopMidi();
    }
    
    //@SuppressWarnings("deprecation")
	public void unload() {
    	if (isMp3) {
    		//Mp3Player.closeMp3();
    		//Mp3Player.stop();
    		//Mp3Player = null;
    		closeMp3();
    	}
    	else
    		unloadMidi();
    }
    
    public void loadMidi()
    {
    	try {
    		// create a stream from a file
    		is = new BufferedInputStream(fi);
    	
    		loaded = true;
    		
    	} catch (Exception ex) {
    		System.out.println("Error buffering Midi file:");
    		ex.printStackTrace();
    	}
    }
    
    public void resumeMidi(double gain, int loops) {
    	try {
        	fi = new FileInputStream(new File(s));
        	} catch(java.io.FileNotFoundException ex) {
        		System.out.println("Midi file not found!");
        		ex.printStackTrace();
        }
    	try {
    		// create a stream from a file
    		is = new BufferedInputStream(fi);
    	} catch (Exception ex) {
    		System.out.println("Error buffering Midi file:");
    		ex.printStackTrace();
    	}
    	playMidi(gain, loops);
    }
    
    public void resumeMidi(double gain) {
    	try {
        	fi = new FileInputStream(new File(s));
        	} catch(java.io.FileNotFoundException ex) {
        		System.out.println("Midi file not found!");
        		ex.printStackTrace();
        }
    	try {
    		// create a stream from a file
    		is = new BufferedInputStream(fi);
    	} catch (Exception ex) {
    		System.out.println("Error buffering Midi file:");
    		ex.printStackTrace();
    	}
    	playMidi(gain);
    }
    
    public void resumeMidi() {
    	try {
        	fi = new FileInputStream(new File(s));
        	} catch(java.io.FileNotFoundException ex) {
        		System.out.println("Midi file not found!");
        		ex.printStackTrace();
        }
    	try {
    		// create a stream from a file
    		is = new BufferedInputStream(fi);
    	} catch (Exception ex) {
    		System.out.println("Error buffering Midi file:");
    		ex.printStackTrace();
    	}
    	playMidi();
    }
    
    public void playMidi(double gain, int loops) {

		try {
			// Sets the current sequence on which the sequencer operates.
			// The stream must point to MIDI file data.
			sequencer.setSequence(is);

			// loop forever
			sequencer.setLoopCount(loops);
			
			if (sequencer instanceof Synthesizer) {
			      Synthesizer synthesizer = (Synthesizer) sequencer;
			      MidiChannel[] channels = synthesizer.getChannels();

			      // gain is a value between 0 and 1 (loudest)
			      for (int i = 0; i < channels.length; i++) {
			    	  channels[i].controlChange(7, (int) (gain * 127.0));
			      }
			}
			
	    	// Starts playback of the MIDI data in the currently loaded sequence.
	    	sequencer.start();
	    
	    	playing = true;
		} catch(IllegalArgumentException ex) {
			System.out.println("There is a mistake in your Midi code,");
			System.out.println("please re-check!");
			ex.printStackTrace();
		} catch (java.lang.IllegalStateException ex) {
    		System.out.println("Error playing Midi file " + s + ", check if the file exists!");
    		ex.printStackTrace();
    	} catch (Exception ex) {
    		System.out.println("Error playing Midi file:");
    		ex.printStackTrace();
    	}
	}
    
    public void playMidi(double gain) {

		try {
			// Sets the current sequence on which the sequencer operates.
			// The stream must point to MIDI file data.
			sequencer.setSequence(is);

			// loop forever
			sequencer.setLoopCount(9999);
			
			if (sequencer instanceof Synthesizer) {
			      Synthesizer synthesizer = (Synthesizer) sequencer;
			      MidiChannel[] channels = synthesizer.getChannels();

			      // gain is a value between 0 and 1 (loudest)
			      for (int i = 0; i < channels.length; i++) {
			    	  channels[i].controlChange(7, (int) (gain * 127.0));
			      }
			}

	    	// Starts playback of the MIDI data in the currently loaded sequence.
	    	sequencer.start();
	    
	    	playing = true;
		} catch(IllegalArgumentException ex) {
			System.out.println("There is a mistake in your Midi code,");
			System.out.println("please re-check!");
			ex.printStackTrace();
		} catch (java.lang.IllegalStateException ex) {
    		System.out.println("Error playing Midi file " + s + ", check if the file exists!");
    		ex.printStackTrace();
    	} catch (Exception ex) {
    		System.out.println("Error playing Midi file:");
    		ex.printStackTrace();
    	}
	}
	
	public void playMidi() {

		try {
			// Sets the current sequence on which the sequencer operates.
			// The stream must point to MIDI file data.
			sequencer.setSequence(is);

			// loop forever
			sequencer.setLoopCount(9999);
			
	    	// Starts playback of the MIDI data in the currently loaded sequence.
	    	sequencer.start();
	    
	    	playing = true;
		} catch(IllegalArgumentException ex) {
			System.out.println("There is a mistake in your Midi code,");
			System.out.println("please re-check!");
			ex.printStackTrace();
		} catch (java.lang.IllegalStateException ex) {
    		System.out.println("Error playing Midi file " + s + ", check if the file exists!");
    		ex.printStackTrace();
    	} catch (Exception ex) {
    		System.out.println("Error playing Midi file:");
    		ex.printStackTrace();
    	}
	}
	
	public void stopMidi() {
		System.out.println("Stopping Midi file...");
		try {
			sequencer.stop();
			playing = false;
		} catch (Exception ex) {
			System.out.println("Error stopping Midi file:");
			ex.printStackTrace();
		}
	}
	
	public void unloadMidi() {
		try {
			is.close();
			loaded = false;
		} catch (Exception ex) {
			System.out.println("Error unloading Midi file:");
			ex.printStackTrace();
		}
	}
	
	public void initMp3() {
		
	}
	
	public void playMp3() { //// http://stackoverflow.com/a/16893482
		try {
			player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
    }
    
    public void resumeMp3() { //// http://stackoverflow.com/a/16893482
    	player.resume();
    }
    
    public void stopMp3() { // stops, but notifies the pause thingy
    	player.pause();
    }
    
    public void closeMp3() { //stops but doesn't notify shit
    	player.close();
    }

}