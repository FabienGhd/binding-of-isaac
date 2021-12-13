package libraries;

public class Keybinding
{
	public static enum SpecialKeys
	{
		RETURN, LEFT, UP, RIGHT, DOWN, MAJ, ESCAPE;
	}

	/**
	 * Get the keycode of the special keys. Useful for interacting with StdDraw.
	 * 
	 * @param key
	 * @return
	 */
	public static int keycodeOf(SpecialKeys key)
	{
		switch (key)
		{
		case RETURN:
			return 10;
		case LEFT:
			return 37;
		case UP:
			return 38;
		case RIGHT:
			return 39;
		case DOWN:
			return 40;
		case MAJ:
			return 16;
		case ESCAPE:
			return 27;
		}
		return -1;
	}

	public static int keycodeOf(char character)
	{
		return (int) character - 32;
	}
}
