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
		Hero isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM, HeroInfos.ISAAC_SIZE, HeroInfos.ISAAC_SPEED, 
				ImagePaths.ISAAC, HeroInfos.HEALTH, HeroInfos.COINS, HeroInfos.DAMAGE, HeroInfos.DAMAGE, HeroInfos.PROJECTILE_SPEED);
		GameWorld world = new GameWorld(isaac);				
		initializeDisplay();
		
		
		//french welcoming message
		File welcomeFR = new File("sounds/welcomeFR.wav");
		AudioInputStream audioStreamFR = AudioSystem.getAudioInputStream(welcomeFR);
		Clip welcomeFrenchClip = AudioSystem.getClip();
		welcomeFrenchClip.open(audioStreamFR);
		
		
		//english welcoming message
		File welcomeEN = new File("sounds/welcomeEN.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(welcomeEN);
		Clip welcomeEnglishClip = AudioSystem.getClip();
		welcomeEnglishClip.open(audioStream);
		
		
		//background sound
		File background = new File("sounds/backgroundMusic.wav");
		AudioInputStream audio = AudioSystem.getAudioInputStream(background);
		Clip backgroundClip = AudioSystem.getClip();
		backgroundClip.open(audio);
		FloatControl gainControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-29.0f); // Reduces volume by 29 decibels.
	
		// Main loop of the game - remove the comments to have a background music and/or a welcoming message in whichever language you want
		while (!world.gameOver())
		{
			processNextStep(world);
			//welcomeFrenchClip.start();
			//welcomeEnglishClip.start();
			//backgroundClip.start();
			
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
