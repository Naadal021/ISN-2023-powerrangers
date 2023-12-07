package model;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ennemies.Demon;
import ennemies.lutin;
import ennemies.mage;
import ennemies.ogre;
import tile.TileManager;
import java.awt.FontFormatException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

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
    int c;
    Font Alkhemikal;
    Thread game;
    public TileManager tileM = new TileManager(this);
    //FPS = FRAME PER SECOND
    int FPS =60;
    private static final int NOMBRE_DE_BOULES = 4; 
    KeyHandler keyH=new KeyHandler();
    public CollisionChecker cChecker=new CollisionChecker(this);
    PersoPrincipal persoPrincipal =new PersoPrincipal(this,keyH);
    Demon Demon1 =new Demon(this);
    lutin lutin1 =new lutin(this);
  
    mage mage1 =new mage(this);
    ogre ogre1=new ogre(this);
    LifePoints lifepoints = new LifePoints(this);
    
    
    private int score = 0; //score lorsque le perso touche la boule
    public void incrementScore() {
        score++;
    }
    private List<Boule> boules = new ArrayList<>();
    private Random random = new Random();
    
    private JLabel scoreLabel;
    private Font  customFont;
    
    private JButton playButton;
    private JTextField usernameField;
    
    private boolean gameStarted = false;
    public static String USERNAME="";
    private JLabel usernameLabel;
    public static String getuserName() {
    	return USERNAME;
    }
    public Interface(){ 
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black); 
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        try {
            InputStream is = getClass().getResourceAsStream("/model/Alkhemikal.ttf");
            Alkhemikal = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setLayout(null);
        //setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
        usernameField = new JTextField("Enter Username");
        USERNAME = usernameField.getText();
        usernameField.setFont(Alkhemikal.deriveFont(Font.PLAIN, 20));
        usernameField.setPreferredSize(new Dimension(150, 30)); 
        usernameField.setBounds(650, 500, 150, 30);
        add(usernameField);
        //setLayout(new FlowLayout(FlowLayout.CENTER, 0, 500));
        playButton = new JButton("Play");
        playButton.setFont(Alkhemikal.deriveFont(Font.PLAIN, 40));
        playButton.setPreferredSize(new Dimension(150, 50));
        playButton.setBounds(650, 550, 150, 50);
        playButton.addActionListener(e -> {
        	gameStarted = true;
            startGame();
        });
        add(playButton);
        
        SwingUtilities.invokeLater(() -> {
            scoreLabel = new JLabel("Score: " + score);
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            add(scoreLabel);
//            usernameLabel = new JLabel("Entered Username: " + USERNAME);
//            usernameLabel.setForeground(Color.WHITE);
//            usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//            add(usernameLabel);
//            playButton.addActionListener(e -> { 
//            	USERNAME = usernameField.getText();
//                usernameLabel.setText("Entered Username: " + USERNAME);
//            });
            // Ajoutez le scoreLabel  et username à l'interface
            this.setLayout(new BorderLayout());
            this.add(scoreLabel, BorderLayout.NORTH);
//            this.add(usernameLabel, BorderLayout.WEST);
        });
    }
    public void startGame(){
    	this.requestFocusInWindow();
        game=new Thread(this);
        game.start();
    }
    public void updateCompteur() {
        c = persoPrincipal.compteur;
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
    
    public void endGame(){
    	this.requestFocusInWindow();
        game=new Thread(this);
        game.interrupt();
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

    return tileNum == floorTile;}
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
        updateCompteur();
        
        
    	persoPrincipal.update();
        
    	
    	Demon1.update();
    	lutin1.update();
     
    	mage1.update();
    	ogre1.update();
     
        }
     
    @Override
   public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    tileM.draw(g2);
    
    if (!gameStarted) {
    	g2.setColor(Color.WHITE);
        g2.setFont(Alkhemikal.deriveFont(Font.PLAIN, 200));
        g2.drawString("Start Game", 300, 400);
        
        g2.drawImage(lifepoints.Hearts[0].getImage(), 20, 40,titleSize,titleSize, null);
        g2.drawImage(lifepoints.Hearts[0].getImage(), 55, 40, titleSize, titleSize, null);
        g2.drawImage(lifepoints.Hearts[0].getImage(), 90, 40,titleSize, titleSize, null);
        
        
        ImageIcon[] imageIcon = {new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f1.png"), 
        		new ImageIcon("Play/src/images/ennemies/big_demon_run_anim_f00.png"),
        		new ImageIcon("Play/src/images/ennemies/elf_m_run_anim_f00.png"),
        		new ImageIcon("Play/src/images/ennemies/knight_f_run_anim_f00.png"),
        		new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f00.png")
        };
        
        g2.drawImage(imageIcon[0].getImage(), 550, 150, 100, 100, null);
        g2.drawImage(imageIcon[1].getImage(), 750, 150, 100, 100, null);
        g2.drawImage(imageIcon[2].getImage(), 850, 150, 100, 100, null);
        g2.drawImage(imageIcon[3].getImage(), 950, 150, 100, 100, null);
        g2.drawImage(imageIcon[4].getImage(), 1050, 150, 100, 100, null);
        return;
    }
    
    if (c < 3) {
        
       

        for (Boule boule : boules) {
            boule.draw(g2);
        }
        
        persoPrincipal.draw(g2);
        Demon1.draw(g2);
        lutin1.draw(g2);
        mage1.draw(g2);
        ogre1.draw(g2);

        g2.setColor(Color.WHITE);
        g2.setFont(Alkhemikal.deriveFont(Font.PLAIN, 16));
        //g2.drawString("Score: " + score, 10, 20);
        if(c==0){
            g2.drawImage(lifepoints.Hearts[0].getImage(), 20, 40,titleSize,titleSize, null);
            g2.drawImage(lifepoints.Hearts[0].getImage(), 55, 40, titleSize, titleSize, null);
            g2.drawImage(lifepoints.Hearts[0].getImage(), 90, 40,titleSize, titleSize, null);
        }

        if(c==1){
            g2.drawImage(lifepoints.Hearts[0].getImage(), 20, 40,titleSize,titleSize, null);
            g2.drawImage(lifepoints.Hearts[0].getImage(), 55, 40, titleSize, titleSize, null);
            g2.drawImage(lifepoints.Hearts[1].getImage(), 90, 40,titleSize, titleSize, null);
        }
        if(c==2){
            g2.drawImage(lifepoints.Hearts[0].getImage(), 20, 40,titleSize,titleSize, null);
            g2.drawImage(lifepoints.Hearts[1].getImage(), 55, 40, titleSize, titleSize, null);
            g2.drawImage(lifepoints.Hearts[1].getImage(), 90, 40,titleSize, titleSize, null);

        }
    } else {
        
        g2.setColor(Color.WHITE);
        g2.setFont(Alkhemikal.deriveFont(Font.PLAIN, 200));
        //g2.setFont(new Font("Algerian", Font.PLAIN, 200));
        g2.drawString("Game Over", 400, 400);
        g2.drawImage(lifepoints.Hearts[1].getImage(), 20, 40,titleSize,titleSize, null);
        g2.drawImage(lifepoints.Hearts[1].getImage(), 55, 40, titleSize, titleSize, null);
        g2.drawImage(lifepoints.Hearts[1].getImage(), 90, 40,titleSize, titleSize, null);
    }

    g2.dispose();
    }
}