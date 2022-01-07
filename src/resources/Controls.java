package resources;

import libraries.Keybinding;

public class Controls
{
	public static int goUp = Keybinding.keycodeOf('z');
	public static int goDown = Keybinding.keycodeOf('s');
	public static int goRight = Keybinding.keycodeOf('d');
	public static int goLeft = Keybinding.keycodeOf('q');
	
	//cheat controls
	public static int cheatKill = Keybinding.keycodeOf('k');
	public static int cheatSpeed = Keybinding.keycodeOf('l');
	public static int cheatMoney = Keybinding.keycodeOf('o');
	public static int cheatDamage = Keybinding.keycodeOf('p');
	public static int invincible = Keybinding.keycodeOf('i');
	
	//public static int startAudio = Keybinding.keycodeOf('m');
	
	// New controls for shooting option
	public static int shootUp = Keybinding.keycodeOf(Keybinding.SpecialKeys.UP);
	public static int shootDown = Keybinding.keycodeOf(Keybinding.SpecialKeys.DOWN);
	public static int shootRight = Keybinding.keycodeOf(Keybinding.SpecialKeys.RIGHT);
	public static int shootLeft = Keybinding.keycodeOf(Keybinding.SpecialKeys.LEFT);
	
}
