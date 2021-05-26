package sample.view;

import javafx.scene.layout.Pane;
import sample.model.Asteroid;
import sample.model.GlobalObj;

import java.util.ArrayList;
import java.util.Random;

import static sample.Main.screenHeight;
import static sample.Main.screenWidth;

public class GameView {
    private final GlobalObj globalObj;
    private  Pane pane;
    private static GameView instance;
    public GameView(GlobalObj globalObj, Pane pane)
    {
        this.globalObj = globalObj;
        this.pane = pane;
    }

    public static GameView getInstance() {
        if (instance == null) {
            instance = new GameView();
        }
        return instance;
    }
    public GameView()
    {
        this.pane = new Pane();
        this.globalObj = new GlobalObj();
    }

    public static void setInstance(GameView gameView) {
        instance = gameView;
    }

    public void addContent()
    {
        pane.getChildren().add(globalObj.getPlayer().getShape());

        Random random = new Random();
        ArrayList<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            Asteroid asteroid = new Asteroid(random.nextInt(screenWidth/3),random.nextInt(screenHeight));
            asteroids.add(asteroid);
        }
        asteroids.forEach(asteroid -> pane.getChildren().add(0,asteroid.getShape()));
        globalObj.setAsteroids(asteroids);

    }
    public GlobalObj  getGlobals()
    {
        return globalObj;
    }

    public Pane getPane() {
        return pane;
    }
    public void  setPane(Pane pane)
    {
        this.pane = pane;
    }
}
