package me.haved.engine;

import org.lwjgl.opengl.Display;

public class Engine2D
{
	private Game game;
	private GameSettings settings;
	
	public Engine2D(Game game, GameSettings settings)
	{
		this.game = game;
		this.settings = settings;
	}
	
	public void startEngine()
	{
		init();
		run();
	}
	
	private void init()
	{
		RenderEngine.initGLWindow(settings.canvasX, settings.canvasY, settings.windowX, settings.windowY);
		Display.setVSyncEnabled(settings.vsync);
		game.init();
	}
	
	private void run()
	{
		Time.start();
		while(!Display.isCloseRequested())
		{
			Display.sync(settings.sychFPS);
			Time.next();
			game.update();
			RenderEngine.clearBuffers();
			RenderEngine.renderGame(game);
			Display.update();
		}
	}
}
