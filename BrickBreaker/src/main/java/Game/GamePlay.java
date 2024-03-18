package Game;

import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GamePlay extends JPanel implements ActionListener, KeyListener {

    private boolean play = false;
    private int totalBrikas = 32;
    private Timer timer;
    private int score = 0;
    private final int delay = 10;
    private int ballposx = randInt();
    private int ballposy = 350;
    private int ballxdir = -2;
    private int ballydir = -3;
    private int playerx = 300;
    private Mapgenerator map;

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay, this);
        timer.start();
        map = new Mapgenerator(4, 8);

    }

    public int randInt() {
        Random ran = new Random();
        int x = ran.nextInt(500) + 100;
        return x;
    }

    public void paint(Graphics g) {
        // background color black
        g.setColor(Color.white);
        g.fillRect(1, 1, 692, 592);

        //yellow border
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 3, 3, 592);
        g.fillRect(683, 3, 3, 592);
        g.setColor(Color.red);
        g.fillRect(0, 560, 692, 3);

        //padel white
        g.setColor(Color.DARK_GRAY);
        g.fillRect(playerx, 550, 100, 8);

        //bricks
        map.draw((Graphics2D) g);

        //ball red
        g.setColor(Color.red);
        g.fillOval(ballposx, ballposy, 20, 20);

        //score
        g.setColor(Color.green);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Score : " + score, 550, 30);

        //game over 
        if (ballposy >= 570) {
            play = false;
            ballxdir = 0;
            ballydir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("seif", Font.BOLD, 40));
            g.drawString("Game over!!", 200, 300);
            g.drawString("Score : "+score , 230, 350);
                    
            g.drawString("Press Enter To Restart ", 150, 400);

        }
        //won
        if (totalBrikas <= 0) {
            play = false;
            ballxdir = 0;
            ballydir = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("seif", Font.BOLD, 40));
            g.drawString("You Won!! Score : " + score, 150, 300);
            g.drawString("Press Enter To Restart ", 150, 350);

        }

    }

    private void moveleft() {
        if (playerx <= 0) {

        } else {
            play = true;
            playerx -= 30;

        }

    }

    private void moveright() {
        if (playerx >= 570) {

        } else {
            play = true;
            playerx += 30;

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play == true) {
            if (ballposx <= 0) {
                ballxdir = -1 * ballxdir;

            }
            if (ballposy <= 0) {
                ballydir = -ballydir;
            }
            if (ballposx >= 670) {
                ballxdir = -ballxdir;
            }
            Rectangle ballrect = new Rectangle(ballposx, ballposy, 20, 20);
            Rectangle padelrect = new Rectangle(playerx, 550, 100, 8);
            if (padelrect.intersects(ballrect)) {
                ballydir = -ballydir;
            }

            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[i].length; j++) {
                    if (map.map[i][j] > 0) {
                        int width = map.BrikWidth;
                        int height = map.BrikHeight;
                        int brikxpos = 80 + j * width;
                        int brikypos = 50 + i * height;
                        Rectangle Brikrect = new Rectangle(brikxpos, brikypos, width, height);
                        if (ballrect.intersects(Brikrect)) {
                            map.setBrik(0, i, j);
                            totalBrikas--;
                            score++;
                            if (ballposx + 19 <= brikxpos || ballposx + 1 >= brikxpos + width) {
                                ballxdir = -ballxdir;
                            } else {
                                ballydir = -ballydir;
                            }
                            break A;
                        }

                    }
                }
            }

            ballposx += ballxdir;
            ballposy += ballydir;
            repaint();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            moveleft();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            moveright();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                score = 0;
                totalBrikas = 32;
                ballposx = randInt();
                ballposy = 350;
                ballxdir = -2;
                ballydir = -3;
                playerx = 320;
                map = new Mapgenerator(4, 8);
            }
        }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
