package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Dimension;

import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 0.85f, yDir = 0.85f;
    private Color color = new Color(150, 20, 90);
    private Random random;

    public GamePanel() {

        random = new Random();
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1345, 730);
        setPreferredSize(size);

    }

    public void changeXDelta(int value) {
        this.xDelta += value;
        // repaint();
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
        // repaint();
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
        // repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateRectangle();
        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 200, 50);


    }

    private void updateRectangle() {
        xDelta += xDir;
        if(xDelta + 200 > getWidth() ||  xDelta < 0 ) {
            xDir *= -1;
            color = getRndColor();
        }

        yDelta += yDir;
        if(yDelta + 50 > getHeight() || yDelta < 0) {
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        
        return new Color(r, g, b);
    }
}







