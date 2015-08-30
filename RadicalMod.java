import java.applet.Applet;
import java.net.URL;
import ds.nfm.*;

public class RadicalMod
{

 public RadicalMod()
 {
     playing = false;
     loaded = 0;
     rvol = 0;
     imod = "";
     pmod = "";
     loaded = 0;
     nonempty = false;
     System.gc();
 }

 public RadicalMod(String string, int i, int i_0, int i_1, boolean bool, boolean bool_2)
 {
     playing = false;
     loaded = 0;
     rvol = 0;
     imod = "";
     pmod = "";
     int i_3 = 22000;
     i_0 = (int)(((float)i_0 / 8000F) * 2.0F * (float)i_3);
     i = (int)((float)i * 0.8F);
     filename = string.replace("mystages/mymusic/", "");
     nonempty = true;
     try
     {
         Module module;
         if(!bool_2)
         {
             module = ModuleLoader.loadMod(string);
         } else
         {
             string = string.replace(' ', '_');
             URL url = new URL((new StringBuilder()).append("http://multiplayer.needformadness.com/tracks/music/").append(string).append(".zip").toString());
             module = ModuleLoader.loadMod(url);
         }
         if(module.isLoaded())
         {
             name = module.getName();
             if(name.trim().equals(""))
                 name = "Untitled";
             ModuleSlayer moduleslayer = ModuleLoader.prepareSlayer(module, i_0, i, i_1);
             byte is[] = moduleslayer.turnbytesNorm(bool);
             if(bool)
                 rvol = moduleslayer.olav;
             sClip = new SuperClip(is, moduleslayer.oln, i_3);
             //sClip.rollBackPos = moduleslayer.rollBackPos;
             //sClip.rollBackTrig = moduleslayer.oln - moduleslayer.rollBackTrig;
             if(bool_2)
                 filename = (new StringBuilder()).append("Length: ").append(getTimer(sClip.stream.available() / 44100)).toString();
             loaded = 2;
         }
     }
     catch(Exception exception)
     {
         exception.printStackTrace();
         System.out.println((new StringBuilder()).append("Error downloading and making Mod: ").append(exception.toString()).toString());
         loaded = 0;
         nonempty = false;
     }
     System.runFinalization();
     System.gc();
 }

 public RadicalMod(String string, Applet applet)
 {
     playing = false;
     loaded = 0;
     rvol = 0;
     imod = "";
     pmod = "";
     loaded = 1;
     imod = string;
     filename = string;
     nonempty = true;
 }

 String getTimer(int secs)
 {
     int mins = secs / 60;
     secs %= 60;
     if(secs >= 10)
         return (new StringBuilder()).append(mins).append(":").append(secs).toString();
     else
         return (new StringBuilder()).append(mins).append(":0").append(secs).toString();
 }
 
 public void loadMod(int i, int j, int k, boolean b1, boolean b2)
 {
	 //stracks[16].loadMod(400, 7600, 125, sunny, macn);
	 loadimod(i, j, k, b1, b2);
 }

 public void loadimod(int i, int j /* ignored */, int i_7, boolean bool, boolean b2)
 {
     if(loaded == 1)
     {
         int i2 = 44000;
         int i_6 = 160;
         if(bool)
             i_6 = 300;
         try
         {
             Module module = ModuleLoader.loadMod(imod);
             if(module.isLoaded())
             {
                 name = module.getName();
                 if(name.trim().equals(""))
                     name = "Untitled";
                 ModuleSlayer moduleslayer = ModuleLoader.prepareSlayer(module, i2, i_6, i_7);
                 byte is[] = moduleslayer.turnbytesNorm(bool);
                 if(bool)
                     rvol = moduleslayer.olav;
                 sClip = new SuperClip(is, moduleslayer.oln, 22000);
                 //sClip.rollBackPos = moduleslayer.rollBackPos;
                 //sClip.rollBackTrig = moduleslayer.oln - moduleslayer.rollBackTrig;
                 loaded = 2;
             }
         }
         catch(Exception exception)
         {
             System.out.println((new StringBuilder()).append("Error making a imod: ").append(exception.toString()).toString());
             loaded = 0;
             nonempty = false;
         }
         System.runFinalization();
         System.gc();
     }
 }
 
 public void loadimod(boolean bool)
 {
     if(loaded == 1)
     {
         int refreshRate = 44000;
         int i_6 = 160;
         if(bool)
             i_6 = 300;
         int i_7 = 125;
         try
         {
             Module module = ModuleLoader.loadMod(imod);
             if(module.isLoaded())
             {
                 name = module.getName();
                 if(name.trim().equals(""))
                     name = "Untitled";
                 ModuleSlayer moduleslayer = ModuleLoader.prepareSlayer(module, refreshRate, i_6, i_7);
                 byte is[] = moduleslayer.turnbytesNorm(bool);
                 if(bool)
                     rvol = moduleslayer.olav;
                 sClip = new SuperClip(is, moduleslayer.oln, 22000);
                 //sClip.rollBackPos = moduleslayer.rollBackPos;
                 //sClip.rollBackTrig = moduleslayer.oln - moduleslayer.rollBackTrig;
                 loaded = 2;
             }
         }
         catch(Exception exception)
         {
             System.out.println((new StringBuilder()).append("Error making a imod: ").append(exception.toString()).toString());
             loaded = 0;
             nonempty = false;
         }
         System.runFinalization();
         System.gc();
     }
 }

 public void loadpmod(boolean bool)
 {
     if(loaded == 1)
     {
         int i = 44000;
         int i_10 = 160;
         if(bool)
             i_10 = 300;
         int i_11 = 125;
         try
         {
             Module module = ModuleLoader.loadMod(pmod);
             if(module.isLoaded())
             {
                 name = module.getName();
                 if(name.trim().equals(""))
                     name = "Untitled";
                 ModuleSlayer moduleslayer = ModuleLoader.prepareSlayer(module, i, i_10, i_11);
                 byte is[] = moduleslayer.turnbytesNorm(bool);
                 if(bool)
                     rvol = moduleslayer.olav;
                 sClip = new SuperClip(is, moduleslayer.oln, 22000);
                 //sClip.rollBackPos = moduleslayer.rollBackPos;
                 //sClip.rollBackTrig = moduleslayer.oln - moduleslayer.rollBackTrig;
                 loaded = 2;
             }
         }
         catch(Exception exception)
         {
             System.out.println((new StringBuilder()).append("Error making a imod: ").append(exception.toString()).toString());
             loaded = 0;
             nonempty = false;
         }
         System.runFinalization();
         System.gc();
     }
 }

 public RadicalMod(String string, boolean bool)
 {
     playing = false;
     loaded = 0;
     rvol = 0;
     imod = "";
     pmod = "";
     loaded = 1;
     //pmod = string;
     //loadpmod(true);
     filename = string;
     nonempty = true;
 }

 public void play()
 {
     if(!playing && loaded == 2)
     {
         sClip.play();
         if(sClip.stoped == 0)
             playing = true;
     }
 }

 public void resume()
 {
     if(!playing && loaded == 2)
     {
         sClip.resume();
         if(sClip.stoped == 0)
             playing = true;
     }
 }

 public void stop()
 {
     if(playing && loaded == 2)
     {
         sClip.stop();
         playing = false;
     }
 }

 protected void unloadimod()
 {
     if(loaded == 2)
     {
         if(playing)
         {
             sClip.stop();
             playing = false;
         }
         try
         {
             sClip.close();
             sClip = null;
         }
         catch(Exception exception) { }
         System.gc();
         loaded = 1;
     }
 }
 
 public void unloadMod()
 {
	 unloadimod();
 }
 
 public void unloadAll() //?????
 {
	 unloadimod();
 }

 protected void unload()
 {
     if(playing && loaded == 2)
     {
         sClip.stop();
         playing = false;
     }
     try
     {
         sClip.close();
         sClip = null;
     }
     catch(Exception exception) { }
     try
     {
         imod = null;
     }
     catch(Exception exception) { }
     System.gc();
     loaded = 0;
 }

 static String name = "";
 static String filename = "";
 static boolean nonempty = false;
 SuperClip sClip;
 boolean playing;
 int loaded;
 int rvol;
 String imod;
 String pmod;

}
