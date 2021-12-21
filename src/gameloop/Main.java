package gameloop;

import gameWorld.GameWorld;
import gameobjects.Hero;
import libraries.Keybinding;
import libraries.StdDraw;
import libraries.Timer;
import resources.Controls;
import resources.DisplaySettings;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

//SOUND imports
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*; 



public class Main
{
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		// Hero, world and display initialisation.
		Hero isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM, HeroInfos.ISAAC_SIZE, HeroInfos.ISAAC_SPEED, ImagePaths.ISAAC, 1, 10);
		GameWorld world = new GameWorld(isaac);				
		initializeDisplay();
		
		//SOUND
		File sound = new File("sounds/backgroundMusic.wav");
		AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
		Clip clip = AudioSystem.getClip();
		clip.open(audio);

	
		// Main loop of the game - remove the comment on 'clip.start' to enjoy a background music
		while (!world.gameOver())
		{
			processNextStep(world);
			//clip.start();
		}
	}

	private static void processNextStep(GameWorld world)
	{
		Timer.beginTimer();
		StdDraw.clear();
		world.processUserInput();
		world.updateGameObjects();
		world.drawGameObjects();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
	}

	private static void initializeDisplay()
	{
		// Set the window's size, in pixels.
		// It is strongly recommended to keep a square window.
		StdDraw.setCanvasSize(RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE,
				RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE);

		// Enables double-buffering.
		// https://en.wikipedia.org/wiki/Multiple_buffering#Double_buffering_in_computer_graphics
		StdDraw.enableDoubleBuffering();
	}
}
