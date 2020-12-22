
package trafficDemo;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author POO 2019/2020
 * @version maio/2020
 */
public class Blinker extends Circle {
    private boolean switchedOn;
    private AnimationTimer timer;
    private long lastUpdate;

    public Blinker(double centerX, double centerY) {
        super(centerX, centerY, 3.0f, Color.TRANSPARENT);
        switchedOn = false;
        lastUpdate = 0;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long pastTime = now - lastUpdate;
                if(pastTime > 350000000){
                    toggle();
                    lastUpdate = now;
                }
            }
        };
    }
    
    public boolean isSwitchedOn() {
        return switchedOn;
    }

    public void switchOn() {
        if (!switchedOn) {
            this.switchedOn = true;
            setFill(Color.GOLD);
        }
    }
    
    public void switchOff() {
        if (switchedOn) {
            this.switchedOn = false;
            setFill(Color.TRANSPARENT);
        }
    }
    
    public void toggle(){
        if(switchedOn){
            switchOff();
        }else{
            switchOn();
        }
    }
    
    public void start(){
        timer.start();
    }
    
    public void stop(){
        timer.stop();
        switchOff();
    }
}
