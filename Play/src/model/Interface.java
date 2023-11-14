package model;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;


import javax.swing.JPanel;

import tile.TileManager;
public class Interface extends JPanel implements Runnable{

    final int OriginalTitleSize= 16;
    final int scale=3;
    public final int titleSize = OriginalTitleSize*scale;
    public final int maxScreenCol= 16;
    public final int maxscreenRow=12;
    final int screenWidth = titleSize*maxScreenCol;
    final int screenHeight=titleSize*maxscreenRow;
    Thread game;
    TileManager tileM= new TileManager(this);
    
    public Interface(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
         
    }
    public void startGame(){
        game=new Thread(this);
        game.start();
    }
    public void run(){
        while(game!=null){
            update();
            repaint();
        }
        
    }
    public void update(){
            
     }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        
        g2.dispose();

    }
}
