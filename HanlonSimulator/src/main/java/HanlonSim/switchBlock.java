package HanlonSim;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class switchBlock extends GameObject{
    private ArrayList<GameState> levelList;
    private Handler handler;
    private int destination;
    private Game game;
    private BufferedImage image;
    private BufferedImageLoader loader;
    public switchBlock(int x, int y, ID id, SpriteSheet ss, ArrayList<GameState> levelList, Handler handler, int destination, Game game)
    {
        super(x,y,id,ss);
        this.levelList = levelList;
        this.handler = handler;
        this.destination = destination;
        this.game = game;
        this.image = ss.grabImage(16,1,32,32);
    }

    public void tick()
    {
        this.collision();
    }

    public void render(Graphics2D g)
    {
        g.drawImage(image, x, y, null);
    }
    public void collision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.hitBox().intersects(this.hitBox()) && tempObject.getId() == ID.PLAYERS)
            {
                game.loadLevel(levelList.get(destination).getLevel(), levelList.get(destination).getLight());
            }
        }
    }
    public Rectangle hitBox()
    {
        return new Rectangle(x,y,32,32);
    }
}