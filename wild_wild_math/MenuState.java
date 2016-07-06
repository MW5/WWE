package wild_wild_math;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.VPos;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MenuState extends GameState {
    private final Font font;
    private final Color textColor = Color.BLANCHEDALMOND;
    private final Color textStrokeColor = Color.CHOCOLATE;
    private final Color menuOptBackgrColor = Color.CHOCOLATE;
    private final Color backgroundColor = Color.BEIGE;
    //private final background Image backgroundImage = 
    private final String[] menuOptionStrings;
    private final Rectangle[] menuOptionRectangles;
    private static final int NONE = 10;
    private static final int START = 0;
    private static final int HIGHSCORE = 1;
    private static final int QUIT = 2;
    private Rectangle startOpt;
    private Rectangle highScoreOpt;
    private Rectangle quitOpt;
    private int activeOpt;
    public GameStateManager gsm;
    
    public MenuState(Canvas c, GameStateManager gsm) {
        this.activeOpt = 4;
        this.gsm = gsm;
        this.font = Font.font("Serif", 50);
        this.menuOptionStrings = new String[] {"Start", "Highscore", "Quit"};
        this.menuOptionRectangles = new Rectangle[] {startOpt, highScoreOpt, quitOpt};
        for (int i=0; i<menuOptionStrings.length; i++) {
            menuOptionRectangles[i] = new Rectangle(c.getWidth()/2-250/2, c.getHeight()/2.91+i*70, 250, font.getSize());
        }
    }
    void handleMouse(Scene s, Canvas c) {
        s.setOnMouseMoved(
            new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    if (menuOptionRectangles[0].contains(e.getX(),e.getY())) {
                        setActiveOpt(START);
                    }
                    if (menuOptionRectangles[HIGHSCORE].contains(e.getX(),e.getY())) {
                        setActiveOpt(HIGHSCORE);
                    }
                    if (menuOptionRectangles[QUIT].contains(e.getX(),e.getY())) {
                        setActiveOpt(QUIT);
                    }
                    if (!menuOptionRectangles[0].contains(e.getX(),e.getY())&&
                        !menuOptionRectangles[HIGHSCORE].contains(e.getX(),e.getY())&&
                        !menuOptionRectangles[QUIT].contains(e.getX(),e.getY())) {
                        setActiveOpt(NONE);
                    }
                }
            }
        );
        s.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    if (menuOptionRectangles[START].contains(e.getX(),e.getY())) {
                        gsm.setState(1);
                    }
                    if (menuOptionRectangles[HIGHSCORE].contains(e.getX(),e.getY())) {
                        System.out.println("highscore to be implemented");
                    }
                    if (menuOptionRectangles[QUIT].contains(e.getX(),e.getY())) {
                        System.exit(0);
                    }
                }
            }
        );
    }
    void handleKeyboard(Scene s, Canvas c) {
        ArrayList<String> input = new ArrayList<String>();
        s.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) ) {
                        input.add( code );
                    }
                    if (input.contains("ESCAPE")) {
                        System.exit(0);
                    }
                }
            });
 
        s.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });
    }
    void draw(GraphicsContext gc, Canvas c) {
        drawBackground(gc, c); 
        drawOptions(gc, c);
    }
    
    private void drawBackground (GraphicsContext gc, Canvas c) {
        gc.setFill(backgroundColor);
        gc.fillRect(0,0, c.getWidth(), c.getHeight());
        
        gc.setFont(font);
        gc.setTextAlign(TextAlignment.CENTER);
        String scoreText = "WILD WILD MATH";
        gc.setFill(textColor);
        gc.setStroke(textStrokeColor);
        gc.fillText(scoreText, c.getWidth()/2, c.getHeight()/10);
        gc.strokeText(scoreText, c.getWidth()/2, c.getHeight()/10);
    }
    
    private void drawOptions(GraphicsContext gc, Canvas c) {
        gc.setFont(font);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.TOP);
        for (int i=0; i<menuOptionStrings.length; i++) {
            gc.setStroke(menuOptBackgrColor);
            if (i == activeOpt) {
                gc.fillRect(c.getWidth()/2-250/2, c.getHeight()/2.91+i*70, 250, font.getSize()); //TESTING
                gc.strokeRect(c.getWidth()/2-250/2, c.getHeight()/2.91+i*70, 250, font.getSize());
            } else {
                gc.strokeRect(c.getWidth()/2-250/2, c.getHeight()/2.91+i*70, 250, font.getSize());
            }
            gc.setFill(textColor);
            gc.setStroke(textStrokeColor);
            gc.fillText(menuOptionStrings[i], c.getWidth()/2, c.getHeight()/3+i*70);
            gc.strokeText(menuOptionStrings[i], c.getWidth()/2, c.getHeight()/3+i*70);
        }
    }
    private void setActiveOpt(int aO) {
        this.activeOpt = aO;
    }
}
