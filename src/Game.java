import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Game extends Applet implements Runnable, KeyListener {

	
	int width = 600;
	int height = 800;
	private int cont1 = 6;
	private int contEnemy = 6;
	
	int cont = 0;
	
	int BULLETS = 30;
	PlayerBullet [] bullet = new PlayerBullet[BULLETS];
	
	int ENEMY = 10;
	Enemy [] enyms = new Enemy[ENEMY];
	
	BufferedImage backbuffer;
	
	Graphics2D g2d;
	int currentBullet =0;
	Thread gameloop;
	
	
	Player p = new Player();
	Enemy eny = new Enemy();
	AffineTransform identity = new AffineTransform();
	
	private int[][]gameSpace = new int[16][12];
	
	public void init(){
		backbuffer = new BufferedImage(600,800, BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();
		
		for(int n =0; n<BULLETS; n++){
			bullet[n] = new PlayerBullet();
		}
		for(int n=0; n<ENEMY; n++){
			enyms[n] = new Enemy();
		}
		this.setSize(600, 800);
		addKeyListener(this);
		
		
		for(int cont = 0; cont < 16; cont++){
			for(int cont2 = 0; cont2 < 12; cont2++){
				gameSpace[cont][cont2] = 0;
			}
		}
		//gameSpace[10][cont1] = 0;
		
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(0, 0, 600, 800);
	}
	
	public void paint(Graphics g){
		g.drawImage(backbuffer, 0, 0, this);
	}
	
	public void update(Graphics g){
		g2d.setTransform(identity);
		
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
	
		drawPlayer();
		drawPlayerBullet();
		drawEnemy();
		paint(g);
		
	}
	
	public void drawPlayer(){
		
		
		g2d.setTransform(identity);
		//gameSpace = p.getPlayerPosition(cont1, g2d, gameSpace);
		g2d.translate(cont1 * 50, 15 * 50);
		//g2d.translate(5 * 50, 10 * 50);
		g2d.scale(2 , 2);
	
		g2d.setColor(Color.RED);
		g2d.fill(p.getShape());
	}
	public void drawEnemy(){
		for(int n =0; n<ENEMY; n++){
			if(enyms[n].isAlive()){
				g2d.setTransform(identity);
				//gameSpace = p.getPlayerPosition(cont1, g2d, gameSpace);
				g2d.translate(enyms[n].getX(), enyms[n].getY());
				//g2d.translate(5 * 50, 10 * 50);
				g2d.scale(2 , 2);
			
				g2d.setColor(Color.DARK_GRAY);
				g2d.fill(enyms[n].getShape());
			}
		}
	}
	public void drawPlayerBullet(){
		
		for(int n =0; n<BULLETS; n++){
			if (bullet[n].isAlive()){
				g2d.setTransform(identity);
				g2d.translate(bullet[n].getX(), bullet[n].getY());
				g2d.setColor(Color.WHITE);
				g2d.draw(bullet[n].getShape());
			}
		}
	}
	
	
	public void start(){
		gameloop = new Thread(this);
		gameloop.start();
		
	}
	public void run(){
		Thread t = Thread.currentThread();
		
		while(t == gameloop){
			try{
				gameUpdate();
				
				Thread.sleep(32);
				
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			repaint();
		}
	}
	public void stop(){
		gameloop = null;
	}
	
	public void gameUpdate(){
		updatePlayer();
		updateBullets();
		updateEnemy();
		checkCollision();
		
	}
	
	public void updatePlayer(){
		for(int cont2 = 0; cont2 < 12; cont2++){
			if(gameSpace[15][cont2] == 1){
				cont1 = cont2;
				break;
			}
		}
	}
	
	public void checkCollision() {
		for(int i=0; i<ENEMY;i++){
			if(enyms[i].isAlive()){
				for(int n = 0; n < BULLETS; n++){
					if(bullet[n].isAlive()){
						if (enyms[n].getBounds().contains(bullet[n].getX() , bullet[n].getY())){
							bullet[n].setAlive(false);
							enyms[i].setAlive(false);
							System.out.println("col");
						}
					}
				}
			}
		}
	}
	
	public void updateBullets() {
		// TODO Auto-generated method stub
		for(int n =0; n<BULLETS; n++){
			//this object being used?
			if(bullet[n].isAlive()){
				// update bullet x posi
				bullet[n].incX(bullet[n].getVelX());
				if(bullet[n].getX() < 0 || bullet[n].getX() > getSize().width){
					bullet[n].setAlive(false);
				}
				// update bullet y posi
				bullet[n].incY(bullet[n].getVelY());
				if(bullet[n].getY() < 0 || bullet[n].getY() > getSize().height){
					bullet[n].setAlive(false);
				}
				
			}
		}
		
	}
	
	public void updateEnemy(){
		cont++;
		if(cont == 20){
			cont =0;
			contEnemy +=1;
		}
		else if(contEnemy == 12){
			cont =0;
			contEnemy = 0;
		}
		for(int n=0; n<ENEMY; n++){
			
			if(enyms[n].isAlive()){
				
				enyms[n].setY(3 * 50);
				enyms[n].setX(contEnemy * 50);
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			gameSpace[15][cont1] =0;
			cont1--;
			if(cont1 == 0){
				cont1 = 11;
			}
			gameSpace[15][cont1] = 1;
			System.out.println(cont1);
		//	repaint();
			for(int cont = 0; cont < 16; cont++){
				for(int cont2 = 0; cont2 < 12; cont2++){
					System.out.print(gameSpace[cont][cont2] + " ");
				}
				System.out.println();
			}
			
			
			
			break;
		case KeyEvent.VK_RIGHT:
			gameSpace[15][cont1] =0;
			cont1++;
			if(cont1 == 12){
				cont1 = 1;
			}
			gameSpace[15][cont1] = 1;
			System.out.println(cont1);
		//	repaint();
			for(int cont = 0; cont < 16; cont++){
				for(int cont2 = 0; cont2 < 12; cont2++){
					System.out.print(gameSpace[cont][cont2] + " ");
				}
				System.out.println();
			}
			
			break;
		case KeyEvent.VK_SPACE:
			currentBullet++;
			if(currentBullet > BULLETS -1)
				currentBullet =0;			
			bullet[currentBullet].setAlive(true);
			
			bullet[currentBullet].setX(50*cont1);
			bullet[currentBullet].setY(15*50);
			bullet[currentBullet].setVelX(0);
			bullet[currentBullet].setVelY(5);
			
			//		repaint();
			break;
		default:
			
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
