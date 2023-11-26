package tile;
import java.awt.Graphics2D;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import model.Interface;
public class TileManager{
    Interface gp;
    Tile[] tile;
    int mapTileNum[][];
     public TileManager(Interface gp){
        this.gp = gp;
        tile = new Tile [46];
        mapTileNum= new int[gp.maxScreenCol][gp.maxscreenRow];
        getTileimage();
        loadMap();
     }
     /**
     *
     */
    public void getTileimage(){
        try{
            tile[0]= new Tile(); 
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/black.png"));
            tile[1]= new Tile(); 
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/images/floor/floor_1.png"));
            
            tile[2]= new Tile(); 
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/wall_outer_mid_right.png"));
            tile[3]= new Tile(); 
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/wall_outer_mid_left.png"));
            tile[4]= new Tile(); 
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/wall_top_mid.png"));
            tile[5]= new Tile(); 
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/wall_down_mid.png"));
            tile[6]= new Tile(); 
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/wall_edge_tshape_up_left.png"));
            tile[7]= new Tile(); 
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/wall_edge_tshape_bottom_left.png"));
            tile[8]= new Tile(); 
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/wall_edge_tshape_bottom_right.png"));
            tile[9]= new Tile(); 
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/wall_edge_tshape_up_right.png"));
            tile[10]= new Tile(); 
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/wall_fountain_basin_red_anim_f2.png"));
            tile[11]= new Tile(); 
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/images/wall/wall_banner_red.png"));
            tile[12]= new Tile(); 
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/images/floor/column.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
        }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/tile/final_decor.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col =0;
            int row= 0;
            while(col< gp.maxScreenCol && row <gp.maxscreenRow ){
                String line = br.readLine();
                while(col< gp.maxScreenCol){
                    String numbers [] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]= num;
                    col ++;
                }

                if(col == gp.maxScreenCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e ){

        }
    }
     
     
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < gp.maxScreenCol && row < gp.maxscreenRow) {
            int tileNum = mapTileNum[col][row]; 
            g2.drawImage(tile[tileNum].image, x, y, gp.titleSize, gp.titleSize, null);
            col++;
            x += gp.titleSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.titleSize;
            }
        }
    }
    }
