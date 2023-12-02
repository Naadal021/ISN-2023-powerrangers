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

import ennemies.Demon;
import ennemies.lutin;
import ennemies.mage;
import ennemies.ogre;
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
    public final int maxScreenCol= 51;
    public final int maxscreenRow=26;
    public final int screenWidth = titleSize*maxScreenCol;
    public final int screenHeight=titleSize*maxscreenRow;
    
    Thread game;
    public TileManager tileM = new TileManager(this);
    //FPS = FRAME PER SECOND
    int FPS =60;
    private static final int NOMBRE_DE_BOULES = 4;
    KeyHandler keyH=new KeyHandler();
    public CollisionChecker cChecker=new CollisionChecker(this);
    PersoPrincipal persoPrincipal =new PersoPrincipal(this,keyH);
    Demon Demon =new Demon(this);
    lutin lutin1 =new lutin(this);
    mage mage1 =new mage(this);
    ogre ogre1=new ogre(this);
    
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
        if (boules != null) {
            boules = new ArrayList<>();
        }

        int tileX, tileY;
        for (int i = 0; i < NOMBRE_DE_BOULES; i++) {
            do {
                // x et y alea de tile
                tileX = random.nextInt(maxScreenCol);
                tileY = random.nextInt(maxscreenRow);
            } while (bouleSeChevauche(tileX * titleSize, tileY * titleSize) || !isFloorTile(tileM, tileX, tileY));

            int x = tileX * titleSize;
            int y = tileY * titleSize;
           
            Boule boule = new Boule(x, y);
            boules.add(boule);
        }
    }

    private boolean isFloorTile(TileManager tileManager, int tileX, int tileY) {
        int tileNum = tileManager.getTileNum(tileX, tileY);
        int floorTile = 1;

        return tileNum == floorTile;
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
    	
    	Demon.update();
    	lutin1.update();
    	mage1.update();
    	ogre1.update();
    	
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
        Demon.draw(g2);
        lutin1.draw(g2);
        mage1.draw(g2);
        ogre1.draw(g2);
        //scoreLabel.setText("Score: " + score);
        //SwingUtilities.invokeLater(() -> scoreLabel.setText("Score: " + score));
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        g2.drawString("Score: " + score, 10, 20);
        g2.dispose();

    }
}
