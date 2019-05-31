package main;

import java.util.ArrayList;

public class University {
	public static Double G_coef= 6.67408; 
	public static ArrayList<Astronomical> Astronomical_list =new ArrayList<Astronomical>();
	public static double radiusbase=5;
	public static void next_State()
	{
		boolean keepMerge=true;
		while(keepMerge)
		{
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
		for(int i=0; i<Astronomical_list.size(); i++)
		{
			Vector a= new Vector();
			for(int j=0; j<Astronomical_list.size(); j++)
			{
				if(i==j) continue;
				a=a.plus(Astronomical_list.get(i).getGravityTransferToAcceleration(Astronomical_list.get(j)));
			}
			if(controller.GR_Mode)
				a=a.plus(Astronomical_list.get(i).getGravityTransferToAcceleration(controller.GR_Object));
			Astronomical_list.get(i).acceleration=a;
		}
		for(int i=0; i<Astronomical_list.size(); i++)
			 Astronomical_list.get(i).nextState();
	}
	 public static void createNewAstronomical(double x, double y, double vx,double vy, double m_coef){
		 Astronomical_list.add(new Astronomical(new Vector(x,y),new Vector(vx,vy),m_coef));
		}
}