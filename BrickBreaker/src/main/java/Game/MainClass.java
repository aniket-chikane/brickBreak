package Game;

import javax.swing.*;

public class MainClass {

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setTitle("Brick Breaker");
        f.setSize(700, 600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        GamePlay p = new GamePlay();
        f.add(p);
        f.setVisible(true);
    }

}
