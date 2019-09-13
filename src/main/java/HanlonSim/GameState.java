package HanlonSim;

import java.awt.image.BufferedImage;

public class GameState
{
    private BufferedImage levelMap;
    private BufferedImage lightMap;
    public GameState(BufferedImage levelMap, BufferedImage lightMap)
    {
        this.levelMap = levelMap;
        this.lightMap = lightMap;
    }
    public BufferedImage getLevel()
    {
        return levelMap;
    }
    public BufferedImage getLight()
    {
        return lightMap;
    }
}