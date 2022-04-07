package application;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Boss{
	protected static double bossmaxHealth = 100.0;
    protected static double bosscurrentHealth = 100.0;
	Main main = new Main();
	private Rectangle boss = new Rectangle(main.getWidth()/2-25,main.getHeight()/8,50,50);
	
	public Boss() {
		play();
	}
	
	//My boss method

    private void play() {
      double x = new Random().nextInt(main.getWidth()); //size of possible X movement based on screen
      double y = new Random().nextInt(main.getHeight()); //size of possible Y movement based on screen
      
      final Timeline timeline = new Timeline();      // cycle count = 2 because of autoreverse
      //timeline.setCycleCount(0); 					//this is 2 on default but changing it will also change boss behavior
      //timeline.setAutoReverse(true);
      final KeyValue kx = new KeyValue(boss.xProperty(), x + 50);
      final KeyValue ky = new KeyValue(boss.yProperty(), y);
//      final KeyValue kx = new KeyValue(boss.xProperty(), x + 50);
//      final KeyValue ky = new KeyValue(boss.yProperty(), y + 0); //making this number lower means boss will stay closer to top
      final KeyValue kScale = new KeyValue(boss.scaleXProperty(), 2); //this would cause boss to change size
//      final KeyValue kFade = new KeyValue(rectBasicTimeline.opacityProperty(), 0); //this would cause boss to fade color
      final KeyFrame kf = new KeyFrame(Duration.millis(1000), kx, ky);
      timeline.getKeyFrames().add(kf);
      timeline.setOnFinished(e -> play());
      timeline.play();
  }
    
    public Rectangle getBoss() {
    	return boss;
    }
    
    public double getY() {
    	return boss.getY();
    }
    
    public double getX() {
    	return boss.getX();
    }
    
    public double getSizeX() {
    	return boss.getWidth();
    }
    
    public double getSizeY() {
    	return boss.getHeight();
    }
    
    public double getBossMaxHealth() {
    	return bossmaxHealth;
    }
    
    public double getBossCurrentHealth() {
    	return bosscurrentHealth;
    }
    
    public void setBossCurrentHealth(double value) {
    	bosscurrentHealth=value;
    }
}
