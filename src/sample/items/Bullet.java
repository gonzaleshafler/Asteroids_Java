package sample.items;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class Bullet extends GameObject {
    public Bullet(double x, double y) {
        super(new Polygon(15, -2, 15, 2, -15, 2, -15, -2),(int)x, (int)y);
        this.getShape().setFill(Color.WHITE);

    }

    @Override
    public void move() {
        int speed = 2;
        this.getShape().setTranslateX(this.getShape().getTranslateX() + this.getVelocity().getX()* speed);
        this.getShape().setTranslateY(this.getShape().getTranslateY() + this.getVelocity().getY()* speed);
    }
}
