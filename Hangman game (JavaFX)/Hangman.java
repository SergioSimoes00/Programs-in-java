
package hangman;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;

/**
 *
 * @author POO 2019/2020
 * @version june/2020
 */
public class Hangman extends Group {
    
    private int count = 0;

    public Hangman () {
        createContent();
    }
    
    private void createContent() {
        Line line1 = new Line(0, 200, 100, 200);
        line1.setStrokeWidth(10);
        line1.setStrokeLineCap(StrokeLineCap.ROUND);
        line1.setStroke(Color.TRANSPARENT);

        Line line2 = new Line(50, 200, 50, 0);
        line2.setStrokeWidth(10);
        line2.setStrokeLineCap(StrokeLineCap.ROUND);
        line2.setStroke(Color.TRANSPARENT);
        
        Line line3 = new Line(50, 0, 150, 0);
        line3.setStrokeWidth(10);
        line3.setStrokeLineCap(StrokeLineCap.ROUND);
        line3.setStroke(Color.TRANSPARENT);
        
        Line line4 = new Line(100, 0, 50, 50);
        line4.setStrokeWidth(10);
        line4.setStrokeLineCap(StrokeLineCap.ROUND);
        line4.setStroke(Color.TRANSPARENT);
        
        Circle head = new Circle(150, 50, 15.0f);
        head.setStrokeWidth(3);
        head.setFill(Color.TRANSPARENT);
        
        // Left arm
        Line line5 = new Line(150, 65, 115, 105);
        line5.setStrokeWidth(10);
        line5.setStrokeLineCap(StrokeLineCap.ROUND);
        line5.setStroke(Color.TRANSPARENT);
             
        // Right arm
        Line line6 = new Line(150, 65, 185, 105);
        line6.setStrokeWidth(10);
        line6.setStrokeLineCap(StrokeLineCap.ROUND);
        line6.setStroke(Color.TRANSPARENT);
        
        // Trunk
        Line line7 = new Line(150, 65, 150, 110);
        line7.setStrokeWidth(10);
        line7.setStrokeLineCap(StrokeLineCap.ROUND);
        line7.setStroke(Color.TRANSPARENT);
        
        // Left leg
        Line line8 = new Line(150, 110, 115, 150);
        line8.setStrokeWidth(10);
        line8.setStrokeLineCap(StrokeLineCap.ROUND);
        line8.setStroke(Color.TRANSPARENT);
             
        // Right leg
        Line line9 = new Line(150, 110, 185, 150);
        line9.setStrokeWidth(10);
        line9.setStrokeLineCap(StrokeLineCap.ROUND);
        line9.setStroke(Color.TRANSPARENT);
        
        // Rope
        Line line10 = new Line(150, 0, 150, 55);
        line10.setStrokeWidth(10);
        line10.setStrokeLineCap(StrokeLineCap.ROUND);
        line10.setStroke(Color.TRANSPARENT);
        
        getChildren().addAll(line1, line2, line3, line4, head, line5, line6, line7, line8, line9, line10);
    }
    
    public void next() {
        Node node = getChildren().get(count++);
        if (node instanceof Line){
            ((Line) node).setStroke(Color.BLACK);
            applyFadeTransition((Line) node);
            applyTranslation((Circle) getChildren().get(4));
        }else{
            ((Circle) node).setFill(Color.BLACK);
            applyFadeTransition((Circle) node);
        }
    }
    
    public boolean isComplete() {
        if (count == getChildren().size()) {           
            return true;
        }
        
        return false;
    }
    
    public void applyFadeTransition(Node node){
        FadeTransition transicao = new FadeTransition(Duration.millis(2000.0), node);
        transicao.setFromValue(0.1);
        transicao.setToValue(1.0);
        transicao.setAutoReverse(false);
        transicao.play();
    }
    
    public void applyTranslation(Node node){
        if(isComplete()){
            TranslateTransition transicao = new TranslateTransition(Duration.millis(2000.0), node);
            transicao.setByX(10.0);
            transicao.setByY(10.0);
            transicao.setCycleCount(1);
            transicao.setAutoReverse(false);
            transicao.play();
        }
    }
}
