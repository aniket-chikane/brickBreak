package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Mapgenerator {

    public int map[][];
    public int BrikWidth;
    public int BrikHeight;

    public Mapgenerator(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = 1;

            }
        }
        BrikWidth = 540 / col;
        BrikHeight = 150 / row;
    }

    public void setBrik(int value, int r, int c) {
        map[r][c] = value;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(new Color(170, 75, 0));
                    g.fillRect(j * BrikWidth + 80, i * BrikHeight + 50, BrikWidth, BrikHeight);
                    g.setColor(Color.white);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j * BrikWidth + 80, i * BrikHeight + 50, BrikWidth, BrikHeight);

                }

            }
        }

    }

}
