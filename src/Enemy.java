import java.awt.Rectangle;

public class Enemy extends Basic_shapes{
	Enemy(){
		setShape(new Rectangle(0,0,20,20));
		setAlive(true);
	}
	
	public Rectangle getBounds(){
		Rectangle r;
		r = new Rectangle((int)getX(), (int) getY(), 30, 30);
		return r;
	}
	
	public void print(){
		System.out.println("X: " + this.getX());
		System.out.println("Y: " + this.getY());
		System.out.println("Altura: " + this.getBounds().getHeight());
		System.out.println("Altura: " + this.getBounds().getWidth());
	}
}
