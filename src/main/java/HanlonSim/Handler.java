package HanlonSim;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class Handler {
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	ArrayList<GameObject> projectiles = new ArrayList<GameObject>();
	private boolean up = false, down = false, right = false, left = false;
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public void tick()
	{
	
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
		for(int i = 0; i < projectiles.size(); i++){
			GameObject tempObject = projectiles.get(i);
			tempObject.tick();
		}
	}

	public void render(Graphics2D g, Window window)
	{
		g.scale((double)window.frame.getWidth()/1280, (double)window.frame.getHeight()/800);
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			if(tempObject.getId() == ID.PLAYERS) ((Player) tempObject).scale(window);
			tempObject.render(g);
		}
	}
	public void addObject(GameObject tempObject)
	{
		object.add(tempObject);
	}
	public void removeObject(GameObject tempObject)
	{
		object.remove(tempObject);
	}
}
