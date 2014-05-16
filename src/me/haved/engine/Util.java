package me.haved.engine;

import java.util.Random;

public class Util
{
	private static Random random = new Random();
	
	public static float randomFloat(float maxVal)
	{
		return random.nextFloat()*maxVal;
	}
	
	public static int randomInt(int maxInt)
	{
		return random.nextInt(maxInt);
	}
}
