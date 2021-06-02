package sample;
import javafx.animation.AnimationTimer;

import javafx.scene.text.Text;
import sample.model.*;
import sample.view.*;
import javafx.fxml.FXML;

import  javafx.scene.input.KeyEvent;
import  javafx.scene.layout.Pane;

import java.util.Random;


public class Controller {

    @FXML private Pane pane;
    @FXML private Text text;

    private GameView gameView;
    AnimationTimer animationTimer;
    @FXML
    public void initialize() {

        gameView = new GameView(new GlobalObj(),pane);
        GameView.setInstance(gameView);
        gameView.addContent();
        pane.setStyle("-fx-background-color: black;");
        pane.setPrefSize(1024,768);
        Random random = new Random();
        KeyboardHandler keyboardhandler = new KeyboardHandler(gameView.getGlobals().getPlayer());
        this.pane.setOnKeyPressed(event -> keyboardhandler.keyPress(event.getCode()));
        this.pane.setOnKeyReleased(event -> keyboardhandler.keyRelease(event.getCode()));

       animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                if (gameView.getGlobals().getPlayer().isActive())
                {
                    text.setText("Points: " + gameView.getGlobals().getPlayer().getPoints());
                    keyboardhandler.handle();
                }
                gameView.update();


                gameView.getGlobals().getAsteroids().forEach(asteroid -> {
                    if (gameView.getGlobals().getPlayer().collide(asteroid))
                    {
                        text.setText("GAME OVER\nYou have collected "+gameView.getGlobals().getPlayer().getPoints()+" points\n(press R to restart)");
                        text.setX(Main.screenHeight/2);
                        text.setY(Main.screenWidth/4);
                        pane.setOnKeyPressed(event -> processKey(event));
                        gameView.getGlobals().getPlayer().setActive(false);
                        stop();
                    }
                });
                if(gameView.getGlobals().getAsteroids().size()<gameView.getGlobals().getAsteroidsCounter())
                {
                    Asteroid asteroid = new Asteroid((int)gameView.getGlobals().getPlayer().getWorldX()+ random.nextInt(2),
                            (int)gameView.getGlobals().getPlayer().getWorldY()+ random.nextInt(2));
                    gameView.getGlobals().getAsteroids().add(asteroid);
                    pane.getChildren().add(0,asteroid.getShape());
                }
            }
        };
       animationTimer.start();

    }
    public void processKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case R:
                animationTimer.stop();
                pane.getChildren().clear();
                pane.getChildren().add(text);
                text.setX(20);
                text.setY(30);
                initialize();
                break;
        }
    }
}
