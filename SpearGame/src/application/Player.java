package application;

import java.awt.MouseInfo;
import javafx.scene.shape.Rectangle;

// This Player class overrides the Character class's movement classes to stop at the edges.
public class Player extends Character {
	
    public Player(int x, int y, int speed) {
        super(new Rectangle(30,30),x,y, speed);
    }
    
    @Override
    public void turnLeft() {
    	if(super.getCharacter().getTranslateX() > 0)
    		super.turnLeft();
    }
    
    @Override
    public void turnRight() {
    	if(this.getCharacter().getTranslateX() < (main.getWidth() - this.getCharacter().getWidth()))
    		super.turnRight();
    }
    
    @Override
    public void up() {
    	if(this.getCharacter().getTranslateY() > 50)
    		super.up();
    }
    
    @Override
    public void down() {
    	if(this.getCharacter().getTranslateY() < main.getHeight() - this.getCharacter().getHeight())
    		super.down();
    	
    }
    
    public double getY() {
    	return super.getCharacter().getTranslateY();
    }
    
    public double getX() {
    	return super.getCharacter().getTranslateX();
    }
    
    public double getSizeX() {
    	return super.getCharacter().getWidth();
    }
    
    public double getSizeY() {
    	return super.getCharacter().getHeight();
    }
    
    public double getMaxHealth() {
    	return super.maxHealth;
    }
    
    public double getCurrentHealth() {
    	return super.currentHealth;
    }

    public double setCurrentHealth(double currentHealth) {
    	return super.currentHealth=currentHealth;
    }
    
}