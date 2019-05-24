package main;

import java.util.ArrayList;



public class University {
		public static Double G_coef= 6.67408; //gravitational_constant
		public static int G_exp= 11; //gravitational_constant
		//6.67408*10^-11 (m^3*kg^-1*s^-2)
		public static long clock=0;
		public static double clock_delay;
		public static double proportional_scale_coef;
		public static int proportional_scale_exp;
		public static double density=10;
		public static ArrayList<Astronomical> Astronomical_list =new ArrayList<Astronomical>();
		public static double radiusbase=5;
		public static double getClock_delay()
		{
			return clock_delay;
		}
		public static void next_State()
		{
			boolean keepMerge=true;
			while(keepMerge)
			{
				
				//Bug Warning: if acceleration too big, then two ball would not stimulate the condition, so one of the ball would eject to distance. 
				//Solution: calculate path of the ball,and determine if two ball crashed.
				keepMerge=false;
				for(int i=0; i<Astronomical_list.size(); i++)
				{
					for(int j=0; j<Astronomical_list.size(); j++)
					{
						if(i==j) continue;
						if(Astronomical_list.get(i).getDistance(Astronomical_list.get(j))<(Astronomical_list.get(i).radius+Astronomical_list.get(j).radius))
						{
							keepMerge=true;
							Astronomical_list.add(Astronomical_list.get(i).integrate(Astronomical_list.get(j)));
							if(i>j)
							{
								Astronomical_list.remove(i);
								Astronomical_list.remove(j);
							}
							else
							{
								Astronomical_list.remove(j);
								Astronomical_list.remove(i);
							}
							break;
						}		
					}
					if(keepMerge)
						break;
				}
			}
			//////////////////////////
			for(int i=0; i<Astronomical_list.size(); i++)
			{
				Vector a= new Vector();
				for(int j=0; j<Astronomical_list.size(); j++)
				{
					if(i==j) continue;
					a=a.plus(Astronomical_list.get(i).getGravityTransferToAcceleration(Astronomical_list.get(j)));
				}
				if(controller.GR_Mode)
				{
					a=a.plus(Astronomical_list.get(i).getGravityTransferToAcceleration(controller.GR_Object));
				}
				Astronomical_list.get(i).setAcceleration(a);
			}
			for(int i=0; i<Astronomical_list.size(); i++)
			{
				 Astronomical_list.get(i).nextState();
			}
		}
		 public static void createNewAstronomical(double x, double y, double vx,double vy, double m_coef, int m_exp)
		 {
			 Astronomical_list.add(new Astronomical(new Vector(x,y),new Vector(vx,vy),m_coef,m_exp));
		 }
		
		 /////////////////////////////////////////////////////////////////////////////////////////
//		 public static void createNewAstronomical(double x, double y, double vx,double vy, double m_coef, int m_exp,Color color)
//		 {
//			 Astronomical_list.add(new Astronomical(new Vector(x,y),new Vector(vx,vy),m_coef,m_exp,color));
//		 }
//		public static void createNewAstronomical(double x, double y, double vx,double vy, double m_coef, int m_exp,Color color,double radius)
//		 {
//			 Astronomical_list.add(new Astronomical(new Vector(x,y),new Vector(vx,vy),m_coef,m_exp,color,radius));
//		 }
		/////////////////////////////////////////////////////////////////////////////////////////////
}
