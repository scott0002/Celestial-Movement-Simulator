package main;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CMS extends Application {
	public static Stage mainStage;
	public static Scene mainScene;
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage mainStage) throws Exception {
        //maybe I can add new function that our vision can tracing one of the ball.
      
        CMS.mainStage = mainStage;
	    FXMLLoader loadder =
	       new FXMLLoader(
	           getClass().getResource("mainscene.fxml"));
	    Parent main = loadder.load();
	    mainScene = new Scene(main);
	    //mainStage.setMaximized(true);
	    mainStage.setFullScreen(true);
	    mainStage.setTitle("Celestial Movement Simulator");
	    mainStage.setScene(mainScene);
	    mainStage.show();
	    
	}
}