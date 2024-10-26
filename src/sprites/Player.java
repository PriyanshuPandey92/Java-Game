package sprites;

import javax.swing.ImageIcon;

public class Player extends Sprite{
    public Player(){
        w = 150;
        h = 150;
        x = 50;
        y = 625;
        image = new ImageIcon(Player.class.getResource("player.gif"));
    }

    public void move(){
        x+=speed;
    }

    public boolean outOfScreen(){
        return x>1500;
    }
    
}
