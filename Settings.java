
public class Settings {

	GameSparker gamesparker;
	public boolean isMph;
	
	public Settings(GameSparker gamesparker)
	{
		this.gamesparker = gamesparker;
		try {
			int a = gamesparker.readcookie("ismph");
			if (a == 1)
				isMph = true;
			else if (a == 0)
				isMph = false;
			else
				throw new Exception();
		} catch (Exception ex) {
			isMph = false;
			gamesparker.savecookie("ismph", "0");
		}
		
	}
	
	public void updateSetting(String setting, boolean value)
	{
		switch(setting) {
		default:
			System.out.println("Malformed setting!");
		case "isMph":
			isMph = value;
			gamesparker.savecookie(setting, "" + value);
		}
	}
	
	public void updateSetting(String setting, int value)
	{
		switch(setting) {
		default:
			System.out.println("Malformed setting!");
		}
	}
	
}
