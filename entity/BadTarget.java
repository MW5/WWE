package entity;

import java.util.Random;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class BadTarget extends Target {
    public BadTarget(Canvas c,int v) {
        super(c, v);
        this.correct = false;
        
        //LATER THEY MUST HAVE ONE IMAGE SET IN TARGET CLASS
        this.image = new Image( "badTarget.png" );
    }
    //gets the right value and changes it slightly
    public void setBadValue(int r) {
        Random generator = new Random(); 
        this.value = r - 5 + generator.nextInt(10)+1;
    }
    public int getBadValue() {
        return value;
    }
}
