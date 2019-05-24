package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class controller implements Initializable{
	public static int MouseState=1;
	public static int TimeStopRadius=50;
	public static double playspeedRatio=1;
	public static double CreateVX=0;
	public static double CreateVY=0;
	public static double CreateMass=1;
	public static double GR_Mass=100;
	public static double ballRatio=1;
	public static int shiftRatio=500;
	public static double displayRatio=1;
	public static double TimeStopX=0;
	public static double TimeStopY=0;
	public static boolean TimeStop=false;
	public static boolean GR_Mode=false;
	public static Astronomical GR_Object;
	
    @FXML
    public Pane root;//pane
    @FXML
    public Button playBtn;
    @FXML
    public Button stopBtn;
    @FXML
    private Slider shift;
    @FXML
    private Slider displayRatioSlider;
    @FXML
    private Slider MassSlider;
    @FXML
    private Slider VXSlider;
    @FXML
    private Slider VYSlider;
    @FXML
    private Slider ballRatioSlider;
    @FXML
    private Accordion GR_toolbox;
    @FXML
    private Accordion TS_toolbox;
    @FXML
    private Accordion Create_toolbox;
    @FXML
    private Slider TimeStopRadiusSlider;
    @FXML
    private Slider ForceSlider;
    @FXML
    private Label number;
    public Timeline fps;
	
	int count=0;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	  fps = new Timeline(new KeyFrame(Duration.millis(1*playspeedRatio),(e)-> {
		  //Random Mode
//		  if(count<1000)
//		  {
//			  University.createNewAstronomical(controller.randomGenerator(System.currentTimeMillis())*controller.randomGenerator(System.currentTimeMillis())%2000,controller.randomGenerator(System.currentTimeMillis())%1000, -5, 5, 300, 100);
//			  University.createNewAstronomical(controller.randomGenerator(System.currentTimeMillis())*controller.randomGenerator(System.currentTimeMillis())*controller.randomGenerator(System.currentTimeMillis())%2000,controller.randomGenerator(System.currentTimeMillis())*controller.randomGenerator(System.currentTimeMillis())*controller.randomGenerator(System.currentTimeMillis())*controller.randomGenerator(System.currentTimeMillis())%1000, 5, -5, 300, 100);
//			  count++;
//		  }
//		  if(count==1000)
//		  {
//			  University.createNewAstronomical(5000, 5000, 0, 0, 100000000, 100);
//			  count++;
//		  }
		  /////////////////////////////////////////
		  	if(TimeStop)
		  		TimeStop();
      		sketch();
      	    University.next_State();
      	    number.setText(String.valueOf(University.Astronomical_list.size()));
	      }));
	  fps.setCycleCount(Timeline.INDEFINITE);
	}
	
	public void reset()
	{
		TimeStopRadius=50;
		TimeStopRadiusSlider.setValue(50);
		CreateVX=0;
		VXSlider.setValue(0);
		CreateVY=0;
		VYSlider.setValue(0);
		CreateMass=1;
		MassSlider.setValue(1);
		GR_Mass=100;
		ForceSlider.setValue(100);
		ballRatio=1;
		ballRatioSlider.setValue(1);
		shiftRatio=500;
		shift.setValue(500);
		displayRatio=1;
		displayRatioSlider.setValue(0);

	}
	public void ClickEventReleased(MouseEvent e)
	{
		number.setText(String.valueOf(University.Astronomical_list.size()));
		if(MouseState==1)
		{
			if(University.Astronomical_list.size()<170)
			if(e.getButton()==MouseButton.PRIMARY)
				University.createNewAstronomical((e.getX()-root.getWidth()/2)/controller.displayRatio,(e.getY()-root.getHeight()/2)/controller.displayRatio,CreateVX,CreateVY,CreateMass,100);
			else if(e.getButton()==MouseButton.SECONDARY)
			{
				University.createNewAstronomical((e.getX()-root.getWidth()/2)/controller.displayRatio,(e.getY()-root.getHeight()/2)/controller.displayRatio,CreateVX,CreateVY,CreateMass*1000,100);
			}
			sketch();
		}
		else if(MouseState==2)
		{
			for(int i=0; i<University.Astronomical_list.size(); i++)
			{
				 University.Astronomical_list.get(i).TimeStop=false;
			}
			TimeStop=false;
			if(e.getButton()==MouseButton.SECONDARY)
			{
				TimeStopRadius/=5;
			}
		}
		else if(MouseState==3)
		{
			GR_Mode=false;
		}
	}	
	
	public void ClickEventPressed(MouseEvent e)
	{
		if(MouseState==2)
		{
			if(e.getButton()==MouseButton.SECONDARY)
			{
				TimeStopRadius*=5;
			}
			TimeStop=true;
			TimeStopX=(e.getX()-root.getWidth()/2);
			TimeStopY=(e.getY()-root.getHeight()/2);
		}
		else if(MouseState==3)
		{
			GR_Mode=true;
			if(e.getButton()==MouseButton.PRIMARY)
				GR_Object=new Astronomical(new Vector((e.getX()-root.getWidth()/2)/controller.displayRatio,(e.getY()-root.getHeight()/2)/controller.displayRatio),new Vector(), GR_Mass,100);
			else if(e.getButton()==MouseButton.SECONDARY)
				GR_Object=new Astronomical(new Vector((e.getX()-root.getWidth()/2)/controller.displayRatio,(e.getY()-root.getHeight()/2)/controller.displayRatio),new Vector(), -GR_Mass,100);
		}
	}
	
	public void ClickEventDragged(MouseEvent e)
	{
		if(MouseState==2)
		{
			TimeStopX=(e.getX()-root.getWidth()/2);
			TimeStopY=(e.getY()-root.getHeight()/2);
		}
		else if(MouseState==3 && GR_Mode)
		{
			GR_Object.setCoor(new Vector((e.getX()-root.getWidth()/2)/controller.displayRatio,(e.getY()-root.getHeight()/2)/controller.displayRatio));
		}
	}
	
	
	@FXML
    public void onCreateRadioChoosed() {
		Create_toolbox.setVisible(true);
		TS_toolbox.setVisible(false);
		GR_toolbox.setVisible(false);
		MouseState=1;
    }
	
	@FXML
    public void onTSRadioChoosed() {
		Create_toolbox.setVisible(false);
		TS_toolbox.setVisible(true);
		GR_toolbox.setVisible(false);
		MouseState=2;
    }
	
	@FXML
    public void onGRRadioChoosed() {
		Create_toolbox.setVisible(false);
		TS_toolbox.setVisible(false);
		GR_toolbox.setVisible(true);
		MouseState=3;
    }
	
	@FXML
    public void onShiftChanged() {
        controller.shiftRatio = (int) shift.getValue();
    }
	
	@FXML
    public void onTimeStopRadiusSliderChanged() {
		controller.TimeStopRadius=(int) TimeStopRadiusSlider.getValue();
    }
	
	
	@FXML
    public void onDisplayRatioSliderChanged() {
        controller.displayRatio = Math.pow(10, displayRatioSlider.getValue());
        sketch();
    }
	
	@FXML
    public void onMassSliderChanged() {
        controller.CreateMass = MassSlider.getValue();
    }
	@FXML
    public void onVXSliderChanged() {
		controller.CreateVX = VXSlider.getValue();
    }
	@FXML
    public void onVYSliderChanged() {
		controller.CreateVY = VYSlider.getValue();
    }
	
	@FXML
    public void onForceSliderChanged() {
		controller.GR_Mass = ForceSlider.getValue();
    }
	
	
	@FXML
    public void onballRatioSliderChanged() {
        controller.ballRatio = Math.pow(10, ballRatioSlider.getValue());
        sketch();
    }
	
	public void OnResetPressed(ActionEvent e) {
		University.Astronomical_list.clear();
		sketch();
		reset();
	};
	
	public void OnplayPressed(ActionEvent e) {
		fps.play();
		playBtn.setVisible(false);
		stopBtn.setVisible(true);
	};
	public void OnstopPressed(ActionEvent e) {
		fps.stop();
		playBtn.setVisible(true);
		stopBtn.setVisible(false);
	};
	public void shiftup(ActionEvent e) {
		shift(3);
		sketch();
	};
	public void shiftdown(ActionEvent e) {
		shift(2);
		sketch();
	};
	public void shiftright(ActionEvent e) {
		shift(1);
		sketch();
	};
	public void shiftleft(ActionEvent e) {
		shift(0);
		sketch();
	};
	
	public void sketch()
	{
		root.getChildren().clear();
  	    if(TimeStop)
  	    {
  	    	Circle circle = new Circle(TimeStopRadius*controller.displayRatio, Color.WHITE);
      		circle.setLayoutX(TimeStopX+root.getWidth()/2);
      		circle.setLayoutY(TimeStopY+root.getHeight()/2);
      		root.getChildren().add(circle);
  	    }
  	    for(int i=0; i<University.Astronomical_list.size(); i++)
        {
  	    	if(University.Astronomical_list.get(i).GR_Mode) continue;
  	    	Circle circle = new Circle(University.Astronomical_list.get(i).radius*controller.displayRatio*controller.ballRatio, University.Astronomical_list.get(i).getColor());
      		circle.setLayoutX(University.Astronomical_list.get(i).coordinate.getX()*controller.displayRatio+root.getWidth()/2);
      		circle.setLayoutY(University.Astronomical_list.get(i).coordinate.getY()*controller.displayRatio+root.getHeight()/2);
      		root.getChildren().add(circle);
        }
  	    
	}
	 
	public void shift(int direct)//0 right, 1 left, 2 up, 3 down
	{
		int dx[]= {1,-1,0,0};
		int dy[]= {0,0,-1,1};
		for(int i=0; i<University.Astronomical_list.size(); i++)
        {
			University.Astronomical_list.get(i).CoordinateShift(shiftRatio*dx[direct]/controller.displayRatio,shiftRatio*dy[direct]/controller.displayRatio);
        }	
	}
	
	public static double randomGenerator(long seed) {
        Random generator = new Random(seed);
        double num = generator.nextDouble() * 1000;

        return num;
    }
	public void TimeStop()
	{
		for(int i=0; i<University.Astronomical_list.size(); i++)
		{
			if(University.Astronomical_list.get(i).getDistance(new Vector(TimeStopX/controller.displayRatio,TimeStopY/controller.displayRatio))<TimeStopRadius*100) 
				University.Astronomical_list.get(i).TimeStop=true;
			else
			{
				University.Astronomical_list.get(i).TimeStop=false;
			}
		}
	}
		
}
