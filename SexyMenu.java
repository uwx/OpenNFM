import java.applet.Applet;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class SexyMenu extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1337L;
	
	MenuBar menubar;
	Menu menuGadgets;
	Menu menuAbout;
	Frame frame;
	GameSparker gamesparker;
	RunApp runapp;
	Settings settings;
	
	public SexyMenu(Frame frame, GameSparker gamesparker, RunApp runapp) {
		this.frame = frame;
		this.gamesparker = gamesparker;
		this.runapp = runapp;
	}
	
	public void initSettings(Settings settings) {
		this.settings = settings;
	}

	public void initMenu() {
		menubar = new MenuBar();
        menuGadgets = new Menu("Settings");
        menuAbout = new Menu("OpenNFM by rafa1231518 & Kaffeinated, special thanks to all contributors!");
        MenuItem aboutOmar = new MenuItem("Need For Madness 2 created by Omar Waly, copyright (c) 2010-2015 Radical Play");
        menuAbout.add(aboutOmar);
        
		menubar.add(menuGadgets);
		menubar.add(menuAbout);
		frame.setMenuBar(menubar);
	}
	
	public void initSpeedoMenu() {
		Menu menuSpeedo = new Menu("Speedometer");
        MenuItem menuItem = new MenuItem("Set MPH");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
            	settings.updateSetting("isMph", true);
            	System.out.println("Speedometer set to MPH");
            }

        }
);
        menuSpeedo.add(menuItem);
        menuItem = new MenuItem("Set KPH");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
            	settings.updateSetting("isMph", false);
            	System.out.println("Speedometer set to KPH");
            }

        }
);
        menuSpeedo.add(menuItem);
        menuGadgets.add(menuSpeedo);
	}
	
	public void resetMenu()
	{
		frame.setMenuBar(menubar);
	}
	
	
	
}
