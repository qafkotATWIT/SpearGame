package application;

import javafx.scene.shape.Rectangle;

public class Player extends Character {

    public Player(int x, int y) {
        super(new Rectangle(10,10),x,y);
    }

}