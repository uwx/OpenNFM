import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public SexyMenu(final Frame frame, final GameSparker gamesparker, final RunApp runapp) {
		this.frame = frame;
		this.gamesparker = gamesparker;
		this.runapp = runapp;
	}

	public void initSettings(final Settings settings) {
		this.settings = settings;
	}

	public void initMenu() {
		menubar = new MenuBar();
		menuGadgets = new Menu("Settings");
		menuAbout = new Menu("OpenNFM by rafa1231518 & Kaffeinated, special thanks to all contributors!");
		final MenuItem aboutOmar = new MenuItem(
				"Need For Madness 2 created by Omar Waly, copyright (c) 2010-2015 Radical Play");
		menuAbout.add(aboutOmar);

		menubar.add(menuGadgets);
		menubar.add(menuAbout);
		frame.setMenuBar(menubar);
	}

	public void initSpeedoMenu() {
		final Menu menuSpeedo = new Menu("Speedometer");
		MenuItem menuItem = new MenuItem("Set MPH");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				settings.updateSetting("isMph", true);
				System.out.println("Speedometer set to MPH");
			}

		});
		menuSpeedo.add(menuItem);
		menuItem = new MenuItem("Set KPH");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				settings.updateSetting("isMph", false);
				System.out.println("Speedometer set to KPH");
			}

		});
		menuSpeedo.add(menuItem);
		menuGadgets.add(menuSpeedo);
	}

	public void resetMenu() {
		frame.setMenuBar(menubar);
	}

}
