package wild_wild_math;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameStateManager {
    private LevelState levelState; //index 0
    private MenuState menuState; //index 1
    private ArrayList<GameState> gameStates; 
    private GameState currentState;
    public Canvas canvas;
    
    public GameStateManager(Canvas c) {
        this.canvas = c;
        this.levelState = new LevelState(c, this);
        this.menuState = new MenuState(c, this);
        this.currentState = menuState;
        
        this.gameStates = new ArrayList<GameState>();
        gameStates.add(menuState);
        gameStates.add(levelState);
    }
    void draw(GraphicsContext gc, Canvas c) {
        currentState.draw(gc, c);
    }
    
    void handleInput(Scene s, Canvas c) {
        currentState.handleMouse(s, c); 
        currentState.handleKeyboard(s, c);
        
    }
    void setState(int gs) {
        this.currentState = gameStates.get(gs);
    }
}
