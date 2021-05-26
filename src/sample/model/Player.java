package sample.model;


import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import sample.view.GameView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Player extends GameObject {
    private double worldX;
    private double worldY;
    private double cooldown;
    ArrayList<Bullet> bullets = new ArrayList<>(5);
    public AtomicInteger points = new AtomicInteger();

    public Player(int x, int y) {

        super(new Polygon(-10,-10,15,0,-10,10), x, y);
        super.getShape().setFill(Color.WHITE);
        this.worldX = 0;
        this.worldY = 0;
        this.cooldown = 0;
        this.points.set(0);

    }


    public double getWorldX() {
        return worldX;
    }

    public double getWorldY() {
        return worldY;
    }
    @Override
    public void move() {
        if (isActive())
        {
            super.move();
            this.worldX += this.getVelocity().getX();
            this.worldY += this.getVelocity().getY();
        }
    }
    public void shoot()
    {
        if (this.cooldown <= 0) {

            for (int i = 0; i < 1; i++) {
                Bullet bullet = new Bullet((int) this.getShape().getTranslateX(), (int) this.getShape().getTranslateY());
                setBulletPos(bullet);
                bullet.accelerate(5000);
                this.bullets.add(bullet);
                GameView.getInstance().getPane().getChildren().add(bullet.getShape());
                this.cooldown += 20;
            }
        }

    }
    private void  setBulletPos(Bullet bullet)
    {
        double moveX = Math.cos(Math.toRadians(this.getShape().getRotate()));
        double moveY = Math.sin(Math.toRadians(this.getShape().getRotate()));
        bullet.getShape().setTranslateX(bullet.getShape().getTranslateX() + moveX * 10 + 5);
        bullet.getShape().setTranslateY(bullet.getShape().getTranslateY() + moveY * 12);
        bullet.getShape().setRotate(this.getShape().getRotate());
    }
    public void cooldown() {
        if (this.cooldown > 0) {
            this.cooldown--;
        }
    }
    public ArrayList<Bullet> getBullets()
    {
        return bullets;
    }
    public AtomicInteger getPoints()
    {
        return points;
    }
}
