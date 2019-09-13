package HanlonSim;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class LightingObject{
	private int radius;
	private int x;
	private int y;
	private BufferedImage lightMap;
	private int lightBlend;
	private float[] dist = {0.0f,0.5f,1.0f};
	private Color[] alpha = {new Color(0,255,0,255),new Color(0,255,0,127),new Color(0,255,0,0)};
	AlphaComposite originalComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
	public LightingObject(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		lightBlend = 17;

	}
	public void tick()
	{

	}
	public void render(Graphics2D g)
	{
		g.setComposite(AlphaComposite.DstOut);
		g.setPaint(new RadialGradientPaint(x,y,300,dist,alpha));
		g.fillOval(x-(600/2),y-(600/2),600,600);
		g.setComposite(originalComposite);
	}
	public Rectangle hitBox()
	{
		return new Rectangle(0,0,0,0);
	}


}
