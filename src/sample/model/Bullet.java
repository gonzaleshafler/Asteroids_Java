package sample.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import sample.Main;


public class Bullet extends GameObject {
    public Bullet(double x, double y) {
        super(new Polygon(15, -2, 15, 2, -15, 2, -15, -2),(int)x, (int)y);
        this.getShape().setFill(Color.RED);

    }


    @Override
    public void move() {
        int speed = 2;
        this.getShape().setTranslateX(this.getShape().getTranslateX() + this.getVelocity().getX()* speed);
        this.getShape().setTranslateY(this.getShape().getTranslateY() + this.getVelocity().getY()* speed);
    }

    public boolean checkOutOfScreen()
    {
        return Math.abs(this.getShape().getTranslateX()) > Main.screenWidth + 20 || Math.abs(this.getShape().getTranslateY()) > Main.screenHeight + 20;
    }
}
