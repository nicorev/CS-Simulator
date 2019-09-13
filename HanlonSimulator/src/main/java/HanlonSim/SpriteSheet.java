package HanlonSim;
import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage image;
	int x, y;
	public SpriteSheet(BufferedImage image)
	{
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height)
	{
		x = col;
		y = row;
		return image.getSubimage((col*32)-32, (row*32)-32, width, height);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
}
