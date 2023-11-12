package model;
import java.awt.Dimension;
import java.awt.Color;


import javax.swing.JPanel;
public class Interface extends JPanel {

    final int OriginalTitleSize= 16;
    final int scale=3;
    final int titleSize = OriginalTitleSize*scale;
    final int maxScreenCol= 24;
    final int maxscreenRow=30;
    final int screenWidth = titleSize*maxScreenCol;
    final int screenHeight=titleSize*maxscreenRow;
    public Interface(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
         
    }
}
