package sample.items;

import java.util.Random;

public class Asteroid extends GameObject {


    private final double rotationalMovement;


    public Asteroid(int x, int y) {
        super(new PolygonGenerator().createPolygon(),x,y);
        Random random = new Random();
        super.getShape().setRotate(random.nextInt(360));

        int acceleration = 1 +random.nextInt(15);
        accelerate(acceleration);
        this.rotationalMovement = 0.5 - random.nextDouble();
    }
    @Override
    public void move() {
        super.move();
        super.getShape().setRotate(super.getShape().getRotate() + rotationalMovement);
    }

}
