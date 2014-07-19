package me.haved.engine.math;

public class Vector2i 
{
	private int x;
	private int y;
	
	public Vector2i(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int length()
	{
		return (int)Math.sqrt(x * x + y * y);
	}

	public int max()
	{
		return Math.max(x, y);
	}

	public int dot(Vector2i r)
	{
		return x * r.getX() + y * r.getY();
	}
	
	public Vector2i normalized()
	{
		int length = length();
		
		return new Vector2i(x / length, y / length);
	}

	public int cross(Vector2i r)
	{
		return x * r.getY() - y * r.getX();
	}

	public Vector2i lerp(Vector2i dest, int lerpFactor)
	{
		return dest.sub(this).mul(lerpFactor).add(this);
	}

	public Vector2i rotate(int angle)
	{
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2i((int)(x * cos - y * sin),(int)(x * sin + y * cos));
	}
	
	public Vector2i add(Vector2i r)
	{
		return new Vector2i(x + r.getX(), y + r.getY());
	}
	
	public Vector2i add(int r)
	{
		return new Vector2i(x + r, y + r);
	}
	
	public Vector2i sub(Vector2i r)
	{
		return new Vector2i(x - r.getX(), y - r.getY());
	}
	
	public Vector2i sub(int r)
	{
		return new Vector2i(x - r, y - r);
	}
	
	public Vector2i mul(Vector2i r)
	{
		return new Vector2i(x * r.getX(), y * r.getY());
	}
	
	public Vector2i mul(int r)
	{
		return new Vector2i(x * r, y * r);
	}
	
	public Vector2i div(Vector2i r)
	{
		return new Vector2i(x / r.getX(), y / r.getY());
	}
	
	public Vector2i div(int r)
	{
		return new Vector2i(x / r, y / r);
	}
	
	public Vector2i abs()
	{
		return new Vector2i(Math.abs(x), Math.abs(y));
	}
	
	public String toString()
	{
		return "(" + x + " " + y + ")";
	}

	public Vector2i set(int x, int y) { this.x = x; this.y = y; return this; }
	public Vector2i set(Vector2i r) { set(r.getX(), r.getY()); return this; }

	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public boolean equals(Vector2i r)
	{
		return x == r.getX() && y == r.getY();
	}
}
