package libraries;

import java.lang.Math;

/*
 * This class represents a pair of double seen as a vector, i.e. with their associated operations.
 * Through the program, these may be used to represent position or size.
 */
public class Vector2
{
	private double positionX;
	private double positionY;

	public Vector2()
	{
		this.positionX = 0;
		this.positionY = 0;
	}

	public Vector2(double x, double y)
	{
		positionX = x;
		positionY = y;
	}

	public Vector2(Vector2 v)
	{
		positionX = v.getX();
		positionY = v.getY();
	}

	public double getX()
	{
		return positionX;
	}

	public double getY()
	{
		return positionY;
	}

	public boolean equals(Vector2 p)
	{
		return (this.positionX == p.positionX && this.positionY == p.positionY);
	}

	public void setX(double x)
	{
		this.positionX = x;
	}

	public void setY(double y)
	{
		this.positionY = y;
	}

	public void addX(double x)
	{
		this.positionX += x;
	}

	public void addY(double y)
	{
		this.positionY += y;
	}

	public Vector2 addVector(Vector2 v)
	{
		Vector2 newVector = new Vector2(this);
		newVector.addX(v.getX());
		newVector.addY(v.getY());
		return newVector;
	}

	public Vector2 subVector(Vector2 v)
	{
		return this.addVector(v.reverse());
	}

	public Vector2 scalarMultiplication(double v)
	{
		return new Vector2(this.getX() * v, this.getY() * v);
	}

	public Vector2 vectorMultiplication(Vector2 v)
	{
		return new Vector2(this.getX() * v.getX(), this.getY() * v.getY());
	}

	public Vector2 reverse()
	{
		Vector2 newVector = new Vector2();
		newVector.setX(-this.getX());
		newVector.setY(-this.getY());
		return newVector;
	}

	public Vector2 absoluteValue()
	{
		Vector2 newVector = new Vector2();
		newVector.setX(Math.abs(getX()));
		newVector.setY(Math.abs(getY()));
		return newVector;
	}

	public String toString()
	{
		return "(" + this.getX() + ", " + this.getY() + ")";
	}

	/*
	 * The Euclidian norm is the usual distance between the vector seen as a
	 * position and the position (0,0).
	 */
	public double euclidianNorm()
	{
		double norm = Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
		return norm;
	}

	public void euclidianNormalize(double newNorm)
	{
		double norm = euclidianNorm();
		// Null vectors remain null.
		if (Math.abs(norm) < 2 * Double.MIN_VALUE)
		{
			return;
		}
		double scalingFactor = newNorm / norm;
		this.setX(this.getX() * scalingFactor);
		this.setY(this.getY() * scalingFactor);
	}

	public double distance(Vector2 vector)
	{
		Vector2 diffVector = vector.subVector(this);
		return diffVector.euclidianNorm();
	}

}
