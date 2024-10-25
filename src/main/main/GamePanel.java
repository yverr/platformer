package main;

import java.awt.Graphics;
import java.io.InputStream;
import java.awt.Dimension;

import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.image.*;
import javax.imageio.ImageIO;    
import java.io.IOException;       


public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;


    public GamePanel() {

       
        mouseInputs = new MouseInputs(this);

        importImg();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void importImg() {
       InputStream is = getClass().getResourceAsStream("/res/player_sprites.png");

        if (is == null) {
        throw new IllegalArgumentException("Image not found: /player_sprites.png");
        }

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            
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

        /* 
          | getSubImage |
        1(y) * 64(widthCharImageSize),
        9(x) * 40(heightCharImageSize)
        */
        subImg = img.getSubimage(1*64, 8*40, 64, 40);
        g.drawImage(subImg, (int) xDelta,(int) yDelta, 128, 80, null);

        /* 
                                  pos | cropImg      | doubleImgSize
        g.drawImage(img.getSubimage(0, 0, 64, 40), 0, 0, 128, 80, null);
        */
    }


}







