
public class Settings {

	GameSparker gamesparker;
	public boolean isMph;

	public Settings(final GameSparker gamesparker) {
		this.gamesparker = gamesparker;
		try {
			final int a = gamesparker.readcookie("ismph");
			if (a == 1)
				isMph = true;
			else if (a == 0)
				isMph = false;
			else
				throw new Exception();
		} catch (final Exception ex) {
			isMph = false;
			gamesparker.savecookie("ismph", "0");
		}

	}

	public void updateSetting(final String setting, final boolean value) {
		switch (setting) {
		default:
			System.out.println("Malformed setting!");
		case "isMph":
			isMph = value;
			gamesparker.savecookie(setting, "" + value);
		}
	}

	public void updateSetting(final String setting, final int value) {
		switch (setting) {
		default:
			System.out.println("Malformed setting!");
		}
	}

}
