package me.haved.engine;

public class Time
{
	private static long startTime = 0;
	private static float deltaTime = 0;
	
	public static void start()
	{
		startTime = System.currentTimeMillis();
	}
	
	public static void next()
	{
		deltaTime = (System.currentTimeMillis() - startTime)*0.001f;
		startTime = System.currentTimeMillis();
	}
	
	public static float delta()
	{
		return deltaTime;
	}
}
