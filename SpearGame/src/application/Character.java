package application;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public abstract class Character {
	
    private Rectangle character;
    private Point2D movement;
    Main main = new Main();
    private double playerX;
    private double playerY;
    protected static double maxHealth = 100.0;
    protected static double currentHealth = 100.0;
    
    public Character(Rectangle rectangle, int x, int y) {
        this.character = rectangle;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.movement = new Point2D(0, 0);
        this.playerX = character.getTranslateX();
        this.playerY = character.getTranslateY();
    }

    
	public Rectangle getCharacter() {
        return character;
    }

	
    public void turnLeft() {
    		this.character.setTranslateX(this.character.getTranslateX() - 3);
    }

    
    public void turnRight() {
    		this.character.setTranslateX(this.character.getTranslateX() + 3);
    }

    
    public void up() {
    		this.character.setTranslateY(this.character.getTranslateY() - 3);
    }
    
    
    public void down() {
    		this.character.setTranslateY(this.character.getTranslateY() + 3);
    }
    
    
    public void restart() {
        this.character.setTranslateX(100);
        this.character.setTranslateY(100);
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