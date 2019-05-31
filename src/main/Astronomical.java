package main;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Astronomical {
	 public boolean BlackHole; 
	 public boolean TimeStop;
	 Vector coordinate;
	 Vector velocity;
	 Vector acceleration;
	 double Mass_coef; 
	 double radius;
	 Color color;
	 Astronomical(Vector coor, Vector velo, double m_coef)
	 {
		 this.BlackHole=false; 
		 this.TimeStop=false;
		 this.coordinate=coor;
		 this.velocity=velo;
		 this.acceleration=new Vector();
		 this.Mass_coef=m_coef;
		 this.radius=University.radiusbase*(Math.log10(m_coef)+1);
		 this.color=Color.color(Math.random(), Math.random(), Math.random());
		 if(this.Mass_coef>=10000000)
		 {
			 this.radius*=10;
			 this.BlackHole=true;
			 this.color=Color.BLACK;
			 controller.limit=100;
		 }
	 }
	 public void nextState()
	 {
		 if(BlackHole || TimeStop) return;
		 this.coordinate = this.coordinate.plus(this.velocity);
		 this.velocity = this.velocity.plus(this.acceleration);
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
		 return Math.sqrt(dx*dx + dy*dy)*100;
	 }
	 public Vector getUnitVector(Astronomical b) 
	 {
		 return b.coordinate.minus(this.coordinate).multiplyConstant(1.0/this.getDistance(b));
	 }
	 public Vector getGravityTransferToAcceleration(Astronomical b)
	 {
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
		 if(this.BlackHole)
			 return new Astronomical(this.coordinate,this.velocity,this.Mass_coef);
		 else if(b.BlackHole)
			 return new Astronomical(b.coordinate,b.velocity,b.Mass_coef);
		 else
			 return new Astronomical(newcoor,newvelo,newMass);
	 }
	 public void CoordinateShift(double dx, double dy) {this.coordinate=this.coordinate.plus(new Vector(dx,dy));}
	 public Paint getColor() {return color;}
	 public void setCoor(Vector v) {this.coordinate=v;}
}