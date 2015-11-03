import java.awt.Rectangle;

public class PlayerBullet extends Basic_shapes{
	public Rectangle getBounds(){
		Rectangle r;
		r = new Rectangle((int)getX(), (int)getY(), 1,1 );
		return r;
	}
	PlayerBullet(){
		setShape(new Rectangle(0,0,3,3));
		setAlive(false);
	}
}
