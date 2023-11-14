package model;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Shape;

import javax.swing.JPanel;

import tile.TileManager;

import model.PersoPrincipal;
import model.Mov;

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
    //FPS = FRAME PER SECOND
    int FPS =60;
    
    KeyHandler keyH=new KeyHandler();
    PersoPrincipal persoPrincipal =new PersoPrincipal(this,keyH);
    
    public Interface(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
         
    }
    public void startGame(){
    	this.requestFocusInWindow();
        game=new Thread(this);
        game.start();
    }
    public void run(){
    	double drawInterval =1000000000/FPS;
    	double delta=0;
    	long lastTime = System.nanoTime();
    	long currentTime;
    	long timer=0;
    	int drawCount=0;
    	
    	while(game !=null) {
    		currentTime = System.nanoTime();
    		delta+=(currentTime-lastTime)/drawInterval;
    		timer+=(currentTime-lastTime);
    		lastTime=currentTime;
    		if (delta>=1) {
    			update();
    			repaint();
    			delta--;
    			drawCount++;
    		}
    		if (timer>=1000000000) {
    			System.out.println("FPS:"+drawCount);
    			drawCount=0;
    			timer=0;
    		} 
    		try {
                Thread.sleep(1); // Ajoutez une pause d'1 milliseconde pour éviter une boucle trop rapide.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    	}
        
    }
    public void update(){ 
    	persoPrincipal.update();
     }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        persoPrincipal.draw(g2);
        g2.dispose();

    }
}
