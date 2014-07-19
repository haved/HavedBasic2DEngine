package me.haved.engine;

import org.lwjgl.input.Mouse;

public class Input
{
	public static float getMouseX()
	{
		return RenderEngine.scaleWindowPointX(Mouse.getX());
	}
	
	public static float getMouseY()
	{
		return RenderEngine.scaleWindowPointY(RenderEngine.getWindowHeight()-Mouse.getY());
	}
}
