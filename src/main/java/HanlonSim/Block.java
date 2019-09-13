package HanlonSim;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Block extends GameObject{
	private BufferedImage blockImage;
	public Block(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		blockImage = ss.grabImage(15, 1, 32, 32);
	}
	public void tick() {
		
	}
	public void render(Graphics2D g) {
		g.drawImage(blockImage,x,y,null);
	}
	public Rectangle hitBox() {
		return new Rectangle(x,y,32,32);
	}
}