import java.awt.Graphics;
import java.awt.Polygon;

public class Player extends Basic_shapes{

	private int[]pontosX = { 0, 5 , 0, -5};
	private int[]pontosY = { -10, 5, 0, 5};
	
	
	public int[][] getPlayerPosition(int cont1,Graphics g, int [][]gameSpace ){
			for(int cont2 = 0; cont2 < 12; cont2++){
				if(gameSpace[10][cont2] == 1){
					g.translate(cont2 * 50, 10 * 50);
				}
			}
		
		gameSpace[10][cont1] = 0;
		return gameSpace;
	}
	Player(){
		setShape(new Polygon(pontosX, pontosY, 4));
		setAlive(true);
		
	}
}
