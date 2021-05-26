package sample;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import sample.model.Player;

public class KeyboardHandler {
    private Map<KeyCode, Boolean> pressedKeys;

    Player player;

    public KeyboardHandler(Player player) {
        this.pressedKeys = new HashMap<>();
        this.player = player;
    }

    public void keyPress(KeyCode keycode) {
        this.pressedKeys.put(keycode, Boolean.TRUE);
    }

    public void keyRelease(KeyCode keycode) {
        this.pressedKeys.put(keycode, Boolean.FALSE);
    }


    public void handle() {

        if (this.pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
            player.turnLeft();
        }
        if (this.pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
            player.turnRight();
        }
        if (this.pressedKeys.getOrDefault(KeyCode.UP,false))
        {
            player.accelerate();
        }
        if (this.pressedKeys.getOrDefault(KeyCode.SPACE, false)) {
            player.shoot();
        }
    }
}
