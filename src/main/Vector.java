package main;

public class Vector {
		double x,y;
		Vector()
		{
			this.x=0;
			this.y=0;
		}
		Vector(double x, double y)
		{
			this.x=x;
			this.y=y;
		}
		public void setVector(double x, double y)
		{
			this.x=x;
			this.y=y;
		}
		public double getX()
		{
			return this.x;
		}
		public double getY()
		{
			return this.y;
		}
		public Vector plus(Vector b)
		{
			return new Vector(this.x+b.x, this.y+b.y);
		}
		public Vector minus(Vector b)
		{
			return new Vector(this.x-b.x, this.y-b.y);
		}
		public Vector multiplyConstant(double b)
		{
			return new Vector(this.x*b, this.y*b);
		}
}
