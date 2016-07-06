package wild_wild_math;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class wild_wild_math extends Application {
    
    @Override
    public void start(Stage theStage) {
        //window title
        theStage.setTitle("Wild Wild Math");
        //group of elements to be on the scene
        Group root = new Group();
        
        //scene to show group elements
        Scene theScene = new Scene(root);
        //put the scene on the stage
        theStage.setScene(theScene);
        //create new canvas to draw on
        Canvas canvas = new Canvas(800,600);
        //canvas.setRotate(30);
        //add canvas to the group of elements to be on the scene
        root.getChildren().add(canvas);
        
        //create required objects
            //gsm
        GameStateManager gsm = new GameStateManager(canvas);
        
        //extract graphic context from canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gsm.handleInput(theScene, canvas);
                gsm.draw(gc, canvas);
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
