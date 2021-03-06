
package trafficDemo;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 *
 * @author POO 2019/2020
 * @version maio/2020
 */
public class Car extends Group implements Drawable {
    private static final int WIDTH = 25;
    private static final int LENGTH = 55;
    
    private Timeline timeline;
    
    private final Color color;
    private double x;
    private double y;
    
    private Blinker frontLeftBlinker;
    private Blinker rearLeftBlinker;
    private Blinker frontRightBlinker;
    private Blinker rearRightBlinker;

    public Car(double frontLeftX, double frontLeftY, Color color) {
        timeline = new Timeline();
        this.x = frontLeftX;
        this.y = frontLeftY;
        this.color = color;
        build();
    }
    
    public Timeline getTimeline(){
        return timeline;
    }
    
    public void go(){
        timeline.play();
    }

    private void build() {
        Rectangle chassis = new Rectangle(x, y, WIDTH, LENGTH);
        
        //Setting the height and width of the arc for a rounded front and rear 
        chassis.setArcWidth(20); 
        chassis.setArcHeight(20);
        chassis.setFill(color);
        getChildren().add(chassis);
        
        Line mirrors = new Line(x - 2, y + 20, x + 27, y + 20);
        mirrors.setStrokeWidth(2);
        mirrors.setStroke(color);
        getChildren().add(mirrors);
 
        Rectangle windShields = new Rectangle(x + 3, y + 12, WIDTH -6, 37);
        windShields.setArcWidth(10);
        windShields.setArcHeight(10);
        windShields.setFill(Color.ALICEBLUE);
        getChildren().add(windShields);
        
        Rectangle carRoof = new Rectangle(x, y + 23, WIDTH, 18);
        carRoof.setArcWidth(10); 
        carRoof.setArcHeight(10);
        carRoof.setFill(color);
        getChildren().add(carRoof);
        
        frontLeftBlinker = new Blinker(x + 2, y + 2);
        getChildren().add(frontLeftBlinker);
        
        rearLeftBlinker = new Blinker(x + 2, y + LENGTH - 2);
        getChildren().add(rearLeftBlinker);
        
        frontRightBlinker = new Blinker(x + WIDTH - 2, y + 2);
        getChildren().add(frontRightBlinker);
        
        rearRightBlinker = new Blinker(x + WIDTH - 2, y + LENGTH - 2);
        getChildren().add(rearRightBlinker);
    }
    
    public void scale(double value) {
        
        //Creating the scale transformation 
        Scale scale = new Scale();

        //Setting the dimensions for the transformation 
        scale.setX(value);
        scale.setY(value);

        //Setting the pivot point for the transformation 
        scale.setPivotX(x + WIDTH/2); 
        scale.setPivotY(y + LENGTH/2); 

        //Adding the transformation
        getTransforms().addAll(scale);
    }
    
    public void rotate(double radAngle) {
        //creating the rotation transformation 
        Rotate rotate = new Rotate(); 

        //Setting the angle for the rotation 
        rotate.setAngle(radAngle); 

        //Setting pivot points for the rotation 
        rotate.setPivotX(x + WIDTH/2); 
        rotate.setPivotY(y + LENGTH/2); 

        //Adding the transformation
        getTransforms().addAll(rotate);
    }
    
    public void translate(double distanceX, double distanceY) {
        //Creating the translation transformation
        Translate translate = new Translate();

        //Setting the X,Y coordinates to apply the translation
        translate.setX(distanceX);
        translate.setY(distanceY);
        
        this.x += distanceX;
        this.y += distanceY;

        // Adding the transformation
        getTransforms().addAll(translate);
    }
    
    public void signalLeftTurn() {
        frontLeftBlinker.start();
        rearLeftBlinker.start();
    }
    
    public void signalRightTurn() {
        frontRightBlinker.start();
        rearRightBlinker.start();
    }
    
    public void signalTurnStop(){
        frontLeftBlinker.stop();
        rearLeftBlinker.stop();
        frontRightBlinker.stop();
        rearRightBlinker.stop();
    }

    @Override
    public void addTo(Group group) {
        group.getChildren().add(this);
    }

}

