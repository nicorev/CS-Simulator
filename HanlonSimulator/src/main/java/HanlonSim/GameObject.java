package HanlonSim;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public abstract class GameObject {
	public int x, y;
	public float velX = 0, velY = 0;
	public ID id;
	public SpriteSheet ss;
	private int health;
	public GameObject(int x, int y, ID id, SpriteSheet ss)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		this.ss = ss;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public abstract void tick();
	public abstract void render (Graphics2D g);
	public abstract Rectangle hitBox();
	public int getX() {
		return x;
	}
	public int getHealth()
	{
		return health;
	}
	public void damage(int x)
	{

	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public float getVelX() {
		return velX;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public float getVelY() {
		return velY;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public double getDistance(GameObject object)
	{
		double distance = Math.hypot(object.hitBox().getCenterX() - this.hitBox().getCenterX(), object.hitBox().getCenterY() - this.hitBox().getCenterY());
		return distance;
	}
	public boolean collidesWith(GameObject testObject)
	{
		return this.hitBox().getBounds2D().intersects(testObject.hitBox().getBounds2D());
	}

}
