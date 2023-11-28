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
}
