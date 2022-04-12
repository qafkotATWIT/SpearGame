package application;

import java.io.InputStream;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

// Images gotten from <a href='https://pngtree.com/so/hand'>hand png from pngtree.com/</a>
// <a href='https://pngtree.com/so/red'>red png from pngtree.com/</a>
public class Boss{
	protected static double bossmaxHealth = 100.0;
    protected static double bosscurrentHealth = 100.0;
    private static int bossSpeed;
    public Timeline timeline = new Timeline();
    private InputStream inStream = getClass().getResourceAsStream("spaceship.png");
    private Image image =  new Image(inStream);
	Main main = new Main();
	private static int bossSize=50;
	private Rectangle boss;
	
	public Boss(int speed, int size) {
		Boss.setBossSize(size);
		Boss.setBossSpeed(speed);
		boss = new Rectangle(main.getWidth()/2-25,main.getHeight()/8,bossSize,bossSize);
		this.getBoss().setFill(new ImagePattern(image));
		play();
	}

    private void play() {
    	double x = new Random().nextInt(main.getWidth()-30); //size of possible X movement based on screen
    	double y = new Random().nextInt(main.getHeight()-30); //size of possible Y movement based on screen
      
    	timeline = new Timeline();
    	final KeyValue kx = new KeyValue(boss.xProperty(), x + 50);
    	final KeyValue ky = new KeyValue(boss.yProperty(), y + 0);//making this number lower means boss will stay closer to top
    	final KeyFrame kf = new KeyFrame(Duration.millis(1100-bossSpeed), kx, ky);
    	timeline.getKeyFrames().add(kf);
    	timeline.setOnFinished(e -> play());
    	timeline.play();
	}
    
    public Timeline getTimeline() {
		return timeline;
    	
    }
    
    public static int getBossSpeed() {
    	return bossSpeed;
    }
    
    public static void setBossSpeed(int speed) {
    	bossSpeed = speed;
    }
    
    public static int getBossSize() {
    	return bossSize;
    }
    
    public static void setBossSize(int size) {
    	bossSize = size;
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
