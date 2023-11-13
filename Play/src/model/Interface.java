package model;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;


import javax.swing.JPanel;

import tile.TileManager;
public class Interface extends JPanel {

    final int OriginalTitleSize= 16;
    final int scale=3;
    public final int titleSize = OriginalTitleSize*scale;
    public final int maxScreenCol= 16;
    public final int maxscreenRow=12;
    final int screenWidth = titleSize*maxScreenCol;
    final int screenHeight=titleSize*maxscreenRow;
    TileManager tileM= new TileManager(this);
    public Interface(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
         
    }
    public void painComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        g2.dispose();

    }
}
