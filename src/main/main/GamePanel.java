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
    private BufferedImage img;
    private BufferedImage[] idleAni;
    private int aniTick, aniIndex, aniSpeed = 30;


    public GamePanel() {

       
        mouseInputs = new MouseInputs(this);

        importImg();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void loadAnimations() {
        idleAni = new BufferedImage[5];

        for(int i = 0; i < idleAni.length; i++)
            idleAni[i] = img.getSubimage(i*64, 0, 64, 40);
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

    private void updateAnimationTick() {
       
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= idleAni.length) 
                aniIndex = 0;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateAnimationTick();

        g.drawImage(idleAni[aniIndex], (int) xDelta, (int) yDelta, 128, 80, null);
    }

   


}







