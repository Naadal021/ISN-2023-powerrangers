package model;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Shape;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tile.TileManager;

import model.PersoPrincipal;
import model.Mov;

import java.util.Random;
import java.util.Timer;
import java.util.ArrayList;
import java.util.List;


public class Interface extends JPanel implements Runnable{

    final int OriginalTitleSize= 10;
    final int scale=3;
    public final int titleSize = OriginalTitleSize*scale;
    public final int maxScreenCol= 75;
    public final int maxscreenRow=28;
    final int screenWidth = titleSize*maxScreenCol;
    final int screenHeight=titleSize*maxscreenRow;
    Thread game;
    TileManager tileM= new TileManager(this);
    //FPS = FRAME PER SECOND
    int FPS =60;
    private static final int NOMBRE_DE_BOULES = 5;
    KeyHandler keyH=new KeyHandler();
    PersoPrincipal persoPrincipal =new PersoPrincipal(this,keyH);
    
    private int score = 0; //score lorsque le perso touche la boule
    public void incrementScore() {
        score++;
    }
    private List<Boule> boules = new ArrayList<>();
    private Random random = new Random();
    private Timer timer;
    private JLabel scoreLabel;
    
    public Interface(){ 
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
        //score = 0;
        //scoreLabel = new JLabel("Score: " + score);
        //scoreLabel.setForeground(Color.WHITE);
        //scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        //add(scoreLabel);
        SwingUtilities.invokeLater(() -> {
            scoreLabel = new JLabel("Score: " + score);
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            add(scoreLabel);
            // Ajoutez le scoreLabel à l'interface
            this.setLayout(new BorderLayout());
            this.add(scoreLabel, BorderLayout.NORTH);
        });
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
    	spawnBoules();
    	while(game !=null) {
    		currentTime = System.nanoTime();
    		delta+=(currentTime-lastTime)/drawInterval;
    		timer+=(currentTime-lastTime);
    		lastTime=currentTime;
    		if (delta>=1) {
    			update();
    			//spawnBoules(); // Générer aléatoirement des boules  //en cmnt car elle genere 5 fois les boules alea et moi je veux que une seule fois
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
    
    
    
    private void spawnBoules() {
    	if (boules!=null) {
    		boules = new ArrayList<>();
    	}
        System.out.println("Avant génération : " + boules.size());
        
        for (int i = 0; i < NOMBRE_DE_BOULES; i++) {
            int randomX, randomY;
            do {
                randomX = random.nextInt(screenWidth - Boule.getDiametre());
                randomY = random.nextInt(screenHeight - Boule.getDiametre());
            } while (bouleSeChevauche(randomX, randomY));

            Boule boule = new Boule(randomX, randomY);
            boules.add(boule);
            System.out.println("Boule générée à (" + randomX + ", " + randomY + ")");
        }
        
        System.out.println("Après génération : " + boules.size());
    }

    private boolean bouleSeChevauche(int x, int y) {
        for (Boule boule : boules) {
            double distance = Math.sqrt(Math.pow(x - boule.getX(), 2) + Math.pow(y - boule.getY(), 2));
            if (distance < Boule.getDiametre()) {
                return true; // Les boules se chevauchent
            }
        }
        return false; // Aucun chevauchement
    }

    public List<Boule> getBoules() {
        return boules;
    }
    public void removeBoule(Boule boule) {
        boules.remove(boule);
    }
    
    
    public void update(){ 
    	persoPrincipal.update();
     }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        
        for (Boule boule : boules) {
            boule.draw(g2);
        }
        persoPrincipal.draw(g2);
        //scoreLabel.setText("Score: " + score);
        //SwingUtilities.invokeLater(() -> scoreLabel.setText("Score: " + score));
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        g2.drawString("Score: " + score, 10, 20);
        g2.dispose();

    }
}
