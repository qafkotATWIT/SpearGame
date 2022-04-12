package application;

import java.io.InputStream;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
//Images <a href='https://pngtree.com/so/red'>red png from pngtree.com/</a>
public abstract class Character {
	
    private Rectangle character;
    private Point2D movement;
    Main main = new Main();
    private double playerX;
    private double playerY;
    private static int playerSpeed = 3;
    protected static double maxHealth = 100.0;
    protected static double currentHealth = 100.0;
    private InputStream inStream = getClass().getResourceAsStream("man.png");
    private Image image =  new Image(inStream); 
    
    public Character(Rectangle rectangle, int x, int y, int playerSpeed) {
        this.character = rectangle;
        this.character.setFill(new ImagePattern(image));
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.movement = new Point2D(0, 0);
        this.playerX = character.getTranslateX();
        this.playerY = character.getTranslateY();
        this.playerSpeed=playerSpeed;
    }

    
	public Rectangle getCharacter() {
        return character;
    }

	
    public void turnLeft() {
    		this.character.setTranslateX(this.character.getTranslateX() - playerSpeed);
    }

    
    public void turnRight() {
    		this.character.setTranslateX(this.character.getTranslateX() + playerSpeed);
    }

    
    public void up() {
    		this.character.setTranslateY(this.character.getTranslateY() - playerSpeed);
    }
    
    
    public void down() {
    		this.character.setTranslateY(this.character.getTranslateY() + playerSpeed);
    }
    
    
    public void restart() {
        this.character.setTranslateX(0);
        this.character.setTranslateY(50);
        this.character.setRotate(0);
        this.movement = new Point2D(0,0);
        
    }
    
    
    public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
    }

    
    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.character.getRotate()));

        changeX *= 0.01;
        changeY *= 0.01;

        this.movement = this.movement.add(changeX, changeY);
    }
    
    public double getPlayerX() {
    	return playerX;
    }
    
    public double getPlayerY() {
    	return playerY;
    }
}