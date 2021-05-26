package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    public static int screenWidth = 1024;
    public static int screenHeight = 768;

/*    @Override
    public void start(Stage stage) {

        Pane pane = GameView.getInstance().getPane();

        pane.setPrefSize(screenWidth,screenHeight);
        pane.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(pane);
        pane.setMinSize(screenWidth,screenHeight);
        stage.setTitle("Asteroids");
        stage.setScene(scene);
        stage.show();


        GameView.getInstance().addContent();
        Random random = new Random();

        Text text = new Text(20,30,"Points: ");
        text.setFont(Font.font ("Verdana", 40));
        text.setFill(Color.WHITE);
        pane.getChildren().add(1,text);


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
                    {
                        text.setText("GAME OVER\nYou have collected "+GameView.getInstance().getGlobals().getPlayer().getPoints()+" points");
                        text.setX(screenWidth/4);
                        text.setY(screenHeight/2);
                        GameView.getInstance().getGlobals().getPlayer().setActive(false);
                    }

                });
                if(GameView.getInstance().getGlobals().getAsteroids().size()<GameView.getInstance().getGlobals().getAsteroidsCounter())
                {
                    Asteroid asteroid = new Asteroid((int)GameView.getInstance().getGlobals().getPlayer().getWorldX()+ random.nextInt(2),
                                                        (int)GameView.getInstance().getGlobals().getPlayer().getWorldY()+ random.nextInt(2));

                    GameView.getInstance().getGlobals().getAsteroids().add(asteroid);
                    pane.getChildren().add(0,asteroid.getShape());
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
                            GameView.getInstance().getGlobals().getPlayer().points.addAndGet(100);
                            text.setText("Points: " + GameView.getInstance().getGlobals().getPlayer().getPoints().addAndGet(1000));
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
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Asteroids");
        primaryStage.setScene(new Scene(root, screenWidth, screenHeight));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
