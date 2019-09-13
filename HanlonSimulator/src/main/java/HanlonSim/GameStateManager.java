package HanlonSim;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameStateManager
{
    public ArrayList<GameState> gameStates = new ArrayList<GameState>();
    private BufferedImageLoader loader = new BufferedImageLoader();
    public GameStateManager()
    {
        gameStates.add(new GameState(loader.loadImage("/images/TestLevel.png"),loader.loadImage("/images/lightMap.png"))); // index 0 testLevel
        gameStates.add(new GameState(loader.loadImage("/images/level2.png"),loader.loadImage("/images/lightMap.png"))); // index 1 testLevel
        gameStates.add(new GameState(loader.loadImage("/images/level3.png"),loader.loadImage("/images/lightMap.png"))); // index 1 testLevel
        
    }

    public BufferedImage getLevel(int x)
    {
        return gameStates.get(x).getLevel();
    }
    public BufferedImage getLight(int x)
    {
        return gameStates.get(x).getLight();
    }
    
    
}