package sprites;

import javax.swing.ImageIcon;

public class Enemy extends Sprite{
    public Enemy(int x , int speed){
        w = 200;
        h = 200;
        this.speed = speed;
        this.x = x;
        y = 250;
        image = new ImageIcon(Player.class.getResource("spider-enemy.gif"));
    }

    public void move(){
        if(y>900){
            y = 0;
        }
        y+=speed;
    }
}
