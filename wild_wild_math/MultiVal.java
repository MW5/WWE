package wild_wild_math;

import java.util.concurrent.ThreadLocalRandom;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MultiVal {
    protected int valueA;
    protected int valueB;
    protected int result;
    public Font font;
    public MultiVal(){
        this.font = Font.font("Serif", 30);
        valueA = ThreadLocalRandom.current().nextInt(1,9+1);
        valueB = ThreadLocalRandom.current().nextInt(1,9+1); 
    }
    public void setRandValueA() {
        valueA = ThreadLocalRandom.current().nextInt(1,9+1);
    }
    public void setRandValueB() {
        valueB = ThreadLocalRandom.current().nextInt(1,9+1);
    }
    public int getResult() {
        return valueA*valueB;
    }
    public int getFakeResult() {
        int fakeResult = valueA*valueB-10+ThreadLocalRandom.current().nextInt(1,9+1);
        if (fakeResult >= 0) {
            return fakeResult;
        } else {
            fakeResult = ThreadLocalRandom.current().nextInt(1,9+1);
            return fakeResult;
        }
    }
    public void draw(GraphicsContext gc, Canvas c) {
        gc.setFont(font);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setTextBaseline(VPos.TOP);
        String text = valueA+" * "+valueB+" = ";
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.CHOCOLATE);
        gc.fillText(text, c.getWidth()-30, 15);
        gc.strokeText(text, c.getWidth()-30, 15);
        gc.strokeLine(0, 30+font.getSize(),c.getWidth(), 60);
    }
}
