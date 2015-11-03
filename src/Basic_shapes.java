import java.awt.Shape;

public class Basic_shapes {
	
	private Shape shape;
	private boolean alive;
	private double x, y;
	private double velX, velY;
	private double moveAngle, faceAngle;
	
	
	// gets and sets
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public double getMoveAngle() {
		return moveAngle;
	}

	public void setMoveAngle(double moveAngle) {
		this.moveAngle = moveAngle;
	}

	public double getFaceAngle() {
		return faceAngle;
	}

	public void setFaceAngle(double faceAngle) {
		this.faceAngle = faceAngle;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public Shape getShape(){
		return shape;
	}
	
	
	//Inc
	public void incX(double i){
		this.x += i;
	}
	public void incY(double i){
		this.y -= i;
	}
	public void incVelX(double i){
		this.velX += i;
	}
	public void incVelY(double i){
		this.velY += i;
	}
	public void incFaceAngle(double i){
		this.faceAngle += i;
	}
	public void incMoveAngle(double i){
		this.moveAngle += i;
	}
	
	Basic_shapes(){
		setShape(null);
		setAlive(false);
		setY(0.0);
		setX(0.0);
		setVelX(0.0);
		setVelY(0.0);
		setMoveAngle(0.0);
		setFaceAngle(0.0);
	}


	
	
	
	
}
