package sample.model;

import  static sample.Main.screenHeight;
import  static sample.Main.screenWidth;
import java.util.ArrayList;
import java.util.List;

public class GlobalObj {
    private static GlobalObj instance;

    private List<Asteroid> asteroids = new ArrayList<>();
    private List<Bullet> bullets;
    private final Player player;
    private  int countOfAsteroids = 10;
    public GlobalObj()
    {
        this.player = new Player(screenWidth / 2, screenHeight / 2);
        bullets = this.player.getBullets();
    }

    public static GlobalObj getInstance() {
        if (instance == null) {
            instance = new GlobalObj();
        }
        return instance;
    }


    public void moveObjects()
    {
        asteroids.forEach(Asteroid::move);
        bullets.forEach(Bullet::move);
        player.move();
    }
    public void collisions() {


        for (Asteroid asteroid : asteroids) {
            for (Bullet bullet : bullets) {
                if (bullet.collide(asteroid)) {
                    asteroid.setActive(false);
                    bullet.setActive(false);
                    player.points.addAndGet(100);
                    break;
                }
            }
        }
    }
    public List<Asteroid> getAsteroids()
    {
        return asteroids;
    }
    public void setAsteroids(ArrayList<Asteroid> asteroids)
    {
        this.asteroids = asteroids;
    }
    public Player getPlayer()
    {
        return player;
    }

    public int getAsteroidsCounter() {
        return countOfAsteroids;
    }
    public void setAsteroidsCounter(int countOfAsteroids) {
        this.countOfAsteroids = countOfAsteroids;
    }
}
