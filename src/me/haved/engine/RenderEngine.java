package me.haved.engine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class RenderEngine
{
	private static int canvasWidth, canvasHeight;
	private static int windowWidth, windowHeight;
	private static float canvasScaleX, canvasScaleY;
	
	/**
	 * Makes a OpenGL window with mouse and keyboard input
	 * 
	 * @param cwidth the width of the canvas the window displays, the canvas is stretched to fit the actual window size
	 * @param cheight the height of the canvas the window displays, the canvas is stretched to fit the actual window size
	 * @param wwidth the width of the window, the canvas is stretched to fit the actual window size
	 * @param wheight the height of the window, the canvas is stretched to fit the actual window size
	 */
	public static void initGLWindow(int cwidth, int cheight, int wwidth, int wheight)
	{
		try
		{
			RenderEngine.canvasWidth = cwidth;
			RenderEngine.canvasHeight = cheight;
			RenderEngine.windowWidth = wwidth;
			RenderEngine.windowHeight = wheight;
			
			RenderEngine.canvasScaleX = (float)RenderEngine.windowWidth / RenderEngine.canvasWidth;
			RenderEngine.canvasScaleY = (float)RenderEngine.windowHeight / RenderEngine.canvasHeight;
			
			Display.setDisplayMode(new DisplayMode(RenderEngine.windowWidth, RenderEngine.windowHeight));
			
			Display.create();
			Mouse.create();
			Keyboard.create();
			
			initGLOrtho(RenderEngine.windowWidth, RenderEngine.windowHeight);
			initGLParameters();
		}
		catch(Exception e)
		{
			System.err.println("Could not create GL window. Forced exit");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void initGLOrtho(int windowWidth, int windowHeight)
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, windowWidth, windowHeight, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public static void initGLParameters()
	{
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void disposeGLWindow()
	{
		Mouse.destroy();
		Keyboard.destroy();
		Display.destroy();
	}
	
	/**
	 * calls renderObject(object) with the game's root object as parameter
	 * 
	 * @param game the game it takes the GameObject from
	 */
	public static void renderGame(Game game)
	{
		glPushMatrix();
		glScalef(RenderEngine.canvasScaleX, RenderEngine.canvasScaleY, 1);
		
		game.render();
		
		glPopMatrix();
	}
	
	public static void clearBuffers()
	{
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public static void resetColor()
	{
		setColor(1, 1, 1, 1);
	}
	
	public static void setColor(float r, float g, float b)
	{
		setColor(r, g, b, 1);
	}
	
	public static void setColor(float r, float g, float b, float a)
	{
		glColor4f(r, g, b, a);
	}
	
	public static void fillRectangle(float x, float y, float width, float height)
	{
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			glVertex2f(x      , y);
			glVertex2f(x+width, y);
			glVertex2f(x+width, y+height);
			glVertex2f(x      , y+height);
		}
		glEnd();
	}
	
	public static void fillRectangle(float width, float height)
	{
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			glVertex2f(0    , 0);
			glVertex2f(width, 0);
			glVertex2f(width, height);
			glVertex2f(0    , height);
		}
		glEnd();
	}
	
	public static void fillRectangleWithTexture(float x, float y, float width, float height, float tx, float ty, float tx2, float ty2)
	{
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(tx   , ty);
			glVertex2f(x      , y);
			glTexCoord2f(tx2  , ty);
			glVertex2f(x+width, y);
			glTexCoord2f(tx2  , ty2);
			glVertex2f(x+width, y+height);
			glTexCoord2f(tx   , ty2);
			glVertex2f(x      , y+height);
		}
		glEnd();
	}
	
	public static void fillRectangleWithTexture(float width, float height, float tx, float ty, float tx2, float ty2)
	{
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(tx , ty);
			glVertex2f(0    , 0);
			glTexCoord2f(tx2, ty);
			glVertex2f(width, 0);
			glTexCoord2f(tx2, ty2);
			glVertex2f(width, height);
			glTexCoord2f(tx , ty2);
			glVertex2f(0    , height);
		}
		glEnd();
	}
	
	public static void pushMatrix()
	{
		glPushMatrix();
	}
	
	public static void popMatrix()
	{
		glPopMatrix();
	}
	
	public static void translate(float x, float y, float z)
	{
		glTranslatef(x, y, z);
	}
	
	public static void rotate(float angle, float x, float y, float z)
	{
		glRotatef(angle, x, y, z);
	}
	
	public static void scale(float x, float y, float z)
	{
		glScalef(x, y, z);
	}
	
	public static int getCanvasWidth()
	{
		return canvasWidth;
	}

	public static int getCanvasHeight()
	{
		return canvasHeight;
	}

	public static int getWindowWidth()
	{
		return windowWidth;
	}

	public static int getWindowHeight()
	{
		return windowHeight;
	}
}