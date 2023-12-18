package model;
import java.awt.Dimension;
import java.util.Collections;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import ennemies.Ennemi;
import tile.TileManager;
import java.awt.FontFormatException;



import java.util.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Interface extends JPanel implements Runnable{

    final int OriginalTitleSize= 10;
    final int scale=3;
    public final int titleSize = OriginalTitleSize*scale;
    public final int maxScreenCol= 51;
    public final int maxscreenRow=26;
    public final int screenWidth = titleSize*maxScreenCol;
    public final int screenHeight=titleSize*maxscreenRow;
    int damage_points;
    int damage_Demon;
    int damage_Mage=0;
    int damage_Lutin;
    int damage_Ogre;
    Font Alkhemikal;
    private ImageIcon blue= new ImageIcon(getClass().getResource("/images/flags/flagblue.jpeg"));
    private ImageIcon green =  new ImageIcon(getClass().getResource("/images/flags/flaggreen.jpeg"));
    private  ImageIcon red=   new ImageIcon(getClass().getResource("/images/flags/flagred.jpeg"));
    private   ImageIcon yellow=  new ImageIcon(getClass().getResource("/images/flags/flagyellow.jpeg"));
    private ImageIcon[] flagIcons = {blue,green,red,yellow};
    Thread game;
    public TileManager tileM = new TileManager(this);
    DecimalFormat dFormat= new DecimalFormat("#0.00");
   
    //FPS = FRAME PER SECOND
    int FPS =60;
    private static final int NOMBRE_DE_BOULES = 4; 
    KeyHandler keyH=new KeyHandler();
    public CollisionChecker cChecker=new CollisionChecker(this);
    public PersoPrincipal persoPrincipal =new PersoPrincipal(this,keyH);
    private String gamestatestring="null";
    private List<Boule> originalBoules = new ArrayList<>(); 

  
    Ennemi Demon = new Ennemi(this,"Demon",1200,150,2,3,18,42,30,titleSize +25);
    Ennemi Ogre = new Ennemi(this,"Ogre",1000,600,2,3,18,42,30,titleSize +25);
    Ennemi Lutin = new Ennemi(this,"Lutin",400,380,2,3,18,42,30,titleSize +5);
    Ennemi Mage = new Ennemi(this,"Mage",600,100,2,3,18,42,30,titleSize +5);
  
    
    LifePoints lifepoints = new LifePoints(this);
   
    ImageIcon[] medalIcons = {new ImageIcon("play/src/model/or.jpeg"),
    new ImageIcon("play/src/model/argent.jpeg"),
    new ImageIcon("play/src/model/bronze.jpeg"),
};
    
 
    
    private List<Boule> boules = new ArrayList<>();
     private List<Boule> boule_temp = new ArrayList<>();
    private Random random = new Random();
    
    
    
    double playtime;
     double flagred = 30;
    double flaggreen=30;
    double flagblue=30;
     double flagyellow=30;
    private JButton scorebutton;
    private JTextField usernameField;
    

    public static String USERNAME="";
    private boolean gamewon=false;
    
    
    public static String getuserName() {
    	return USERNAME;
    }
    public Interface(){ 
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_B) {
                    gamestatestring = "null";
                    restartGame();  // Ensure the repaint to reflect the change
                }
            }
        });
        
        
        // Load custom font
        try {
            InputStream is = getClass().getResourceAsStream("/model/Alkhemikal.ttf");
            Alkhemikal = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        setLayout(null);

        // Initialize and format username field
        usernameField = new JTextField("Enter Username press enter ");
        USERNAME = usernameField.getText();
        usernameField.setFont(Alkhemikal.deriveFont(Font.PLAIN, 20));
        usernameField.setPreferredSize(new Dimension(150, 30));
        usernameField.setBounds(600, 500, 250, 30);

        // Add KeyListener to the username field to start the game on Enter press
        usernameField.addKeyListener((KeyListener) new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    gamestatestring = "play";
                    play();
                }
            }
        });
        add(usernameField);
        scorebutton = new JButton("Score");
        scorebutton.setFont(Alkhemikal.deriveFont(Font.PLAIN, 40));
        scorebutton.setPreferredSize(new Dimension(150, 50));
        scorebutton.setBounds(650, 550, 150, 50);
        scorebutton.addActionListener(e -> {
        	gamestatestring = "score";
            
        });
        add(scorebutton);
        /*JButton rulesButton = new JButton("Rules");
        rulesButton.setFont(Alkhemikal.deriveFont(Font.PLAIN, 40));
        rulesButton.setPreferredSize(new Dimension(150, 50));
        rulesButton.setBounds(650, 600, 150, 50);

        rulesButton.addActionListener(e -> {
            gamestatestring = "rules";
            repaint();
        });

        add(rulesButton);*/
        JButton rulesButton = new JButton("Rules");
        rulesButton.setFont(Alkhemikal.deriveFont(Font.PLAIN, 40));
        rulesButton.setPreferredSize(new Dimension(150, 50));
        rulesButton.setBounds(650, 600, 150, 50);

        rulesButton.addActionListener(e -> {
            ImageIcon rulesImage = new ImageIcon("Play/src/model/rules.jpg"); // Remplacez par le chemin de votre image
            new RulesWindow(rulesImage);
        });

        add(rulesButton);


    }
        
    public void startGame(){
    	this.requestFocusInWindow();
        game=new Thread(this);
        game.start();
    }
    private static List<Object[]> readScoreFile(String fileName) {
    List<Object[]> playerList = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\s+");  // Split by space, tab, or other whitespace
            if (parts.length == 2) {
                String playerName = parts[0];

                // Replace comma with dot before parsing
                String playtimeString = parts[1].replace(',', '.');

                double playertime = Double.parseDouble(playtimeString);
                Object[] player = {playerName, playertime};
                playerList.add(player);
            }
        }

        // Sort the list based on playtime (minimal score to maximum)
        Collections.sort(playerList, Comparator.comparingDouble(player -> (double) player[1]));
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    }

    return playerList;
}
    public void updateCompteur() {
        damage_points = persoPrincipal.damage_points;
    }
    public void getdamage(){
        damage_Demon=persoPrincipal.damage_Demon;
        damage_Ogre=persoPrincipal.damage_Ogre;
        damage_Mage=persoPrincipal.damage_Mage;
        damage_Lutin=persoPrincipal.damage_Lutin;

    }
    
     private ImageIcon[] flags = {
        new ImageIcon(getClass().getResource("/images/flags/flagblue.jpeg")),
        new ImageIcon(getClass().getResource("/images/flags/flaggreen.jpeg")),
        new ImageIcon(getClass().getResource("/images/flags/flagred.jpeg")),
        new ImageIcon(getClass().getResource("/images/flags/flagyellow.jpeg"))
    };

    // Other fields and methods in your Interface class...

    public List<ImageIcon> getFlagsList() {
        // Convert the array to a List
        return Arrays.asList(flags);
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
    			//System.out.println("FPS:"+drawCount);
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
   
  
    public void restartGame() {
        this.requestFocusInWindow();
        boules.clear(); // Clear the current state of boules
        spawnBoules(); // Restore the original state of boules
        gamestatestring = "null";
        persoPrincipal.setDefaultValues();
        Demon.x=1200;
        Demon.y=150;
        Demon.icone=titleSize+25;
        Mage.x=600;
        Mage.y=100;
        Mage.icone=titleSize+5;
        Ogre.x=1000;
        Ogre.y=600;
        Ogre.icone=titleSize+25;
        Lutin.x=400;
        Lutin.y=380;
        Lutin.icone=titleSize+5;
        flagred=30;
        flagblue=30;
        flaggreen=30;
        flagyellow=30;
        persoPrincipal.damage_Demon=0;
        persoPrincipal.damage_Mage=0;
        persoPrincipal.damage_Lutin=0;
        persoPrincipal.damage_Ogre=0;
        persoPrincipal.damage_points=0;
        gamewon=false;
        playtime=0;
        persoPrincipal.transferAndEmpty(); // Reset game state if needed
        // Additional reset logic goes here if necessary
        if(game==null){
            startGame();
        }
        repaint();
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
        boule_temp.add(boule);
    }
    
    public void play(){
        USERNAME = usernameField.getText(); // Retrieve the username when the game starts
        
        this.requestFocusInWindow();
    }
    private static void writeScoreToFile(String filePath, String playerName, double playtime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Appending to the file (true parameter in FileWriter constructor)

            // Format the line and write it to the file
            String line = String.format("%s %.2f%n", playerName, playtime);
            writer.write(line);

            System.out.println("Score added to the file successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){ 
     
        updateCompteur();
        getdamage();
        persoPrincipal.update();
        Demon.update();
        Lutin.update();
        Mage.update();
    	Ogre.update();
     
    }
    public static void replaceCommaWithDot(String filePath) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + "_temp"))) {
    
                String line;
                while ((line = reader.readLine()) != null) {
                    // Replace ',' with '.'
                    line = line.replace(',', '.');
                    writer.write(line);
                    writer.newLine(); // Add a newline character to separate lines
                }
    
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            // Rename the temporary file to the original file
            renameFile(filePath + "_temp", filePath);
        }
    
    private static void renameFile(String oldFilePath, String newFilePath) {
            // Rename the temporary file to the original file
            java.io.File oldFile = new java.io.File(oldFilePath);
            java.io.File newFile = new java.io.File(newFilePath);
    
            if (oldFile.renameTo(newFile)) {
                System.out.println("File renamed successfully.");
            } else {
                System.err.println("Failed to rename the file.");
            }
        }
    
    @Override
   public void paintComponent(Graphics g) {
   
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    getdamage();
    
    if (gamestatestring=="null"){
        
        g2.setColor(Color.WHITE);
      g2.setFont(Alkhemikal.deriveFont(Font.PLAIN, 200));
        g2.drawString("FlagQuest", 400, 400);
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
    
    if (gamestatestring=="play"){
    this.requestFocusInWindow();
        
    tileM.draw(g2);
     g2.setColor(Color.WHITE);
        g2.setFont(Alkhemikal.deriveFont(Font.PLAIN, 50));
    if(persoPrincipal.getcolor()=="red"&& flagred>0){
            g2.drawImage(flagIcons[2].getImage(),0,560,50,50,null);
            g2.drawString(" : "+dFormat.format(flagred), 60,600);
            }
        else if(persoPrincipal.getcolor()=="green"&& flaggreen>0){
             g2.drawImage(flagIcons[1].getImage(),0,560,50,50,null);
            g2.drawString(" : "+dFormat.format(flaggreen), 60,600);
            }
        else if(persoPrincipal.getcolor()=="blue"&& flagblue>0){
             g2.drawImage(flagIcons[0].getImage(),0,560,50,50,null);
            g2.drawString(" : "+dFormat.format(flagblue), 60,600);
            }
        else if(persoPrincipal.getcolor()=="yellow"&& flagyellow>0){
             g2.drawImage(flagIcons[3].getImage(),0,560,50,50,null);
            g2.drawString(" : "+dFormat.format(flagyellow), 60,600);
            }
    if (damage_points < 3) {
        
       

        for (Boule boule : boules) {
            
            boule.draw(g2);
        }
        
        persoPrincipal.draw(g2);
        Demon.draw(g2);
       
        Lutin.draw(g2);
        Mage.draw(g2);
        Ogre.draw(g2);

       
        if(!gamewon){
        playtime+=(double)1/60;
        g2.drawString("Time: "+dFormat.format(playtime), 0, 500);
        }
        
        
        
        lifepoints.draw(g2,damage_points);
        if(damage_Demon>=1 && damage_Mage>=1 && damage_Lutin>=1 && damage_Ogre>=1){
            
           
             gamewon=true;
             gamestatestring="attente";
        
           

        }
        if((damage_Mage==0 && flagblue<0)||(damage_Ogre==0 && flaggreen<0)||(damage_Demon==0&&flagred<0)||(damage_Lutin==0 && flagyellow<0)) {
      
        g2.setColor(Color.WHITE);
        g2.setFont(Alkhemikal.deriveFont(Font.PLAIN, 200));
    
        g2.drawString("Game Over", 400, 400);
        game=null;
        }
    } else  {
        g2.setColor(Color.WHITE);
        g2.setFont(Alkhemikal.deriveFont(Font.PLAIN, 200));
    
        g2.drawString("Game Over", 400, 400);
    
        game = null;
    }
    
    if (gamestatestring == "score") {
        this.requestFocusInWindow(); // Add this line to ensure the panel has focus
        List<Object[]> best3players = readScoreFile("Play/src/model/Scores.txt");
        
        g2.setColor(Color.WHITE);
        g2.setFont(Alkhemikal.deriveFont(Font.PLAIN, 50));
        
        // Draw only the top three players
        int numPlayersToDraw = Math.min(3, best3players.size());
        String[][] playerNames = new String[numPlayersToDraw][2];
        double[][] playerTimes = new double[numPlayersToDraw][2];
        for (int i = 0; i < numPlayersToDraw; i++) {
            playerNames[i][0] = (String) best3players.get(i)[0];
            playerTimes[i][0] = (double) best3players.get(i)[1];
            
            // Adjust Y-coordinate for each name
        }
        String goldname=playerNames[0][0];
        String silvername=playerNames[1][0];
        String bronzename=playerNames[2][0];
        double goldscore= playerTimes[0][0];
        double silverscore= playerTimes[1][0];
        double bronzescore= playerTimes[2][0];
     
        g2.drawImage(medalIcons[0].getImage(),650,00,null);
        g2.drawImage(medalIcons[1].getImage(),300,200,null);
        g2.drawImage(medalIcons[2].getImage(),1250,400,null);
        g2.drawString(goldname, 600, 300 );
        g2.drawString(dFormat.format(goldscore),800,300);
        g2.drawString(silvername, 200, 500 );
        g2.drawString(dFormat.format(silverscore),400,500);
        g2.drawString(bronzename, 1200, 700 );
        g2.drawString(dFormat.format(bronzescore),1350,700);}
         if (gamestatestring=="rules"){
    	
    	    ImageIcon rulesImage = new ImageIcon("Play/src/model/rules.jpg"); // Remplacez par le chemin de votre image

    	    // Dessiner l'image
    	    g2.drawImage(rulesImage.getImage(), 100, 100, null);
    	

    }
    if(gamestatestring=="attente"){
         tileM.draw(g2);
        g2.setColor(Color.WHITE);
        g2.setFont(Alkhemikal.deriveFont(Font.PLAIN, 100));
        g2.drawString(getuserName()+ " WON", 500, 400);
        g2.setFont(Alkhemikal.deriveFont(Font.PLAIN, 100));
        g2.drawString("IN "+dFormat.format(playtime), 550, 500);
        g2.drawString("PRESS B TO RESTART", 300, 100);
    }
    
    g2.dispose();
    }
}
}
