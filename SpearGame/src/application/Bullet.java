package application;


/*---------------THIS IS NOT BEING USED-------------------------*/



import javafx.scene.shape.Rectangle;

public class Bullet {
	public Bullet(double x, double y, double x1, double y1) {
		Rectangle bullet = new Rectangle(10,10);
		for(double i=x; i<=x1; i=i+10) {
			double a = 0.05;
			for(double j=y; j<=y1*a;j=j+10) {
				
				a*=2;
			}
		}
		
	}
}
