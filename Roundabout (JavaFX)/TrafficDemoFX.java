
package trafficDemo;

import java.util.logging.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author POO 2019/2020
 * @version maio/2020
 */
public class TrafficDemoFX extends Application {
    
    public static final int WINDOW_WIDTH = 800;    
    public static final int WINDOW_HEIGHT = 600;

    public static final double CENTER_X = WINDOW_WIDTH / 2;
    public static final double CENTER_Y = WINDOW_HEIGHT / 2 - 60;
    
    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        
        roundaboutTraffic(root);
        
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        primaryStage.setTitle("Roundabout Traffic Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void roundaboutTraffic(Group group) {
        
        Roundabout roundabout = new Roundabout(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2 - 60, Color.SILVER);
        roundabout.addTo(group);
        
        putTitle(group);

        createButtons(group);
    }

    public void putTitle(Group group) {

        Font verdana = Font.font("Verdana", FontWeight.BOLD, 20);
        
        Text title = new Text(CENTER_X - 190, 35, String.format("%s", "Circulação correta numa rotunda"));
        title.setFont(verdana);
        title.setFill(Color.DARKGREY);

        group.getChildren().add(title);
    }
    
    public void createCarsFirstExit(Group group) {
        Car orangeCar1 = new Car(CENTER_X + 400, CENTER_Y - 50, Color.DARKORANGE);              
        orangeCar1.rotate(90);
        orangeCar1.addTo(group);
        
        PathTransition pathTransition = new PathTransition();
        
        Path path = new Path();
        
        MoveTo moveTo = new MoveTo(800, 220);
        
        LineTo line1 = new LineTo(550, 220);
        CubicCurveTo curve1 = new CubicCurveTo(550, 220, 520, 215, 510, 190);
        CubicCurveTo curve2 = new CubicCurveTo(510, 190, 505, 155, 450, 135);
        CubicCurveTo curve3 = new CubicCurveTo(450, 135, 420, 125, 420, 70);
        
        path.getElements().add(moveTo);
        path.getElements().addAll(line1, curve1, curve2, curve3);
        
        pathTransition.setNode(orangeCar1);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        
        EventHandler setInvisible = e -> {
            orangeCar1.setVisible(false);
        };
        
        EventHandler rightLights = e -> {
            orangeCar1.signalRightTurn();
        };
        
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10000), setInvisible);
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(4000), rightLights);
        
        orangeCar1.getTimeline().getKeyFrames().addAll(keyFrame, keyFrame1);
        
        pathTransition.play();
        orangeCar1.getTimeline().play();

//        Car orangeCar2 = new Car(CENTER_X + 70, CENTER_Y - 105, Color.DARKORANGE);
//        orangeCar2.addTo(group);
//        orangeCar2.signalRightTurn();
//        orangeCar2.rotate(-45);
//
//        Car orangeCar3 = new Car(CENTER_X + 5, CENTER_Y - 205, Color.DARKORANGE);
//        orangeCar3.addTo(group);
//        orangeCar3.signalRightTurn();
    }
    
    public void createCarsSecondExit(Group group) throws InterruptedException {
        Car redCar1 = new Car(CENTER_X - 440, CENTER_Y - 5, Color.DARKRED);
        redCar1.addTo(group);
        redCar1.rotate(90);
        
        EventHandler leftLights = e -> {
            redCar1.signalLeftTurn();
        };
        
        EventHandler rightLights = e -> {
            redCar1.signalRightTurn();
        };
        
        EventHandler lightsOff = e -> {
            redCar1.signalTurnStop();
        };
        
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(9000), new KeyValue(redCar1.translateXProperty(), 295),
                new KeyValue(redCar1.translateYProperty(), 0), new KeyValue(redCar1.rotateProperty(), 0));
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(7000), rightLights);
        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(12000), new KeyValue(redCar1.translateXProperty(), 357.5),
                new KeyValue(redCar1.translateYProperty(), 30), new KeyValue(redCar1.rotateProperty(), 35));
        KeyFrame keyFrame4 = new KeyFrame(Duration.millis(11001), lightsOff);
        KeyFrame keyFrame5 = new KeyFrame(Duration.millis(11002), leftLights);
        KeyFrame keyFrame6 = new KeyFrame(Duration.millis(15000), new KeyValue(redCar1.translateXProperty(), 420),
                new KeyValue(redCar1.translateYProperty(), 60), new KeyValue(redCar1.rotateProperty(), 0));
        KeyFrame keyFrame7 = new KeyFrame(Duration.millis(15001), lightsOff);
        KeyFrame keyFrame8 = new KeyFrame(Duration.millis(17000), rightLights);
        KeyFrame keyFrame9 = new KeyFrame(Duration.millis(18000), new KeyValue(redCar1.translateXProperty(), 490),
                new KeyValue(redCar1.translateYProperty(), 30), new KeyValue(redCar1.rotateProperty(), -35));
        KeyFrame keyFrame10 = new KeyFrame(Duration.millis(21000), new KeyValue(redCar1.translateXProperty(), 560),
                new KeyValue(redCar1.translateYProperty(), 0), new KeyValue(redCar1.rotateProperty(), 0));
        KeyFrame keyFrame11 = new KeyFrame(Duration.millis(22000), lightsOff);
        KeyFrame keyFrame12 = new KeyFrame(Duration.millis(30000), new KeyValue(redCar1.translateXProperty(), 870),
                new KeyValue(redCar1.translateYProperty(), 0), new KeyValue(redCar1.rotateProperty(), 0));
        
        redCar1.getTimeline().getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3, keyFrame4, keyFrame5,
                keyFrame6, keyFrame7, keyFrame8, keyFrame9, keyFrame10, keyFrame11, keyFrame12);
        redCar1.getTimeline().playFromStart();
        

//        Car redCar2 = new Car(CENTER_X - 45, CENTER_Y + 45, Color.DARKRED);
//        redCar2.addTo(group);
//        redCar2.signalRightTurn();
//        redCar2.rotate(125);
//        
//        Car redCar3 = new Car(CENTER_X + 85, CENTER_Y + 45, Color.DARKRED);
//        redCar3.addTo(group);
//        redCar3.signalRightTurn();
//        redCar3.rotate(40);
//        
//        Car redCar4 = new Car(CENTER_X + 180, CENTER_Y - 5, Color.DARKRED);
//        redCar4.addTo(group);
//        redCar4.signalRightTurn();
//        redCar4.rotate(90);
    }
        
    public void createCarsThirdExit(Group group) {
        Car greenCar1 = new Car(CENTER_X + 5, CENTER_Y + 150, Color.SEAGREEN);
        greenCar1.addTo(group);
        greenCar1.rotate(90);
        
        PathTransition pathTransition = new PathTransition();
        
        Path path = new Path();
        
        MoveTo moveTo = new MoveTo(420, 400);
        
        LineTo line1 = new LineTo(420, 380);
        CubicCurveTo curve1 = new CubicCurveTo(420, 380, 425, 330, 450, 300);
        CubicCurveTo curve2 = new CubicCurveTo(450, 300, 520, 230, 440, 160);
        CubicCurveTo curve3 = new CubicCurveTo(440, 160, 370, 120, 300, 175);
        CubicCurveTo curve4 = new CubicCurveTo(300, 175, 280, 205, 220, 215);
        LineTo line2 = new LineTo(-60, 220);
        
        path.getElements().add(moveTo);
        path.getElements().addAll(line1, curve1, curve2, curve3, curve4, line2);
        
        pathTransition.setNode(greenCar1);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.millis(15000));
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        
        EventHandler leftLights = e -> {
            greenCar1.signalLeftTurn();
        };
        
        EventHandler rightLights = e -> {
            greenCar1.signalRightTurn();
        };
        
        EventHandler lightsOff = e -> {
            greenCar1.signalTurnStop();
        };
        
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(2000), leftLights);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(2001), lightsOff);
        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(4000), rightLights);
        
        greenCar1.getTimeline().getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        
        pathTransition.play();
        greenCar1.getTimeline().play();

//        Car greenCar2 = new Car(CENTER_X + 65, CENTER_Y, Color.SEAGREEN);
//        greenCar2.addTo(group);
//        greenCar2.signalLeftTurn();
//        greenCar2.rotate(20);
//        
//        Car greenCar3 = new Car(CENTER_X - 80, CENTER_Y - 120, Color.SEAGREEN);
//        greenCar3.addTo(group);
//        greenCar3.signalRightTurn();
//        greenCar3.rotate(-125);
//        
//        Car greenCar4 = new Car(CENTER_X - 200, CENTER_Y - 45, Color.SEAGREEN);
//        greenCar4.addTo(group);
//        greenCar4.signalRightTurn();
//        greenCar4.rotate(-90);
    }
    
    public void createButtons(Group group) {

        Font verdana = Font.font("Verdana", 14);
        
        Button btn1 = new Button();
        btn1.setText("1ª saída: tomar a via mais à direita");
        btn1.setFont(verdana);
        btn1.setTextFill(Color.DARKORANGE);
        btn1.setMinWidth(650);
        btn1.setFocusTraversable(false);
        btn1.setLayoutX(80);
        btn1.setLayoutY(WINDOW_HEIGHT - 140);
        btn1.setOnAction(e -> createCarsFirstExit(group));
        
        Button btn2 = new Button();
        btn2.setText("2ª saída: tomar a via da esquerda, a seguir à 1ª saída passar para a via mais à direita");
        btn2.setFont(verdana);
        btn2.setTextFill(Color.DARKRED);
        btn2.setMinWidth(650);
        btn2.setFocusTraversable(false);
        btn2.setLayoutX(80);
        btn2.setLayoutY(WINDOW_HEIGHT - 100);
        btn2.setOnAction(e -> {
            try {
                createCarsSecondExit(group);
            } catch (InterruptedException ex) {
                Logger.getLogger(TrafficDemoFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button btn3 = new Button();
        btn3.setText("3ª saída: tomar a via da esquerda, a seguir à 2ª saída passar para a via mais à direita");
        btn3.setFont(verdana);
        btn3.setTextFill(Color.SEAGREEN);
        btn3.setMinWidth(650);
        btn3.setFocusTraversable(false);
        btn3.setLayoutX(80);
        btn3.setLayoutY(WINDOW_HEIGHT - 60);
        btn3.setOnAction(e -> createCarsThirdExit(group));

        group.getChildren().addAll(btn1, btn2, btn3);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
