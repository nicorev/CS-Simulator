package HanlonSim;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pickup extends GameObject {
	private int listPos;
	Handler handler;
	public	Pickup(int x, int y, ID id, Handler handler, SpriteSheet ss){
			super(x,y,id, ss);
			this.handler = handler;
			
		}
	public void tick() {
		listPos = handler.object.lastIndexOf(this);
		
	}

	public void render(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 32, 32);
	}
	
	public Rectangle hitBox() {
		return new Rectangle(x,y,32,32);
	}
	public int getListPos()
	{
		return listPos;
	}
}
