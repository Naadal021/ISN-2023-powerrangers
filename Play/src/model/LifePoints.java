package model;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LifePoints extends SuperObject{
    Interface Inter;
    public LifePoints(Interface Inter){
        this.Inter=Inter;
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/images/wall/ui_heart_full.png"));
            image2= ImageIO.read(getClass().getResourceAsStream("/images/wall/ui_heart_empty.png"));

        }
        catch(IOException e){
        e.printStackTrace();
    }
    }
}
