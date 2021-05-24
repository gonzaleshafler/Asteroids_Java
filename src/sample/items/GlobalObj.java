package sample.items;

import  static sample.Main.screenHeight;
import  static sample.Main.screenWidth;
import java.util.ArrayList;
import java.util.List;

public class GlobalObj {
    private static GlobalObj instance;

    private List<Asteroid> asteroids = new ArrayList<>();
    private final Player player;

    public GlobalObj()
    {
        this.player = new Player(screenWidth / 2, screenHeight / 2);
    }

    public static GlobalObj getInstance() {
        if (instance == null) {
            instance = new GlobalObj();
        }
        return instance;
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
}
