package HanlonSim;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Floor extends GameObject{
	BufferedImage image;
	public Floor(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		BufferedImageLoader loader = new BufferedImageLoader();
		image = loader.loadImage("/images/floor.png");
	}


	public void tick() {
	}


	public void render(Graphics2D g) {
		g.drawImage(image,0,0,null);
	}


	public Rectangle hitBox() {
		return new Rectangle(1,1,1,1);
	}

}
