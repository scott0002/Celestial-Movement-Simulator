package main;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Astronomical {
	 public boolean TimeStop;
	 public boolean GR_Mode;
	 Vector init_Coordinate;
	 Vector init_Velocity ;
	 Vector coordinate;
	 Vector velocity;
	 Vector acceleration;
	 double Mass_coef; 
	 int Mass_exp; 
	 double radius;
	 //int density;
	 Color color;
	 Astronomical()
	 {
		 this.GR_Mode=false;
		 this.TimeStop=false;
		 this.init_Coordinate=new Vector();
		 this.init_Velocity=new Vector();
		 this.coordinate=init_Coordinate;
		 this.velocity=init_Velocity;
		 this.acceleration=new Vector();
		 this.Mass_coef=0.0;
		 this.Mass_exp=0;
		 this.radius=0;
		 this.color=Color.color(Math.random(), Math.random(), Math.random());
		 //color
	 }
	 Astronomical(Vector coor, Vector velo, double m_coef, int m_exp)
	 {
		 this.GR_Mode=false;
		 this.TimeStop=false;
		 this.init_Coordinate=coor;
		 this.init_Velocity=velo;
		 this.coordinate=init_Coordinate;
		 this.velocity=init_Velocity;
		 this.acceleration=new Vector();
		 this.Mass_coef=m_coef;
		 this.Mass_exp=m_exp;
		 this.radius=University.radiusbase*(Math.log10(m_coef)+1);
		 this.color=Color.color(Math.random(), Math.random(), Math.random());
		 //color
	 }
//	 Astronomical(Vector coor, Vector velo, double m_coef, int m_exp, Color color)
//	 {
//		 this.GR_Mode=false;
//		 this.TimeStop=false;
//		 this.init_Coordinate=coor;
//		 this.init_Velocity=velo;
//		 this.coordinate=init_Coordinate;
//		 this.velocity=init_Velocity;
//		 this.acceleration=new Vector();
//		 this.Mass_coef=m_coef;
//		 this.Mass_exp=m_exp;
//		 this.radius=University.radiusbase*(Math.log10(m_coef)+1);
//		 this.color=color;
//		 //color
//	 }
//	 Astronomical(Vector coor, Vector velo, double m_coef, int m_exp, Color color,double radius)
//	 {
//		 this.GR_Mode=false;
//		 this.TimeStop=false;
//		 this.init_Coordinate=coor;
//		 this.init_Velocity=velo;
//		 this.coordinate=init_Coordinate;
//		 this.velocity=init_Velocity;
//		 this.acceleration=new Vector();
//		 this.Mass_coef=m_coef;
//		 this.Mass_exp=m_exp;
//		 this.radius=University.radiusbase*(Math.log10(m_coef)+1);
//		 this.color=color;
//		 this.radius=radius;
//		 //color
//	 }

	public void nextState()
	 {
		 if(TimeStop) return;
		 this.coordinate = this.coordinate.plus(this.velocity);
		 this.velocity = this.velocity.plus(this.acceleration);
	 }
	 public void setAcceleration(Vector a)
	 {
		 this.acceleration=a;
	 }
	 public double getDistance(Astronomical b)
	 {
		 double dx=b.coordinate.minus(this.coordinate).getX();
		 double dy=b.coordinate.minus(this.coordinate).getY();
		 return Math.sqrt(dx*dx + dy*dy);
	 }
	 public double getDistance(Vector b)
	 {
		 double dx=b.minus(this.coordinate).getX();
		 double dy=b.minus(this.coordinate).getY();
		 return Math.sqrt(dx*dx + dy*dy)*10;
	 }
	 public Vector getUnitVector(Astronomical b)
	 {
		 return b.coordinate.minus(this.coordinate).multiplyConstant(1.0/this.getDistance(b));
	 }
	 public Vector getGravityTransferToAcceleration(Astronomical b)
	 {
		 double pc=University.proportional_scale_coef;
		 double pp=University.proportional_scale_exp;
		 double dis=this.getDistance(b);
		 return this.getUnitVector(b).multiplyConstant(b.Mass_coef*University.G_coef/(dis*dis)) ;
	 }
	 public Astronomical integrate(Astronomical b)
	 {
		 Vector newcoor=this.coordinate.plus(b.coordinate).multiplyConstant(0.5);
		 Vector thismomentum=this.velocity.multiplyConstant(this.Mass_coef);
		 Vector bmomentum=b.velocity.multiplyConstant(b.Mass_coef);
		 double newMass=this.Mass_coef+b.Mass_coef;
		 Vector newvelo=thismomentum.plus(bmomentum).multiplyConstant(1/newMass);
		 return new Astronomical(newcoor,newvelo,newMass,100);
	 }
	 public void CoordinateShift(double dx, double dy)
	 {
		 this.coordinate=this.coordinate.plus(new Vector(dx,dy));
	 }
	 public void setColor(Color color) {
			// TODO Auto-generated method stub
			this.color=color;
		}
	public Paint getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	public void setCoor(Vector v)
	{
		this.coordinate=v;
	}
}
