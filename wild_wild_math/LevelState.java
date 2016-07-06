package wild_wild_math;

import entity.GoodTarget;
import entity.BadTarget;
import entity.Target;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LevelState extends GameState {
    private final Target[] allTargets;
    private final BadTarget[] badTargets;
    private final BadTarget badTarg1;
    private final BadTarget badTarg2;
    private final BadTarget badTarg3;
    private final GoodTarget goodTarg;
    private final MultiVal multiVal;
    private final Score score;
    public GameStateManager gsm;
    public LevelState (Canvas c, GameStateManager gsm) {
            //score
        this.score = new Score(0);
            //multiplication value generator
        this.multiVal = new MultiVal();

        this.badTarg1 = new BadTarget(c,  multiVal.getFakeResult());
        this.badTarg2 = new BadTarget(c, multiVal.getFakeResult());
        this.badTarg3 = new BadTarget(c, multiVal.getFakeResult());
        this.badTargets = new BadTarget[] {badTarg1, badTarg2, badTarg3};
        this.goodTarg = new GoodTarget(c, multiVal.getResult());
        //for collision detection
        this.allTargets = new Target[] {goodTarg, badTarg1, badTarg2, badTarg3};

        this.gsm = gsm;
        handleCollisionAndDuplicateVals(c);
        
    }
    void handleMouse(Scene s, Canvas c) {
        s.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    if (goodTarg.contains(e.getX(), e.getY())) {
                        //update score
                        score.addPoints(1);
                        //set new values to multiply
                        multiVal.setRandValueA();
                        multiVal.setRandValueB();

                            //update targets with new values
                        goodTarg.setValue(multiVal.getResult());
                        for (BadTarget bT : badTargets) {
                            bT.setValue(multiVal.getFakeResult());
                        }

                        for (Target t : allTargets) {
                            t.randomizeTarget(c);
                        }
                        handleCollisionAndDuplicateVals(c);
                        //TESTING
                        for (int i=0; i<allTargets.length; i++) {
                        System.out.println("Obiekt "+allTargets[i].getValue()+" X: "+allTargets[i].getCenterX()+ " Y: "+allTargets[i].getCenterY());
                        }
                    }
                }
            }
        );
    }
    private void handleCollisionAndDuplicateVals(Canvas c) {
        System.out.println("NEW");
        //COLLISION DETECTION THAT WORKS!
        for (int i=0; i<allTargets.length; i++) {
            if(i!=0) {
                for (int j=i; j>0; j--) {
                    //TO DO HANDLE COLLISION DETECTION ON TARGET INITIALIZATION
                    //handles target collisons
                    System.out.println("Checked pair: "+allTargets[i].getValue()+" "+allTargets[j-1].getValue());
                    while (isCollision(allTargets[j-1].getCenterX(),
                                            allTargets[i].getCenterX(),
                                            allTargets[j-1].getCenterY(),
                                            allTargets[i].getCenterY(),
                                            allTargets[j-1].getRadius(),
                                            allTargets[i].getRadius())) {
                        System.out.println("Pair: "+allTargets[i].getValue()+" "+allTargets[j-1].getValue());
                        allTargets[i].randomizeTarget(c);
                        j=i;
                    }
                    //handles duplicate values
                    while(allTargets[i].getValue()==allTargets[j-1].getValue()) {
                        allTargets[i].setValue(multiVal.getFakeResult());
                    }
                }
            }
        }
    }
 
    private boolean isCollision(double x1, double x2, double y1, double y2, double r1, double r2) {
        double xDif = x1 - x2;
        double yDif = y1 - y2;
        double distanceSquared = xDif * xDif + yDif * yDif;
        return distanceSquared < (r1 + r2) * (r1 + r2);
    };  
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
                        gsm.setState(0);
                        score.setPoints(0);
                        multiVal.setRandValueA();
                        multiVal.setRandValueB();
                        goodTarg.setValue(multiVal.getResult());
                        for (Target t : allTargets) {
                            t.randomizeTarget(c);
                        }
                            //update targets with new values
                        goodTarg.setValue(multiVal.getResult());
                        for (BadTarget bT : badTargets) {
                            bT.setValue(multiVal.getFakeResult());
                        }
                        for (Target t : allTargets) {
                                    t.randomizeTarget(c);
                                }
                        handleCollisionAndDuplicateVals(c);
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
        //background
        gc.setFill(Color.IVORY);
        gc.fillRect(0,0, c.getWidth(), c.getHeight());
        //target
        goodTarg.draw(gc);
        
        for (BadTarget t : badTargets) {
            t.draw(gc);
        }
        //score
        score.draw(gc);
        //equation
        multiVal.draw(gc, c);
    }     
}
