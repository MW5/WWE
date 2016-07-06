package entity;

import java.util.concurrent.ThreadLocalRandom;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public abstract class Target extends Circle {
    public boolean correct;
    public int value;
    public Image image;
    protected int minRadius;
    protected int maxRadius;
    public Font font;
    
    public Target(Canvas c, int v) {
        //TO BE IMPLEMENTED AS ONE IMAGE FOR TARGETS AFTER TESTING
        //this.image = new Image( "target.png" );
        this.minRadius = 30;
        this.maxRadius = 70;
        this.setRadius(getRandRadius());
        this.setCenterX(getRandPosX(c));
        this.setCenterY(getRandPosY(c));
        this.value = v;
    }
    public int getRandRadius() {
        return ThreadLocalRandom.current().nextInt(minRadius,maxRadius+1);
    }
    public int getRandPosX(Canvas c) {
        return ThreadLocalRandom.current().nextInt((int)this.getRadius(), (int)c.getWidth()-(int)this.getRadius()+1);
    }
    public int getRandPosY(Canvas c) {
        return ThreadLocalRandom.current().nextInt((int)this.getRadius()+60, (int)c.getHeight()-(int)this.getRadius()+1);
    }
    public void randRadiusChange() {
        this.setRadius(getRandRadius());
    }
    public void randPosChange(Canvas c) {
        this.setCenterX(getRandPosX(c));
        this.setCenterY(getRandPosY(c));
    }
    public void randomizeTarget(Canvas c) {
        //ADD while colliding loop 
        this.randRadiusChange();
        this.randPosChange(c);
    }
    public void setValue(int v) {
        this.value = v;
    }
    public int getValue() {
        return value;
    }
     public void draw(GraphicsContext gc) {
        //to draw target image
        gc.drawImage(image,
                        this.getCenterX()-this.getRadius(),
                        this.getCenterY()-this.getRadius(),
                        this.getRadius()*2,
                        this.getRadius()*2);
        //to draw numbers on the image
        gc.setFont(font);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.BLACK);
        gc.fillText(Integer.toString(this.value), this.getCenterX(), this.getCenterY());
    }
}
