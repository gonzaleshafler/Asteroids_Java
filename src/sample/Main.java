package sample;


import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.items.Asteroid;
import sample.items.Bullet;
import sample.view.GameView;
import java.util.Random;

public class Main extends Application {

    public static int screenWidth = 1024;
    public static int screenHeight = 1024;


    @Override
    public void start(Stage stage) {

        Pane pane = GameView.getInstance().getPane();

        pane.setPrefSize(screenWidth,screenHeight);
        pane.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(pane);
        pane.setMinWidth(1024);
        pane.setMinHeight(1024);
        stage.setTitle("Asteroids");
        stage.setScene(scene);
        stage.show();
        GameView.getInstance().addContent();
        Random random = new Random();
        KeyboardHandler keyboardhandler = new KeyboardHandler(GameView.getInstance().getGlobals().getPlayer());
        scene.setOnKeyPressed(event -> keyboardhandler.keyPress(event.getCode()));
        scene.setOnKeyReleased(event -> keyboardhandler.keyRelease(event.getCode()));


        new AnimationTimer(){
            @Override
            public void handle(long now) {
                if (GameView.getInstance().getGlobals().getPlayer().isActive())
                {
                    keyboardhandler.handle();
                }

                GameView.getInstance().getGlobals().getPlayer().move();
                GameView.getInstance().getGlobals().getPlayer().cooldown();
                GameView.getInstance().getGlobals().getAsteroids().forEach(Asteroid::move);
                GameView.getInstance().getGlobals().getPlayer().getBullets().forEach(Bullet::move);


                GameView.getInstance().getGlobals().getAsteroids().forEach(asteroid -> {
                    if (GameView.getInstance().getGlobals().getPlayer().collide(asteroid))
                        stop();
                });
                if(GameView.getInstance().getGlobals().getAsteroids().size()<5)
                {
                    Asteroid asteroid = new Asteroid((int)GameView.getInstance().getGlobals().getPlayer().getWorldX()* random.nextInt(10),(int)GameView.getInstance().getGlobals().getPlayer().getWorldY()* random.nextInt(10));
                    GameView.getInstance().getGlobals().getAsteroids().add(asteroid);
                    pane.getChildren().add(asteroid.getShape());
                }

                for(int i = 0; i< GameView.getInstance().getGlobals().getAsteroids().size();i++)
                {
                    for(int  j =0; j< GameView.getInstance().getGlobals().getPlayer().getBullets().size();j++)
                    {
                        if (GameView.getInstance().getGlobals().getPlayer().getBullets().get(j).collide(GameView.getInstance().getGlobals().getAsteroids().get(i)))
                        {
                            pane.getChildren().remove(GameView.getInstance().getGlobals().getAsteroids().get(i).getShape());
                            pane.getChildren().remove(GameView.getInstance().getGlobals().getPlayer().getBullets().get(j).getShape());
                            GameView.getInstance().getGlobals().getAsteroids().remove(i--);
                            GameView.getInstance().getGlobals().getPlayer().getBullets().remove(j);

                            System.out.println(GameView.getInstance().getGlobals().getPlayer().points.toString());
                            break;

                        }
                    }
                }

//                asteroids.forEach(asteroid -> {
//                    player.bullets.forEach(bullet -> {
//                        if(bullet.collide(asteroid))
//                        {
//                            stop();
//                        }
//                    });
//                });

            }
        }.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
