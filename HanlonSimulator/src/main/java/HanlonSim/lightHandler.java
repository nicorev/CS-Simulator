package HanlonSim;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
public class lightHandler
{
    BufferedImage lightMap;
    public lightHandler(BufferedImage lightMap)
    {
        this.lightMap = lightMap;
    }
    public void renderLight(Graphics2D g)
    {
        int w = lightMap.getWidth();
        int h = lightMap.getHeight();
        for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				int pixel = lightMap.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
                if(blue == 255 && green == 255 && red == 255) 
                {
                    new LightingObject(x*32, y*32, 255).render(g);
                }
			}
		}
    }
}