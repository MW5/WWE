package wild_wild_math;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Score {
    public int points;
    public Font font;
    
    public Score(int p) {
        this.points = p;
        this.font = Font.font("Serif", 30);
    }
    public void setPoints(int p) {
        points = p;
    }
    public int getPoints() {
        return points;
    }
    public void addPoints(int p) {
        points += p;
    }
    public void draw(GraphicsContext gc) {
        gc.setFont(font);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.TOP);
        String scoreText = "Score: "+getPoints();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.CHOCOLATE);
        gc.fillText(scoreText, 30, 15);
        gc.strokeText(scoreText, 30, 15);
    }
}
