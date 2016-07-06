package entity;

import entity.Target;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class GoodTarget extends Target {
    public GoodTarget(Canvas c, int v) {
        super(c, v);
        this.correct = true;
        
        //LATER THEY MUST HAVE ONE IMAGE SET IN TARGET CLASS
        this.image = new Image( "goodTarget.png" );
    }
    
    public void setGoodValue(int gV) {
        this.value = gV;
    }
    public int getGoodValue() {
        return value;
    }
}
