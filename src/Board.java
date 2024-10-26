import javax.imageio.ImageIO;
import javax.swing.*;

import sprites.Enemy;
import sprites.Plasma;
import sprites.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements GameConstants {
    Timer timer;
    BufferedImage background;
    Player player;
    Enemy enemies[] = new Enemy[3];

    // List to store active plasma objects
    List<Plasma> plasmaList = new ArrayList<>();

    public Board() {
        loadBackground();
        player = new Player();
        loadEnemies();
        gameLoop();
        bindEvents();
        setFocusable(true);
    }

    private void gameOver(Graphics pen) {
        if (player.outOfScreen()) {
            pen.setFont(new Font("times", Font.BOLD, 100));
            pen.setColor(Color.RED);
            pen.drawString("Game Win", 300, 900 / 2);
            timer.stop();
        }
        for (Enemy enemy : enemies) {
            if (isCollide(enemy)) {
                pen.setFont(new Font("times", Font.BOLD, 100));
                pen.setColor(Color.RED);
                pen.drawString("Game Over", 300, 900 / 2);
                timer.stop();
            }
        }
    }

    private boolean isCollide(Enemy enemy) {
        int xDistance = Math.abs(player.x - enemy.x);
        int yDistance = Math.abs(player.y - enemy.y);
        int maxH = Math.max(player.h, enemy.h);
        int maxW = Math.max(player.w, enemy.w);
        return xDistance <= maxW - 100 && yDistance <= maxH - 100;
    }

    private void bindEvents() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.speed = 10;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.speed = -10;
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    loadPlasma(player.x); 
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.speed = 0;
            }
        });
    }

    private void loadPlasma(int playerCurrentPosition) {
        Plasma plasma = new Plasma(playerCurrentPosition);
        plasma.speed = 15; 
        plasmaList.add(plasma); // Storing plasma in the list
    }

    private void loadEnemies() {
        int x = 250;
        int gap = 370;
        int speed = 5;
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(x, speed);
            x += gap;
            speed += 5;
        }
    }

    private void gameLoop() {
        timer = new Timer(50, (e) -> repaint());
        timer.start();
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(Board.class.getResource("game-bg-image.jpg"));
        } catch (IOException e) {
            System.out.println("Unable to load Background Image");
            System.exit(1);
            e.printStackTrace();
        }
    }

    private void printEnemies(Graphics pen) {
        for (Enemy enemy : enemies) {
            enemy.draw(pen);
            enemy.move();
        }
    }

    private void printPlasma(Graphics pen) {
        for (int i = 0; i < plasmaList.size(); i++) {
            Plasma plasma = plasmaList.get(i);
            plasma.draw(pen);
            plasma.move();

            // Removing plasma if it goes off-screen
            if (plasma.x > GWIDTH) {
                plasmaList.remove(i);
                i--; 
            }
        }
    }

    @Override
    public void paintComponent(Graphics pen) {
        super.paintComponent(pen); // Clean up

        // Drawing logic is here
        pen.drawImage(background, 0, 0, GWIDTH, GHEIGHT, null);
        player.draw(pen);
        player.move();
        printEnemies(pen);
        printPlasma(pen);
        gameOver(pen);
    }
}
