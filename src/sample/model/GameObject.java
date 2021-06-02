package sample.model;

import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import sample.Main;

public abstract class GameObject {
    private Polygon  obj;

    private Point2D velocity;

    private boolean active;

    public GameObject(Polygon node, int x, int y)
    {
        this.obj = node;
        this.obj.setTranslateX(x);
        this.obj.setTranslateY(y);
        this.active = true;
        this.velocity = new Point2D(0,0);

    }
    public  void move()
    {
        this.obj.setTranslateX(this.obj.getTranslateX() + this.velocity.getX());
        this.obj.setTranslateY(this.obj.getTranslateY() + this.velocity.getY());

        if (this.obj.getTranslateX() < 0) {
            this.obj.setTranslateX(this.obj.getTranslateX() + Main.screenWidth);
        }

        if (this.obj.getTranslateX() > Main.screenHeight) {
            this.obj.setTranslateX(this.obj.getTranslateX() % Main.screenWidth);
        }

        if (this.obj.getTranslateY() < 0) {
            this.obj.setTranslateY(this.obj.getTranslateY() + Main.screenHeight);
        }

        if (this.obj.getTranslateY() > Main.screenHeight) {
            this.obj.setTranslateY(this.obj.getTranslateY() % Main.screenHeight);
        }

    }

    public void turnLeft() {

        this.obj.setRotate(this.obj.getRotate() - 1);
    }

    public void turnLeft(int t) {

        this.obj.setRotate(this.obj.getRotate() - t);
    }

    public void turnRight() {
        this.obj.setRotate(this.obj.getRotate() + 1);
    }



    public void turnRight(int t) {
        this.obj.setRotate(this.obj.getRotate() + t);
    }



    public void accelerate() {
        double vX = Math.cos(Math.toRadians(this.obj.getRotate()));
        double vY = Math.sin(Math.toRadians(this.obj.getRotate()));

        vX *= 0.02;
        vY *= 0.02;

        this.velocity = this.velocity.add(vX,vY);
    }



    public void accelerate(int t) {
        double vX = Math.cos(Math.toRadians(this.obj.getRotate()));
        double vY = Math.sin(Math.toRadians(this.obj.getRotate()));

        vX *= 0.02 * t;
        vY *= 0.02 * t;

        this.velocity = this.velocity.add(vX, vY);
    }

    public boolean isActive() {
        return this.active;
    }



    public void setActive(boolean active) {
        this.active = active;
    }


    public Point2D getVelocity() {
        if ( Math.abs(this.velocity.getX())>=4 || Math.abs(this.velocity.getY())>=4)
            this.velocity =new Point2D(this.velocity.normalize().getX()*4,this.velocity.normalize().getY()*4) ;


        return this.velocity;
    }

    public void setVelocity(Point2D vel) {
        this.velocity = vel;
    }


    public Polygon getShape() {

        return this.obj;
    }


    public void setShape(Polygon shape) {
        this.obj = shape;
    }




    public boolean collide(GameObject other)
    {
        Shape collisionArea = Shape.intersect(this.obj,other.getShape());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }
}
