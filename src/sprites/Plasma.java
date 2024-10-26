package sprites;

import javax.swing.ImageIcon;

public class Plasma extends Sprite {
    public Plasma(int x){
        w = 130;
        h = 130;
        this.x = x;
        y = 625;
        image = new ImageIcon(Plasma.class.getResource("Plasma.gif"));
    }
    public void move(){
        x+=speed;
    }
}
