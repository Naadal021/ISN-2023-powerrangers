package model;

public class CollisionChecker {
	Interface inter;
	public CollisionChecker (Interface inter) {
	this.inter=inter;
	}
	public void checkTile (Mov mov) {
		int movLeftx= mov.x + mov.solidArea.x;
		int movRightx= mov.x + mov.solidArea.x + mov.solidArea.width;
		int movTopy= mov.y + mov.solidArea.y;
		int movBottomy = mov.y + mov.solidArea.y + mov.solidArea.height;
		
		int movleftcol = movLeftx/inter.titleSize;
		int movRightCol = movRightx/inter.titleSize;
		int movTopRow = movTopy/inter.titleSize;
		int movBottomRow=movBottomy/inter.titleSize;
		int tileNum1, tileNum2;
		switch(mov.direction) {
				case "up":
					movTopRow=(movTopy-mov.speed)/inter.titleSize;
					tileNum1=inter.tileM.mapTileNum[movleftcol][movTopRow];
					tileNum2=inter.tileM.mapTileNum[movRightCol][movTopRow];
					if(inter.tileM.tile[tileNum1].collision==true || inter.tileM.tile[tileNum2].collision==true) {
						mov.collisionOn=true;
					}
				break;
				case "down":
					movBottomRow=(movBottomy+mov.speed)/inter.titleSize;
					tileNum1=inter.tileM.mapTileNum[movleftcol][movBottomRow];
					tileNum2=inter.tileM.mapTileNum[movRightCol][movBottomRow];
					if(inter.tileM.tile[tileNum1].collision==true || inter.tileM.tile[tileNum2].collision==true) {
						mov.collisionOn=true;
					}
				break;
				case "left":
					movleftcol=(movLeftx-mov.speed)/inter.titleSize;
					tileNum1=inter.tileM.mapTileNum[movleftcol][movTopRow];
					tileNum2=inter.tileM.mapTileNum[movleftcol][movBottomRow];
					if(inter.tileM.tile[tileNum1].collision==true || inter.tileM.tile[tileNum2].collision==true) {
						mov.collisionOn=true;
					}
				break;
				case "right":
					movRightCol=(movRightx+mov.speed)/inter.titleSize;
					tileNum1=inter.tileM.mapTileNum[movRightCol][movTopRow];
					tileNum2=inter.tileM.mapTileNum[movRightCol][movBottomRow];
					if(inter.tileM.tile[tileNum1].collision==true || inter.tileM.tile[tileNum2].collision==true) {
						mov.collisionOn=true;
					}
				break;
		}
		
	}
	public int checkEntity(Mov mov,Mov target) {
		int index = 999;
		mov.solidArea.x= mov.x + mov.solidArea.x;
		mov.solidArea.y= mov.y + mov.solidArea.y;
		target.solidArea.x= target.x + target.solidArea.x;
		target.solidArea.y= target.y + target.solidArea.y;
		
		switch(mov.direction) {
				case "up":
					mov.solidArea.y-=mov.speed;
					if(mov.solidArea.intersects(target.solidArea)) {
						mov.collisionOn=true;
						index=0;
					}
				break;
				case "down":
					mov.solidArea.y+=mov.speed;
					if(mov.solidArea.intersects(target.solidArea)) {
						mov.collisionOn=true;
						index=0;}
					
				break;
				case "left":
					mov.solidArea.x-=mov.speed;
					if(mov.solidArea.intersects(target.solidArea)) {
						mov.collisionOn=true;
						index=0;}
					
				break;
				case "right":
					mov.solidArea.x+=mov.speed;
					if(mov.solidArea.intersects(target.solidArea)) {
						mov.collisionOn=true;
						index=0;}
				break;
				}
		mov.solidArea.x= mov.solidAreaDefaultX;
		mov.solidArea.y= mov.solidAreaDefaultY;
		target.solidArea.x= target.solidAreaDefaultX;
		target.solidArea.y= target.solidAreaDefaultY;
		return index;
		
}
	public void checkplayer(Mov mov) {
		
		mov.solidArea.x= mov.x + mov.solidArea.x;
		mov.solidArea.y= mov.y + mov.solidArea.y;
		inter.persoPrincipal.solidArea.x= inter.persoPrincipal.x + inter.persoPrincipal.solidArea.x;
		inter.persoPrincipal.solidArea.y= inter.persoPrincipal.y + inter.persoPrincipal.solidArea.y;
		
		switch(mov.direction) {
				case "up":
					mov.solidArea.y-=mov.speed;
					if(mov.solidArea.intersects(inter.persoPrincipal.solidArea)) {
						mov.collisionOn=true;
						
					}
				break;
				case "down":
					mov.solidArea.y+=mov.speed;
					if(mov.solidArea.intersects(inter.persoPrincipal.solidArea)) {
						mov.collisionOn=true;
						}
					
				break;
				case "left":
					mov.solidArea.x-=mov.speed;
					if(mov.solidArea.intersects(inter.persoPrincipal.solidArea)) {
						mov.collisionOn=true;
						}
					
				break;
				case "right":
					mov.solidArea.x+=mov.speed;
					if(mov.solidArea.intersects(inter.persoPrincipal.solidArea)) {
						mov.collisionOn=true;
						}
				break;
				}
		mov.solidArea.x= mov.solidAreaDefaultX;
		mov.solidArea.y= mov.solidAreaDefaultY;
		inter.persoPrincipal.solidArea.x= inter.persoPrincipal.solidAreaDefaultX;
		inter.persoPrincipal.solidArea.y= inter.persoPrincipal.solidAreaDefaultY;
		
		
}
}
