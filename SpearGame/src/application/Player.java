package application;

import javafx.scene.shape.Rectangle;

// This Player class overrides the Character class's movement classes to stop at the edges.
public class Player extends Character {

    public Player(int x, int y) {
        super(new Rectangle(10,10),x,y);
    }
    
    @Override
    public void turnLeft() {
    	if(super.getCharacter().getTranslateX() > 0)
    		this.getCharacter().setTranslateX(this.getCharacter().getTranslateX() - 3);
    }

    @Override
    public void turnRight() {
    	if(this.getCharacter().getTranslateX() < (main.getWidth() - this.getCharacter().getWidth()))
    		this.getCharacter().setTranslateX(this.getCharacter().getTranslateX() + 3);
    }

    @Override
    public void up() {
    	if(this.getCharacter().getTranslateY() > 0)
    		this.getCharacter().setTranslateY(this.getCharacter().getTranslateY() - 3);
    }
    
    @Override
    public void down() {
    	if(this.getCharacter().getTranslateY() < main.getHeight() - this.getCharacter().getHeight())
    		this.getCharacter().setTranslateY(this.getCharacter().getTranslateY() + 3);
    }

}